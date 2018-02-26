package com.example.toshiba.adapter;

import android.animation.Animator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toshiba.alertatsunami.ComentarioFragment;
import com.example.toshiba.alertatsunami.R;
import com.example.toshiba.bean.TremorBean;

import java.util.ArrayList;

/**
 * Created by cris_ on 24/11/2017.
 */

public class CardView_Adapter_Alarma extends RecyclerView.Adapter<CardView_Adapter_Alarma.ViewHolder_Alertas> implements AdapterView.OnItemClickListener {
    static int lastPosition = -1;
    ArrayList<TremorBean> listado;
    TremorBean objTremorBean;
    Context context;

    public CardView_Adapter_Alarma(TremorBean obj) {
        objTremorBean = obj;
    }

    public CardView_Adapter_Alarma(ArrayList<TremorBean> lista, Context context1) {
        listado = lista;
        context = context1;
    }

    @Override
    public ViewHolder_Alertas onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_alertar, parent, false);
        CardView_Adapter_Alarma.ViewHolder_Alertas viewHolder = new CardView_Adapter_Alarma.ViewHolder_Alertas(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder_Alertas holder, final int position) {
        holder.txtlugar.setText("LUGAR : " + listado.get(position).getPlace());
        holder.txthora.setText("HORA : " + listado.get(position).getDateTime());
        holder.txtmagnitud.setText("MAGNITUD : " + listado.get(position).getMagnitude());
        holder.txtevaluacion.setText("EVALUACION : " + listado.get(position).getEvaluation());
        holder.txtreporte.setText("REPORTE : " + listado.get(position).getReport());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Toast.makeText(context, "HOLA MUNDO", Toast.LENGTH_SHORT).show();
                final CharSequence[] items = {"INFORMACION", "REGISTRAR OBSERVACION"};
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(context);
                dialogo1.setTitle("OpciÃ³n");
                dialogo1.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (items[i] == "INFORMACION") {
                            Fragment fragments = new ComentarioFragment();
                            Bundle parametro = new Bundle();
                            parametro.putString("cismo", listado.get(position).getPlace());
                            fragments.setArguments(parametro);
                            FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                            fragmentManager.beginTransaction()
                                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                                    .replace(R.id.contenedor_fragments, fragments)
                                    .addToBackStack(null)
                                    .commit();

                        } else {
                            Toast.makeText(context, "No cargo", Toast.LENGTH_SHORT).show();


                        }
                    }
                });
                AlertDialog alert = dialogo1.create();
                alert.show();
            }
        });
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animacion(holder.cardView, position);
        }
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder_Alertas holder) {
        super.onViewAttachedToWindow(holder);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            animateCircularReveal(holder.itemView);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = null;
        animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }

    @Override
    public int getItemCount() {
        return null != listado ? listado.size() : 0;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    public static class ViewHolder_Alertas extends RecyclerView.ViewHolder {
        public TextView txtlugar;
        public TextView txthora;
        public TextView txtmagnitud;
        public TextView txtevaluacion;
        public TextView txtreporte;
        public CardView cardView;
        public ImageView imagen;

        public ViewHolder_Alertas(View itemView) {
            super(itemView);
            //texto para actualizar
            txtlugar = (TextView) itemView.findViewById(R.id.txttemblor_lugar);
            txthora = (TextView) itemView.findViewById(R.id.txttemblor_hora);
            txtmagnitud = (TextView) itemView.findViewById(R.id.txttemblor_magnitud);
            txtevaluacion = (TextView) itemView.findViewById(R.id.txttemblor_evaluacion);
            txtreporte = (TextView) itemView.findViewById(R.id.txttemblor_reporte);
            cardView = (CardView) itemView.findViewById(R.id.cardview_alerta);
            imagen = (ImageView) itemView.findViewById(R.id.imagenalerta);
        }
    }

    public void Animacion(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.left_cardview);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }

    }
}
