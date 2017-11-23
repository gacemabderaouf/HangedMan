package GuiHangAMan;

import KernelHangAMan.Player;
import KernelHangAMan.Players;
import KernelHangAMan.PseudonymeException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Created by Rifux on 07/05/2017.
 */
public class ModifFormPane extends WelcomeRoot{

    private  GridPane gridPane;
    private GridPane gridInGrid;
    private WelcomeButtons welcomeButtons;
    private Label userName;
    private TextField userNameTextField;
    private Text pseudonymeAnswer;

    public ModifFormPane(Scene welcomeScene) {
        super(welcomeScene);
        gridPane=setGridPane(true);
        setCenter(gridPane);
    }



    public ModifFormPane(Scene welcomeScene,boolean useless) {
        super(welcomeScene);

        setCenter(setGridPane(false));
    }

    GridPane setGridPane(boolean goback){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(30);
        grid.setPadding(new Insets(25, 25, 25, 25));

        gridInGrid=new GridPane();
        gridInGrid.setHgap(50);
        grid.add(gridInGrid,1,2);

        pseudonymeAnswer = new Text();
        pseudonymeAnswer.setId("pseudonymeAnswer");
        grid.add(pseudonymeAnswer,1,4);

        userName = new Label("New User Name:");
        userName.setId("userName");
        grid.add(userName, 0, 1);

        userNameTextField = new TextField();
        grid.add(userNameTextField, 1, 1);
        userNameTextField.setOnKeyPressed(e->{
            pseudonymeAnswer.setText("");
        });

        if(goback) {
            gridInGrid.add(setLogInButton(), 2, 0);
            //grid.add(new GoBackButton(getWelcomeScene(), welcomeButtons), 1, 6);
        }

        return grid;
    }

    private Button setLogInButton(){
        Button logInButton = new Button("Change Pseudonyme");
        logInButton.setId("log-in");

        logInButton.setOnAction(e -> {
            if(userNameTextField.getText().equals("") ) pseudonymeAnswer.setText("Veuillez entrer un pseudonyme");
            else{
                try {
                    if(Players.modifyPseudonyme(Main.player,userNameTextField.getText())) {
                        getWelcomeScene().setRoot(welcomeButtons);
                        welcomeButtons.top.getCurrentPlayerPseudonyme().setText(Main.player.getPseudonyme());
                        welcomeButtons.top.getCurrentPlayerScore().setText(" Highest Score: "+Main.player.getHighestScore());
                    }
                    else{
                        pseudonymeAnswer.setText("pseudonyme existe deja");
                    }
                    userNameTextField.setText("");
                }catch (PseudonymeException ex){
                    userNameTextField.setText("");
                    pseudonymeAnswer.setText("le pseudonyme doit commencer par une lettre");
                }
            }
        });

        return logInButton;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public GridPane getGridInGrid() {
        return gridInGrid;
    }

    public WelcomeButtons getWelcomeButtons() {
        return welcomeButtons;
    }

    public void setWelcomeButtons(WelcomeButtons welcomeButtons) {
        this.welcomeButtons = welcomeButtons;
    }

    public Label getUserName() {
        return userName;
    }

    public TextField getUserNameTextField() {
        return userNameTextField;
    }

    public Text getPseudonymeAnswer() {
        return pseudonymeAnswer;
    }
}
