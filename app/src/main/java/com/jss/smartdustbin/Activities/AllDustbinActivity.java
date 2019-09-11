package com.jss.smartdustbin.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jss.smartdustbin.Models.Dustbin;
import com.jss.smartdustbin.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AllDustbinActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener {

    TextView dustbinState;
    TextView dustbinCity;
    TextView dustbinLocality;
    LinearLayout content;
    ProgressBar loader;
    ProgressBar dustbinLevelPb;
    Button dustbinMoreDetails;
    private HashMap<Marker, Integer> mHashMap;
    ArrayList<Dustbin> dustbinArrayList;
    HashMap<String, Dustbin> markerDustbinHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_dustbin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Dustbin");
        mHashMap = new HashMap<Marker, Integer>();

        dustbinArrayList = new ArrayList<>();
        dustbinState = findViewById(R.id.state_text_view);
        dustbinCity = findViewById(R.id.city_text_view);
        dustbinLocality = findViewById(R.id.locality_text_view);
        content = findViewById(R.id.comtent_all_dustbin_activity);
        loader = findViewById(R.id.loader_all_dustbin_map);
        dustbinLevelPb = findViewById(R.id.dustbin_progressbar);
        dustbinMoreDetails = findViewById(R.id.bt_more_details);
        markerDustbinHashMap = new HashMap<String, Dustbin>();

        dustbinLevelPb.setProgress(100);

        dustbinMoreDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dustbinDetailsActivityIntent = new Intent(AllDustbinActivity.this, DustbinDetailsActivity.class);
                startActivity(dustbinDetailsActivityIntent);
            }
        });
        Dustbin dustbin1 = new Dustbin();
        dustbin1.setState("UP");
        dustbin1.setCity("Noida");
        dustbin1.setId("102");
        dustbin1.setLocality("Sector 58");

        Dustbin dustbin2 = new Dustbin();
        dustbin2.setState("UP");
        dustbin2.setCity("Noida");
        dustbin2.setId("106");
        dustbin2.setLocality("Sector 62");

        Dustbin dustbin3 = new Dustbin();
        dustbin3.setState("UP");
        dustbin3.setCity("Noida");
        dustbin3.setId("102");
        dustbin3.setLocality("Sector 64");

        Dustbin dustbin4 = new Dustbin();
        dustbin4.setCity("Noida");
        dustbin4.setId("102");
        dustbin4.setLocality("Sector 64");

        dustbinArrayList.add(dustbin1);
        dustbinArrayList.add(dustbin2);
        dustbinArrayList.add(dustbin3);
        dustbinArrayList.add(dustbin4);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.all_dustbins_map);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                // mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                mMap.clear(); //clear old markers

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(37.4219999,-122.0862462))
                        .zoom(10)
                        .bearing(0)
                        .tilt(45)
                        .build();

                                                                                                                                                                                                                                                                                                                                                                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 1000, null);
                /*mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(37.4219999,-122.0862462)));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(16));


*/              for (int i = 0; i < dustbinArrayList.size(); i++){

                }
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.4219999, -122.0862462))
                        .title("Spider Man")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                markerDustbinHashMap.put(marker.getId(), dustbinArrayList.get(0));


                Marker marker1 =  mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.4629101,-122.2449094))
                        .title("Iron Man")
                        .snippet("His Talent : Plenty of money"));
                markerDustbinHashMap.put(marker1.getId(), dustbinArrayList.get(1));

                Marker marker2 = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(37.3092293,-122.1136845))
                        .title("Captain America"));
                markerDustbinHashMap.put(marker2.getId(), dustbinArrayList.get(2));

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {

                        dustbinState.setText(markerDustbinHashMap.get(marker.getId()).getState());
                        marker.getId();
                        dustbinCity.setText(markerDustbinHashMap.get(marker.getId()).getCity());
                        dustbinLocality.setText(markerDustbinHashMap.get(marker.getId()).getLocality());
                        Toast.makeText(getApplicationContext(),"Marker is clicked" ,Toast.LENGTH_LONG).show();
                        return true;
                    }
                });

            }
        });



    }

    private void updateMap(){

    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        Toast.makeText(this,"Marker is clicked" ,Toast.LENGTH_LONG).show();

       /* if (marker.equals,(myMarker))
        {
            //handle click here
        }*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {


            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
