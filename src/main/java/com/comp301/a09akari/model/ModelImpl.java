package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private final PuzzleLibrary library;
  private final List<ModelObserver> observers;
  private int currentPuzzleIndex = 0;
  private Boolean[][] lamps;

  public ModelImpl(PuzzleLibrary library) {
    if (library.size() <= 0) {
      throw new IllegalArgumentException();
    }
    this.library = library;
    this.currentPuzzleIndex = 0;
    this.lamps = new Boolean[this.getActivePuzzle().getHeight()][this.getActivePuzzle().getWidth()];
    for (int row = 0; row < this.lamps.length; row++) {
      for (int col = 0; col < this.lamps[row].length; col++) {
        this.lamps[row][col] = false;
      }
    }
    this.observers = new ArrayList<>();
  }

  @Override
  public void addLamp(int r, int c) {
    if (r >= this.getActivePuzzle().getHeight()
        || c >= this.getActivePuzzle().getWidth()
        || r < 0
        || c < 0) {
      throw new IndexOutOfBoundsException("value for r or c is out of bounds");
    }
    CellType type = this.getActivePuzzle().getCellType(r, c);
    if (type != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (!this.lamps[r][c]) {
      this.lamps[r][c] = true;
    }
    this.notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r >= this.getActivePuzzle().getHeight()
        || c >= this.getActivePuzzle().getWidth()
        || r < 0
        || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    CellType type = this.getActivePuzzle().getCellType(r, c);
    if (type != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (type == CellType.CORRIDOR && this.lamps[r][c] == true) {
      this.lamps[r][c] = false;
    }
    this.notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r >= this.getActivePuzzle().getHeight()
        || c >= this.getActivePuzzle().getWidth()
        || r < 0
        || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    CellType type = this.getActivePuzzle().getCellType(r, c);
    if (type != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    for (int i = 0; i <= r; i++) {
      if (this.getActivePuzzle().getCellType(r - i, c) != CellType.CORRIDOR) {
        break;
      }
      if (this.lamps[r - i][c]) {
        return true;
      }
    }
    for (int i = 0; i < this.getActivePuzzle().getHeight() - r; i++) {
      if (this.getActivePuzzle().getCellType(r + i, c) != CellType.CORRIDOR) {
        break;
      }
      if (this.lamps[r + i][c]) {
        return true;
      }
    }
    for (int i = 0; i < this.getActivePuzzle().getWidth() - c; i++) {
      if (this.getActivePuzzle().getCellType(r, c + i) != CellType.CORRIDOR) {
        break;
      }
      if (this.lamps[r][c + i]) {
        return true;
      }
    }
    for (int i = 0; i <= c; i++) {
      if (this.getActivePuzzle().getCellType(r, c - i) != CellType.CORRIDOR) {
        break;
      }
      if (this.lamps[r][c - i]) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r >= this.getActivePuzzle().getHeight()
        || c >= this.getActivePuzzle().getWidth()
        || r < 0
        || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    CellType type = this.getActivePuzzle().getCellType(r, c);
    if (type != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return this.lamps[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r >= this.getActivePuzzle().getHeight()
        || c >= this.getActivePuzzle().getWidth()
        || r < 0
        || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (!this.lamps[r][c]) {
      throw new IllegalArgumentException();
    }
    boolean illegal = false;
    boolean lampFound = false;
    int lampCount = 0;
    for (int col = 0; col < this.lamps[r].length; col++) {
      if (this.lamps[r][col]) {
        lampFound = true;
        lampCount += 1;
      }
      if (this.getActivePuzzle().getCellType(r, col) != CellType.CORRIDOR && lampFound) {
        lampCount = 0;
      }
      if (lampCount > 1) {
        return true;
      }
    }

    lampFound = false;
    lampCount = 0;
    for (int row = 0; row < this.lamps.length; row++) {
      if (this.lamps[row][c]) {
        lampFound = true;
        lampCount += 1;
      }
      if (this.getActivePuzzle().getCellType(row, c) != CellType.CORRIDOR && lampFound == true) {
        lampCount = 0;
      }
      if (lampCount > 1) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return this.library.getPuzzle(this.currentPuzzleIndex);
  }

  @Override
  public int getActivePuzzleIndex() {
    return this.currentPuzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index >= this.library.size() || index < 0) {
      throw new IndexOutOfBoundsException();
    }
    this.currentPuzzleIndex = index;
    this.lamps = new Boolean[this.getActivePuzzle().getHeight()][this.getActivePuzzle().getWidth()];
    for (int row = 0; row < this.getActivePuzzle().getHeight(); row++) {
      for (int col = 0; col < this.getActivePuzzle().getWidth(); col++) {
        this.lamps[row][col] = false;
      }
    }
    this.notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return this.library.size();
  }

  @Override
  public void resetPuzzle() {
    for (int row = 0; row < this.getActivePuzzle().getHeight(); row++) {
      for (int col = 0; col < this.getActivePuzzle().getWidth(); col++) {
        this.lamps[row][col] = false;
      }
    }
    this.notifyObservers();
  }

  @Override
  public boolean isSolved() {
    for (int row = 0; row < this.getActivePuzzle().getHeight(); row++) {
      for (int col = 0; col < this.getActivePuzzle().getWidth(); col++) {
        CellType type = this.getActivePuzzle().getCellType(row, col);
        if (type.equals(CellType.CLUE)) {
          if (!isClueSatisfied(row, col)) {
            return false;
          }
        } else if (type.equals(CellType.CORRIDOR)) {
          if (!isLit(row, col)) {
            return false;
          }
          if (this.lamps[row][col] && this.isLampIllegal(row, col)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r >= this.getActivePuzzle().getHeight()
        || c >= this.getActivePuzzle().getWidth()
        || r < 0
        || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    CellType type = this.getActivePuzzle().getCellType(r, c);
    if (type != CellType.CLUE) {
      throw new IllegalArgumentException();
    }

    int maxLights = 0;
    int counter = 0;

    if (this.getActivePuzzle().getClue(r, c) == 1) {
      maxLights = 1;
    } else if (this.getActivePuzzle().getClue(r, c) == 2) {
      maxLights = 2;
    } else if (this.getActivePuzzle().getClue(r, c) == 3) {
      maxLights = 3;
    } else if (this.getActivePuzzle().getClue(r, c) == 4) {
      maxLights = 4;
    }

    if (r - 1 != -1) {
      if (this.lamps[r - 1][c]) {
        counter++;
      }
    }
    if (r + 1 != this.getActivePuzzle().getHeight()) {
      if (this.lamps[r + 1][c]) {
        counter++;
      }
    }
    if (c - 1 != -1) {
      if (this.lamps[r][c - 1]) {
        counter++;
      }
    }
    if (c + 1 != this.getActivePuzzle().getWidth()) {
      if (this.lamps[r][c + 1]) {
        counter++;
      }
    }
    return counter == maxLights;
  }

  @Override
  public void addObserver(ModelObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException();
    }
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    if (observer == null || !this.observers.contains(observer)) {
      throw new IllegalArgumentException();
    }
    this.observers.remove(observer);
  }

  private void notifyObservers() {
    for (ModelObserver m : observers) {
      m.update(this);
    }
  }
}
