package com.meteoru.kalei.munch1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class IssuesAdapter extends ArrayAdapter<Issue> {

    public IssuesAdapter(Context context, ArrayList<Issue> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Issue issue = getItem(position);
        // Check if an existing view is being reused
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_issue, parent, false);
        }
        // Get references and populate views
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);
        tvTitle.setText(issue.getTitle());
        tvBody.setText(issue.getBody());
        return convertView;
    }
}
