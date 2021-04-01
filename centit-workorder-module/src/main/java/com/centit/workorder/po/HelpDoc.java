package com.centit.workorder.po;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.centit.search.document.ObjectDocument;
import com.centit.support.database.orm.GeneratorCondition;
import com.centit.support.database.orm.GeneratorType;
import com.centit.support.database.orm.ValueGenerator;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @ValueGenerator(strategy = GeneratorType.UUID)
    private String docId;

    /**
     * 类别ID null
     */
    @Column(name = "CATALOG_ID")
    //@Length(max = 32, message = "字段长度不能大于{max}")
    private String  catalogId;
    /**
     * 文档标题 null
     */
    @Column(name = "DOC_TITLE")
    @NotBlank(message = "字段不能为空")
    //@Length(max = 500, message = "字段长度不能大于{max}")
    private String  docTitle;
    /**
     * 文档层级 null
     */
    @Column(name = "DOC_LEVEL")
//    @NotBlank(message = "字段不能为空")
    private int  docLevel;
    /**
     * 文档路径 null
     */
    @Column(name = "DOC_PATH")
    @NotBlank(message = "字段不能为空")
    //@Length(max = 500, message = "字段长度不能大于{max}")
    private String  docPath;
    /**
     * 副文本 CLOB 字段
     */
    @Column(name = "DOC_FILE")
    @JSONField(deserialize = false, serialize = false)
    private String docFile;
    /**
     * 业务系统ID null
     */
    @Column(name = "OS_ID")
    @NotBlank(message = "字段不能为空")
    //@Length(max = 20, message = "字段长度不能大于{max}")
    private String  osId;
    /**
     * 业务项目模块 模块，或者表
     */
    @Column(name = "OPT_ID")
    //@Length(max = 64, message = "字段长度不能大于{max}")
    private String  optId;
    /**
     * 业务操作方法 方法，或者字段
     */
    @Column(name = "OPT_METHOD")
    //@Length(max = 64, message = "字段长度不能大于{max}")
    private String  optMethod;

    /**
     * 归属人员 null， 他有管理权
     */
    @Column(name = "OWNER_USER")
    //@Length(max = 32, message = "字段长度不能大于{max}")
    private String  ownerUser;

    /**
     * 编辑人员 null
     */
    @Column(name = "UPDATE_USER")
    //@Length(max = 32, message = "字段长度不能大于{max}")
    private String  updateUser;
    /**
     * 是否开放编辑，默认为true
     */
    @Column(name = "OPEN_EDIT")
    //@Length(max = 1, message = "字段长度不能大于{max}")
    private boolean  openEdit;
    /**
     * 编辑时间 null
     */
    @Column(name = "LAST_UPDATE_TIME")
    @ValueGenerator(strategy = GeneratorType.FUNCTION,
            condition = GeneratorCondition.ALWAYS, value = "today()")
    private Date  lastUpdateTime;


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


    public ObjectDocument generateObjectDocument(){
        ObjectDocument document = new ObjectDocument();
        document.setOsId(this.getOsId());
        document.setOptId(this.getDocId());
        document.setOptMethod(this.getOptMethod());
//        document.setOptUrl("/os/"+this.getOsId()+"/documents/"+this.getDocId());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("docId", this.getDocId());
        jsonObject.put("docPath", this.getDocPath());
        document.setOptUrl(jsonObject.toString());
        document.setTitle(this.getDocTitle());
        if (!StringUtils.isBlank(this.getDocFile())){
            document.setContent(filterTag(this.getDocFile()));
        }
        return document;
    }

    private String filterTag(String content){
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(content);
        content=m_style.replaceAll(""); //过滤style标签

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(content);
        content=m_html.replaceAll(""); //过滤html标签

        return content.trim(); //返回文本字符串

    }
}
