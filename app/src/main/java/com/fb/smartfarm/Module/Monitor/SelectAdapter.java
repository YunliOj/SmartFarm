package com.fb.smartfarm.Module.Monitor;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fb.smartfarm.R;
import com.fb.smartfarm.UtilsTools.LogUtil;
import com.fb.smartfarm.ViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by echo on 2017/5/7.
 */

public class SelectAdapter extends BaseAdapter{
    private Context mContext;
    private List<String> mList;
    private int currPosition = 0;
    public SelectAdapter(Context context, List<String> list) {
        this.mContext = context;
        mList = new ArrayList<>();
        mList.clear();
        mList.addAll(list);
    }
    @Override
    public int getCount() {
        if(mList!=null)
            return mList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mList!=null)
            return mList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_double_list_item,null,false);
        }
        TextView tv = ViewHolder.get(convertView,R.id.tv_double_base_station);
        tv.setText(mList.get(position));

        LinearLayout select = (LinearLayout) convertView.findViewById(R.id.base_station_chosen);
        if(position == currPosition){
            select.setBackgroundColor(Color.parseColor("#24bd9a"));
        }else {
            select.setBackgroundColor(Color.parseColor("#eaeaea"));
        }
        return convertView;
    }

    public void resetData(List<String> list){
        if(mList!=null){
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void setCurrSeletor(int position){
        this.currPosition = position;
        notifyDataSetChanged();
    }

}
