package controller;

import helpers.Helper;
import model.GameBoard;
import model.Players;
import view.ConsoleView;

public class GameController {

    public void startGame() {
        GameBoard gameBoard = new GameBoard();
        ConsoleView consoleView = new ConsoleView();
        checkWinner(gameBoard);
        getMove(gameBoard);
        consoleView.showBoard(gameBoard);
    }
    //TODO
    public void getMove(GameBoard gameBoard) {
        ConsoleView.sayGetMove();
        int x = ConsoleView.getCoordinateX();
        int y = ConsoleView.getCoordinateY();
        if (Helper.checkXCoordinate(x) || Helper.checkYCoordinate(y)) {
            gameBoard.setFigureToXY(x, y, getFigurePlayer(gameBoard));
        } else {
            ConsoleView.sayAboutError();
        }
    }

    public char getFigurePlayer(GameBoard gameBoard) {
        int countOfFirstFigure = 0, countOfSecondFigure = 0;
        for (int i = 0; i < GameBoard.NUMBER_OF_CELL; i++) {
            for (int j = 0; j < GameBoard.NUMBER_OF_CELL; j++) {
                switch (gameBoard.board[i][j]) {
                    case Players.figureOfFirstPlayer: countOfFirstFigure++;
                        break;
                    case Players.figureOfSecondPlayer: countOfSecondFigure++;
                        break;
                }
            }
        }
        if(countOfFirstFigure == countOfSecondFigure) {
            return Players.figureOfFirstPlayer;
        } else {
            return Players.figureOfSecondPlayer;
        }
    }

    public boolean checkWinner(GameBoard gameBoard) {
        while (true) {
            getMove(gameBoard);
            if (checkVertical(gameBoard) || checkHorizontal(gameBoard) || checkRightDiagonal(gameBoard) || checkLeftDiagonal(gameBoard)) {
                return true;
            }
        }
    }

    private boolean checkVertical(GameBoard gameBoard) {
        for (int i = 0; i < GameBoard.NUMBER_OF_CELL; i++) {
            if (gameBoard.board[1][i] == gameBoard.board[2][i] == gameBoard.board[3][i]) {
                if (checkSumbol(gameBoard.board[1][i])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkHorizontal(GameBoard gameBoard) {
        for (int i = 0; i < GameBoard.NUMBER_OF_CELL; i++) {
            if (gameBoard.board[i][1] == gameBoard.board[i][2] == gameBoard.board[i][3]) {
                if (checkSumbol(gameBoard.board[i][1])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkRightDiagonal(GameBoard gameBoard) {
        if (gameBoard.board[1][1] == gameBoard.board[2][2] == gameBoard.board[3][3]) {
            if (checkSumbol(gameBoard.board[1][1])) {
                return true;
            }
        }
    }

    private boolean checkLeftDiagonal(GameBoard gameBoard) {
        if (gameBoard.board[3][1] == gameBoard.board[2][2] == gameBoard.board[1][3]) {
            if (checkSumbol(gameBoard.board[3][1])) {
                return true;
            }
        }
    }

    private boolean checkSumbol(char sumbol) {
        if ((sumbol == Players.figureOfFirstPlayer) || (sumbol == Players.figureOfSecondPlayer)) {
            return true;
        }
        return false;
    }

}
