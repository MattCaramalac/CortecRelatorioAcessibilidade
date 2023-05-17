package com.mpms.relatorioacessibilidadecortec.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PoolEntryTwo;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.PoolBenchListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.PoolEquipListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.PoolRampListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters.PoolStairsListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.CounterInterface;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.ScrollEditText;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;
import com.whygraphics.multilineradiogroup.MultiLineRadioGroup;

import org.jetbrains.annotations.NotNull;


public class PoolTwoFragment extends Fragment implements TagInterface, ScrollEditText, RadioGroupInterface, CounterInterface {

    RadioGroup sportsRadio, pavementRadio, accessRadio, competitionRadio, rampRadio, stairsRadio, benchRadio, equipRadio;
    MultiLineRadioGroup poolType;
    TextInputLayout pavementWidthField, pavementObsField, poolDepthField, photoField, obsField;
    TextInputEditText pavementWidthValue, pavementObsValue, poolDepthValue, photoValue, obsValue;
    MaterialButton addRamp, addStairs, addBench, addEquip, returnPool, savePool;
    TextView sportsError, pavementHeader, pavementError, accessHeader, accessError, competitionError, typeError,
            rampError, stairsError, benchError, equipError, rampCounter, stairsCounter, benchCounter, equipCounter;
    ViewModelEntry modelEntry;
    Bundle poolTwoBundle;

    int cRamp = 0, cStairs = 0, cBench = 0, cEquip = 0;

    public PoolTwoFragment() {
        // Required empty public constructor
    }

    public static PoolTwoFragment newInstance() {
        return new PoolTwoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            poolTwoBundle = new Bundle(this.getArguments());
        else
            poolTwoBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pool_two, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiatePoolTwoView(view);

        modelEntry.getPool(poolTwoBundle.getInt(POOL_ID)).observe(getViewLifecycleOwner(), this::loadPoolTwoData);

    }

    private void instantiatePoolTwoView(View view) {
//        RadioGroup
        sportsRadio = view.findViewById(R.id.sports_pool_radio);
        pavementRadio = view.findViewById(R.id.pool_pavement_side_radio);
        accessRadio = view.findViewById(R.id.pool_pavement_access_radio);
        competitionRadio = view.findViewById(R.id.competition_pool_radio);
        rampRadio = view.findViewById(R.id.pool_has_ramp_radio);
        stairsRadio = view.findViewById(R.id.pool_has_stairs_radio);
        benchRadio = view.findViewById(R.id.pool_has_bench_radio);
        equipRadio = view.findViewById(R.id.pool_has_equip_radio);
        sportsRadio.setOnCheckedChangeListener(this::radioListener);
        pavementRadio.setOnCheckedChangeListener(this::radioListener);
        rampRadio.setOnCheckedChangeListener(this::radioListener);
        stairsRadio.setOnCheckedChangeListener(this::radioListener);
        benchRadio.setOnCheckedChangeListener(this::radioListener);
        equipRadio.setOnCheckedChangeListener(this::radioListener);
//        MultiLineRadioGroup
        poolType = view.findViewById(R.id.pool_type_radio);
//        TextInputLayout
        pavementWidthField = view.findViewById(R.id.pool_pavement_width_field);
        pavementObsField = view.findViewById(R.id.pool_pavement_access_obs_field);
        poolDepthField = view.findViewById(R.id.pool_max_depth_field);
        photoField = view.findViewById(R.id.pool_photo_field_2);
        obsField = view.findViewById(R.id.pool_obs_field);
//        TextInputEditText
        pavementWidthValue = view.findViewById(R.id.pool_pavement_width_value);
        pavementObsValue = view.findViewById(R.id.pool_pavement_access_obs_value);
        poolDepthValue = view.findViewById(R.id.pool_max_depth_value);
        photoValue = view.findViewById(R.id.pool_photo_value_2);
        obsValue = view.findViewById(R.id.pool_obs_value);
//        MaterialButton
        addRamp = view.findViewById(R.id.pool_add_ramp_button);
        addStairs = view.findViewById(R.id.pool_add_stairs_button);
        addBench = view.findViewById(R.id.pool_add_bench_button);
        addEquip = view.findViewById(R.id.pool_add_equip_button);
        returnPool = view.findViewById(R.id.return_pool_screen_1);
        savePool = view.findViewById(R.id.proceed_save_pool);
        addRamp.setOnClickListener(this::clickListener);
        addStairs.setOnClickListener(this::clickListener);
        addBench.setOnClickListener(this::clickListener);
        addEquip.setOnClickListener(this::clickListener);
        returnPool.setOnClickListener(this::clickListener);
        savePool.setOnClickListener(this::clickListener);
//        TextView
        sportsError = view.findViewById(R.id.sports_pool_error);
        pavementHeader = view.findViewById(R.id.pool_pavement_side_header);
        pavementError = view.findViewById(R.id.pool_pavement_side_error);
        accessHeader = view.findViewById(R.id.pool_pavement_access_header);
        accessError = view.findViewById(R.id.pool_pavement_access_error);
        competitionError = view.findViewById(R.id.competition_pool_error);
        typeError = view.findViewById(R.id.pool_type_error);
        rampError = view.findViewById(R.id.pool_has_ramp_error);
        stairsError = view.findViewById(R.id.pool_has_stairs_error);
        benchError = view.findViewById(R.id.pool_has_bench_error);
        equipError = view.findViewById(R.id.pool_has_equip_error);
        rampCounter = view.findViewById(R.id.pool_ramp_counter);
        stairsCounter = view.findViewById(R.id.pool_stairs_counter);
        benchCounter = view.findViewById(R.id.pool_bench_counter);
        equipCounter = view.findViewById(R.id.pool_equip_counter);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());

