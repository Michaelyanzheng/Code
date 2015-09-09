package com.zheng.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by michael on 2015/8/24.
 */
public class CrimeLab {

    private ArrayList<Crime> mCrimes;

    private static CrimeLab sCrimeLab;
    private Context mContext;

    private CrimeLab(Context context){

        mContext = context;
        mCrimes = new ArrayList<Crime>();

//        for (int i = 0 ; i < 100 ; i++){
//            Crime crime = new Crime();
//            crime.setTitle("crime * " + i);
//            mCrimes.add(crime);
//        }
    }

    public Crime getCrime(UUID id){

        for (Crime crime : mCrimes){
            if (crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }

    public static CrimeLab get(Context context){

        if (sCrimeLab == null){

            sCrimeLab = new CrimeLab(context.getApplicationContext());
        }

        return sCrimeLab;
    }

    public void addCrime(Crime crime){

        mCrimes.add(crime);
    }

    public ArrayList<Crime> getCrimes() {

        return mCrimes;
    }

    public void setCrimes(ArrayList<Crime> crimes) {

        mCrimes = crimes;
    }
}
