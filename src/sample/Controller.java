package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Label statusLabel;
    public TextField displayCal;

    public void openCB(ActionEvent actionEvent) {
    }

    public void saveCB(ActionEvent actionEvent) {
    }

    public void exitCB(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void deleteCB(ActionEvent actionEvent) {
        statusLabel.setText("Status: ");
        displayCal.clear();

    }

    public void authorCB(ActionEvent actionEvent) {
        statusLabel.setText("Avtor: Martin Vrbančič");
    }

    public void numberCal(ActionEvent actionEvent) {
       Button btn = (Button) actionEvent.getSource();
        String buttonPressed = btn.getText();
        System.out.println(buttonPressed);
        switch (buttonPressed){
            case "*":
                if (displayCal != null)
                    displayCal.setText(displayCal.getText() + " * ");
                else
                    statusLabel.setText("Error prvo stevilke");
                break;
            case "/":
                if (displayCal != null)
                    displayCal.setText(displayCal.getText() + " / ");
                else
                    statusLabel.setText("Error prvo stevilke");
                break;
            case "+":
                if (displayCal != null)
                    displayCal.setText(displayCal.getText() + " + ");
                else
                    statusLabel.setText("Error prvo stevilke");
                break;
            case "-":
                if (displayCal != null)
                    displayCal.setText(displayCal.getText() + " - ");
                else
                    statusLabel.setText("Error prvo stevilke");
                break;
            case "=":
                int result = calulate(displayCal.getText());
                displayCal.setText(Integer.toString(result));
                break;
            default:
                displayCal.setText(displayCal.getText() + buttonPressed);

        }
    }

    private int calulate(String getText){
        String elemetnsOfEquation [] = getText.split(" ");
        int result = 0;

        for (int i = 0; i < elemetnsOfEquation.length; i++) {
            switch (elemetnsOfEquation[i]){
                case "*":
                    result *= Integer.parseInt(elemetnsOfEquation[++i]);
                    break;
                case "+":
                    result += Integer.parseInt(elemetnsOfEquation[++i]);
                    break;
                case "-":
                    result -= Integer.parseInt(elemetnsOfEquation[++i]);
                    break;
                case "/":
                    result /= Integer.parseInt(elemetnsOfEquation[++i]);
                    break;
                default:
                    result = Integer.parseInt(elemetnsOfEquation[i]);
            }
        }
        return result;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
