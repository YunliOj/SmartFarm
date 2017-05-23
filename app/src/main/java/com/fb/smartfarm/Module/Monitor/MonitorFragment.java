package com.fb.smartfarm.Module.Monitor;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.smartfarmfengbo.smartfarm.R;
import com.fb.smartfarm.view.CustomView.MyBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 2017/4/29.
 */

public class MonitorFragment extends MyBaseFragment{
    private ScrollView mScrollView;
    private Context mContext;

    private DisplayMetrics dm;
    private PopupWindow popWindow;
    private SelectAdapter moreAdapter;
    private ListView morelist;
    private Button tv;
    private List<String> mainList;
    private ListView mainlist;
    private Button btnPlay;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScrollView = (ScrollView) inflater.inflate(R.layout.layout_fragment_monitor,container,false);
        return mScrollView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity();
        btnPlay = (Button) getActivity().findViewById(R.id.play);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"播放",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
