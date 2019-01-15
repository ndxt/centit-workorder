package com.centit.workorder.po;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created by zhang_gd on 2017/7/27.
 *
 * 协助处理人员
 */

@Entity
@Table(name = "F_ASSIST_OPERATOR")
public class AssistOperator implements java.io.Serializable {
    private static final long serialVersionUID =  1L;

    @Id
    @Column(name = "QUESTION_ID")
    @NotBlank(message = "字段不能为空")
    //@Length(max = 32, message = "字段长度不能大于{max}")
    private String questionId;
    /**
     * 协助处理人
     */
    @Id
    @Column(name = "OPERATOR_CODE")
    @NotBlank(message = "字段不能为空")
    //@Length(max = 32, message = "字段长度不能大于{max}")
    private String  operatorCode;
    /**
     * 加入时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;


    public AssistOperator(){}

    public AssistOperator(@NotBlank(message = "字段不能为空") String questionId, @NotBlank(message = "字段不能为空") String operatorCode) {
        this.questionId = questionId;
        this.operatorCode = operatorCode;
    }

    public AssistOperator(@NotBlank(message = "字段不能为空") String questionId, @NotBlank(message = "字段不能为空") String operatorCode,
            Date createDate){
        this.questionId = questionId;
        this.operatorCode = operatorCode;
        this.createDate = createDate;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
