package album.cover.maker;

import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.FilenameUtils;

public class AlbumCoverMaker extends Application {
    
    private File currImage;
    private File currSaveLoc;
    
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        
        /** Menu Setup **/
        MenuBar menuBar = new MenuBar();
        
        Menu menuFile = new Menu("File");
        MenuItem mOpen = new MenuItem("Open");
        MenuItem mSaveAs = new MenuItem("Save As...");
        MenuItem mExit = new MenuItem("Exit");
        menuFile.getItems().addAll(mOpen, mSaveAs, mExit);
        
        mOpen.setOnAction((ActionEvent e) -> {
            FileChooser fC = new FileChooser();
            fC.setTitle("Select a Photo for Album Cover.");
            try {
                do {
                    currImage = fC.showOpenDialog(stage);
                } while (!FilenameUtils.getExtension(currImage.getName()).
                        equals("png") ||
                         !FilenameUtils.getExtension(currImage.getName()).
                        equals("jpg"));
            } catch (NullPointerException npe) {
                
            }
        });
        
        mSaveAs.setOnAction((ActionEvent e) -> {
            FileChooser fC = new FileChooser();
            fC.setTitle("Select Save Destination");
            currSaveLoc = fC.showOpenDialog(stage);
        });
        
        mExit.setOnAction((ActionEvent e) -> {
            Platform.exit();
        });
        
        menuBar.getMenus().addAll(menuFile);
        /**            **/
        
        HBox vboxContainer = new HBox();
        VBox vbox = new VBox();
        
        TextField ytLinkTF = new TextField("Song Link");
        TextField titleTF = new TextField("Song Title");
        TextField artistTF = new TextField("Artist");
        
        HBox row1Container = new HBox();
        TextField albumTF = new TextField("Album");
        TextField yearTF = new TextField("Year");
        row1Container.setSpacing(10);
        row1Container.setAlignment(Pos.CENTER);
        row1Container.getChildren().addAll(albumTF, yearTF);
        
        HBox row2Container = new HBox();
        Label row2ContainerLabel = new Label("Edit Fonts:");
        Button headingButton = new Button("Heading Font");
        Button titleButton = new Button("Title Font");
        Button subtitleButton = new Button("Subtitle Font");
        row2Container.setSpacing(10);
        row2Container.setAlignment(Pos.CENTER);
        row2Container.getChildren().addAll(row2ContainerLabel, headingButton,
                titleButton, subtitleButton);
        
        Text text = new Text("FONTS\n----------------\nHeading Font: \nTitle"
                + " Font: \nSubtitle Font: ");
        
        Label styleCBLabel = new Label("Style");
        ObservableList<String> options = 
            FXCollections.observableArrayList(
                "Normal",
                "Indie",
                "ODESZA",
                "R&B",
                "Chill",
                "Future Bass",
                "Trap",
                "Lofi"
            );
        ComboBox styleCB = new ComboBox(options);
        styleCBLabel.setLabelFor(styleCB);
        
        ArrayList<Separator> vboxSeps = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            vboxSeps.add(new Separator());
        }
        
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(new Text("Editor"), vboxSeps.get(0), ytLinkTF,
                titleTF, artistTF, row1Container, vboxSeps.get(1), 
                row2Container, vboxSeps.get(2), text, vboxSeps.get(3), 
                styleCBLabel, styleCB, vboxSeps.get(4));
        Separator s = new Separator(Orientation.VERTICAL);
        vboxContainer.getChildren().addAll(vbox, s);
        
        VBox hboxContainer = new VBox();
        HBox hbox = new HBox();
        
        Text statusText = new Text("Status: ");
        
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(5));
        hbox.getChildren().addAll(statusText);
        hboxContainer.getChildren().addAll(new Separator(), hbox);
        
        root.setTop(menuBar);
        root.setLeft(vboxContainer);
        root.setBottom(hboxContainer);
        
        Scene scene = new Scene(root, 1280, 800);
        
        stage.setTitle("Album Cover Maker v0.1");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
