package com.example.a1124;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MsgFragment extends Fragment {


    public MsgFragment() {
        // Required empty public constructor
    }

    private TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_msg, container, false);

        textView = view.findViewById(R.id.txt2);


                Bundle bundle = getArguments();
                String result = bundle.getString("MainActivity");
                textView.setText(result);
                if (result.equals("")){
                    textView.setText("null");
                }



        return view;
    }

}
