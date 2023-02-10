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
import com.mpms.relatorioacessibilidadecortec.data.parcels.RestToiletSideBarsParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;


public class RestSideWallFragment extends Fragment implements TagInterface {

    ImageButton horSideBar, horSideDistBar, vertArtBar1, artBar2;
    TextInputLayout measureFieldD, measureFieldE, measureFieldF, measureFieldG, measureFieldH, measureFieldI, measureFieldJ, horSideDiamField,
            vertArtDiamField, horBarDistField, vertBarDistField;
    TextInputEditText measureValueD, measureValueE, measureValueF, measureValueG, measureValueH, measureValueI, measureValueJ, horSideDiamValue,
            vertArtDiamValue, horBarDistValue, vertBarDistValue;
    RadioGroup hasHorSideBar, hasVertArtBar;
    TextView question1, question2, hBarError, vBarError;

    Bundle imgData;
    ViewModelEntry modelEntry;

    static int layout, restID;

    public RestSideWallFragment() {
        // Required empty public constructor
    }

    public static RestSideWallFragment newInstance(int visual, int restroom) {
        layout = visual;
        restID = restroom;
        return new RestSideWallFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgData = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_side_wall, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSideWallViews(view);

        if (restID > 0)
            modelEntry.getRestToiletData(restID).observe(getViewLifecycleOwner(), this::loadSideWallData);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            if (checkSideWallFields()) {
                bundle.putBoolean(CHILD_DATA_COMPLETE, true);
                gatherData(bundle);
            } else {
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
            }
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });
    }

    public void instantiateSideWallViews(View view) {
//        ImageButton
        horSideBar = view.findViewById(R.id.hor_side_bar_img);
        horSideDistBar = view.findViewById(R.id.side_wall_bar_dist_img);
        vertArtBar1 = view.findViewById(R.id.vert_art_bar_img_1);
        artBar2 = view.findViewById(R.id.vert_art_bar_img_2);
//        TextInputLayout
        measureFieldD = view.findViewById(R.id.bar_measureD_field);
        measureFieldE = view.findViewById(R.id.bar_measureE_field);
        measureFieldF = view.findViewById(R.id.bar_measureF_field);
        measureFieldG = view.findViewById(R.id.bar_measureG_field);
        measureFieldH = view.findViewById(R.id.bar_measureH_field);
        measureFieldI = view.findViewById(R.id.bar_measureI_field);
        measureFieldJ = view.findViewById(R.id.bar_measureJ_field);
        horSideDiamField = view.findViewById(R.id.side_hor_bar_section_field);
        vertArtDiamField = view.findViewById(R.id.vert_bar_section_field);
        horBarDistField = view.findViewById(R.id.ext_face_side_bar_dist_field);
        vertBarDistField = view.findViewById(R.id.ext_face_vert_bar_dist_field);
//        TextInputEditText
        measureValueD = view.findViewById(R.id.bar_measureD_value);
        measureValueE = view.findViewById(R.id.bar_measureE_value);
        measureValueF = view.findViewById(R.id.bar_measureF_value);
        measureValueG = view.findViewById(R.id.bar_measureG_value);
        measureValueH = view.findViewById(R.id.bar_measureH_value);
        measureValueI = view.findViewById(R.id.bar_measureI_value);
        measureValueJ = view.findViewById(R.id.bar_measureJ_value);
        horSideDiamValue = view.findViewById(R.id.side_hor_bar_section_value);
        vertArtDiamValue = view.findViewById(R.id.vert_bar_section_value);
        horBarDistValue = view.findViewById(R.id.ext_face_side_bar_dist_value);
        vertBarDistValue = view.findViewById(R.id.ext_face_vert_bar_dist_value);
//        RadioGroup
        hasHorSideBar = view.findViewById(R.id.side_hor_bar_radio);
        hasVertArtBar = view.findViewById(R.id.vert_bar_radio);
//        TextView
        hBarError = view.findViewById(R.id.side_hor_bar_error);
        vBarError = view.findViewById(R.id.vert_bar_error);
        question1 = view.findViewById(R.id.side_hor_bar_header);
        question2 = view.findViewById(R.id.vert_art_bar_header);
//        Images
        if (layout == 0) {
            Glide.with(this).load(R.drawable.sidebar).centerCrop().into(horSideBar);
            Glide.with(this).load(R.drawable.disthorbar).centerCrop().into(horSideDistBar);
            Glide.with(this).load(R.drawable.artbar).centerCrop().into(vertArtBar1);
            Glide.with(this).load(R.drawable.artbar2).centerCrop().into(artBar2);

            measureFieldF.setVisibility(View.GONE);
            vertBarDistField.setVisibility(View.GONE);
            horBarDistField.setVisibility(View.GONE);
            question1.setText(getString(R.string.hint_has_side_bar_toilet));
            question2.setText(getString(R.string.hint_has_side_art_bar_toilet));
        } else if (layout == 1) {
            artBar2.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.wallbar).centerCrop().into(horSideBar);
            Glide.with(this).load(R.drawable.disthorwall).centerCrop().into(horSideDistBar);
            Glide.with(this).load(R.drawable.vertbar).centerCrop().into(vertArtBar1);

            artBar2.setVisibility(View.GONE);
            question1.setText(getString(R.string.hint_has_hor_wall_bar_toilet));
            question2.setText(getString(R.string.hint_has_vert_wall_bar_toilet));
        }

