package FrontEnd;

//Imports
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.function.Supplier;

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
            stage.setScene(new Scene(screen, 800, 600));
            stage.show();
        } else {
            System.err.println("Screen not found: " + name);
        }
    }
    
    
    /*
    private final Stage stage;
    private final HashMap<String, Pane> screenMap = new HashMap<>();

    public ScreenManager(Stage stage) {
        this.stage = stage;
    }

    // Add a screen
    public void addScreen(String name, Pane screen) {
        screenMap.put(name, screen);
    }

    // Switch to a screen
    public void showScreen(String name) {
        Pane screen = screenMap.get(name);
        if (screen != null) {
            stage.setScene(new Scene(screen, 800, 600));
            stage.show();
        } else {
            System.err.println("Screen not found: " + name);
        }
    }
    */
}

