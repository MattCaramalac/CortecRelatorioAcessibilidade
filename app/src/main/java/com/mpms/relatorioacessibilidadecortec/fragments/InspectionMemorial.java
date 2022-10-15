package com.mpms.relatorioacessibilidadecortec.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.mpms.relatorioacessibilidadecortec.R;
import com.mpms.relatorioacessibilidadecortec.activities.MainActivity;
import com.mpms.relatorioacessibilidadecortec.commService.JsonCreation;
import com.mpms.relatorioacessibilidadecortec.commService.SentData;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;
import com.mpms.relatorioacessibilidadecortec.model.ViewModelEntry;
import com.mpms.relatorioacessibilidadecortec.report.TextUpdate;
import com.mpms.relatorioacessibilidadecortec.util.HeaderNames;
import com.mpms.relatorioacessibilidadecortec.util.IdListObservable;
import com.mpms.relatorioacessibilidadecortec.util.TagInterface;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;



public class InspectionMemorial extends Fragment implements TagInterface {

    OnFragmentInteractionListener listener;

    TextInputLayout dropdownMenuLocations;
    AutoCompleteTextView listItemsMemorial;
    ArrayAdapter<String> adapterLocations;

    SchoolEntry school;

    IdListObservable bID = new IdListObservable();
    IdListObservable roomID = new IdListObservable();
    IdListObservable extID = new IdListObservable();
    IdListObservable parkID = new IdListObservable();
    IdListObservable sideID = new IdListObservable();
    IdListObservable doorID = new IdListObservable();
    IdListObservable flRoomID = new IdListObservable();
    IdListObservable flSideID = new IdListObservable();
    IdListObservable flExtID = new IdListObservable();
    IdListObservable flParkID = new IdListObservable();

    List<RoomEntry> roomList = new ArrayList<>();
    List<ExternalAccess> extList = new ArrayList<>();
    List<ParkingLotEntry> parkList = new ArrayList<>();
    List<PlaygroundEntry> playList = new ArrayList<>();
    List<RestroomEntry> restList = new ArrayList<>();
    List<SidewalkEntry> sideList = new ArrayList<>();
    List<WaterFountainEntry> fountList = new ArrayList<>();
    //    Ramp & Stairs Lists
    List<RampStairsEntry> roomStRaList = new ArrayList<>();
    List<RampStairsEntry> sideStRaList = new ArrayList<>();
    List<RampStairsEntry> extStRaList = new ArrayList<>();
    List<RampStairsEntry> parkStRaList = new ArrayList<>();
    //Door-related entities
    List<BlackboardEntry> boardList = new ArrayList<>();
    List<CounterEntry> counterList = new ArrayList<>();
    List<DoorEntry> doorList = new ArrayList<>();
    List<FreeSpaceEntry> freeList = new ArrayList<>();
    List<SwitchEntry> switchList = new ArrayList<>();
    List<TableEntry> tableList = new ArrayList<>();
    List<WindowEntry> windowList = new ArrayList<>();
    //    Gate & Door Entities
    List<DoorLockEntry> doorLockList = new ArrayList<>();
    List<DoorLockEntry> gateLockList = new ArrayList<>();
    //    Gate Entities
    List<GateObsEntry> gateList = new ArrayList<>();
    //    Gate & Sidewalk Entities
    List<PayPhoneEntry> extPhoneList = new ArrayList<>();
    List<PayPhoneEntry> sidePhoneList = new ArrayList<>();
    //    Sidewalk Entities
    List<SidewalkSlopeEntry> slopeList = new ArrayList<>();
    //    Stair/Ramp Flights
    List<RampStairsFlightEntry> roomFlightList = new ArrayList<>();
    List<RampStairsFlightEntry> sideFlightList = new ArrayList<>();
    List<RampStairsFlightEntry> extFlightList = new ArrayList<>();
    List<RampStairsFlightEntry> parkFlightList = new ArrayList<>();
    //    RampStairs Components
    List<RampStairsRailingEntry> roomRailList = new ArrayList<>();
    List<RampStairsRailingEntry> sideRailList = new ArrayList<>();
    List<RampStairsRailingEntry> extRailList = new ArrayList<>();
    List<RampStairsRailingEntry> parkRailList = new ArrayList<>();
    List<RampStairsHandrailEntry> roomHandList = new ArrayList<>();
    List<RampStairsHandrailEntry> sideHandList = new ArrayList<>();
    List<RampStairsHandrailEntry> extHandList = new ArrayList<>();
    List<RampStairsHandrailEntry> parkHandList = new ArrayList<>();

