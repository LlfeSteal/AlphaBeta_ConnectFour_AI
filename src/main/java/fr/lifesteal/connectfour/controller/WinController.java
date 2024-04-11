package fr.lifesteal.connectfour.controller;

import fr.lifesteal.connectfour.datas.game.Case;
import fr.lifesteal.connectfour.datas.game.CaseColor;
import fr.lifesteal.connectfour.datas.game.Column;
import fr.lifesteal.connectfour.datas.players.Player;

public class WinController {

    public int find4(Player player) {
        int pointInLine = this.find4InLine(player);
        if (pointInLine != 1) return pointInLine;
        int pointInColumn = this.find4InColumn(player);
        if (pointInColumn != -1) return pointInColumn;
        int pointInDiagonal = this.find4InDiagonal(player);
        if (pointInDiagonal != -1) return pointInDiagonal;
        return -1;
    }

    private int find4InLine(Player player) {
        Board board = Board.getInstance();
        int count = -1;
        for (int row = 0; row < board.getRowNumber(); row++) {
            int points = this.find4Aligned(0, row, 1, 0, player.getColor());
            if (points != -1) {
                count = points;
                break;
            }
        }
        return count;
    }

    private int find4InColumn(Player player) {
        Board board = Board.getInstance();
        int count = -1;
        for (int col = 0; col < board.getColumnNumber() ; col++) {
            int points = this.find4Aligned(col, 0, 0, 1, player.getColor());
            if (points != -1) {
                count = points;
                break;
            }
        }
        return count;
    }

    private int find4InDiagonal(Player player) {
        Board board = Board.getInstance();
        int count = -1;
        for (int col = 0; col < board.getColumnNumber(); col++) {
            int points = this.find4Aligned(col, 0, 1, 1, player.getColor());
            if (points != -1) {
                count = points;
                return count;
            }
            points = this.find4Aligned(col, 0, -1, 1, player.getColor());
            if (points != -1) {
                count = points;
                return count;
            }
        }
        for (int row = 0; row < board.getRowNumber(); row++) {
            int points = this.find4Aligned(0, row, 1, 1, player.getColor());
            if (points != -1) {
                count = points;
                return count;
            }
            points = this.find4Aligned(board.getColumnNumber()-1, row, -1, 1, player.getColor());
            if (points != -1) {
                count = points;
                return count;
            }
        }
        return count;
    }


    private int find4Aligned(int startCol, int startRow, int directionCol, int directionRow, CaseColor color) {
        Board board = Board.getInstance();
        int count = 0;
        int currentColumnNumber = startCol;
        int currentRowNumber = startRow;
        while ((currentColumnNumber >= 0)
                && (currentColumnNumber < board.getColumnNumber())
                && (currentRowNumber >= 0)
                && (currentRowNumber < board.getRowNumber())) {
            Column currentColumn = board.getColumns().get(currentColumnNumber);
            Case currentCase = currentColumn.getCases().get(currentRowNumber);
            if (currentCase.getValue() == color) {
                ++count;
            } else {
                count = 1;
            }
            if (count >= 4) {
                return count;
            }
            currentColumnNumber += directionCol;
            currentRowNumber += directionRow;
        }
        return -1;
    }

    public int find(CaseColor color, int number) {
        Board board = Board.getInstance();
        int count = 0;

        for (int row = 0; row < board.getRowNumber(); row++) {
            count += this.findAligned(0, row,1,0, color, number);
        }

        for (int col = 0; col < board.getColumnNumber(); col++) {
            count += this.findAligned(col, 0,0,1, color, number);
        }

        for (int col = 0; col < board.getColumnNumber(); col++) {
            count += this.findAligned(col, 0, 1,1, color, number);
            count += this.findAligned(col, 0, -1,1, color, number);
        }

        for (int row = 0; row < board.getRowNumber(); row++) {
            count += this.findAligned(0, row, 1,1, color, number);
            count += this.findAligned(board.getRowNumber()-1, row, -1,1, color, number);
        }
        return count;
    }

    public int findAligned(int startCol, int startRow, int directionCol, int directionRow, CaseColor color, int number) {
        Board board = Board.getInstance();
        int tokenCount = 0;
        int alignCount = 0;
        int currentColumnNumber = startCol;
        int currentRowNumber = startRow;
        CaseColor previousCase = CaseColor.WHITE;
        while ((currentColumnNumber >= 0)
                && (currentColumnNumber < board.getColumnNumber())
                && (currentRowNumber >= 0)
                && (currentRowNumber < board.getRowNumber())) {
            Column currentColumn = board.getColumns().get(currentColumnNumber);
            Case currentCase = currentColumn.getCases().get(currentRowNumber);
            if (currentCase.getValue() == color) {
                if ((tokenCount == number) && ((previousCase == CaseColor.WHITE) || (currentCase.getValue() == CaseColor.WHITE))) {
                    ++alignCount;
                }
                tokenCount = 0;
                previousCase = currentCase.getValue();
            } else {
                ++tokenCount;
            }
            currentColumnNumber += directionCol;
            currentRowNumber += directionRow;
        }
        return alignCount;
    }

    public int evaluate(Player player, int depth) {
        int points = this.find4(player);
        if (points == 4) {
            System.out.println("GET MAX DEPTH RETURN  : " + depth);
            return 100000-(8-depth);
        } else if (points == -1){
            CaseColor oponent = CaseColor.WHITE;
            if (player.getColor() != CaseColor.YELLOW) {
                oponent = CaseColor.YELLOW;
            }
            return this.find(player.getColor(),3)*100- this.find(oponent, 3) * 100+ this.find(player.getColor(), 2) * 50- this.find(oponent, 2)*50;
        } else {
            return -100000+(8-depth);
        }
    }


}
