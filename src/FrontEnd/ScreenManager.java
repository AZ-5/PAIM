package FrontEnd;

//Imports
import BackEnd.Warnings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.function.Supplier;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class ScreenManager {
    private final Stage stage;
    private final HashMap<String, Supplier<Pane>> screenMap = new HashMap<>();

    public ScreenManager(Stage stage) {
        this.stage = stage;
    }

    // Register a screen factory
    public void addScreen(String name, Supplier<Pane> screenFactory) {
        screenMap.put(name, screenFactory);
    }

    // Show a new screen instance
    public void showScreen(String name) {
        Supplier<Pane> screenFactory = screenMap.get(name);
        if (screenFactory != null) {
            Pane screen = screenFactory.get(); // create a fresh instance
            
            // Create the menu bar
            MenuBar menuBar = createMenuBar();           
            BorderPane layout = new BorderPane();
            layout.setTop(menuBar);
            layout.setCenter(screen);     
            
            stage.setScene(new Scene(layout, 800, 600));
            stage.show();
        } else {
            System.err.println("Screen not found: " + name);
        }
    }
    
    private MenuBar createMenuBar() {

        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> stage.close());
        MenuItem logoutItem = new MenuItem("Logout");
        logoutItem.setOnAction(e -> showScreen("login"));
        fileMenu.getItems().addAll(logoutItem, exitItem);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(e -> Warnings.showAlert("PAIM will handle all "
                + "information related to purchases.."));
        helpMenu.getItems().add(aboutItem);

        MenuBar menuBar = new MenuBar(fileMenu, helpMenu);
        return menuBar;
}
    
}