    Observer blockIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllRoomsInSchool(idList).observe(getViewLifecycleOwner(), rList -> {
            roomList = rList;
            List<Integer> rIdList = new ArrayList<>();
            for (RoomEntry r : rList)
                rIdList.add(r.getRoomID());
            roomID.setIdList(rIdList);
        });
        ViewModelEntry.getAllExtAccess(idList).observe(getViewLifecycleOwner(), exList -> {
            extList = exList;
            List<Integer> exIdList = new ArrayList<>();
            for (ExternalAccess e : exList)
                exIdList.add(e.getExternalAccessID());
            extID.setIdList(exIdList);
        });
        ViewModelEntry.getAllParkingLots(idList).observe(getViewLifecycleOwner(), pList -> {
            parkList = pList;
            List<Integer> pIdList = new ArrayList<>();
            for (ParkingLotEntry p : pList)
                pIdList.add(p.getParkingID());
            parkID.setIdList(pIdList);
        });
        ViewModelEntry.getAllPlaygrounds(idList).observe(getViewLifecycleOwner(), pgList -> playList = pgList);
        ViewModelEntry.getAllRestEntries(idList).observe(getViewLifecycleOwner(), reList -> restList = reList);
        ViewModelEntry.getAllSidewalks(idList).observe(getViewLifecycleOwner(), sList -> {
            sideList = sList;
            List<Integer> sIDList = new ArrayList<>();
            for (SidewalkEntry s : sList)
                sIDList.add(s.getSidewalkID());
            sideID.setIdList(sIDList);
        });
        ViewModelEntry.getAllWaterFountains(idList).observe(getViewLifecycleOwner(), wList -> fountList = wList);
    };

    Observer roomIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllBlackboards(idList).observe(getViewLifecycleOwner(), bList -> boardList = bList);
        ViewModelEntry.getAllCounters(idList).observe(getViewLifecycleOwner(), cList -> counterList = cList);
        ViewModelEntry.getAllDoors(idList).observe(getViewLifecycleOwner(), dList -> {
            doorList = dList;
            List<Integer> dIdList = new ArrayList<>();
            for (DoorEntry d : dList)
                dIdList.add(d.getDoorID());
            doorID.setIdList(dIdList);
        });
        ViewModelEntry.getAllFreeSpaces(idList).observe(getViewLifecycleOwner(), fList -> freeList = fList);
        ViewModelEntry.getAllRampStRoom(idList).observe(getViewLifecycleOwner(), rsList -> {
            roomStRaList = rsList;
            List<Integer> stRaList = new ArrayList<>();
            for (RampStairsEntry st : rsList)
                stRaList.add(st.getRampStairsID());
            flRoomID.setIdList(stRaList);
        });
        ViewModelEntry.getAllSwitches(idList).observe(getViewLifecycleOwner(), sList -> switchList = sList);
        ViewModelEntry.getAllTables(idList).observe(getViewLifecycleOwner(), tList -> tableList = tList);
        ViewModelEntry.getAllWindows(idList).observe(getViewLifecycleOwner(), wList -> windowList = wList);
    };

    Observer extAccessIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllLocksFromGates(idList).observe(getViewLifecycleOwner(), dLoList -> gateLockList = dLoList);
        ViewModelEntry.getAllGateObs(idList).observe(getViewLifecycleOwner(), gList -> gateList = gList);
        ViewModelEntry.getAllPhonesExtAccess(idList).observe(getViewLifecycleOwner(), phList -> extPhoneList = phList);
        ViewModelEntry.getAllRampStExt(idList).observe(getViewLifecycleOwner(), rampList -> {
            extStRaList = rampList;
            List<Integer> stRaList = new ArrayList<>();
            for (RampStairsEntry st : rampList)
                stRaList.add(st.getRampStairsID());
            flExtID.setIdList(stRaList);
        });
    };

    Observer parkIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllRampStPark(idList).observe(getViewLifecycleOwner(), rampList -> {
            parkStRaList = rampList;
            List<Integer> stRaList = new ArrayList<>();
            for (RampStairsEntry st : rampList)
                stRaList.add(st.getRampStairsID());
            flParkID.setIdList(stRaList);
        });
    };

    Observer sideIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllPhonesSidewalk(idList).observe(getViewLifecycleOwner(), phList -> sidePhoneList = phList);
        ViewModelEntry.getAllRampStExt(idList).observe(getViewLifecycleOwner(), rampList -> {
            sideStRaList = rampList;
            List<Integer> stRaList = new ArrayList<>();
            for (RampStairsEntry st : rampList)
                stRaList.add(st.getRampStairsID());
            flSideID.setIdList(stRaList);
        });
        ViewModelEntry.getAllSideSlopes(idList).observe(getViewLifecycleOwner(), slList -> {
            slopeList = slList;

        });
    };

    Observer doorIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllLocksFromDoor(idList).observe(getViewLifecycleOwner(), dLoList -> doorLockList = dLoList);
    };

    Observer flRoomIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllFlights(idList).observe(getViewLifecycleOwner(), rFlightList -> {
            roomFlightList = rFlightList;
            List<Integer> flIDList = new ArrayList<>();
            for (RampStairsFlightEntry fl : rFlightList)
                flIDList.add(fl.getFlightID());

            ViewModelEntry.getAllHandrails(flIDList).observe(getViewLifecycleOwner(), flHand -> roomHandList = flHand);
            ViewModelEntry.getAllRailings(flIDList).observe(getViewLifecycleOwner(), flRail -> roomRailList = flRail);
        });
    };

    Observer flSideIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllFlights(idList).observe(getViewLifecycleOwner(), sFlightList -> {
            sideFlightList = sFlightList;
            List<Integer> flIDList = new ArrayList<>();
            for (RampStairsFlightEntry fl : sFlightList)
                flIDList.add(fl.getFlightID());

            ViewModelEntry.getAllHandrails(flIDList).observe(getViewLifecycleOwner(), flHand -> sideHandList = flHand);
            ViewModelEntry.getAllRailings(flIDList).observe(getViewLifecycleOwner(), flRail -> sideRailList = flRail);
        });
    };

    Observer flExtIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllFlights(idList).observe(getViewLifecycleOwner(), eFlightList -> {
            extFlightList = eFlightList;
            List<Integer> flIDList = new ArrayList<>();
            for (RampStairsFlightEntry fl : eFlightList)
                flIDList.add(fl.getFlightID());

            ViewModelEntry.getAllHandrails(flIDList).observe(getViewLifecycleOwner(), flHand -> extHandList = flHand);
            ViewModelEntry.getAllRailings(flIDList).observe(getViewLifecycleOwner(), flRail -> extRailList = flRail);
        });
    };

    Observer flParkIdList = (o, arg) -> {
        IdListObservable idBlockList = (IdListObservable) o;
        List<Integer> idList = idBlockList.getIdList();

        ViewModelEntry.getAllFlights(idList).observe(getViewLifecycleOwner(), pFlightList -> {
            parkFlightList = pFlightList;
            List<Integer> flIDList = new ArrayList<>();
            for (RampStairsFlightEntry fl : pFlightList)
                flIDList.add(fl.getFlightID());

            ViewModelEntry.getAllHandrails(flIDList).observe(getViewLifecycleOwner(), flHand -> parkHandList = flHand);
            ViewModelEntry.getAllRailings(flIDList).observe(getViewLifecycleOwner(), flRail -> parkRailList = flRail);
        });
    };

    MaterialButton saveAndClose;

    ViewModelEntry modelEntry;

    Bundle fragInspection;

    public InspectionMemorial() {
        // Required empty public constructor
    }

    public static InspectionMemorial newInstance() {
        return new InspectionMemorial();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null)
            fragInspection = new Bundle(this.getArguments());
        else
            fragInspection = new Bundle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_inspection_memorial, container, false);
        dropdownMenuLocations = rootView.findViewById(R.id.menu_options_memorial);
        listItemsMemorial = rootView.findViewById(R.id.list_item_memorial);
        modelEntry = new ViewModelEntry(requireActivity().getApplication());
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        saveAndClose = view.findViewById(R.id.saveAndQuit);

        modelEntry.getEntry(fragInspection.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), entry -> school = entry);

        modelEntry.getAllBlockIds(fragInspection.getInt(SCHOOL_ID)).observe(getViewLifecycleOwner(), bList -> bID.setIdList(bList));

        bID.addObserver(blockIdList);
        roomID.addObserver(roomIdList);
        extID.addObserver(extAccessIdList);
        parkID.addObserver(parkIdList);
        sideID.addObserver(sideIdList);
        doorID.addObserver(doorIdList);
        flRoomID.addObserver(flRoomIdList);
        flSideID.addObserver(flSideIdList);
        flExtID.addObserver(flExtIdList);
        flParkID.addObserver(flParkIdList);

        saveAndClose.setOnClickListener(v -> {

//            Retrofit retro = new Retrofit.Builder().baseUrl("https://httpbin.org/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(getUnsafeOkHttpClient().build()).build();
//
//            SendJson sendJson = retro.create(SendJson.class);

            JsonCreation jCreate = new JsonCreation(school, roomList, extList, parkList, playList, restList, sideList, fountList, roomStRaList, sideStRaList,
                    extStRaList, parkStRaList, boardList, counterList, doorList, freeList, switchList, tableList, windowList, doorLockList,
                    gateLockList, gateList, extPhoneList, sidePhoneList, slopeList, roomFlightList, sideFlightList, extFlightList, parkFlightList,
                    roomRailList, sideRailList, extRailList, parkRailList, roomHandList, sideHandList, extHandList, parkHandList);

            HashMap<String, String> tData = jCreate.createJson();

            try {
                TextUpdate.reportGenerator(tData, getContext());
            } catch (OpenXML4JException | IOException e) {
                e.printStackTrace();
            }

//            Call<SentData> request = sendJson.PostData(sentData);

//            Recebe resposta positiva, necessário testar novamente depois
//            request.enqueue(new Callback<SentData>() {
//                @Override
//                public void onResponse(Call<SentData> call, Response<SentData> response) {
////                    Se funcionar
////                    Toast.makeText(getActivity(), "Dados enviados com sucesso", Toast.LENGTH_SHORT).show();
//                    Log.i("Comunicação - Sucesso ", "Sucesso na Comunicação");
//                }
//
//                @Override
//                public void onFailure(Call<SentData> call, Throwable t) {
////                    Se falhar
////                    Toast.makeText(getActivity(), "Houve um Problema. Por favor, tente novamente", Toast.LENGTH_SHORT).show();
//                    Log.i("Comunicação - Falha ", t.toString());
//
//                }
//            });

            Intent sender =  new Intent(Intent.ACTION_SEND);
            sender.putExtra(Intent.EXTRA_EMAIL, "mattcaramalac@gmail.com");
            sender.putExtra(Intent.EXTRA_SUBJECT, "Teste Geração DOCX");
            sender.putExtra(Intent.EXTRA_STREAM, TextUpdate.fileName);
            sender.setType("message/rfc822");
            startActivity(Intent.createChooser(sender, "Escolha o App desejado"));

            Intent intent = new Intent(requireActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString());
        }

    }

    @Override
    public void onResume() {
        if (fragInspection.getBoolean(EXT_AREA_REG))
            adapterLocations = new ArrayAdapter<>(getContext(), R.layout.dropdown_list_memorial, HeaderNames.externalAreaOptions);
        else if (fragInspection.getBoolean(SUP_AREA_REG))
            adapterLocations = new ArrayAdapter<>(getContext(), R.layout.dropdown_list_memorial, HeaderNames.supportAreaOptions);
        else
            adapterLocations = new ArrayAdapter<>(getContext(), R.layout.dropdown_list_memorial, HeaderNames.blockOptions);
        listItemsMemorial.setAdapter(adapterLocations);

        listItemsMemorial.setOnItemClickListener((parent, view, position, id) -> listener.onDropdownChoice(position));

        super.onResume();
    }

    public interface OnFragmentInteractionListener {
        void onDropdownChoice(int choice);
    }

//    public static OkHttpClient.Builder getUnsafeOkHttpClient() {
//
//        try {
//            // Create a trust manager that does not validate certificate chains
//            final TrustManager[] trustAllCerts = new TrustManager[]{
//                    new X509TrustManager() {
//                        @Override
//                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
//                        }
//
//                        @Override
//                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
//                        }
//
//                        @Override
//                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                            return new java.security.cert.X509Certificate[]{};
//                        }
//                    }
//            };
//
//            // Install the all-trusting trust manager
//            final SSLContext sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//
//            // Create an ssl socket factory with our all-trusting manager
//            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
//            builder.hostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String hostname, SSLSession session) {
//                    return true;
//                }
//            });
//            return builder;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}