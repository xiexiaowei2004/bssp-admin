package com.powerbridge.bssp.edi.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 单据配置表
 * </p>
 *
 * @author simon.xie
 * @since 2017-06-10
 */
@TableName("edi_business_config")
public class EdiBusinessConfig implements Serializable {
	@TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("UID")
	private String uid;
    /**
     * 单据类型
     */
	@TableField("DOC_TYPE")
	private String docType;
    /**
     * 业务类型
     */
	@TableField("BIZ_TYPE")
	private String bizType;
    /**
     * 表名
     */
	@TableField("TABLE_NAME")
	private String tableName;
    /**
     * 服务名
     */
	@TableField("TABLE_SERVICE")
	private String tableService;
    /**
     * 关联字段名
     */
	@TableField("BIZKEY")
	private String bizkey;
    /**
     * 企业内部编号字段名
     */
	@TableField("ETPS_PREENT_NO")
	private String etpsPreentNo;
    /**
     * 主键名
     */
	@TableField("PRIMARYKEY")
	private String primarykey;
    /**
     * 数据源
     */
	@TableField("DATA_SOURCE")
	private String dataSource;
    /**
     * BEAN名字
     */
	@TableField("BEAN_NAME")
	private String beanName;
    /**
     * 处理标识
     */
	@TableField("STATUS")
	private String status;
    /**
     * 备注
     */
	@TableField("REMARKS")
	private String remarks;
    /**
     * 排序号
     */
	@TableField("ORDER_NO")
	private Integer orderNo;
    /**
     * 创建人代码
     */
	@TableField("CREATE_BY")
	private String createBy;
    /**
     * 创建人名称
     */
	@TableField("CREATE_NAME")
	private String createName;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private String createTime;
    /**
     * 修改人代码
     */
	@TableField("UPDATE_BY")
	private String updateBy;
    /**
     * 修改人名称
     */
	@TableField("UPDATE_NAME")
	private String updateName;
    /**
     * 修改时间
     */
	@TableField("UPDATE_TIME")
	private String updateTime;


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableService() {
		return tableService;
	}

	public void setTableService(String tableService) {
		this.tableService = tableService;
	}

	public String getBizkey() {
		return bizkey;
	}

	public void setBizkey(String bizkey) {
		this.bizkey = bizkey;
	}

	public String getPrimarykey() {
		return primarykey;
	}

	public void setPrimarykey(String primarykey) {
		this.primarykey = primarykey;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getEtpsPreentNo() {
		return etpsPreentNo;
	}

	public void setEtpsPreentNo(String etpsPreentNo) {
		this.etpsPreentNo = etpsPreentNo;
	}
}
