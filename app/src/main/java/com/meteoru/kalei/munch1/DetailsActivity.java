package com.meteoru.kalei.munch1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    TextView tvTitle, tvUrl, tvComments_url, tvHtml_url, tvUser_login,
            tvState, tvUpdatedAt, tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvUrl = (TextView) findViewById(R.id.tvUrl);
        tvComments_url = (TextView) findViewById(R.id.tvComments_url);
        tvHtml_url = (TextView) findViewById(R.id.tvHtml_url);
        tvUser_login = (TextView) findViewById(R.id.tvUser_login);
        tvState = (TextView) findViewById(R.id.tvState);
        tvUpdatedAt = (TextView) findViewById(R.id.tvUpdatedAt);
        tvBody = (TextView) findViewById(R.id.tvBody);

        Bundle stuff = getIntent().getExtras();
        tvTitle.setText(stuff.getString("title", "Default Title"));
        tvUrl.setText(stuff.getString("url", "https://something.com"));
        tvComments_url.setText(stuff.getString("comments_url", "https://something.com"));
        tvHtml_url.setText(stuff.getString("html_url", "https://something.com"));
        tvUser_login.setText(stuff.getString("user_login", "Joe Jackson"));
        tvState.setText(stuff.getString("state", "State not found"));
        tvUpdatedAt.setText(stuff.getString("updated_at", "Update time not found"));
        tvBody.setText(stuff.getString("body", "Body not found.").subSequence(0, 139));
    }

}
