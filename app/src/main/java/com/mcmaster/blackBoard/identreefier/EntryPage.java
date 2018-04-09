package com.mcmaster.blackBoard.identreefier;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

public class EntryPage extends Activity implements OnItemSelectedListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private UserInput userInput;
    private TextView addressField; //Add a new TextView to your activity_main to display the address
    private String provider;

    private static final String TAG = "MainActivity";
    private TextView mLatitudeTextView;
    private TextView mLongitudeTextView;
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;
    private LocationManager mLocationManager;

    private LocationRequest mLocationRequest;
    private com.google.android.gms.location.LocationListener listener;
    private long UPDATE_INTERVAL = 2 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */

    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);

        Spinner tree_type = (Spinner) findViewById(R.id.tree_type_spinner);
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.tree_type, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tree_type.setAdapter(aa);
        tree_type.setOnItemSelectedListener(this);

        Spinner leaf_arrangement = (Spinner) findViewById(R.id.leaflet_arrangement_spinner);
        ArrayAdapter<CharSequence> aa2 = ArrayAdapter.createFromResource(this, R.array.l_arrangement, android.R.layout.simple_spinner_item);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_arrangement.setAdapter(aa2);
        leaf_arrangement.setOnItemSelectedListener(this);

        Spinner leaf_type = (Spinner) findViewById(R.id.leaf_type_spinner);
        ArrayAdapter<CharSequence> aa3 = ArrayAdapter.createFromResource(this, R.array.l_type, android.R.layout.simple_spinner_item);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_type.setAdapter(aa3);
        leaf_type.setOnItemSelectedListener(this);

        this.userInput = new UserInput();


        mLatitudeTextView = (TextView) findViewById((R.id.textView16));
        mLongitudeTextView = (TextView) findViewById((R.id.textView18));

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        checkLocation();
    }

    @Override
    public void onConnected(Bundle bundle) {
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

        startLocationUpdates();

        mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if(mLocation == null){
            startLocationUpdates();
        }
        if (mLocation != null) {

            // mLatitudeTextView.setText(String.valueOf(mLocation.getLatitude()));
            //mLongitudeTextView.setText(String.valueOf(mLocation.getLongitude()));
        } else {
            Toast.makeText(this, "Location not Detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Connection Suspended");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    protected void startLocationUpdates() {
        // Create the location request
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);
        // Request location updates
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
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                mLocationRequest, this);
        Log.d("reque", "--->>>>");
    }

    @Override
    public void onLocationChanged(Location location) {

        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        mLatitudeTextView.setText(String.valueOf(location.getLatitude()));
        mLongitudeTextView.setText(String.valueOf(location.getLongitude() ));

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        // You can now create a LatLng Object for use with maps
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if((userInput.lattitude == null) || (userInput.longitude == null)){
            userInput.longitude = latLng.longitude;
            userInput.lattitude = latLng.latitude;
        }
        Log.v("Lat,long",userInput.lattitude.toString() +","+userInput.longitude.toString());
    }

    private boolean checkLocation() {
        if(!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

  /**
     * String treeType;
     * String leafGrouping;
     * String leafEdge;
     * String lobesOrNot;
     * String leafBladeStructure;
     * String leafBase;
     * String leafShape;
     * String leafBudArrangement
     * String barkColour;
     * String barkTexture;
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
        switch (parent.getId()){
            case R.id.tree_type_spinner:
                String treeType = parent.getSelectedItem().toString().trim();
                Log.v("input-tree-type: ", treeType);
                userInput.treeType = treeType;
                Log.v("input-tree-type: ",userInput.treeType);
                break;
            case R.id.leaflet_arrangement_spinner:
                userInput.leafletArrangement = parent.getSelectedItem().toString().trim();
                Log.v("input-leaflet-arng:",userInput.leafletArrangement);
                break;
            case R.id.leaf_type_spinner:
                userInput.leafType = parent.getSelectedItem().toString().trim();
                Log.v("input-leaf-type: ",userInput.leafType);
                break;

        }
    }


    //public void add
    public void sendBlackBoardData(View view){
        Intent intent = new Intent(this,BlackBoard.class);
        intent.putExtra("userInput", userInput);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
