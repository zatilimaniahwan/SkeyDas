package com.example.nasiru_kun.skeydas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class ResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        final EditText type3 = (EditText) findViewById(R.id.type3);
        final EditText new1 = (EditText) findViewById(R.id.new1);
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
                    type3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    new1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //hide password of door
                    type3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    new1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }
}
