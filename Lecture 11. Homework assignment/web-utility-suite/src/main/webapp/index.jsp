<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #e0f0ff, #f5f7fa);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        header {
            background-color: #1a73e8;
            color: white;
            padding: 20px;
            text-align: center;
        }

        header h1 {
            margin: 0;
            font-size: 1.8em;
        }

        header p {
            margin: 5px 0 0;
            font-size: 1em;
            opacity: 0.9;
        }

        main {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }

        .features {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            width: 100%;
            max-width: 800px;
        }

        .feature-card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
            transition: transform 0.2s ease, box-shadow 0.2s ease;
            text-decoration: none;
            color: #333;
        }

        .feature-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
        }

        .feature-card h3 {
            margin-top: 0;
            color: #1a3e72;
            font-size: 1.2em;
        }

        .feature-card p {
            font-size: 0.95em;
            margin: 10px 0 0;
            color: #555;
        }

        footer {
            text-align: center;
            padding: 10px;
            font-size: 0.9em;
            color: #666;
        }

        @media (max-width: 500px) {
            header h1 {
                font-size: 1.5em;
            }

            .feature-card {
                padding: 15px;
            }
        }
    </style>
</head>
<body>
<header>
    <h1>Welcome</h1>
    <p>Выберите нужную функцию приложения</p>
</header>
<main>
    <div class="features">
        <a href="${pageContext.request.contextPath}/quote" class="feature-card">
            <h3>Цитата Линуса</h3>
            <p>Показать цитату Линуса Торвальдса.</p>
        </a>
        <a href="${pageContext.request.contextPath}/stats" class="feature-card">
            <h3>Максимум/Минимум/Среднее</h3>
            <p>Вычисление max, min или среднего из трёх чисел</p>
        </a>
        <a href="${pageContext.request.contextPath}/textstats" class="feature-card">
            <h3>Статистика по тексту</h3>
            <p>Анализ текста: гласные, согласные, пунктуация</p>
        </a>
        <a href="${pageContext.request.contextPath}/calculator" class="feature-card">
            <h3>Калькулятор</h3>
            <p>Сложение, вычитание, умножение, деление, степень, процент</p>
        </a>
    </div>
</main>
<footer>
    &copy; End
</footer>
</body>
</html>
