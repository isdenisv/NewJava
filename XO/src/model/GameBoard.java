package model;

public abstract class GameBoard {

    public final static final int NUMBER_OF_CELL = 3;

    public final char[][] board = new char[NUMBER_OF_CELL][NUMBER_OF_CELL];

    public void setFigureToXY(int x, int y, char figure) {
        this.board[x][y] = figure;
    }



}
