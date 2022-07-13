package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {

  // Note: "Easy" 7x7 Puzzle ID 930887 from https://www.puzzle-light-up.com/specific.php
  public static int[][] PUZZLE_01 = {
    {6, 6, 6, 6, 1, 6, 6},
    {6, 6, 6, 5, 6, 6, 6},
    {0, 6, 6, 6, 6, 6, 6},
    {6, 5, 6, 6, 6, 4, 6},
    {6, 6, 6, 6, 6, 6, 5},
    {6, 6, 6, 2, 6, 6, 6},
    {6, 6, 5, 6, 6, 6, 6},
  };

  // Note: This is the puzzle shown on the wikipedia page for Akari
  // See: https://en.wikipedia.org/wiki/Light_Up_(puzzle)
  public static int[][] PUZZLE_02 = {
    {5, 6, 6, 5, 6, 6, 6, 6, 6, 5},
    {6, 6, 6, 6, 6, 6, 6, 5, 6, 6},
    {6, 3, 6, 6, 6, 6, 0, 6, 6, 6},
    {6, 6, 2, 6, 6, 5, 6, 6, 6, 1},
    {6, 6, 6, 1, 0, 5, 6, 6, 6, 6},
    {6, 6, 6, 6, 1, 5, 5, 6, 6, 6},
    {5, 6, 6, 6, 2, 6, 6, 2, 6, 6},
    {6, 6, 6, 5, 6, 6, 6, 6, 5, 6},
    {6, 6, 1, 6, 6, 6, 6, 6, 6, 6},
    {0, 6, 6, 6, 6, 6, 1, 6, 6, 0},
  };

  // Note: "Easy" 7x7 Puzzle ID 9070893 from https://www.puzzle-light-up.com/specific.php
  public static int[][] PUZZLE_03 = {
    {6, 6, 5, 6, 6, 6, 6},
    {6, 5, 6, 6, 6, 4, 6},
    {6, 6, 6, 6, 6, 6, 5},
    {6, 6, 6, 6, 6, 6, 6},
    {3, 6, 6, 6, 6, 6, 6},
    {6, 2, 6, 6, 6, 5, 6},
    {6, 6, 6, 6, 0, 6, 6},
  };

  // Note: "Normal" 10x10 Puzzle ID 6424638 from https://www.puzzle-light-up.com/specific.php
  public static int[][] PUZZLE_04 = {
    {6, 1, 6, 6, 6, 6, 5, 6, 6, 6},
    {6, 6, 6, 6, 6, 6, 6, 6, 6, 5},
    {6, 6, 5, 5, 6, 6, 6, 2, 6, 6},
    {2, 6, 6, 5, 6, 6, 1, 5, 6, 6},
    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
    {6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
    {6, 6, 5, 2, 6, 6, 0, 6, 6, 1},
    {6, 6, 2, 6, 6, 6, 5, 1, 6, 6},
    {2, 6, 6, 6, 6, 6, 6, 6, 6, 6},
    {6, 6, 6, 5, 6, 6, 6, 6, 5, 6},
  };

  // Note: Your app should work with non-square puzzles like the one here
  public static int[][] PUZZLE_05 = {
    {6, 6, 5, 6, 6, 6},
    {6, 5, 6, 6, 6, 3},
    {6, 6, 6, 6, 6, 6},
    {6, 6, 6, 6, 6, 6},
    {3, 6, 6, 6, 6, 6},
    {6, 2, 6, 6, 6, 6},
    {6, 6, 6, 6, 0, 6},
  };

  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI

    PuzzleLibrary library = new PuzzleLibraryImpl();

    Puzzle p1 = new PuzzleImpl(PUZZLE_01);
    Puzzle p2 = new PuzzleImpl(PUZZLE_02);
    Puzzle p3 = new PuzzleImpl(PUZZLE_03);
    Puzzle p4 = new PuzzleImpl(PUZZLE_04);
    Puzzle p5 = new PuzzleImpl(PUZZLE_05);

    library.addPuzzle(p1);
    library.addPuzzle(p2);
    library.addPuzzle(p3);
    library.addPuzzle(p4);
    library.addPuzzle(p5);

    Model model = new ModelImpl(library);

    ClassicMvcController controller = new ControllerImpl(model);

    View view = new View(controller);

    Scene scene =
        new Scene(
            view.render(),
            controller.getActivePuzzle().getHeight() * 100,
            controller.getActivePuzzle().getWidth() * 100);
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);

    model.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
          stage.sizeToScene();
        });

    stage.setTitle("Akari Game");

    stage.show();
  }
}
