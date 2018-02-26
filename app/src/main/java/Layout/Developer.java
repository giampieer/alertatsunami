package Layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.toshiba.alertatsunami.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Developer extends Fragment {

    TextView info;
    public Developer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View obj=inflater.inflate(R.layout.fragment_informacion, container, false);
        //swipeContainer = (SwipeRefreshLayout) obj.findViewById(R.id.swipe_proy);
        //swipeContainer.setOnRefreshListener(this);
        info=(TextView) obj.findViewById(R.id.TXTINFO);
        String texto="\n\n\n\nNuestra plataforma móvil sirve para la alerta de prevención de un posible Tsunami. \n" +
                "La aplicación esta desarrollada para la Hackathon de INNOVA.\nDEVELOPERS:\n - ALVAREZ MENDOZA CRISTHOPER \n - MARISCAL POMACAJA GIAMPIEER \n - MOZOMBITE DELGADO LUIS \n - MONZON YACUA CHRISTOPHER \n - VILCHEZ LUCANA JHON" ;

        info.setText(texto);

        return obj;
    }

}
