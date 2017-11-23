package GuiHangAMan;

import KernelHangAMan.Players;
import KernelHangAMan.PseudonymeException;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * Created by Rifux on 07/05/2017.
 */
public class FormPane extends ModifFormPane{

    public FormPane(Scene welcomeScene) {
        super(welcomeScene,false);
        addInGridPane();
    }


    void addInGridPane() {
        getUserName().setText("User Name:");


        getGridInGrid().add(setSignUpButton(),1,0);
        getGridInGrid().add(setLogInButton(),2,0);

    }

    private Button setSignUpButton(){
        Button signUpButton = new Button("Sign up");
        signUpButton.setId("sign-up");

        signUpButton.setOnAction(e -> {
            if(getUserNameTextField().getText().equals("") ) getPseudonymeAnswer().setText("Veuillez entrer un pseudonyme");
            else{
                try {
                    Main.player = Players.registrate(getUserNameTextField().getText());
                    if(Main.player==null) {getPseudonymeAnswer().setText("pseudonyme existe deja"); }
                    else{
                        getWelcomeScene().setRoot(getWelcomeButtons());
                        getWelcomeButtons().top.getCurrentPlayerPseudonyme().setText(Main.player.getPseudonyme());
                        getWelcomeButtons().top.getCurrentPlayerScore().setText(" Highest Score: "+Main.player.getHighestScore());

                    }
                    getUserNameTextField().setText("");
                }catch (PseudonymeException ex){
                    getUserNameTextField().setText("");
                    getPseudonymeAnswer().setText("le pseudonyme doit commencer par une lettre");
                }
            }
        });

        return signUpButton;
    }

    private Button setLogInButton(){
        Button logInButton = new Button("Log in");
        logInButton.setId("log-in");

        logInButton.setOnAction(e -> {
            if(getUserNameTextField().getText().equals("") ) getPseudonymeAnswer().setText("Veuillez entrer un pseudonyme");
            else{
                Main.player=Players.authenticate(getUserNameTextField().getText());
                if(Main.player==null) {getPseudonymeAnswer().setText("Ce pseudonyme n'existe pas"); }
                else {
                    getWelcomeScene().setRoot(getWelcomeButtons());
                    getWelcomeButtons().top.getCurrentPlayerPseudonyme().setText(Main.player.getPseudonyme());
                    getWelcomeButtons().top.getCurrentPlayerScore().setText(" Highest Score: "+Main.player.getHighestScore());
                }
                getUserNameTextField().setText("");
            }

        });

        return logInButton;
    }
}
