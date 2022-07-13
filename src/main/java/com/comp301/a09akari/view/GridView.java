package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GridView implements FXComponent {
  private final ClassicMvcController controller;

  public GridView(ClassicMvcController controller) {
    this.controller = controller;
  }

  private static javafx.scene.control.Button makeTile(
      CellType t, int r, int c, ClassicMvcController controller) {
    javafx.scene.control.Button b = null;
    if (t == CellType.CORRIDOR) {
      if (controller.isLamp(r, c)) {
        b = new javafx.scene.control.Button();
        Image i = new Image("/light-bulb.png");
        ImageView imageView = new ImageView(i);
        imageView.setFitHeight(18);
        imageView.setFitWidth(18);
        b.setGraphic(imageView);
      } else {
        b = new javafx.scene.control.Button();
      }
      if (controller.isLamp(r, c) && controller.isLampIllegal(r, c)) {
        b.getStyleClass().add("illegalLamp");
      } else if (controller.isLit(r, c)) {
        b.getStyleClass().add("lit-corridor");
      } else {
        b.getStyleClass().add("corridor");
      }
    } else if (t == CellType.WALL) {
      b = new javafx.scene.control.Button();
      b.getStyleClass().add("wall");
    } else if (t == CellType.CLUE) {
      b = new javafx.scene.control.Button("" + controller.getClueNumber(r, c));
      if (controller.isSolved(r, c)) {
        b.getStyleClass().add("solved-clue");
      } else {
        b.getStyleClass().add("clue");
      }
    }
    b.setOnAction(
        (ActionEvent event) -> {
          controller.clickCell(r, c);
        });
    b.getStyleClass().add("corridor");
    return b;
  }

  @Override
  public Parent render() {
    GridPane layout = new GridPane();
    layout.setHgap(2);
    layout.setVgap(2);
    layout.getStyleClass().add("grid");

    for (int i = this.controller.getPuzzleWidth() - 1; i >= 0; i--) {
      for (int j = this.controller.getPuzzleHeight() - 1; j >= 0; j--) {
        layout.add(makeTile(this.controller.getCellType(j, i), j, i, this.controller), i, j);
      }
    }
    return layout;
  }
}
