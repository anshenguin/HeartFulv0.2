package com.example.hp.heartful;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by HP INDIA on 08-Apr-17.
 */

public class FragmentTwo extends Fragment {
    private Button Addvalue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tab_two, container, false);
        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        Addvalue=(Button)view.findViewById(R.id.addValue);
        Addvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mref= rootRef.child("Name");
                mref.setValue("Gulshan");
            }
        });
        return view;

    }

}
