package fr.lifesteal.connectfour.controller;

import fr.lifesteal.connectfour.Utils;
import fr.lifesteal.connectfour.datas.algorithm.Algorithm;
import fr.lifesteal.connectfour.datas.algorithm.AlphaBetaAlgorithm;
import fr.lifesteal.connectfour.datas.algorithm.MinMaxAlgorithm;
import fr.lifesteal.connectfour.datas.game.CaseColor;
import fr.lifesteal.connectfour.datas.players.Human;
import fr.lifesteal.connectfour.datas.players.IA;
import fr.lifesteal.connectfour.datas.players.Player;
import fr.lifesteal.connectfour.views.BoardView;

import java.util.ArrayList;
import java.util.List;

public class PlayersController {

    public static List<Player> getPlayers() {
        BoardView.askGameType();
        int choice = Utils.getKeyboardInput();
        return getPlayerByUserChoice(choice);
    }

    private static List<Player> getPlayerByUserChoice(int choice) {
        List<Player> playersChosen = new ArrayList<Player>();
        switch (choice) {
            case 1:
                playersChosen.add(new Human("Player1", CaseColor.YELLOW));
                playersChosen.add(new Human("Player 2", CaseColor.RED));
                break;
            case 2:
                playersChosen.add(new Human("Player 1", CaseColor.YELLOW));
                BoardView.askForIAAlgorithm("Player 2");
                playersChosen.add(new IA("Player 2", CaseColor.RED, getChosenAlgorithm(Utils.getKeyboardInput())));
                break;
            case 3:
                BoardView.askForIAAlgorithm("Player 1");
                playersChosen.add(new IA("Player 1", CaseColor.YELLOW, getChosenAlgorithm(Utils.getKeyboardInput())));
                BoardView.askForIAAlgorithm("Player 2");
                playersChosen.add(new IA("Player 2",CaseColor.RED, getChosenAlgorithm(Utils.getKeyboardInput())));
                break;
            default:
                return getPlayers();
        }
        return playersChosen;
    }

    private static Algorithm getChosenAlgorithm(int choice) {
        switch (choice) {
            case 1:
                return new AlphaBetaAlgorithm();
            case 2:
                return new MinMaxAlgorithm();
            default:
                return new AlphaBetaAlgorithm();
        }
    }
}
