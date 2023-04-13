package no.uib.inf101.sem2.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;


import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.model.CheckersBoard;
import no.uib.inf101.sem2.model.checkerspiece.AbstractPiece;


public class CheckersView extends JPanel {
    // Feltvariabler
    ViewableCheckersModel view;
    ColorTheme colorTheme;

    public CheckersView(ViewableCheckersModel view, CheckersBoard board) {
        this.view = view;
        this.colorTheme = new DefaultColorTheme();
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(600, 600));
    }

    /**
     * @param g draws the game
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGame(g2);
    }

    /**
     * DrawsTheGame
     * 
     * @param g2
     * 
     */
    public void drawGame(Graphics2D g2) {
        Rectangle2D rectangle = new Rectangle2D.Double(0 , 0, this.getWidth() , this.getHeight() );
        drawCells(g2, view.getTilesOnBoard(), new CellPositionToPixelConverter(rectangle, view.getDimension(), 0), colorTheme);
    }

    /**
     * 
     * @param g         "Pencil"
     * @param cell      Each cell in the iterable list GridCell<Charaters> used to
     *                  draw different colors based on a char
     * @param converter Converts a CellPosition to a Pixel
     * @param CT        Color Theme set in DefualtColorTheme
     */
    private static void drawCells(Graphics2D g, Iterable<GridCell<AbstractPiece>> cell, CellPositionToPixelConverter converter, ColorTheme CT) {
        for (GridCell<AbstractPiece> gridCell : cell) {
            Color color = Color.black;
            Rectangle2D rect = converter.getBoundsForCell(gridCell.pos());
            g.setColor(color);
            g.fill(rect);
        }
    }
}