package com.meteoru.kalei.munch1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Issue {
    public String url;
    public String title;

    public Issue() {}

    public Issue(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Issue fromJson(JSONObject jsonObject) {
        Issue issue = new Issue();
        // Deserialize json into object fields
        try {
            issue.setUrl(jsonObject.getString("url"));
            issue.setTitle(jsonObject.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return issue;
    }

    public static ArrayList<Issue> fromJsonArray(JSONArray jsonArray) {
        ArrayList<Issue> issues = new ArrayList<>(jsonArray.length());
        // Process each result in json array, decode and convert to Issue object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject issueJson = null;
            try {
                issueJson = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
            Issue myIssue = Issue.fromJson(issueJson);
            if (myIssue != null) {
                issues.add(myIssue);
            }
        }
        return issues;
    }
}
