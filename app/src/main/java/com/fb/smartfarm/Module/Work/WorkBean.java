package com.fb.smartfarm.Module.Work;

/**
 * Created by echo on 2017/4/29.
 */

public class WorkBean {
    private String mWorkType;
    private String mWorkPlace;
    private String mWorkCrop;
    private String mMedicine;
    private String mDate;
    private String mContent;

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public WorkBean(){}
    public String getWorkType() {
        return mWorkType;
    }

    public void setWorkType(String mWorkType) {
        this.mWorkType = mWorkType;
    }

    public String getWorkPlace() {
        return mWorkPlace;
    }

    public void setWorkPlace(String mWorkPlace) {
        this.mWorkPlace = mWorkPlace;
    }

    public String getWorkCrop() {
        return mWorkCrop;
    }

    public void setWorkCrop(String mWorkCrop) {
        this.mWorkCrop = mWorkCrop;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmMedicine() {
        return mMedicine;
    }

    public void setmMedicine(String mMedicine) {
        this.mMedicine = mMedicine;
    }
}
