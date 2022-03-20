package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField displayCal;
    public TextArea dnevnikIzracunov;
    public TextArea dogodki;
    public Label status;
    public Accordion harmonika;
    public TitledPane kalkulator;
    public TitledPane dnevnikIzracunaH;
    public TitledPane dogodkiH;

    public void openCB(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if (f!=null){
            try(BufferedReader br = new BufferedReader(new FileReader(f))){
                StringBuffer sb = new StringBuffer("");
                String line;
                while ((line=br.readLine())!=null)
                    sb.append(line).append('\n');
                br.close();
                dnevnikIzracunov.setText(sb.toString());
                status.setText("Uspel prebrati datoteko: "+f.getName()+" "+f.length()+" bytes");
                if (!dogodki.getText().isEmpty())
                    dogodki.setText(dogodki.getText()+"\nOdpiram");
                else
                    dogodki.setText("Odpiram");
            }catch (Exception e){
                status.setText("Napaka pri branju datoteke: "+f.getName());
            }
        }


    }

    public void saveCB(ActionEvent actionEvent) {
        FileChooser fc= new FileChooser();
        File f = fc.showSaveDialog(null);
        if (f!=null){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))){
                bw.write(dnevnikIzracunov.getText());
                bw.close();
                status.setText("Shranil datoteko: "+ f.getName());
                if (!dogodki.getText().isEmpty())
                    dogodki.setText(dogodki.getText()+"\nShranjujem");
                else
                    dogodki.setText("Shranjujem");
            }catch (Exception e){
                status.setText("nisem uspel zapisati datoteke: "+ f.getName());
            }
        }



    }

    public void exitCB(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void deleteCB(ActionEvent actionEvent) {
        status.setText("Status: ");
        displayCal.clear();
        dnevnikIzracunov.clear();

    }

    public void authorCB(ActionEvent actionEvent) {
        status.setText("Avtor: Martin Vrbančič");
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
                    status.setText("Error prvo stevilke");
                break;
            case "/":
                if (displayCal != null)
                    displayCal.setText(displayCal.getText() + " / ");
                else
                    status.setText("Error prvo stevilke");
                break;
            case "+":
                if (displayCal != null)
                    displayCal.setText(displayCal.getText() + " + ");
                else
                    status.setText("Error prvo stevilke");
                break;
            case "-":
                if (displayCal != null)
                    displayCal.setText(displayCal.getText() + " - ");
                else
                    status.setText("Error prvo stevilke");
                break;
            case "=":
                String displayText = displayCal.getText(); //get text from display
                //check if input is correct
                char f = displayText.charAt(0);
                char l = displayText.charAt(displayText.length()-1);
                if (Character.isDigit(f) && Character.isDigit(l)) {
                    double result = calulate(displayText);
                    String resultForDisplay = Double.toString(result);
                    displayCal.setText(resultForDisplay);


                    String resultForDnevnikIzracunov = displayText + " = " + resultForDisplay;
                    if (!dnevnikIzracunov.getText().isEmpty())
                        dnevnikIzracunov.setText(dnevnikIzracunov.getText() + "\n" + resultForDnevnikIzracunov);
                    else
                        dnevnikIzracunov.setText(resultForDnevnikIzracunov);

                    if (!dogodki.getText().isEmpty())
                        dogodki.setText(dogodki.getText() + "\nRačunam: " + resultForDisplay);
                    else
                        dogodki.setText("Računam: " + resultForDisplay);
                }
                else {
                    status.setText("Error: Račun je v naroben formatu!");
                }


                break;
            default:
                displayCal.setText(displayCal.getText() + buttonPressed);

        }
    }
    private double fistMultiply(String first, String sec){
        Double element1 = Double.parseDouble(first);
        Double element2 = Double.parseDouble(sec);
        return element1*element2;

    }


    private double calulate(String getText){
        String elemetnsOfEquation [] = getText.split(" ");
        /*double res;
        for (int i = 0; i < elemetnsOfEquation.length; i++) {
            if (elemetnsOfEquation[i].equals("*")){
                res = fistMultiply(elemetnsOfEquation[i-1], elemetnsOfEquation[i+1]);
            }
            else if (elemetnsOfEquation[i].equals("/")){

            }
        }*/
        double result = Double.parseDouble(elemetnsOfEquation[0]);
        for (int i = 1; i < elemetnsOfEquation.length; i++) {
            switch (elemetnsOfEquation[i]){
                case "*":
                    result *= Double.parseDouble(elemetnsOfEquation[++i]);
                    break;
                case "+":
                    result += Double.parseDouble(elemetnsOfEquation[++i]);
                    break;
                case "-":
                    result -= Double.parseDouble(elemetnsOfEquation[++i]);
                    break;
                case "/":
                    result /= Double.parseDouble(elemetnsOfEquation[++i]);
                    break;
                default:
                    result = Double.parseDouble(elemetnsOfEquation[i]);
            }
        }
        return result;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        harmonika.setExpandedPane(kalkulator);
    }

    public void deleteInput(ActionEvent actionEvent) {
        displayCal.clear();
    }

    public void deleteOneCharacter(ActionEvent actionEvent) {
        String deleteCharacter = displayCal.getText();
        if (!deleteCharacter.isEmpty()) {
            String showNewDeletedInput = deleteCharacter.substring(0, deleteCharacter.length() - 1);
            displayCal.setText(showNewDeletedInput);
        }

    }

}
