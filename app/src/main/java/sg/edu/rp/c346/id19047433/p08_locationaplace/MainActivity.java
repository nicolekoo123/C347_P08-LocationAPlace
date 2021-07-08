package sg.edu.rp.c346.id19047433.p08_locationaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3;
    private GoogleMap map;
    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng poi_Singapore = new LatLng(1.3521, 103.8198);

                LatLng poi_North = new LatLng(1.458836, 103.814023);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_North)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654\nOperating hours: 10am-5pm" +
                                "Tel:65433456")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.star)));

                LatLng poi_Central = new LatLng(1.289242, 103.827314);
                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542\n" +
                                "Operating hours: 11am-8pm" +
                                "Tel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_East = new LatLng(1.356315, 103.953314);
                Marker East = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788\n" +
                                "Operating hours: 9am-5pm" +
                                "Tel:66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (marker.getTitle().equals("East")){
                            Toast.makeText(MainActivity.this,"East",Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals("Central")){
                            Toast.makeText(MainActivity.this,"Central",Toast.LENGTH_SHORT).show();
                        }
                        else if (marker.getTitle().equals("North - HQ")){
                            Toast.makeText(MainActivity.this,"North - HQ",Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore, 11));
                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
            }
        });

        spn = findViewById(R.id.spinner);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        LatLng poi_Singapore = new LatLng(1.3521, 103.8198);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore, 11));
                        break;
                    case 1:
                        LatLng poi_North = new LatLng(1.458836, 103.814023);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North, 15));
                        break;
                    case 2:
                        LatLng poi_Central = new LatLng(1.289242, 103.827314);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central, 15));
                        break;
                    case 3:
                        LatLng poi_East = new LatLng(1.356315, 103.953314);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East, 15));
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}