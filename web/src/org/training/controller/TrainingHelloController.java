/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.model.SimilarProduct;
import org.training.service.CaptureAndUploadProcess;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class TrainingHelloController
{

	@Resource
	private CaptureAndUploadProcess captureAndUploadProcessImpl;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String logInPage(final ModelMap model)
	{
		return "login";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(@RequestParam(value = "phone", required = false)
	final String phone, final Model model)
	{
		final String url = "https://localhost:9002/occ/v2/electronics/history?phone=" + phone;

		try
		{
			final URL apiUrl = new URL(url);
			final HttpURLConnection httpURLConnection = (HttpURLConnection) apiUrl.openConnection();
			httpURLConnection.setRequestMethod("GET");

			try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())))
			{
				final int statusCode = httpURLConnection.getResponseCode();

				if (statusCode == HttpURLConnection.HTTP_OK)
				{
					final StringBuilder responseContent = new StringBuilder();
					String inputLine;

					while ((inputLine = bufferedReader.readLine()) != null)
					{
						responseContent.append(inputLine);
					}

					System.out.println("Response Status: " + statusCode);
					System.out.println("Response Body: " + responseContent.toString());
					// Assuming model is a valid object of a class that can handle attributes

					final ObjectMapper objectMapper = new ObjectMapper();
					final List<String> responseList = Arrays
							.asList(objectMapper.readValue(responseContent.toString(), String[].class));

					model.addAttribute("urls", responseList);
				}
				else
				{
					System.err.println("HTTP request failed with response code: " + statusCode);
				}
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		return "welcome";
	}

	@GetMapping("/dream")
	public String getCategoryPage(final Model model)
	{
		List<String> categoriesList = new ArrayList<>();
		String imageURL = "";
		try
		{
			final URL apiURL = new URL("https://localhost:9002/occ/v2/electronics/getcategory");
			final HttpURLConnection httpURLConnection = (HttpURLConnection) apiURL.openConnection();
			httpURLConnection.setRequestMethod("GET");
			final int responseCode = httpURLConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK)
			{
				try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())))
				{
					final StringBuilder responseContent = new StringBuilder();
					String inputLine;
					while ((inputLine = bufferedReader.readLine()) != null)
					{
						responseContent.append(inputLine);
					}
					final ObjectMapper objectMapper = new ObjectMapper();
					categoriesList = objectMapper.readValue(responseContent.toString(), new TypeReference<>()
					{
					});
					// Extract the image URL (assuming it's the last element in the list)
					imageURL = categoriesList.remove(categoriesList.size() - 1);
				}
			}
			else
			{
				System.err.println("HTTP request failed with response code: " + responseCode);
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
			System.err.println("An error occurred while connecting to the API: " + e.getMessage());
		}

		model.addAttribute("response", categoriesList);
		model.addAttribute("imageUrl", imageURL);

		return "upload-form";
	}

	@PostMapping("/similar")
	public String getResultsPage(@RequestParam("selectedValues")
	final List<String> selectedValues, final Model model)
	{
		List<SimilarProduct> categorizedProducts = null;
		try
		{
			final String queryString = selectedValues.stream().map(selectedValue -> "selectedValues=" + selectedValue)
					.collect(Collectors.joining("&"));
			final String finalUrl = "https://localhost:9002/occ/v2/electronics/getProducts" + "?" + queryString;

			final CloseableHttpClient httpClient = HttpClients.custom()
					.setSSLContext(SSLContextBuilder.create().loadTrustMaterial(new TrustAllStrategy()).build())
					.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();

			final HttpPost httpPost = new HttpPost(finalUrl);
			final HttpResponse httpResponse = httpClient.execute(httpPost);
			final String responseContent = EntityUtils.toString(httpResponse.getEntity());

			final ObjectMapper objectMapper = new ObjectMapper();
			categorizedProducts = objectMapper.readValue(responseContent,
					new TypeReference<List<SimilarProduct>>()
					{
					});
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			System.err.println("An error occurred while connecting to the API: " + e.getMessage());
		}

		model.addAttribute("similarProducts", categorizedProducts);
		model.addAttribute("uniqueGenders", captureAndUploadProcessImpl.getUniqueGenders(categorizedProducts));
		model.addAttribute("uniqueBrands", captureAndUploadProcessImpl.getUniqueBrands(categorizedProducts));
		model.addAttribute("uniqueCurrencies", captureAndUploadProcessImpl.getUniqueCurrencies(categorizedProducts));

		return "similar-products";
	}

}




