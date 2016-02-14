package com.meteoru.kalei.munch1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Issue {
    public String url, title, comments_url, html_url, user_login, state;

    public Issue() {}

    public Issue(String url, String title, String comments_url, String html_url,
                 String user_login, String state) {
        this.url = url;
        this.title = title;
        this.comments_url = comments_url;
        this.html_url = html_url;
        this.user_login = user_login;
        this.state = state;

    }

    public String getUrl() { return url; }
    public String getTitle() { return title; }
    public String getComments_url() { return comments_url; }
    public String getHtml_url() { return  html_url; }
    public String getUser_login() { return user_login; }
    public String getState() { return state; }

    public void setUrl(String url) { this.url = url; }
    public void setTitle(String title) { this.title = title; }
    public void setComments_url(String comments_url) { this.comments_url = comments_url; }
    public void setHtml_url(String html_url) { this.html_url = html_url; }
    public void setUser_login(String user_login) { this.user_login = user_login; }
    public void setState(String state) { this.state = state; }

    public static Issue fromJson(JSONObject jsonObject) {
        Issue issue = new Issue();
        // Deserialize json into object fields
        try {
            issue.setUrl(jsonObject.getString("url"));
            issue.setTitle(jsonObject.getString("title"));
            issue.setComments_url(jsonObject.getString("comments_url"));
            issue.setHtml_url(jsonObject.getString("html_url"));
            issue.setUser_login(jsonObject.getJSONObject("user").getString("login"));
            issue.setState(jsonObject.getString("state"));
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
