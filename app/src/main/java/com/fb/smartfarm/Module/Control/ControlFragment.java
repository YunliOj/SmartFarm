package com.fb.smartfarm.Module.Control;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.fb.smartfarm.R;
import com.fb.smartfarm.view.CustomView.ControlItem;
import com.fb.smartfarm.view.CustomView.MyBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 2017/4/27.
 */

public class ControlFragment extends MyBaseFragment{
    private ControlItem mWaterpump;
    private ControlItem mFen;
    private ControlItem mSunshade;
    private ControlItem mCurtain;
    private LinearLayout mLayout;
    private Spinner mSpinner;
    private Context mContext;
    private List<String> mDataPlace;
    private ArrayAdapter<String> mAdapterPlace;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = (LinearLayout) inflater.inflate(R.layout.layout_fragment_control,container,false);
        return mLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        initView();
        initData();
    }

    private void initData() {//获取数据，校准
        mWaterpump.calibration();
        mSunshade.calibration();
        mFen.calibration();
        mCurtain.calibration();
        mDataPlace = new ArrayList<>();
        mDataPlace.add("珠海鱼庄");
        mDataPlace.add("深圳牛庄");
        mAdapterPlace = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_dropdown_item,mDataPlace);
        mSpinner.setAdapter(mAdapterPlace);
    }

    private void initView(){
        mWaterpump = (ControlItem) mLayout.findViewById(R.id.control_water_pump);
        mFen = (ControlItem) mLayout.findViewById(R.id.control_draught_fan);
        mSunshade = (ControlItem) mLayout.findViewById(R.id.control_sunshade);
        mCurtain = (ControlItem) mLayout.findViewById(R.id.control_curtain);
        mSpinner = (Spinner) mLayout.findViewById(R.id.control_spinner);

        mWaterpump.setImgRes(R.drawable.u1051,R.drawable.u1049);
        mFen.setImgRes(R.drawable.u1054,R.drawable.u1056);
        mSunshade.setImgRes(R.drawable.u1059,R.drawable.u1061);
        mCurtain.setImgRes(R.drawable.u1090,R.drawable.u1088);
    }

}
