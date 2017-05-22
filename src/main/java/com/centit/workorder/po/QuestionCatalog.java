package com.centit.workorder.po;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
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
			,String  osId,String  catalogName,String  creator,Date  createTime, String defaultOperator, int timeLimit) {
		this.catalogId = catalogId;
		this.osId= osId;
		this.catalogName= catalogName;
		this.creator= creator;
		this.createTime= createTime;
		this.defaultOperator = defaultOperator;
		this.timeLimit = timeLimit;
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

	public QuestionCatalog copy(QuestionCatalog other){
		this.setCatalogId(other.getCatalogId());
		this.osId= other.getOsId();  
		this.catalogName= other.getCatalogName();  
		this.creator= other.getCreator();  
		this.createTime= other.getCreateTime();
		this.defaultOperator= other.getDefaultOperator();
		this.timeLimit= other.getTimeLimit();
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
		return this;
	}

	public QuestionCatalog clearProperties(){
		this.osId= null;  
		this.catalogName= null;  
		this.creator= null;  
		this.createTime= null;
		this.defaultOperator= null;
		this.timeLimit= -1;
		return this;
	}
}
