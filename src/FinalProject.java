/**
 * Created by Arthur on 10/26/2016.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class FinalProject extends Application {

  private Gui gui;


  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage)
  {
    Group root = new Group();

    setUpGui(root);

    Scene scene = new Scene(root, 500, 500, null);
    scene.setFill(Color.CORAL);

    primaryStage.setResizable(false);
    primaryStage.setTitle("String Search");

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void setUpGui(Group root)
  {
    gui = new Gui(root);
  }

}