//        Listener
        hasHorSideBar.setOnCheckedChangeListener(this::barRadioListener);
        hasVertArtBar.setOnCheckedChangeListener(this::barRadioListener);
        horSideBar.setOnClickListener(this::imgButtonView);
        horSideDistBar.setOnClickListener(this::imgButtonView);
        vertArtBar1.setOnClickListener(this::imgButtonView);
//        ModelEntry
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    public void imgButtonView(View view) {
        if (layout == 0) {
            if (view == horSideBar)
                imgData.putInt(IMAGE_ID, R.drawable.sidebar);
            else if (view == horSideDistBar)
                imgData.putInt(IMAGE_ID, R.drawable.disthorwall);
            else if (view == vertArtBar1)
                imgData.putInt(IMAGE_ID, R.drawable.artbar);
            else if (view == artBar2)
                imgData.putInt(IMAGE_ID, R.drawable.artbar2);
        } else {
            if (view == horSideBar)
                imgData.putInt(IMAGE_ID, R.drawable.wallbar);
            else if (view == horSideDistBar)
                imgData.putInt(IMAGE_ID, R.drawable.disthorwall);
            else if (view == vertArtBar1)
                imgData.putInt(IMAGE_ID, R.drawable.vertbar);
        }
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgData);
    }

    private void barRadioListener(RadioGroup radio, int check) {
        int index = getCheckedRadioIndex(radio);

        if (radio == hasHorSideBar) {
            if (index == 1) {
                horSideBar.setVisibility(View.VISIBLE);
                horSideDistBar.setVisibility(View.VISIBLE);
                measureFieldD.setVisibility(View.VISIBLE);
                measureFieldE.setVisibility(View.VISIBLE);
                measureFieldG.setVisibility(View.VISIBLE);
                horSideDiamField.setVisibility(View.VISIBLE);
                if (layout == 1) {
                    measureFieldF.setVisibility(View.VISIBLE);
                    horBarDistField.setVisibility(View.VISIBLE);
                }
            } else {
                measureValueD.setText(null);
                measureValueE.setText(null);
                measureValueG.setText(null);
                horSideDiamValue.setText(null);
                horSideBar.setVisibility(View.GONE);
                horSideDistBar.setVisibility(View.GONE);
                measureFieldD.setVisibility(View.GONE);
                measureFieldE.setVisibility(View.GONE);
                measureFieldG.setVisibility(View.GONE);
                horSideDiamField.setVisibility(View.GONE);
                if (layout == 1) {
                    measureValueF.setText(null);
                    measureFieldF.setVisibility(View.GONE);
                    horBarDistValue.setText(null);
                    horBarDistField.setVisibility(View.GONE);
                }
            }
        } else if (radio == hasVertArtBar) {
            if (index == 1) {
                vertArtBar1.setVisibility(View.VISIBLE);
                measureFieldJ.setVisibility(View.VISIBLE);
                measureFieldH.setVisibility(View.VISIBLE);
                measureFieldI.setVisibility(View.VISIBLE);
                vertArtDiamField.setVisibility(View.VISIBLE);
                if (layout == 0) {
                    artBar2.setVisibility(View.VISIBLE);
                    vertBarDistField.setVisibility(View.GONE);
                } else {
                    artBar2.setVisibility(View.GONE);
                    vertBarDistField.setVisibility(View.VISIBLE);
                }
            } else {
                measureValueJ.setText(null);
                measureValueH.setText(null);
                measureValueI.setText(null);
                vertArtDiamValue.setText(null);
                vertArtBar1.setVisibility(View.GONE);
                measureFieldJ.setVisibility(View.GONE);
                measureFieldH.setVisibility(View.GONE);
                measureFieldI.setVisibility(View.GONE);
                vertArtDiamField.setVisibility(View.GONE);
                if (layout == 0) {
                    artBar2.setVisibility(View.GONE);
                } else {
                    vertBarDistValue.setText(null);
                    vertBarDistField.setVisibility(View.GONE);
                }
            }
        }
    }

    private void loadSideWallData(RestroomEntry rest) {
        if (layout == 0) {
            if (rest.getHasSideBar() != null && rest.getHasSideBar() > -1) {
                hasHorSideBar.check(hasHorSideBar.getChildAt(rest.getHasSideBar()).getId());
                if (rest.getHasSideBar() == 1) {
                    if (rest.getSideBarD() != null)
                        measureValueD.setText(String.valueOf(rest.getSideBarD()));
                    if (rest.getSideBarE() != null)
                        measureValueE.setText(String.valueOf(rest.getSideBarE()));
                    if (rest.getSideBarDistG() != null)
                        measureValueG.setText(String.valueOf(rest.getSideBarDistG()));
                    if (rest.getSideBarSect() != null)
                        horSideDiamValue.setText(String.valueOf(rest.getSideBarSect()));
                }
            }
            if (rest.getHasArtBar() != null && rest.getHasArtBar() > -1) {
                hasVertArtBar.check(hasVertArtBar.getChildAt(rest.getHasArtBar()).getId());
                if (rest.getHasArtBar() == 1) {
                    if (rest.getArtBarH() != null)
                        measureValueH.setText(String.valueOf(rest.getArtBarH()));
                    if (rest.getArtBarI() != null)
                        measureValueI.setText(String.valueOf(rest.getArtBarI()));
                    if (rest.getArtBarJ() != null)
                        measureValueJ.setText(String.valueOf(rest.getArtBarJ()));
                    if (rest.getArtBarSect() != null)
                        vertArtDiamValue.setText(String.valueOf(rest.getArtBarSect()));
                }
            }
        } else if (layout == 1) {
            if (rest.getHasHorBar() != null && rest.getHasHorBar() > -1) {
                hasHorSideBar.check(hasHorSideBar.getChildAt(rest.getHasHorBar()).getId());
                if (rest.getHasHorBar() == 1) {
                    if (rest.getHorBarD() != null)
                        measureValueD.setText(String.valueOf(rest.getHorBarD()));
                    if (rest.getHorBarE() != null)
                        measureValueE.setText(String.valueOf(rest.getHorBarE()));
                    if (rest.getHorBarF() != null)
                        measureValueF.setText(String.valueOf(rest.getHorBarF()));
                    if (rest.getHorBarDistG() != null)
                        measureValueG.setText(String.valueOf(rest.getHorBarDistG()));
                    if (rest.getHorBarSect() != null)
                        horSideDiamValue.setText(String.valueOf(rest.getHorBarSect()));
                    if (rest.getHorBarDist() != null)
                        horBarDistValue.setText(String.valueOf(rest.getHorBarDist()));
                }
            }
            if (rest.getHasVertBar() != null && rest.getHasVertBar() > -1) {
                hasVertArtBar.check(hasVertArtBar.getChildAt(rest.getHasVertBar()).getId());
                if (rest.getHasVertBar() == 1) {
                    if (rest.getVertBarH() != null)
                        measureValueH.setText(String.valueOf(rest.getVertBarH()));
                    if (rest.getVertBarI() != null)
                        measureValueI.setText(String.valueOf(rest.getVertBarI()));
                    if (rest.getVertBarJ() != null)
                        measureValueJ.setText(String.valueOf(rest.getVertBarJ()));
                    if (rest.getVertBarSect() != null)
                        vertArtDiamValue.setText(String.valueOf(rest.getVertBarSect()));
                    if (rest.getVertBarDist() != null)
                        vertBarDistValue.setText(String.valueOf(rest.getVertBarDist()));
                }
            }
        }
    }


    private int getCheckedRadioIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    private boolean checkSideWallFields() {
        clearSideWallErrors();
        int i = 0;
        if (getCheckedRadioIndex(hasHorSideBar) == -1) {
            i++;
            hBarError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadioIndex(hasHorSideBar) == 1) {
            if (TextUtils.isEmpty(measureValueD.getText())) {
                i++;
                measureFieldD.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(measureValueE.getText())) {
                i++;
                measureFieldE.setError(getText(R.string.req_field_error));
            }

            if (TextUtils.isEmpty(measureValueG.getText())) {
                i++;
                measureFieldG.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(horSideDiamValue.getText())) {
                i++;
                horSideDiamField.setError(getText(R.string.req_field_error));
            }
            if (layout == 1) {
                if (TextUtils.isEmpty(measureValueF.getText())) {
                    i++;
                    measureFieldF.setError(getText(R.string.req_field_error));
                }
                if (TextUtils.isEmpty(horBarDistValue.getText())) {
                    i++;
                    horBarDistField.setError(getText(R.string.req_field_error));
                }
            }
        }
        if (getCheckedRadioIndex(hasVertArtBar) == -1) {
            i++;
            vBarError.setVisibility(View.VISIBLE);
        } else if (getCheckedRadioIndex(hasVertArtBar) == 1) {
            if (TextUtils.isEmpty(measureValueJ.getText())) {
                i++;
                measureFieldJ.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(measureValueH.getText())) {
                i++;
                measureFieldH.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(measureValueI.getText())) {
                i++;
                measureFieldI.setError(getText(R.string.req_field_error));
            }
            if (TextUtils.isEmpty(vertArtDiamValue.getText())) {
                i++;
                vertArtDiamField.setError(getText(R.string.req_field_error));
            }
            if (layout == 1 && TextUtils.isEmpty(vertBarDistValue.getText())) {
                i++;
                vertBarDistField.setError(getText(R.string.req_field_error));
            }
        }

        return i == 0;
    }

    private void clearSideWallErrors() {
        hBarError.setVisibility(View.GONE);
        vBarError.setVisibility(View.GONE);
        measureFieldD.setErrorEnabled(false);
        measureFieldE.setErrorEnabled(false);
        measureFieldF.setErrorEnabled(false);
        measureFieldG.setErrorEnabled(false);
        measureFieldH.setErrorEnabled(false);
        measureFieldI.setErrorEnabled(false);
        measureFieldJ.setErrorEnabled(false);
        vertArtDiamField.setErrorEnabled(false);
        vertBarDistField.setErrorEnabled(false);
        horSideDiamField.setErrorEnabled(false);
        horBarDistField.setErrorEnabled(false);
    }

    private void gatherData(Bundle bundle) {

        int horBar, vertBar;
        Double horD = null, horE = null, horF = null, horG = null, horDiam = null, horDist = null, vertH = null, vertI = null, vertJ = null, vertDiam = null, vertDist = null;

        horBar = getCheckedRadioIndex(hasHorSideBar);
        if (horBar == 1) {
            if (!TextUtils.isEmpty(measureValueD.getText()))
                horD = Double.parseDouble(String.valueOf(measureValueD.getText()));
            if (!TextUtils.isEmpty(measureValueE.getText()))
                horE = Double.parseDouble(String.valueOf(measureValueE.getText()));
            if (!TextUtils.isEmpty(measureValueF.getText()))
                horF = Double.parseDouble(String.valueOf(measureValueF.getText()));
            if (!TextUtils.isEmpty(measureValueG.getText()))
                horG = Double.parseDouble(String.valueOf(measureValueG.getText()));
            if (!TextUtils.isEmpty(horSideDiamValue.getText()))
                horDiam = Double.parseDouble(String.valueOf(horSideDiamValue.getText()));
            if (!TextUtils.isEmpty(horBarDistValue.getText()))
                horDist = Double.parseDouble(String.valueOf(horBarDistValue.getText()));
        }

        vertBar = getCheckedRadioIndex(hasVertArtBar);
        if (vertBar == 1) {
            if (!TextUtils.isEmpty(measureValueH.getText()))
                vertH = Double.parseDouble(String.valueOf(measureValueH.getText()));
            if (!TextUtils.isEmpty(measureValueI.getText()))
                vertI = Double.parseDouble(String.valueOf(measureValueI.getText()));
            if (!TextUtils.isEmpty(measureValueJ.getText()))
                vertJ = Double.parseDouble(String.valueOf(measureValueJ.getText()));
            if (!TextUtils.isEmpty(vertArtDiamValue.getText()))
                vertDiam = Double.parseDouble(String.valueOf(vertArtDiamValue.getText()));
            if (!TextUtils.isEmpty(vertBarDistValue.getText()))
                vertDist = Double.parseDouble(String.valueOf(vertBarDistValue.getText()));
        }

        RestToiletSideBarsParcel parcel = new RestToiletSideBarsParcel(horBar, horD, horE, horF, horG, horDiam, horDist, vertBar, vertH, vertI, vertJ, vertDiam, vertDist);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));

    }
}