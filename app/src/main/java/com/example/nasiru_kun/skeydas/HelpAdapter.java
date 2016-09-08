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
 * Created by Nasiru-kun on 13-Aug-16.
 */
public class HelpAdapter extends BaseExpandableListAdapter {

    private Help context;
    private HashMap<String, List<String>> Help_Category;
    private List<String> Help_List;

    public HelpAdapter(Help context, HashMap<String, List<String>> Help_Category, List<String> Help_List) {
        this.context = context;
        this.Help_Category = Help_Category;
        this.Help_List = Help_List;
    }

    @Override
    public int getGroupCount() {

        return Help_List.size();
    }

    @Override
    public int getChildrenCount(int i) {

        return Help_Category.get(Help_List.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {

        return Help_List.get(i);
    }

    @Override
    public Object getChild(int parent, int child) {

        return Help_Category.get(Help_List.get(parent)).get(child);
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
        String group_title = (String) getGroup(parent);
        if (convertview==null){
            LayoutInflater inflater = (LayoutInflater) context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.parent_help,parentview,false);
        }
        TextView ptv = (TextView)convertview.findViewById(R.id.parenthelp);
        ptv.setTypeface(null, Typeface.BOLD);
        ptv.setText(group_title);

        return convertview;
    }

    @Override
    public View getChildView(int parent, int child, boolean lastChild, View convertview, ViewGroup parentview) {
        String child_title = (String)getChild(parent,child);
        if (convertview==null){
            LayoutInflater inflater = (LayoutInflater)context.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.child_help,parentview,false);
        }
        TextView ctv = (TextView)convertview.findViewById(R.id.childhelp);
        ctv.setText(child_title);

        return convertview;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}