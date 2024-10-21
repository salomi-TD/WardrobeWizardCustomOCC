/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.model;

import java.util.List;


/**
 *
 */
public class FilteringData
{
	public List<String> selectedGender;
	public List<String> selectedBrand;
	public List<String> selectedCurrency;
	/**
	 * @return the selectedGender
	 */
	public List<String> getSelectedGender()
	{
		return selectedGender;
	}
	
	/**
	 * @param selectedGender
	 *           the selectedGender to set
	 */
	public void setSelectedGender(final List<String> selectedGender)
	{
		this.selectedGender = selectedGender;
	}
	/**
	 * @return the selectedBrand
	 */
	public List<String> getSelectedBrand()
	{
		return selectedBrand;
	}
	
	/**
	 * @param selectedBrand
	 *           the selectedBrand to set
	 */
	public void setSelectedBrand(final List<String> selectedBrand)
	{
		this.selectedBrand = selectedBrand;
	}
	/**
	 * @return the selectedCurrency
	 */
	public List<String> getSelectedCurrency()
	{
		return selectedCurrency;
	}
	
	/**
	 * @param selectedCurrency
	 *           the selectedCurrency to set
	 */
	public void setSelectedCurrency(final List<String> selectedCurrency)
	{
		this.selectedCurrency = selectedCurrency;
	}

	/**
	 * @return the selectedGender
	 */

}
