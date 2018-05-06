package Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Map.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Mapping Directions");
        primaryStage.setScene(new Scene(root));
        root.getStylesheets().add(MapController.class.getResource("./Styles.css").toExternalForm());
        primaryStage.setMaximized(true);
        primaryStage.show();
        ((MapController)loader.getController()).loadMapView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
