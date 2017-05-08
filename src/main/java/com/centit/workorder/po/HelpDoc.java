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
 
  系统帮助文档null   
*/
@Entity
@Table(name = "F_HELP_DOC")
public class HelpDoc implements java.io.Serializable {
	private static final long serialVersionUID =  1L;



	/**
	 * 文档ID null 
	 */
	@Id
	@Column(name = "DOC_ID")
	@GeneratedValue(generator = "assignedGenerator")
	@GenericGenerator(name = "assignedGenerator", strategy = "assigned")
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
	@NotBlank(message = "字段不能为空")
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
	private String  docFile;
	/**
	 * 业务系统ID null 
	 */
	@Column(name = "OS_ID")
	@Length(max = 20, message = "字段长度不能大于{max}")
	private String  osId;
	/**
	 * 业务项目模块 模块，或者表 
	 */
	@Column(name = "Opt_ID")
	@NotBlank(message = "字段不能为空")
	@Length(max = 64, message = "字段长度不能大于{max}")
	private String  optId;
	/**
	 * 业务操作方法 方法，或者字段 
	 */
	@Column(name = "OPT_Method")
	@Length(max = 64, message = "字段长度不能大于{max}")
	private String  optMethod;
	/**
	 * 编辑人员 null 
	 */
	@Column(name = "update_user")
	@Length(max = 32, message = "字段长度不能大于{max}")
	private String  updateUser;
	/**
	 * 编辑时间 null 
	 */
	@Column(name = "last_update_time")
	private Date  lastUpdateTime;
	
