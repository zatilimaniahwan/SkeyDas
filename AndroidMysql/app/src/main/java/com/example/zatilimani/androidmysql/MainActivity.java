package com.example.zatilimani.androidmysql;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import com.example.zatilimani.androidmysql.R;
import com.example.zatilimani.androidmysql.app.AppConfig;
import com.example.zatilimani.androidmysql.app.AppController;
import com.example.zatilimani.androidmysql.helper.SQLiteHelper;
import com.example.zatilimani.androidmysql.helper.SessionManager;

public class MainActivity extends Activity{
    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout;

    private SQLiteHelper db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView) findViewById(R.id.devicename);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        // SqLite database handler
        db = new SQLiteHelper(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutDevice();
        }

        // Fetching user details from SQLite
        HashMap<String, String> device = db.getDeviceDetails();

        String devicename = device.get("devicename");
        String email = device.get("email");

        // Displaying the user details on the screen
        txtName.setText(devicename);
        txtEmail.setText(email);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutDevice();
            }
        });
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutDevice() {
        session.setLogin(false);

        db.deleteDevices();

        // Launching the login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
