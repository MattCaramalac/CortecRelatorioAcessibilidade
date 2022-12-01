package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

public class RestSideBarFragment extends Fragment implements TagInterface {

    ImageButton artBar1, artBar2, sideBar1, sideBar2;
    TextInputLayout sBarFieldD, sBarFieldE, sBarFieldG, sBarSecField, aBarFieldH, aBarFieldI, aBarFieldJ, aBarSecField;
    TextInputEditText sBarValueD, sBarValueE, sBarValueG, sBarSecValue, aBarValueH, aBarValueI, aBarValueJ, aBarSecValue;
    RadioGroup sBarRadio, aBarRadio;
    TextView sBarError, aBarError;

    Bundle imgBundle;
    ViewModelEntry modelEntry;

    static int restID;

    public RestSideBarFragment() {
        // Required empty public constructor
    }

    public static RestSideBarFragment newInstance(int i) {
        restID = i;
        return new RestSideBarFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_side_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSideBarViews(view);

        modelEntry.getOneRestroomEntry(restID).observe(getViewLifecycleOwner(), this::loadSideBarData);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            if (checkSideBarFields()) {
                bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                gatherData(bundle);
            } else {
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
            }
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });
    }

    private boolean checkSideBarFields() {
        clearSideBarErrors();
        int i = 0;
        if (getCheckedRadio(sBarRadio) == -1) {
            i++;
            sBarError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadio(sBarRadio) == 1) {
            if (TextUtils.isEmpty(sBarValueD.getText())) {
                i++;
                sBarFieldD.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(sBarValueE.getText())) {
                i++;
                sBarFieldE.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(sBarValueG.getText())) {
                i++;
                sBarFieldG.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(sBarSecValue.getText())) {
                i++;
                sBarSecField.setError(getText(R.string.req_field_error));
            }
        }
        if (getCheckedRadio(aBarRadio) == -1) {
            i++;
            aBarError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadio(aBarRadio) == 1) {
            if (TextUtils.isEmpty(aBarValueH.getText())) {
                i++;
                aBarFieldH.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(aBarValueI.getText())) {
                i++;
                aBarFieldI.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(aBarValueJ.getText())) {
                i++;
                aBarFieldJ.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(aBarSecValue.getText())) {
                i++;
                aBarSecField.setError(getText(R.string.req_field_error));
            }
        }

        return i == 0;
    }

    private void clearSideBarErrors() {
        sBarError.setVisibility(View.GONE);
        aBarError.setVisibility(View.GONE);
        sBarFieldD.setErrorEnabled(false);
        sBarFieldE.setErrorEnabled(false);
        sBarFieldG.setErrorEnabled(false);
        sBarSecField.setErrorEnabled(false);
        aBarFieldH.setErrorEnabled(false);
        aBarFieldI.setErrorEnabled(false);
        aBarFieldJ.setErrorEnabled(false);
        aBarSecField.setErrorEnabled(false);
    }

    private void instantiateSideBarViews(View view) {
//        ImageButton
        artBar1 = view.findViewById(R.id.art_bar_img1);
        artBar2 = view.findViewById(R.id.art_bar_img2);
        sideBar1 = view.findViewById(R.id.side_bar_img);
        sideBar2 = view.findViewById(R.id.side_bar_dist_img);
//        TextInputLayout
        sBarFieldD = view.findViewById(R.id.side_bar_measureD_field);
        sBarFieldE = view.findViewById(R.id.side_bar_measureE_field);
        sBarFieldG = view.findViewById(R.id.side_bar_measureG_field);
        sBarSecField = view.findViewById(R.id.side_bar_section_field);
        aBarFieldH = view.findViewById(R.id.art_bar_measureH_field);
        aBarFieldI = view.findViewById(R.id.art_bar_measureI_field);
        aBarFieldJ = view.findViewById(R.id.art_bar_measureJ_field);
        aBarSecField = view.findViewById(R.id.art_bar_section_field);
//        TextInputEditText
        sBarValueD = view.findViewById(R.id.side_bar_measureD_value);
        sBarValueE = view.findViewById(R.id.side_bar_measureE_value);
        sBarValueG = view.findViewById(R.id.side_bar_measureG_value);
        sBarSecValue = view.findViewById(R.id.side_bar_section_value);
        aBarValueH = view.findViewById(R.id.art_bar_measureH_value);
        aBarValueI = view.findViewById(R.id.art_bar_measureI_value);
        aBarValueJ = view.findViewById(R.id.art_bar_measureJ_value);
        aBarSecValue = view.findViewById(R.id.art_bar_section_value);
//        RadioGroup
        sBarRadio = view.findViewById(R.id.side_bar_radio);
        aBarRadio = view.findViewById(R.id.art_bar_radio);
//        TextView
        sBarError = view.findViewById(R.id.side_bar_error);
        aBarError = view.findViewById(R.id.art_bar_error);
//        CheckedChangeListeners
        sBarRadio.setOnCheckedChangeListener(this::radioChangeListener);
        aBarRadio.setOnCheckedChangeListener(this::radioChangeListener);
//        ClickListeners
        artBar1.setOnClickListener(this::imgExpandClickListener);
        artBar2.setOnClickListener(this::imgExpandClickListener);
        sideBar1.setOnClickListener(this::imgExpandClickListener);
        sideBar2.setOnClickListener(this::imgExpandClickListener);
//        Images into ImageButton
        Glide.with(this).load(R.drawable.sidebar).centerCrop().into(sideBar1);
        Glide.with(this).load(R.drawable.disthorbar).centerCrop().into(sideBar2);
        Glide.with(this).load(R.drawable.artbar).centerCrop().into(artBar1);
        Glide.with(this).load(R.drawable.artbar2).centerCrop().into(artBar2);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private void imgExpandClickListener(View view) {
        if (view == sideBar1)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.sidebar);
        else if (view == sideBar2)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.disthorbar);
        else if (view == artBar1)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.artbar);
        else if (view == artBar2)
            imgBundle.putInt(ExpandImageDialog.IMAGE_ID, R.drawable.artbar2);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    private int getCheckedRadio(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private void radioChangeListener(RadioGroup radio, int checkedID) {
        int index = getCheckedRadio(radio);

        if (radio == sBarRadio) {
            if (index == 1) {
                sideBar1.setVisibility(View.VISIBLE);
                sideBar2.setVisibility(View.VISIBLE);
                sBarFieldD.setVisibility(View.VISIBLE);
                sBarFieldE.setVisibility(View.VISIBLE);
                sBarFieldG.setVisibility(View.VISIBLE);
                sBarSecField.setVisibility(View.VISIBLE);
            } else {
                sBarValueD.setText(null);
                sBarValueD.setText(null);
                sBarValueD.setText(null);
                sBarSecValue.setText(null);
                sideBar1.setVisibility(View.GONE);
                sideBar2.setVisibility(View.GONE);
                sBarFieldD.setVisibility(View.GONE);
                sBarFieldE.setVisibility(View.GONE);
                sBarFieldG.setVisibility(View.GONE);
                sBarSecField.setVisibility(View.GONE);
            }
        } else {
            if (index == 1) {
                artBar1.setVisibility(View.VISIBLE);
                artBar2.setVisibility(View.VISIBLE);
                aBarFieldH.setVisibility(View.VISIBLE);
                aBarFieldI.setVisibility(View.VISIBLE);
                aBarFieldJ.setVisibility(View.VISIBLE);
                aBarSecField.setVisibility(View.VISIBLE);
            } else {
                aBarValueH.setText(null);
                aBarValueI.setText(null);
                aBarValueJ.setText(null);
                aBarSecValue.setText(null);
                artBar1.setVisibility(View.GONE);
                artBar2.setVisibility(View.GONE);
                aBarFieldI.setVisibility(View.GONE);
                aBarFieldH.setVisibility(View.GONE);
                aBarFieldJ.setVisibility(View.GONE);
                aBarSecField.setVisibility(View.GONE);
            }
        }
    }

    private void loadSideBarData(RestroomEntry rest) {
        if (rest.getHasHorBar() != null)
            sBarRadio.check(sBarRadio.getChildAt(rest.getHasHorBar()).getId());
        if (rest.getHorBarD() != null)
            sBarValueD.setText(String.valueOf(rest.getHorBarD()));
        if (rest.getHorBarE() != null)
            sBarValueE.setText(String.valueOf(rest.getHorBarE()));
        if (rest.getHorBarDistG() != null)
            sBarValueG.setText(String.valueOf(rest.getHorBarDistG()));
        if (rest.getHorBarSect() != null)
            sBarSecValue.setText(String.valueOf(rest.getHorBarSect()));

        if (rest.getHasArtBar() != null)
            aBarRadio.check(aBarRadio.getChildAt(rest.getHasArtBar()).getId());
        if (rest.getArtBarH() != null)
            aBarValueH.setText(String.valueOf(rest.getArtBarH()));
        if (rest.getArtBarI() != null)
            aBarValueI.setText(String.valueOf(rest.getArtBarI()));
        if (rest.getArtBarJ() != null)
            aBarValueJ.setText(String.valueOf(rest.getArtBarJ()));
        if (rest.getArtBarSect() != null)
            aBarSecValue.setText(String.valueOf(rest.getArtBarSect()));
    }

    private void gatherData(Bundle bundle) {
        bundle.putInt(HAS_HOR, getCheckedRadio(sBarRadio));
        if (getCheckedRadio(sBarRadio) == 1) {
            bundle.putDouble(SIZE_D, Double.parseDouble(String.valueOf(sBarValueD.getText())));
            bundle.putDouble(SIZE_E, Double.parseDouble(String.valueOf(sBarValueE.getText())));
            bundle.putDouble(SIZE_G, Double.parseDouble(String.valueOf(sBarValueG.getText())));
            bundle.putDouble(DIAM_A, Double.parseDouble(String.valueOf(sBarSecValue.getText())));
        }
        bundle.putInt(HAS_VERT, getCheckedRadio(aBarRadio));
        if (getCheckedRadio(aBarRadio) == 1) {
            bundle.putDouble(SIZE_H, Double.parseDouble(String.valueOf(aBarValueH.getText())));
            bundle.putDouble(SIZE_I, Double.parseDouble(String.valueOf(aBarValueI.getText())));
            bundle.putDouble(SIZE_J, Double.parseDouble(String.valueOf(aBarValueJ.getText())));
            bundle.putDouble(DIAM_B, Double.parseDouble(String.valueOf(aBarSecValue.getText())));
        }
    }

}