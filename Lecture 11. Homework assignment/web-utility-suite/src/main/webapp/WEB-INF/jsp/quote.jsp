<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
            background-color: #f5f5f5;
        }

        .quote-container {
            max-width: 600px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .quote-text {
            font-size: 1.2em;
            color: #333;
            margin-bottom: 15px;
        }

        .quote-author {
            text-align: right;
            font-style: italic;
            color: #555;
        }
    </style>
</head>
<body>
<div class="quote-container">
    <div class="quote-text">
        <c:out value="${quoteText}"/>
    </div>
    <div class="quote-author">
        Linus Torvalds
    </div>
</div>
</body>
</html>



