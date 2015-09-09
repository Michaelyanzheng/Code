package com.zheng.geoquiz;

/**
 * Created by Michael on 2015/7/20.
 */
public class TrueFalse {

    private int mQuestion;

    private boolean mTrueQuestion;

    private boolean mIsCheat;

    public TrueFalse(int question,boolean trueQuestion){
        mQuestion = question;
        mTrueQuestion = trueQuestion;
        mIsCheat = false;
    }

    public boolean isCheat() {
        return mIsCheat;
    }

    public void setIsCheat(boolean isCheat) {
        mIsCheat = isCheat;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }
}
