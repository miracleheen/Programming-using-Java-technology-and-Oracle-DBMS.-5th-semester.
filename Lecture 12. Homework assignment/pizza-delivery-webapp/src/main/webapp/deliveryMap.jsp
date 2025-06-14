<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Карта зоны доставки</title>
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
    <div class="map-container">
        <h2>Зона доставки пиццы</h2>
        <img src="${pageContext.request.contextPath}/static/images/delivery_area.png"
             alt="Зона доставки"/>
        <p class="note">Доставка осуществляется в пределах области, выделенной на карте выше.</p>
        <a href="${pageContext.request.contextPath}/" class="back-link">← На главную</a>
    </div>
</main>
<footer>
    &copy; 2025 Pizza Delivery
</footer>
</body>
</html>
