<%@ page import="domain.model.Game" %>
<%@ page import="java.util.Collection" %>
<%@ page import="ui.Controller" %>
<%@ page import="domain.model.Game" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
        </h1>
        <jsp:include page="nav.jsp"/>
        <h2>Overzicht Games</h2>

        <%
            Collection<Game> games = (Collection<Game>) request.getAttribute("games");
        %>
    </header>
    <main>


        <table>
            <tr>
                <th>Naam</th>
                <th>Minimum Leeftijd</th>
                <th>Prijs</th>
            </tr>

            <%for (Game game : games) {%>

            <tr>
                <td><%=game.getNaam()%>
                </td>
                <td><%=game.getMinimumLeeftijd()%> jaar
                </td>
                <td>€ <%=game.getPrijs()%>
                </td>
            </tr>

            <%
                }
            %>

            <caption>Overzicht prijzen</caption>
        </table>
    </main>
    <footer>
        &copy; Made with Love -Bryan
    </footer>
</div>
</body>
</html>