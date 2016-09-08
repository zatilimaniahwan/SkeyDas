package com.example.nasiru_kun.skeydas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nasiru-kun on 12-Aug-16.
 */
public class DataProvider {

    public static HashMap<String, List<String>> getInfo(){
        HashMap<String,List<String>> AboutDetails = new HashMap<String, List<String>>();

        List<String> SKeyDAs = new ArrayList<String>();
        SKeyDAs.add("Smart Key-Door Apps or acronym to SKeyDAs is an electromechanical lock which is designed to perform locking operations on a door when it receives instructions from an authorized device using button or password to execute the authorization process.");

        List<String> Arduino_Hardware = new ArrayList<String>();
        Arduino_Hardware.add("Arduino boards is one of the hardware that used as the locking door. It will received the instruction to open the door when user send the password or button through smartphones.");

        AboutDetails.put("Smart Key-Door Apps",SKeyDAs);
        AboutDetails.put("Arduino Hardware", Arduino_Hardware);

        return AboutDetails;
    }
}