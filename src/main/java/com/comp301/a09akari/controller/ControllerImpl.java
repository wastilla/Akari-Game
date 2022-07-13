package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;

import java.util.Random;

public class ControllerImpl implements ClassicMvcController {
  private final Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    int index = this.model.getActivePuzzleIndex();
    if (this.model.getActivePuzzleIndex() < this.model.getPuzzleLibrarySize()) {
      index += 1;
    }
    if (index == this.model.getPuzzleLibrarySize()) {
      index = 0;
    }
    this.model.setActivePuzzleIndex(index);
  }

  @Override
  public void clickPrevPuzzle() {
    int index = this.model.getActivePuzzleIndex();
    if (this.model.getActivePuzzleIndex() >= 0) {
      index--;
    }
    if (index == -1) {
      index = this.model.getPuzzleLibrarySize() - 1;
    }
    this.model.setActivePuzzleIndex(index);
  }

  @Override
  public void clickRandPuzzle() {
    Random rand = new Random();
    int randomPuzzle = rand.nextInt(this.model.getPuzzleLibrarySize());
    if (!(this.model.getPuzzleLibrarySize() == 0)) {
      while (randomPuzzle == this.model.getActivePuzzleIndex()) {
        randomPuzzle = rand.nextInt(this.model.getPuzzleLibrarySize());
      }
    }
    this.model.setActivePuzzleIndex(randomPuzzle);
  }

  @Override
  public void clickResetPuzzle() {
    this.model.resetPuzzle();
  }

  public int getPuzzleWidth() {
    return this.model.getActivePuzzle().getWidth();
  }

  public int getPuzzleHeight() {
    return this.model.getActivePuzzle().getHeight();
  }

  @Override
  public void clickCell(int r, int c) {
    if (this.model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      if (this.model.isLamp(r, c)) {
        this.model.removeLamp(r, c);
      } else if (!this.model.isLamp(r, c)) {
        this.model.addLamp(r, c);
      }
    }
  }

  public CellType getCellType(int r, int c) {
    return this.model.getActivePuzzle().getCellType(r, c);
  }

  public Puzzle getActivePuzzle() {
    return this.model.getActivePuzzle();
  }

  @Override
  public int getClueNumber(int r, int c) {
    return this.model.getActivePuzzle().getClue(r, c);
  }

  public int getPuzzleIndex() {
    return this.model.getActivePuzzleIndex();
  }

  public int getLibrarySize() {
    return this.model.getPuzzleLibrarySize();
  }

  public boolean isLampIllegal(int r, int c) {
    return this.model.isLampIllegal(r, c);
  }

  public boolean isLit(int r, int c) {
    return this.model.isLit(r, c);
  }

  public boolean isLamp(int r, int c) {
    return this.model.isLamp(r, c);
  }

  public boolean isSolved(int r, int c) {
    return this.model.isClueSatisfied(r, c);
  }

  public boolean isPuzzleSolved() {
    return this.model.isSolved();
  }
}
