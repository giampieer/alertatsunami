package com.example.toshiba.alertatsunami;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toshiba.Conexion.Conexion;


/**
 * A simple {@link Fragment} subclass.
 */
public class ComentarioFragment extends Fragment {
    Conexion ObjConexion = new Conexion();
    String Conexion;
    TextView LBLUBICASION;
    EditText TXTCOMENTARIO;
    String Sismo;
    public ComentarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View obj = inflater.inflate(R.layout.fragment_comentario, container, false);
        TXTCOMENTARIO = (EditText) obj.findViewById(R.id.TXTCOMENTARIO);
        LBLUBICASION = (TextView) obj.findViewById(R.id.LBLSISMO);
        Cargar();
        return obj;
    }
    public void Cargar() {
        new Async_Cargar_Datos().execute();
    }

    class Async_Cargar_Datos extends AsyncTask<Void, Void, String> {

        private ProgressDialog progressDialog;

        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(), "", "Cargando", true);
        }
        @Override
        protected String doInBackground(Void... voids) {
            String mensaje = "";
            mensaje = String.valueOf(ObjConexion.isConnected(getActivity().getApplicationContext()));
             Conexion = mensaje;

            if(Conexion.equals("true")){
                Sismo = getArguments().getString("cismo");
            }
            return mensaje;
        }
        protected void onPostExecute(String result) {
            if (Conexion.equals("true")) {
                LBLUBICASION.setText(Sismo);

            } else {
                Toast.makeText(getActivity(), "No  hay conexion a internet", Toast.LENGTH_LONG).show();

            }
            progressDialog.dismiss();
        }
    }

}
