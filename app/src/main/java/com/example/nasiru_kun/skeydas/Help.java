package com.example.nasiru_kun.skeydas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Help extends Fragment {

    HashMap<String,List<String>> Help_category;
    List<String> Help_list;
    ExpandableListView expandableListView;
    HelpAdapter adapter;

    public Help() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        expandableListView = (ExpandableListView)view.findViewById(R.id.explist);
        Help_category = HelpProvider.getInfo();
        Help_list = new ArrayList<String>(Help_category.keySet());
        adapter = new HelpAdapter(this,Help_category, Help_list);
        expandableListView.setAdapter(adapter);

        return  view;
    }
}