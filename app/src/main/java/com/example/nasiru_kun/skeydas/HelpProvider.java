package com.example.nasiru_kun.skeydas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nasiru-kun on 13-Aug-16.
 */
public class HelpProvider {

    public static HashMap<String, List<String>> getInfo(){
        HashMap<String, List<String>> HelpDetails = new HashMap<String, List<String>>();

        List<String> use =new ArrayList<String>();
        use.add("User can open the door when click the door button and send the password to open other door as they turn on the Bluetooth and connected the devices.");

        List<String> connect = new ArrayList<String>();
        connect.add("Firstly, user must turn on the Bluetooth. User has to search the device correctly and connect the device as founded.");

        List<String> set = new ArrayList<String>();
        set.add("For the new user who was downloaded this application, you must set the password at the Set Password. Set the password to open the door using button and password.");

        List<String> reset = new ArrayList<String>();
        reset.add("To change the password, user can reset the current password to the new password at the Reset Password.");

        List<String> forgot = new ArrayList<String>();
        forgot.add("User can set the new password by email if they were forgot the password. Go to the Forget Password.");

        HelpDetails.put("How to use this application ?", use);
        HelpDetails.put("How to connect device ?", connect);
        HelpDetails.put("How to set password ?", set);
        HelpDetails.put("How to reset password ?", reset);
        HelpDetails.put("How to set password if forgot the password ?", forgot);

        return HelpDetails;
    }
}