package com.example.toshiba.alertatsunami;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.toshiba.Conexion.Conexion;
import com.example.toshiba.adapter.CardView_Adapter_Alarma;
import com.example.toshiba.bean.TremorBean;
import com.example.toshiba.dao.TremorDAO;

import java.util.ArrayList;


public class SismoFragment extends Fragment {
    RecyclerView.Adapter adapter;
    RecyclerView recyclerview;
    String conexion = "";
    TremorDAO objdao = null;
    Conexion conexion1 = new Conexion();
    TremorBean objbean = null;
    ArrayList<TremorBean> lista = new ArrayList<TremorBean>();

    public SismoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View obj = inflater.inflate(R.layout.fragment_sismo, container, false);
        recyclerview = (RecyclerView) obj.findViewById(R.id.recycler_view_sismo);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        // Inflate the layout for this fragment
        Cargar_Actividades_Hoy();
        return obj;
    }

    private void Cargar_Actividades_Hoy() {
        new Async_Listar_Problemas().execute();
    }

    class Async_Listar_Problemas extends AsyncTask<Void, Void, String> {
        String mensaje = "";
        private ProgressDialog progressDialog;

        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(getActivity(), "", "Cargando Temblores...", true);
        }

        @Override
        protected String doInBackground(Void... obj) {

            mensaje = String.valueOf(conexion1.isConnected(getActivity().getApplicationContext()));
            conexion = mensaje;
            if (conexion.equals("true")) {
                objdao = new TremorDAO();

                lista = objdao.ListTremors();
                adapter = new CardView_Adapter_Alarma(lista, getActivity());
            }
            return mensaje;
        }

        protected void onPostExecute(String result) {
            if (conexion.equals("true")) {
                recyclerview.setAdapter(adapter);
            } else {
                //Toast.makeText(getActivity(), "No  hay conexion a internet", Toast.LENGTH_LONG).show();

            }
            progressDialog.dismiss();
        }
    }


}
