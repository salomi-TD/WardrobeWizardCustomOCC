<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="org.training.model.SimilarProduct" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
            display: flex;
        }

        .product-filter {
            width: 20%;
            padding: 20px;
            background-color: white;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .product-container {
    width: 80%;
    padding: 20px;
}

       .product {
  		  border: 1px solid #ddd;
          padding: 10px;
          background-color: white;
          box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
          box-sizing: border-box;
      }

        .product img {
            max-width: 100px;
            max-height: 100px;
            display: block;
            margin: 0 auto 10px;
        }
		
		.product-row {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px; /* Adjust the spacing between rows */
}

        .product a {
    text-decoration: none;
    color: #007bff;
    display: block;
    text-align: center;
}

    </style>

    <!-- Vue JS -->
	<script type="text/javascript" src="https://unpkg.com/vue@3.2.33/dist/vue.global.js"></script>

	<!-- Axios -->
	<script src="https://unpkg.com/axios@1.1.2/dist/axios.min.js"></script>

	<!-- jQuery (kept for potential other use-cases, though not needed here) -->
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

</head>
<body>
	<div id="app" style="display: flex; width: 100%">
		<input type="hidden" id="similarProducts" value="${similarProducts}"/>
		<input type="hidden" id="uniqueGenders" value="${uniqueGenders}"/>
		<input type="hidden" id="uniqueBrands" value="${uniqueBrands}"/>
		<input type="hidden" id="uniqueCurrencies" value="${uniqueCurrencies}"/>
	    <div class="product-filter">
	        <h2>Filter by Gender</h2>
	        <div v-for="(item, index) in uniqueGenders" :key="index" class="checkbox-label gender">
	            <input type="checkbox" class="checkbox" :value="item" @click="sendSelectedValuesToBackend">
	            {{ item }}
	        </div>
	
	        <h2>Filter by Brand</h2>
	        <div v-for="(item, index) in uniqueBrands" :key="index" class="checkbox-label brand">
	            <input type="checkbox" class="checkbox" :value="item" @click="sendSelectedValuesToBackend">
	            {{ item }}
	        </div>
	
	        <h2>Filter by Currency</h2>
	        <div v-for="(item, index) in uniqueCurrencies" :key="index" class="checkbox-label currency">
	            <input type="checkbox" class="checkbox" :value="item" @click="sendSelectedValuesToBackend">
	            {{ item }}
	        </div>
	    </div>
	
	    <div class="product-container">
    <h1>Similar Products</h1>
    <div class="container">
    
   
    <div class ="row" v-if="!filtersApplied">
        <c:forEach var="product" items="${similarProducts}">
            <div class="product col-md-4">
                <c:if test="${not empty product.images and not empty product.images[0]}">
                    <img src="${product.images[0]}" alt="${product.name}" />
                </c:if>
                <div><strong>Brand Name:</strong> ${product.brand_name}</div>
                <div><strong>Description:</strong> ${product.name}</div>
                <div><strong>Price:</strong>${product.currency} &nbsp ${product.price}</div>
                <div><strong>Reduced Price:</strong>${product.currency} &nbsp ${product.reduced_price}</div>
                <div><strong>Gender:</strong> ${product.gender}</div>
                <div><strong>URL:</strong> <a href="${product.url}">Click Me</a></div>
            </div>
        </c:forEach>
        {{similarProducts.products}}
    </div>
     </div>
    <div v-else>
        <div v-if="filteredProducts.length === 0">
            No products match the selected filters.
        </div>
        <div class="row">
        <div v-for="product in filteredProducts" :key="product.id" class="product col-md-4">
            <div v-if="product.images && product.images.length > 0">
                <img :src="product.images[0]" :alt="product.name" />
            </div>
            <div><strong>Brand Name:</strong> {{ product.brand_name }}</div>
            <div><strong>Description:</strong> {{ product.name }}</div>
            <div><strong>Price:</strong>{{ product.currency }} &nbsp {{ product.price }}</div>
            <div><strong>Reduced Price:</strong>{{ product.currency }} &nbsp {{ product.reduced_price }}</div>
            <div><strong>Gender:</strong> {{ product.gender }}</div>
            <div><strong>URL:</strong> <a :href="product.url">Click Me</a></div>
        </div>
        </div>
    </div>
</div>
	</div>
<script type="module" src="${pageContext.request.contextPath}/_ui/similar-products-v-app.js" ></script>
</body>
</html>