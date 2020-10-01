package domain.db;

import domain.DomainException;
import domain.model.Game;

import java.util.ArrayList;
import java.util.List;

public class GameShop {
    private List<Game> games;

    public GameShop() {
        games = new ArrayList<>();
        Game GTAV = new Game("GTA V", 18, 30);
        Game ARK = new Game("ARK Survival Evolved", 10, 50);
        Game Fifa = new Game("Fifa 21", 13, 60);
        Game Minercraft = new Game("Minecraft", 7, 27);
        Game CallOfDuty = new Game("Call of Duty: Black Ops Cold War", 18, 60);
        Game FlightSimulator = new Game("Microsoft Flight Simulator Premium Deluxe Edition", 13, 120);
        this.voegToe(GTAV);
        this.voegToe(ARK);
        this.voegToe(Fifa);
        this.voegToe(Minercraft);
        this.voegToe(CallOfDuty);
        this.voegToe(FlightSimulator);
    }

    public void voegToe(Game game) {
        if (game == null)
            throw new DomainException("Game kan niet worden toegevoegd");
        games.add(game);
    }

    public List<Game> geefGames() {
        return games;
    }

}

