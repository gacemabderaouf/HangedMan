package GuiHangAMan;


import KernelHangAMan.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    public static Player player = null;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Words.initWords("mots.poo");
        Scene welcomeScene = new Scene(new Pane(), 550, 600);


        BestScorePane bestScorePane=new BestScorePane(welcomeScene);
        FormPane formPane=new FormPane(welcomeScene);
        ModifFormPane modifFormPane=new ModifFormPane(welcomeScene);
        WelcomeButtons welcomeButtons=new WelcomeButtons(primaryStage,welcomeScene,null,bestScorePane,modifFormPane,formPane);
        welcomeScene.setRoot(formPane);


        primaryStage.setTitle("Hanged Man");
        primaryStage.setScene(welcomeScene);


        primaryStage.setOnCloseRequest(e->{
            e.consume();

            Players.dbToFile();
            primaryStage.close();
        });
        primaryStage.show();

/*******************************************************************************************/

    }
    public static void main(String[] args) {

        launch(args);

        /*Connection connection=null;
        Statement state;
        String url="jdbc:sqlite:players.db";
        try  {
            connection = DriverManager.getConnection(url);
            state=connection.createStatement();


            Players.registrate("alim");
            Players.registrate("yacine");
            Players.registrate("yassine");
            Players.registrate("nadir");
            Players.registrate("amine");

            state.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

}