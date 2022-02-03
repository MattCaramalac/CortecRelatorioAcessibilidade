package com.mpms.relatorioacessibilidadecortec.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.BlockRegisterActivity;
import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.ExtAccessSocialFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.ExtAccessVehicleFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.ExtAccessSocialFragment.FRAG_DATA;
import static com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.ExtAccessSocialFragment.SOCIAL_FRAG;

public class ExternalAccessFragment extends Fragment {

    public static final String EXT_ACCESS_SAVE_ATTEMPT = "EXT_ACCESS_SAVE_ATTEMPT";

    RadioGroup entranceTypeRadio;
    TextInputLayout entranceLocationField, externalAccessObsField;
    TextInputEditText entranceLocationValue, externalAccessObsValue;
    Button saveExternalAccess, cancelExternalAccess;
    TextView accessTypeError;
    Fragment accessType;

    Bundle extBundle = new Bundle();
    Bundle fragComm = new Bundle();

    ArrayList<String> fragData = new ArrayList<>();

    ArrayList<String> extFrag = new ArrayList<>(Arrays.
            asList(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null));

    public static final String EXT_ACCESS_ID = "EXT_ACCESS_ID";
    public static final String EXT_ARRAY = "EXT_ARRAY";
    public static final String EXT_GATHER_DATA = "EXT_GATHER_DATA";

    int existingEntry = 0;
    int recentEntry = 0;

    private int lastExtAccess;

    private ViewModelEntry modelEntry;

    public ExternalAccessFragment() {
        // Required empty public constructor
    }

