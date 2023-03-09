package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.Dialogs.DialogClass.ExpandImageDialog;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxToilUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestToiletUpdate;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.RestToiletSideBarsParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.RestSideWallFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

import java.util.ArrayList;

public class RestToiletFragment extends Fragment implements TagInterface, ScrollEditText {

    ImageButton toilet1, toilet2, toilet3, frontBar, pHolder1, pHolder2, pHolder3;
    TextView pHoldError, pHoldTypeHeader, pHoldTypeError, pHoldAlignHeader, pHoldAlignError, tTypeError, tSeatError, tSocError, socCornerError,
            tSideWallError, tFrontBarError, tSocHeader, socCornerHeader, doucheError;
    RadioGroup papHoldRadio, papHoldTypeRadio, pHoldAlignRadio, tTypeRadio, tSeatRadio, tSocRadio, socCornerRadio, tWallRadio, tFrontBarRadio, doucheRadio;
    TextInputLayout tActDescField, tActHeightField, tActObsField, papEmbAField, papEmbBField, papSupAField, papObsField, tNoSeatHeightField,
            tSeatHeightField, fSocField, lSocField, fBarFieldMeasureA, fBarFieldMeasureB, fBarFieldMeasureC, fBarSectField, fBarDistField,
            tObsField, doucheActHeightField, douchePressHeightField, doucheObsField;
    TextInputEditText tActDescValue, tActHeightValue, tActObsValue, papEmbAValue, papEmbBValue, papSupAValue, papObsValue, tNoSeatHeightValue,
            tSeatHeightValue, fSocValue, lSocValue, fBarValueMeasureA, fBarValueMeasureB, fBarValueMeasureC, fBarSectValue, fBarDistValue,
            tObsValue, doucheActHeightValue, douchePressHeightValue, doucheObsValue;
    FrameLayout barFrag;

    ViewModelEntry modelEntry;

    Bundle resToilBundle, imgBundle, cFragBundle;

    Button saveSupBar, returnUpView;

    ArrayList<TextInputEditText> toiletObsArray = new ArrayList<>();


    public RestToiletFragment() {
        // Required empty public constructor
    }


    public static RestToiletFragment newInstance() {
        return new RestToiletFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            resToilBundle = this.getArguments();
        else
            resToilBundle = new Bundle();
        imgBundle = new Bundle();
        cFragBundle = new Bundle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restroom_toilet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateSupportView(view);

        if (resToilBundle.getInt(BOX_ID) > 0)
            modelEntry.getBoxToiletData(resToilBundle.getInt(BOX_ID)).observe(getViewLifecycleOwner(), this::loadBoxToiletData);
        else if (resToilBundle.getInt(REST_ID) > 0)
            modelEntry.getRestToiletData(resToilBundle.getInt(REST_ID)).observe(getViewLifecycleOwner(), this::loadToiletData);

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, this, (key, bundle) -> {
            if (checkEmptySupBarField() && bundle.getBoolean(CHILD_DATA_COMPLETE)) {
                if (resToilBundle.getBoolean(FROM_BOX)) {
                    RestBoxToilUpdate tUpdate = boxToilUpdate(bundle);
                    ViewModelEntry.updateBoxToilet(tUpdate);
                } else {
                    RestToiletUpdate tUpdate = barUpdate(bundle);
                    ViewModelEntry.updateRestToiletData(tUpdate);
                }
                callNextFragment(resToilBundle);
            } else
                toastMessage();
        });


