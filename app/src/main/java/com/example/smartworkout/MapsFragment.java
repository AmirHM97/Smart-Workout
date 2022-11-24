package com.example.smartworkout;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsFragment extends Fragment implements OnMapReadyCallback {


    GoogleMap googleMap;
    boolean permission_Granted = false;
    private LatLng latLng3;


    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);


        checkPermission();
        if (permission_Granted == true) {
            if (checkGoogleService()) {
           //     Toast.makeText(getActivity(), "Google Service is Available", Toast.LENGTH_SHORT).show();

                SupportMapFragment supportMapFragment = SupportMapFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.containerJan, supportMapFragment).commit();
                supportMapFragment.getMapAsync(this);

            } else {
                Toast.makeText(getActivity(), "Google Service is Not Available", Toast.LENGTH_SHORT).show();
            }
        }
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());  // context zadim inja!!!!!!!!!!
        try {
            List<Address> addressList = geocoder.getFromLocationName("Guelph Gryphons Athletics Centre", 1);

            if (addressList.size() > 0) {

                latLng3 = new LatLng(addressList.get(0).getLatitude(),addressList.get(0).getLongitude());


            }


        } catch (IOException e) {
            e.printStackTrace();
        }




        return view;
    }

    private boolean checkGoogleService() {

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int resss = googleApiAvailability.isGooglePlayServicesAvailable(getActivity());
        if (resss == ConnectionResult.SUCCESS) {
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(resss)) {
            Dialog dialog = googleApiAvailability.getErrorDialog(getActivity(), resss, 201, new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
             //       Toast.makeText(getActivity(), "User", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }


        return false;
    }

    private void checkPermission() {

        Dexter.withContext(getActivity()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                permission_Granted = true;
                Toast.makeText(getActivity(), "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getActivity().getPackageName(), "");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                permissionToken.continuePermissionRequest();

            }
        }).check();

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("Guelph Athletic Center");

        if (latLng3 == null) {
            Toast.makeText(getContext(), "No Location Found With That Name", Toast.LENGTH_SHORT).show();
        } else {


            markerOptions.position(latLng3);
            googleMap.addMarker(markerOptions);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng3, 15);
            googleMap.animateCamera(cameraUpdate);
        }
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setCompassEnabled(true);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);

            return;
        }
        googleMap.setMyLocationEnabled(true);


    }





}