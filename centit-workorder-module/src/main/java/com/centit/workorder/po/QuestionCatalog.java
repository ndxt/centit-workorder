package com.centit.workorder.po;

import com.centit.support.database.orm.GeneratorType;
import com.centit.support.database.orm.ValueGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
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
	@ValueGenerator(strategy = GeneratorType.UUID)
	private String catalogId;
	/**
	 * 业务系统ID null 
	 */
	@Column(name = "OS_ID")
	@NotBlank(message = "字段不能为空")
	//@Length(max = 20, message = "字段长度不能大于{max}")
	private String  osId;
	/**
	 * 类别名称 null 
	 */
	@Column(name = "CATALOG_NAME")
	@NotBlank(message = "字段不能为空")
	//@Length(max = 100, message = "字段长度不能大于{max}")
	private String  catalogName;
	/**
	 * 创建人员 null 
	 */
	@Column(name = "CREATOR")
	//@Length(max = 32, message = "字段长度不能大于{max}")
	private String  creator;
	/**
	 * 创建时间 null 
	 */
	@Column(name = "CREATE_TIME")
	private Date  createTime;
	/**
	 *默认责任人
	 */
	@Column(name = "DEFAULT_OPERATOR")
	private String defaultOperator;
	/**
	 *时效
	 */
	@Column(name = "TIME_LIMIT")
	private int timeLimit;
	/**
	 * 关键字
	 */
	@Column(name = "CATALOG_KEY_WORDS")
	//@Length(max = 200, message = "字段长度不能大于{max}")
	private String  catalogKeyWords;

	/**
	 * 父节点ID
	 */
	@Column(name = "PARENT_ID")
	//@Length(max = 32, message = "字段长度不能大于{max}")
	private String  parentId;

	/**
	 *排序
	 */
	@Column(name = "SORT")
	private int sort;

	/**
	 * 图标
	 */
	@Column(name = "ICON")
	//@Length(max = 100, message = "字段长度不能大于{max}")
	private String  icon;

	/**
	 * 描述
	 */
	@Column(name = "CATALOG_DESCRIBE")
	//@Length(max = 200, message = "字段长度不能大于{max}")
	private String  catalogDescribe;




	public String getCatalogKeyWords() {
		return catalogKeyWords;
	}

	public void setCatalogKeyWords(String catalogKeyWords) {
		this.catalogKeyWords = catalogKeyWords;
	}
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
			,String  osId,String  catalogName,String  creator,Date  createTime,
			String defaultOperator, int timeLimit, String catalogKeyWords,
			String parentId,int sort,String icon,String catalogDescribe) {
		this.catalogId = catalogId;
		this.osId= osId;
		this.catalogName= catalogName;
		this.creator= creator;
		this.createTime= createTime;
		this.defaultOperator = defaultOperator;
		this.timeLimit = timeLimit;
		this.catalogKeyWords = catalogKeyWords;
		this.parentId = parentId;
		this.sort = sort;
		this.icon = icon;
		this.catalogDescribe = catalogDescribe;
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

	public String getDefaultOperator() {
		return defaultOperator;
	}

	public void setDefaultOperator(String defaultOperator) {
		this.defaultOperator = defaultOperator;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCatalogDescribe() {
		return catalogDescribe;
	}

	public void setCatalogDescribe(String catalogDescribe) {
		this.catalogDescribe = catalogDescribe;
	}

	public QuestionCatalog copy(QuestionCatalog other){
		this.setCatalogId(other.getCatalogId());
		this.osId= other.getOsId();  
		this.catalogName= other.getCatalogName();  
		this.creator= other.getCreator();  
		this.createTime= other.getCreateTime();
		this.defaultOperator= other.getDefaultOperator();
		this.timeLimit= other.getTimeLimit();
		this.catalogKeyWords= other.getCatalogKeyWords();
		this.parentId= other.getParentId();
		this.sort= other.getSort();
		this.icon= other.getIcon();
		this.catalogDescribe= other.getCatalogDescribe();
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
		if( other.getDefaultOperator() != null)
			this.defaultOperator= other.getDefaultOperator();
		if( other.getTimeLimit() != -1)
			this.timeLimit= other.getTimeLimit();
		if (other.getCatalogKeyWords() != null)
			this.catalogKeyWords= other.getCatalogKeyWords();
		if (other.getParentId() != null)
			this.parentId= other.getParentId();
		if (other.getSort() != -1)
			this.sort= other.getSort();
		if (other.getIcon() != null)
			this.icon= other.getIcon();
		if (other.getCatalogDescribe() != null)
			this.catalogDescribe= other.getCatalogDescribe();
		return this;
	}

	public QuestionCatalog clearProperties(){
		this.osId= null;  
		this.catalogName= null;  
		this.creator= null;  
		this.createTime= null;
		this.defaultOperator= null;
		this.timeLimit= -1;
		this.catalogKeyWords= null;
		this.parentId= null;
		this.sort= -1;
		this.icon= null;
		this.catalogDescribe= null;
		return this;
	}
}
