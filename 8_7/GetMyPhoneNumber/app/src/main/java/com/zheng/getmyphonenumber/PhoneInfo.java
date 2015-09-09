package com.zheng.getmyphonenumber;

/**
 * Created by michael on 2015/8/7.
 */
public class PhoneInfo {

    private String mPhoneName;
    private String mPhoneNumber;

    public PhoneInfo(String phoneName, String phoneNumber) {
        mPhoneName = phoneName;
        mPhoneNumber = phoneNumber;
    }

    public String getPhoneName() {
        return mPhoneName;
    }

    public void setPhoneName(String phoneName) {
        mPhoneName = phoneName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }
}
