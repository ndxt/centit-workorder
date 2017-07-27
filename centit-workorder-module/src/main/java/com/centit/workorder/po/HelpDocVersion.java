package com.centit.workorder.po;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 
  系统帮助文档历史版本null   
*/
@Entity
@Table(name = "F_HELP_DOC_VERSION")
public class HelpDocVersion implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	@EmbeddedId
	private com.centit.workorder.po.HelpDocVersionId cid;


	/**
	 * 文件ID null 
	 */
	@Column(name = "DOC_FILE")
	private String  docFile;
	/**
	 * 文档标题 null 
	 */
	@Column(name = "DOC_TITLE")
	@NotBlank(message = "字段不能为空")
	@Length(max = 500, message = "字段长度不能大于{max}")
	private String  docTitle;
	/**
	 * 编辑人员 null 
	 */
	@Column(name = "UPDATE_USER")
	@Length(max = 32, message = "字段长度不能大于{max}")
	private String  updateUser;
	/**
	 * 编辑时间 null 
	 */
	@Column(name = "LAST_UPDATE_TIME")
	private Date  lastUpdateTime;

	// Constructors
	/** default constructor */
	public HelpDocVersion() {
	}
	/** minimal constructor */
	public HelpDocVersion(com.centit.workorder.po.HelpDocVersionId id 
				
		,String  docTitle) {
		this.cid = id; 
			
	
		this.docTitle= docTitle; 		
	}

/** full constructor */
	public HelpDocVersion(com.centit.workorder.po.HelpDocVersionId id
			
	,String  docFile,String  docTitle,String  updateUser,Date  lastUpdateTime) {
		this.cid = id; 
			
	
		this.docFile= docFile;
		this.docTitle= docTitle;
		this.updateUser= updateUser;
		this.lastUpdateTime= lastUpdateTime;		
	}

	public com.centit.workorder.po.HelpDocVersionId getCid() {
		return this.cid;
	}
	
	public void setCid(com.centit.workorder.po.HelpDocVersionId id) {
		this.cid = id;
	}
  
	public String getDocId() {
		if(this.cid==null)
			this.cid = new com.centit.workorder.po.HelpDocVersionId();
		return this.cid.getDocId();
	}
	
	public void setDocId(String helpDoc) {
		if(this.cid==null)
			this.cid = new com.centit.workorder.po.HelpDocVersionId();
		this.cid.setDocId(helpDoc);
	}
  
	public int getDocVersion() {
		if(this.cid==null)
			this.cid = new com.centit.workorder.po.HelpDocVersionId();
		return this.cid.getDocVersion();
	}
	
	public void setDocVersion(int docVersion) {
		if(this.cid==null)
			this.cid = new com.centit.workorder.po.HelpDocVersionId();
		this.cid.setDocVersion(docVersion);
	}
	
	

	// Property accessors
  
	public String getDocFile() {
		return this.docFile;
	}
	
	public void setDocFile(String docFile) {
		this.docFile = docFile;
	}
  
	public String getDocTitle() {
		return this.docTitle;
	}
	
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}
  
	public String getUpdateUser() {
		return this.updateUser;
	}
	
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
  
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}



	public HelpDocVersion copy(HelpDocVersion other){
  
		this.setDocId(other.getDocId());
		this.setDocVersion(other.getDocVersion());
  
		this.docFile= other.getDocFile();  
		this.docTitle= other.getDocTitle();  
		this.updateUser= other.getUpdateUser();  
		this.lastUpdateTime= other.getLastUpdateTime();

		return this;
	}
	
	public HelpDocVersion copyNotNullProperty(HelpDocVersion other){
  
	if( other.getDocId() != null)
		this.setDocId(other.getDocId());
	if( other.getDocVersion() != -1)
		this.setDocVersion(other.getDocVersion());
  
		if( other.getDocFile() != null)
			this.docFile= other.getDocFile();  
		if( other.getDocTitle() != null)
			this.docTitle= other.getDocTitle();  
		if( other.getUpdateUser() != null)
			this.updateUser= other.getUpdateUser();  
		if( other.getLastUpdateTime() != null)
			this.lastUpdateTime= other.getLastUpdateTime();		

		return this;
	}

	public HelpDocVersion clearProperties(){
  
		this.docFile= null;  
		this.docTitle= null;  
		this.updateUser= null;  
		this.lastUpdateTime= null;

		return this;
	}
}
