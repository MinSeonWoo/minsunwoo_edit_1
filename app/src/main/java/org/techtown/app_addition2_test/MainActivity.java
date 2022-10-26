package org.techtown.app_addition2_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SupportMapFragment mapFragment;
    GoogleMap map;

    MarkerOptions myLocationMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Button button4 = findViewById(R.id.button4);
//        button4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
//            }
//        });


        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainToListActivity.class);
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookmarkActivity.class);
                startActivity(intent);
            }
        });

//        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(GoogleMap googleMap) {
//                Log.d("Map", "지도 준비됨.");
//                map = googleMap;
//            }
//        });
//
//        try {
//            MapsInitializer.initialize(this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startLocationService();
//            }
//        });
//
//        String[] permissions = {
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//        };
//
//        checkPermissions(permissions);
//    }
//
//    public void checkPermissions(String[] permissions) {
//        ArrayList<String> targetList = new ArrayList<String>();
//
//        for (int i = 0; i < permissions.length; i++){
//            String curPermission = permissions[i];
//            int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission);
//            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, curPermission + " 권한 있음", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, curPermission + " 권한 없음", Toast.LENGTH_SHORT).show();
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)) {
//                    Toast.makeText(this, curPermission + " 권한 설명 필요함", Toast.LENGTH_SHORT).show();
//                } else {
//                    targetList.add(curPermission);
//                }
//            }
//        }
//
//        String[] targets = new String[targetList.size()];
//        targetList.toArray(targets);
//
//        // 위험 권한 부여 요청
//        ActivityCompat.requestPermissions(this, targets, 101);
//    }
//
//    @Override
//    //권한 요청 콜백함수
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode) { //요청코드 확인
//            case 101: {
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "첫 번째 권한을 사용자가 승인함", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "첫 번째 권한 거부됨", Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }
//        }
//    }
//
//    public void showToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//    }
//
//
//    public void startLocationService() {
//        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//        try {
//            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if (location != null) {
//                double latitude = location.getLatitude();
//                double longitude = location.getLongitude();
//                String message = "최근 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;
//
//                Log.d("Map", message);
//            }
//
//            GPSListener gpsListener = new GPSListener();
//            long minTime = 10000;
//            float minDistance = 0;
//
//            manager.requestLocationUpdates(
//                    LocationManager.GPS_PROVIDER,
//                    minTime, minDistance, gpsListener);
//
//            Toast.makeText(getApplicationContext(), "내 위치확인 요청함",
//                    Toast.LENGTH_SHORT).show();
//
//        } catch (SecurityException e) {
//            e.printStackTrace();
//        }
//    }
//
//    class GPSListener implements LocationListener {
//        public void onLocationChanged(Location location) {
//            Double latitude = location.getLatitude();
//            Double longitude = location.getLongitude();
//
//            String message = "내 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;
//            Log.d("Map", message);
//
//            showCurrentLocation(latitude, longitude);
//        }
//
//        public void onProviderDisabled(String provider) {
//        }
//
//        public void onProviderEnabled(String provider) {
//        }
//
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//        }
//    }
//
//    private void showCurrentLocation(Double latitude, Double longitude) {
//        LatLng curPoint = new LatLng(latitude, longitude);
//        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
//
//        showMyLocationMarker(curPoint);
//    }
//
//    private void showMyLocationMarker(LatLng curPoint) {
//        if (myLocationMarker == null) {
//            myLocationMarker = new MarkerOptions();
//            myLocationMarker.position(curPoint);
//            myLocationMarker.title("● 내 위치\n");
//            myLocationMarker.snippet("● GPS로 확인한 위치");
//            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
//            map.addMarker(myLocationMarker);
//        } else {
//            myLocationMarker.position(curPoint);
//        }
    }
}