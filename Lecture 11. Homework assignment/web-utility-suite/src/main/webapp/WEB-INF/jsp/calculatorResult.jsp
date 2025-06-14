<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
            background-color: #f5f5f5;
        }

        .result-container {
            max-width: 500px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .result-container h2 {
            margin-top: 0;
            color: #005b96;
        }

        .calculation {
            font-size: 1.2em;
            margin-bottom: 15px;
            color: #333;
            word-break: break-word;
        }

        .result-line {
            font-size: 1.3em;
            font-weight: bold;
            color: #007bff;
        }

        .back-link {
            display: inline-block;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="result-container">
    <h2>Result</h2>
    <c:set var="n1" value="${num1}"/>
    <c:set var="n2" value="${num2}"/>
    <c:set var="op" value="${operation}"/>
    <c:set var="res" value="${result}"/>

    <div class="calculation">
        <c:choose>
            <c:when test="${op == 'add'}">
                <fmt:formatNumber value="${n1}"/> + <fmt:formatNumber value="${n2}"/> =
            </c:when>

            <c:when test="${op == 'sub'}">
                <fmt:formatNumber value="${n1}"/> - <fmt:formatNumber value="${n2}"/> =
            </c:when>

            <c:when test="${op == 'mul'}">
                <fmt:formatNumber value="${n1}"/> × <fmt:formatNumber value="${n2}"/> =
            </c:when>

            <c:when test="${op == 'div'}">
                <fmt:formatNumber value="${n1}"/> ÷ <fmt:formatNumber value="${n2}"/> =
            </c:when>

            <c:when test="${op == 'pow'}">
                <fmt:formatNumber value="${n1}"/> ^ <fmt:formatNumber value="${n2}"/> =
            </c:when>

            <c:when test="${op == 'percent'}">
                <fmt:formatNumber value="${n1}"/>% от <fmt:formatNumber value="${n2}"/> =
            </c:when>

            <c:otherwise>
                Результат =
            </c:otherwise>
        </c:choose>

        <span class="result-line">
            <fmt:formatNumber value="${res}" maxFractionDigits="6"/>
        </span>
    </div>

    <a href="${pageContext.request.contextPath}/calculator" class="back-link">← Новая операция</a>
</div>
</body>
</html>
