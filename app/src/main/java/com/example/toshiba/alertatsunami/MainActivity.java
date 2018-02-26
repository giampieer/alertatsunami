package com.example.toshiba.alertatsunami;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Layout.Ajustes;
import Layout.Ayuda;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    transaction.replace(R.id.contenedor_fragments,new InicioFragment()).addToBackStack(null).commit();
                    return true;
                case R.id.navigation_dashboard:
                    transaction.replace(R.id.contenedor_fragments,new SismoFragment()).addToBackStack(null).commit();
                    return true;
                case R.id.navigation_notifications:
                    transaction.replace(R.id.contenedor_fragments,new AlertaFragment()).addToBackStack(null).commit();
                    return true;
            }
            return false;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Linterna) {
            FragmentManager fragmentActivity = getSupportFragmentManager();
            LinternaFragment container = new LinternaFragment();
            fragmentActivity.beginTransaction().replace(R.id.contenedor_fragments, container).addToBackStack(null).commit();}
        if (id == R.id.Ayuda) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Ayuda container1Fragment = new Ayuda();
            fragmentManager.beginTransaction().replace(R.id.contenedor_fragments, container1Fragment).addToBackStack(null).commit();

        }
        if (id == R.id.Ajustes) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Ajustes container1Fragment = new Ajustes();
            fragmentManager.beginTransaction().replace(R.id.contenedor_fragments, container1Fragment).addToBackStack(null).commit();}
        if (id == R.id.CERRARSESION) {

            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);
            dialogo1.setTitle("Importante");
            dialogo1.setMessage("¿Deseas cerrar sesion ?");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    cerrar_sesion();                }
            });
            dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {

                }
            });
            dialogo1.show();        }

        return super.onOptionsItemSelected(item);
    }
    public void cerrar_sesion(){
        //limpiar los datos almacenados para inicar sesion
        System.exit(0);

    }


    public void alarmaOff(View v) {
        mp.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargar();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void cargar(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contenedor_fragments,new InicioFragment()).addToBackStack(null).commit();
    }

    public void sendNotification(View view) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);

//Create the intent that’ll fire when the user taps the notification//
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mBuilder.setContentIntent(pendingIntent);

        mBuilder.setSmallIcon(R.drawable.emer);
        mBuilder.setContentTitle("Alerta De Tsunami");
        mBuilder.setContentText("Se recomienda alejarse de lugares cercanos al mar");

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(001, mBuilder.build());

        mp = MediaPlayer.create(this, R.raw.alert);
        mp.start();
        mp.setLooping(true);
    }

}
