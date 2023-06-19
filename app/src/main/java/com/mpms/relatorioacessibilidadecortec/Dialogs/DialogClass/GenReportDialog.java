package com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;


public class GenReportDialog extends DialogFragment {

    static GenReportDialog fragment;

    public static GenReportDialog showProgressDialog(FragmentManager manager) {
        fragment = new GenReportDialog();
        fragment.show(manager, "PROGRESS_DIALOG");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gen_report_dialog, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            int length = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, length);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
        }
    }

    public static void closeDialog(FragmentManager manager) {
        manager.beginTransaction().remove(fragment).commit();
    }
}