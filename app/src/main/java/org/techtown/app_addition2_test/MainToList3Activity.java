package org.techtown.app_addition2_test;


import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.app.Activity;
        import android.graphics.Rect;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.view.Window;


public class MainToList3Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_to_list3);

        Button button200 = findViewById(R.id.button200);
        button200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CarRegisterActivity.class);
                startActivity(intent);
            }
        });

        Button button201 = findViewById(R.id.button201);
        button201.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        Button button202 = findViewById(R.id.button202);
        button202.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangeActivity.class);
                startActivity(intent);
            }
        });

        Button button203 = findViewById(R.id.button203);
        button203.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FQA_Activity.class);
                startActivity(intent);
            }
        });

        Button button251 = findViewById(R.id.button251);
        button251.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GongjiActivity.class);
                startActivity(intent);
            }
        });


    }
}