package com.example.foodheroes.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.manager.SupportRequestManagerFragment;
import com.example.foodheroes.Models.Mitra;
import com.example.foodheroes.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.ErrorDialogFragment;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

public class MapsRelawanActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public static final  int PERMISSION_REQUEST = 99;
    private boolean LocationPermissionGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private static final float DEFAULT_ZOOM = 15f;
    private GoogleMap gMap;
    DatabaseReference MitraReff;
    private Location locationLast;
    private Marker marker;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;

    String alamatPenerima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_relawan);

        Intent intent = getIntent();
        alamatPenerima = intent.getStringExtra("AlamatPenerima");

        Toast.makeText(this, alamatPenerima, Toast.LENGTH_SHORT).show();
        Log.d("asd123", alamatPenerima);
        getLocationPermission();

    }

    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public boolean isServiceOK(){
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapsRelawanActivity.this);
        if(available == ConnectionResult.SUCCESS){
            Log.d("GoogeAPI","Service Mantul");
        } else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d("GoogleAPI","Error tapi santuy");
        } else{
            Toast.makeText(MapsRelawanActivity.this, "Gaisok iki", Toast.LENGTH_SHORT).show();

        }
        return false;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        Toast.makeText(MapsRelawanActivity.this, "Map is Ready", Toast.LENGTH_SHORT).show();

        if(LocationPermissionGranted){
            getDeviceLocated();
            buildGoolgeApiClient();
            gMap.setMyLocationEnabled(true);
            gMap.getUiSettings().setMyLocationButtonEnabled(false);
        }
        getLocation();
    }

    protected synchronized void buildGoolgeApiClient(){

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }

    private void getLocationPermission(){
        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
           if(ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                LocationPermissionGranted = true;
                initMap();
           } else {
               ActivityCompat.requestPermissions(this, permission, 1234);
           }
        } else {
            ActivityCompat.requestPermissions(this, permission, 1234);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        LocationPermissionGranted = false;

        switch (requestCode){
            case 1234:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            LocationPermissionGranted = false;
                            return;
                        }
                    }
                    LocationPermissionGranted = true;
                    initMap();
                }
            }
        }
    }

    private void getDeviceLocated(){
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if(LocationPermissionGranted){
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()){
                            Log.d("Location","Location Found");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM);
                        } else{
                            Toast.makeText(MapsRelawanActivity.this, "Current Location Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        } catch (SecurityException e){
            Log.e("getDeviceLocated", e.getMessage());
        }
    }

    private void moveCamera(LatLng latLng, float zoom){
        Log.d("Camera Position", "Lat : " + latLng.latitude + "Lon : " + latLng.longitude);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
    }

    private void getLocation(){

        MarkerOptions markerOptions = new MarkerOptions();

        List<Address> addressList = null;
        Geocoder geocoder = new Geocoder(com.example.foodheroes.Activities.MapsRelawanActivity.this);

        try {
            addressList = geocoder.getFromLocationName(alamatPenerima, 1);
        } catch (IOException e){
            e.printStackTrace();
        }
        Address address = addressList.get(0);
        LatLng MitralatLng = new LatLng(address.getLatitude(), address.getLongitude());
        markerOptions.position(MitralatLng);
        markerOptions.title(alamatPenerima);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
        gMap.addMarker(markerOptions);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(MitralatLng));

//        for(int i = 0; i < results.size(); i++){
//
//            List<Address> addressList = null;
//
//            MarkerOptions markerOptions = new MarkerOptions();
//
//
//            if (true){
//                Geocoder geocoder = new Geocoder(com.example.foodheroes.Activities.MapsRelawanActivity.this);
//
//                try {
//                    addressList = geocoder.getFromLocationName(results.get(i).getAlamatGor(),1);
//                    Log.d("ASD",results.get(i).getAlamatGor());
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                for (int j = 0; j < addressList.size();j++){
//                    Address address = addressList.get(j);
//                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
//                    markerOptions.position(latLng);
//                    markerOptions.title(results.get(i).getNamaGor());
//                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
//                    mMap.addMarker(markerOptions);
//                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//                }
//
//            }
//
//        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        locationRequest = new LocationRequest();

        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onLocationChanged(Location location) {
        locationLast = location;

        if (marker!=null){
            marker.remove();
        }

//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        // LatLng latLng = new LatLng(-34, 151);

//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Choose Location");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//        // markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.logo));
//
//        marker = gMap.addMarker(markerOptions);
//
//        gMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        gMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if (googleApiClient!=null){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }

    }
}
