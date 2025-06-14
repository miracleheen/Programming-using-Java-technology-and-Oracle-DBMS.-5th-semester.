<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Калькулятор</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
            background-color: #eef2f5;
        }

        .form-container {
            max-width: 500px;
            margin: 50px auto;
            background: #ffffff;
            padding: 25px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .form-container h2 {
            margin-top: 0;
            color: #1a3e72;
            text-align: center;
        }

        .form-field {
            margin-bottom: 20px;
        }

        .form-field label {
            display: block;
            margin-bottom: 5px;
            color: #333;
            font-weight: bold;
        }

        .form-field input[type="text"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1em;
        }

        .radio-group {
            margin-bottom: 20px;
        }

        .radio-item {
            margin-bottom: 8px;
        }

        .radio-item input[type="radio"] {
            margin-right: 8px;
        }

        .radio-item label {
            color: #333;
            font-size: 1em;
            cursor: pointer;
        }

        .error {
            color: #b00020;
            font-size: 0.9em;
            margin-bottom: 15px;
            display: block;
            background: #fdecea;
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #f5c6cb;
        }

        .submit-btn {
            width: 100%;
            background-color: #1a73e8;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1em;
            transition: background-color 0.2s ease;
        }

        .submit-btn:hover {
            background-color: #1558b0;
        }

        .back-link {
            display: inline-block;
            margin-top: 20px;
            color: #1a73e8;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Калькулятор</h2>
    <c:if test="${not empty requestScope.errorMessage}">
        <span class="error">${requestScope.errorMessage}</span>
    </c:if>
    <form method="post" action="${pageContext.request.contextPath}/calculator">
        <div class="form-field">
            <label for="num1">Число 1:</label>
            <input type="text" id="num1" name="num1"
                   value="${not empty requestScope.num1 ? requestScope.num1 : (param.num1 != null ? param.num1 : '')}"
                   placeholder="Введите число 1"/>
        </div>
        <div class="form-field">
            <label for="num2">Число 2:</label>
            <input type="text" id="num2" name="num2"
                   value="${not empty requestScope.num2 ? requestScope.num2 : (param.num2 != null ? param.num2 : '')}"
                   placeholder="Введите число 2"/>
        </div>

        <div class="radio-group">
            <div class="radio-item">
                <label>
                    <input type="radio" name="operation" value="add"
                           <c:if test="${requestScope.selectedOp == 'add' || empty requestScope.selectedOp}">checked</c:if> />
                    Сложение
                </label>
            </div>
            <div class="radio-item">
                <label>
                    <input type="radio" name="operation" value="sub"
                           <c:if test="${requestScope.selectedOp == 'sub'}">checked</c:if> />
                    Вычитание
                </label>
            </div>
            <div class="radio-item">
                <label>
                    <input type="radio" name="operation" value="mul"
                           <c:if test="${requestScope.selectedOp == 'mul'}">checked</c:if> />
                    Умножение
                </label>
            </div>
            <div class="radio-item">
                <label>
                    <input type="radio" name="operation" value="div"
                           <c:if test="${requestScope.selectedOp == 'div'}">checked</c:if> />
                    Деление
                </label>
            </div>
            <div class="radio-item">
                <label>
                    <input type="radio" name="operation" value="pow"
                           <c:if test="${requestScope.selectedOp == 'pow'}">checked</c:if> />
                    Возведение в степень
                </label>
            </div>
            <div class="radio-item">
                <label>
                    <input type="radio" name="operation" value="percent"
                           <c:if test="${requestScope.selectedOp == 'percent'}">checked</c:if> />
                    Процент
                </label>
            </div>
        </div>

        <button type="submit" class="submit-btn">Вычислить</button>
    </form>

    <p>
        <a href="${pageContext.request.contextPath}/" class="back-link">← На главную</a>
    </p>
</div>
</body>
</html>