    public static ExternalAccessFragment newInstance() {
        return new ExternalAccessFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            extBundle.putInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER, this.getArguments().getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER));
            extBundle.putInt(EXT_ACCESS_ID, this.getArguments().getInt(EXT_ACCESS_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_external_access, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateExternalAccessViews(view);
        allowExternalObsScroll();

        if (extBundle.getInt(EXT_ACCESS_ID) > 0) {
            modelEntry.getOneExternalAccess(extBundle.getInt(EXT_ACCESS_ID)).observe(getViewLifecycleOwner(), this::gatherExtAccessInfo);
            getChildFragmentManager().setFragmentResult(EXT_GATHER_DATA, extBundle);
            existingEntry = 1;
        }

//      Como a verificação estava ocorrendo depois do dialogo ser criado, coloca-se o chamado do dialogo dentro do observer
//      Para garantir que o bundle receba o ID necessário. (existe solução mais elegante?)
//        TODO - Procurar solução possivelmente mais elegante
//        modelEntry.getLastExternalAccess().observe(getViewLifecycleOwner(), lastAccess -> {
//            if (recentEntry == 1) {
//                lastExtAccess = lastAccess.getExternalAccessID();
//                extBundle.putInt(EXT_ACCESS_ID, lastExtAccess);
////                buttonClicked(extButtonChoice);
//                extButtonChoice = -1;
//            }
//        });

        cancelExternalAccess.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStackImmediate());

        saveExternalAccess.setOnClickListener(v -> {
//            TODO - Implementar método de update também
            if (checkEmptyFields()) {

            }


//            if (checkEmptyFields()) {
//                if (upCounter == 0) {
//                    ExternalAccess newAccess = newExtAccess(extBundle);
////                    TODO - Alterar método de gravação de dados, precisa alterar a tabela também
//                    if (newAccess != null)
//                        ViewModelEntry.insertExternalAccess(newAccess);
//                    Toast.makeText(getContext(), R.string.register_created_message, Toast.LENGTH_SHORT).show();
//                    clearFields();
//                } else if (upCounter > 0) {
//                    ExternalAccess upAccess = newExtAccess(extBundle);
//                    if (recentEntry == 1) {
//                        upAccess.setExternalAccessID(lastExtAccess);
//                        recentEntry = 0;
//                    }
//                    if (existingEntry == 1) {
//                        upAccess.setExternalAccessID(extBundle.getInt(EXT_ACCESS_ID));
//                        existingEntry = 0;
//                    }
////                    TODO - Alterar método de gravação de dados, precisa alterar a tabela também
//                    if (upAccess != null)
//                        ViewModelEntry.updateExternalAccess(upAccess);
//                    Toast.makeText(getContext(), "Cadastro atualizado com sucesso!", Toast.LENGTH_SHORT).show();
//                    clearFields();
//                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
//                } else {
//                    Toast.makeText(getContext(), "Algo inesperado ocorreu. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
//                    clearFields();
//                }
//            } else
//                Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        });

        getChildFragmentManager().setFragmentResultListener(FRAG_DATA, this, (key, bundle) -> {
            fragData = bundle.getStringArrayList(SOCIAL_FRAG);
            ExternalAccess extAccess = newExtAccess(extBundle, fragData);
            if (extBundle.getInt(EXT_ACCESS_ID) != 0) {

            } else {
                if(!Objects.equals(extFrag.get(18), "false")) {
                    ViewModelEntry.insertExternalAccess(extAccess);
                    clearExtAccessFields();
                    Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private void instantiateExternalAccessViews(View view) {
//        TextInputLayout
        entranceLocationField = view.findViewById(R.id.entrance_location_field);
        externalAccessObsField = view.findViewById(R.id.external_access_obs_field);
//        TextInputEditText
        entranceLocationValue = view.findViewById(R.id.entrance_location_value);
        externalAccessObsValue = view.findViewById(R.id.external_access_obs_value);
//        RadioGroup
        entranceTypeRadio = view.findViewById(R.id.external_access_type_radio);
//        TextView
        accessTypeError = view.findViewById(R.id.external_access_type_error);
//        MaterialButton
        saveExternalAccess = view.findViewById(R.id.save_ext_access);
        cancelExternalAccess = view.findViewById(R.id.cancel_ext_access);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        entranceTypeRadio.setOnCheckedChangeListener(this::radioGroupActivation);
    }

    private void gatherExtAccessInfo(ExternalAccess extAccess) {
        entranceLocationValue.setText(extAccess.getAccessLocation());
        entranceTypeRadio.check(entranceTypeRadio.getChildAt(extAccess.getEntranceType()).getId());
        externalAccessObsValue.setText(extAccess.getExtAccessObs());
    }

    private int getRadioCheckIndex(RadioGroup radio) {
        return radio.indexOfChild(radio.findViewById(radio.getCheckedRadioButtonId()));
    }

    public void radioGroupActivation(RadioGroup radioGroup, int checkedID) {
        int index = radioGroup.indexOfChild(radioGroup.findViewById(checkedID));
        if (index == 1)
            accessType = new ExtAccessVehicleFragment();
        else
            accessType = new ExtAccessSocialFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.external_access_layout, accessType).commit();
    }

    private boolean scrollingField(View v, MotionEvent event) {
        v.getParent().requestDisallowInterceptTouchEvent(true);
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP) {
            v.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void allowExternalObsScroll() {
        externalAccessObsValue.setOnTouchListener(this::scrollingField);
    }

    public boolean checkEmptyFields() {
        clearExternalAccessErrors();
        int errors = 0;
        if (TextUtils.isEmpty(entranceLocationValue.getText())) {
            errors++;
            entranceLocationField.setError(getText(R.string.blank_field_error));
            extFrag.set(18, "false");
        } else
            extFrag.set(18, null);
        if (entranceTypeRadio.getCheckedRadioButtonId() == -1) {
            accessTypeError.setVisibility(View.VISIBLE);
            errors++;
        } else {
            fragComm.putStringArrayList(EXT_ARRAY, extFrag);
            getChildFragmentManager().setFragmentResult(EXT_ACCESS_SAVE_ATTEMPT, fragComm);
        }
        return errors == 0;
    }

    public void clearExternalAccessErrors() {
        entranceLocationField.setErrorEnabled(false);
        accessTypeError.setVisibility(View.GONE);
    }

    private void clearExtAccessFields() {
        entranceLocationValue.setText(null);
        entranceTypeRadio.clearCheck();
        externalAccessObsValue.setText(null);
        getChildFragmentManager().beginTransaction().remove(accessType).commit();
        accessType = null;
    }

    public ExternalAccess newExtAccess(Bundle bundle, ArrayList<String> arrayList) {
        String location, extAccessObs, obsSIA, floorType, gateSillObs;
        Integer entranceType, hasSIA = null, gateHasTracks = null, gateHasTrackRamp = null, gateSillType = null, gateHasObstacles = null,
                gateHasPayphone = null, gateHasIntercom = null, gateHasSoundSign = null;
        Double gateWidth = null, gateTrackHeight = null, sillInclinationHeight = null, sillStepHeight = null, sillSlopeAngle = null, sillSlopeWidth = null, intercomHeight = null;

        location = String.valueOf(entranceLocationValue.getText());
        extAccessObs = String.valueOf(externalAccessObsValue.getText());
        entranceType = getRadioCheckIndex(entranceTypeRadio);
        if (arrayList.get(0) != null)
            hasSIA = Integer.valueOf(arrayList.get(0));
        obsSIA = arrayList.get(1);
        floorType = arrayList.get(2);
        if (arrayList.get(3) != null)
            gateWidth = Double.valueOf(arrayList.get(3));
        if (arrayList.get(4) != null)
            gateHasTracks = Integer.valueOf(arrayList.get(4));
        if (arrayList.get(5) != null)
            gateTrackHeight = Double.valueOf(arrayList.get(5));
        if (arrayList.get(6) != null)
            gateHasTrackRamp = Integer.valueOf(arrayList.get(6));
        if (arrayList.get(7) != null)
            gateSillType = Integer.valueOf(arrayList.get(7));
        if (arrayList.get(8) != null)
            sillInclinationHeight = Double.valueOf(arrayList.get(8));
        if (arrayList.get(9) != null)
            sillStepHeight = Double.valueOf(arrayList.get(9));
        if (arrayList.get(10) != null)
            sillSlopeAngle = Double.valueOf(arrayList.get(10));
        if (arrayList.get(11) != null)
            sillSlopeWidth = Double.valueOf(arrayList.get(11));
        gateSillObs = arrayList.get(12);
        if (arrayList.get(13) != null)
            gateHasObstacles = Integer.valueOf(arrayList.get(13));
        if (arrayList.get(14) != null)
            gateHasPayphone = Integer.valueOf(arrayList.get(14));
        if (arrayList.get(15) != null)
            gateHasIntercom = Integer.valueOf(arrayList.get(15));
        if (arrayList.get(16) != null)
            intercomHeight = Double.valueOf(arrayList.get(16));
        if (arrayList.get(17) != null)
            gateHasSoundSign = Integer.valueOf(arrayList.get(17));

        return new ExternalAccess(bundle.getInt(BlockRegisterActivity.BLOCK_SPACE_REGISTER), location, entranceType, extAccessObs, hasSIA, obsSIA, floorType, gateWidth,
                gateHasTracks, gateTrackHeight, gateHasTrackRamp, gateSillType, sillInclinationHeight, sillStepHeight, sillSlopeAngle, sillSlopeWidth, gateSillObs,
                gateHasObstacles, gateHasPayphone, gateHasIntercom, intercomHeight, gateHasSoundSign);
    }

}
