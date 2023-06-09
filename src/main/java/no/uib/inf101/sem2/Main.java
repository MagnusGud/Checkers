package no.uib.inf101.sem2;

import no.uib.inf101.sem2.checkers.controller.CheckersController;
import no.uib.inf101.sem2.checkers.model.CheckersBoard;
import no.uib.inf101.sem2.checkers.model.CheckersModel;
import no.uib.inf101.sem2.checkers.view.CheckersView;

import javax.swing.JFrame;

public class Main {
  public static void main(String[] args) {
    CheckersBoard board = new CheckersBoard(8, 8);
    CheckersModel model = new CheckersModel(board);
    model.setInitalBoard();
    model.outPutBoard();
    CheckersView view = new CheckersView(model);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(true);
    frame.setTitle("Checkers");
    frame.setContentPane(view);
    frame.pack();
    frame.addMouseListener(new CheckersController(model, view, model));
    frame.addKeyListener(new CheckersController(model, view, model));
    frame.setVisible(true);
  }
}
