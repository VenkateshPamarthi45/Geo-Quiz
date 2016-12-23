package com.venkateshpamarthi.geoquiz;

/**
 * Created by venkateshpamarthi on 22/12/16.
 */

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mIsCheated;

    /**
     * Instantiates a new Question.
     *
     * @param textResId  the text res id
     * @param answerTrue the answer true
     * @param isCheated  the is cheated
     */
    public Question(int textResId, boolean answerTrue, boolean isCheated) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mIsCheated = isCheated;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isCheated() {
        return mIsCheated;
    }

    public void setCheated(boolean cheated) {
        mIsCheated = cheated;
    }
}
