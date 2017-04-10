package com.example.hp.heartful;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HP INDIA on 10-Apr-17.
 */

public class OrgInfoAdapter extends ArrayAdapter {

    public OrgInfoAdapter(Context context, ArrayList<OrgInfo> Organisations) {
        super(context, 0, Organisations);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.home_list_item, parent, false);

        }
        OrgInfo currentOrg = (OrgInfo) getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view.
        TextView orgName = (TextView) listItemView.findViewById(R.id.org_name);
        // Get the Miwok translation from the currentWord object and set this text on
        // the Miwok TextView.
        orgName.setText(currentOrg.getOrgname());

        // Find the TextView in the list_item.xml layout with the ID default_text_view.
        TextView orgInfo = (TextView) listItemView.findViewById(R.id.org_about);
        // Get the default translation from the currentWord object and set this text on
        // the default TextView.
        orgInfo.setText(currentOrg.getOrginfo());

        // Find the ImageView in the list_item.xml layout with the ID image.
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.org_logo);
        // Check if an image is provided for this word or not
        if (currentOrg.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentOrg.getImageResourceId());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
