package com.fb.smartfarm.view.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.fb.smartfarm.Module.Monitor.SelectAdapter;
import com.fb.smartfarm.R;
import com.fb.smartfarm.UtilsTools.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by echo on 2017/5/13.
 */

public class DoubleListView extends LinearLayout {
    private Context mContext;
    private final String TAG = DoubleListView.class.getSimpleName();
    private ListView mFirstListView;
    private ListView mSecondListView;
    private SelectAdapter mFirstAdapter;
    private SelectAdapter mSecondAdapter;
    private HashMap<String,String[]> mDataMap;
    private List<String> mFirstList;
    private List<List<String>> mSecondList;
    private int mCurrIndex = 0;
    private OnPlaceChangeListener mListener;
    public DoubleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_double_list, this, true);
        mFirstListView = (ListView) findViewById(R.id.double_first);
        mSecondListView = (ListView) findViewById(R.id.double_second);
        mFirstList = new ArrayList<>();
        mSecondList = new ArrayList<>();
        mFirstListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        mFirstListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.d(TAG,"position :: "+position);
                for (int i = 0;i<mSecondList.get(position).size();i++){
                    LogUtil.d(TAG,"get :: "+mSecondList.get(position).get(i));
                    mCurrIndex = position;
                    mFirstListView.setSelection(position);
                }
                mFirstAdapter.setCurrSeletor(position);
                mSecondAdapter.resetData(mSecondList.get(position));
            }
        });

        mSecondListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,mSecondList.get(mCurrIndex).get(position),Toast.LENGTH_SHORT).show();
                mSecondAdapter.setCurrSeletor(position);
                if(mListener!=null){
                    mListener.onChange(mSecondList.get(mCurrIndex).get(position));
                }
            }
        });
    }

    public void removeListener(){
        mListener = null;
    }

    public interface OnPlaceChangeListener{
        void onChange(String s);
    }

    public void setOnPlaceChangerListener(OnPlaceChangeListener listener){
        this.mListener = listener;
    }

    public void setDoubleData(HashMap map) {
        mDataMap = map;
        Iterator iter = mDataMap.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            mFirstList.add(entry.getKey().toString());
            mSecondList.add((List<String>) entry.getValue());
            LogUtil.d(TAG,"setDoubleData"+entry.getKey());
        }
        mFirstAdapter = new SelectAdapter(mContext,mFirstList);
        mFirstListView.setAdapter(mFirstAdapter);

        mSecondAdapter = new SelectAdapter(mContext,mSecondList.get(0));
        mSecondListView.setAdapter(mSecondAdapter);
    }
}
