package com.centit.workorder.po;

import com.alibaba.fastjson.JSONObject;
import com.centit.framework.core.po.EntityWithTimestamp;
import com.centit.search.document.ObjectDocument;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * create by scaffold 2017-05-08 
 * @author codefan@sina.com
 
  系统帮助文档null   
*/
@Entity
@Table(name = "F_HELP_DOC")
public class HelpDoc implements java.io.Serializable,EntityWithTimestamp {
	private static final long serialVersionUID =  1L;

	/**
	 * 文档ID null 
	 */
	@Id
	@Column(name = "DOC_ID")
	@GeneratedValue(generator = "assignedGenerator")
	@GenericGenerator(name = "assignedGenerator", strategy = "uuid")
	private String docId;

	/**
	 * 类别ID null 
	 */
	@Column(name = "CATALOG_ID")
	@Length(max = 32, message = "字段长度不能大于{max}")
	private String  catalogId;
	/**
	 * 文档标题 null 
	 */
	@Column(name = "DOC_TITLE")
	@NotBlank(message = "字段不能为空")
	@Length(max = 500, message = "字段长度不能大于{max}")
	private String  docTitle;
	/**
	 * 文档层级 null 
	 */
	@Column(name = "DOC_LEVEL")
//	@NotBlank(message = "字段不能为空")
	private int  docLevel;
	/**
	 * 文档路径 null 
	 */
	@Column(name = "DOC_PATH")
	@NotBlank(message = "字段不能为空")
	@Length(max = 500, message = "字段长度不能大于{max}")
	private String  docPath;
	/**
	 * 文件ID null 
	 */
	@Column(name = "DOC_FILE")
	private String docFile;
	/**
	 * 业务系统ID null 
	 */
	@Column(name = "OS_ID")
	@Length(max = 20, message = "字段长度不能大于{max}")
	private String  osId;
	/**
	 * 业务项目模块 模块，或者表 
	 */
	@Column(name = "OPT_ID")
	@NotBlank(message = "字段不能为空")
	@Length(max = 64, message = "字段长度不能大于{max}")
	private String  optId;
	/**
	 * 业务操作方法 方法，或者字段 
	 */
	@Column(name = "OPT_METHOD")
	@Length(max = 64, message = "字段长度不能大于{max}")
	private String  optMethod;
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

    /**
     * 评价
     */
	@OneToMany(mappedBy="docId",cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	private List<HelpDocComment> helpDocComments;
	
	// Constructors
	/** default constructor */
	public HelpDoc() {
	}

	/** minimal constructor */
	public HelpDoc(String docId, String docTitle, int docLevel, String docPath, String optId) {
		this.docId = docId;
		this.docTitle= docTitle;
		this.docLevel= docLevel; 
		this.docPath= docPath; 
		this.optId= optId; 		
	}

	/** full constructor */
	public HelpDoc(String docId, String catalogId, String docTitle, int docLevel, String docPath, String docFile,
				   String osId, String optId, String optMethod, String updateUser, Date lastUpdateTime) {

		this.docId = docId;		
		this.catalogId= catalogId;
		this.docTitle= docTitle;
		this.docLevel= docLevel;
		this.docPath= docPath;
		this.docFile= docFile;
		this.osId= osId;
		this.optId= optId;
		this.optMethod= optMethod;
		this.updateUser= updateUser;
		this.lastUpdateTime= lastUpdateTime;		
	}
	
	public String getDocId() {
		return this.docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}
	// Property accessors
  
	public String getCatalogId() {
		return this.catalogId;
	}
	
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
  
	public String getDocTitle() {
		return this.docTitle;
	}

	public String getLabel() {
		return this.docTitle;
	}
	
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}
  
	public int getDocLevel() {
		return this.docLevel;
	}
	
	public void setDocLevel(int docLevel) {
		this.docLevel = docLevel;
	}
  
	public String getDocPath() {
		return this.docPath;
	}
	
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
  
	public String getDocFile() {
		return this.docFile;
	}
	
