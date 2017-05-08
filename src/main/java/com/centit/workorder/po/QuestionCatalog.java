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
 
  系统问题类别null   
*/
@Entity
@Table(name = "F_QUESTION_CATALOG")
public class QuestionCatalog implements java.io.Serializable {
	private static final long serialVersionUID =  1L;



	/**
	 * 类别ID null 
	 */
	@Id
	@Column(name = "CATALOG_ID")
	@GeneratedValue(generator = "assignedGenerator")
	@GenericGenerator(name = "assignedGenerator", strategy = "uuid")
	private String catalogId;

	/**
	 * 业务系统ID null 
	 */
	@Column(name = "OS_ID")
	@NotBlank(message = "字段不能为空")
	@Length(max = 20, message = "字段长度不能大于{max}")
	private String  osId;
	/**
	 * 类别名称 null 
	 */
	@Column(name = "CATALOG_NAME")
	@NotBlank(message = "字段不能为空")
	@Length(max = 100, message = "字段长度不能大于{max}")
	private String  catalogName;
	/**
	 * 创建人员 null 
	 */
	@Column(name = "creator")
	@Length(max = 32, message = "字段长度不能大于{max}")
	private String  creator;
	/**
	 * 创建时间 null 
	 */
	@Column(name = "create_time")
	private Date  createTime;
	
