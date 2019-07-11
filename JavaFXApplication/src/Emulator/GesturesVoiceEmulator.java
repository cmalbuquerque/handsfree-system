/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emulator;

import appBackend.ChromeController;
import databaseDB.Action;
import databaseDB.EmulatorDB;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import userInterface.JavaFXApplication;

/**
 *
 * @author Andreia Patrocínio
 */
public class GesturesVoiceEmulator {

    private static Robot robot;
    private static WebDriver driver;
    private HashMap<String, String> HashMapGestureMLGestureBD = new HashMap<>();
    private Stage primaryStage;
    private HashMap<String, String> voiceCommands;
    private HashMap<String, String> gesturesCommands;
    private EmulatorDB emulatorDB;

    public GesturesVoiceEmulator(ChromeController chromeController, Stage primaryStage) {
        driver = chromeController.getDriver();
        this.primaryStage = primaryStage;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println("Erro");
        }

        emulatorDB = new EmulatorDB();
        emulatorDB.setProfile(1);
        //fetchFromDatabase(1);

    }

//    public void receiveGesture(String gesture) {
//        if (gesture.equals("closedUp") || gesture.equals("up") || gesture.equals("stoped")) {
//            System.out.println("Gestos de controlo");
//        } else {
//            for (String key : HashMapGestureMLGestureBD.keySet()) {
//                if (key.equals(gesture)) {
//                    String value = HashMapGestureMLGestureBD.get(key);
//                    for (String key2 : gesturesCommands.keySet()) {
//                        if (key2.equals(value)) {
//                            String action = gesturesCommands.get(key2);
//                            System.out.println("ACTION " + action);
//                            if (action != null) {
//                                executeAction(action);
//                            } else {
//                                System.out.println("Não está nenhuma ação associada " + action);
//                            }
//
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public void receiveVoiceCommand(String voice) {
//        System.out.println("--------------------------VOICE " + voice);
//        for (String key : voiceCommands.keySet()) {
//            if ((key.toLowerCase()).equals(voice)) {
//                String action = voiceCommands.get(key);
//                System.out.println("ACTION" + action);
//                executeAction(action);
//            }
//        }
//
//    }
    public void receiveGesture(String gesture) throws ClassNotFoundException {
        addToHashMapGestureMLGestureBD();
        if (gesture.equals("closedUp") || gesture.equals("up") || gesture.equals("stoped")) {
            System.out.println("Gestos de controlo");
        } else {
            for (String key : HashMapGestureMLGestureBD.keySet()) {
                if (key.equals(gesture)) {
                    String value = HashMapGestureMLGestureBD.get(key);
                    //System.out.println(EmulatorDB.getHashMapActionGesture());
                    for (String key2 : EmulatorDB.getHashMapActionGesture().keySet()) {
                        if (key2.equals(value)) {
                            String action = EmulatorDB.getHashMapActionGesture().get(key2);
                            System.out.println("ACTION " + action);
                            if (action != null) {
                                executeAction(action);
                            } else {
                                System.out.println("Não está nenhuma ação associada " + action);
                            }

                        }
                    }
                }
            }
        }
    }

    public void receiveVoiceCommand(String voice) throws ClassNotFoundException {
        System.out.println("--------------------------VOICE " + voice);
        for (String key : EmulatorDB.getHashMapActionVoice().keySet()) {

            if ((key.toLowerCase()).equals(voice)) {
                String action = EmulatorDB.getHashMapActionVoice().get(key);
                System.out.println("ACTION" + action);
                executeAction(action);
            }
        }

    }

    public void fetchFromDatabase(int profileNr) {
        //EmulatorDB emulatorDB = new EmulatorDB();
        emulatorDB.setProfile(profileNr);
//        addToHashMapGestureMLGestureBD();
//        gesturesCommands = emulatorDB.getHashMapActionGesture();
//        voiceCommands = emulatorDB.getHashMapActionVoice();
        System.out.println("----------------------------------------------------Changed Profile to profile nr" + profileNr);
    }

    public void addToHashMapGestureMLGestureBD() {
        HashMapGestureMLGestureBD.put("click", "Click");
        HashMapGestureMLGestureBD.put("twoFingerClick", "Double Click");
        HashMapGestureMLGestureBD.put("panLeft", "Open Hand Left-Right");
        HashMapGestureMLGestureBD.put("panRight", "Open Hand Right-Left");
        HashMapGestureMLGestureBD.put("panUp", "Open Hand Down-Up");
        HashMapGestureMLGestureBD.put("panDown", "Open Hand Up-Down");
        HashMapGestureMLGestureBD.put("closedStoped", "Close Hand");
    }

    public void executeAction(String action) {
        switch (action) {
            case "SWIPE_LEFT":
                left();
                break;
            case "SWIPE_RIGHT":
                right();
                break;
            case "SWIPE_DOWN":
                down();
                break;
            case "SWIPE_UP":
                up();
                break;
            case "SCROLL_DOWN":
                scrollDown();
                break;
            case "SCROLL_UP":
                scrollUp();
                break;
            case "ZOOM_IN":
                ctrMais();
                break;
            case "ZOOM_OUT":
                ctrMenos();
                break;
            case "DOUBLE CLICK":
                doubleClick();
                break;
            case "RIGHT_CLICK":
                rightClick();
                break;
            case "LEFT_CLICK":
                leftClick();
                break;
            case "DRAG_DOWN":
                dragDown();
                break;
            case "DRAG_UP":
                dragUp();
                break;
            case "DRAG_LEFT":
                dragLeft();
                break;
            case "DRAG_RIGHT":
                dragRight();
                break;
            case "SHOW_MENU":
                //primaryStage.setIconified(false);
                break;
            case "NEW_TAB":
                break;
            case "CHANGE_APP":
                //primaryStage.setIconified(false);
                break;
            case "REFRESH":
                keyPress(KeyEvent.VK_F5);
                break;
            case "UNDO":

                break;

            case "FULL_SCREEN":
                keyPress(KeyEvent.VK_F11);
                break;
            case "RELOAD":
                keyPress(KeyEvent.VK_F11);
                break;
            case "UP":
                keyPress(KeyEvent.VK_UP);
                break;
            case "DOWN":
                keyPress(KeyEvent.VK_DOWN);
                break;
            case "LEFT":
                keyPress(KeyEvent.VK_LEFT);
                break;
            case "RIGHT":
                keyPress(KeyEvent.VK_RIGHT);
                break;
            case "SPACE":
                keyPress(KeyEvent.VK_SPACE);
                break;
            case "ONE":
                keyPress(KeyEvent.VK_F1);
                break;
            case "TWO":
                keyPress(KeyEvent.VK_F2);
                break;
            case "THREE":
                keyPress(KeyEvent.VK_F3);
                break;
            case "FOUR":
                keyPress(KeyEvent.VK_F4);
                break;
            case "FIVE":
                keyPress(KeyEvent.VK_F5);
                break;
            case "SIX":
                keyPress(KeyEvent.VK_F6);
                break;

            //Dicoogle    
            case "IMAGE_INFO":
                dicoogleImageInfo();
                break;
            case "FLIP":
                dicoogleFlip();
                break;
            case "FLIP_RIGHT":
                dicoogleFlipRight();
                break;
            case "FLIP_LEFT":
                dicoogleFlipLeft();
                break;
            case "ROTATE":
                dicoogleFlip();
                break;
            case "ROTATE_RIGHT":
                dicoogleFlipRight();
                break;
            case "ROTATE_LEFT":
                dicoogleFlipLeft();
                break;
            case "RESET":
                dicoogleAnnotate();
                //dicoogleReset();
                break;
            case "RESIZE":
                dicoogleAnnotate();
                //dicoogleReset();
                break;
            case "HIDE":
                dicoogleHideShow();
                break;
        }
    }

    public void keyPress(int key) {
//        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); 
        robot.keyPress(key);
        robot.delay(100);
        robot.keyRelease(key);
    }
    //https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html 

    public void centerMouse() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int width = (int) d.getWidth() / 2;
        int heigth = (int) d.getHeight() / 2;
        robot.mouseMove(width, heigth);
    }

    public Point getPointerInfo() {
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        return b;
    }

    public void up() {
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newY = y - 100;
        robot.mouseMove(x, newY);
    }

    public void down() {
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newY = y + 100;
        robot.mouseMove(x, newY);
    }

    public void left() {
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newX = x - 100;
        robot.mouseMove(newX, y);
    }

    public void right() {
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newX = x + 100;
        robot.mouseMove(newX, y);
    }

    public void leftClick() {
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public void rightClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void doubleClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void scrollUp() {
        for (int i = 0; i < 5; i++) {
            robot.mouseWheel(-1);
        }
    }

    public void scrollDown() {
        for (int i = 0; i < 5; i++) {
            robot.mouseWheel(1);
        }
    }

    public void dragLeft() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newX = x - 75;
        robot.mouseMove(newX, y);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        centerMouse();
    }

    public void dragRight() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newX = x + 75;
        robot.mouseMove(newX, y);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        centerMouse();
    }

    public void dragUp() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newY = y - 75;
        robot.mouseMove(x, newY);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        centerMouse();
    }

    public void dragDown() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newY = y + 75;
        robot.mouseMove(x, newY);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        centerMouse();
    }

    public void ctrMais() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(50);
        robot.keyPress(KeyEvent.VK_PLUS);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_PLUS);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    public void ctrMenos() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(50);
        robot.keyPress(KeyEvent.VK_LESS);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_LESS);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    public void dicoogleMisc() {
        WebElement misc = driver.findElement(By.id("btn-Miscellaneous"));
        misc.click();
    }

    public void dicoogleMiscToogleFullscreen() {
        WebElement misc = driver.findElement(By.id("btn-FullscreenTool"));
        misc.click();
    }

    public void dicoogleReset() {
        WebElement reset = driver.findElement(By.cssSelector("i[class='fa fa-power-off']"));
        reset.click();
    }

    public static void dicoogleAnnotate() {
        WebElement annotate = driver.findElement(By.cssSelector("i[class='icon-annotate']"));
        annotate.click();
    }

    public void dicoogleAnnotateRuler() {
        WebElement annotate = driver.findElement(By.cssSelector("i[class='icon-line']"));
        annotate.click();
    }

    public static void dicoogleAnnotateRectangular() {
        WebElement annotate = driver.findElement(By.cssSelector("i[class='icon-square']"));
        annotate.click();
    }

    public void dicoogleImageInfo() {
        WebElement imageInfo = driver.findElement(By.cssSelector("i[class='fa fa-info-circle']"));
        imageInfo.click();

    }

    public void dicoogleImageAdjustments() {
        WebElement imageadjust = driver.findElement(By.cssSelector("i[class='fa fa-adjust']"));
        imageadjust.click();
    }

    public void dicoogleFit() {
        WebElement fit = driver.findElement(By.cssSelector("i[class='fa fa-arrows']"));
        fit.click();
    }

    public void dicoogleFlip() {
        WebElement flip = driver.findElement(By.cssSelector("i[class='icon-rotate']"));
        flip.click();
    }

    public void dicoogleFlipRight() {
        WebElement flip = driver.findElement(By.cssSelector("i[class='fa fa-rotate-right']"));
        flip.click();
    }

    public void dicoogleFlipLeft() {
        WebElement flip = driver.findElement(By.cssSelector("i[class='fa fa-rotate-left']"));
        flip.click();
    }

    public void dicoogleHideShow() {
        WebElement hide = driver.findElement(By.cssSelector("i[class='fa fa-undo']"));
        hide.click();

    }

}
