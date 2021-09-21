package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.mpms.relatorioacessibilidadecortec.R;

public class AddSidewalkSlopeDialog extends DialogFragment {

    static Bundle sidewalkBundle = new Bundle();
    Bundle sidewalkBundleTwo = new Bundle();

    public static AddSidewalkSlopeDialog sidewalkSlope(FragmentManager fragmentManager, Bundle bundle) {
        AddSidewalkSlopeDialog sidewalkSlope = new AddSidewalkSlopeDialog();
        sidewalkSlope.show(fragmentManager, "SLOPE_DIALOG");
        sidewalkBundle = bundle;
        return sidewalkSlope;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_sidewalk_slope_dialog, container, false);
    }
}