	@OneToMany(mappedBy = "questionCatalog", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<QuestionInfo> questionInfos;
	
	@OneToMany(mappedBy = "questionCatalog", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<HelpDoc> helpDocs;

	// Constructors
	/** default constructor */
	public QuestionCatalog() {
	}
	/** minimal constructor */
	public QuestionCatalog(
		String catalogId		
		,String  osId,String  catalogName) {
	
	
		this.catalogId = catalogId;		
	
		this.osId= osId; 
		this.catalogName= catalogName; 		
	}

/** full constructor */
	public QuestionCatalog(
	 String catalogId		
	,String  osId,String  catalogName,String  creator,Date  createTime) {
	
	
		this.catalogId = catalogId;		
	
		this.osId= osId;
		this.catalogName= catalogName;
		this.creator= creator;
		this.createTime= createTime;		
	}
	

  
	public String getCatalogId() {
		return this.catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	// Property accessors
  
	public String getOsId() {
		return this.osId;
	}
	
	public void setOsId(String osId) {
		this.osId = osId;
	}
  
	public String getCatalogName() {
		return this.catalogName;
	}
	
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
  
	public String getCreator() {
		return this.creator;
	}
	
	public void setCreator(String creator) {
		this.creator = creator;
	}
  
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Set<QuestionInfo> getQuestionInfos(){
		if(this.questionInfos==null)
			this.questionInfos = new HashSet<QuestionInfo>();
		return this.questionInfos;
	}

	public void setQuestionInfos(Set<QuestionInfo> questionInfos) {
		this.questionInfos = questionInfos;
	}	

	public void addQuestionInfo(QuestionInfo questionInfo ){
		if (this.questionInfos==null)
			this.questionInfos = new HashSet<QuestionInfo>();
		this.questionInfos.add(questionInfo);
	}
	
	public void removeQuestionInfo(QuestionInfo questionInfo ){
		if (this.questionInfos==null)
			return;
		this.questionInfos.remove(questionInfo);
	}
	
	public QuestionInfo newQuestionInfo(){
		QuestionInfo res = new QuestionInfo();
  
		res.setCatalogId(this.getCatalogId());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceQuestionInfos(Set<QuestionInfo> questionInfos) {
		Set<QuestionInfo> newObjs = new HashSet<QuestionInfo>();
		for(QuestionInfo p :questionInfos){
			if(p==null)
				continue;
			QuestionInfo newdt = newQuestionInfo();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<QuestionInfo> oldObjs = new HashSet<QuestionInfo>();
		oldObjs.addAll(getQuestionInfos());
		
		for(Iterator<QuestionInfo> it=oldObjs.iterator(); it.hasNext();){
			QuestionInfo odt = it.next();
			found = false;
			for(QuestionInfo newdt :newObjs){
				if(odt.getQuestionId().equals( newdt.getQuestionId())){
					found = true;
					break;
				}
			}
			if(! found)
				removeQuestionInfo(odt);
		}
		oldObjs.clear();
		//insert or update
		for(QuestionInfo newdt :newObjs){
			found = false;
			for(Iterator<QuestionInfo> it=getQuestionInfos().iterator();
			 it.hasNext();){
				QuestionInfo odt = it.next();
				if(odt.getQuestionId().equals( newdt.getQuestionId())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addQuestionInfo(newdt);
		} 	
	}	

	public Set<HelpDoc> getHelpDocs(){
		if(this.helpDocs==null)
			this.helpDocs = new HashSet<HelpDoc>();
		return this.helpDocs;
	}

	public void setHelpDocs(Set<HelpDoc> helpDocs) {
		this.helpDocs = helpDocs;
	}	

	public void addHelpDoc(HelpDoc helpDoc ){
		if (this.helpDocs==null)
			this.helpDocs = new HashSet<HelpDoc>();
		this.helpDocs.add(helpDoc);
	}
	
	public void removeHelpDoc(HelpDoc helpDoc ){
		if (this.helpDocs==null)
			return;
		this.helpDocs.remove(helpDoc);
	}
	
	public HelpDoc newHelpDoc(){
		HelpDoc res = new HelpDoc();
  
		res.setCatalogId(this.getCatalogId());

		return res;
	}
	/**
	 * 替换子类对象数组，这个函数主要是考虑hibernate中的对象的状态，以避免对象状态不一致的问题
	 * 
	 */
	public void replaceHelpDocs(Set<HelpDoc> helpDocs) {
		Set<HelpDoc> newObjs = new HashSet<HelpDoc>();
		for(HelpDoc p :helpDocs){
			if(p==null)
				continue;
			HelpDoc newdt = newHelpDoc();
			newdt.copyNotNullProperty(p);
			newObjs.add(newdt);
		}
		//delete
		boolean found = false;
		Set<HelpDoc> oldObjs = new HashSet<HelpDoc>();
		oldObjs.addAll(getHelpDocs());
		
		for(Iterator<HelpDoc> it=oldObjs.iterator(); it.hasNext();){
			HelpDoc odt = it.next();
			found = false;
			for(HelpDoc newdt :newObjs){
				if(odt.getDocId().equals( newdt.getDocId())){
					found = true;
					break;
				}
			}
			if(! found)
				removeHelpDoc(odt);
		}
		oldObjs.clear();
		//insert or update
		for(HelpDoc newdt :newObjs){
			found = false;
			for(Iterator<HelpDoc> it=getHelpDocs().iterator();
			 it.hasNext();){
				HelpDoc odt = it.next();
				if(odt.getDocId().equals( newdt.getDocId())){
					odt.copy(newdt);
					found = true;
					break;
				}
			}
			if(! found)
				addHelpDoc(newdt);
		} 	
	}	


	public QuestionCatalog copy(QuestionCatalog other){
  
		this.setCatalogId(other.getCatalogId());
  
		this.osId= other.getOsId();  
		this.catalogName= other.getCatalogName();  
		this.creator= other.getCreator();  
		this.createTime= other.getCreateTime();
	
		this.questionInfos = other.getQuestionInfos();	
		this.helpDocs = other.getHelpDocs();
		return this;
	}
	
	public QuestionCatalog copyNotNullProperty(QuestionCatalog other){
  
	if( other.getCatalogId() != null)
		this.setCatalogId(other.getCatalogId());
  
		if( other.getOsId() != null)
			this.osId= other.getOsId();  
		if( other.getCatalogName() != null)
			this.catalogName= other.getCatalogName();  
		if( other.getCreator() != null)
			this.creator= other.getCreator();  
		if( other.getCreateTime() != null)
			this.createTime= other.getCreateTime();		
	
		//this.questionInfos = other.getQuestionInfos();
        replaceQuestionInfos(other.getQuestionInfos());
			
		//this.helpDocs = other.getHelpDocs();
        replaceHelpDocs(other.getHelpDocs());
		
		return this;
	}

	public QuestionCatalog clearProperties(){
  
		this.osId= null;  
		this.catalogName= null;  
		this.creator= null;  
		this.createTime= null;
	
		this.questionInfos = new HashSet<QuestionInfo>();	
		this.helpDocs = new HashSet<HelpDoc>();
		return this;
	}
}
