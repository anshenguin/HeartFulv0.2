package com.example.hp.heartful;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by HP INDIA on 08-Apr-17.
 */

public class FragmentOne extends Fragment {
    public FragmentOne(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_list, container, false);
        super.onCreate(savedInstanceState);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.category_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinadapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.category_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(spinadapter);
        // Create a list of words
        ArrayList<OrgInfo> Organisations = new ArrayList<OrgInfo>();
        Organisations.add(new OrgInfo("AASRA", "do you wish you could help yourself well now you can lol", R.drawable.heart));
        Organisations.add(new OrgInfo("AASRA", "do you wish you could help yourself well now you can lol", R.drawable.heart));
        Organisations.add(new OrgInfo("AASRA", "do you wish you could help yourself well now you can lol", R.drawable.heart));
        Organisations.add(new OrgInfo("AASRA", "do you wish you could help yourself well now you can lol", R.drawable.heart));
        Organisations.add(new OrgInfo("AASRA", "do you wish you could help yourself well now you can lol", R.drawable.heart));
        Organisations.add(new OrgInfo("AASRA", "do you wish you could help yourself well now you can lol", R.drawable.heart));
        Organisations.add(new OrgInfo("AASRA", "do you wish you could help yourself well now you can lol", R.drawable.heart));
        Organisations.add(new OrgInfo("AASRA", "do you wish you could help yourself well now you can lol", R.drawable.heart));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        OrgInfoAdapter adapter = new OrgInfoAdapter(getActivity(), Organisations);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        return rootView;
    }


}
