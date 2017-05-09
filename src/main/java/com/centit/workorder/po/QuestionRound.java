package com.centit.workorder.po;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;


/**
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 
  问题的交流null   
*/
@Entity
@Table(name = "F_QUESTION_ROUND")
public class QuestionRound implements java.io.Serializable {
	private static final long serialVersionUID =  1L;



	/**
	 * 交流ID null 
	 */
	@Id
	@Column(name = "ROUND_ID")
	@GeneratedValue(generator = "assignedGenerator")
	@GenericGenerator(name = "assignedGenerator", strategy = "uuid")
	private String roundId;

	/**
	 * 问题ID null 
	 */
	@Column(name = "QUESTION_ID")
	@Length(max = 32, message = "字段长度不能大于{max}")
	private QuestionInfo  questionInfo;
	/**
	 * 交流内容 null 
	 */
	@Column(name = "ROUND_CONTENT")
	@NotBlank(message = "字段不能为空")
	private String  roundContent;
	/**
	 * 编辑状态 N: 未编辑  U: 已被编辑 
	 */
	@Column(name = "edit_state")
	@Length(max = 0, message = "字段长度不能大于{max}")
	private String  editState;
	/**
	 * 交流状态 N: 未回复  C : 已回复 
	 */
	@Column(name = "ROUND_STATE")
	@Length(max = 0, message = "字段长度不能大于{max}")
	private String  roundState;
	/**
	 * 追问还是解答 追问还是解答 
	 */
	@Column(name = "Q_OR_A")
	@Length(max = 0, message = "字段长度不能大于{max}")
	private String  orA;
	/**
	 * 创建时间 null 
	 */
	@Column(name = "create_time")
	private Date  createTime;
	/**
	 * 编辑时间 null 
	 */
	@Column(name = "last_update_time")
	private Date  lastUpdateTime;
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

	// Constructors
	/** default constructor */
	public QuestionRound() {
	}
	/** minimal constructor */
	public QuestionRound(
		String roundId		
		,String  roundContent) {
	
	
		this.roundId = roundId;		
	
		this.roundContent= roundContent; 		
	}

/** full constructor */
	public QuestionRound(
	 String roundId		
	,QuestionInfo  questionInfo,String  roundContent,String  editState,String  roundState,String  orA,Date  createTime,Date  lastUpdateTime,String  userCode,String  userName) {
	
	
		this.roundId = roundId;		
	
		this.questionInfo= questionInfo;
		this.roundContent= roundContent;
		this.editState= editState;
		this.roundState= roundState;
		this.orA= orA;
		this.createTime= createTime;
		this.lastUpdateTime= lastUpdateTime;
		this.userCode= userCode;
		this.userName= userName;		
	}
	

  
	public String getRoundId() {
		return this.roundId;
	}

	public void setRoundId(String roundId) {
		this.roundId = roundId;
	}
	// Property accessors
  
	public QuestionInfo getQuestionInfo() {
		return this.questionInfo;
	}
	
	public void setQuestionInfo(QuestionInfo questionInfo) {
		this.questionInfo = questionInfo;
	}
  
	public String getRoundContent() {
		return this.roundContent;
	}
	
	public void setRoundContent(String roundContent) {
		this.roundContent = roundContent;
	}
  
	public String getEditState() {
		return this.editState;
	}
	
	public void setEditState(String editState) {
		this.editState = editState;
	}
  
	public String getRoundState() {
		return this.roundState;
	}
	
	public void setRoundState(String roundState) {
		this.roundState = roundState;
	}
  
	public String getOrA() {
		return this.orA;
	}
	
	public void setOrA(String orA) {
		this.orA = orA;
	}
  
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
  
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
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



	public QuestionRound copy(QuestionRound other){
  
		this.setRoundId(other.getRoundId());
  
		this.questionInfo= other.getQuestionInfo();  
		this.roundContent= other.getRoundContent();  
		this.editState= other.getEditState();  
		this.roundState= other.getRoundState();  
		this.orA= other.getOrA();  
		this.createTime= other.getCreateTime();  
		this.lastUpdateTime= other.getLastUpdateTime();  
		this.userCode= other.getUserCode();  
		this.userName= other.getUserName();

		return this;
	}
	
	public QuestionRound copyNotNullProperty(QuestionRound other){
  
	if( other.getRoundId() != null)
		this.setRoundId(other.getRoundId());
  
		if( other.getQuestionInfo() != null)
			this.questionInfo= other.getQuestionInfo();  
		if( other.getRoundContent() != null)
			this.roundContent= other.getRoundContent();  
		if( other.getEditState() != null)
			this.editState= other.getEditState();  
		if( other.getRoundState() != null)
			this.roundState= other.getRoundState();  
		if( other.getOrA() != null)
			this.orA= other.getOrA();  
		if( other.getCreateTime() != null)
			this.createTime= other.getCreateTime();  
		if( other.getLastUpdateTime() != null)
			this.lastUpdateTime= other.getLastUpdateTime();  
		if( other.getUserCode() != null)
			this.userCode= other.getUserCode();  
		if( other.getUserName() != null)
			this.userName= other.getUserName();		

		return this;
	}

	public QuestionRound clearProperties(){
  
		this.questionInfo= null;  
		this.roundContent= null;  
		this.editState= null;  
		this.roundState= null;  
		this.orA= null;  
		this.createTime= null;  
		this.lastUpdateTime= null;  
		this.userCode= null;  
		this.userName= null;

		return this;
	}
}
