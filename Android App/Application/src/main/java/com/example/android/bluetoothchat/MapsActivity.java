package com.example.android.bluetoothchat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.telephony.SmsManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //location manager that mange the location
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // initialize this location
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        // check network provider is enable
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // parameters provider - min time to undated -min distance to update - location listener
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // get Latitude
                    double    latitude = location.getLatitude();
                    // get longitude
                    double     longitude = location.getLongitude();
                    LatLng latLng = new LatLng(latitude, longitude);
                    // instance goe vorder class
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality() + ",";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 50.2f));
                        sendMassage(longitude,latitude);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // get Latitude
                    double   latitude = location.getLatitude();
                    // get longitude
                    double     longitude = location.getLongitude();
                    // instance the class , latlng
                    LatLng latLng = new LatLng(latitude, longitude);
                    // instance geocoder class
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        String str = addressList.get(0).getLocality() + ",";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 50.2f));
                        sendMassage(longitude,latitude);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });

        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }

    /*---------------------------- */
// send massage
    public void sendMassage(double longitude, double latitude) {
        SharedPreferences sharedPreferences = getSharedPreferences("number", Context.MODE_PRIVATE);
        String n1 = sharedPreferences.getString("number1","not avialable");
        String n2 = sharedPreferences.getString("number2","not avialable");
        String n3 = sharedPreferences.getString("number3","not avialable");



        /*send massage with button
        Intent intent = new Intent();
        intent.setAction(intent.ACTION_VIEW);
        intent.setType("vnd.android-dir/mms-sms");
        intent.setData(Uri.parse("sms:"+n1)) ;
        Intent intent2 = new Intent();
        intent2.setAction(intent.ACTION_VIEW);
        intent2.setType("vnd.android-dir/mms-sms");
        intent2.setData(Uri.parse("sms:"+n2)) ;
        Intent intent3 = new Intent();
        intent3.setAction(intent.ACTION_VIEW);
        intent3.setType("vnd.android-dir/mms-sms");
        intent3.setData(Uri.parse("sms:"+n3)) ;

        intent.putExtra("sms_body","help me this is my loction : "+ "https://www.google.co.id/maps/@"+latitude+","+longitude);
        startActivity(intent);
        intent2.putExtra("sms_body","help me this is my loction : "+ "https://www.google.co.id/maps/@"+latitude+","+longitude);
        startActivity(intent2);intent.putExtra("sms_body","help me this is my loction : "+ "https://www.google.co.id/maps/@"+latitude+","+longitude);
        startActivity(intent);
        intent3.putExtra("sms_body","help me this is my loction : "+ "https://www.google.co.id/maps/@"+latitude+","+longitude);
        startActivity(intent3); */

        // send massage automatic

        String text = "help me this is my location : "+ "https://www.google.co.id/maps/@"+latitude+","+longitude ;

        SmsManager smsMgr1 = SmsManager.getDefault();
        smsMgr1.sendTextMessage(n1, null, text, null, null);

        SmsManager smsMgr2 = SmsManager.getDefault();
        smsMgr2.sendTextMessage(n2, null, text, null, null);

        SmsManager smsMgr3 = SmsManager.getDefault();
        smsMgr3.sendTextMessage(n3, null, text, null, null);

    } }