	public void setDocFile(String docFile) {
		this.docFile = docFile;
	}
  
	public String getOsId() {
		return this.osId;
	}
	
	public void setOsId(String osId) {
		this.osId = osId;
	}
  
	public String getOptId() {
		return this.optId;
	}
	
	public void setOptId(String optId) {
		this.optId = optId;
	}
  
	public String getOptMethod() {
		return this.optMethod;
	}
	
	public void setOptMethod(String optMethod) {
		this.optMethod = optMethod;
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

    public List<HelpDocComment> getHelpDocComments() {
        return helpDocComments;
    }

    public void setHelpDocComments(List<HelpDocComment> helpDocComments) {
        this.helpDocComments = helpDocComments;
    }

    @Override
	public Date getLastModifyDate() {
		return this.lastUpdateTime;
	}

	@Override
	public void setLastModifyDate(Date date) {
		this.lastUpdateTime = date;
	}

	public HelpDoc copy(HelpDoc other){
  
		this.setDocId(other.getDocId());
		this.catalogId = other.getCatalogId();
		this.docTitle = other.getDocTitle();
		this.docLevel = other.getDocLevel();
		this.docPath = other.getDocPath();
		this.docFile = other.getDocFile();
		this.osId = other.getOsId();
		this.optId = other.getOptId();
		this.optMethod = other.getOptMethod();
		this.updateUser = other.getUpdateUser();
		this.lastUpdateTime = other.getLastUpdateTime();
	
		return this;
	}
	
	public HelpDoc copyNotNullProperty(HelpDoc other){
  
		if( other.getDocId() != null)
			this.setDocId(other.getDocId());
  
		if( other.getCatalogId() != null)
			this.catalogId= other.getCatalogId();
		if( other.getDocTitle() != null)
			this.docTitle= other.getDocTitle();  
		if( other.getDocLevel() != -1)
			this.docLevel= other.getDocLevel();  
		if( other.getDocPath() != null)
			this.docPath= other.getDocPath();  
		if( other.getDocFile() != null)
			this.docFile= other.getDocFile();  
		if( other.getOsId() != null)
			this.osId= other.getOsId();  
		if( other.getOptId() != null)
			this.optId= other.getOptId();  
		if( other.getOptMethod() != null)
			this.optMethod= other.getOptMethod();  
		if( other.getUpdateUser() != null)
			this.updateUser= other.getUpdateUser();  
		if( other.getLastUpdateTime() != null)
			this.lastUpdateTime= other.getLastUpdateTime();		

		return this;
	}

	public HelpDoc clearProperties(){
  
		this.catalogId= null;
		this.docTitle= null;  
		this.docLevel= -1;
		this.docPath= null;  
		this.docFile= null;  
		this.osId= null;  
		this.optId= null;  
		this.optMethod= null;  
		this.updateUser= null;  
		this.lastUpdateTime= null;
	
		return this;
	}

	public HelpDocVersion generateVersion(int docVersion){
		HelpDocVersion helpDocVersion = new HelpDocVersion();
		helpDocVersion.setDocId(this.getDocId());
		helpDocVersion.setDocVersion(docVersion);
		helpDocVersion.setDocFile(this.getDocFile());
		helpDocVersion.setDocTitle(this.getDocTitle());
		helpDocVersion.setUpdateUser(this.getUpdateUser());
		helpDocVersion.setLastUpdateTime(this.getLastUpdateTime());

		return helpDocVersion;
	}

	public ObjectDocument generateObjectDocument(){
		ObjectDocument document = new ObjectDocument();
        document.setOsId(this.getOsId());
        document.setOptId(this.getOptId());
        document.setOptMethod(this.getOptMethod());
//        document.setOptUrl("/os/"+this.getOsId()+"/documents/"+this.getDocId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("docId", this.getDocId());
		jsonObject.put("docPath", this.getDocPath());
        document.setOptUrl(jsonObject.toString());
        document.setTitle(this.getDocTitle());
        document.setContent(this.getDocFile());
        return document;
	}
}
