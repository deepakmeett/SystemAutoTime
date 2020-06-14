package com.example.systemautotime;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (Settings.Global.getInt( getContentResolver(), Settings.Global.AUTO_TIME ) == 1) {
                    Toast.makeText( this, "Automatic date & time enable", Toast.LENGTH_SHORT ).show();
                } else {
//                    Toast.makeText( this, "Automatic date & time disable", Toast.LENGTH_SHORT ).show();
                    showDialog();
                }
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showDialog() {
        AlertDialog.Builder builderDia = new AlertDialog.Builder( this );
        builderDia.setTitle( "Automatic date & time detection" );
        builderDia.setCancelable( false );
        builderDia.setMessage( "You need to enable Automatic date & time to access this.\n\nPress OK to Exit" );
        builderDia.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        } );
        builderDia.show();
    }
}
