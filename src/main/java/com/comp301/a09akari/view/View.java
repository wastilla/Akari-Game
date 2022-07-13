package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class View implements FXComponent {

  private final ClassicMvcController controller;

  public View(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    ControlsView controls = new ControlsView(this.controller);
    GridView grid = new GridView(this.controller);
    layout.getChildren().addAll(controls.render(), grid.render());

    return layout;
  }
}
