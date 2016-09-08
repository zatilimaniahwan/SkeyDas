package com.example.nasiru_kun.skeydas;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Nasiru-kun on 12-Aug-16.
 */
public class AboutAdapter extends BaseExpandableListAdapter{

    private About ctx;
    private HashMap<String, List<String>> About_Category;
    private List<String> About_List;

    public AboutAdapter(About ctx, HashMap<String, List<String>> About_Category, List<String> About_List){
        this.ctx = ctx;
        this.About_Category = About_Category;
        this.About_List = About_List;
    }

    @Override
    public int getGroupCount() {
        return About_List.size();
    }

    @Override
    public int getChildrenCount(int i) {


        return About_Category.get(About_List.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return About_List.get(i);
    }

    @Override
    public Object getChild(int parent, int child) {

        return About_Category.get(About_List.get(parent)).get(child);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int parent, int child) {


        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertview, ViewGroup parentview) {
        String group_title = (String)getGroup(parent);
        if(convertview==null){
            LayoutInflater inflater = (LayoutInflater) ctx.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.parent_layout,parentview,false);

        }
        TextView parent_textview = (TextView)convertview.findViewById(R.id.parent_txt);
        parent_textview.setTypeface(null, Typeface.BOLD);
        parent_textview.setText(group_title);
        return convertview;
    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild, View convertview, ViewGroup parentview) {
        String child_title = (String)getChild(parent,child);
        if (convertview == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.child_layout,parentview,false);
        }
        TextView child_textview = (TextView)convertview.findViewById(R.id.child_txt);
        child_textview.setText(child_title);

        return convertview;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
