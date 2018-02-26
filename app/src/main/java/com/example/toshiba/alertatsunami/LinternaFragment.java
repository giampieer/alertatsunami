package com.example.toshiba.alertatsunami;


import android.hardware.Camera;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class LinternaFragment extends Fragment {
    FloatingActionButton boton;
    Camera camera;
    boolean turnon = false;


    public LinternaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View obj=inflater.inflate(R.layout.fragment_linterna, container, false);
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
