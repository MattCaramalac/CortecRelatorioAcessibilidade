package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.InclinationParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.parceler.Parcels;

import java.util.ArrayList;


public class SillInclinationFragment extends Fragment implements TagInterface {

    TextInputLayout inclHeightField, inclField1, inclField2, inclField3, inclField4;
    TextInputEditText inclHeightValue, inclValue1, inclValue2, inclValue3, inclValue4;
    MaterialButton addMeasure;
    ImageButton delMeasure;

    int measureQnt = 1;

    ArrayList<TextInputLayout> inclArray = new ArrayList<>();

    ViewModelEntry modelEntry;

    public SillInclinationFragment() {
        // Required empty public constructor
    }

    public static SillInclinationFragment newInstance() {
        return new SillInclinationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sill_inclination, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateInclinationSillView(view);

        addMeasure.setOnClickListener(v -> {
            if (measureQnt < 1) {
                measureQnt = 1;
                Toast.makeText(getContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            } else if (measureQnt < 4) {
                if (measureQnt == 1)
                    delMeasure.setVisibility(View.VISIBLE);
                inclArray.get(measureQnt).setVisibility(View.VISIBLE);
                measureQnt++;
            } else
                Toast.makeText(getContext(), "O limite de medições foi atingido!", Toast.LENGTH_SHORT).show();
        });

        delMeasure.setOnClickListener(v -> {
            if (measureQnt > 1) {
                inclArray.get(measureQnt - 1).getEditText().setText(null);
                inclArray.get(measureQnt - 1).setVisibility(View.GONE);
                measureQnt--;
                if (measureQnt == 1)
                    delMeasure.setVisibility(View.GONE);
            }
        });

        getParentFragmentManager().setFragmentResultListener(LOAD_CHILD_DATA, this, (key, bundle) -> {
            if (bundle.getInt(DOOR_ID) > 0) {
                modelEntry.getSpecificDoor(bundle.getInt(DOOR_ID)).observe(getViewLifecycleOwner(), this::loadInclinationDoorData);
            } else if (bundle.getBoolean(FROM_EXT_ACCESS)) {
                modelEntry.getOneExternalAccess(bundle.getInt(AMBIENT_ID))
                        .observe(getViewLifecycleOwner(), this::loadInclinationExtAccData);
            } else if (bundle.getInt(PLAY_ID) > 0) {
                modelEntry.getOnePlayground(bundle.getInt(PLAY_ID))
                        .observe(getViewLifecycleOwner(), this::loadInclinationPlayData);
            } else if (bundle.getInt(SIDEWALK_SLOPE_ID) > 0) {
                modelEntry.getSidewalkSlopeEntry(bundle.getInt(SIDEWALK_SLOPE_ID))
                        .observe(getViewLifecycleOwner(), this::loadInclinationSlopeStreetData);
            } else if (bundle.getInt(REST_ID) > 0) {
                modelEntry.getOneRestroomEntry(bundle.getInt(REST_ID))
                        .observe(getViewLifecycleOwner(), this::loadRestIncSlope);
            }
        });

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, this, (key, bundle) -> {
            if (checkInclinationNoEmptyFields(bundle))
                createInclParcel(bundle);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });

    }

    private void instantiateInclinationSillView(View view) {
//        TextInputLayout
        inclHeightField = view.findViewById(R.id.sill_inclination_height_field);
        inclField1 = view.findViewById(R.id.incl_measure_1_field);
        inclField2 = view.findViewById(R.id.incl_measure_2_field);
        inclField3 = view.findViewById(R.id.incl_measure_3_field);
        inclField4 = view.findViewById(R.id.incl_measure_4_field);
//        TextInputEditText
        inclHeightValue = view.findViewById(R.id.sill_inclination_height_value);
        inclValue1 = view.findViewById(R.id.incl_measure_1_value);
        inclValue2 = view.findViewById(R.id.incl_measure_2_value);
        inclValue3 = view.findViewById(R.id.incl_measure_3_value);
        inclValue4 = view.findViewById(R.id.incl_measure_4_value);
//        MaterialButton
        addMeasure = view.findViewById(R.id.add_incl_measure_button);
//        ImageButton
        delMeasure = view.findViewById(R.id.delete_incl_measure);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        addFieldsToArray();
    }

