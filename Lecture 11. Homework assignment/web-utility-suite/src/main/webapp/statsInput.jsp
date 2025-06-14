<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
            background-color: #f0f0f0;
        }

        .form-container {
            max-width: 450px;
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

        .form-field {
            margin-bottom: 15px;
        }

        .form-field label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }

        .form-field input[type="text"],
        .form-field input[type="number"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        .radio-group {
            margin-bottom: 15px;
        }

        .radio-group label {
            margin-right: 10px;
            color: #333;
        }

        .error {
            color: red;
            font-size: 0.9em;
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
    <h2>Вычисление максимума, минимума или среднего</h2>
    <c:if test="${not empty requestScope.errorMessage}">
        <span class="error">${requestScope.errorMessage}</span>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/stats">
        <div class="form-field">
            <label for="num1">Число 1:</label>
            <input type="text" id="num1" name="num1"
                   value="${not empty requestScope.num1 ? requestScope.num1 : (param.num1 != null ? param.num1 : '')}"/>
        </div>

        <div class="form-field">
            <label for="num2">Число 2:</label>
            <input type="text" id="num2" name="num2"
                   value="${not empty requestScope.num2 ? requestScope.num2 : (param.num2 != null ? param.num2 : '')}"/>
        </div>

        <div class="form-field">
            <label for="num3">Число 3:</label>
            <input type="text" id="num3" name="num3"
                   value="${not empty requestScope.num3 ? requestScope.num3 : (param.num3 != null ? param.num3 : '')}"/>
        </div>

        <div class="radio-group">
            <label>
                <input type="radio" name="operation" value="max"
                       <c:if test="${requestScope.selectedOp == 'max' || empty requestScope.selectedOp}">checked</c:if> />
                Максимум
            </label>
            <label>
                <input type="radio" name="operation" value="min"
                       <c:if test="${requestScope.selectedOp == 'min'}">checked</c:if> />
                Минимум
            </label>
            <label>
                <input type="radio" name="operation" value="avg"
                       <c:if test="${requestScope.selectedOp == 'avg'}">checked</c:if> />Среднее арифметическое
            </label>
        </div>

        <button type="submit" class="submit-btn">Вычислить</button>
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/" class="back-link">← На главную</a>
    </p>
</div>
</body>
</html>
