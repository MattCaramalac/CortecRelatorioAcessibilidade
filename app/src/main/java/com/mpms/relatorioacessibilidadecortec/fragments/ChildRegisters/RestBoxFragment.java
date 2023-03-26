package com.mpms.relatorioacessibilidadecortec.fragments.ChildRegisters;

import android.os.Bundle;
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
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxFirstUpdate;
import com.mpms.relatorioacessibilidadecortec.data.parcels.BoxBarParcel;
import com.mpms.relatorioacessibilidadecortec.data.parcels.BoxUpViewParcel;
import com.mpms.relatorioacessibilidadecortec.data.parcels.CommonBoxParcel;
import com.mpms.relatorioacessibilidadecortec.fragments.ChildFragments.RestCommonBoxFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RestUpViewFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.util.RadioGroupInterface;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

public class RestBoxFragment extends Fragment implements TagInterface, RadioGroupInterface {

    RadioGroup boxTypeRadio;
    TextView boxTypeError;
    MaterialButton saveContinue, cancelRegister;

    ViewModelEntry modelEntry;
    Bundle boxBundle;

    static boolean isRecent = false;


    public RestBoxFragment() {
        // Required empty public constructor
    }

    public static RestBoxFragment newInstance() {
        return new RestBoxFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            boxBundle = new Bundle(this.getArguments());
            boxBundle.putBoolean(FROM_BOX, true);
        } else
            boxBundle = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rest_box, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instantiateBoxViews(view);

        if (boxBundle.getInt(BOX_ID) > 0)
            modelEntry.getOneBoxEntry(boxBundle.getInt(BOX_ID)).observe(getViewLifecycleOwner(), this::loadBoxData);

        getChildFragmentManager().setFragmentResultListener(CHILD_DATA_LISTENER, this, (key, bundle) -> {
            if (bundle.getBoolean(CHILD_DATA_COMPLETE)) {
                isRecent = true;
                RestBoxEntry newBoxEntry = newBox(bundle);
                if (bundle.getInt(BOX_ID) == 0) {
                    ViewModelEntry.insertRestBox(newBoxEntry);
                    modelEntry.getLastBoxEntry().observe(this, box -> {
                        if (isRecent) {
                            boxBundle.putInt(BOX_ID, box.getBoxID());
                            callNextFrag(indexRadio(boxTypeRadio));
                        }
                    });
                } else if (bundle.getInt(BOX_ID) > 0) {
                    RestBoxFirstUpdate update = firstUpBox(bundle);
                    ViewModelEntry.updateBoxFirstData(update);
                    callNextFrag(indexRadio(boxTypeRadio));
                }
            } else
                Toast.makeText(getContext(), getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();

        });
    }

    private void callNextFrag(int type) {
        if (type == 1) {
            Fragment fragment = DoorFragment.newInstance();
            fragment.setArguments(boxBundle);
            requireActivity().getSupportFragmentManager().beginTransaction().
                    replace(R.id.show_fragment_selected, fragment).addToBackStack(null).commit();
        } else {
            Toast.makeText(getContext(), getString(R.string.register_created_message), Toast.LENGTH_SHORT).show();
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
        }


    }

    private void instantiateBoxViews(View view) {
//        RadioGroup
        boxTypeRadio = view.findViewById(R.id.box_type_radio);
//        TextView
        boxTypeError = view.findViewById(R.id.box_type_error);
//        MaterialButton
        saveContinue = view.findViewById(R.id.save_continue_box);
        cancelRegister = view.findViewById(R.id.cancel_box);
//        Listeners
        boxTypeRadio.setOnCheckedChangeListener(this::radioListener);
        saveContinue.setOnClickListener(this::buttonClick);
        cancelRegister.setOnClickListener(this::buttonClick);
//        ViewModel
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
    }

