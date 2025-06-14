<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>res</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
            background-color: #f5f5f5;
        }

        .result-container {
            max-width: 700px;
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

        .section {
            margin-bottom: 20px;
        }

        .section h3 {
            margin-bottom: 8px;
            color: #0069d9;
        }

        .stats-line {
            font-size: 1.1em;
            margin-bottom: 5px;
            color: #333;
        }

        .chars-list {
            margin: 0;
            padding: 0;
            list-style: none;
            display: flex;
            flex-wrap: wrap;
        }

        .chars-list li {
            margin-right: 8px;
            font-size: 1.1em;
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

        .original-text {
            white-space: pre-wrap;
            overflow-wrap: break-word;
            word-break: break-word;
            background: #f9f9f9;
            padding: 10px;
            border-radius: 4px;
            border: 1px solid #ddd;
            max-height: 400px;
            overflow-y: auto;
        }
    </style>
</head>
<body>
<div class="result-container">
    <h2>Результаты анализа текста</h2>

    <div class="section">
        <h3>Исходный текст:</h3>
        <div class="original-text">
            <c:out value="${text}" escapeXml="true"/>
        </div>
    </div>

    <div class="section">
        <h3>Гласные</h3>
        <div class="stats-line">Количество: ${countVowels}</div>
        <c:choose>
            <c:when test="${empty foundVowels}">
                <div>Гласных символов не найдено</div>
            </c:when>
            <c:otherwise>
                <div>Найденные уникальные гласные:</div>
                <ul class="chars-list">
                    <c:forEach var="ch" items="${foundVowels}">
                        <li><c:out value="${ch}"/></li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="section">
        <h3>Согласные</h3>
        <div class="stats-line">Количество: ${countConsonants}</div>
        <c:choose>
            <c:when test="${empty foundConsonants}">
                <div>Согласных символов не найдено</div>
            </c:when>
            <c:otherwise>
                <div>Найденные уникальные согласные:</div>
                <ul class="chars-list">
                    <c:forEach var="ch" items="${foundConsonants}">
                        <li><c:out value="${ch}"/></li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="section">
        <h3>Знаки препинания</h3>
        <div class="stats-line">Количество: ${countPunct}</div>
        <c:choose>
            <c:when test="${empty foundPunctuations}">
                <div>Знаков препинания не найдено</div>
            </c:when>
            <c:otherwise>
                <div>Найденные уникальные знаки препинания:</div>
                <ul class="chars-list">
                    <c:forEach var="ch" items="${foundPunctuations}">
                        <li><c:out value="${ch}"/></li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>

    <a href="${pageContext.request.contextPath}/textstats" class="back-link">← Ввести другой текст</a>
</div>
</body>
</html>