    private void addFieldsToArray() {
        inclArray.add(inclField1);
        inclArray.add(inclField2);
        inclArray.add(inclField3);
        inclArray.add(inclField4);
    }

    private boolean checkInclinationNoEmptyFields(Bundle bundle) {
        clearErrorInclinationSill();
        int i = 0;
        if (TextUtils.isEmpty(inclHeightValue.getText())) {
            inclHeightField.setError(getString(R.string.req_field_error));
            i++;
        }

        switch (measureQnt) {
            case 4:
                if (TextUtils.isEmpty(inclValue4.getText())) {
                    inclField4.setError(getString(R.string.req_field_error));
                    i++;
                }
            case 3:
                if (TextUtils.isEmpty(inclValue3.getText())) {
                    inclField3.setError(getString(R.string.req_field_error));
                    i++;
                }
            case 2:
                if (TextUtils.isEmpty(inclValue2.getText())) {
                    inclField2.setError(getString(R.string.req_field_error));
                    i++;
                }
            case 1:
                if (TextUtils.isEmpty(inclValue1.getText())) {
                    inclField1.setError(getString(R.string.req_field_error));
                    i++;
                }
                break;
        }
        if (!bundle.getBoolean(ADD_ITEM_REQUEST)) {
            bundle.putBoolean(CHILD_DATA_COMPLETE, i == 0);
        }
        return i == 0;
    }

    private void createInclParcel(Bundle bundle) {
        Double inclHeight = null, measure1 = null, measure2 = null, measure3 = null, measure4 = null;

        if (!TextUtils.isEmpty(inclHeightValue.getText()))
            inclHeight = Double.parseDouble(String.valueOf(inclHeightValue.getText()));

        switch (measureQnt) {
            case 4:
                if (!TextUtils.isEmpty(inclValue4.getText()))
                    measure4 = Double.parseDouble(String.valueOf(inclValue4.getText()));
            case 3:
                if (!TextUtils.isEmpty(inclValue3.getText()))
                    measure3 = Double.parseDouble(String.valueOf(inclValue3.getText()));
            case 2:
                if (!TextUtils.isEmpty(inclValue2.getText()))
                    measure2 = Double.parseDouble(String.valueOf(inclValue2.getText()));
            case 1:
                if (!TextUtils.isEmpty(inclValue1.getText()))
                    measure1 = Double.parseDouble(String.valueOf(inclValue1.getText()));
                break;
        }
        InclinationParcel parcel = new InclinationParcel(inclHeight, measureQnt, measure1, measure2, measure3, measure4);
        bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
    }

    private void clearErrorInclinationSill() {
        inclHeightField.setErrorEnabled(false);
    }

    private void loadInclinationExtAccData(ExternalAccess access) {
        if (access.getSillInclinationHeight() != null)
            inclHeightValue.setText(String.valueOf(access.getSillInclinationHeight()));
        if (access.getInclQnt() != null)
            measureQnt = access.getInclQnt();
        switch (measureQnt) {
            case 4:
                if (access.getInclAngle4() != null) {
                    inclField4.setVisibility(View.VISIBLE);
                    inclValue4.setText(String.valueOf(access.getInclAngle4()));
                }
            case 3:
                if (access.getInclAngle3() != null) {
                    inclField3.setVisibility(View.VISIBLE);
                    inclValue3.setText(String.valueOf(access.getInclAngle3()));
                }
            case 2:
                if (access.getInclAngle2() != null) {
                    inclField2.setVisibility(View.VISIBLE);
                    inclValue2.setText(String.valueOf(access.getInclAngle2()));
                }
            default:
                if (access.getInclAngle1() != null)
                    inclValue1.setText(String.valueOf(access.getInclAngle1()));
                break;
        }
    }

