package com.rocket.teamazbow.customsearchview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.RawRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

public class SearchDialog {

    private FragmentManager fragmentManager;
    private Context context;

    private int backIconColor;
    private int searchIconColor;
    private int bottomDividerColor;
    private int searchTextColor;
    private String searchHint;
    private Class searchableActivity;

    SearchDialog(FragmentManager fragmentManager, Context context){
        this.fragmentManager =fragmentManager;
        this.context = context;

        //Default values
    }

    public SearchDialog setBackIconColor(@ColorRes int iconColor) {
        this.backIconColor = ContextCompat.getColor(context , iconColor);

        return this;
    }

    public SearchDialog setBackIconColor(String colorCode) {
        try {
            this.backIconColor = Color.parseColor(colorCode);
        } catch (Exception e){
            e.printStackTrace();
        }

        return this;
    }

    public SearchDialog setSearchIconColor(@ColorRes int iconColor) {
        this.searchIconColor = ContextCompat.getColor(context , iconColor);

        return this;
    }

    public SearchDialog setSearchIconColor(String colorCode) {
        try {
            this.searchIconColor = Color.parseColor(colorCode);
        } catch (Exception e){
            e.printStackTrace();
        }

        return this;
    }

    public SearchDialog setBottomDividerColor(@ColorRes int iconColor) {
        this.bottomDividerColor = ContextCompat.getColor(context , iconColor);

        return this;
    }

    public SearchDialog setBottomDividerColor(String colorCode) {
        try {
            this.bottomDividerColor = Color.parseColor(colorCode);
        } catch (Exception e){
            e.printStackTrace();
        }

        return this;
    }

    public SearchDialog setSearchTextColor(@ColorRes int iconColor) {
        this.searchTextColor = ContextCompat.getColor(context , iconColor);

        return this;
    }

    public SearchDialog setSearchTextColor(String colorCode) {
        try {
            this.searchTextColor = Color.parseColor(colorCode);
        } catch (Exception e){
            e.printStackTrace();
        }

        return this;
    }

    public SearchDialog setSearchHint(String searchHint){
        this.searchHint = searchHint;
        return this;
    }

    public SearchDialog setSearchableActivity(Class searchableActivity){
        this.searchableActivity = searchableActivity;
        return this;
    }

    public SearchDialogFragment show() {
        Bundle bundle = new Bundle();
        bundle.putInt(SearchDialogFragment.BACK_ICON_COLOR, backIconColor);
        bundle.putInt(SearchDialogFragment.SEARCH_ICON_COLOR, searchIconColor);
        bundle.putInt(SearchDialogFragment.BOTTOM_DIVIDER_COLOR, bottomDividerColor);
        bundle.putInt(SearchDialogFragment.SEARCH_TEXT_COLOR, searchTextColor);
        bundle.putString(SearchDialogFragment.SEARCH_HINT, searchHint);
        bundle.putString(SearchDialogFragment.TARGET_ACTIVITY, searchableActivity.getCanonicalName());

        SearchDialogFragment fragment =  SearchDialogFragment.Companion.newInstance(bundle);
        fragment.show(fragmentManager, "search_dialog");

        return fragment;
    }



}