    private RestBoxEntry newBox(Bundle bundle) {
        int boxType;
        Integer comHasBar = null, comLeftBarType = null, comRightBarType = null, accBoxDrain = null;
        Double comDoorWidth = null, comBoxDiam = null, comDoorDist = null, comBoxWidth = null, leftShapeA = null, leftShapeB = null, leftShapeC = null, leftShapeD = null,
                leftShapeDiam = null, leftShapeDist = null, rightShapeA = null, rightShapeB = null, rightShapeC = null, rightShapeD = null, rightShapeDiam = null,
                rightShapeDist = null, upLength = null, upWidth = null, upA = null, upB = null, upC = null, upD = null;
        String comBoxObs = null, leftBarObs = null, rightBarObs = null, upViewObs = null, accBoxDrainObs = null, upPhoto = null;

        boxType = indexRadio(boxTypeRadio);
        if (boxType == 0) {
            CommonBoxParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
            comDoorWidth = parcel.getDoorWidth();
            comBoxDiam = parcel.getBoxFreeDiam();
            comHasBar = parcel.getHasBars();
            if (comHasBar == 1) {
                comDoorDist = parcel.getDoorDist();
                comBoxWidth = parcel.getBoxWidth();
                comLeftBarType = parcel.getLeftHasBarType();
                if (parcel.getLeftParcel() != null) {
                    BoxBarParcel barParcel = parcel.getLeftParcel();
                    if (comLeftBarType == 0 || comLeftBarType == 1) {
                        leftShapeA = barParcel.getComBoxLeftShapeBarA();
                        leftShapeB = barParcel.getComBoxLeftShapeBarB();
                        leftShapeC = barParcel.getComBoxLeftShapeBarC();
                        leftShapeD = barParcel.getComBoxLeftShapeBarD();
                        leftShapeDiam = barParcel.getComBoxLeftShapeBarDiam();
                        leftShapeDist = barParcel.getComBoxLeftShapeBarDist();
                        leftBarObs = barParcel.getComBoxLeftObs();
                    } else if (comLeftBarType == 2)
                        leftBarObs = barParcel.getComBoxLeftObs();
                }
                comRightBarType = parcel.getRightHasBarType();
                if (parcel.getRightParcel() != null) {
                    BoxBarParcel barParcel = parcel.getRightParcel();
                    if (comRightBarType == 0 || comRightBarType == 1) {
                        rightShapeA = barParcel.getComBoxRightShapeBarA();
                        rightShapeB = barParcel.getComBoxRightShapeBarB();
                        rightShapeC = barParcel.getComBoxRightShapeBarC();
                        rightShapeD = barParcel.getComBoxRightShapeBarD();
                        rightShapeDiam = barParcel.getComBoxRightShapeBarDiam();
                        rightShapeDist = barParcel.getComBoxRightShapeBarDist();
                        rightBarObs = barParcel.getComBoxRightObs();
                    } else if (comRightBarType == 2)
                        rightBarObs = barParcel.getComBoxRightObs();
                }
            }
            comBoxObs = parcel.getBoxObs();
            upPhoto = parcel.getBoxPhoto();
        } else {
            BoxUpViewParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
            upLength = parcel.getUpViewLength();
            upWidth = parcel.getUpViewWidth();
            upA = parcel.getUpViewMeasureA();
            if (parcel.getUpViewMeasureB() != null)
                upB = parcel.getUpViewMeasureB();
            upC = parcel.getUpViewMeasureC();
            if (parcel.getUpViewMeasureD() != null)
                upD = parcel.getUpViewMeasureD();
            if (parcel.getUpViewObs() != null)
                upViewObs = parcel.getUpViewObs();
            accBoxDrain = parcel.getRestDrain();
            if (parcel.getRestDrainObs() != null)
                accBoxDrainObs = parcel.getRestDrainObs();
            if (parcel.getUpPhoto() != null)
                upPhoto = parcel.getUpPhoto();
        }
        return new RestBoxEntry(boxBundle.getInt(REST_ID), boxType, comDoorWidth, comBoxDiam, comHasBar, comDoorDist, comBoxWidth, comLeftBarType, leftShapeA,
                leftShapeB, leftShapeC, leftShapeD, leftShapeDiam, leftShapeDist, leftBarObs, comRightBarType, rightShapeA, rightShapeB, rightShapeC, rightShapeD,
                rightShapeDiam, rightShapeDist, rightBarObs, comBoxObs, upLength, upWidth, upA, upB, upC, upD, upViewObs, accBoxDrain, accBoxDrainObs,
                null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null,
                null, null, null, null, null, upPhoto, null, null, null,
                null);
    }

