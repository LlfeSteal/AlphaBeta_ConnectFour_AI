package fr.lifesteal.connectfour.controller;

import fr.lifesteal.connectfour.datas.game.Case;
import fr.lifesteal.connectfour.datas.game.CaseColor;
import fr.lifesteal.connectfour.datas.game.Column;
import fr.lifesteal.connectfour.datas.players.Player;
import fr.lifesteal.connectfour.views.BoardView;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final Board instance = new Board();
    private final int rowNumber = 6;
    private final int columnNumber = 7;
    private int playLeft = this.columnNumber*this.rowNumber;
    private List<Player> players;
    private List<Column> columns = new ArrayList<Column>();
    private WinController winController = new WinController();


    public static Board getInstance() {
        return instance;
    }

    public void initBoard() {
        this.createColumns();
        for (int i = 0; i < this.columnNumber; i++) {
            Column currentColumn = this.columns.get(i);
            for (int j = 0; j < this.rowNumber; j++) {
                currentColumn.addCase(new Case(j));
            }
        }
    }

    private void createColumns() {
        for (int i = 0; i < this.columnNumber; ++i) {
            this.columns.add(new Column(i));
        }
    }

    public List<Column> getColumns() {
        return columns;
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public void start() {
        this.players = PlayersController.getPlayers();
        boolean win = false;
        Player winner = null;
        do {
            for (Player player : this.players) {
                BoardView.displayBoard();
                this.playColumn(player.play(), player.getColor());
                win = isWin(player);
                winner = player;
                --playLeft;
            }
        } while (!win && playLeft >= 0);
        BoardView.displayBoard();
        if (winner != null) {
            BoardView.displayMessage(winner.getName() + " a gagné");
        } else {
            BoardView.displayMessage("Une erreur est survenue lors de la fin de la partie");
        }
    }

    public boolean isWin(Player player) {

        return false;
    }

    public Case playColumn(int columnNumber, CaseColor color) {
        System.out.println("COLUMN : " + columnNumber);
        Column selectedColumn = this.columns.get(columnNumber);
        return selectedColumn.setCaseColor(color);
    }

    public boolean isFirstPlay() {
        return this.columnNumber * this.rowNumber == this.playLeft;
    }

    @Override
    public String toString() {
        StringBuilder seralise = new StringBuilder();

        for (Column currentColumn : this.columns) {
            seralise.append("##Colonne n°").append(currentColumn.getColumnID()).append("\n");
            for (Case currentCase : currentColumn.getCases()) {
                seralise.append(currentCase.toString()).append("\n");
            }
        }
        return seralise.toString();
    }

    public WinController getWinController() {
        return winController;
    }

    public Player getOpponent(Player player) {
        for (Player currentPlayer : this.players) {
            if (player.getColor() != currentPlayer.getColor()) {
                return currentPlayer;
            }
        }
        return null;
    }
}
