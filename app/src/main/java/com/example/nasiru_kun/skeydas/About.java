package com.example.nasiru_kun.skeydas;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class About extends Fragment {

    HashMap<String,List<String>> About_category;
    List<String> About_list;
    ExpandableListView Exp_list;
    AboutAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public About() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        Exp_list = (ExpandableListView)view.findViewById(R.id.exp_list);
        About_category = DataProvider.getInfo();
        About_list = new ArrayList<String>(About_category.keySet());
        adapter = new AboutAdapter(this, About_category, About_list);
        Exp_list.setAdapter(adapter);

        return view;
    }
}