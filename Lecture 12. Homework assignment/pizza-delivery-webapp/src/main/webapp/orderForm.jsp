<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Заказ пиццы</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&family=Roboto:wght@300;400;500&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css"/>
    <script src="${pageContext.request.contextPath}/static/js/script.js" defer></script>
</head>
<body>
<header>
    <h1>Pizza Delivery</h1>
</header>
<main>
    <div class="form-container">
        <h2>Заказ пиццы</h2>

        <c:if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/submitOrder">
            <div class="form-field">
                <label for="pizzaType">Выберите пиццу:</label>
                <select id="pizzaType" name="pizzaType">
                    <option value="">-- Выберите --</option>
                    <c:forEach var="pt" items="${pizzaTypes}">
                        <c:set var="code" value="${pt.name()}"/>
                        <c:set var="dtCodes">
                            <c:forEach var="dt" items="${pt.defaultToppings}" varStatus="status">
                                ${dt.name()}<c:if test="${!status.last}">,</c:if>
                            </c:forEach>
                        </c:set>

                        <c:set var="dtNames">
                            <c:forEach var="dt" items="${pt.defaultToppings}" varStatus="status">
                                ${dt.displayName}<c:if test="${!status.last}">,</c:if>
                            </c:forEach>
                        </c:set>

                        <option value="${code}"
                                data-base-price="${pt.basePrice}"
                                data-default-toppings="${dtCodes}"
                                data-default-display-names="${dtNames}"
                                <c:if test="${selectedPizzaType == code}">selected</c:if>>
                                ${pt.displayName} (базовая цена: ${pt.basePrice} руб.)
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div id="baseToppingsInfo" class="form-field"
                 style="font-style: italic; color: var(--color-text-secondary);">
            </div>

            <div id="toppingsSection" class="form-field">
                <label>Добавить топпинги:</label>
                <c:forEach var="t" items="${allToppings}">
                    <div class="checkbox-item">
                        <label>
                            <input type="checkbox" name="topping" value="${t.name()}" data-price="${t.price}"
                                   <c:if test="${selectedToppings != null && selectedToppings.contains(t.name())}">checked</c:if> />
                                ${t.displayName} (+${t.price} руб.)
                        </label>
                    </div>
                </c:forEach>
            </div>

            <div id="livePrice" class="form-field">
                Итого: 0 руб.
            </div>

            <div class="form-field">
                <label for="name">Ваше имя:</label>
                <input type="text" id="name" name="name"
                       value="${fn:escapeXml(name)}" placeholder="Введите имя"/>
            </div>

            <div class="form-field">
                <label for="phone">Телефон:</label>
                <input type="text" id="phone" name="phone"
                       value="${fn:escapeXml(phone)}" placeholder="+7XXXXXXXXXX"/>
            </div>

            <div class="form-field">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email"
                       value="${fn:escapeXml(email)}" placeholder="example@mail.com"/>
            </div>

            <div class="form-field">
                <label for="address">Адрес доставки:</label>
                <textarea id="address" name="address" rows="3"
                          placeholder="Улица, дом, квартира...">${fn:escapeXml(address)}</textarea>
            </div>

            <button type="submit" class="submit-btn">Оформить заказ</button>
        </form>
    </div>
</main>
<footer>
    &copy; 2025 Pizza Delivery
</footer>
</body>
</html>