    private void loadInclinationPlayData(PlaygroundEntry playEntry) {
        if (playEntry.getInclHeight() != null)
            inclHeightValue.setText(String.valueOf(playEntry.getInclHeight()));
        if (playEntry.getInclMeasureQnt() != null)
            measureQnt = playEntry.getInclMeasureQnt();
        switch (measureQnt) {
            case 4:
                if (playEntry.getInclAngle4() != null) {
                    inclField4.setVisibility(View.VISIBLE);
                    inclValue4.setText(String.valueOf(playEntry.getInclAngle4()));
                }
            case 3:
                if (playEntry.getInclAngle3() != null) {
                    inclField3.setVisibility(View.VISIBLE);
                    inclValue3.setText(String.valueOf(playEntry.getInclAngle3()));
                }
            case 2:
                if (playEntry.getInclAngle2() != null) {
                    inclField2.setVisibility(View.VISIBLE);
                    inclValue2.setText(String.valueOf(playEntry.getInclAngle2()));
                }
            default:
                if (playEntry.getInclAngle1() != null)
                    inclValue1.setText(String.valueOf(playEntry.getInclAngle1()));
                break;
        }
    }

    private void loadInclinationDoorData(DoorEntry doorEntry) {
        if (doorEntry.getSillInclinationHeight() != null)
            inclHeightValue.setText(String.valueOf(doorEntry.getSillInclinationHeight()));
        if (doorEntry.getInclQnt() != null)
            measureQnt = doorEntry.getInclQnt();
        switch (measureQnt) {
            case 4:
                if (doorEntry.getInclAngle4() != null) {
                    inclField4.setVisibility(View.VISIBLE);
                    inclValue4.setText(String.valueOf(doorEntry.getInclAngle4()));
                }
            case 3:
                if (doorEntry.getInclAngle3() != null) {
                    inclField3.setVisibility(View.VISIBLE);
                    inclValue3.setText(String.valueOf(doorEntry.getInclAngle3()));
                }
            case 2:
                if (doorEntry.getInclAngle2() != null) {
                    inclField2.setVisibility(View.VISIBLE);
                    inclValue2.setText(String.valueOf(doorEntry.getInclAngle2()));
                }
            default:
                if (doorEntry.getInclAngle1() != null)
                    inclValue1.setText(String.valueOf(doorEntry.getInclAngle1()));
                break;
        }
    }

    private void loadInclinationSlopeStreetData(SidewalkSlopeEntry slopeEntry) {
        if (slopeEntry.getInclinationJunctionHeight() != null)
            inclHeightValue.setText(String.valueOf(slopeEntry.getInclinationJunctionHeight()));
        if (slopeEntry.getInclQnt() != null)
            measureQnt = slopeEntry.getInclQnt();
        switch (measureQnt) {
            case 4:
                if (slopeEntry.getInclAngle4() != null) {
                    inclField4.setVisibility(View.VISIBLE);
                    inclValue4.setText(String.valueOf(slopeEntry.getInclAngle4()));
                }
            case 3:
                if (slopeEntry.getInclAngle3() != null) {
                    inclField3.setVisibility(View.VISIBLE);
                    inclValue3.setText(String.valueOf(slopeEntry.getInclAngle3()));
                }
            case 2:
                if (slopeEntry.getInclAngle2() != null) {
                    inclField2.setVisibility(View.VISIBLE);
                    inclValue2.setText(String.valueOf(slopeEntry.getInclAngle2()));
                }
            default:
                if (slopeEntry.getInclAngle1() != null)
                    inclValue1.setText(String.valueOf(slopeEntry.getInclAngle1()));
                break;
        }
    }

//    TODO - Atualizar o carregamento de dados assim que for alterada a tabela de sanitários
    private void loadRestIncSlope(RestroomEntry entry) {
        if (entry.getSillIncHeight() != null)
            inclHeightValue.setText(String.valueOf(entry.getSillIncHeight()));
    }
}