package fr.lifesteal.connectfour.datas.players;

import fr.lifesteal.connectfour.datas.game.CaseColor;

public abstract class Player {

    private String name;
    private CaseColor color;

    public Player(String name, CaseColor color) {
        this.name = name;
        this.color = color;
    }

    public abstract int play();

    public String getName() {
        return name;
    }

    public CaseColor getColor() {
        return color;
    }
}
