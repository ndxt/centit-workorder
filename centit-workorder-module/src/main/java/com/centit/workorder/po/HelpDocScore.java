package com.centit.workorder.po;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

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
	@Column(name = "DOC_ID")
	@Length(max = 32, message = "字段长度不能大于{max}")
	private String  docId;
	/**
	 * 文档评分 null 
	 */
	@Column(name = "DOC_SCORE")
//	@NotBlank(message = "字段不能为空")
	private int  docScore;
	/**
	 * 用户代码 null 
	 */
	@Column(name = "USER_CODE")
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
	@Column(name = "CREATE_TIME")
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
	,String  docId,int  docScore,String  userCode,String  userName,Date  createTime) {
	
	
		this.scoreId = scoreId;		
	
		this.docId= docId;
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
  
	public String getDocId() {
		return this.docId;
	}
	
	public void setDocId(String docId) {
		this.docId = docId;
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
  
		this.docId= other.getDocId();
		this.docScore= other.getDocScore();  
		this.userCode= other.getUserCode();  
		this.userName= other.getUserName();  
		this.createTime= other.getCreateTime();

		return this;
	}
	
	public HelpDocScore copyNotNullProperty(HelpDocScore other){
  
	if( other.getScoreId() != null)
		this.setScoreId(other.getScoreId());
  
		if( other.getDocId() != null)
			this.docId= other.getDocId();
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
  
		this.docId= null;
		this.docScore= -1;
		this.userCode= null;  
		this.userName= null;  
		this.createTime= null;

		return this;
	}
}
