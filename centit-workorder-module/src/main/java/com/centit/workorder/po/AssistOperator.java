package com.centit.workorder.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zhang_gd on 2017/7/27.
 *
 * 协助处理人员
 */

@Entity
@Table(name = "F_ASSIST_OPERATOR")
public class AssistOperator implements java.io.Serializable {
    private static final long serialVersionUID =  1L;

    @EmbeddedId
    private com.centit.workorder.po.AssistOperatorId aid;
    /**
     * 加入时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;


    public AssistOperator(){}

    public AssistOperator(com.centit.workorder.po.AssistOperatorId id,Date createDate){
        this.createDate = createDate;

    }

    public AssistOperatorId getAid() {
        return aid;
    }

    public void setAid(AssistOperatorId aid) {
        this.aid = aid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
