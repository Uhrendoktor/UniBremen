

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Die Test-Klasse FieldTest.
 *
 * @author  (Ihr Name)
 * @version (eine Versionsnummer oder ein Datum)
 */
public class FieldTest
{
    /**
     * Konstruktor fuer die Test-Klasse FieldTest
     */
    public FieldTest()
    {
    }

    /**
     *  Setzt das Testgerüst fuer den Test.
     *
     * Wird vor jeder Testfall-Methode aufgerufen.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Gibt das Testgerüst wieder frei.
     *
     * Wird nach jeder Testfall-Methode aufgerufen.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testMap1() {
        String[] map = new String[] {
            "O-O-O-O  ",
            "|   |    ",
            "O O-O-O O",
            "| | | | |",
            "O-O-O-O-O",
            "| | | | |",
            "O O-O-O O",
            "    |   |",
            "O-O-O-O-O"
        };
        testField(map);
    }
    
    @Test
    public void testMap2() {
        String[] map = new String[10];
        testField(map);
    }
    
    @Test
    public void testMap3() {
        String[] map = new String[] {};
        testField(map);
    }
    
    @Test
    public void testMap4() {
        testField(null);
    }
    
    public void testField(String[] map) {
        Field field = new Field(map);
        for(int y = -1; y <= map.length; y++){
            for(int x = -1; x <= map[y].length(); x++){
                if(field.hasNeighbor(x,y,0)) assertEquals(map[y*2].charAt(x*2+1),'-');
                if(field.hasNeighbor(x,y,1)) assertEquals(map[y*2+1].charAt(x*2),'|');
                if(field.hasNeighbor(x,y,2)) assertEquals(map[y*2].charAt(x*2-1),'-');
                if(field.hasNeighbor(x,y,3)) assertEquals(map[y*2-1].charAt(x*2),'|');
            }
        }
    }
}

