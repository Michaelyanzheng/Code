package com.zheng.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Michael on 2015/8/1.
 */
public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.zheng.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.zheng.geoquiz.answer_show";

    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;

    private Button mShowAnswerButton;

    private boolean isSave_AnswerShow = false;
    private static final String IS_CHEAT_ANSWER = "isCheatAnswer";

    private boolean isSave_TextShow = false;
    private static final String IS_CHEAT_TEXT = "isCheatText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

        if (savedInstanceState != null){
            isSave_AnswerShow = savedInstanceState.getBoolean(IS_CHEAT_ANSWER,false);
            isSave_TextShow = savedInstanceState.getBoolean(IS_CHEAT_TEXT,false);
        }

        setAnswerShowResult(isSave_AnswerShow);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        if (isSave_TextShow){
            showAnswerText();
        }

        mShowAnswerButton = (Button) findViewById(R.id.showAnswerButton);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnswerText();
                setAnswerShowResult(true);
                isSave_AnswerShow = true;
                isSave_TextShow = true;
            }
        });
    }

    private void showAnswerText(){
        if (mAnswerIsTrue){
            mAnswerTextView.setText(R.string.true_button);
        }else{
            mAnswerTextView.setText(R.string.false_button);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putBoolean(IS_CHEAT_ANSWER, isSave_AnswerShow);
        outState.putBoolean(IS_CHEAT_TEXT, isSave_TextShow);

        super.onSaveInstanceState(outState);
    }

    public void setAnswerShowResult(boolean isAnswerShow){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShow);
        setResult(RESULT_OK,data);
    }
}















