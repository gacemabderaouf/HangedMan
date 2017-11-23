package GuiHangAMan;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Created by Rifux on 14/05/2017.
 */
public class SaveRequest {

    static boolean answer=false;

    static  boolean saveScoreRequest(){

        Stage boxRequest= new Stage();
        boxRequest.initModality(Modality.APPLICATION_MODAL);
        boxRequest.setTitle("Score Saving Request");
        boxRequest.setOnCloseRequest(e->{
            e.consume();
            boxRequest.close();
        });

        Label label=new Label();
        label.setText("Do you want to save your score ?");

        Button yes = new Button("Yes");
        yes.setOnMouseClicked(e->{
            answer=true;
            boxRequest.close();
        });
        yes.setAlignment(Pos.CENTER_LEFT);

        Button no = new Button("No");
        no.setOnMouseClicked(e->{
            boxRequest.close();
        });
        yes.setAlignment(Pos.CENTER_RIGHT);


        HBox hBox = new HBox(50);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(yes,no);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,hBox);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout,300,150);
        boxRequest.setScene(scene);
        boxRequest.showAndWait();

        return answer;
    }
}
