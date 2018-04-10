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
import android.support.v4.content.ContextCompat;
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
import android.Manifest;


public class EntryPage extends Activity implements OnItemSelectedListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private UserInput userInput;
    private TextView addressField; //Add a new TextView to your activity_main to display the address
    private String provider;

    private static final String TAG = "EntryPage";
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

        Spinner tree_type = (Spinner) findViewById(R.id.leaf_type_spinner);
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.leaf_type, android.R.layout.simple_spinner_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tree_type.setAdapter(aa);
        tree_type.setOnItemSelectedListener(this);

        Spinner leaf_arrangement = (Spinner) findViewById(R.id.leaflet_arrangement_spinner);
        ArrayAdapter<CharSequence> aa2 = ArrayAdapter.createFromResource(this, R.array.l_arrangement, android.R.layout.simple_spinner_item);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_arrangement.setAdapter(aa2);
        leaf_arrangement.setOnItemSelectedListener(this);

        Spinner leaf_type = (Spinner) findViewById(R.id.leaf_grouping_spinner);
        ArrayAdapter<CharSequence> aa3 = ArrayAdapter.createFromResource(this, R.array.l_grouping, android.R.layout.simple_spinner_item);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_type.setAdapter(aa3);
        leaf_type.setOnItemSelectedListener(this);

        Spinner leaf_edges = (Spinner) findViewById(R.id.leaf_edge_spinner);
        ArrayAdapter<CharSequence> aa4 = ArrayAdapter.createFromResource(this, R.array.l_edges, android.R.layout.simple_spinner_item);
        aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_edges.setAdapter(aa4);
        leaf_edges.setOnItemSelectedListener(this);

        Spinner leaf_isLobbed = (Spinner) findViewById(R.id.leaf_isLobbed_spinner);
        ArrayAdapter<CharSequence> aa5 = ArrayAdapter.createFromResource(this, R.array.l_lobbed, android.R.layout.simple_spinner_item);
        aa5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_isLobbed.setAdapter(aa5);
        leaf_isLobbed.setOnItemSelectedListener(this);

        Spinner leaf_bladeStructure = (Spinner) findViewById(R.id.leaf_blade_spinner);
        ArrayAdapter<CharSequence> aa6 = ArrayAdapter.createFromResource(this, R.array.l_blade_struture, android.R.layout.simple_spinner_item);
        aa6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_bladeStructure.setAdapter(aa6);
        leaf_bladeStructure.setOnItemSelectedListener(this);

        Spinner leaf_base = (Spinner) findViewById(R.id.leaf_base_spinner);
        ArrayAdapter<CharSequence> aa7 = ArrayAdapter.createFromResource(this, R.array.l_base, android.R.layout.simple_spinner_item);
        aa7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_base.setAdapter(aa7);
        leaf_base.setOnItemSelectedListener(this);

        Spinner leaf_shape = (Spinner) findViewById(R.id.leaf_shape_spinner);
        ArrayAdapter<CharSequence> aa8 = ArrayAdapter.createFromResource(this, R.array.l_shape, android.R.layout.simple_spinner_item);
        aa8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_shape.setAdapter(aa8);
        leaf_shape.setOnItemSelectedListener(this);

        Spinner leaf_needlesOrScales = (Spinner) findViewById(R.id.leaf_needlesOrScales_spinner);
        ArrayAdapter<CharSequence> aa9 = ArrayAdapter.createFromResource(this, R.array.l_needlesOrScales, android.R.layout.simple_spinner_item);
        aa9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_needlesOrScales.setAdapter(aa9);
        leaf_needlesOrScales.setOnItemSelectedListener(this);

        Spinner leaf_needlesBundled = (Spinner) findViewById(R.id.leaf_needlesBundled);
        ArrayAdapter<CharSequence> aa10 = ArrayAdapter.createFromResource(this, R.array.l_needlesBundled, android.R.layout.simple_spinner_item);
        aa10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leaf_needlesBundled.setAdapter(aa10);
        leaf_needlesBundled.setOnItemSelectedListener(this);

        Spinner bark_color = (Spinner) findViewById(R.id.bark_color_spinner);
        ArrayAdapter<CharSequence> aa11 = ArrayAdapter.createFromResource(this, R.array.bark_color, android.R.layout.simple_spinner_item);
        aa11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bark_color.setAdapter(aa11);
        bark_color.setOnItemSelectedListener(this);

        Spinner bark_color2 = (Spinner) findViewById(R.id.bark_color2_spinner);
        ArrayAdapter<CharSequence> aa12 = ArrayAdapter.createFromResource(this, R.array.bark_color, android.R.layout.simple_spinner_item);
        aa12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bark_color2.setAdapter(aa12);
        bark_color2.setOnItemSelectedListener(this);

        Spinner bark_texture = (Spinner) findViewById(R.id.bark_texture_spinner);
        ArrayAdapter<CharSequence> aa13 = ArrayAdapter.createFromResource(this, R.array.bark_texture, android.R.layout.simple_spinner_item);
        aa13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bark_texture.setAdapter(aa13);
        bark_texture.setOnItemSelectedListener(this);



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
        relocate();

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

        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        // You can now create a LatLng Object for use with maps
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if((userInput.lattitude == null) || (userInput.longitude == null)){
            userInput.longitude = latLng.longitude;
            userInput.lattitude = latLng.latitude;
            Log.v("Lat,long",userInput.lattitude.toString() +","+userInput.longitude.toString());
        }
        Log.v("Lat,long-afterIF",userInput.lattitude.toString() +","+userInput.longitude.toString());
    }

    private boolean checkLocation() {
        if (ActivityCompat.checkSelfPermission(EntryPage.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(EntryPage.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EntryPage.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return true;
        }else{
            return isLocationEnabled();
        }
    }

    public boolean relocate(){
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


        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
//
//    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
//
//    public boolean checkLocationPermission() {
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            // Should we show an explanation?
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)) {
//
//                // Show an explanation to the user *asynchronously* -- don't block
//                // this thread waiting for the user's response! After the user
//                // sees the explanation, try again to request the permission.
//                new AlertDialog.Builder(this)
//                        .setTitle(R.string.title_location_permission)
//                        .setMessage(R.string.text_location_permission)
//                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                //Prompt the user once explanation has been shown
//                                ActivityCompat.requestPermissions(EntryPage.this,
//                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                                        MY_PERMISSIONS_REQUEST_LOCATION);
//                            }
//                        })
//                        .create()
//                        .show();
//
//
//            } else {
//                // No explanation needed, we can request the permission.
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                        MY_PERMISSIONS_REQUEST_LOCATION);
//            }
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_LOCATION: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                    // permission was granted, yay! Do the
//                    // location-related task you need to do.
//                    if (ContextCompat.checkSelfPermission(this,
//                            Manifest.permission.ACCESS_FINE_LOCATION)
//                            == PackageManager.PERMISSION_GRANTED) {
//
//                        //Request location updates:
//                        locationManager.requestLocationUpdates(provider, 400, 1, this);
//                    }
//
//                } else {
//
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//
//                }
//                return;
//            }
//
//        }
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//
//            locationManager.requestLocationUpdates(provider, 400, 1, this);
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (ContextCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//
//            locationManager.removeUpdates(this);
//        }
//    }

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
            case R.id.leaf_type_spinner:
                String treeType = parent.getSelectedItem().toString().trim();
                Log.v("input-tree-type: ", treeType);
                userInput.treeType = treeType;
                Log.v("input-tree-type: ",userInput.treeType);
                break;
            case R.id.leaflet_arrangement_spinner:
                userInput.leafletArrangement = parent.getSelectedItem().toString().trim();
                Log.v("input-leaflet-arng:",userInput.leafletArrangement);
                break;
            case R.id.leaf_grouping_spinner:
                userInput.leafType = parent.getSelectedItem().toString().trim();
                Log.v("input-leaf-type: ",userInput.leafType);
                break;
            case R.id.leaf_edge_spinner:
                userInput.leafEdge = parent.getSelectedItem().toString().trim();
                Log.v("input-leaf-edge: ",userInput.leafEdge);
                break;
            case R.id.leaf_isLobbed_spinner:
                userInput.lobesOrNot = parent.getSelectedItem().toString().trim();
                Log.v("input-leaf-isLobbed: ",userInput.lobesOrNot);
                break;
            case R.id.leaf_blade_spinner:
                userInput.leafBladeStructure = parent.getSelectedItem().toString().trim();
                Log.v("input-leaf-blade: ",userInput.leafBladeStructure);
                break;
            case R.id.leaf_base_spinner:
                userInput.leafBase = parent.getSelectedItem().toString().trim();
                Log.v("input-leaf-base: ",userInput.leafBase);
                break;
            case R.id.leaf_shape_spinner:
                userInput.leafShape = parent.getSelectedItem().toString().trim();
                Log.v("input-leaf-shape: ",userInput.leafShape);
                break;
            //conifers
            case R.id.leaf_needlesOrScales_spinner:
                userInput.needlesOrScales = parent.getSelectedItem().toString().trim();
                Log.v("input-leaf-needles1: ",userInput.needlesOrScales);
                break;
            case R.id.leaf_needlesBundled:
                userInput.needlesBundled = parent.getSelectedItem().toString().trim();
                Log.v("input-leaf-needles2: ",userInput.needlesBundled);
                break;
            //end
            case R.id.bark_color_spinner:
                userInput.barkColour = parent.getSelectedItem().toString().trim();
                Log.v("input-bark-color: ",userInput.barkColour);
                break;
            case R.id.bark_color2_spinner:
                userInput.barkColor2 = parent.getSelectedItem().toString().trim();
                Log.v("input-bark-color: ",userInput.barkColor2);
                break;
            case R.id.bark_texture_spinner:
                userInput.barkTexture = parent.getSelectedItem().toString().trim();
                Log.v("input-bark-texture: ",userInput.barkTexture);
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
