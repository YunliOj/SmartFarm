package com.fb.smartfarm.Module;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.fb.smartfarm.Module.Work.WarnMessageActivity;
import com.fb.smartfarm.Module.Work.WarningAdapter;
import com.fb.smartfarm.Module.Work.WarningBean;
import com.fb.smartfarm.R;
import com.fb.smartfarm.view.CustomView.BaseActivity;
import com.fb.smartfarm.view.CustomView.TopBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echo on 2017/5/1.
 */

public class WarningActivity extends BaseActivity implements AdapterView.OnItemClickListener ,TopBar.TopListener{
    private ListView mListView;
    private WarningAdapter mAdapter;
    private boolean isShowCb;
    private CheckBox mCheckAll;
    private List<WarningBean> list;
    private LinearLayout mChoose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTopBar.setCenterText(getString(R.string.message_list));
        mTopBar.setRightText(getString(R.string.clear));
        mTopBar.showRightText();
        mTopBar.showLeft();
        mTopBar.setLeftBgRes(R.drawable.u978);
        mTopBar.showLeft();
        LayoutInflater.from(this).inflate(R.layout.layout_warning_activity, mContentLayout, true);
        mChoose = (LinearLayout) findViewById(R.id.choose_layout);
        mListView = (ListView) mContentLayout.findViewById(R.id.warning_list);
        mCheckAll = (CheckBox) findViewById(R.id.check_all);

        list = new ArrayList<WarningBean>();
        WarningBean bean = new WarningBean();
        bean.setDate("2017/1/1");
        bean.setHardware("10039");
        bean.setPlace("深圳");
        bean.setState("已处理");
        WarningBean bean1 = new WarningBean();
        bean1.setDate("2017/1/3");
        bean1.setHardware("10012");
        bean1.setPlace("深圳");
        bean1.setState("已处理");
        WarningBean bean2 = new WarningBean();
        bean2.setDate("2017/1/2");
        bean2.setHardware("10040");
        bean2.setPlace("深圳");
        bean2.setState("已处理");
        list.add(bean);
        list.add(bean1);
        list.add(bean2);
        mAdapter = new WarningAdapter(this, list);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);

        mCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mAdapter.isCheckAll(true);
                }else {
                    mAdapter.isCheckAll(false);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        mTopBar.setListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(this, WarnMessageActivity.class);
        Bundle bundle = new Bundle();
        WarningBean bean = list.get(position);
        intent.putExtra("date",bean.getDate());
        intent.putExtra("place",bean.getPlace());
        intent.putExtra("hardware",bean.getHardware());
        intent.putExtra("state",bean.getState());
        intent.putExtra("content",bean.getContent());
        intent.putExtra("advice",bean.getAdvice());
        
        startActivity(intent);
    }

    @Override
    public void clickLeft() {
        back();
    }

    private void back(){
        if(isShowCb){
            if(mAdapter!=null){
                mAdapter.isShowCheckBox(false);
                mTopBar.setCenterText(getString(R.string.message_list));
                mTopBar.setRightText(getString(R.string.clear));
                mCheckAll.setVisibility(View.GONE);
            }
            mChoose.setVisibility(View.VISIBLE);
            isShowCb = false;
        }else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void clickRight() {
        if(!isShowCb){
            if(mAdapter!=null){
                mChoose.setVisibility(View.GONE);
                mAdapter.isShowCheckBox(true);
                mTopBar.setCenterText(getString(R.string.clear_record));
                mTopBar.setRightText(getString(R.string.confirm));
                mCheckAll.setVisibility(View.VISIBLE);
            }
            isShowCb = true;
        }else {
            list.removeAll(mAdapter.getRemoveList());
            mAdapter.notifyDataSetChanged();
            if(list.size() == 0){
                mCheckAll.setVisibility(View.INVISIBLE);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTopBar.removeListener();
    }
}
