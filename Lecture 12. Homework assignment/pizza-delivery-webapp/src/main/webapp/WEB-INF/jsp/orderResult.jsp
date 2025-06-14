<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Подтверждение заказа</title>
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
    <div class="result-container">
        <h2>Ваш заказ принят!</h2>
        <c:set var="order" value="${order}"/>

        <div class="detail"><strong>Пицца:</strong> ${order.pizzaType.displayName}</div>
        <div class="detail"><strong>Базовая цена:</strong> ${order.pizzaType.basePrice} руб.</div>

        <c:if test="${not empty order.pizzaType.defaultToppings}">
            <div class="detail">
                <strong>Базовые ингредиенты:</strong>
                <c:forEach var="dt" items="${order.pizzaType.defaultToppings}" varStatus="status">
                    <c:out value="${dt.displayName}"/>
                    <c:if test="${!status.last}">, </c:if>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${not empty order.extraToppings}">
            <div class="detail">
                <strong>Дополнительные топпинги:</strong>
                <c:forEach var="et" items="${order.extraToppings}" varStatus="status">
                    <c:out value="${et.displayName}"/> (+${et.price} руб.)
                    <c:if test="${!status.last}">, </c:if>
                </c:forEach>
            </div>
        </c:if>

        <div class="detail"><strong>Итого к оплате:</strong> ${order.totalPrice} руб.</div>
        <hr/>

        <div class="detail"><strong>Имя:</strong> <c:out value="${order.customerName}"/></div>
        <div class="detail"><strong>Телефон:</strong> <c:out value="${order.phone}"/></div>
        <div class="detail"><strong>Email:</strong> <c:out value="${order.email}"/></div>
        <div class="detail"><strong>Адрес доставки:</strong> <c:out value="${order.address}"/></div>
        <hr/>

        <div class="success">Заказ оформлен! Чек будет отправлен на вашу почту: <c:out value="${order.email}"/>.</div>

        <a href="${pageContext.request.contextPath}/order" class="back-link">← Сделать новый заказ</a>
    </div>
</main>
<footer>
    &copy; 2025 Pizza Delivery
</footer>
</body>
</html>
