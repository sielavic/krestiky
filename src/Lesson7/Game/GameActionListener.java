/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lesson7.Game;

/**
 *
 * @author stas
 */


//Портировать методы AI и определения победы в ООП-реализацию.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;



    public GameActionListener(int row, int cell, GameButton gButton) {
        this.row = row;
        this.cell = cell;
        this.button = gButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();

        if (board.isTurnable(row, cell)) {
            updateByPlayersData(board);

            if (board.isFull()) {
                board.getGame().showMessage("Ничья");
                board.emptyField();
            } else {
                updateByAiData(board);
            }
        } else {
            board.getGame().showMessage("Некорректный ход!");
        }
    }

    private void updateByPlayersData(GameBoard board) {
        board.updateGameField(row, cell);

        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin(button.getBoard().getGame().getCurrentPlayer().getPlayerSign())) {
            button.getBoard().getGame().showMessage("Вы выиграли!");
            board.emptyField();
            board.getGame().passTurn();
        } else {
            board.getGame().passTurn();
        }

    }

    private void updateByAiData(GameBoard board) {

        boolean moveFound = false;
        int x=-1, y=-1;
        char comp = board.getGame().getCurrentPlayer().getPlayerSign();
        int dimension = GameBoard.dimension;


        for(int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board.gameField[i][j] == GameBoard.nullSymbol) {
                    if (i - 1 >= 0 && j - 1 >= 0 && board.gameField[i - 1][j - 1] == comp) {
                        y = i;
                        x = j;
                        moveFound = true;
                    } else if (i - 1 >= 0 && board.gameField[i - 1][j] == comp) {
                        y = i;
                        x = j;
                        moveFound = true;
                    } else if (i - 1 >= 0 && j + 1 < dimension && board.gameField[i - 1][j + 1] == comp) {
                        y = i;
                        x = j;
                        moveFound = true;
                    } else if (j + 1 < dimension && board.gameField[i][j + 1] == comp) {
                        y = i;
                        x = j;
                        moveFound = true;
                    } else if (i + 1 < dimension && j + 1 < dimension && board.gameField[i + 1][j + 1] == comp) {
                        y = i;
                        x = j;
                        moveFound = true;
                    } else if (i + 1 < dimension && board.gameField[i + 1][j] == comp) {
                        y = i;
                        x = j;
                        moveFound = true;
                    } else if (i + 1 < dimension && j - 1 >= 0 && board.gameField[i + 1][j - 1] == comp) {
                        y = i;
                        x = j;
                        moveFound = true;
                    } else if (j - 1 >= 0 && board.gameField[i][j - 1] == comp) {
                        y = i;
                        x = j;
                        moveFound = true;
                    }
                }

                if (moveFound) {
                    break;
                }
            }

            if (moveFound) {
                break;
            }

        }
        if(x == -1){
            Random rnd = new Random();

            do {
                x = rnd.nextInt(GameBoard.dimension);
                y = rnd.nextInt(GameBoard.dimension);
            } while (!board.isTurnable(x, y));

        }
        board.updateGameField(x, y);
        int cellIndex = GameBoard.dimension * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin(button.getBoard().getGame().getCurrentPlayer().getPlayerSign())) {
            System.out.println("komp win");
            button.getBoard().getGame().showMessage("Компьютер выиграл!");
            board.emptyField();
        } else if(board.isFull()) {
            board.getGame().showMessage("Ничья");
            board.emptyField();
        } else {
            board.getGame().passTurn();
        }




        }

}
