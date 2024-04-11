package fr.lifesteal.connectfour.datas.players;

import fr.lifesteal.connectfour.datas.algorithm.Algorithm;
import fr.lifesteal.connectfour.datas.game.CaseColor;

public class IA extends Player {

    private final Algorithm algorithm;

    public IA(String name, CaseColor color, Algorithm algorithm) {
        super(name, color);
        this.algorithm = algorithm;
    }

    public int play() {
        int column = algorithm.getBetterColumn(this);
        System.out.println("CHOSEN COLUMN = " + column);
        return column;
    }
}
