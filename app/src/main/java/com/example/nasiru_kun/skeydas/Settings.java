package com.example.nasiru_kun.skeydas;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment implements View.OnClickListener{


    public Settings() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        TextView one = (TextView) view.findViewById(R.id.setpassword);
        TextView two = (TextView) view.findViewById(R.id.resetpassword);
        TextView three = (TextView) view.findViewById(R.id.forgetpassword);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.setpassword:
                Intent intent = new Intent(getActivity(),SetPassword.class);
                startActivity(intent);
                break;

            case R.id.resetpassword:
                Intent intent1 = new Intent(getActivity(),ResetPassword.class);
                startActivity(intent1);
                break;

            case R.id.forgetpassword:
                Intent intent2 = new Intent(getActivity(),ForgetPassword.class);
                startActivity(intent2);
                break;
        }
    }
}