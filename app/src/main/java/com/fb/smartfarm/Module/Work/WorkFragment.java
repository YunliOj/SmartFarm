package com.fb.smartfarm.Module.Work;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.testwape.SwipeMenu;
import com.example.testwape.SwipeMenuCreator;
import com.example.testwape.SwipeMenuItem;
import com.example.testwape.SwipeMenuListView;
import com.fb.smartfarm.R;
import com.fb.smartfarm.UtilsTools.LogUtil;
import com.fb.smartfarm.view.CustomView.MyBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 2017/4/29.
 */

public class WorkFragment extends MyBaseFragment implements AdapterView.OnItemClickListener{
    private final String TAG = WorkFragment.class.getSimpleName();
    private LinearLayout mLayout;
    private List<WorkBean> mlist;
    private WorkAdapter mAdapter;
    private Context mContext;
    private SwipeMenuListView mListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLayout = (LinearLayout) inflater.inflate(R.layout.layout_fragment_work,container,false);
        return mLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mContext = getActivity();
        initView();
    }

    private void initView() {
        mListView = (SwipeMenuListView) mLayout.findViewById(R.id.work_list);

        WorkBean b = new WorkBean();
        b.setWorkType("芒果");
        b.setmDate("2017/4/2");
        b.setmMedicine("100031");
        b.setWorkCrop("芒果");
        b.setWorkPlace("芒果地");
        b.setmContent("又大又甜");
        WorkBean b1 = new WorkBean();
        b1.setWorkType("西瓜");
        b1.setmDate("2017/4/3");
        b1.setmMedicine("100033");
        b1.setWorkCrop("西瓜");
        b1.setWorkPlace("西瓜地");
        b1.setmContent("又大又甜");
        WorkBean b2 = new WorkBean();
        b2.setWorkType("香蕉");
        b2.setmDate("2017/4/4");
        b2.setmMedicine("100031");
        b2.setWorkCrop("香蕉");
        b2.setWorkPlace("香蕉地");
        b2.setmContent("又大又香");
        WorkBean b3 = new WorkBean();
        b3.setWorkType("苹果");
        b3.setmDate("2017/4/6");
        b3.setmMedicine("100031");
        b3.setWorkCrop("苹果");
        b3.setWorkPlace("苹果地");
        b3.setmContent("又大又甜");
        WorkBean b4 = new WorkBean();
        b4.setWorkType("苹果");
        b4.setmDate("2017/4/6");
        b4.setmMedicine("100031");
        b4.setWorkCrop("苹果");
        b4.setWorkPlace("苹果地");
        b4.setmContent("又大又甜");
        WorkBean b5 = new WorkBean();
        b5.setWorkType("哈哈果");
        b5.setmDate("2017/4/6");
        b5.setmMedicine("100031");
        b5.setWorkCrop("哈哈");
        b5.setWorkPlace("哈哈果地");
        b5.setmContent("又大又甜");
        mlist = new ArrayList<>();
        mlist.add(b);
        mlist.add(b1);
        mlist.add(b2);
        mlist.add(b3);
        mlist.add(b4);
        mlist.add(b5);
        mAdapter = new WorkAdapter(getActivity(),mlist);
        mListView.setAdapter(mAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        mContext);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(com.example.testwape.R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        mlist.remove(position);
                        mAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });

        mListView.setOnItemClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LogUtil.d(TAG,"onItemClick");
        Intent intent = new Intent();
        intent.setClass(mContext,WorkRecordActivity.class);
        startActivity(intent);
    }
}
