package controller.main.dump;

import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import view.setting.databaseConfigurator.IDatabaseConf;

/**
 * This <PPP_1> project in package <view.setting.databaseConfigurator.dump> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 6:36 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class DumpMethod
{
    Parent root = null;

    @Deprecated public void change_db_setting_1(ActionEvent actionEvent)
    {
        try
        {
            // Load the fxml file and create a new stage for the popup
            FXMLLoader loader      = new FXMLLoader(IDatabaseConf.class.getResource("VDatabaseConf.fxml"));
            GridPane   page        = loader.load();
            Stage      rootStage   = null;
            Stage      dialogStage = new Stage();
            dialogStage.setWidth(320);
            dialogStage.setHeight(200);
            dialogStage.setX(rootStage.getX() + (rootStage.getWidth() / 2) - (dialogStage.getWidth() / 2));
            dialogStage.setY(rootStage.getY() + (rootStage.getHeight() / 2) - (dialogStage.getHeight() / 2));
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(root.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller
/*            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);*/

            // Show the dialog and wait until the user closes it
            dialogStage.show();
            //dialogStage.showAndWait();


        }
        catch(IOException e)
        {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }

    @Deprecated public void change_db_setting_2(ActionEvent actionEvent)
    {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Login Dialog");
        dialog.setHeaderText("Look, a Custom Login Dialog");

        // Set the icon (must be included in the project).
        //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == loginButtonType)
            {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
    }
}
