package ui;

import domain.db.GameShop;
import domain.model.Game;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    GameShop gameShop = new GameShop();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = "";
        String action = "home";

        if (request.getParameter("command") != null) action = request.getParameter("command");

        switch (action) {
            case "overzicht":
                destination = overzicht(request, response);
                break;
            case "formulier":
                destination = formulier(request, response);
                break;
            default:
                destination = home(request, response);
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String home(HttpServletRequest request, HttpServletResponse response) {
        return "index.jsp";
    }

    private String overzicht(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("games", gameShop.geefGames());
        return "index.jsp";
    }

    private String formulier(HttpServletRequest request, HttpServletResponse response) {

        if (request.getMethod().equals("POST")) {
            ArrayList<String> errors = new ArrayList<>();

            controleerInvoer(request, errors);

            if (errors.size() <= 0) {
                voegToe(request, response);
                return "index.jsp";
            }

            request.setAttribute("errors", errors);
        }
        return "formulier.jsp";

    }

    private void controleerInvoer(HttpServletRequest request, ArrayList<String> errors) {
        String naamGame = request.getParameter("naam");
        String prijs = request.getParameter("prijs");
        String minimumLeeftijd = request.getParameter("minimumLeeftijd");

        if (naamGame == null || naamGame.trim().equals("")) {
            errors.add("De game naam mag niet leeg zijn.");
            request.setAttribute("prevNaamGame", naamGame);
            request.setAttribute("prevPrijs", prijs);
            request.setAttribute("prevMinimumLeeftijd", minimumLeeftijd);
        }

        if (prijs.trim().isEmpty() || prijs == null || Double.parseDouble(prijs) < 0) {
            errors.add("De prijs mag niet leeg zijn en moet groter zijn dan 0");
            request.setAttribute("prevNaamGame", naamGame);
            request.setAttribute("prevPrijs", prijs);
            request.setAttribute("prevMinimumLeeftijd", minimumLeeftijd);
        }

        if (minimumLeeftijd.trim().isEmpty() || minimumLeeftijd == null || Integer.parseInt(minimumLeeftijd) < 0) {
            errors.add("De minimum leeftijd mag niet leeg zijn en moet groter zijn dan 0");
            request.setAttribute("prevNaamGame", naamGame);
            request.setAttribute("prevPrijs", prijs);
            request.setAttribute("prevMinimumLeeftijd", minimumLeeftijd);
        }

    }

    private String voegToe(HttpServletRequest request, HttpServletResponse response) {
        String naamGame = request.getParameter("naam");
        String prijs = request.getParameter("prijs");
        String minimumLeeftijd = request.getParameter("minimumLeeftijd");

        try {
            double prijsDouble = Double.parseDouble(prijs);
            int minimulLeeftijdInt = Integer.parseInt(minimumLeeftijd);

            gameShop.voegToe(new Game(naamGame, minimulLeeftijdInt, prijsDouble));
            request.setAttribute("games", gameShop.geefGames());

        } catch (NumberFormatException ex) {
            return "formulier.jsp";
        }
        return "index.jsp";
    }

}
