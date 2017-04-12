package com.example.hp.heartful;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by HP INDIA on 08-Apr-17.
 */

public class FragmentThree extends Fragment {

    short number = 1;
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view_popup = inflater.inflate(R.layout.fragment_login_popup, container, false);
        final View view_signup = inflater.inflate(R.layout.tab_three, container, false);

        ImageButton cross = (ImageButton) view_popup.findViewById(R.id.cross);
        cross.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                number = 0;
            }
        });

        if(number == 1)
            return view_popup;
        else
            return view_signup;
    }

    //    public void onClick(View view) {

//        Fragment fragment2 = new Fragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id. );
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }

//    final ImageButton button = (ImageButton) findViewById(R.id.cross);
//         button.setOnClickListener(new View.OnClickListener() {
//        public void onClick(View v) {
//            // Perform action on click
//        }
//    });

}
