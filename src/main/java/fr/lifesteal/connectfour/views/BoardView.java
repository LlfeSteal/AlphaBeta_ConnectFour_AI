package fr.lifesteal.connectfour.views;

import fr.lifesteal.connectfour.controller.Board;
import fr.lifesteal.connectfour.datas.game.Case;
import fr.lifesteal.connectfour.datas.game.Column;
import fr.lifesteal.connectfour.datas.players.Player;

public class BoardView {

    public static void displayBoard() {
        Board board = Board.getInstance();
        displayBoardHeaders();
        for (int i = 0; i < board.getRowNumber(); i++) {
            StringBuilder lineCases = new StringBuilder();
            for (int j = 0; j < board.getColumnNumber(); j++) {
                Column column = board.getColumns().get(j);
                Case currentCase = column.getCases().get(i);
                String position = "(" + column.getColumnID() + ", " + currentCase.getRowPosition() + ")";
                lineCases.append(currentCase.getValue()).append(" | ");
            }
            System.out.println(lineCases.toString());
        }
    }

    private static void displayBoardHeaders() {
        StringBuilder header = new StringBuilder();
        for (Column column : Board.getInstance().getColumns()) {
            header.append(column.getColumnID()).append(" | ");
        }
        System.out.println(header);
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void askForIAAlgorithm(String playerName) {
        System.out.println("Quel algorithm voulez-vous utiliser pour " + playerName);
        System.out.println("1) Alpha Beta");
        System.out.println("2) Min Max");
    }

    public static void askGameType() {
        System.out.println("Comment voulez-vous jouer ?");
        System.out.println("1) Joueur contre Joueur.");
        System.out.println("2) Joueur contre IA.");
        System.out.println("3) IA contre IA.");
    }

    public static void askPlayerForColumn(Player player) {
        System.out.println("Choisissez la colomne pour " + player.getName());
    }
}
