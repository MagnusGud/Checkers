package no.uib.inf101.sem2.checkers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


import no.uib.inf101.sem2.checkers.model.checkerspiece.PieceFactory;
import no.uib.inf101.sem2.grid.CellPosition;

public class TestCapture {
    @Test
    public void testDoubleCapture() {   
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        PieceFactory factory = new PieceFactory();
        board.set(new CellPosition(1, 1), factory.getNext('P', 'b') );
        board.set(new CellPosition(3, 3), factory.getNext('P', 'b') );
        board.set(new CellPosition(4, 4), factory.getNext('P', 'w') );

        model.move(new CellPosition(4, 4), new CellPosition(2, 2));
        model.move(new CellPosition(2, 2), new CellPosition(0, 0));
        String expected = String.join("\n", new String[] {
            "K-------",
            "--------",
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
    public void kingCanCaptureBackward() {   
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        PieceFactory factory = new PieceFactory();
        board.set(new CellPosition(2, 2), factory.getNext('K', 'w') );
        board.set(new CellPosition(3, 3), factory.getNext('P', 'b') );

        model.move(new CellPosition(2, 2), new CellPosition(4, 4));
        String expected = String.join("\n", new String[] {
            "--------",
            "--------",
            "--------",
            "--------",
            "----K---",
            "--------",
            "--------",
            "--------"
        });
        assertEquals(expected, model.outPutBoard());
    }
    @Test
    public void testBackwardsCaptureNotLegal() {   
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        PieceFactory factory = new PieceFactory();
        board.set(new CellPosition(2, 2), factory.getNext('P', 'w') );
        board.set(new CellPosition(3, 3), factory.getNext('P', 'b') );

        model.move(new CellPosition(2, 2), new CellPosition(4, 4));
        String expected = String.join("\n", new String[] {
            "--------",
            "--------",
            "--P-----",
            "---P----",
            "--------",
            "--------",
            "--------",
            "--------"
        });
        assertEquals(expected, model.outPutBoard());
    }

    @Test
    public void testCannotCaptureOwnPiece() {
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        model.setInitalBoard();
        model.move(new CellPosition(5, 2), new CellPosition(4, 3));
        model.move(new CellPosition(2, 5), new CellPosition(3, 6));
        model.move(new CellPosition(5, 4), new CellPosition(3, 2));
        String expected = String.join("\n", new String[] {
            "-P-P-P-P",
            "P-P-P-P-",
            "-P-P---P",
            "------P-",
            "---P----",
            "P---P-P-",
            "-P-P-P-P",
            "P-P-P-P-"
        });
        assertEquals(expected, model.outPutBoard());
    }

    @Test
    public void testCapturePiece() {
        CheckersBoard board = new CheckersBoard(8, 8);
        CheckersModel model = new CheckersModel(board);
        model.setInitalBoard();
        model.move(new CellPosition(5, 2), new CellPosition(4, 3));
        model.move(new CellPosition(2, 1), new CellPosition(3, 2));
        model.move(new CellPosition(4, 3), new CellPosition(2, 1));
        String expected = String.join("\n", new String[] {
            "-P-P-P-P",
            "P-P-P-P-",
            "-P-P-P-P",
            "--------",
            "--------",
            "P---P-P-",
            "-P-P-P-P",
            "P-P-P-P-"
        });
        assertEquals(expected, model.outPutBoard());
    }
}
