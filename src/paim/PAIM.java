/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paim;


import javafx.application.Application;
import javafx.stage.Stage;


/**
 *
 * @author Jonathan
 */
public class PAIM extends Application {

    @Override
    public void start(Stage primaryStage) {
        ScreenManager screenManager = new ScreenManager(primaryStage);
        
        // Create and show the initial login view
        LoginView loginView = new LoginView();
        DebugWindowsScreen debugScreen = new DebugWindowsScreen();
        
        //Place screens for screenManager here
        loginView.setScreenManager(screenManager);
        debugScreen.setScreenManager(screenManager);

        // add screens to the manager
        screenManager.addScreen("login", loginView.getView());
        screenManager.addScreen("debug", debugScreen.getView());
        
        //Show the login screen
        //screenManager.showScreen("login");
        screenManager.showScreen("debug");

    }

    public static void main(String[] args) {
        launch(args);
    }
}
