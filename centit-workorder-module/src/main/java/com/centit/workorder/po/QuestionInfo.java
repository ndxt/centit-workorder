package com.centit.workorder.po;

import com.centit.support.database.orm.GeneratorType;
import com.centit.support.database.orm.ValueGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * create by scaffold 2017-05-08
 * @author codefan@sina.com

系统问题列表null
 */
@Entity
@Table(name = "F_QUESTION_INFO")
public class QuestionInfo implements java.io.Serializable {
    private static final long serialVersionUID =  1L;

    /**
     * 问题ID null
     */
    @Id
    @Column(name = "QUESTION_ID")
    @GeneratedValue(generator = "assignedGenerator")
    @ValueGenerator(strategy = GeneratorType.UUID)
    private String questionId;
    /**
     * 类别ID null
     */
    @Column(name = "CATALOG_ID")
    //@Length(max = 32, message = "字段长度不能大于{max}")
    private String  catalogId;
    /**
     * 业务系统ID null
     */
    @Column(name = "OS_ID")
    @NotBlank(message = "字段不能为空")
    //@Length(max = 20, message = "字段长度不能大于{max}")
    private String  osId;
    /**
     * 用户代码 null
     */
    @Column(name = "USER_CODE")
    //@Length(max = 32, message = "字段长度不能大于{max}")
    private String  userCode;
    /**
     * 用户名称 null
     */
    @Column(name = "USER_NAME")
    //@Length(max = 64, message = "字段长度不能大于{max}")
    private String  userName;
    /**
     * 问题标题 null
     */
    @Column(name = "QUESTION_TITLE")
    @NotBlank(message = "字段不能为空")
    //@Length(max = 500, message = "字段长度不能大于{max}")
    private String  questionTitle;
    /**
     * 问题内容 null
     */
    @Column(name = "QUESTION_CONTENT")
    private String  questionContent;
    /**
     * 问题状态：U:未处理，H:处理状态，R:回复，C:关闭
     */
    @Column(name = "QUESTION_STATE")
    //@Length(max = 1, message = "字段长度不能大于{max}")
    private String  questionState;
    /**
     * 创建时间 null
     */
    @Column(name = "CREATE_TIME")
    private Date  createTime;
    /**
     * 编辑状态 N: 未编辑  U: 已被编辑
     */
    @Column(name = "EDIT_STATE")
    //@Length(max = 1, message = "字段长度不能大于{max}")
    private String  editState;
    /**
     * 编辑时间 null
     */
    @Column(name = "LAST_UPDATE_TIME")
    private Date  lastUpdateTime;
    /**
     *受理时间
     */
    @Column(name = "ACCEPT_TIME")
    private Date  acceptTime;
    /**
     *处理完成时间
     */
    @Column(name = "COMPLETE_TIME")
    private Date  completeTime;
    /**
     *关闭时间
     */
    @Column(name = "CLOSED_TIME")
    private Date  closedTime;
    /**
     *问题评价
     */
    @Column(name = "EVALUATE_SCORE")
    private int  evaluateScore;
    /**
     *评价时间
     */
    @Column(name = "EVALUATE_TIME")
    private Date  evaluateTime;
    /**
     *责任人
     */
    @Column(name="CURRENT_OPERATOR")
    private String currentOperator;

    // Constructors
    /** default constructor */
    public QuestionInfo() {
    }
    /** minimal constructor */
    public QuestionInfo(
            String questionId
            ,String  osId,String  questionTitle,String  questionContent) {
        this.questionId = questionId;
        this.osId= osId;
        this.questionTitle= questionTitle;
        this.questionContent= questionContent;
    }

    /** full constructor */
    public QuestionInfo(
            String questionId
            ,String  catalogId,String  osId,String  userCode,String  userName,String  questionTitle,String  questionContent,String  questionState,Date  createTime,String  editState,Date  lastUpdateTime, String currentOperator,
            Date acceptTime,Date completeTime,Date closedTime,int evaluateScore,Date evaluateTime) {
        this.questionId = questionId;
        this.catalogId= catalogId;
        this.osId= osId;
        this.userCode= userCode;
        this.userName= userName;
        this.questionTitle= questionTitle;
        this.questionContent= questionContent;
        this.questionState= questionState;
        this.createTime= createTime;
        this.editState= editState;
        this.lastUpdateTime= lastUpdateTime;
        this.currentOperator = currentOperator;
        this.acceptTime= acceptTime;
        this.completeTime= completeTime;
        this.closedTime= closedTime;
        this.evaluateScore= evaluateScore;
        this.evaluateTime= evaluateTime;
    }



