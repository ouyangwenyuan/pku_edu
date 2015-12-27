package com.pku.pkuapp.widget;

import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.pku.pkuapp.R;


public class ShowPicView extends RelativeLayout implements OnTouchListener {

    private int innerw;
    private int curi;
    private int itc;
    private int[] bers;
    private int lle = 0;
    private int rre = 0;
    private float x1;
    private float x2;
    private float x3;
    private LinearLayout ilil;
    private LinearLayout dldl;
    private View firi = null;
    private MarginLayoutParams fip = null;
    private VelocityTracker vct;

    public ShowPicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void scrollToNext() {
        new ScrollTask().execute(-20);
    }

    public void scrollToPrevious() {
        new ScrollTask().execute(20);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            initializeItems();
            initializeDots();
        }
    }

    private void initializeItems() {
        innerw = getWidth();
        ilil = (LinearLayout) getChildAt(0);
        itc = ilil.getChildCount();

        Button newItem = new Button(getContext());
        newItem.setWidth(LayoutParams.MATCH_PARENT);
        newItem.setHeight(LayoutParams.WRAP_CONTENT);
        newItem.setBackground(getResources().getDrawable(R.drawable.show_picture_1));
        ilil.addView(newItem);
        int initItemsCount = itc + 1;

        bers = new int[initItemsCount];
        for (int i = 0; i < initItemsCount; i++) {
            bers[i] = -i * innerw;
            View item = ilil.getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) item.getLayoutParams();
            params.width = innerw;
            item.setLayoutParams(params);
            item.setOnTouchListener(this);
        }
        lle = bers[initItemsCount - 1];
        firi = ilil.getChildAt(0);
        fip = (MarginLayoutParams) firi.getLayoutParams();
    }

    private void initializeDots() {
        dldl = (LinearLayout) getChildAt(1);
        refreshDotsLayout();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        createVelocityTracker(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                x2 = event.getRawX();
                int distanceX = (int) (x2 - x1) - (curi * innerw);
                fip.leftMargin = distanceX;
                if (beAbleToScroll()) {
                    if (fip.leftMargin < (lle + innerw) || curi >= itc) {
                        curi = 0;
                        fip.leftMargin = 0;
                    }
                    if (fip.leftMargin > 0 || curi < 0) {
                        curi = 0;
                        fip.leftMargin = 0;
                    }
                    firi.setLayoutParams(fip);
                }
                break;
            case MotionEvent.ACTION_UP:
                x3 = event.getRawX();
                if (beAbleToScroll()) {
                    if (wantScrollToPrevious()) {
                        if (shouldScrollToPrevious()) {
                            curi--;
                            scrollToPrevious();
                            refreshDotsLayout();
                        } else {
                            scrollToNext();
                        }
                    } else if (wantScrollToNext()) {
                        if (shouldScrollToNext()) {
                            curi++;
                            scrollToNext();
                            refreshDotsLayout();
                        } else {
                            scrollToPrevious();
                        }
                    }
                }
                recycleVelocityTracker();
                break;
        }
        return false;
    }

    private boolean beAbleToScroll() {
        return fip.leftMargin < rre && fip.leftMargin > lle + innerw;
    }

    private boolean wantScrollToPrevious() {
        return x3 - x1 > 0;
    }

    private boolean wantScrollToNext() {
        return x3 - x1 < 0;
    }

    private boolean shouldScrollToNext() {
        return x1 - x3 > innerw / 2 || getScrollVelocity() > 200;
    }

    private boolean shouldScrollToPrevious() {
        return x3 - x1 > innerw / 2 || getScrollVelocity() > 200;
    }

    private void refreshDotsLayout() {
        dldl.removeAllViews();
        for (int i = 0; i < itc; i++) {
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(0,
                    LayoutParams.MATCH_PARENT);
            linearParams.weight = 1;
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            ImageView image = new ImageView(getContext());
            if (i == curi % itc) {
                image.setBackgroundResource(R.drawable.dot_1);
            } else {
                image.setBackgroundResource(R.drawable.dot_2);
            }
            LayoutParams relativeParams = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            relativeParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            relativeLayout.addView(image, relativeParams);
            dldl.addView(relativeLayout, linearParams);
        }
    }

    private void createVelocityTracker(MotionEvent event) {
        if (vct == null) {
            vct = VelocityTracker.obtain();
        }
        vct.addMovement(event);
    }

    private int getScrollVelocity() {
        vct.computeCurrentVelocity(1000);
        int velocity = (int) vct.getXVelocity();
        return Math.abs(velocity);
    }

    private void recycleVelocityTracker() {
        vct.recycle();
        vct = null;
    }

    private boolean isCrossBorder(int leftMargin, int speed) {
        for (int border : bers) {
            if (speed > 0) {
                if (leftMargin >= border && leftMargin - speed < border) {
                    return true;
                }
            } else {
                if (leftMargin <= border && leftMargin - speed > border) {
                    return true;
                }
            }
        }
        return false;
    }

    private int findClosestBorder(int leftMargin) {
        int absLeftMargin = Math.abs(leftMargin);
        int closestBorder = bers[0];
        int closestMargin = Math.abs(Math.abs(closestBorder) - absLeftMargin);
        for (int border : bers) {
            int margin = Math.abs(Math.abs(border) - absLeftMargin);
            if (margin < closestMargin) {
                closestBorder = border;
                closestMargin = margin;
            }
        }
        return closestBorder;
    }

    class ScrollTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... speed) {
            if (fip.leftMargin < lle) {
                curi = 0;
                fip.leftMargin = 0;
            }
            if (fip.leftMargin > 0 || curi < 0) {
                curi = 0;
                fip.leftMargin = 0;
            }

            int leftMargin = fip.leftMargin;
            //TODO do what
//            while (true) {
//                leftMargin = leftMargin + speed[0];
//                if (isCrossBorder(leftMargin, speed[0])) {
//                    leftMargin = findClosestBorder(leftMargin);
//                    break;
//                }
//                publishProgress(leftMargin);
//            }
            return leftMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... leftMargin) {
            fip.leftMargin = leftMargin[0];
            firi.setLayoutParams(fip);
        }

        @Override
        protected void onPostExecute(Integer leftMargin) {
            fip.leftMargin = leftMargin;
            firi.setLayoutParams(fip);
            if (fip.leftMargin < (lle + innerw) || curi >= itc) {
                curi = 0;
                fip.leftMargin = 0;
            }
            if (fip.leftMargin > 0 || curi < 0) {
                curi = 0;
                fip.leftMargin = 0;
            }
        }
    }

    public void gotoFirst() {
        if (firi != null && fip != null) {
            fip.leftMargin = 0;
            firi.setLayoutParams(fip);
            curi = 0;
            refreshDotsLayout();
        }
    }

}  