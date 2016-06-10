package controller.auth;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import model.db.DBProperties;
import model.helper.Converter;
import model.helper.ImageManipulation;

/**
 * This <PPP_1> project in package <controller.auth> created by :
 * Name         : syafiq
 * Date / Time  : 03 June 2016, 5:35 AM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class CRegister implements Initializable
{
    @FXML private BorderPane  root;
    @FXML private ImageView   iv_avatar;
    @FXML private TextField   tf_firstName;
    @FXML private TextField   tf_middleName;
    @FXML private TextField   tf_lastName;
    @FXML private TextField   tf_nickName;
    @FXML private DatePicker  dp_dateOfBirth;
    @FXML private ToggleGroup tg_gender;
    @FXML private Label       l_avatarPath;
    private       String      gender;
    private       FileChooser fileChooser;
    private       File        file;

    @Override public void initialize(URL location, ResourceBundle resources)
    {
        this.changeDatePickerFormat();
        this.setGenderListener();
        this.fileChooser = new FileChooser();
    }

    private void setGenderListener()
    {
        tg_gender.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            CRegister.this.gender = ((RadioButton) newToggle.getToggleGroup().getSelectedToggle()).getText();
        });
    }

    private void changeDatePickerFormat()
    {
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>()
        {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(CRegister.this.dp_dateOfBirth.getPromptText());

            @Override public String toString(LocalDate date)
            {
                if(date != null)
                {
                    return dateFormatter.format(date);
                }
                else
                {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string)
            {
                if(string != null && !string.isEmpty())
                {
                    return LocalDate.parse(string, dateFormatter);
                }
                else
                {
                    return null;
                }
            }
        };
        this.dp_dateOfBirth.setConverter(converter);
    }

    public void chooseAvatar(ActionEvent actionEvent)
    {
        this.configureFileChooser(this.fileChooser);
        this.file = fileChooser.showOpenDialog(this.root.getScene().getWindow());
        if(this.file != null)
        {
            this.l_avatarPath.setText(this.file.getAbsolutePath());
            this.iv_avatar.setImage(new Image(this.file.toURI().toString()));
            model.helper.ImageViewCenterImage.adjustImagePosition(this.iv_avatar);

        }
    }

    public void cancelRegister(ActionEvent actionEvent)
    {
        ((Stage) this.root.getScene().getWindow()).close();
    }

    public void doRegister(ActionEvent actionEvent)
    {
        DBProperties properties = DBProperties.getInstance();
        try
        {
            properties.preparedStatement = properties.connection.prepareStatement("INSERT INTO `user` VALUES (NULL, ?, ?, ?, ?, ?, ?, NULL)");
            properties.preparedStatement.setString(1, this.tf_firstName.getText() + " " + this.tf_middleName.getText() + " " + this.tf_lastName.getText());
            properties.preparedStatement.setString(2, this.tf_nickName.getText());
            properties.preparedStatement.setString(3, this.dp_dateOfBirth.getValue().toString());
            properties.preparedStatement.setString(4, this.gender.equalsIgnoreCase("Male") ? "L" : "P");
            properties.preparedStatement.setBytes(5, Converter.fileToBytes(this.file));
            properties.preparedStatement.setBytes(6, Converter.bufferedImageToBytes(ImageManipulation.scale(ImageIO.read(file), 256), "png"));
            properties.preparedStatement.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success Register");
            alert.setHeaderText("Look, an Success");
            alert.setContentText("Success Register User");
            alert.showAndWait();

            ((Stage) this.root.getScene().getWindow()).close();
        }
        catch(SQLException ignored)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Register");
            alert.setHeaderText("Look, an Error");
            alert.setContentText("Error Register User");
            alert.showAndWait();
        }
        catch(IOException ignored)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Upload");
            alert.setHeaderText("Look, an Error");
            alert.setContentText("Error Upload Your Photo");
            alert.showAndWait();
        }
        catch(NullPointerException ignored)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Photo Required");
            alert.setHeaderText("Look, an Error");
            alert.setContentText("Please Select a photo");
            alert.showAndWait();
        }
    }

    private void configureFileChooser(final FileChooser fileChooser)
    {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"), new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));
    }

}
