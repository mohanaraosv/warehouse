package com.warehouse.common;

import java.util.ArrayList;
import java.util.List;

public class QueryHQL {

	public List <Object> validParams = new ArrayList<Object>();
	public String dynamicQuery = null;
	
	/**
	 * @return the validParams
	 */
	public List<Object> getValidParams() {
		return validParams;
	}
	/**
	 * @param validParams the validParams to set
	 */
	public void setValidParams(List <Object> validParams) {
		this.validParams = validParams;
	}
	/**
	 * @return the dynamicQuery
	 */
	public String getDynamicQuery() {
		return dynamicQuery;
	}
	/**
	 * @param dynamicQuery the dynamicQuery to set
	 */
	public void setDynamicQuery(String dynamicQuery) {
		this.dynamicQuery = dynamicQuery;
	}
}
