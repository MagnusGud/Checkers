package no.uib.inf101.sem2.view;

import org.junit.jupiter.api.Test;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.model.ChekersBoard;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.geom.Rectangle2D;
import no.uib.inf101.sem2.grid.CellPosition;

public class TestCellPositionToPixelConverter {
    @Test
    public void sanityTest() {
    GridDimension gd = new ChekersBoard(3, 4);
    CellPositionToPixelConverter converter = new CellPositionToPixelConverter(
        new Rectangle2D.Double(29, 29, 340, 240), gd, 30
    );
    Rectangle2D expected = new Rectangle2D.Double(214, 129, 47.5, 40);
    assertEquals(expected, converter.getBoundsForCell(new CellPosition(1, 2)));
    }
}
