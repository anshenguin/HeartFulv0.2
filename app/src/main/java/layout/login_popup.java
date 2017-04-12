package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.heartful.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link login_popup.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link login_popup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class login_popup extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_popup, container, false);
    }
    public void hidePopup(View view){

    }
}
