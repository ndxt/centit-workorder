package com.centit.workorder.po;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;


/**
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 
  帮助文档评价null   
*/
@Entity
@Table(name = "F_HELP_DOC_COMMENT")
public class HelpDocComment implements java.io.Serializable {
	private static final long serialVersionUID =  1L;



	/**
	 * 评价ID null 
	 */
	@Id
	@Column(name = "COMMENT_ID")
	@GeneratedValue(generator = "assignedGenerator")
	@GenericGenerator(name = "assignedGenerator", strategy = "assigned")
	private String commentId;

	/**
	 * 文档ID null 
	 */
	@Column(name = "DOC_ID")
	@Length(max = 32, message = "字段长度不能大于{max}")
	private String  docId;
	/**
	 * 评价内容 null 
	 */
	@Column(name = "DOC_COMMENT")
	private String  docComment;
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
	public HelpDocComment() {
	}
	/** minimal constructor */
	public HelpDocComment(
		String commentId		
		) {
	
	
		this.commentId = commentId;		
			
	}

/** full constructor */
	public HelpDocComment(
	 String commentId		
	,String  docId,String  docComment,String  userCode,String  userName,Date  createTime) {
	
	
		this.commentId = commentId;		
	
		this.docId= docId;
		this.docComment= docComment;
		this.userCode= userCode;
		this.userName= userName;
		this.createTime= createTime;		
	}
	

  
	public String getCommentId() {
		return this.commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	// Property accessors
  
	public String getDocId() {
		return this.docId;
	}
	
	public void setDocId(String docId) {
		this.docId = docId;
	}
  
	public String getDocComment() {
		return this.docComment;
	}
	
	public void setDocComment(String docComment) {
		this.docComment = docComment;
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



	public HelpDocComment copy(HelpDocComment other){
  
		this.setCommentId(other.getCommentId());
  
		this.docId= other.getDocId();  
		this.docComment= other.getDocComment();  
		this.userCode= other.getUserCode();  
		this.userName= other.getUserName();  
		this.createTime= other.getCreateTime();

		return this;
	}
	
	public HelpDocComment copyNotNullProperty(HelpDocComment other){
  
	if( other.getCommentId() != null)
		this.setCommentId(other.getCommentId());
  
		if( other.getDocId() != null)
			this.docId= other.getDocId();  
		if( other.getDocComment() != null)
			this.docComment= other.getDocComment();  
		if( other.getUserCode() != null)
			this.userCode= other.getUserCode();  
		if( other.getUserName() != null)
			this.userName= other.getUserName();  
		if( other.getCreateTime() != null)
			this.createTime= other.getCreateTime();		

		return this;
	}

	public HelpDocComment clearProperties(){
  
		this.docId= null;  
		this.docComment= null;  
		this.userCode= null;  
		this.userName= null;  
		this.createTime= null;

		return this;
	}
}
