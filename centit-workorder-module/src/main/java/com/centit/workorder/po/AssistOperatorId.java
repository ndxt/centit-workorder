package com.centit.workorder.po;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by zhang_gd on 2017/7/27.
 *  协助处理人员主键
 */
@Embeddable
public class AssistOperatorId implements java.io.Serializable {
    private static final long serialVersionUID =  1L;

    /**
     * 问题ID
     */
    @Column(name = "QUESTION_ID")
    @NotBlank(message = "字段不能为空")
    @Length(max = 32, message = "字段长度不能大于{max}")
    private String questionId;
    /**
     * 协助处理人
     */
    @Column(name = "OPERATOR_CODE")
    @NotBlank(message = "字段不能为空")
    @Length(max = 32, message = "字段长度不能大于{max}")
    private String  operatorCode;


    public AssistOperatorId(){}

    public AssistOperatorId(String questionId,String  operatorCode){
        this.questionId = questionId;
        this.operatorCode = operatorCode;

    }


    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

}
