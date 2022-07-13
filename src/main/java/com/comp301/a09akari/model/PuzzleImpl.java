package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {

  private final int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {
    return this.board[0].length;
  }

  @Override
  public int getHeight() {
    return this.board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r >= this.getHeight() || c >= this.getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    int type = this.board[r][c];
    if (type == 0 || type == 1 || type == 2 || type == 3 || type == 4) {
      return CellType.CLUE;
    } else if (type == 5) {
      return CellType.WALL;
    } else if (type == 6) {
      return CellType.CORRIDOR;
    }
    return null;
  }

  @Override
  public int getClue(int r, int c) {
    if (r >= this.getHeight() || c >= this.getWidth() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return this.board[r][c];
  }
}
