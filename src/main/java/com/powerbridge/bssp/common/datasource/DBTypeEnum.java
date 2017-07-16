package com.powerbridge.bssp.common.datasource;

public enum DBTypeEnum {
	bssp("dataSource_bssp"), 
	cod("dataSource_parameter"), 
	edi("dataSource_edi"), 
	ems("dataSource_ems"),
	sas("dataSource_sas"),
	inv("dataSource_inv"),
	cop("dataSource_cop"),
	dcr("dataSource_dcr"),
	cop_et("dataSource_cop_et"),
	erp("dataSource_erp"),
	entry("dataSource_entry");

	private String value;

	DBTypeEnum(String value) {
	    this.value = value;
	}

	public String getValue() {
	    return value;
	}
}
