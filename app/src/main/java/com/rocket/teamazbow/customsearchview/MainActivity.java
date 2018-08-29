package com.rocket.teamazbow.customsearchview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearchDialog();
            }
        });

    }


    private void showSearchDialog(){

        new SearchDialog(getSupportFragmentManager() , this)
                .setBackIconColor(R.color.colorAccent)
                .setSearchIconColor(R.color.colorAccent)
                .setBottomDividerColor(R.color.colorAccent)
                .setSearchHint("Whats Up?")
                .setSearchableActivity(Activity2.class)
                .show();

    }


}
