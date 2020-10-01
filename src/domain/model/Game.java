package domain.model;

import domain.DomainException;

public class Game {
    private int minimumLeeftijd;
    private int prijs;
    private final String naam;

    public Game(String naam, int minimumLeeftijd, int prijs) {
        setMinimumLeeftijd(minimumLeeftijd);
        setPrijs(prijs);
        if (naam == null || naam.trim().isEmpty() || naam.trim().length() < 3)
            throw new DomainException("Geen geldige naam");
        this.naam = naam;
    }

    public int getMinimumLeeftijd() {
        return minimumLeeftijd;
    }

    public void setMinimumLeeftijd(int minimumLeeftijd) {
        if (minimumLeeftijd < 0)
            throw new DomainException("Geen geldige leeftijd");
        this.minimumLeeftijd = minimumLeeftijd;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(int prijs) {
        if (prijs<0)
            throw new DomainException("Geen geldige prijs");
        this.prijs = prijs;
    }

    public String getNaam() {
        return naam;
    }

}
