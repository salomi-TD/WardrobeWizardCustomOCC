/*
 * Copyright (c) 2023 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.service.impl;


import de.hybris.platform.servicelayer.model.ModelService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;
import org.training.dao.UserLoginDao;
import org.training.model.ImageUploadResponse;
import org.training.model.JsonResponse;
import org.training.model.SimilarProduct;
import org.training.model.WardrobeWizardRegModel;
import org.training.service.CaptureAndUploadProcess;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public class CaptureAndUploadProcessImpl implements CaptureAndUploadProcess
{

	@Resource(name = "userLoginDao")
	private UserLoginDao userLoginDao;

	private final ObjectMapper objectMapper = new ObjectMapper();

	private static String imageURL = null;
	private static int OTP = 0;
	private static boolean isUserExits = false;
	private static String userMobileNumber = null;


	private static final String UPLOAD_DIRECTORY = "/home/vkumbar/Pictures";

	private ModelService modelService;

	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	@Override
	public String sendRequestToImagekitAPI(final String apiKey, final String imageUrl, final String uploadUrl)
	{
		final File fileToUpload = new File(imageUrl);

		try (CloseableHttpClient httpClient = HttpClients.createDefault())
		{
			final HttpPost httpPost = new HttpPost(uploadUrl);

			// Set the Authorization header with your API key
			httpPost.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((apiKey + ":").getBytes()));

			final HttpEntity entity = MultipartEntityBuilder.create()
					.addBinaryBody("file", fileToUpload, ContentType.create("image/jpg"), fileToUpload.getName())
					.addTextBody("fileName", fileToUpload.getName()).addTextBody("folder", "/Wardrobe_Wizard/" + userMobileNumber)
					.build();

			httpPost.setEntity(entity);

			final HttpResponse response = httpClient.execute(httpPost);

			return (response.getEntity() != null) ? EntityUtils.toString(response.getEntity())
					: "Image upload failed: No response received from the server";
		}
		catch (final IOException e)
		{
			return "Image upload failed: " + e.getMessage();
		}
	}

	@Override
	public String sendRequestToLykdatAPI(final String url, final List<NameValuePair> params)
	{
		try (CloseableHttpClient httpClient = HttpClients.createDefault())
		{
			final HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			httpPost.setEntity(new UrlEncodedFormEntity(params, StandardCharsets.UTF_8));

			try (CloseableHttpResponse response = httpClient.execute(httpPost))
			{
				final int statusCode = response.getStatusLine().getStatusCode();
				System.out.println("Response Code: " + statusCode);

				final HttpEntity entity = response.getEntity();
				return (entity != null) ? EntityUtils.toString(entity, StandardCharsets.UTF_8) : null;
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("An error occurred while sending the API request for Lykdat API.", e);
		}
	}

	@Override
	public List<SimilarProduct> getResponseFromLykdatAPI(final String imageUrl)
	{
		final List<NameValuePair> lykdatAPIReqParams = new ArrayList<>();
		imageURL = imageUrl;
		lykdatAPIReqParams
				.add(new BasicNameValuePair("api_key", "7f3a25a7f682bc14a2e526011b6c40a2e5840280b8381f590a05cf07b228aed0"));
		lykdatAPIReqParams.add(new BasicNameValuePair("image_url", imageUrl));

		try
		{
			final String lykdatResponseBody = sendRequestToLykdatAPI("https://cloudapi.lykdat.com/v1/global/search",
					lykdatAPIReqParams);
			if (lykdatResponseBody != null)
			{
				final JsonResponse lykdatJsonResponse = objectMapper.readValue(lykdatResponseBody, JsonResponse.class);
				return extractSimilarProductsFromLykdat(lykdatJsonResponse);
			}
		}
		catch (final IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error while processing Lykdat API response", e);
		}
		// Return an empty list for other exceptional cases
		return Collections.emptyList();
	}

	@Override
	public List<SimilarProduct> getResponseFromImagekitAPI(final MultipartFile file)
	{
		if (file.isEmpty())
		{
			return Collections.emptyList();
		}
		try
		{
			final String fileName = file.getOriginalFilename();
			final String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
			final File dest = new File(filePath);

			file.transferTo(dest);
			final String response = sendRequestToImagekitAPI("private_KkfRbeeW5OwEvpVbIc8qhVPn0+E=", dest.toString(),
					"https://upload.imagekit.io/api/v1/files/upload");
			final ImageUploadResponse imageKitResponse = objectMapper.readValue(response, ImageUploadResponse.class);
			return getResponseFromLykdatAPI(imageKitResponse.getUrl());
		}
		catch (final IOException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Error processing the image while getting Imagekit API response", e);
		}
	}

	public List<SimilarProduct> extractSimilarProductsFromLykdat(final JsonResponse jsonResponse)
	{
		final List<SimilarProduct> Similar_products = jsonResponse.getData().getResult_groups().stream()
				.flatMap(resultGroup -> resultGroup.getSimilar_products().stream()).collect(Collectors.toList());
		//savingResponse(Similar_products);
		return Similar_products;
	}

	/*
	 * public void savingWWResponse(final List<SimilarProduct> similarProducts) { for (final SimilarProduct
	 * similarProduct : similarProducts) { final WardrobeWizardResponseModel wardrobeWizardResponseModel = new
	 * WardrobeWizardResponseModel();
	 *
	 * wardrobeWizardResponseModel.setBrandName(similarProduct.getBrand_name());
	 * wardrobeWizardResponseModel.setId(similarProduct.getId());
	 * wardrobeWizardResponseModel.setDescription(similarProduct.getName());
	 * wardrobeWizardResponseModel.setCategory(similarProduct.getCategory());
	 * wardrobeWizardResponseModel.setCurrency(similarProduct.getCurrency());
	 * wardrobeWizardResponseModel.setGender(similarProduct.getGender());
	 * wardrobeWizardResponseModel.setMatchingImage(similarProduct.getMatching_image());
	 * wardrobeWizardResponseModel.setPrice(similarProduct.getPrice());
	 * wardrobeWizardResponseModel.setReducedPrice(similarProduct.getReduced_price());
	 * wardrobeWizardResponseModel.setSiteUrl(similarProduct.getUrl());
	 *
	 * modelService.save(wardrobeWizardResponseModel); } }
	 */

	@Override
	public LinkedHashSet<String> getUniqueCategories(final List<SimilarProduct> similarProducts)
	{
		final LinkedHashSet<String> uniqueCategories = similarProducts.stream()
				.map(product -> product.getCategory().replaceAll("[&\\s]", "")).collect(Collectors.toCollection(LinkedHashSet::new));

		uniqueCategories.add(imageURL);

		return uniqueCategories;
	}

	@Override
	public LinkedHashSet<String> getUniqueBrands(final List<SimilarProduct> similarProducts)
	{
		return similarProducts.stream().filter(product -> product.getBrand_name() != null)
				.map(product -> product.getBrand_name().replaceAll("[&\\s]", ""))
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}

	@Override
	public LinkedHashSet<String> getUniqueGenders(final List<SimilarProduct> similarProducts)
	{
		return similarProducts.stream().filter(product -> product.getGender() != null)
				.map(product -> product.getGender().replaceAll("[&\\s]", "")).collect(Collectors.toCollection(LinkedHashSet::new));
	}

	@Override
	public LinkedHashSet<String> getUniqueCurrencies(final List<SimilarProduct> similarProducts)
	{
		return similarProducts.stream().filter(product -> product.getCurrency() != null)
				.map(product -> product.getCurrency().replaceAll("[&\\s]", "")).collect(Collectors.toCollection(LinkedHashSet::new));
	}

	@Override
	public List<SimilarProduct> getProductsByCategory(final List<SimilarProduct> similarProducts,
			final List<String> selectedValues)
	{
		return similarProducts.stream().filter(product -> selectedValues.contains(product.getCategory().replaceAll("[&\\s]", "")))
				.collect(Collectors.toList());
	}

	@Override
	public List<SimilarProduct> getFilteredProducts(final List<SimilarProduct> similarProducts, List<String> selectedGender,
			List<String> selectedBrand, List<String> selectedCurrency)
	{
		if (selectedGender.isEmpty() && selectedBrand.isEmpty() && selectedCurrency.isEmpty())
		{
			return similarProducts;
		}
		final List<SimilarProduct> filteredProducts = new ArrayList<>();
		String brandName;
		String currency;
		String gender;

		for (final SimilarProduct product : similarProducts)
		{
			if (product.getBrand_name() != null)
			{
				brandName = product.getBrand_name().replace(" ", "").replace("&", "");
			}
			else
			{
				brandName = " ";
			}
			if (product.getCurrency() != null)
			{
				currency = product.getCurrency().replace(" ", "").replace("&", "");
			}
			else
			{
				currency = " ";
			}

			if (product.getGender() != null)
			{
				gender = product.getGender().replace(" ", "").replace("&", "");
			}
			else
			{
				gender = " ";
			}

			if (selectedBrand == null)
			{
				selectedBrand = new ArrayList<>();
			}

			if (selectedGender == null)
			{
				selectedGender = new ArrayList<>();
			}

			if (selectedCurrency == null)
			{
				selectedCurrency = new ArrayList<>();
			}

			if (selectedGender.contains(gender) && selectedBrand.contains(brandName) && selectedCurrency.contains(currency))
			{
				filteredProducts.add(product);
			}

			if (selectedGender.contains(gender) && selectedBrand.isEmpty() && selectedCurrency.contains(currency))
			{
				filteredProducts.add(product);
			}

			if (selectedGender.contains(gender) && selectedBrand.contains(brandName) && selectedCurrency.isEmpty())
			{
				filteredProducts.add(product);
			}

			if (selectedGender.isEmpty() && selectedBrand.contains(brandName) && selectedCurrency.contains(currency))
			{
				filteredProducts.add(product);
			}

			if (selectedGender.contains(gender) && selectedBrand.isEmpty() && selectedCurrency.isEmpty())
			{
				filteredProducts.add(product);
			}

			if (selectedGender.isEmpty() && selectedBrand.contains(brandName) && selectedCurrency.isEmpty())
			{
				filteredProducts.add(product);
			}

			if (selectedGender.isEmpty() && selectedBrand.isEmpty() && selectedCurrency.contains(currency))
			{
				filteredProducts.add(product);
			}
		}

		return filteredProducts;
	}


	@Override
	public List<SimilarProduct> getSortedProducts(final List<SimilarProduct> similarProducts, final String selectedValue)
	{
		Comparator<SimilarProduct> comparator;

		if ("price_low_to_high".equals(selectedValue))
		{
			comparator = Comparator.comparingDouble(product -> parsePrice(product.getPrice()));
		}
		else if ("price_high_to_low".equals(selectedValue))
		{
			comparator = (product1, product2) -> Double.compare(parsePrice(product2.getPrice()), parsePrice(product1.getPrice()));
		}
		else if ("brand_name".equals(selectedValue))
		{
			comparator = Comparator.comparing(product -> product.getBrand_name().toLowerCase());
		}
		else if ("brand_name_reverse".equals(selectedValue))
		{
			comparator = (product1, product2) -> product2.getBrand_name().toLowerCase()
					.compareTo(product1.getBrand_name().toLowerCase());
		}
		else
		{
			// Handle invalid selectedValue
			throw new IllegalArgumentException("Invalid selectedValue: " + selectedValue);
		}

		similarProducts.sort(comparator);

		return similarProducts;
	}


	private double parsePrice(final String price)
	{
		// Remove commas and parse to double
		return Double.parseDouble(price.replace(",", ""));
	}



	@Override
	public Boolean isUserExits(final String mobileNumber)
	{
		isUserExits = userLoginDao.checkUser(mobileNumber);
		return isUserExits;
	}

	@Override
	public String getOTP(final String mobileNumber)
	{
		userMobileNumber = mobileNumber;
		isUserExits(userMobileNumber);
		final Random random = new Random();
		OTP = random.nextInt(900000) + 100000;
		return String.valueOf(OTP);
	}

	@Override
	public String verifyOTP(final int enterdOTP)
	{
		if (enterdOTP == OTP && Boolean.TRUE.equals(isUserExits))
		{
			return "user Exists";
		}
		if (enterdOTP == OTP && Boolean.FALSE.equals(isUserExits))
		{
			final WardrobeWizardRegModel wardrobeWizardRegModel = new WardrobeWizardRegModel();
			wardrobeWizardRegModel.setMobileNumber(userMobileNumber);
			modelService.save(wardrobeWizardRegModel);
			createFolder();
		}
		else
		{
			return "entered wrong OTP ";
		}
		return "verifyed succsesfully";
	}

	@Override
	public void createFolder()
	{
		final String apiKey = "private_KkfRbeeW5OwEvpVbIc8qhVPn0+E=";
		final String ENDPOINT = "https://api.imagekit.io/v1/folder/";
		final String folderName = userMobileNumber;
		final String parentFolderPath = "Wardrobe_Wizard";

		try
		{
			final URL url = new URL(ENDPOINT);
			final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set the request method and headers
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");

			// Set basic authentication using apiKey and apiKey
			final String auth = apiKey + ":" + apiKey;
			final String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
			connection.setRequestProperty("Authorization", "Basic " + encodedAuth);

			// Set the request payload
			final String requestBody = String.format("{\"folderName\": \"%s\", \"parentFolderPath\": \"%s\"}", folderName,
					parentFolderPath);
			connection.setDoOutput(true);
			try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream()))
			{
				outputStream.writeBytes(requestBody);
				outputStream.flush();
			}

			// Get the response from the server
			final int responseCode = connection.getResponseCode();
			System.out.println("Response Code: " + responseCode);
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getHistory(final String mobileNumber)
	{

		final String API_ENDPOINT = "https://api.imagekit.io/v1/files?path=Wardrobe_Wizard/" + mobileNumber;
		final String PRIVATE_API_KEY = "private_KkfRbeeW5OwEvpVbIc8qhVPn0+E=";
		final List<String> historyList = new ArrayList<>();

		try
		{
			// Create URL object
			final URL url = new URL(API_ENDPOINT);

			// Create connection object
			final HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set the request method to GET
			connection.setRequestMethod("GET");

			// Set the Authorization header
			final String authHeader = "Basic "
					+ Base64.getEncoder().encodeToString((PRIVATE_API_KEY + ":").getBytes(StandardCharsets.UTF_8));
			connection.setRequestProperty("Authorization", authHeader);

			// Send the request and receive the response
			final int responseCode = connection.getResponseCode();

			// Read the response
			final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			final StringBuilder response = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null)
			{
				response.append(inputLine);
			}
			in.close();

			// Parse JSON response
			final ObjectMapper objectMapper = new ObjectMapper();
			final JsonNode rootNode = objectMapper.readTree(response.toString());

			for (final JsonNode node : rootNode)
			{
				final String urlValue = node.get("url").asText();
				historyList.add(urlValue);
				System.out.println("URL: " + urlValue);
			}


		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return historyList;
	}

}