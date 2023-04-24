package com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.FallProtectionEntry;
import com.mpms.relatorioacessibilidadecortec.data.parcels.FallProtectParcel;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class FallProtectChildFragment extends Fragment implements RadioGroupInterface, TagInterface, ScrollEditText {

    TextInputLayout protectSizeField, obsField;
    TextInputEditText protectSizeValue, obsValue;
    TextView visualContrastHeader, visualContrastError, tactileContrastHeader, tactileContrastError;
    RadioGroup visualContrastRadio, tactileContrastRadio;

    ViewModelEntry modelEntry;

    static int layout;
    static Bundle dataBundle;
    Bundle imgData;

    public FallProtectChildFragment() {
        // Required empty public constructor
    }

    public static FallProtectChildFragment newInstance(int visual, Bundle bundle) {
        layout = visual;
        dataBundle = bundle;
        return new FallProtectChildFragment();
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
        return inflater.inflate(R.layout.fragment_fall_protect_child, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateViews(view);

        if (dataBundle.getInt(PROTECT_ID) > 0)
            modelEntry.getOneFallProtection(dataBundle.getInt(PROTECT_ID)).observe(getViewLifecycleOwner(), this::loadProtectData);

        getParentFragmentManager().setFragmentResultListener(GATHER_CHILD_DATA, getViewLifecycleOwner(), (key, bundle) -> {
            if (noEmptyFields()) {
                FallProtectParcel parcel = createProtectParcel();
                bundle.putParcelable(CHILD_PARCEL, Parcels.wrap(parcel));
                bundle.putBoolean(CHILD_DATA_COMPLETE, true);
            } else
                bundle.putBoolean(CHILD_DATA_COMPLETE, false);
            getParentFragmentManager().setFragmentResult(CHILD_DATA_LISTENER, bundle);
        });
    }

    private void instantiateViews(View view) {
//        TextInputLayout
        protectSizeField = view.findViewById(R.id.fall_protect_width_field);
        obsField = view.findViewById(R.id.fall_protect_obs_field);
//        TextInputEditText
        protectSizeValue = view.findViewById(R.id.fall_protect_width_value);
        obsValue = view.findViewById(R.id.fall_protect_obs_value);
//        TextView
        visualContrastError = view.findViewById(R.id.fall_protect_color_contrast_error);
        visualContrastHeader = view.findViewById(R.id.fall_protect_color_contrast_header);
        tactileContrastError = view.findViewById(R.id.fall_protect_tactile_contrast_error);
        tactileContrastHeader = view.findViewById(R.id.fall_protect_tactile_contrast_header);
//        RadioGroup
        visualContrastRadio = view.findViewById(R.id.fall_protect_color_contrast_radio);
        tactileContrastRadio = view.findViewById(R.id.fall_protect_tactile_contrast_radio);
//        ViewModelEntry
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
//        Layout
        if (layout == 0)
            protectSizeField.setHint("Largura da Faixa de Proteção (m)");
        else if (layout == 1) {
            protectSizeField.setHint("Altura da Proteção Vertical (m)");
            tactileContrastRadio.setVisibility(View.GONE);
            tactileContrastHeader.setVisibility(View.GONE);
        } else if (layout == 2) {
            protectSizeField.setHint("Altura do Guarda Corpo (m)");
            visualContrastRadio.setVisibility(View.GONE);
            visualContrastHeader.setVisibility(View.GONE);
            tactileContrastRadio.setVisibility(View.GONE);
            tactileContrastHeader.setVisibility(View.GONE);
        } else {
// Fechar Fragmento
        }
    }

    private boolean noEmptyFields() {
        clearErrors();
        int i = 0;
        if (TextUtils.isEmpty(protectSizeValue.getText())) {
            i++;
            protectSizeField.setError(getString(R.string.req_field_error));
        }
        if (indexRadio(visualContrastRadio) == -1) {
            i++;
            visualContrastError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(tactileContrastRadio) == -1) {
            i++;
            tactileContrastError.setVisibility(View.VISIBLE);
        }

        return i == 0;
    }

    private void clearErrors() {
        protectSizeField.setErrorEnabled(false);
        visualContrastError.setVisibility(View.GONE);
        tactileContrastError.setVisibility(View.GONE);
    }

    private FallProtectParcel createProtectParcel() {
        Double lengthHeight = null;
        Integer hasTactile = null, hasVisual = null;
        String obs = null;

        if (!TextUtils.isEmpty(protectSizeValue.getText()))
            lengthHeight = Double.parseDouble(String.valueOf(protectSizeValue.getText()));
        switch (layout) {
            case 0:
                hasTactile = indexRadio(tactileContrastRadio);
            case 1:
                hasVisual = indexRadio(visualContrastRadio);
                break;
            default:
                break;
        }

        if (!TextUtils.isEmpty(obsValue.getText()))
            obs = String.valueOf(obsValue.getText());

        return new FallProtectParcel(lengthHeight, hasVisual, hasTactile, obs);
    }

    private void loadProtectData(FallProtectionEntry entry) {
        if (entry.getProtectWidthLength() != null)
            protectSizeValue.setText(String.valueOf(entry.getProtectWidthLength()));
        if (entry.getHasVisualContrast() != null)
            checkRadioGroup(visualContrastRadio, entry.getHasVisualContrast());
        if (entry.getHasTactileContrast() != null)
            checkRadioGroup(tactileContrastRadio, entry.getHasTactileContrast());
        if (entry.getFallProtectObs() != null)
            obsValue.setText(String.valueOf(entry.getFallProtectObs()));
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {

    }
}