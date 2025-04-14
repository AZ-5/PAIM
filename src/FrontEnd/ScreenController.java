package FrontEnd;

//Imports
public class ScreenController {
        protected ScreenManager screenManager;

        public void setScreenManager(ScreenManager screenManager) {
            this.screenManager = screenManager;
        }

        public void switchTo(String screenName) {
            if (screenManager != null) {
                screenManager.showScreen(screenName);
            }
        }
    } //End Subclass ScreenController