        saveSupBar.setOnClickListener(v -> {
            if (getCheckedRadioIndex(tWallRadio) != -1) {
                getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, cFragBundle);
            } else {
                checkEmptySupBarField();
                toastMessage();
            }


        });

        returnUpView.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());
    }

    private void toastMessage() {
        Toast.makeText(getContext(), getText(R.string.empty_fields), Toast.LENGTH_SHORT).show();
    }

    private void addObsFields() {
        toiletObsArray.add(tObsValue);
        toiletObsArray.add(tActObsValue);
        toiletObsArray.add(papObsValue);
        toiletObsArray.add(doucheObsValue);
    }

    private void instantiateSupportView(View view) {
//        TextInputLayout
        tNoSeatHeightField = view.findViewById(R.id.toilet_no_seat_height_field);
        tSeatHeightField = view.findViewById(R.id.toilet_seat_height_field);
        fSocField = view.findViewById(R.id.soculo_front_field);
        lSocField = view.findViewById(R.id.soculo_side_field);
        fBarFieldMeasureA = view.findViewById(R.id.bar_measureA_field);
        fBarFieldMeasureB = view.findViewById(R.id.bar_measureB_field);
        fBarFieldMeasureC = view.findViewById(R.id.bar_measureC_field);
        fBarSectField = view.findViewById(R.id.front_bar_section_field);
        fBarDistField = view.findViewById(R.id.ext_face_bar_dist_field);
        tObsField = view.findViewById(R.id.toilet_obs_field);
        tActDescField = view.findViewById(R.id.toil_act_desc_field);
        tActHeightField = view.findViewById(R.id.toil_act_height_field);
        tActObsField = view.findViewById(R.id.toil_act_obs_field);
        papEmbAField = view.findViewById(R.id.pap_measureA_field);
        papEmbBField = view.findViewById(R.id.pap_measureB_field);
        papSupAField = view.findViewById(R.id.pap_super_measureA_field);
        papObsField = view.findViewById(R.id.pap_holder_obs_field);
        doucheActHeightField = view.findViewById(R.id.douche_height_field);
        douchePressHeightField = view.findViewById(R.id.douche_pressure_height_field);
        doucheObsField = view.findViewById(R.id.douche_obs_field);
//        TextInputEditText
        tNoSeatHeightValue = view.findViewById(R.id.toilet_no_seat_height_value);
        tSeatHeightValue = view.findViewById(R.id.toilet_seat_height_value);
        fSocValue = view.findViewById(R.id.soculo_front_value);
        lSocValue = view.findViewById(R.id.soculo_side_value);
        fBarValueMeasureA = view.findViewById(R.id.bar_measureA_value);
        fBarValueMeasureB = view.findViewById(R.id.bar_measureB_value);
        fBarValueMeasureC = view.findViewById(R.id.bar_measureC_value);
        fBarSectValue = view.findViewById(R.id.front_bar_section_value);
        fBarDistValue = view.findViewById(R.id.ext_face_bar_dist_value);
        tObsValue = view.findViewById(R.id.toilet_obs_value);
        tActDescValue = view.findViewById(R.id.toil_act_desc_value);
        tActHeightValue = view.findViewById(R.id.toil_act_height_value);
        tActObsValue = view.findViewById(R.id.toil_act_obs_value);
        papEmbAValue = view.findViewById(R.id.pap_measureA_value);
        papEmbBValue = view.findViewById(R.id.pap_measureB_value);
        papSupAValue = view.findViewById(R.id.pap_super_measureA_value);
        papObsValue = view.findViewById(R.id.pap_holder_obs_value);
        doucheActHeightValue = view.findViewById(R.id.douche_height_value);
        douchePressHeightValue = view.findViewById(R.id.douche_pressure_height_value);
        doucheObsValue = view.findViewById(R.id.douche_obs_value);
//        TextView
        tTypeError = view.findViewById(R.id.toilet_type_error);
        tSeatError = view.findViewById(R.id.toilet_seat_error);
        tSocError = view.findViewById(R.id.toilet_soculo_error);
        socCornerError = view.findViewById(R.id.soculo_corner_error);
        tSideWallError = view.findViewById(R.id.side_wall_error);
        tFrontBarError = view.findViewById(R.id.front_bar_error);
        tSocHeader = view.findViewById(R.id.toilet_soculo_header);
        socCornerHeader = view.findViewById(R.id.soculo_corner_header);
        pHoldError = view.findViewById(R.id.toilet_paper_error);
        pHoldTypeHeader = view.findViewById(R.id.t_paper_type_header);
        pHoldTypeError = view.findViewById(R.id.t_paper_type_error);
        pHoldAlignHeader = view.findViewById(R.id.pap_holder2_header);
        pHoldAlignError = view.findViewById(R.id.pap_holder2_error);
        doucheError = view.findViewById(R.id.douche_error);
//        RadioGroup
        tTypeRadio = view.findViewById(R.id.toilet_type_radio);
        tSeatRadio = view.findViewById(R.id.toilet_seat_radio);
        tSocRadio = view.findViewById(R.id.toilet_soculo_radio);
        socCornerRadio = view.findViewById(R.id.soculo_corner_radio);
        tWallRadio = view.findViewById(R.id.side_wall_radio);
        tFrontBarRadio = view.findViewById(R.id.front_bar_radio);
        papHoldRadio = view.findViewById(R.id.toilet_paper_radio);
        papHoldTypeRadio = view.findViewById(R.id.t_paper_type_radio);
        pHoldAlignRadio = view.findViewById(R.id.pap_holder2_radio);
        doucheRadio = view.findViewById(R.id.douche_radio);
//        ImageButton
        toilet1 = view.findViewById(R.id.toilet_type_1);
        toilet2 = view.findViewById(R.id.toilet_type_2);
        toilet3 = view.findViewById(R.id.toilet_type_3);
        frontBar = view.findViewById(R.id.rest_front_bar_img);
        pHolder1 = view.findViewById(R.id.pap_holder_image);
        pHolder2 = view.findViewById(R.id.pap_holder2_image);
        pHolder3 = view.findViewById(R.id.pap_holder3_image);
//        FrameLayout
        barFrag = view.findViewById(R.id.toilet_wall_fragment);
//        MaterialButton
        saveSupBar = view.findViewById(R.id.save_sup_bar);
        returnUpView = view.findViewById(R.id.return_up_view);
//        Setting Images into ImageButton
        Glide.with(this).load(R.drawable.convtoilet).centerCrop().into(toilet1);
        Glide.with(this).load(R.drawable.susptoilet).centerCrop().into(toilet2);
        Glide.with(this).load(R.drawable.boxtoilet).centerCrop().into(toilet3);
        Glide.with(this).load(R.drawable.frontbar).centerCrop().into(frontBar);
        Glide.with(this).load(R.drawable.papholder).centerCrop().into(pHolder1);
        Glide.with(this).load(R.drawable.papholder2).centerCrop().into(pHolder2);
        Glide.with(this).load(R.drawable.papholder3).centerCrop().into(pHolder3);
//        ClickListeners
        toilet1.setOnClickListener(this::imgExpandClickListener);
        toilet2.setOnClickListener(this::imgExpandClickListener);
        toilet3.setOnClickListener(this::imgExpandClickListener);
        frontBar.setOnClickListener(this::imgExpandClickListener);
        pHolder1.setOnClickListener(this::imgExpandClickListener);
        pHolder2.setOnClickListener(this::imgExpandClickListener);
        pHolder3.setOnClickListener(this::imgExpandClickListener);
//        CheckedChangeListeners
        tTypeRadio.setOnCheckedChangeListener(this::radioListener);
        tSeatRadio.setOnCheckedChangeListener(this::radioListener);
        tSocRadio.setOnCheckedChangeListener(this::radioListener);
        socCornerRadio.setOnCheckedChangeListener(this::radioListener);
        tWallRadio.setOnCheckedChangeListener(this::radioListener);
        tFrontBarRadio.setOnCheckedChangeListener(this::radioListener);
        papHoldRadio.setOnCheckedChangeListener(this::radioListener);
        papHoldTypeRadio.setOnCheckedChangeListener(this::radioListener);
        doucheRadio.setOnCheckedChangeListener(this::radioListener);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        addObsFields();
        allowObsScroll(toiletObsArray);
    }

    private void imgExpandClickListener(View view) {
        if (view == toilet1)
            imgBundle.putInt(IMAGE_ID, R.drawable.convtoilet);
        else if (view == toilet2)
            imgBundle.putInt(IMAGE_ID, R.drawable.susptoilet);
        else if (view == toilet3)
            imgBundle.putInt(IMAGE_ID, R.drawable.boxtoilet);
        else if (view == frontBar)
            imgBundle.putInt(IMAGE_ID, R.drawable.frontbar);
        else if (view == pHolder1)
            imgBundle.putInt(IMAGE_ID, R.drawable.papholder);
        else if (view == pHolder2)
            imgBundle.putInt(IMAGE_ID, R.drawable.papholder2);
        else if (view == pHolder3)
            imgBundle.putInt(IMAGE_ID, R.drawable.papholder3);
        ExpandImageDialog.expandImage(requireActivity().getSupportFragmentManager(), imgBundle);
    }

    public void radioListener(RadioGroup radio, int checkedID) {
        int index = getCheckedRadioIndex(radio);
        if (radio == tTypeRadio) {
            if (index == 1) {
                tSocHeader.setVisibility(View.GONE);
                tSocRadio.clearCheck();
                tSocRadio.setVisibility(View.GONE);
                tSeatError.setVisibility(View.GONE);
                fSocValue.setText(null);
                fSocField.setVisibility(View.GONE);
                lSocValue.setText(null);
                lSocField.setVisibility(View.GONE);
                socCornerHeader.setVisibility(View.GONE);
                socCornerRadio.clearCheck();
                socCornerRadio.setVisibility(View.GONE);
                socCornerError.setVisibility(View.GONE);
            } else {
                tSocHeader.setVisibility(View.VISIBLE);
                tSocRadio.setVisibility(View.VISIBLE);
            }
        } else if (radio == tSeatRadio) {
            if (index == 1)
                tSeatHeightField.setVisibility(View.VISIBLE);
            else {
                tSeatHeightValue.setText(null);
                tSeatHeightField.setVisibility(View.GONE);
            }
        } else if (radio == tSocRadio) {
            if (index == 1) {
                fSocField.setVisibility(View.VISIBLE);
                lSocField.setVisibility(View.VISIBLE);
                socCornerHeader.setVisibility(View.VISIBLE);
                socCornerRadio.setVisibility(View.VISIBLE);
            } else {
                fSocValue.setText(null);
                lSocValue.setText(null);
                fSocField.setVisibility(View.GONE);
                lSocField.setVisibility(View.GONE);
                socCornerHeader.setVisibility(View.GONE);
                socCornerRadio.setVisibility(View.GONE);
            }
        } else if (radio == tWallRadio) {
            RestSideWallFragment fragment = new RestSideWallFragment();
            fragment.setArguments(resToilBundle);
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.toilet_wall_fragment, RestSideWallFragment.newInstance(index, resToilBundle)).commit();
        } else if (radio == papHoldRadio) {
            if (index == 0) {
                papEmbAValue.setText(null);
                papEmbBValue.setText(null);
                papSupAValue.setText(null);
                papHoldTypeRadio.clearCheck();
                pHoldAlignRadio.clearCheck();
                papEmbAField.setVisibility(View.GONE);
                papEmbBField.setVisibility(View.GONE);
                papSupAField.setVisibility(View.GONE);
                pHoldTypeHeader.setVisibility(View.GONE);
                papHoldTypeRadio.setVisibility(View.GONE);
                pHoldTypeError.setVisibility(View.GONE);
                pHoldAlignHeader.setVisibility(View.GONE);
                pHoldAlignRadio.setVisibility(View.GONE);
                pHoldAlignError.setVisibility(View.GONE);
                pHolder1.setVisibility(View.GONE);
                pHolder2.setVisibility(View.GONE);
                pHolder3.setVisibility(View.GONE);
            } else {
                pHoldTypeHeader.setVisibility(View.VISIBLE);
                papHoldTypeRadio.setVisibility(View.VISIBLE);
            }
        } else if (radio == papHoldTypeRadio) {
            if (index == 0) {
                papSupAValue.setText(null);
                pHoldAlignRadio.clearCheck();
                papSupAField.setVisibility(View.GONE);
                pHoldAlignHeader.setVisibility(View.GONE);
                pHoldAlignRadio.setVisibility(View.GONE);
                pHoldAlignError.setVisibility(View.GONE);
                pHolder2.setVisibility(View.GONE);
                pHolder3.setVisibility(View.GONE);

                papEmbAField.setVisibility(View.VISIBLE);
                papEmbBField.setVisibility(View.VISIBLE);
                pHolder1.setVisibility(View.VISIBLE);
            } else {
                papEmbAValue.setText(null);
                papEmbBValue.setText(null);
                papEmbAField.setVisibility(View.GONE);
                papEmbBField.setVisibility(View.GONE);
                pHolder1.setVisibility(View.GONE);

                papSupAField.setVisibility(View.VISIBLE);
                pHoldAlignHeader.setVisibility(View.VISIBLE);
                pHoldAlignRadio.setVisibility(View.VISIBLE);
                pHolder2.setVisibility(View.VISIBLE);
                pHolder3.setVisibility(View.VISIBLE);
            }
        } else if (radio == doucheRadio) {
            if (index == 0) {
                doucheActHeightValue.setText(null);
                doucheActHeightField.setVisibility(View.GONE);
                douchePressHeightValue.setText(null);
                douchePressHeightField.setVisibility(View.GONE);
            } else {
                doucheActHeightField.setVisibility(View.VISIBLE);
                douchePressHeightField.setVisibility(View.VISIBLE);
            }
        } else if (radio == tFrontBarRadio) {
            if (index == 1) {
                fBarFieldMeasureA.setVisibility(View.VISIBLE);
                fBarFieldMeasureB.setVisibility(View.VISIBLE);
                fBarFieldMeasureC.setVisibility(View.VISIBLE);
                fBarSectField.setVisibility(View.VISIBLE);
                fBarDistField.setVisibility(View.VISIBLE);
                frontBar.setVisibility(View.VISIBLE);
            } else {
                fBarValueMeasureA.setText(null);
                fBarValueMeasureB.setText(null);
                fBarValueMeasureC.setText(null);
                fBarSectValue.setText(null);
                fBarDistValue.setText(null);
                fBarFieldMeasureA.setVisibility(View.GONE);
                fBarFieldMeasureB.setVisibility(View.GONE);
                fBarFieldMeasureC.setVisibility(View.GONE);
                fBarSectField.setVisibility(View.GONE);
                fBarDistField.setVisibility(View.GONE);
                frontBar.setVisibility(View.GONE);
            }
        }
    }

    public void callNextFragment(Bundle bundle) {
        if (bundle.getInt(REST_TYPE) != 3) {
            RestAccessFragment toilAccess = RestAccessFragment.newInstance();
            toilAccess.setArguments(bundle);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.show_fragment_selected, toilAccess).addToBackStack(null).commit();
        } else {
            Toast.makeText(getContext(), getText(R.string.register_created_message), Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStack(REST_LIST, 0);
        }

    }

    private int getCheckedRadioIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public boolean checkEmptySupBarField() {
        clearEmptyFieldsErrors();
        int i = 0;
        if (getCheckedRadioIndex(tTypeRadio) == -1) {
            tTypeError.setVisibility(View.VISIBLE);
            i++;
        } else if (getCheckedRadioIndex(tTypeRadio) != 1) {
            if (getCheckedRadioIndex(tSocRadio) == -1) {
                tSocError.setVisibility(View.VISIBLE);
                i++;
            } else if (getCheckedRadioIndex(tSocRadio) == 1) {
                if (TextUtils.isEmpty(fSocValue.getText())) {
                    fSocField.setError(getText(R.string.req_field_error));
                    i++;
                }
                if (TextUtils.isEmpty(lSocValue.getText())) {
                    lSocField.setError(getText(R.string.req_field_error));
                    i++;
                }
                if (getCheckedRadioIndex(socCornerRadio) == -1) {
                    socCornerError.setVisibility(View.VISIBLE);
                    i++;
                }
            }
        }
        if (TextUtils.isEmpty(tNoSeatHeightValue.getText())) {
            tNoSeatHeightField.setError(getText(R.string.req_field_error));
            i++;
        }
        if (getCheckedRadioIndex(tSeatRadio) == -1) {
            tSeatError.setVisibility(View.VISIBLE);
            i++;
        } else if (getCheckedRadioIndex(tSeatRadio) == 1) {
            if (TextUtils.isEmpty(tSeatHeightValue.getText())) {
                tSeatHeightField.setError(getText(R.string.req_field_error));
                i++;
            }
        }
        if (getCheckedRadioIndex(tWallRadio) == -1) {
            tSideWallError.setVisibility(View.VISIBLE);
            i++;
        }
        if (getCheckedRadioIndex(tFrontBarRadio) == -1) {
            tFrontBarError.setVisibility(View.VISIBLE);
            i++;
        } else if (getCheckedRadioIndex(tFrontBarRadio) == 1) {
            if (TextUtils.isEmpty(fBarValueMeasureA.getText())) {
                fBarFieldMeasureA.setError(getText(R.string.req_field_error));
                i++;
            }
            if (TextUtils.isEmpty(fBarValueMeasureB.getText())) {
                fBarFieldMeasureB.setError(getText(R.string.req_field_error));
                i++;
            }
            if (TextUtils.isEmpty(fBarValueMeasureC.getText())) {
                fBarFieldMeasureC.setError(getText(R.string.req_field_error));
                i++;
            }
            if (TextUtils.isEmpty(fBarSectValue.getText())) {
                fBarSectField.setError(getText(R.string.req_field_error));
                i++;
            }
            if (TextUtils.isEmpty(fBarDistValue.getText())) {
                fBarDistField.setError(getText(R.string.req_field_error));
                i++;
            }
        }
        if (TextUtils.isEmpty(tActDescValue.getText())) {
            i++;
            tActDescField.setError(getText(R.string.req_field_error));
        }
        if (TextUtils.isEmpty(tActHeightValue.getText())) {
            i++;
            tActHeightField.setError(getText(R.string.req_field_error));
        }
        if (getCheckedRadioIndex(papHoldRadio) == -1) {
            pHoldError.setVisibility(View.VISIBLE);
            i++;
        } else if (getCheckedRadioIndex(papHoldRadio) == 1) {
            if (getCheckedRadioIndex(papHoldTypeRadio) == -1) {
                i++;
                pHoldTypeError.setVisibility(View.VISIBLE);
            } else if (getCheckedRadioIndex(papHoldTypeRadio) == 0) {
                if (TextUtils.isEmpty(papEmbAValue.getText())) {
                    i++;
                    papEmbAField.setError(getText(R.string.req_field_error));
                }
                if (TextUtils.isEmpty(papEmbBValue.getText())) {
                    i++;
                    papEmbBField.setError(getText(R.string.req_field_error));
                }
            } else {
                if (getCheckedRadioIndex(pHoldAlignRadio) == -1) {
                    i++;
                    pHoldAlignError.setVisibility(View.VISIBLE);
                }
                if (TextUtils.isEmpty(papSupAValue.getText())) {
                    i++;
                    papSupAField.setError(getText(R.string.req_field_error));
                }
            }
        }
        if (getCheckedRadioIndex(doucheRadio) == -1) {
            doucheError.setVisibility(View.VISIBLE);
            i++;
        } else if (getCheckedRadioIndex(doucheRadio) == 1) {
            if (TextUtils.isEmpty(doucheActHeightValue.getText())) {
                i++;
                doucheActHeightField.setError(getText(R.string.req_field_error));
            }
        }

        return i == 0;
    }

    public void clearEmptyFieldsErrors() {
        tNoSeatHeightField.setErrorEnabled(false);
        tSeatHeightField.setErrorEnabled(false);
        fSocField.setErrorEnabled(false);
        lSocField.setErrorEnabled(false);
        fBarFieldMeasureA.setErrorEnabled(false);
        fBarFieldMeasureB.setErrorEnabled(false);
        fBarFieldMeasureC.setErrorEnabled(false);
        fBarSectField.setErrorEnabled(false);
        fBarDistField.setErrorEnabled(false);

        tTypeError.setVisibility(View.GONE);
        tSeatError.setVisibility(View.GONE);
        tSocError.setVisibility(View.GONE);
        socCornerError.setVisibility(View.GONE);
        tSideWallError.setVisibility(View.GONE);
        tFrontBarError.setVisibility(View.GONE);
    }

    public void loadToiletData(RestroomEntry rest) {
        if (rest.getToType() != null)
            tTypeRadio.check(tTypeRadio.getChildAt(rest.getToType()).getId());
        if (rest.getToHasSoculo() != null)
            tSocRadio.check(tSocRadio.getChildAt(rest.getToHasSoculo()).getId());
        if (rest.getFrSoculo() != null)
            fSocValue.setText(String.valueOf(rest.getFrSoculo()));
        if (rest.getLatSoculo() != null)
            lSocValue.setText(String.valueOf(rest.getLatSoculo()));
        if (rest.getSocCorners() != null)
            socCornerRadio.check(socCornerRadio.getChildAt(rest.getSocCorners()).getId());
        if (rest.getToHeightNoSeat() != null)
            tNoSeatHeightValue.setText(String.valueOf(rest.getToHeightNoSeat()));
        if (rest.getToHasSeat() != null)
            tSeatRadio.check(tSeatRadio.getChildAt(rest.getToHasSeat()).getId());
        if (rest.getToHeightSeat() != null)
            tSeatHeightValue.setText(String.valueOf(rest.getToHeightSeat()));
        if (rest.getToHasFrontBar() != null)
            tFrontBarRadio.check(tFrontBarRadio.getChildAt(rest.getToHasFrontBar()).getId());
        if (rest.getFrBarA() != null)
            fBarValueMeasureA.setText(String.valueOf(rest.getFrBarA()));
        if (rest.getFrBarB() != null)
            fBarValueMeasureB.setText(String.valueOf(rest.getFrBarB()));
        if (rest.getFrBarC() != null)
            fBarValueMeasureC.setText(String.valueOf(rest.getFrBarC()));
        if (rest.getFrBarSect() != null)
            fBarSectValue.setText(String.valueOf(rest.getFrBarSect()));
        if (rest.getFrBarDist() != null)
            fBarDistValue.setText(String.valueOf(rest.getFrBarDist()));
        if (rest.getToHasWall() != null) {
            tWallRadio.check(tWallRadio.getChildAt(rest.getToHasWall()).getId());
            getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, resToilBundle);
        }
        if (rest.getToActDesc() != null)
            tActDescValue.setText(rest.getToActDesc());
        if (rest.getToActHeight() != null)
            tActHeightValue.setText(String.valueOf(rest.getToActHeight()));
        if (rest.getToActObs() != null)
            tActObsValue.setText(rest.getToActObs());
        if (rest.getHasPapHolder() != null)
            papHoldRadio.check(papHoldRadio.getChildAt(rest.getHasPapHolder()).getId());
        if (rest.getPapHolderType() != null)
            papHoldTypeRadio.check(papHoldTypeRadio.getChildAt(rest.getPapHolderType()).getId());
        if (rest.getPapEmbDist() != null)
            papEmbAValue.setText(String.valueOf(rest.getPapEmbDist()));
        if (rest.getPapEmbHeight() != null)
            papEmbBValue.setText(String.valueOf(rest.getPapEmbHeight()));
        if (rest.getPapSupAlign() != null)
            pHoldAlignRadio.check(pHoldAlignRadio.getChildAt(rest.getPapSupAlign()).getId());
        if (rest.getPapSupHeight() != null)
            papSupAValue.setText(String.valueOf(rest.getPapSupHeight()));
        if (rest.getPapHoldObs() != null)
            papObsValue.setText(rest.getPapHoldObs());
        if (rest.getHasDouche() != null)
            doucheRadio.check(doucheRadio.getChildAt(rest.getHasDouche()).getId());
        if (rest.getDoucheActHeight() != null)
            doucheActHeightValue.setText(String.valueOf(rest.getDoucheActHeight()));
        if (rest.getDouchePressHeight() != null)
            douchePressHeightValue.setText(String.valueOf(rest.getDouchePressHeight()));
        if (rest.getDoucheObs() != null)
            doucheObsValue.setText(rest.getDoucheObs());
        if (rest.getToiletObs() != null)
            tObsValue.setText(rest.getToiletObs());


    }

    public void loadBoxToiletData(RestBoxEntry rest) {
        if (rest.getToType() != null)
            tTypeRadio.check(tTypeRadio.getChildAt(rest.getToType()).getId());
        if (rest.getToHasSoculo() != null)
            tSocRadio.check(tSocRadio.getChildAt(rest.getToHasSoculo()).getId());
        if (rest.getFrSoculo() != null)
            fSocValue.setText(String.valueOf(rest.getFrSoculo()));
        if (rest.getLatSoculo() != null)
            lSocValue.setText(String.valueOf(rest.getLatSoculo()));
        if (rest.getSocCorners() != null)
            socCornerRadio.check(socCornerRadio.getChildAt(rest.getSocCorners()).getId());
        if (rest.getToHeightNoSeat() != null)
            tNoSeatHeightValue.setText(String.valueOf(rest.getToHeightNoSeat()));
        if (rest.getToHasSeat() != null)
            tSeatRadio.check(tSeatRadio.getChildAt(rest.getToHasSeat()).getId());
        if (rest.getToHeightSeat() != null)
            tSeatHeightValue.setText(String.valueOf(rest.getToHeightSeat()));
        if (rest.getToHasFrontBar() != null)
            tFrontBarRadio.check(tFrontBarRadio.getChildAt(rest.getToHasFrontBar()).getId());
        if (rest.getFrBarA() != null)
            fBarValueMeasureA.setText(String.valueOf(rest.getFrBarA()));
        if (rest.getFrBarB() != null)
            fBarValueMeasureB.setText(String.valueOf(rest.getFrBarB()));
        if (rest.getFrBarC() != null)
            fBarValueMeasureC.setText(String.valueOf(rest.getFrBarC()));
        if (rest.getFrBarSect() != null)
            fBarSectValue.setText(String.valueOf(rest.getFrBarSect()));
        if (rest.getFrBarDist() != null)
            fBarDistValue.setText(String.valueOf(rest.getFrBarDist()));
        if (rest.getToHasWall() != null) {
            tWallRadio.check(tWallRadio.getChildAt(rest.getToHasWall()).getId());
            getChildFragmentManager().setFragmentResult(LOAD_CHILD_DATA, resToilBundle);
        }
        if (rest.getToActDesc() != null)
            tActDescValue.setText(rest.getToActDesc());
        if (rest.getToActHeight() != null)
            tActHeightValue.setText(String.valueOf(rest.getToActHeight()));
        if (rest.getToActObs() != null)
            tActObsValue.setText(rest.getToActObs());
        if (rest.getHasPapHolder() != null)
            papHoldRadio.check(papHoldRadio.getChildAt(rest.getHasPapHolder()).getId());
        if (rest.getPapHolderType() != null)
            papHoldTypeRadio.check(papHoldTypeRadio.getChildAt(rest.getPapHolderType()).getId());
        if (rest.getPapEmbDist() != null)
            papEmbAValue.setText(String.valueOf(rest.getPapEmbDist()));
        if (rest.getPapEmbHeight() != null)
            papEmbBValue.setText(String.valueOf(rest.getPapEmbHeight()));
        if (rest.getPapSupAlign() != null)
            pHoldAlignRadio.check(pHoldAlignRadio.getChildAt(rest.getPapSupAlign()).getId());
        if (rest.getPapSupHeight() != null)
            papSupAValue.setText(String.valueOf(rest.getPapSupHeight()));
        if (rest.getPapHoldObs() != null)
            papObsValue.setText(rest.getPapHoldObs());
        if (rest.getHasDouche() != null)
            doucheRadio.check(doucheRadio.getChildAt(rest.getHasDouche()).getId());
        if (rest.getDoucheActHeight() != null)
            doucheActHeightValue.setText(String.valueOf(rest.getDoucheActHeight()));
        if (rest.getDouchePressHeight() != null)
            douchePressHeightValue.setText(String.valueOf(rest.getDouchePressHeight()));
        if (rest.getDoucheObs() != null)
            doucheObsValue.setText(rest.getDoucheObs());
        if (rest.getToiletObs() != null)
            tObsValue.setText(rest.getToiletObs());


    }

    public RestToiletUpdate barUpdate(Bundle bundle) {

        int tType, tHasSeat, tHasFrontBar, tHasWall, tHasPapHolder, hasDouche;
        Integer tHasSoculo = null, socCorners = null, hasHorBar = null, hasVertBar = null, hasSideBar = null, hasArtBar = null, pHolderType = null, pSupAligned = null;
        double tHeightNoSeat, tActHeight;
        Double tHeightSeat = null, fSoculo = null, lSoculo = null, fBarA = null, fBarB = null, fBarC = null, fBarSect = null,
                fBarDist = null, horBarD = null, horBarE = null, horBarF = null, horBarDist = null, horBarDistG = null, horBarSect = null, vertBarH = null,
                vertBarI = null, vertBarJ = null, vertBarSect = null, vertBarDist = null, sideBarD = null, sideBarE = null, sideBarDistG = null, sideBarSect = null,
                artBarH = null, artBarI = null, artBarJ = null, artBarSect = null,
                pEmbDist = null, pEmbHeight = null, pSupHeight = null, doucheActHeight = null, douchePressHeight = null;
        String tActDesc, tActObs, pHolderObs, doucheObs, tObs;

        tType = getCheckedRadioIndex(tTypeRadio);
        if (tType != 1) {
            tHasSoculo = getCheckedRadioIndex(tSocRadio);
            if (tHasSoculo == 1) {
                fSoculo = Double.parseDouble(String.valueOf(fSocValue.getText()));
                lSoculo = Double.parseDouble(String.valueOf(lSocValue.getText()));
                socCorners = getCheckedRadioIndex(socCornerRadio);
            }
        }
        tHeightNoSeat = Double.parseDouble(String.valueOf(tNoSeatHeightValue.getText()));
        tHasSeat = getCheckedRadioIndex(tSeatRadio);
        if (tHasSeat == 1)
            tHeightSeat = Double.parseDouble(String.valueOf(tSeatHeightValue.getText()));
        tHasFrontBar = getCheckedRadioIndex(tFrontBarRadio);
        if (tHasFrontBar == 1) {
            fBarA = Double.parseDouble(String.valueOf(fBarValueMeasureA.getText()));
            fBarB = Double.parseDouble(String.valueOf(fBarValueMeasureB.getText()));
            fBarC = Double.parseDouble(String.valueOf(fBarValueMeasureC.getText()));
            fBarSect = Double.parseDouble(String.valueOf(fBarSectValue.getText()));
            fBarDist = Double.parseDouble(String.valueOf(fBarDistValue.getText()));
        }
        tHasWall = getCheckedRadioIndex(tWallRadio);

        RestToiletSideBarsParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

        if (tHasWall == 0) {
            hasSideBar = parcel.getHasHorSideBar();
            if (hasSideBar == 1) {
                sideBarD = parcel.getHorSideMeasureD();
                sideBarE = parcel.getHorSideMeasureE();
                sideBarDistG = parcel.getHorSideMeasureG();
                sideBarSect = parcel.getHorSideDiam();
            }
            hasArtBar = parcel.getHasVertArtBar();
            if (hasArtBar == 1) {
                artBarH = parcel.getVertArtMeasureH();
                artBarI = parcel.getVertArtMeasureI();
                artBarJ = parcel.getVertArtMeasureJ();
                artBarSect = parcel.getVertArtDiam();
            }
        } else {

            hasHorBar = parcel.getHasHorSideBar();
            if (hasHorBar == 1) {
                horBarD = parcel.getHorSideMeasureD();
                horBarE = parcel.getHorSideMeasureE();
                horBarF = parcel.getHorMeasureF();
                horBarDistG = parcel.getHorSideMeasureG();
                horBarSect = parcel.getHorSideDiam();
                horBarDist = parcel.getHorDist();
            }
            hasVertBar = parcel.getHasVertArtBar();
            if (hasVertBar == 1) {
                vertBarH = parcel.getVertArtMeasureH();
                vertBarI = parcel.getVertArtMeasureI();
                vertBarJ = parcel.getVertArtMeasureJ();
                vertBarSect = parcel.getVertArtDiam();
                vertBarDist = parcel.getVertDist();
            }
        }
        tActDesc = String.valueOf(tActDescValue.getText());
        tActHeight = Double.parseDouble(String.valueOf(tActHeightValue.getText()));
        tActObs = String.valueOf(tActObsValue.getText());

        tHasPapHolder = getCheckedRadioIndex(papHoldRadio);
        if (tHasPapHolder == 1) {
            pHolderType = getCheckedRadioIndex(papHoldTypeRadio);
            if (pHolderType == 0) {
                pEmbDist = Double.parseDouble(String.valueOf(papEmbAValue.getText()));
                pEmbHeight = Double.parseDouble(String.valueOf(papEmbBValue.getText()));
            } else if (pHolderType == 1) {
                pSupAligned = getCheckedRadioIndex(pHoldAlignRadio);
                pSupHeight = Double.parseDouble(String.valueOf(papSupAValue.getText()));
            }
        }
        pHolderObs = String.valueOf(papObsValue.getText());

        hasDouche = getCheckedRadioIndex(doucheRadio);
        if (hasDouche == 1) {
            doucheActHeight = Double.parseDouble(String.valueOf(doucheActHeightValue.getText()));
            douchePressHeight = Double.parseDouble(String.valueOf(douchePressHeightValue.getText()));
        }

        doucheObs = String.valueOf(doucheObsValue.getText());

        tObs = String.valueOf(tObsValue.getText());

        return new RestToiletUpdate(resToilBundle.getInt(REST_ID), tType, tHeightNoSeat, tHasSeat, tHeightSeat, tHasSoculo, fSoculo, lSoculo, socCorners,
                tHasFrontBar, fBarA, fBarB, fBarC, fBarSect, fBarDist, tHasWall, hasHorBar, horBarD, horBarE, horBarF, horBarDistG, horBarSect, horBarDist,
                hasVertBar, vertBarH, vertBarI, vertBarJ, vertBarSect, vertBarDist, hasSideBar, sideBarD, sideBarE, sideBarDistG, sideBarSect, hasArtBar,
                artBarH, artBarI, artBarJ, artBarSect, tActDesc, tActHeight, tActObs, tHasPapHolder, pHolderType, pEmbDist, pEmbHeight, pSupAligned, pSupHeight,
                pHolderObs, hasDouche, douchePressHeight, doucheActHeight, doucheObs, tObs);
    }

    public RestBoxToilUpdate boxToilUpdate(Bundle bundle) {

        int tType, tHasSeat, tHasFrontBar, tHasWall, tHasPapHolder, hasDouche;
        Integer tHasSoculo = null, socCorners = null, hasHorBar = null, hasVertBar = null, hasSideBar = null, hasArtBar = null, pHolderType = null, pSupAligned = null;
        double tHeightNoSeat, tActHeight;
        Double tHeightSeat = null, fSoculo = null, lSoculo = null, fBarA = null, fBarB = null, fBarC = null, fBarSect = null,
                fBarDist = null, horBarD = null, horBarE = null, horBarF = null, horBarDist = null, horBarDistG = null, horBarSect = null, vertBarH = null,
                vertBarI = null, vertBarJ = null, vertBarSect = null, vertBarDist = null, sideBarD = null, sideBarE = null, sideBarDistG = null, sideBarSect = null,
                artBarH = null, artBarI = null, artBarJ = null, artBarSect = null,
                pEmbDist = null, pEmbHeight = null, pSupHeight = null, doucheActHeight = null, douchePressHeight = null;
        String tActDesc, tActObs, pHolderObs, doucheObs, tObs;

        tType = getCheckedRadioIndex(tTypeRadio);
        if (tType != 1) {
            tHasSoculo = getCheckedRadioIndex(tSocRadio);
            if (tHasSoculo == 1) {
                fSoculo = Double.parseDouble(String.valueOf(fSocValue.getText()));
                lSoculo = Double.parseDouble(String.valueOf(lSocValue.getText()));
                socCorners = getCheckedRadioIndex(socCornerRadio);
            }
        }
        tHeightNoSeat = Double.parseDouble(String.valueOf(tNoSeatHeightValue.getText()));
        tHasSeat = getCheckedRadioIndex(tSeatRadio);
        if (tHasSeat == 1)
            tHeightSeat = Double.parseDouble(String.valueOf(tSeatHeightValue.getText()));
        tHasFrontBar = getCheckedRadioIndex(tFrontBarRadio);
        if (tHasFrontBar == 1) {
            fBarA = Double.parseDouble(String.valueOf(fBarValueMeasureA.getText()));
            fBarB = Double.parseDouble(String.valueOf(fBarValueMeasureB.getText()));
            fBarC = Double.parseDouble(String.valueOf(fBarValueMeasureC.getText()));
            fBarSect = Double.parseDouble(String.valueOf(fBarSectValue.getText()));
            fBarDist = Double.parseDouble(String.valueOf(fBarDistValue.getText()));
        }
        tHasWall = getCheckedRadioIndex(tWallRadio);

        RestToiletSideBarsParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));

        if (tHasWall == 0) {
            hasSideBar = parcel.getHasHorSideBar();
            if (hasSideBar == 1) {
                sideBarD = parcel.getHorSideMeasureD();
                sideBarE = parcel.getHorSideMeasureE();
                sideBarDistG = parcel.getHorSideMeasureG();
                sideBarSect = parcel.getHorSideDiam();
            }
            hasArtBar = parcel.getHasVertArtBar();
            if (hasArtBar == 1) {
                artBarH = parcel.getVertArtMeasureH();
                artBarI = parcel.getVertArtMeasureI();
                artBarJ = parcel.getVertArtMeasureJ();
                artBarSect = parcel.getVertArtDiam();
            }
        } else {

            hasHorBar = parcel.getHasHorSideBar();
            if (hasHorBar == 1) {
                horBarD = parcel.getHorSideMeasureD();
                horBarE = parcel.getHorSideMeasureE();
                horBarF = parcel.getHorMeasureF();
                horBarDistG = parcel.getHorSideMeasureG();
                horBarSect = parcel.getHorSideDiam();
                horBarDist = parcel.getHorDist();
            }
            hasVertBar = parcel.getHasVertArtBar();
            if (hasVertBar == 1) {
                vertBarH = parcel.getVertArtMeasureH();
                vertBarI = parcel.getVertArtMeasureI();
                vertBarJ = parcel.getVertArtMeasureJ();
                vertBarSect = parcel.getVertArtDiam();
                vertBarDist = parcel.getVertDist();
            }
        }
        tActDesc = String.valueOf(tActDescValue.getText());
        tActHeight = Double.parseDouble(String.valueOf(tActHeightValue.getText()));
        tActObs = String.valueOf(tActObsValue.getText());

        tHasPapHolder = getCheckedRadioIndex(papHoldRadio);
        if (tHasPapHolder == 1) {
            pHolderType = getCheckedRadioIndex(papHoldTypeRadio);
            if (pHolderType == 0) {
                pEmbDist = Double.parseDouble(String.valueOf(papEmbAValue.getText()));
                pEmbHeight = Double.parseDouble(String.valueOf(papEmbBValue.getText()));
            } else if (pHolderType == 1) {
                pSupAligned = getCheckedRadioIndex(pHoldAlignRadio);
                pSupHeight = Double.parseDouble(String.valueOf(papSupAValue.getText()));
            }
        }
        pHolderObs = String.valueOf(papObsValue.getText());

        hasDouche = getCheckedRadioIndex(doucheRadio);
        if (hasDouche == 1) {
            doucheActHeight = Double.parseDouble(String.valueOf(doucheActHeightValue.getText()));
            douchePressHeight = Double.parseDouble(String.valueOf(douchePressHeightValue.getText()));
        }

        doucheObs = String.valueOf(doucheObsValue.getText());

        tObs = String.valueOf(tObsValue.getText());

        return new RestBoxToilUpdate(resToilBundle.getInt(BOX_ID), tType, tHeightNoSeat, tHasSeat, tHeightSeat, tHasSoculo, fSoculo, lSoculo, socCorners,
                tHasFrontBar, fBarA, fBarB, fBarC, fBarSect, fBarDist, tHasWall, hasHorBar, horBarD, horBarE, horBarF, horBarDistG, horBarSect, horBarDist,
                hasVertBar, vertBarH, vertBarI, vertBarJ, vertBarSect, vertBarDist, hasSideBar, sideBarD, sideBarE, sideBarDistG, sideBarSect, hasArtBar,
                artBarH, artBarI, artBarJ, artBarSect, tActDesc, tActHeight, tActObs, tHasPapHolder, pHolderType, pEmbDist, pEmbHeight, pSupAligned, pSupHeight,
                pHolderObs, hasDouche, douchePressHeight, doucheActHeight, doucheObs, tObs);
    }

}