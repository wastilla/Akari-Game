package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Puzzle;

public interface ClassicMvcController {
  /** Handles the click action to go to the next puzzle */
  void clickNextPuzzle();

  /** Handles the click action to go to the previous puzzle */
  void clickPrevPuzzle();

  /** Handles the click action to go to a random puzzle */
  void clickRandPuzzle();

  /** Handles the click action to reset the currently active puzzle */
  void clickResetPuzzle();

  /** Handles the click event on the cell at row r, column c */
  void clickCell(int r, int c);

  /** Returns the width of the active puzzle */
  int getPuzzleWidth();

  /** Returns the height of the active puzzle */
  int getPuzzleHeight();

  CellType getCellType(int r, int c);

  Puzzle getActivePuzzle();

  int getClueNumber(int r, int c);

  int getPuzzleIndex();

  int getLibrarySize();

  boolean isLampIllegal(int r, int c);

  boolean isLit(int r, int c);

  boolean isLamp(int r, int c);

  boolean isSolved(int r, int c);

  boolean isPuzzleSolved();
}
