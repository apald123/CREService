package com.cisco.cre.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Item {

	protected String partNumber;
	protected String description;
	protected String itemType;
	protected String productType;
	protected String productFamily;
	protected String serviceSKU;
	//@JsonInclude(value=Include.NON_EMPTY)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MMM-yy", timezone="PST")
	protected Date eolAnnounceDate;
	protected String bu;
	//@JsonInclude(value=Include.NON_EMPTY)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MMM-yy", timezone="PST")
	protected Date eolSaleDate;
	//@JsonInclude(value=Include.NON_EMPTY)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MMM-yy", timezone="PST")
	@JsonDeserialize(converter = com.cisco.cre.util.MyDateConverter.class)
	protected Date lastSupportDate;
	protected String leadTime;
	protected String techGroup;
	protected String productSubGroup;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductFamily() {
		return productFamily;
	}
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	public String getServiceSKU() {
		return serviceSKU;
	}
	public void setServiceSKU(String serviceSKU) {
		this.serviceSKU = serviceSKU;
	}
	public Date getEolAnnounceDate() {
		return eolAnnounceDate;
	}
	public void setEolAnnounceDate(Date eolAnnounceDate) {
		this.eolAnnounceDate = eolAnnounceDate;
	}
	public String getBu() {
		return bu;
	}
	public void setBu(String bu) {
		this.bu = bu;
	}
	public Date getEolSaleDate() {
		return eolSaleDate;
	}
	public void setEolSaleDate(Date eolSaleDate) {
		this.eolSaleDate = eolSaleDate;
	}
	public Date getLastSupportDate() {
		return lastSupportDate;
	}
	public void setLastSupportDate(Date lastSupportDate) {
		this.lastSupportDate = lastSupportDate;
	}
	public String getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}
	public String getTechGroup() {
		return techGroup;
	}
	public void setTechGroup(String techGroup) {
		this.techGroup = techGroup;
	}
	public String getProductSubGroup() {
		return productSubGroup;
	}
	public void setProductSubGroup(String productSubGroup) {
		this.productSubGroup = productSubGroup;
	}

}
