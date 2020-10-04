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

@WebServlet("/Controller") //We even aan dat dit over de servlet gaat genaam /Controller
public class Controller extends HttpServlet { //Deze klasse maakt gebruik van de servlet
    GameShop gameShop = new GameShop(); //Maken een gameShop aan

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response); //We voeren een processrequest uit indien een post request
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response); //We voeren ene processrequest uit indien een get request
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = "";
        String action = "overzicht"; //Default pagina is de overzichtpagina

        if (request.getParameter("command") != null) action = request.getParameter("command"); //Als de command in de link ingevuld is, dan is action gelijk aan deze command
        switch (action) { //switch statement om te bepalen wat de destioantion moet zijn met deze action
            case "overzicht":
                destination = overzicht(request, response);
                break;
            case "formulier":
                destination = formulier(request, response);
                break;
            default:
                destination = overzicht(request, response);
        }
        request.getRequestDispatcher(destination).forward(request, response); //Ga naar de pagina dat hoort bij destination
    }

    private String overzicht(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("games", gameShop.geefGames()); //Geef aan parameter games de games mee om deze te kunnen displayen op de pagina
        return "index.jsp"; //geef de index pagina (overzicht) terug met deze parameter erbij
    }

    private String formulier(HttpServletRequest request, HttpServletResponse response) {
        if (request.getMethod().equals("POST")) { //Kijk na of het wel een post request is (er wordt iets verstuurd)
            ArrayList<String> errors = new ArrayList<>(); //Stel een errors array op om hierin al de errors bij te houden

            controleerInvoer(request, errors); //Roep de methode op om de invoer te controleren

            if (errors.size() == 0) { //Als de errors leeg zijn, voer dan de voegtoe functie uit
                voegToe(request, response);
                return "index.jsp"; //geef de overview pagina terug na het toevoegen.
            }

            request.setAttribute("errors", errors); //geef aan de response de errors mee
        }
        return "formulier.jsp"; //geef het formulier terug aangezien de error size > 0

    }

    private void controleerInvoer(HttpServletRequest request, ArrayList<String> errors) {
        String naamGame = request.getParameter("naam"); //vraag de naam op van de post request
        String prijs = request.getParameter("prijs"); //vraag de prijs op van de post request
        String minimumLeeftijd = request.getParameter("minimumLeeftijd"); //vraag de minimulleeftijd op uit de post request

        if (naamGame == null || naamGame.equals("")){ //voer een paar checks uit op de naam
            errors.add("De game naam mag niet leeg zijn."); //indien een van deze fouten van toepassing zijn, voegen we een error toe aan de array errors
            request.setAttribute("prevNaamGame", naamGame); //geef mee dat de naam van de game gelijk is aan uw String prevNaamgame (om in uw form de oude naam weer in te evullen)
            request.setAttribute("prevPrijs", prijs);
            request.setAttribute("prevMinimumLeeftijd", minimumLeeftijd);
        }

        //idem
        if (prijs.trim().isEmpty() || prijs == null || Integer.parseInt(prijs) < 0) {
            errors.add("De prijs mag niet leeg zijn en moet groter zijn dan 0");
            request.setAttribute("prevNaamGame", naamGame);
            request.setAttribute("prevPrijs", prijs);
            request.setAttribute("prevMinimumLeeftijd", minimumLeeftijd);
        }

        //idem
        if (minimumLeeftijd.trim().isEmpty() || minimumLeeftijd == null || Integer.parseInt(minimumLeeftijd) < 0) {
            errors.add("De minimum leeftijd mag niet leeg zijn en moet groter zijn dan 0");
            request.setAttribute("prevNaamGame", naamGame);
            request.setAttribute("prevPrijs", prijs);
            request.setAttribute("prevMinimumLeeftijd", minimumLeeftijd);
        }

    }

    private String voegToe(HttpServletRequest request, HttpServletResponse response) {
        String naamGame = request.getParameter("naam"); //vraag de naam op van de post request
        String prijs = request.getParameter("prijs"); //vraag de prijs op van de post request
        String minimumLeeftijd = request.getParameter("minimumLeeftijd"); //vraag de minimulleeftijd op uit de post request

        try { //probeer:
            int prijsInt = Integer.parseInt(prijs); //om de String prijs om te zetten naar een int
            int minimulLeeftijdInt = Integer.parseInt(minimumLeeftijd); //probeer om de String minimumleeftijd om te zetten naar een int

            gameShop.voegToe(new Game(naamGame, minimulLeeftijdInt, prijsInt)); //voeg een nieuw game obect toe aan de gameshop die we hierboven hebben aangemaakt
            request.setAttribute("games", gameShop.geefGames()); //zet de String games gelijk aan alle games van de gameshop (array) om deze op de webpagina te kunnen gebruiken

        } catch (NumberFormatException ex) { //indien er een fout is bij het converteren van String -> int of bij het toevoegen van de game, vang deze error op
            return "formulier.jsp"; //geef in dit geval het formulier terug
        }
        return "index.jsp"; //anders geef je de index (overzicht pagina) terug
    }

}
