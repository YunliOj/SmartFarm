package com.fb.smartfarm.Module.Work;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fb.smartfarm.R;
import com.fb.smartfarm.UtilsTools.LogUtil;
import com.fb.smartfarm.ViewHolder;

import java.util.List;

/**
 * Created by echo on 2017/4/29.
 */

public class WorkAdapter extends BaseAdapter{
    private final String TAG = WorkAdapter.class.getSimpleName();
    private List<WorkBean> mList;
    private Context mContext;
    private final int TAG_CLICK = 100;
    private final int TAG_DEL = 101;
    public WorkAdapter(Context context,List<WorkBean> list){
        this.mContext = context;
        this.mList = list;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_work_list_item,parent,false);
        }
        RelativeLayout r = ViewHolder.get(convertView,R.id.work_item_left);
        TextView date = ViewHolder.get(convertView,R.id.tv_work_item_date);
        TextView place = ViewHolder.get(convertView,R.id.tv_work_item_place);
        TextView type = ViewHolder.get(convertView,R.id.tv_work_item_type);
        TextView content = ViewHolder.get(convertView,R.id.tv_work_item_content);
        WorkBean bean = mList.get(position);
        if(bean!=null){
            date.setText(bean.getmDate());
            type.setText(bean.getWorkType());
            place.setText(bean.getWorkPlace());
            content.setText(bean.getmContent());
            if(position == 0){
                type.setBackground(mContext.getDrawable(R.mipmap.round_blue));
            }else if((position %2) == 0){
                type.setBackground(mContext.getDrawable(R.mipmap.round_blue));

            }else {
                type.setBackground(mContext.getDrawable(R.mipmap.round_orange));

            }
        }



        return convertView;
    }

    private void startWorkActivity(){
        Intent intent = new Intent();
        intent.setClass(mContext,WorkRecordActivity.class);
        mContext.startActivity(intent);
    }

}
