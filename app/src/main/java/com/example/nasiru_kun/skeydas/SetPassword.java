package com.example.nasiru_kun.skeydas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class SetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        final EditText type1 = (EditText) findViewById(R.id.type1);
        final EditText type2 = (EditText) findViewById(R.id.type2);
        //get the show or hide password door Checkbox
        CheckBox checkBox = (CheckBox) findViewById(R.id.cbshowpass);

        /** add onClickListener on Checkbox
         * when user clicks on this checkbox, this is the handler
         */
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //checkbox status is changed from uncheck to checked
                if (!isChecked) {
                    //show password of door
                    type1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    type2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //hide password of door
                    type1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    type2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
}