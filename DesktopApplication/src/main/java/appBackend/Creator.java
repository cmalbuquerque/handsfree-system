package appBackend;

public class Creator {

    public static void main(String[] args) {

        //start chromeController Thread
        ChromeController chrome = new ChromeController();
        chrome.start();

        DesktopMain.startUI(args);

    }

}
