package org.techtown.app_addition2_test;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import java.lang.*;
import android.Manifest;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
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
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

    NotificationManager manager;

    private static String CHANNEL_ID = "channel1";
    private static String CHANNEL_NAME = "Channel1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // Button button4 = findViewById(R.id.button4);
        //button4.setOnClickListener(new View.OnClickListener() {
       //     @Override
        //    public void onClick(View v) {
        //       Toast.makeText(getApplicationContext(), "버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
        //   }
       //});


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

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d("Map", "지도 준비됨.");
                map = googleMap;

                LatLng spot1 = new LatLng(36.8357, 127.1481);
                googleMap.addMarker(new MarkerOptions().position(spot1).title("ㅎ"));
                LatLng spot2 = new LatLng(36.8324, 127.1479);
                googleMap.addMarker(new MarkerOptions().position(spot2).title("메가박스"));
                LatLng spot3 = new LatLng(36.8336, 127.1458);
                googleMap.addMarker(new MarkerOptions().position(spot3).title("한아름마트"));
                LatLng spot4 = new LatLng(36.8348, 127.1459);
                googleMap.addMarker(new MarkerOptions().position(spot4).title("통닭신사"));
            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();
            }
        });

        String[] permissions = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        checkPermissions(permissions);
    }

    public void checkPermissions(String[] permissions) {
        ArrayList<String> targetList = new ArrayList<String>();

        for (int i = 0; i < permissions.length; i++){
            String curPermission = permissions[i];
            int permissionCheck = ContextCompat.checkSelfPermission(this, curPermission);
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, curPermission + " 권한 있음", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(this, curPermission + " 권한 없음", Toast.LENGTH_SHORT).show();
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, curPermission)) {
                    Toast.makeText(this, curPermission + " 권한 설명 필요함", Toast.LENGTH_SHORT).show();
                } else {
                    targetList.add(curPermission);
                }
            }
        }

        String[] targets = new String[targetList.size()];
        targetList.toArray(targets);

        // 위험 권한 부여 요청
        ActivityCompat.requestPermissions(this, targets, 101);
    }

    @Override
    //권한 요청 콜백함수
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) { //요청코드 확인
            case 101: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한을 사용자가 승인함", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "권한 거부됨", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    public void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String message = "최근 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;

                Log.d("Map", message);

                if (Math.round(latitude) == 37 && Math.round(longitude) == 127) {
                    showNoti1();
                } else {
                    Toast.makeText(this, "Math.round(latitude) : " + Math.round(latitude) + "\nMath.round(longitude) : " + Math.round(longitude), Toast.LENGTH_LONG).show();
                }
            }
            GPSListener gpsListener = new GPSListener();
            long minTime = 10000;
            float minDistance = 0;

            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime, minDistance, gpsListener);

            Toast.makeText(getApplicationContext(), "내 위치확인 요청함",
                    Toast.LENGTH_SHORT).show();

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public void showNoti1(){
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         manager.createNotificationChannel(new NotificationChannel(
                 CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
         ));

         builder = new NotificationCompat.Builder(this, CHANNEL_NAME);
        } else {
        builder = new NotificationCompat.Builder(this);
        }


        builder.setContentTitle("Happy Parking 알림");
        builder.setContentText("불법 주정차 금지 구역에 진입하셨습니다.");
        builder.setSmallIcon(android.R.drawable.ic_menu_view);
        Notification noti = builder.build();

        manager.notify(1, noti);
    }

    class GPSListener implements LocationListener {
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String message = "내 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;
            Log.d("Map", message);



            showCurrentLocation(latitude, longitude);
        }

        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }

    private void showCurrentLocation(Double latitude, Double longitude) {
        LatLng curPoint = new LatLng(latitude, longitude);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));

        showMyLocationMarker(curPoint, latitude, longitude);
    }

    private void showMyLocationMarker(LatLng curPoint, Double latitude, Double longitude) {
        if (myLocationMarker == null) {
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(curPoint);
            myLocationMarker.title("● 내 위치\n");
           // myLocationMarker.snippet("● GPS로 확인한 위치");
            myLocationMarker.snippet("좌표: "+ latitude + ", "+ longitude);
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(myLocationMarker);
        } else {
            myLocationMarker.position(curPoint);
        }
    }
}