    public String getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getCatalogId() {
        return this.catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getOsId() {
        return this.osId;
    }

    public void setOsId(String osId) {
        this.osId = osId;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuestionTitle() {
        return this.questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return this.questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionState() {
        return this.questionState;
    }

    public void setQuestionState(String questionState) {
        this.questionState = questionState;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEditState() {
        return this.editState;
    }

    public void setEditState(String editState) {
        this.editState = editState;
    }

    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getCurrentOperator() {
        return currentOperator;
    }

    public void setCurrentOperator(String currentOperator) {
        this.currentOperator = currentOperator;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Date getClosedTime() {
        return closedTime;
    }

    public void setClosedTime(Date closedTime) {
        this.closedTime = closedTime;
    }

    public int getEvaluateScore() {
        return evaluateScore;
    }

    public void setEvaluateScore(int evaluateScore) {
        this.evaluateScore = evaluateScore;
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }



    public QuestionRound newQuestionRound(){
        QuestionRound res = new QuestionRound();
        return res;
    }

    public QuestionInfo copy(QuestionInfo other){
        this.setQuestionId(other.getQuestionId());
        this.catalogId= other.getCatalogId();
        this.osId= other.getOsId();
        this.userCode= other.getUserCode();
        this.userName= other.getUserName();
        this.questionTitle= other.getQuestionTitle();
        this.questionContent= other.getQuestionContent();
        this.questionState= other.getQuestionState();
        this.createTime= other.getCreateTime();
        this.editState= other.getEditState();
        this.lastUpdateTime= other.getLastUpdateTime();
        this.currentOperator= other.getCurrentOperator();
        this.acceptTime= other.getAcceptTime();
        this.completeTime= other.getCompleteTime();
        this.closedTime= other.getClosedTime();
        this.evaluateScore= other.getEvaluateScore();
        this.evaluateTime= other.getEvaluateTime();
        return this;
    }

    public QuestionInfo copyNotNullProperty(QuestionInfo other){
        if( other.getQuestionId() != null)
            this.setQuestionId(other.getQuestionId());
        if( other.getCatalogId() != null)
            this.catalogId= other.getCatalogId();
        if( other.getOsId() != null)
            this.osId= other.getOsId();
        if( other.getUserCode() != null)
            this.userCode= other.getUserCode();
        if( other.getUserName() != null)
            this.userName= other.getUserName();
        if( other.getQuestionTitle() != null)
            this.questionTitle= other.getQuestionTitle();
        if( other.getQuestionContent() != null)
            this.questionContent= other.getQuestionContent();
        if( other.getQuestionState() != null)
            this.questionState= other.getQuestionState();
        if( other.getCreateTime() != null)
            this.createTime= other.getCreateTime();
        if( other.getEditState() != null)
            this.editState= other.getEditState();
        if( other.getLastUpdateTime() != null)
            this.lastUpdateTime= other.getLastUpdateTime();
        if(other.getCurrentOperator() != null)
            this.currentOperator = other.getCurrentOperator();
        if(other.getAcceptTime() != null)
            this.acceptTime = other.getAcceptTime();
        if(other.getCompleteTime() != null)
            this.completeTime = other.getCompleteTime();
        if(other.getClosedTime() != null)
            this.closedTime = other.getClosedTime();
        if(other.getEvaluateScore() != -1)
            this.evaluateScore = other.getEvaluateScore();
        if(other.getEvaluateTime() != null)
            this.evaluateTime = other.getEvaluateTime();
        return this;
    }

    public QuestionInfo clearProperties(){
        this.catalogId= null;
        this.osId= null;
        this.userCode= null;
        this.userName= null;
        this.questionTitle= null;
        this.questionContent= null;
        this.questionState= null;
        this.createTime= null;
        this.editState= null;
        this.lastUpdateTime= null;
        this.currentOperator= null;
        this.acceptTime= null;
        this.completeTime= null;
        this.closedTime= null;
        this.evaluateScore= -1;
        this.evaluateTime= null;
        return this;
    }
}
