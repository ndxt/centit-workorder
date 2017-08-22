package com.centit.workorder.po;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * HelpDocVersionId  entity.
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 * 系统帮助文档历史版本null   
*/
//系统帮助文档历史版本 的主键
@Embeddable
public class HelpDocVersionId implements java.io.Serializable {
	private static final long serialVersionUID =  1L;

	/**
	 * 文档ID null 
	 */
	@Column(name = "DOC_ID")
	@NotBlank(message = "字段不能为空")
	private String docId;

	/**
	 * 文档版本号 null 
	 */
	@Column(name = "DOC_VERSION")
	@NotBlank(message = "字段不能为空")
	private int docVersion;

	// Constructors
	/** default constructor */
	public HelpDocVersionId() {
	}
	/** full constructor */
	public HelpDocVersionId(String docId, int docVersion) {

		this.docId = docId;
		this.docVersion = docVersion;	
	}

  
	public String getDocId() {
		return this.docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}
  
	public int getDocVersion() {
		return this.docVersion;
	}

	public void setDocVersion(int docVersion) {
		this.docVersion = docVersion;
	}


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof HelpDocVersionId))
			return false;
		
		HelpDocVersionId castOther = (HelpDocVersionId) other;
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
