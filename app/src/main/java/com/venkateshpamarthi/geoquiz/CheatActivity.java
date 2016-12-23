package com.venkateshpamarthi.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String TAG = "CheatActivity";
    private static final String EXTRA_ANSWER_IS_TRUE = "com.venkateshpamarthi.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.venkateshpamarthi.geoquiz.answer_shown";
    private static final String KEY_ANSWER_SHOWN = "answer_shown";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;
    private TextView mSdkVersionTextView;
    private boolean mIsAnswerShown = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        if(getIntent().hasExtra(EXTRA_ANSWER_IS_TRUE)){
            mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        }
        if(savedInstanceState != null){
            mIsAnswerShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN,false);
            setAnswerShownResult(mIsAnswerShown);
        }
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mSdkVersionTextView = (TextView) findViewById(R.id.sdk_version_text_view);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                mIsAnswerShown = true;
                setAnswerShownResult(mIsAnswerShown);
                setShowAnswerWithCircularRevealAnimationViaSdkVersion();
            }
        });
        mSdkVersionTextView.setText(getString(R.string.api_level, Build.VERSION.SDK_INT));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_ANSWER_SHOWN,mIsAnswerShown);
    }

    private void setAnswerShownResult(boolean answerShownResult) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ANSWER_SHOWN,answerShownResult);
        setResult(RESULT_OK,intent);
    }
    private void setShowAnswerWithCircularRevealAnimationViaSdkVersion(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            int cx = mShowAnswerButton.getWidth() / 2;
            int cy = mShowAnswerButton.getHeight() / 2;
            float radius = mShowAnswerButton.getWidth();
            Animator anim = ViewAnimationUtils
                    .createCircularReveal(mShowAnswerButton, cx, cy, radius, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    mAnswerTextView.setVisibility(View.VISIBLE);
                    mShowAnswerButton.setVisibility(View.INVISIBLE);
                } });
            anim.start();
        }else{
            mAnswerTextView.setVisibility(View.VISIBLE);
            mShowAnswerButton.setVisibility(View.INVISIBLE);
        }
    }
}
