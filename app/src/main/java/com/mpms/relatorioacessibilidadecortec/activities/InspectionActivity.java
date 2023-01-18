package com.mpms.relatorioacessibilidadecortec.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.mpms.relatorioacessibilidadecortec.BuildConfig;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.commService.JsonCreation;
import com.mpms.relatorioacessibilidadecortec.fragments.ExternalAccessListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.InspectionMemorial;
import com.mpms.relatorioacessibilidadecortec.fragments.ParkingLotListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.PlaygroundListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RestListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.RoomRegisterListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.SidewalkListFragment;
import com.mpms.relatorioacessibilidadecortec.fragments.WaterFountainListFragment;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.report.TextUpdate;
import com.mpms.relatorioacessibilidadecortec.util.BoolObservable;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InspectionActivity extends AppCompatActivity implements InspectionMemorial.OnFragmentInteractionListener, TagInterface {

//    -----------------------------------

    static File filePath;
    Uri fileUri;
//    private static Context context;
    private static String[] address;
    Future<?> check;

    ExecutorService service;
    Handler handler;

    static TextUpdate upText;
    static HashMap<String, String> tData;
    static ActivityResultLauncher<String> fillCreatedDocxFile;
    static CircularProgressIndicator circBar;

    //    ------------------------------------
    static BoolObservable endReport = new BoolObservable();

    Observer repObserver = (o, arg) -> {
        BoolObservable bEnd = (BoolObservable) o;
        boolean hasFinished = bEnd.getFinished();

        if (hasFinished) {
            if (!upText.error && Build.VERSION.SDK_INT > Build.VERSION_CODES.Q)
                sendEmailIntent(fileUri, address, this);
            else if (!upText.error)
                sendEmailIntent(Uri.parse("placeholder"), address, this);
            else {
                showProgress(false);
                Toast.makeText(getApplicationContext(), getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
            }
        }
    };

//    ------------------------------------

    public static int endRegister = 0;
    Bundle inspectionBundle = new Bundle();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction;
    ViewModelEntry modelEntry;
    Fragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        endRegister = 0;
        setContentView(R.layout.activity_inspection);
        modelEntry = new ViewModelEntry(getApplication());
        inspectionBundle = getIntent().getBundleExtra(AREAS_REG_BUNDLE);
        service = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
        circBar = findViewById(R.id.progress_indicator);

        if (inspectionBundle.getInt(BLOCK_ID) == 0) {
            int areaType = 0;
            if (inspectionBundle.getBoolean(EXT_AREA_REG))
                areaType = 1;
            else if (inspectionBundle.getBoolean(SUP_AREA_REG))
                areaType = 2;
            modelEntry.getAreaFromSchool(inspectionBundle.getInt(SCHOOL_ID), areaType)
                    .observe(this, area -> inspectionBundle.putInt(BLOCK_ID, area.getBlockSpaceID()));
        }

        upText = new TextUpdate();

        endReport.addObserver(repObserver);

        this.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (registerFragmentOnScreen())
                    finish();
                else {
                    setEnabled(false);
                    InspectionActivity.super.onBackPressed();
                }
                setEnabled(true);
            }
        });

        fillCreatedDocxFile = registerForActivityResult(new CreateDocumentDaex(), result -> {

            Future<?> future = service.submit(() -> {
                try {
                    fileUri = result;
                    handler.post(() -> {
                        boolean finish = upText.docFiller(tData, result, getApplicationContext());
                        endReport.setFinished(finish);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            try {
                check = (Future<?>) future.get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            while (check != null) {
                try {
                    check = (Future<?>) future.get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        InspectionMemorial newFrag = InspectionMemorial.newInstance();
        newFrag.setArguments(inspectionBundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.chooseItemMemorial, newFrag).commit();
    }

    @Override
    protected void onRestart() {
        if (endRegister == 1) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        } else
            endRegister = 0;

        super.onRestart();
    }

    @Override
    public void onDropdownChoice(int choice) {
        if (inspectionBundle.getBoolean(EXT_AREA_REG)) {
            switch (choice) {
                case 0:
                    displayExtAccessListFragment();
                    break;
                case 1:
                    displaySidewalkListFragment();
                    break;
                case 2:
                    displayParkingLotListFragment();
                    break;
                case 3:
                    displayRoomsRegisterListFragment(choice);
                    break;
            }
        } else if (inspectionBundle.getBoolean(SUP_AREA_REG)) {
            switch (choice) {
                case 0:
                    displayFountainListFragment();
                    break;
                case 1:
                    displayParkingLotListFragment();
                    break;
                case 2:
                    displayRoomsRegisterListFragment(choice);
                    break;
                case 3:
                    displayPlaygroundListFragment();
                    break;
                case 4:
                    displayRestroomListFragment();
                    break;
            }
        } else {
            switch (choice) {
                case 0:
                case 10:
                    displayRestroomListFragment();
                    break;
                case 1:
                    displayFountainListFragment();
                    break;
                default:
                    displayRoomsRegisterListFragment(choice);
                    break;
            }
        }
    }

    public void displayExtAccessListFragment() {
        ExternalAccessListFragment extAccessList = ExternalAccessListFragment.newInstance();
        extAccessList.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, extAccessList).addToBackStack(EXTERNAL_LIST).commit();
    }

    public void displayRestroomListFragment() {
        RestListFragment restListFragment = RestListFragment.newInstance();
        restListFragment.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, restListFragment).addToBackStack(REST_LIST).commit();
    }

    public void displayFountainListFragment() {
        WaterFountainListFragment waterListFrag = WaterFountainListFragment.newInstance();
        waterListFrag.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, waterListFrag).addToBackStack(WATER_LIST).commit();

    }

    public void displaySidewalkListFragment() {
        SidewalkListFragment sidewalkList = SidewalkListFragment.newInstance();
        sidewalkList.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, sidewalkList).addToBackStack(SIDEWALK_LIST).commit();
    }

    public void displayRoomsRegisterListFragment(int chosenItem) {
        RoomRegisterListFragment roomList = RoomRegisterListFragment.newInstance(chosenItem);
        roomList.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, roomList).addToBackStack(ROOM_LIST).commit();
    }

    public void displayParkingLotListFragment() {
        ParkingLotListFragment parkingList = ParkingLotListFragment.newInstance();
        parkingList.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, parkingList).addToBackStack(PARKING_LIST).commit();
    }

    public void displayPlaygroundListFragment() {
        PlaygroundListFragment playListFragment = PlaygroundListFragment.newInstance();
        playListFragment.setArguments(inspectionBundle);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.show_fragment_selected, playListFragment).addToBackStack(PLAYGROUND_LIST).commit();
    }

    public boolean registerFragmentOnScreen() {
        int i = 1;
        frag = getSupportFragmentManager().findFragmentById(R.id.show_fragment_selected);
        if (frag == null)
            i = 0;
        else {
            if (frag instanceof ExternalAccessListFragment || frag instanceof ParkingLotListFragment || frag instanceof PlaygroundListFragment
                    || frag instanceof RestListFragment || frag instanceof RoomRegisterListFragment || frag instanceof SidewalkListFragment
                    || frag instanceof WaterFountainListFragment)
                i = 0;
        }
        return i == 0;
    }

    public static void callFunction(HashMap<String, String> tData, JsonCreation jCreate, Context context) {
        showProgress(true);
        address = new String[]{jCreate.getSchool().getEmailAddress()};
        InspectionActivity.tData = tData;
        List<String> blockList = jCreate.ambListCreator();
        upText.setJsonCreation(jCreate, blockList);
        upText.newFileName();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            try {
                boolean finish = upText.docFiller(tData, Uri.parse("placeholder"), context.getApplicationContext());
                endReport.setFinished(finish);
            } catch (OpenXML4JRuntimeException e) {
                InspectionActivity.endRegister = 0;
                e.printStackTrace();
            }
        } else {
            filePath = upText.path;
            fillCreatedDocxFile.launch(upText.fName);
        }
    }

    public static void sendEmailIntent(Uri uri, String[] address, Context context) {
        Intent sender = new Intent(Intent.ACTION_SEND);
        sender.putExtra(Intent.EXTRA_SUBJECT, "Relatório DOCX");
        sender.putExtra(Intent.EXTRA_EMAIL, address);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            Uri fileUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", new File(upText.fileName));
            sender.putExtra(Intent.EXTRA_STREAM, fileUri);
        } else
            sender.putExtra(Intent.EXTRA_STREAM, uri);
        sender.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        sender.setType("message/rfc822");
        InspectionActivity.endRegister = 1;
        showProgress(false);
        context.startActivity(Intent.createChooser(sender, "Escolha o App desejado"));
    }

    public static class CreateDocumentDaex extends ActivityResultContracts.CreateDocument {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @NonNull
        @NotNull
        @Override
        public Intent createIntent(@NonNull @NotNull Context context, @NonNull @NotNull String input) {
            return super.createIntent(context, input)
                    .setType("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
                    .addCategory(Intent.CATEGORY_OPENABLE)
                    .putExtra(DocumentsContract.EXTRA_INITIAL_URI, filePath);
        }
    }

    public static void showProgress(boolean show) {
        circBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }
}




