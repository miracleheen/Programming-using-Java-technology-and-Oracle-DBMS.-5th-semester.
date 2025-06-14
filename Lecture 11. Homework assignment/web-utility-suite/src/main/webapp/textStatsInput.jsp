<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Статистика</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
            background-color: #f0f0f0;
        }

        .form-container {
            max-width: 600px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .form-container h2 {
            margin-top: 0;
            color: #333;
        }

        textarea {
            width: 100%;
            height: 200px;
            padding: 8px;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
            font-size: 1em;
        }

        .error {
            color: red;
            margin-bottom: 15px;
            display: block;
        }

        .submit-btn {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
        }

        .submit-btn:hover {
            background-color: #0056b3;
        }

        .back-link {
            display: inline-block;
            margin-top: 15px;
            color: #007bff;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Введите текст для анализа</h2>
    <c:if test="${not empty requestScope.errorMessage}">
        <span class="error">${requestScope.errorMessage}</span>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/textstats">
        <textarea name="text" placeholder="Введите ваш текст...">${fn:escapeXml(requestScope.text)}</textarea>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <br/>
        <button type="submit" class="submit-btn">Анализировать</button>
    </form>
    <p>
        <a href="${pageContext.request.contextPath}/" class="back-link">← На главную</a>
    </p>
</div>
</body>
</html>
