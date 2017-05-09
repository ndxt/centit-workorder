package com.centit.workorder.po;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
	@GenericGenerator(name = "assignedGenerator", strategy = "uuid")
	private String questionId;

	/**
	 * 类别ID null 
	 */
	@Column(name = "CATALOG_ID")
	@Length(max = 32, message = "字段长度不能大于{max}")
	private QuestionCatalog  questionCatalog;
	/**
	 * 业务系统ID null 
	 */
	@Column(name = "OS_ID")
	@NotBlank(message = "字段不能为空")
	@Length(max = 20, message = "字段长度不能大于{max}")
	private String  osId;
	/**
	 * 用户代码 null 
	 */
	@Column(name = "User_Code")
	@Length(max = 32, message = "字段长度不能大于{max}")
	private String  userCode;
	/**
	 * 用户名称 null 
	 */
	@Column(name = "USER_NAME")
	@Length(max = 64, message = "字段长度不能大于{max}")
	private String  userName;
	/**
	 * 问题标题 null 
	 */
	@Column(name = "QUESTION_TITLE")
	@NotBlank(message = "字段不能为空")
	@Length(max = 500, message = "字段长度不能大于{max}")
	private String  questionTitle;
	/**
	 * 问题内容 null 
	 */
	@Column(name = "QUESTION_CONTENT")
	@NotBlank(message = "字段不能为空")
	private String  questionContent;
	/**
	 * 问题状态 N : 正常  C: 关闭 
	 */
	@Column(name = "QUESTION_STATE")
	@Length(max = 0, message = "字段长度不能大于{max}")
	private String  questionState;
	/**
	 * 创建时间 null 
	 */
	@Column(name = "create_time")
	private Date  createTime;
	/**
	 * 编辑状态 N: 未编辑  U: 已被编辑 
	 */
	@Column(name = "edit_state")
	@Length(max = 0, message = "字段长度不能大于{max}")
	private String  editState;
	/**
	 * 编辑时间 null 
	 */
	@Column(name = "last_update_time")
	private Date  lastUpdateTime;

	/**
	 *责任人
	 */
	@Column(name="CURRENT_OPERATOR")
	private String currentOperator;

	@OneToMany(mappedBy = "questionInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<QuestionRound> questionRounds;

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
			,QuestionCatalog  questionCatalog,String  osId,String  userCode,String  userName,String  questionTitle,String  questionContent,String  questionState,Date  createTime,String  editState,Date  lastUpdateTime, String currentOperator) {


		this.questionId = questionId;

		this.questionCatalog= questionCatalog;
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
	}



	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	// Property accessors

	public QuestionCatalog getQuestionCatalog() {
		return this.questionCatalog;
	}

	public void setQuestionCatalog(QuestionCatalog questionCatalog) {
		this.questionCatalog = questionCatalog;
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

	public Set<QuestionRound> getQuestionRounds(){
		if(this.questionRounds==null)
			this.questionRounds = new HashSet<QuestionRound>();
		return this.questionRounds;
	}

	public void setQuestionRounds(Set<QuestionRound> questionRounds) {
		this.questionRounds = questionRounds;
	}

	public void addQuestionRound(QuestionRound questionRound ){
		if (this.questionRounds==null)
			this.questionRounds = new HashSet<QuestionRound>();
		this.questionRounds.add(questionRound);
	}

	public void removeQuestionRound(QuestionRound questionRound ){
		if (this.questionRounds==null)
			return;
		this.questionRounds.remove(questionRound);
	}

	public QuestionRound newQuestionRound(){
		QuestionRound res = new QuestionRound();

		res.setQuestionInfo(this);

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 *
	 */
	public void replaceQuestionRounds(Set<QuestionRound> questionRounds) {
		Set<QuestionRound> newObjs = new HashSet<QuestionRound>();
		for(QuestionRound p :questionRounds){
			if(p==null)
				continue;
			QuestionRound newdt = newQuestionRound();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<QuestionRound> oldObjs = new HashSet<QuestionRound>();
		oldObjs.addAll(getQuestionRounds());

		for(Iterator<QuestionRound> it=oldObjs.iterator(); it.hasNext();){
			QuestionRound odt = it.next();
			found = false;
			for(QuestionRound newdt :newObjs){
				if(odt.getRoundId().equals( newdt.getRoundId())){
					found = true;
					break;
				}
			}
			if(! found)
				removeQuestionRound(odt);
		}
		oldObjs.clear();
		//insert or update
		for(QuestionRound newdt :newObjs){
			found = false;
			for(Iterator<QuestionRound> it=getQuestionRounds().iterator();
				it.hasNext();){
				QuestionRound odt = it.next();
				if(odt.getRoundId().equals( newdt.getRoundId())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addQuestionRound(newdt);
		}
	}


	public QuestionInfo copy(QuestionInfo other){

		this.setQuestionId(other.getQuestionId());

		this.questionCatalog= other.getQuestionCatalog();
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

		this.questionRounds = other.getQuestionRounds();
		return this;
	}

	public QuestionInfo copyNotNullProperty(QuestionInfo other){

		if( other.getQuestionId() != null)
			this.setQuestionId(other.getQuestionId());

		if( other.getQuestionCatalog() != null)
			this.questionCatalog= other.getQuestionCatalog();
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

		//this.questionRounds = other.getQuestionRounds();
		replaceQuestionRounds(other.getQuestionRounds());

		return this;
	}

	public QuestionInfo clearProperties(){

		this.questionCatalog= null;
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

		this.questionRounds = new HashSet<QuestionRound>();
		return this;
	}
}