    private RestBoxFirstUpdate firstUpBox(Bundle bundle) {
        int boxType;
        Integer comHasBar = null, comLeftBarType = null, comRightBarType = null, accBoxDrain = null;
        Double comDoorWidth = null, comBoxDiam = null, comDoorDist = null, comBoxWidth = null, leftShapeA = null, leftShapeB = null, leftShapeC = null, leftShapeD = null,
                leftShapeDiam = null, leftShapeDist = null, rightShapeA = null, rightShapeB = null, rightShapeC = null, rightShapeD = null, rightShapeDiam = null,
                rightShapeDist = null, upLength = null, upWidth = null, upA = null, upB = null, upC = null, upD = null;
        String comBoxObs = null, leftBarObs = null, rightBarObs = null, upViewObs = null, accBoxDrainObs = null, photo = null;

        boxType = indexRadio(boxTypeRadio);
        if (boxType == 0) {
            CommonBoxParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
            comDoorWidth = parcel.getDoorWidth();
            comBoxDiam = parcel.getBoxFreeDiam();
            comHasBar = parcel.getHasBars();
            if (comHasBar == 1) {
                comDoorDist = parcel.getDoorDist();
                comBoxWidth = parcel.getBoxWidth();
                comLeftBarType = parcel.getLeftHasBarType();
                if (parcel.getLeftParcel() != null) {
                    BoxBarParcel barParcel = parcel.getLeftParcel();
                    if (comLeftBarType == 0 || comLeftBarType == 1) {
                        leftShapeA = barParcel.getComBoxLeftShapeBarA();
                        leftShapeB = barParcel.getComBoxLeftShapeBarB();
                        leftShapeC = barParcel.getComBoxLeftShapeBarC();
                        leftShapeD = barParcel.getComBoxLeftShapeBarD();
                        leftShapeDiam = barParcel.getComBoxLeftShapeBarDiam();
                        leftShapeDist = barParcel.getComBoxLeftShapeBarDist();
                        leftBarObs = barParcel.getComBoxLeftObs();
                    } else if (comLeftBarType == 2)
                        leftBarObs = barParcel.getComBoxLeftObs();
                }
                comRightBarType = parcel.getRightHasBarType();
                if (parcel.getRightParcel() != null) {
                    BoxBarParcel barParcel = parcel.getRightParcel();
                    if (comRightBarType == 0 || comRightBarType == 1) {
                        rightShapeA = barParcel.getComBoxRightShapeBarA();
                        rightShapeB = barParcel.getComBoxRightShapeBarB();
                        rightShapeC = barParcel.getComBoxRightShapeBarC();
                        rightShapeD = barParcel.getComBoxRightShapeBarD();
                        rightShapeDiam = barParcel.getComBoxRightShapeBarDiam();
                        rightShapeDist = barParcel.getComBoxRightShapeBarDist();
                        rightBarObs = barParcel.getComBoxRightObs();
                    } else if (comRightBarType == 2)
                        rightBarObs = barParcel.getComBoxRightObs();
                }
            }
            comBoxObs = parcel.getBoxObs();
            photo = parcel.getBoxPhoto();
        } else {
            BoxUpViewParcel parcel = Parcels.unwrap(bundle.getParcelable(CHILD_PARCEL));
            upLength = parcel.getUpViewLength();
            upWidth = parcel.getUpViewWidth();
            upA = parcel.getUpViewMeasureA();
            if (parcel.getUpViewMeasureB() != null)
                upB = parcel.getUpViewMeasureB();
            upC = parcel.getUpViewMeasureC();
            if (parcel.getUpViewMeasureD() != null)
                upD = parcel.getUpViewMeasureD();
            if (parcel.getUpViewObs() != null)
                upViewObs = parcel.getUpViewObs();
            accBoxDrain = parcel.getRestDrain();
            if (parcel.getRestDrainObs() != null)
                accBoxDrainObs = parcel.getRestDrainObs();
            if (parcel.getUpPhoto() != null)
                photo = parcel.getUpPhoto();
        }

        return new RestBoxFirstUpdate(boxBundle.getInt(BOX_ID), boxType, comDoorWidth, comBoxDiam, comHasBar, comDoorDist, comBoxWidth, comLeftBarType, leftShapeA,
                leftShapeB, leftShapeC, leftShapeD, leftShapeDiam, leftShapeDist, leftBarObs, comRightBarType, rightShapeA, rightShapeB, rightShapeC, rightShapeD,
                rightShapeDiam, rightShapeDist, rightBarObs, comBoxObs, upLength, upWidth, upA, upB, upC, upD, upViewObs, accBoxDrain, accBoxDrainObs, photo);
    }


    private void loadBoxData(RestBoxEntry entry) {
        boxTypeRadio.check(boxTypeRadio.getChildAt(entry.getTypeBox()).getId());
    }

    private boolean checkEmptyBoxField() {
        boxTypeError.setVisibility(View.GONE);
        if (indexRadio(boxTypeRadio) == -1) {
            boxTypeError.setVisibility(View.VISIBLE);
            return false;
        }
        return true;
    }

    private void buttonClick(View view) {
        if (checkEmptyBoxField() && view == saveContinue)
            getChildFragmentManager().setFragmentResult(GATHER_CHILD_DATA, boxBundle);
        else if (view == cancelRegister)
            requireActivity().getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void radioListener(RadioGroup radio, int id) {
        int index = indexRadio(radio);

        Fragment fragment;
        if (index == 0)
            fragment = new RestCommonBoxFragment();
        else
            fragment = new RestUpViewFragment();
        fragment.setArguments(boxBundle);
        getChildFragmentManager().beginTransaction().replace(R.id.box_type_fragment, fragment).addToBackStack(null).commit();

    }
}