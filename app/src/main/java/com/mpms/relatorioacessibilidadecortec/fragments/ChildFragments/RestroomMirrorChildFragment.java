package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.fragments.RestroomMirrorUrinalFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelFragments;


public class RestroomMirrorChildFragment extends Fragment {

    public static final String MIRROR_A = "MIRROR_A";
    public static final String MIRROR_B = "MIRROR_B";
    public static final String MIRROR_OBS = "MIRROR_OBS";
    public static final String FILL_MIRROR = "FILL_MIRROR";
    public static final String MIRROR_ID = "MIRROR_ID";

    TextInputLayout measureFieldA, measureFieldB, mirrorObsField;
    TextInputEditText measureValueA, measureValueB, mirrorObsValue;

    Double measureA, measureB;
    String mirrorObs;

    ImageButton mirror;
    Bundle imgBundleMirror = new Bundle();
    Bundle mirrorDataBundle = new Bundle();

    ViewModelFragments modelFragments;

    public RestroomMirrorChildFragment() {
        // Required empty public constructor
    }

    public static RestroomMirrorChildFragment newInstance() {
        return new RestroomMirrorChildFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        modelFragments = new ViewModelProvider(requireActivity()).get(ViewModelFragments.class);
        return inflater.inflate(R.layout.fragment_restroom_mirror_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateMirrorViews(view);
        allowSinkOneObsScroll();

        mirror = view.findViewById(R.id.mirror_image);

        Glide.with(this).load(R.drawable.mirror).fitCenter().into(mirror);

        mirror.setOnClickListener(v -> {
            imgBundleMirror.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.mirror);
            ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundleMirror);
        });

        modelFragments.getSendMirrorFragData().observe(getViewLifecycleOwner(), mirrorData -> {
            if (mirrorData.getBoolean(FILL_MIRROR))
                gatherMirrorData(mirrorData);
        });

        modelFragments.getCheckMirUrFrags().observe(getViewLifecycleOwner(), checkBundle -> {
            if (!checkBundle.getBoolean(RestroomMirrorUrinalFragment.HAS_URINAL)) {
                if (checkEmptyMirrorFragFields()) {
                    mirrorDataBundle.putString(MIRROR_OBS, String.valueOf(mirrorObsValue.getText()));
                    if (checkBundle.getBoolean(RestroomMirrorUrinalFragment.HAS_BOTH)) {
                        mirrorDataBundle.putBoolean(RestroomMirrorUrinalFragment.HAS_URINAL, true);
                        modelFragments.setCheckMirUrFrags(mirrorDataBundle);
                    } else if (checkBundle.getBoolean(RestroomMirrorUrinalFragment.HAS_MIRROR))
                        modelFragments.setRestChildFragBundle(mirrorDataBundle);
                }
            }
        });
    }

    private void gatherMirrorData(Bundle bundle) {
        measureValueA.setText(String.valueOf(bundle.getDouble(MIRROR_A)));
        measureValueB.setText(String.valueOf(bundle.getDouble(MIRROR_B)));
        mirrorObsValue.setText(String.valueOf(bundle.getDouble(MIRROR_OBS)));
    }

    public boolean checkEmptyMirrorFragFields() {
        clearEmptyMirrorFieldsErrors();
        int i = 0;
        if (TextUtils.isEmpty(measureValueA.getText())) {
            measureFieldA.setError(getString(R.string.blank_field_error));
            i++;
        } else
            mirrorDataBundle.putDouble(MIRROR_A, Double.parseDouble(String.valueOf(measureValueA.getText())));

        if (TextUtils.isEmpty(measureValueB.getText())) {
            measureFieldB.setError(getString(R.string.blank_field_error));
            i++;
        } else
            mirrorDataBundle.putDouble(MIRROR_B, Double.parseDouble(String.valueOf(measureValueB.getText())));

        return i == 0;
    }

    public void clearEmptyMirrorFieldsErrors() {
        measureFieldA.setErrorEnabled(false);
        measureFieldB.setErrorEnabled(false);
    }

    private void instantiateMirrorViews(View view) {
        measureFieldA = view.findViewById(R.id.mirror_A_measurement_field);
        measureFieldB = view.findViewById(R.id.mirror_B_measurement_field);
        mirrorObsField = view.findViewById(R.id.mirror_obs_field);
        measureValueA = view.findViewById(R.id.mirror_A_measurement_value);
        measureValueB = view.findViewById(R.id.mirror_B_measurement_value);
        mirrorObsValue = view.findViewById(R.id.mirror_obs_value);
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowSinkOneObsScroll() {
            mirrorObsValue.setOnTouchListener(this::scrollingField);
    }
}