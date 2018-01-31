package com.example.gebruiker.pokemon;

import android.widget.ListView;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by javiergonzalezcabezas
 * (https://github.com/thedeveloperworldisyours/ListViewInsideScrollView/blob/master/src/main/java/com/thedeveloperworldisyours/listviewinsidescrollview/view/NonScrollListView.java)
 * on 1/5/15.
 *
 * NonScrollListView is used as ListView in ScrollView
 */
class NonScrollListView extends ListView {

    public NonScrollListView(Context context) {
        super(context);
    }
    public NonScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public NonScrollListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = View.MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }
}