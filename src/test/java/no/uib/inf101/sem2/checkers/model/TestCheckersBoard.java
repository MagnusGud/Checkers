package no.uib.inf101.sem2.checkers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import no.uib.inf101.sem2.checkers.model.checkerspiece.PieceFactory;
import no.uib.inf101.sem2.grid.CellPosition;


public class TestCheckersBoard {
    
    @Test
    public void testBoardInitalBoard() {
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        model.setInitalBoard();
        String expected = String.join("\n", new String[] {
            "-P-P-P-P",
            "P-P-P-P-",
            "-P-P-P-P",
            "--------",
            "--------",
            "P-P-P-P-",
            "-P-P-P-P",
            "P-P-P-P-"
        });
        assertEquals(expected, model.outPutBoard());
    }


    @Test
    public void testMovePiece() {
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        model.setInitalBoard();
        model.move(new CellPosition(5, 0), new CellPosition(4, 1));
        String expected = String.join("\n", new String[] {
            "-P-P-P-P",
            "P-P-P-P-",
            "-P-P-P-P",
            "--------",
            "-P------",
            "--P-P-P-",
            "-P-P-P-P",
            "P-P-P-P-"
        });
        assertEquals(expected, model.outPutBoard());
    }

    @Test
    public void testMovePieceFalse() {
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        model.setInitalBoard();
        model.move(new CellPosition(5, 0), new CellPosition(4, 1));
        model.move(new CellPosition(4, 1), new CellPosition(3, 0));
        String expected = String.join("\n", new String[] {
            "-P-P-P-P",
            "P-P-P-P-",
            "-P-P-P-P",
            "--------",
            "-P------",
            "--P-P-P-",
            "-P-P-P-P",
            "P-P-P-P-"
        });
        assertEquals(expected, model.outPutBoard());
    }

    
    

    @Test
    public void testKingCanMoveBackwards() {
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        PieceFactory factory = new PieceFactory();
        model.setInitalBoard();
        board.set(new CellPosition(3, 2), factory.getNext('K', 'w') );
        model.move(new CellPosition(3 , 2), new CellPosition(4, 1));
        String expected = String.join("\n", new String[] {
            "-P-P-P-P",
            "P-P-P-P-",
            "-P-P-P-P",
            "--------",
            "-K------",
            "P-P-P-P-",
            "-P-P-P-P",
            "P-P-P-P-"
        });
        assertEquals(expected, model.outPutBoard());
    }

    @Test
    public void cannotMoveBackWards() {
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        PieceFactory factory = new PieceFactory();
        board.set(new CellPosition(1, 1), factory.getNext('P', 'w') );
        model.move(new CellPosition(1, 1), new CellPosition(2, 0));
    
        String expected = String.join("\n", new String[] {
            "--------",
            "-P------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------"
        });
        assertEquals(expected, model.outPutBoard());
    }
    @Test
    public void testPromoteToKing() {
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        PieceFactory factory = new PieceFactory();
        board.set(new CellPosition(1, 1), factory.getNext('P', 'w') );
        board.set(new CellPosition(6, 1), factory.getNext('P', 'b') );

        model.move(new CellPosition(1, 1), new CellPosition(0, 0));
        model.move(new CellPosition(6, 1), new CellPosition(7, 2));
        String expected = String.join("\n", new String[] {
            "K-------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--------",
            "--K-----"
        });
        assertEquals(expected, model.outPutBoard());
    }

    @Test
    public void testNoMoreLegalMove() {
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        PieceFactory factory = new PieceFactory();
        board.set(new CellPosition(0, 0), factory.getNext('P', 'b'));
        board.set(new CellPosition(1, 1), factory.getNext('P', 'w'));
        board.set(new CellPosition(2, 2), factory.getNext('P', 'w'));
        board.set(new CellPosition(3, 3), factory.getNext('P', 'w'));

        model.move(new CellPosition(3, 3), new CellPosition(2, 4));
        model.getGameState();
        GameState expected = GameState.GAME_OVER;
        assertEquals(expected, model.getGameState());
    }

    @Test
    public void testGameOverWhite() {
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        PieceFactory factory = new PieceFactory();
        board.set(new CellPosition(1, 1), factory.getNext('P', 'b'));
        board.set(new CellPosition(2, 2), factory.getNext('P', 'w'));
        model.move(new CellPosition(2, 2), new CellPosition(0, 0));
        model.getGameState();
        GameState expected = GameState.GAME_OVER;
        assertEquals(expected, model.getGameState());
    }

    @Test
    public void testGameOverBlack() {
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        PieceFactory factory = new PieceFactory();
        board.set(new CellPosition(1, 1), factory.getNext('P', 'b'));
        board.set(new CellPosition(3, 3), factory.getNext('P', 'w'));
        model.move(new CellPosition(3, 3), new CellPosition(2, 2));
        model.move(new CellPosition(1, 1), new CellPosition(3, 3));
        model.getGameState();
        GameState expected = GameState.GAME_OVER;
        assertEquals(expected, model.getGameState());
    }

}
