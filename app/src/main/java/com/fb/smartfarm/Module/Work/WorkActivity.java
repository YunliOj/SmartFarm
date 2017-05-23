package com.fb.smartfarm.Module.Work;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fb.smartfarm.R;
import com.fb.smartfarm.UtilsTools.LogUtil;
import com.fb.smartfarm.view.CustomView.BaseActivity;
import com.fb.smartfarm.view.CustomView.TopBar;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFImageView;

/**
 * Created by echo on 2017/4/30.
 */

    public class WorkActivity extends BaseActivity implements View.OnClickListener ,TopBar.TopListener ,DatePicker.OnDateChangedListener{
    private final String TAG = WorkActivity.class.getSimpleName();
    private TextView mEdDate;
    private EditText mEdFertilizer;
    private EditText mEdResidual;
    private EditText mEdDosage;
    private PopupWindow popupWindow;

    private Spinner mPlaceSpinner;
    private Spinner mCropSpinner;
    private ImageButton mBtnAddPhoto;
    private Button mBtnFertilize;
    private Button mBtnSprayInsecticide;
    private Button mBtnWatering;
    private Button mBtnWeeding;
    private Button mBtnPruning;
    private WindowManager windowManager;
    private boolean isAdd;

    private int mYear;
    private int mMonth;
    private int mday;

    private ImageView one;
    private ImageView two;
    private ImageView three;
    private ImageView four;


    private Button delOne;
    private Button delTwo;
    private Button delThree;
    private Button delFour;

    private LinearLayout windowlayout;
    private final int REQUEST_CODE_GALLERY = 100;
    private final int REQUEST_CODE_CAMERA = 101;
    private ImageView[] imgs;
    private Button[] dels;
    private int photoCount = 4;
    private static int activityType;
    private final int NEW_DATA = 0;
    private final int UPDATE_DATA = 1;
    private GalleryFinal.OnHanlderResultCallback callback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            photoCount = photoCount - resultList.size();
           for (int i = 0;i<resultList.size();i++){
               PhotoInfo info = resultList.get(i);
               LogUtil.d("","path :: "+info.getPhotoPath());
               File file = new File(info.getPhotoPath());
               if(file.exists()){
                   Bitmap bm = BitmapFactory.decodeFile(info.getPhotoPath());
                   imgs[i].setImageBitmap(bm);
                   if(getIntent().getAction().equals("editor")){
                       dels[i].setVisibility(View.VISIBLE);
                   }
               }
           }
            if(isAdd){
                windowManager.removeView(windowlayout);
                isAdd = false;
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            LogUtil.d("","onHanlderFailure");
            if(isAdd){
                windowManager.removeView(windowlayout);
                isAdd = false;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String ac = getIntent().getAction();
        if(!TextUtils.isEmpty(ac)){
            if(ac.equals("editor")){
                mTopBar.setCenterText("编辑工作记录");
                activityType = UPDATE_DATA;
            }else if(ac.equals("new")){
                mTopBar.setCenterText("新增工作记录");
                activityType = NEW_DATA;
            }
        }

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mday = c.get(Calendar.DAY_OF_MONTH);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        mTopBar.setRightText("保存");
        mTopBar.showLeft();
        mTopBar.setLeftBgRes(R.drawable.u978);
        mTopBar.showRightText();
        LayoutInflater.from(this).inflate(R.layout.layout_new_work_record_activity,mContentLayout,true);
        mEdDate = (TextView) mContentLayout.findViewById(R.id.ed_new_work_date);
        mEdDosage = (EditText) mContentLayout.findViewById(R.id.de_new_work_dosage);
        mEdResidual = (EditText) mContentLayout.findViewById(R.id.ed_new_work_residual);
        mEdFertilizer = (EditText) mContentLayout.findViewById(R.id.ed_new_work_fertilization);

        mPlaceSpinner = (Spinner) findViewById(R.id.spinner_new_work_choose_place);
        mCropSpinner = (Spinner) findViewById(R.id.spinner_new_work_choose_crop);
        mBtnAddPhoto = (ImageButton) findViewById(R.id.btn_new_work_add_photo);

        mBtnFertilize = (Button) findViewById(R.id.btn_new_work_fertilization);
        mBtnSprayInsecticide = (Button) findViewById(R.id.btn_new_work_spray_insecticide);
        mBtnWatering = (Button) findViewById(R.id.btn_new_work_watering);
        mBtnWeeding = (Button) findViewById(R.id.btn_new_work_weeding);
        mBtnPruning = (Button) findViewById(R.id.btn_new_work_pruning);

        mBtnWeeding.setOnClickListener(this);
        mBtnFertilize.setOnClickListener(this);
        mBtnSprayInsecticide.setOnClickListener(this);
        mBtnPruning.setOnClickListener(this);
        mBtnWatering.setOnClickListener(this);

        mBtnAddPhoto.setOnClickListener(this);

        delOne = (Button) findViewById(R.id.btn_del_one);
        delTwo = (Button) findViewById(R.id.btn_del_two);
        delThree = (Button) findViewById(R.id.btn_del_three);
        delFour = (Button) findViewById(R.id.btn_del_four);

        mTopBar.setListener(this);
        mEdDate.setOnClickListener(this);

        one = (ImageView) findViewById(R.id.img_photo_one);
        two = (ImageView) findViewById(R.id.img_photo_two);
        three = (ImageView) findViewById(R.id.img_photo_three);
        four = (ImageView) findViewById(R.id.img_photo_four);
        imgs = new ImageView[]{one,two,three,four};
        dels = new Button[]{delOne,delTwo,delThree,delFour};


        delOne.setOnClickListener(this);
        delTwo.setOnClickListener(this);
        delThree.setOnClickListener(this);
        delFour.setOnClickListener(this);

        ThemeConfig theme = new ThemeConfig.Builder().build();
        FunctionConfig functionConfig = new FunctionConfig.Builder().
                setEnableCamera(true).setEnableEdit(true).setEnableRotate(true).build();
        ImageLoader imageLoader = new ImageLoader() {
            @Override
            public void displayImage(Activity activity, String path, GFImageView imageView, Drawable defaultDrawable, int width, int height) {

            }

            @Override
            public void clearMemoryCache() {

            }
        };

        CoreConfig coreConfig = new CoreConfig.Builder(this,imageLoader,theme).setFunctionConfig(functionConfig).build();
        GalleryFinal.init(coreConfig);
    }


    private void selectWorkType(int type){
        switch (type){
            case R.id.btn_new_work_fertilization:
                mBtnFertilize.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnWatering.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnPruning.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnSprayInsecticide.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnWeeding.setBackground(getDrawable(R.mipmap.round_orange));
                break;
            case R.id.btn_new_work_spray_insecticide:
                mBtnFertilize.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnWatering.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnPruning.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnSprayInsecticide.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnWeeding.setBackground(getDrawable(R.mipmap.round_orange));
                break;
            case R.id.btn_new_work_watering:
                mBtnFertilize.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnWatering.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnPruning.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnSprayInsecticide.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnWeeding.setBackground(getDrawable(R.mipmap.round_orange));
                break;
            case R.id.btn_new_work_weeding:
                mBtnFertilize.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnWatering.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnPruning.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnSprayInsecticide.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnWeeding.setBackground(getDrawable(R.mipmap.round_orange));
                break;
            case R.id.btn_new_work_pruning:
                mBtnFertilize.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnWatering.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnPruning.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnSprayInsecticide.setBackground(getDrawable(R.mipmap.round_orange));
                mBtnWeeding.setBackground(getDrawable(R.mipmap.round_orange));
                break;
        }
    }

    private void getPhotoWindow(){
        windowlayout = new LinearLayout(this);
        windowlayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_add_photo,windowlayout,true);
        Button choose = (Button) windowlayout.findViewById(R.id.choose_photo);
        Button take = (Button) windowlayout.findViewById(R.id.take_photo);
        Button exit = (Button) windowlayout.findViewById(R.id.exit);

        choose.setOnClickListener(this);
        take.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    private WindowManager.LayoutParams getWindowParams(){
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250, this.getResources().getDisplayMetrics());

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = height;
        lp.gravity = Gravity.BOTTOM;
        return lp;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_new_work_add_photo:
                if (isAdd)
                    return;
                if(windowlayout == null){
                    getPhotoWindow();
                }
                windowManager.addView(windowlayout,getWindowParams());
                isAdd = true;
                break;
            case R.id.btn_new_work_fertilization:
                LogUtil.d(TAG,"btn_new_work_fertilization");
                break;
            case R.id.btn_new_work_pruning:
                LogUtil.d(TAG,"btn_new_work_pruning");
                break;
            case R.id.btn_new_work_spray_insecticide:
                LogUtil.d(TAG,"btn_new_work_spray_insecticide");
                break;
            case R.id.btn_new_work_watering:
                LogUtil.d(TAG,"btn_new_work_watering");
                break;
            case R.id.btn_new_work_weeding:
                LogUtil.d(TAG,"btn_new_work_weeding");
                break;
            case R.id.ed_new_work_date:
                startDateChoose();
                break;
            case R.id.tv_compele_date_choose:
                if(popupWindow!=null){
                    popupWindow.dismiss();
                }
                break;
            case R.id.take_photo:
                if(photoCount <= 0){
                    Toast.makeText(WorkActivity.this,"最多添加4张图片",Toast.LENGTH_SHORT).show();
                    if(isAdd){
                        windowManager.removeView(windowlayout);
                        isAdd = false;
                    }
                    return;
                }
                LogUtil.d("123","take photo");
                GalleryFinal.openCamera(REQUEST_CODE_CAMERA,callback);
                break;
            case R.id.choose_photo:
                if(photoCount <= 0){
                    Toast.makeText(WorkActivity.this,"最多添加4张图片",Toast.LENGTH_SHORT).show();
                    if(isAdd){
                        windowManager.removeView(windowlayout);
                        isAdd = false;
                    }
                    return;
                }
                LogUtil.d("123","choose photo");
                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY,4,callback);
                break;
            case R.id.exit:
                LogUtil.d("123","exit");
                if(isAdd){
                    windowManager.removeView(windowlayout);
                    isAdd = false;
                }

                break;

            case R.id.btn_del_one:
                imgs[0].setWillNotDraw(true);
                delOne.setVisibility(View.GONE);
                break;
            case R.id.btn_del_two:
                imgs[1].setWillNotDraw(true);
                delTwo.setVisibility(View.GONE);
                break;
            case R.id.btn_del_three:
                imgs[2].setWillNotDraw(true);
                delThree.setVisibility(View.GONE);
                break;
            case R.id.btn_del_four:
                imgs[3].setWillNotDraw(true);
                delFour.setVisibility(View.GONE);
                break;
        }
    }

    private void startDateChoose(){
        View view = getLayoutInflater().inflate(R.layout.layout_date_choose,null,true);
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 360, this.getResources().getDisplayMetrics());
        TextView compele = (TextView) view.findViewById(R.id.tv_compele_date_choose);
        compele.setOnClickListener(this);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,height,true);
        popupWindow.setTouchable(true);
        DatePicker picker = (DatePicker) view.findViewById(R.id.date_picker);
        picker.init(mYear,mMonth,mday,this);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(mEdDate,0,60);


    }

    @Override
    public void clickLeft() {
        finish();
    }

    @Override
    public void clickRight() {
        Toast.makeText(this,"保存",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mEdDate.setText(year+"/"+monthOfYear+"/"+dayOfMonth);
        LogUtil.d("qw","onDateChanged");
    }
}
