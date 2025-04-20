/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;


import FrontEnd.DebugWindowsScreen;
import FrontEnd.ScreenManager;
import FrontEnd.LoginView;
import FrontEnd.Purchasing;
import FrontEnd.AdminScreen;
import FrontEnd.CreateAccount;
import FrontEnd.WorkorderScreen;
import FrontEnd.InventoryScreen;
import javafx.application.Application;
import javafx.stage.Stage;


public class PAIM extends Application {

    @Override
    public void start(Stage primaryStage) {
        ScreenManager screenManager = new ScreenManager(primaryStage);
        
        // Create and show the initial login view
        LoginView loginView = new LoginView();
        DebugWindowsScreen debugScreen = new DebugWindowsScreen();
        Purchasing purchasingScreen = new Purchasing();
        AdminScreen adminScreen = new AdminScreen();
        CreateAccount createAccScreen = new CreateAccount();
        WorkorderScreen workorderScreen = new WorkorderScreen();
        InventoryScreen inventoryScreen = new InventoryScreen();
        
        //Place screens for screenManager here
        loginView.setScreenManager(screenManager);
        debugScreen.setScreenManager(screenManager);
        purchasingScreen.setScreenManager(screenManager);
        adminScreen.setScreenManager(screenManager);
        createAccScreen.setScreenManager(screenManager);
        workorderScreen.setScreenManager(screenManager);
        inventoryScreen.setScreenManager(screenManager);

        // add screens to the manager
        screenManager.addScreen("login", loginView.getView());
        screenManager.addScreen("debug", debugScreen.getView());
        screenManager.addScreen("purchasing", purchasingScreen.getView());
        //screenManager.addScreen("admin", adminScreen.getView());
        screenManager.addScreen("create", createAccScreen.getView());
        screenManager.addScreen("workorder", workorderScreen.getView());
        screenManager.addScreen("inventory", inventoryScreen.getView());
        
        //Show the login screen
        screenManager.showScreen("login");
        //screenManager.showScreen("debug");

    }

    public static void main(String[] args) {
        launch(args);
    }
}
