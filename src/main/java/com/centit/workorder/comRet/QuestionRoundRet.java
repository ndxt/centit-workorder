package com.centit.workorder.comRet;

import com.centit.workorder.po.QuestionInfo;
import com.centit.workorder.po.QuestionRound;

import java.util.List;

/**
 * Created by zhang_gd on 2017/5/15.
 */
public class QuestionRoundRet {
    private QuestionInfo questionInfo;
    private List<QuestionRound> questionRoundList;

    public QuestionInfo getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(QuestionInfo questionInfo) {
        this.questionInfo = questionInfo;
    }

    public List<QuestionRound> getQuestionRoundList() {
        return questionRoundList;
    }

    public void setQuestionRoundList(List<QuestionRound> questionRoundList) {
        this.questionRoundList = questionRoundList;
    }
}
