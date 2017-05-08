package com.centit.workorder.po;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;


/**
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 
  帮组文档评分null   
*/
@Entity
@Table(name = "F_HELP_DOC_SCORE")
public class HelpDocScore implements java.io.Serializable {
	private static final long serialVersionUID =  1L;



	/**
	 * 评分ID null 
	 */
	@Id
	@Column(name = "SCORE_ID")
	@GeneratedValue(generator = "assignedGenerator")
	@GenericGenerator(name = "assignedGenerator", strategy = "uuid")
	private String scoreId;

	/**
	 * 文档ID null 
	 */
	@ManyToOne
	@JoinColumn(name = "DOC_ID")
	@Length(max = 32, message = "字段长度不能大于{max}")
	private HelpDoc  helpDoc;
	/**
	 * 文档评分 null 
	 */
	@Column(name = "DOC_SCORE")
	@NotBlank(message = "字段不能为空")
	private int  docScore;
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
	 * 评价时间 null 
	 */
	@Column(name = "create_time")
	private Date  createTime;

	// Constructors
	/** default constructor */
	public HelpDocScore() {
	}
	/** minimal constructor */
	public HelpDocScore(
		String scoreId		
		,int  docScore) {
	
	
		this.scoreId = scoreId;		
	
		this.docScore= docScore; 		
	}

/** full constructor */
	public HelpDocScore(
	 String scoreId		
	,HelpDoc  helpDoc,int  docScore,String  userCode,String  userName,Date  createTime) {
	
	
		this.scoreId = scoreId;		
	
		this.helpDoc= helpDoc;
		this.docScore= docScore;
		this.userCode= userCode;
		this.userName= userName;
		this.createTime= createTime;		
	}
	

  
	public String getScoreId() {
		return this.scoreId;
	}

	public void setScoreId(String scoreId) {
		this.scoreId = scoreId;
	}
	// Property accessors
  
	public HelpDoc getHelpDoc() {
		return this.helpDoc;
	}
	
	public void setHelpDoc(HelpDoc helpDoc) {
		this.helpDoc = helpDoc;
	}
  
	public int getDocScore() {
		return this.docScore;
	}
	
	public void setDocScore(int docScore) {
		this.docScore = docScore;
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
  
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public HelpDocScore copy(HelpDocScore other){
  
		this.setScoreId(other.getScoreId());
  
		this.helpDoc= other.getHelpDoc();  
		this.docScore= other.getDocScore();  
		this.userCode= other.getUserCode();  
		this.userName= other.getUserName();  
		this.createTime= other.getCreateTime();

		return this;
	}
	
	public HelpDocScore copyNotNullProperty(HelpDocScore other){
  
	if( other.getScoreId() != null)
		this.setScoreId(other.getScoreId());
  
		if( other.getHelpDoc() != null)
			this.helpDoc= other.getHelpDoc();  
		if( other.getDocScore() != -1)
			this.docScore= other.getDocScore();  
		if( other.getUserCode() != null)
			this.userCode= other.getUserCode();  
		if( other.getUserName() != null)
			this.userName= other.getUserName();  
		if( other.getCreateTime() != null)
			this.createTime= other.getCreateTime();		

		return this;
	}

	public HelpDocScore clearProperties(){
  
		this.helpDoc= null;  
		this.docScore= -1;
		this.userCode= null;  
		this.userName= null;  
		this.createTime= null;

		return this;
	}
}
