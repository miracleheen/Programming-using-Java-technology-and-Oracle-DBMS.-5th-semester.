<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Res</title>
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
            color: #333;
        }

        .numbers-list {
            list-style: none;
            padding: 0;
        }

        .numbers-list li {
            margin-bottom: 8px;
            font-size: 1.1em;
        }

        .numbers-list li.max {
            font-weight: bold;
            color: #007bff;
        }

        .numbers-list li.min {
            font-weight: bold;
            color: #d9534f;
        }

        .result-line {
            margin-top: 15px;
            font-size: 1.2em;
            font-weight: bold;
            color: #333;
        }

        .back-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            color: #007bff;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="result-container">
    <h2>Результат вычисления</h2>
    <ul class="numbers-list">
        <c:choose>
            <c:when test="${operation == 'max'}">
                <c:choose>
                    <c:when test="${num1 == resultValue}">
                        <li class="max">Число 1: <fmt:formatNumber value="${num1}" maxFractionDigits="5"/>
                            (максимальное)
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>Число 1: <fmt:formatNumber value="${num1}" maxFractionDigits="5"/></li>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${num2 == resultValue}">
                        <li class="max">Число 2: <fmt:formatNumber value="${num2}" maxFractionDigits="5"/>
                            (максимальное)
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>Число 2: <fmt:formatNumber value="${num2}" maxFractionDigits="5"/></li>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${num3 == resultValue}">
                        <li class="max">Число 3: <fmt:formatNumber value="${num3}" maxFractionDigits="5"/>
                            (максимальное)
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>Число 3: <fmt:formatNumber value="${num3}" maxFractionDigits="5"/></li>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${operation == 'min'}">
                <!-- Выделяем минимум -->
                <c:choose>
                    <c:when test="${num1 == resultValue}">
                        <li class="min">Число 1: <fmt:formatNumber value="${num1}" maxFractionDigits="5"/>
                            (минимальное)
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>Число 1: <fmt:formatNumber value="${num1}" maxFractionDigits="5"/></li>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${num2 == resultValue}">
                        <li class="min">Число 2: <fmt:formatNumber value="${num2}" maxFractionDigits="5"/>
                            (минимальное)
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>Число 2: <fmt:formatNumber value="${num2}" maxFractionDigits="5"/></li>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${num3 == resultValue}">
                        <li class="min">Число 3: <fmt:formatNumber value="${num3}" maxFractionDigits="5"/>
                            (минимальное)
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>Число 3: <fmt:formatNumber value="${num3}" maxFractionDigits="5"/></li>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${operation == 'avg'}">
                <li>Число 1: <fmt:formatNumber value="${num1}" maxFractionDigits="5"/></li>
                <li>Число 2: <fmt:formatNumber value="${num2}" maxFractionDigits="5"/></li>
                <li>Число 3: <fmt:formatNumber value="${num3}" maxFractionDigits="5"/></li>
            </c:when>
        </c:choose>
    </ul>

    <div class="result-line">
        <c:choose>
            <c:when test="${operation == 'max'}">
                Максимальное значение: <fmt:formatNumber value="${resultValue}" maxFractionDigits="5"/>
            </c:when>
            <c:when test="${operation == 'min'}">
                Минимальное значение: <fmt:formatNumber value="${resultValue}" maxFractionDigits="5"/>
            </c:when>
            <c:when test="${operation == 'avg'}">
                Среднее арифметическое: <fmt:formatNumber value="${resultValue}" maxFractionDigits="5"/>
            </c:when>
            <c:otherwise>
                Результат: <fmt:formatNumber value="${resultValue}" maxFractionDigits="5"/>
            </c:otherwise>
        </c:choose>
    </div>

    <a href="${pageContext.request.contextPath}/stats" class="back-link">← Ввести другие числа</a>
</div>
</body>
</html>


