package com.meteoru.kalei.munch1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetailsActivity extends AppCompatActivity {
    TextView tvTitle, tvBody, tvComments;
    Bundle stuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvBody = (TextView) findViewById(R.id.tvBody);
        tvComments = (TextView) findViewById(R.id.tvComments);

        stuff = getIntent().getExtras();
        tvTitle.setText(stuff.getString("title", "Title not found."));
        tvBody.setText(stuff.getString("body", "Body not found."));

        fetchComments();
    }

    private void fetchComments() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setUserAgent("ckashby");
        RequestParams params = new RequestParams();
        String url = stuff.getString("comments_url");
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseArray) {
                StringBuilder stringBuilder = new StringBuilder();
                String formatString = "comment:%s\nauthor:%s\n";
                for (int i = 0; i < responseArray.length(); i++) {
                    try {
                        String author = responseArray.getJSONObject(i).getJSONObject("user").getString("login");
                        String comment = responseArray.getJSONObject(i).getString("body");
                        stringBuilder.append(String.format(formatString, comment, author));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                tvComments.setText(stringBuilder.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

}
