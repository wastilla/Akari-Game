package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ControlsView implements FXComponent {
  private final ClassicMvcController controller;

  public ControlsView(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layoutVert = new VBox();
    layoutVert.getStyleClass().add("vert-controls-layout");
    HBox layout = new HBox();
    layout.getStyleClass().add("controls-layout");

    Button previousButton = new Button("Prev");
    previousButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });
    layout.getChildren().add(previousButton);

    Button resetButton = new Button("Reset");
    resetButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickResetPuzzle();
        });
    layout.getChildren().add(resetButton);

    Button randomButton = new Button("Random");
    randomButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickRandPuzzle();
        });
    layout.getChildren().add(randomButton);

    Button nextButton = new Button("Next");
    nextButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickNextPuzzle();
        });
    layout.getChildren().add(nextButton);

    javafx.scene.control.Label label =
        new javafx.scene.control.Label(
            "Puzzle "
                + (this.controller.getPuzzleIndex() + 1)
                + " of "
                + this.controller.getLibrarySize());
    layout.getChildren().add(label);
    layout.setSpacing(10.0);
    layoutVert.getChildren().add(layout);

    if (this.controller.isPuzzleSolved()) {
      javafx.scene.control.Label solvedMessage =
          new javafx.scene.control.Label("Congratulations!! You solved the puzzle!!");
      solvedMessage.getStyleClass().add("congrats-Message");
      layoutVert.getChildren().add(solvedMessage);
    }

    return layoutVert;
  }
}
