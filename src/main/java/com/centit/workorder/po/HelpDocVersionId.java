package com.centit.workorder.po;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@ManyToOne
	@JoinColumn(name = "DOC_ID")
	@NotBlank(message = "字段不能为空")
	private HelpDoc helpDoc;

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
	public HelpDocVersionId(HelpDoc helpDoc, int docVersion) {

		this.helpDoc = helpDoc;
		this.docVersion = docVersion;	
	}

  
	public HelpDoc getHelpDoc() {
		return this.helpDoc;
	}

	public void setHelpDoc(HelpDoc helpDoc) {
		this.helpDoc = helpDoc;
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
		boolean ret = true;
  
		ret = ret && ( this.getHelpDoc() == castOther.getHelpDoc() ||
					   (this.getHelpDoc() != null && castOther.getHelpDoc() != null
							   && this.getHelpDoc().equals(castOther.getHelpDoc())));
  
		ret = ret && ( this.getDocVersion() == castOther.getDocVersion() ||
					   (this.getDocVersion() != -1 && castOther.getDocVersion() != -1
							   && this.getDocVersion()==castOther.getDocVersion()));

		return ret;
	}
	
	public int hashCode() {
		int result = 17;
  
		result = 37 * result +
		 	(this.getHelpDoc() == null ? 0 :this.getHelpDoc().hashCode());
  
		result = 37 * result +
		 	(this.getDocVersion() == -1 ? 0 :this.getDocVersion());
	
		return result;
	}
}
