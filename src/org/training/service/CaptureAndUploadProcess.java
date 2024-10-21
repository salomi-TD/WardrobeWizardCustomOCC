/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.service;

import java.util.LinkedHashSet;
import java.util.List;

import org.apache.http.NameValuePair;
import org.springframework.web.multipart.MultipartFile;
import org.training.model.SimilarProduct;

public interface CaptureAndUploadProcess
{

	public String sendRequestToImagekitAPI(String apiKey, String imageUrl, String uploadUrl);

	public String sendRequestToLykdatAPI(String url, List<NameValuePair> params);

	public List<SimilarProduct> getResponseFromLykdatAPI(String imageUrl);

	public List<SimilarProduct> getResponseFromImagekitAPI(final MultipartFile file);

	LinkedHashSet<String> getUniqueCategories(List<SimilarProduct> similarProducts);

	List<SimilarProduct> getProductsByCategory(List<SimilarProduct> similarProducts, List<String> selectedValues);

	LinkedHashSet<String> getUniqueBrands(List<SimilarProduct> similarProducts);

	LinkedHashSet<String> getUniqueGenders(List<SimilarProduct> similarProducts);


	LinkedHashSet<String> getUniqueCurrencies(List<SimilarProduct> similarProducts);

	public Boolean isUserExits(final String mobileNumber);

	public String getOTP(final String mobileNumber);

	public String verifyOTP(int enterdOTP);

	/**
	 *
	 */
	void createFolder();

	public List<String> getHistory(final String mobileNumber);

	/**
	 *
	 */
	List<SimilarProduct> getFilteredProducts(List<SimilarProduct> similarProducts, List<String> selectedGender,
			List<String> selectedBrand, List<String> selectedCurrency);

	/**
	 *
	 */
	List<SimilarProduct> getSortedProducts(List<SimilarProduct> similarProducts, String selectedValue);

}