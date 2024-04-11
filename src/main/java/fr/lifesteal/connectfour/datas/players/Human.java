package fr.lifesteal.connectfour.datas.players;

import fr.lifesteal.connectfour.Utils;
import fr.lifesteal.connectfour.datas.game.CaseColor;
import fr.lifesteal.connectfour.views.BoardView;

public class Human extends Player {

    public Human(String name, CaseColor color) {
        super(name, color);
    }

    public int play() {
        BoardView.askPlayerForColumn(this);
        return Utils.getKeyboardInput();
    }
}
