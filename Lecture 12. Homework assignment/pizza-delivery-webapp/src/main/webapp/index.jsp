<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Pizza Delivery</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&family=Roboto:wght@300;400;500&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css"/>
</head>
<body>
<header>
    <h1>Pizza Delivery</h1>
</header>
<main>
    <div class="features">
        <a href="${pageContext.request.contextPath}/order" class="feature-card">
            <h2>🍕 Заказать пиццу</h2>
            <p>Выберите пиццу, добавьте топпинги и введите данные доставки</p>
        </a>
        <a href="<c:url value='/delivery'/>" class="feature-card">
            <h2>🗺️ Карта доставки</h2>
            <p>Узнайте зону доставки</p>
        </a>
    </div>
</main>
<footer>
    &copy; 2025 Pizza Delivery
</footer>
<script src="${pageContext.request.contextPath}/static/js/script.js" defer></script>
</body>
</html>