	@OneToMany(mappedBy = "helpDoc", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<HelpDocVersion> helpDocVersions;
	
	@OneToMany(mappedBy = "helpDoc", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<HelpDocComment> helpDocComments;
	
	@OneToMany(mappedBy = "helpDoc", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<HelpDocScore> helpDocScores;

	// Constructors
	/** default constructor */
	public HelpDoc() {
	}
	/** minimal constructor */
	public HelpDoc(
		String docId		
		,String  docTitle,int  docLevel,String  docPath,String  optId) {
	
	
		this.docId = docId;		
	
		this.docTitle= docTitle; 
		this.docLevel= docLevel; 
		this.docPath= docPath; 
		this.optId= optId; 		
	}

/** full constructor */
	public HelpDoc(
	 String docId		
	,String  catalogId,String  docTitle,int  docLevel,String  docPath,String  docFile,String  osId,String  optId,String  optMethod,String  updateUser,Date  lastUpdateTime) {
	
	
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


	public Set<HelpDocVersion> getHelpDocVersions(){
		if(this.helpDocVersions==null)
			this.helpDocVersions = new HashSet<HelpDocVersion>();
		return this.helpDocVersions;
	}

	public void setHelpDocVersions(Set<HelpDocVersion> helpDocVersions) {
		this.helpDocVersions = helpDocVersions;
	}	

	public void addHelpDocVersion(HelpDocVersion helpDocVersion ){
		if (this.helpDocVersions==null)
			this.helpDocVersions = new HashSet<HelpDocVersion>();
		this.helpDocVersions.add(helpDocVersion);
	}
	
	public void removeHelpDocVersion(HelpDocVersion helpDocVersion ){
		if (this.helpDocVersions==null)
			return;
		this.helpDocVersions.remove(helpDocVersion);
	}
	
	public HelpDocVersion newHelpDocVersion(){
		HelpDocVersion res = new HelpDocVersion();
  
		res.setDocId(this.getDocId());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceHelpDocVersions(Set<HelpDocVersion> helpDocVersions) {
		Set<HelpDocVersion> newObjs = new HashSet<HelpDocVersion>();
		for(HelpDocVersion p :helpDocVersions){
			if(p==null)
				continue;
			HelpDocVersion newdt = newHelpDocVersion();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<HelpDocVersion> oldObjs = new HashSet<HelpDocVersion>();
		oldObjs.addAll(getHelpDocVersions());
		
		for(Iterator<HelpDocVersion> it=oldObjs.iterator(); it.hasNext();){
			HelpDocVersion odt = it.next();
			found = false;
			for(HelpDocVersion newdt :newObjs){
				if(odt.getCid().equals( newdt.getCid())){
					found = true;
					break;
				}
			}
			if(! found)
				removeHelpDocVersion(odt);
		}
		oldObjs.clear();
		//insert or update
		for(HelpDocVersion newdt :newObjs){
			found = false;
			for(Iterator<HelpDocVersion> it=getHelpDocVersions().iterator();
			 it.hasNext();){
				HelpDocVersion odt = it.next();
				if(odt.getCid().equals( newdt.getCid())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addHelpDocVersion(newdt);
		} 	
	}	

	public Set<HelpDocComment> getHelpDocComments(){
		if(this.helpDocComments==null)
			this.helpDocComments = new HashSet<HelpDocComment>();
		return this.helpDocComments;
	}

	public void setHelpDocComments(Set<HelpDocComment> helpDocComments) {
		this.helpDocComments = helpDocComments;
	}	

	public void addHelpDocComment(HelpDocComment helpDocComment ){
		if (this.helpDocComments==null)
			this.helpDocComments = new HashSet<HelpDocComment>();
		this.helpDocComments.add(helpDocComment);
	}
	
	public void removeHelpDocComment(HelpDocComment helpDocComment ){
		if (this.helpDocComments==null)
			return;
		this.helpDocComments.remove(helpDocComment);
	}
	
	public HelpDocComment newHelpDocComment(){
		HelpDocComment res = new HelpDocComment();
  
		res.setDocId(this.getDocId());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceHelpDocComments(Set<HelpDocComment> helpDocComments) {
		Set<HelpDocComment> newObjs = new HashSet<HelpDocComment>();
		for(HelpDocComment p :helpDocComments){
			if(p==null)
				continue;
			HelpDocComment newdt = newHelpDocComment();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<HelpDocComment> oldObjs = new HashSet<HelpDocComment>();
		oldObjs.addAll(getHelpDocComments());
		
		for(Iterator<HelpDocComment> it=oldObjs.iterator(); it.hasNext();){
			HelpDocComment odt = it.next();
			found = false;
			for(HelpDocComment newdt :newObjs){
				if(odt.getCommentId().equals( newdt.getCommentId())){
					found = true;
					break;
				}
			}
			if(! found)
				removeHelpDocComment(odt);
		}
		oldObjs.clear();
		//insert or update
		for(HelpDocComment newdt :newObjs){
			found = false;
			for(Iterator<HelpDocComment> it=getHelpDocComments().iterator();
			 it.hasNext();){
				HelpDocComment odt = it.next();
				if(odt.getCommentId().equals( newdt.getCommentId())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addHelpDocComment(newdt);
		} 	
	}	

	public Set<HelpDocScore> getHelpDocScores(){
		if(this.helpDocScores==null)
			this.helpDocScores = new HashSet<HelpDocScore>();
		return this.helpDocScores;
	}

	public void setHelpDocScores(Set<HelpDocScore> helpDocScores) {
		this.helpDocScores = helpDocScores;
	}	

	public void addHelpDocScore(HelpDocScore helpDocScore ){
		if (this.helpDocScores==null)
			this.helpDocScores = new HashSet<HelpDocScore>();
		this.helpDocScores.add(helpDocScore);
	}
	
	public void removeHelpDocScore(HelpDocScore helpDocScore ){
		if (this.helpDocScores==null)
			return;
		this.helpDocScores.remove(helpDocScore);
	}
	
	public HelpDocScore newHelpDocScore(){
		HelpDocScore res = new HelpDocScore();
  
		res.setDocId(this.getDocId());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceHelpDocScores(Set<HelpDocScore> helpDocScores) {
		Set<HelpDocScore> newObjs = new HashSet<HelpDocScore>();
		for(HelpDocScore p :helpDocScores){
			if(p==null)
				continue;
			HelpDocScore newdt = newHelpDocScore();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<HelpDocScore> oldObjs = new HashSet<HelpDocScore>();
		oldObjs.addAll(getHelpDocScores());
		
		for(Iterator<HelpDocScore> it=oldObjs.iterator(); it.hasNext();){
			HelpDocScore odt = it.next();
			found = false;
			for(HelpDocScore newdt :newObjs){
				if(odt.getScoreId().equals( newdt.getScoreId())){
					found = true;
					break;
				}
			}
			if(! found)
				removeHelpDocScore(odt);
		}
		oldObjs.clear();
		//insert or update
		for(HelpDocScore newdt :newObjs){
			found = false;
			for(Iterator<HelpDocScore> it=getHelpDocScores().iterator();
			 it.hasNext();){
				HelpDocScore odt = it.next();
				if(odt.getScoreId().equals( newdt.getScoreId())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addHelpDocScore(newdt);
		} 	
	}	


	public HelpDoc copy(HelpDoc other){
  
		this.setDocId(other.getDocId());
  
		this.catalogId= other.getCatalogId();  
		this.docTitle= other.getDocTitle();  
		this.docLevel= other.getDocLevel();  
		this.docPath= other.getDocPath();  
		this.docFile= other.getDocFile();  
		this.osId= other.getOsId();  
		this.optId= other.getOptId();  
		this.optMethod= other.getOptMethod();  
		this.updateUser= other.getUpdateUser();  
		this.lastUpdateTime= other.getLastUpdateTime();
	
		this.helpDocVersions = other.getHelpDocVersions();	
		this.helpDocComments = other.getHelpDocComments();	
		this.helpDocScores = other.getHelpDocScores();
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
	
		//this.helpDocVersions = other.getHelpDocVersions();
        replaceHelpDocVersions(other.getHelpDocVersions());
			
		//this.helpDocComments = other.getHelpDocComments();
        replaceHelpDocComments(other.getHelpDocComments());
			
		//this.helpDocScores = other.getHelpDocScores();
        replaceHelpDocScores(other.getHelpDocScores());
		
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
	
		this.helpDocVersions = new HashSet<HelpDocVersion>();	
		this.helpDocComments = new HashSet<HelpDocComment>();	
		this.helpDocScores = new HashSet<HelpDocScore>();
		return this;
	}
}