        counterListener();
    }

    private void loadPoolTwoData(PoolEntry pool) {
        if (pool.getIsSportsPool() != null) {
            checkRadioGroup(sportsRadio, pool.getIsSportsPool());
            if (pool.getIsSportsPool() == 0) {
                if (pool.getHasPavementedSide() != null) {
                    checkRadioGroup(pavementRadio, pool.getHasPavementedSide());
                    if (pool.getHasPavementedSide() == 1) {
                        if (pool.getPavementWidth() != null)
                            pavementWidthValue.setText(String.valueOf(pool.getPavementWidth()));
                        if (pool.getIsPavementAccess() != null)
                            checkRadioGroup(accessRadio, pool.getIsPavementAccess());
                    }
                }
            }
            if (pool.getPavementObs() != null)
                pavementObsValue.setText(pool.getPavementObs());
            if (pool.getUsedInCompetitions() != null)
                checkRadioGroup(competitionRadio, pool.getUsedInCompetitions());
            if (pool.getPoolDepth() != null)
                poolDepthValue.setText(String.valueOf(pool.getPoolDepth()));
            if (pool.getPoolType() != null)
                poolType.checkAt(pool.getPoolType());
            if (pool.getPoolHasRamp() != null)
                checkRadioGroup(rampRadio, pool.getPoolHasRamp());
            if (pool.getPoolHasStairs() != null)
                checkRadioGroup(stairsRadio, pool.getPoolHasStairs());
            if (pool.getPoolHasBench() != null)
                checkRadioGroup(benchRadio, pool.getPoolHasBench());
            if (pool.getPoolHasEquip() != null)
                checkRadioGroup(equipRadio, pool.getPoolHasStairs());
        }

    }

    private boolean checkEmptyFields() {
        clearErrorMessages();
        int i = 0;
        if (indexRadio(sportsRadio) == -1) {
            i++;
            sportsError.setVisibility(View.VISIBLE);
        } else if (indexRadio(sportsRadio) == 0) {
            if (indexRadio(pavementRadio) == -1) {
                i++;
                pavementError.setVisibility(View.VISIBLE);
            } else if (indexRadio(pavementRadio) == 1) {
                if (TextUtils.isEmpty(pavementWidthValue.getText())) {
                    i++;
                    pavementWidthField.setError(getString(R.string.req_field_error));
                }
                if (indexRadio(accessRadio) == -1) {
                    i++;
                    accessError.setVisibility(View.VISIBLE);
                } else if (indexRadio(accessRadio) == 0) {
                    if (TextUtils.isEmpty(pavementObsValue.getText())) {
                        i++;
                        pavementObsField.setError(getString(R.string.req_field_error));
                    }
                }
            }
        }

        if (indexRadio(competitionRadio) == -1) {
            i++;
            competitionError.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(poolDepthValue.getText())) {
            i++;
            poolDepthField.setError(getString(R.string.req_field_error));
        }
        if (poolType.getCheckedRadioButtonIndex() == -1) {
            i++;
            typeError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(rampRadio) == -1) {
            i++;
            rampError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(stairsRadio) == -1) {
            i++;
            stairsError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(benchRadio) == -1) {
            i++;
            benchError.setVisibility(View.VISIBLE);
        }
        if (indexRadio(equipRadio) == -1) {
            i++;
            equipError.setVisibility(View.VISIBLE);
        }

        return i == 0;
    }

    private void clearErrorMessages() {
        sportsError.setVisibility(View.GONE);
        pavementError.setVisibility(View.GONE);
        accessError.setVisibility(View.GONE);
        competitionError.setVisibility(View.GONE);
        typeError.setVisibility(View.GONE);
        rampError.setVisibility(View.GONE);
        stairsError.setVisibility(View.GONE);
        benchError.setVisibility(View.GONE);
        equipError.setVisibility(View.GONE);

        pavementWidthField.setErrorEnabled(false);
        pavementObsField.setErrorEnabled(false);
        poolDepthField.setErrorEnabled(false);
    }

    private PoolEntryTwo updatePoolTwo(Bundle bundle) {
        Integer hasPavementedSide = null, isPavementAccess = null, isSportsPool = null, usedInCompetitions = null, pool = null, poolHasRamp = null, poolHasStairs = null,
                poolHasBench = null, poolHasEquip = null;
        Double pavementWidth = null, poolDepth = null;
        String pavementObs = null, poolPhoto2 = null, poolObs = null;

        if (indexRadio(sportsRadio) != -1)
            isSportsPool = indexRadio(sportsRadio);
        if (isSportsPool != null && isSportsPool == 0) {
            hasPavementedSide = indexRadio(pavementRadio);
            if (hasPavementedSide == 1) {
                if (!TextUtils.isEmpty(pavementWidthValue.getText()))
                    pavementWidth = Double.parseDouble(String.valueOf(pavementWidthValue.getText()));
                isPavementAccess = indexRadio(accessRadio);
            }
        }
        if (!TextUtils.isEmpty(pavementObsValue.getText()))
            pavementObs = String.valueOf(pavementObsValue.getText());
        if (indexRadio(competitionRadio) != -1)
            usedInCompetitions = indexRadio(competitionRadio);
        if (!TextUtils.isEmpty(poolDepthValue.getText()))
            poolDepth = Double.parseDouble(String.valueOf(poolDepthValue.getText()));
        if (poolType.getCheckedRadioButtonIndex() != -1)
            pool = poolType.getCheckedRadioButtonIndex();
        if (indexRadio(rampRadio) != -1)
            poolHasRamp = indexRadio(rampRadio);
        if (indexRadio(stairsRadio) != -1)
            poolHasStairs = indexRadio(stairsRadio);
        if (indexRadio(benchRadio) != -1)
            poolHasBench = indexRadio(benchRadio);
        if (indexRadio(equipRadio) != -1)
            poolHasEquip = indexRadio(equipRadio);
        if (!TextUtils.isEmpty(photoValue.getText()))
            poolPhoto2 = String.valueOf(photoValue.getText());
        if (!TextUtils.isEmpty(obsValue.getText()))
            poolObs = String.valueOf(obsValue.getText());

        return new PoolEntryTwo(bundle.getInt(POOL_ID), isSportsPool, hasPavementedSide, pavementWidth, isPavementAccess, pavementObs, usedInCompetitions, poolDepth, pool,
                poolHasRamp, poolHasStairs, poolHasBench, poolHasEquip, poolPhoto2, poolObs);
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        if (radio == sportsRadio) {
            if (index == 0) {
                pavementHeader.setVisibility(View.VISIBLE);
                pavementRadio.setVisibility(View.VISIBLE);
            } else {
                pavementRadio.clearCheck();
                pavementHeader.setVisibility(View.GONE);
                pavementRadio.setVisibility(View.GONE);
            }
        } else if (radio == pavementRadio) {
            if (index == 1) {
                pavementWidthField.setVisibility(View.VISIBLE);
                accessHeader.setVisibility(View.VISIBLE);
                accessRadio.setVisibility(View.VISIBLE);
            } else {
                accessRadio.clearCheck();
                accessHeader.setVisibility(View.GONE);
                accessRadio.setVisibility(View.GONE);
                pavementWidthValue.setText(null);
                pavementWidthField.setVisibility(View.GONE);
            }
        } else if (radio == rampRadio) {
            if (index == 1) {
                addRamp.setVisibility(View.VISIBLE);
                rampCounter.setVisibility(View.VISIBLE);
            } else {
                addRamp.setVisibility(View.GONE);
                rampCounter.setVisibility(View.GONE);
            }
        } else if (radio == stairsRadio) {
            if (index == 1) {
                addStairs.setVisibility(View.VISIBLE);
                stairsCounter.setVisibility(View.VISIBLE);
            } else {
                addStairs.setVisibility(View.GONE);
                stairsCounter.setVisibility(View.GONE);
            }
        } else if (radio == benchRadio) {
            if (index == 1) {
                addBench.setVisibility(View.VISIBLE);
                benchCounter.setVisibility(View.VISIBLE);
            } else {
                addBench.setVisibility(View.GONE);
                benchCounter.setVisibility(View.GONE);
            }
        } else if (radio == equipRadio) {
            if (index == 1) {
                addEquip.setVisibility(View.VISIBLE);
                equipCounter.setVisibility(View.VISIBLE);
            } else {
                addEquip.setVisibility(View.GONE);
                equipCounter.setVisibility(View.GONE);
            }
        }
    }

    private void clickListener(View view) {
        if (view == returnPool)
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        else if (view == savePool) {
            if (checkEmptyFields()) {
                PoolEntryTwo upTwo = updatePoolTwo(poolTwoBundle);
                modelEntry.updatePoolTwo(upTwo);
                Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().popBackStack(POOL_LIST, 0);
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        } else {
            Fragment fragment = null;
            PoolEntryTwo upTwo = updatePoolTwo(poolTwoBundle);
            modelEntry.updatePoolTwo(upTwo);
            if (view == addRamp) {
                fragment = PoolRampListFragment.newInstance();
            } else if (view == addStairs) {
                fragment = PoolStairsListFragment.newInstance();
            } else if (view == addBench) {
                fragment = PoolBenchListFragment.newInstance();
            } else if (view == addEquip) {
                fragment = PoolEquipListFragment.newInstance();
            }

            if (fragment != null) {
                fragment.setArguments(poolTwoBundle);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
            }
        }
    }

    private void counterListener() {

        modelEntry.getPoolRamps(poolTwoBundle.getInt(POOL_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0) {
                cRamp = list.size();
                setCounter(getContext(), rampCounter, list.size());
            }
            else {
                cRamp = 0;
                clearCounter(getContext(), rampCounter);
            }
        });

        modelEntry.getPoolStairs(poolTwoBundle.getInt(POOL_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0) {
                cStairs = list.size();
                setCounter(getContext(), stairsCounter, list.size());
            }
            else {
                cStairs = 0;
                clearCounter(getContext(), stairsCounter);
            }
        });

        modelEntry.getPoolBenches(poolTwoBundle.getInt(POOL_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0) {
                cBench = list.size();
                setCounter(getContext(), benchCounter, list.size());
            }
            else {
                clearCounter(getContext(), benchCounter);
                cBench = 0;
            }
        });

        modelEntry.getPoolEquips(poolTwoBundle.getInt(POOL_ID)).observe(getViewLifecycleOwner(), list -> {
            if (list != null && list.size() > 0) {
                setCounter(getContext(), equipCounter, list.size());
                cEquip = list.size();
            }
            else {
                clearCounter(getContext(), equipCounter);
                cEquip = 0;
            }
        });

    }

}