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


import javax.swing.*;

public class Game {
    private GameBoard board;
    private GamePlayer[] gamePlayers = new GamePlayer[2];
    public int playersTurn = 0;

    public Game(){

        this.board = new GameBoard(this);
    }

    public  void initGame (){
        gamePlayers[0] = new GamePlayer('X');
        gamePlayers[1] = new GamePlayer('O');
    }

    void passTurn(){
        if(playersTurn == 0)
            playersTurn = 1;
        else
            playersTurn = 0;
    }

    GamePlayer getCurrentPlayer(){
        return gamePlayers[playersTurn];
    }

    void showMessage(String messageText){
        JOptionPane.showMessageDialog(board, messageText);
    }

    


}
    

