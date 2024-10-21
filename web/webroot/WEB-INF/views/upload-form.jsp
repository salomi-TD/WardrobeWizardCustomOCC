<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Response Display</title>
    <style>
        .response-item {
            margin-bottom: 20px;
            border: 1px solid #ccc;
            padding: 10px;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
</head>
<body>
    <h1>Response Display</h1>

    <div id="app">
        <div class="container">
            <div class="image-container">
                <!-- Display the image -->
                <img src="${imageUrl}" alt="Image">
            </div>
            <div class="items-container">
                <c:forEach var="item" items="${response}" varStatus="loop">
                    <div class="checkbox-label">
                        <input type="checkbox" class="checkbox" :value="${item}" @click="handleCheckboxClick('${item}')">
                        ${item}
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Add a submit button to send the selected values to the controller -->
        <button @click="submitSelectedValues">Submit Selected Values</button>
    </div>

   <script src="${pageContext.request.contextPath}/_ui/category-v-app.js"></script>
</body>
</html>
