import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Box;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Gui
{
  private final int BORDER = 5;

  TextField toSearchFrom;
  Button readButton;
  TextField patternToSearchFor;
  Button findPattern;

  private Group inputGroup;
  private Group outputGroup;
  private TextArea previewToSearchFrom;


  Gui(Group root)
  {
    setUpInputGroup();
    setUpOutputGroup();
    setUpButtons();

    root.getChildren().addAll(inputGroup, outputGroup);
  }

  private void setUpInputGroup()
  {
    inputGroup = new Group();
    inputGroup.setTranslateY(50);
    inputGroup.setTranslateX(50);

    toSearchFrom = new TextField();
    toSearchFrom.setPrefSize(300, 20);

    toSearchFrom.setText("File name of file to read here.");

    readButton = new Button("Read");
    readButton.setTranslateX(toSearchFrom.getTranslateX() +
            toSearchFrom.getPrefWidth() + BORDER);

    patternToSearchFor = new TextField();
    patternToSearchFor.setTranslateY(toSearchFrom.getPrefHeight() + BORDER*2);
    patternToSearchFor.setPrefSize(150, 20);
    patternToSearchFor.setText("Pattern Here");

    findPattern = new Button("Find Pattern");
    findPattern.setTranslateY(toSearchFrom.getPrefHeight() + BORDER*2);
    findPattern.setTranslateX(patternToSearchFor.getPrefWidth() + BORDER);

//    inputGroup.getChildren().add(readButton);
    inputGroup.getChildren().addAll(toSearchFrom, patternToSearchFor, findPattern);
  }

  private void setUpButtons()
  {
    findPattern.setOnAction(ae->
    {
      try
      {
        BufferedReader bR = new BufferedReader(new FileReader("src/"+toSearchFrom.getText()));
        int length = KMPSearch.lengthOfText("src/"+toSearchFrom.getText());
//        previewToSearchFrom.setText(KMPSearch.stringSearchKMP(toSearchFrom.getText(), patternToSearchFor.getText()));
        previewToSearchFrom.setText(KMPSearch.stringSearchKMP(bR, patternToSearchFor.getText(), length));
      }
      catch(IOException e)
      {
        previewToSearchFrom.setText("Can't find the file named " + toSearchFrom.getText() +"." );
      }
    });
  }

  private void setUpOutputGroup()
  {
    outputGroup = new Group();
    outputGroup.setTranslateX(50);
    outputGroup.setTranslateY(150);

    previewToSearchFrom = new TextArea("You will see input here after clicking the 'Read' button");
    previewToSearchFrom.setEditable(false); //so users can't be cheeky and delete stuff
    previewToSearchFrom.setPrefSize(350, 200);


    outputGroup.getChildren().add(previewToSearchFrom);
  }
}
