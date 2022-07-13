package com.comp301.a09akari;

import com.comp301.a09akari.model.ModelImpl;
import com.comp301.a09akari.model.PuzzleImpl;
import com.comp301.a09akari.model.PuzzleLibraryImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  @Test
  public void shouldAnswerWithTrue() {
    int[][] testPuzzle01 = {
      {6, 6, 6, 6, 6},
      {1, 6, 6, 6, 6},
      {6, 6, 6, 6, 6},
      {6, 6, 6, 6, 6},
      {4, 6, 6, 6, 6},
    };
    int[][] testPuzzle02 = {
            {6, 5, 1},
            {6, 6, 6},
            {6, 6, 6},
    };
    int[][]  testPuzzle03 = {
            {6},
    };
    int[][] testPuzzle04 = {
            {6,6,6},
            {6,6,6},
            {6,5,6},
            {6,5,6},
            {6,6,6},
    };
    int[][] testPuzzle05 = {
            {},
    };
    PuzzleImpl p = new PuzzleImpl(testPuzzle01);
    PuzzleImpl p2 = new PuzzleImpl(testPuzzle02);
    PuzzleImpl p3 = new PuzzleImpl(testPuzzle03);
    PuzzleImpl p4 = new PuzzleImpl(testPuzzle04);
    PuzzleImpl p5 = new PuzzleImpl(testPuzzle05);
    // ArrayList<Puzzle> PuzzleList = new ArrayList<>();
    PuzzleLibraryImpl testPuzzleLibrary = new PuzzleLibraryImpl();
    System.out.println(p.getCellType(0, 2));
    System.out.println(p.getCellType(2, 2));

    testPuzzleLibrary.addPuzzle(p);
    testPuzzleLibrary.addPuzzle(p2);
    testPuzzleLibrary.addPuzzle(p3);
    testPuzzleLibrary.addPuzzle(p4);
    testPuzzleLibrary.addPuzzle(p5);
    ModelImpl testModel = new ModelImpl(testPuzzleLibrary);

    //Testing Puzzle 01
    testModel.addLamp(2, 2);
    assertTrue(testModel.isLamp(2, 2));
    assertTrue(testModel.isLit(0, 2));
    assertFalse(testModel.isLit(4,4));

    //Switching to Puzzle 02
    testModel.setActivePuzzleIndex(1);

    //Testing Puzzle 02
    testModel.addLamp(2, 0);
    testModel.addLamp(1,2);
    assertTrue(testModel.isLit(0,0));
    assertTrue(testModel.isLit(1,0));
    assertTrue(testModel.isLit(1,1));
    assertTrue(testModel.isLit(1,2));
    assertTrue(testModel.isLit(2,0));
    assertTrue(testModel.isLit(2,1));
    assertTrue(testModel.isLit(2,2));
    assertTrue(testModel.isSolved());

    //Switching to Puzzle 03
    testModel.setActivePuzzleIndex(2);

    testModel.addLamp(0,0);
    assertTrue(testModel.isSolved());

    //Switching Back to Puzzle 01
    testModel.setActivePuzzleIndex(0);
    testModel.addLamp(4,4);
    assertFalse(testModel.isSolved());

    //Switching Back to Puzzle 04
    testModel.setActivePuzzleIndex(3);
    testModel.addLamp(4, 0);
    testModel.addLamp(0, 2);
    testModel.addLamp(1,1);
    assertTrue(testModel.isLit(1,0));
    assertTrue(testModel.isLit(1,1));
    assertTrue(testModel.isLit(1,2));
    assertTrue(testModel.isLit(3,2));
    assertTrue(testModel.isSolved());

    testModel.setActivePuzzleIndex(4);
    assertTrue(testModel.isSolved());
  }
}
