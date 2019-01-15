package com.centit.workorder.po;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

	@Id
	@Column(name = "DOC_ID")
	@NotBlank(message = "字段不能为空")
	private String docId;

	/**
	 * 文档版本号 null
	 */
	@Id
	@Column(name = "DOC_VERSION")
	@NotBlank(message = "字段不能为空")
	private int docVersion;

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
	//@Length(max = 500, message = "字段长度不能大于{max}")
	private String  docTitle;
	/**
	 * 编辑人员 null 
	 */
	@Column(name = "UPDATE_USER")
	//@Length(max = 32, message = "字段长度不能大于{max}")
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
	public HelpDocVersion(@NotBlank(message = "字段不能为空") String docId, @NotBlank(message = "字段不能为空") int docVersion
				
		,String  docTitle) {
		this.docId = docId;
		this.docVersion = docVersion;
	
		this.docTitle= docTitle; 		
	}

/** full constructor */
	public HelpDocVersion(@NotBlank(message = "字段不能为空") String docId, @NotBlank(message = "字段不能为空") int docVersion
			
	,String  docFile,String  docTitle,String  updateUser,Date  lastUpdateTime) {
		this.docId = docId;
		this.docVersion = docVersion;
	
		this.docFile= docFile;
		this.docTitle= docTitle;
		this.updateUser= updateUser;
		this.lastUpdateTime= lastUpdateTime;		
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public int getDocVersion() {
		return docVersion;
	}

	public void setDocVersion(int docVersion) {
		this.docVersion = docVersion;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HelpDocVersion))
			return false;

		HelpDocVersion castOther = (HelpDocVersion) other;
		boolean ret;

		ret = this.getDocId() == castOther.getDocId() ||
				(this.getDocId() != null && castOther.getDocId() != null
						&& this.getDocId().equals(castOther.getDocId()));

		ret = ret && ( this.getDocVersion() == castOther.getDocVersion() ||
				(this.getDocVersion() != -1 && castOther.getDocVersion() != -1
						&& this.getDocVersion()==castOther.getDocVersion()));

		return ret;
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result +
				(this.getDocId() == null ? 0 :this.getDocId().hashCode());

		result = 37 * result +
				(this.getDocVersion() == -1 ? 0 :this.getDocVersion());

		return result;
	}
}
