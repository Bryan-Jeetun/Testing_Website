<%@ page import="java.util.Collection" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Voeg Game Toe</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <%
        Collection<String> errors = (Collection<String>) request.getAttribute("errors");
    %>
    <header>
        <h1>
        </h1>
        <jsp:include page="nav.jsp"/>
        <h2>Voeg een game toe</h2>

    </header>
    <main>
        <c:if test="${not empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items = "${errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>

        <form method="post" action="Controller?command=formulier" novalidate>
            <p><label for="naam">Naam Game</label><input type="text" autocomplete="off" id="naam" name="naam"
                                                                value="${prevNaamGame}" required></p>
            <p><label for="prijs">Prijs</label><input type="number" id="prijs" name="prijs"
                                                                   value="${prevPrijs}" autocomplete="off" required></p>
            <p><label for="minimumLeeftijd">Minimum Leeftijd</label><input type="number" id="minimumLeeftijd" autocomplete="off" name="minimumLeeftijd"
                                                      value="${prevMinimumLeeftijd}" required></p>
            <p><input type="submit" id="vestuur" value="Voeg Toe"></p>

        </form>
    </main>
    <footer>
        &copy; Made with Love -Bryan
    </footer>
</div>
</body>
</html>
