package com.rocket.teamazbow.customsearchview;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class SearchDialogManager {

    public static String SEARCH = "search_dialog_query";

    private Context context;

    SearchDialogManager(Context context){
        this.context = context;
    }


    public void saveSearchQuery(String query){
        SuggestionDBHelper.getInstance(context).saveSearchQuery(query);
    }

    public List<String> getSearchHistory(){
        try {
            return SuggestionDBHelper.getInstance(context).getRecentSearchQuerys();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return null;
        }
    }

    public void clearSearchHistory(){
        SuggestionDBHelper.getInstance(context).clearData();
    }


}
