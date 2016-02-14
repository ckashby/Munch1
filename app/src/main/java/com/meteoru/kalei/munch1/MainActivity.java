package com.meteoru.kalei.munch1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.*;
import cz.msebera.android.httpclient.Header;
import org.json.JSONArray;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://api.github.com/repos/ReactiveX/RxJava/issues";
    ArrayList<Issue> issuesArray;
    ListView lvResults;
    ArrayAdapter<Issue> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvResults = (ListView) findViewById(R.id.lvResults);
        issuesArray = new ArrayList<Issue>();
        adapter = new ArrayAdapter<Issue>(this,
                android.R.layout.simple_list_item_1, issuesArray);
        lvResults.setAdapter(adapter);
        
        AsyncHttpClient client = new AsyncHttpClient();
        client.setUserAgent("ckashby");
        RequestParams params = new RequestParams();
        params.put("User-Agent", "ckashby");
        params.put("url", "url");
        params.put("title", "title");
        client.get(BASE_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseArray) {
                // called when response HTTP status is "200"
                try {
                    issuesArray = Issue.fromJsonArray(responseArray);
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Toast.makeText(getBaseContext(), "Request for JSON data failed.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
