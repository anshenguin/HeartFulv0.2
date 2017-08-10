package com.example.hp.heartful;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by HP INDIA on 08-Apr-17.
 */

public  class FragmentOne extends Fragment  {
    public FragmentOne() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.home_list, container, false);
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
        Organisations.add(new OrgInfo("Sada Suhaagan Raho", "test info", R.drawable.heart));
        Organisations.add(new OrgInfo("NGO with a really big name", "but an average info length", R.drawable.heart));
        Organisations.add(new OrgInfo("WHO", "are you?", R.drawable.heart));
        Organisations.add(new OrgInfo("aaaaaaaaaaabbbbbbbbbbbccccccccccccc", "aaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbccccccccccccaaaaaaaaaaaacccccccccc", R.drawable.heart));
        Organisations.add(new OrgInfo("chhota naam", "but info itni bdi ki chhote devices mein info view ke baahar chle jaae and last mein teen chhote dots dikhein haha", R.drawable.heart));
        Organisations.add(new OrgInfo("Jaago Graahak Jaago", "Apne Adhikaar ko Jaano, Apne Hakk ko Pehchano. Jaago, Graahak Jaago!", R.drawable.heart));
        Organisations.add(new OrgInfo("Satyamev Jayate", "Saccha hai pyaar tera!", R.drawable.heart));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
         final OrgInfoAdapter adapter = new OrgInfoAdapter(getActivity(), Organisations);
        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent myIntent = new Intent(getActivity(), orgInsideActivity.class);
                getActivity().startActivity(myIntent);
            }
        });
       final   EditText searchItem= (EditText)rootView. findViewById(R.id.search_item);

        // Capture Text in EditText
        searchItem.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = searchItem.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
        return rootView;
    }
    }



