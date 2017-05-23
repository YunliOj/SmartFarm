package com.fb.smartfarm.Module.Work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.fb.smartfarm.R;
import com.fb.smartfarm.UtilsTools.LogUtil;
import com.fb.smartfarm.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 2017/5/1.
 */

public class WarningAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener{
    private List<WarningBean> mList;
    private List<WarningBean> mRemoveList;
    private Context mContext;
    private boolean mShowCb;
    private boolean mIsALL;
    public WarningAdapter(Context context, List<WarningBean> list){
        this.mList = list;
        this.mContext = context;
        mRemoveList = new ArrayList<>();
    }
    @Override
    public int getCount() {
        if(mList!=null){
            LogUtil.d("Echo",""+mList.size());
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mList!=null){
            return mList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_item_warning,parent,false);
        }
        TextView hardWare = ViewHolder.get(convertView,R.id.hardware_name);
        TextView date = ViewHolder.get(convertView,R.id.warning_date);
        TextView place = ViewHolder.get(convertView,R.id.warning_place);
        TextView state = ViewHolder.get(convertView,R.id.warning_state);
        CheckBox cb = ViewHolder.get(convertView,R.id.cb_warning);
        if(cb!=null){
            if(mShowCb){
                cb.setVisibility(View.VISIBLE);
            }else {
                cb.setVisibility(View.INVISIBLE);
            }
        }
        if(mIsALL){
            cb.setChecked(true);
        }else {
            cb.setChecked(false);
        }
        cb.setOnCheckedChangeListener(this);
        cb.setTag(mList.get(position));
        hardWare.setText(mList.get(position).getHardware());
        date.setText(mList.get(position).getDate());
        place.setText(mList.get(position).getPlace());
        state.setText(mList.get(position).getState());

        return convertView;
    }

    public void isShowCheckBox(boolean isShow){
        this.mShowCb = isShow;
        notifyDataSetChanged();
    }

    public List<WarningBean> getRemoveList(){
        return mRemoveList;
    }

    public void isCheckAll(boolean isAll){
        this.mIsALL = isAll;
        if (!mIsALL){
            mRemoveList.clear();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            mRemoveList.add((WarningBean) buttonView.getTag());
        }else {
            mRemoveList.remove(buttonView.getTag());
        }
    }
}
