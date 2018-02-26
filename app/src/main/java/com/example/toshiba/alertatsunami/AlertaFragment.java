package com.example.toshiba.alertatsunami;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.toshiba.Conexion.Conexion;
import com.example.toshiba.adapter.CardView_Adapter_Alarma;
import com.example.toshiba.bean.TremorBean;
import com.example.toshiba.dao.TremorDAO;

import java.util.ArrayList;


public class AlertaFragment extends Fragment {

    FloatingActionButton boton;
    Camera camera;
    boolean turnon = false;
    public AlertaFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View obj=inflater.inflate(R.layout.fragment_alerta, container, false);
        boton = (FloatingActionButton) obj.findViewById(R.id.boton);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!turnon) {
                    camera = Camera.open();
                    Camera.Parameters parameters = camera.getParameters();
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(parameters);
                    camera.startPreview();
                    turnon = true;
                }else{
                    camera.stopPreview();
                    camera.release();
                    turnon = false;
                }
            }
        });
        return obj;

    }

}
