package com.example.hp.heartful;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import static android.R.attr.width;

/**
 * Created by HP INDIA on 08-Apr-17.
 */

public class FragmentThree extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_three, container, false);

    }


    @Override
    public void setMenuVisibility(final boolean visible) {
        if (visible) {
            FragmentManager fm = getFragmentManager();
            login_dialogbox dialogFragment = new login_dialogbox ();
            dialogFragment.show(fm,"LoginPopup");
        }

        super.setMenuVisibility(visible);
    }
}