package paim;

/** 
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Jonathan Cotterman
 * @Assignment Name: paim
 * @Date: Mar 2, 2025
 * @Subclass ScreenManager Description: 
 */
//Imports
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.HashMap;

public class ScreenManager {
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
            stage.setScene(new Scene(screen, 600, 400));
            stage.show();
        } else {
            System.err.println("Screen not found: " + name);
        }
    }
    
}

