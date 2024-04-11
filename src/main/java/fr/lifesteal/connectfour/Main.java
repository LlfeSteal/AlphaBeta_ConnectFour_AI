package fr.lifesteal.connectfour;

import fr.lifesteal.connectfour.controller.Board;

public class Main {
    public static void main(String[] args) {
        Board board = Board.getInstance();
        board.initBoard();
        board.start();
    }
}
