package fr.lifesteal.connectfour.datas.algorithm;

import fr.lifesteal.connectfour.controller.Board;
import fr.lifesteal.connectfour.controller.WinController;
import fr.lifesteal.connectfour.datas.game.Case;
import fr.lifesteal.connectfour.datas.game.CaseColor;
import fr.lifesteal.connectfour.datas.game.Column;
import fr.lifesteal.connectfour.datas.players.Player;

public class AlphaBetaAlgorithm implements Algorithm {

    private int nbrNoeuds = 0;

    public int getBetterColumn(Player player) {
        int alpha =  -10000000;
        int beta = 10000000;
        Board board = Board.getInstance();
        int bestColumnNumber = -1;
        int depth = 8;
        if (!board.isFirstPlay()) {
            for (Column currentColumn : board.getColumns()) {
                if (!currentColumn.isFull()) {
                    int columnNumberToTest = board.getColumns().indexOf(currentColumn);
                    Case casePlayed = board.playColumn(columnNumberToTest, player.getColor());
                    int points = this.getMin(depth-1,player, alpha, beta);

                    System.out.println("points : " + points);
                    System.out.println("alpha : " + alpha);
                    if (points > alpha) {
                        alpha = points;
                        System.out.println("CHANGE ALPHA");
                        bestColumnNumber = currentColumn.getColumnID();
                    }
                    casePlayed.setValue(CaseColor.WHITE);
                    System.out.println("BETA : " + beta);
                    if (alpha >= beta) {
                        break;
                    }
                } else System.out.println("COLUMN : " + currentColumn.getColumnID() + " IS FULL");
            }
            return bestColumnNumber;
        } else {
            this.nbrNoeuds = 0;
            return board.getColumnNumber()/2;
        }
    }

    public int getMin(int depth,Player player, int alpha, int beta) {
        Board board = Board.getInstance();
        WinController winController = board.getWinController();
        ++this.nbrNoeuds;

        player = board.getOpponent(player);

        if (depth == 0 || (board.getWinController().find4(player)!=-1)) {
            return winController.evaluate(player, depth);
        }

        for (Column currentColumn : board.getColumns()) {
            if (!currentColumn.isFull()) {
                Case casePlayed = board.playColumn(currentColumn.getColumnID(), player.getColor());
                int points = this.getMax(depth-1,player, alpha, beta);

                System.out.println("GET MAX : " + points);
                System.out.println("BETA : " + beta);
                if (points < beta) {
                    System.out.println("CHANGE BETA");
                    beta = points;
                }
                casePlayed.setValue(CaseColor.WHITE);
                if (beta <= alpha) {
                    return alpha;
                }
            }
        }
        return beta;
    }

    public int getMax(int depth,Player player, int alpha, int beta) {
        Board board = Board.getInstance();
        WinController winController = board.getWinController();
        ++this.nbrNoeuds;

        player = board.getOpponent(player);

        if (depth == 0 || (board.getWinController().find4(player)!=-1)) {
           return winController.evaluate(player, depth);
        }
        for (Column currentColumn : board.getColumns()) {
            if (!currentColumn.isFull()) {
                Case casePlayed = board.playColumn(currentColumn.getColumnID(), player.getColor());
                int points = this.getMin(depth-1,player, alpha, beta);

                if (points > alpha) {
                    alpha = points;
                }
                casePlayed.setValue(CaseColor.WHITE);
                if (alpha >= beta) {
                    return beta;
                }
            }
        }
        return alpha;
    }

}
