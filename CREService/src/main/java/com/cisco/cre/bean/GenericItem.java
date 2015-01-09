package com.cisco.cre.bean;

public class GenericItem {

	protected String name;
	protected String description;
	protected String itemType;
	protected String productType;
	protected String productFamily;
	
	
	public GenericItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setItemType(String type) {
		this.itemType = type;
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
}
