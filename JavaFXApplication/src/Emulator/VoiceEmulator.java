/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emulator;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 *
 * @author Andreia Patrocínio
 */
public class VoiceEmulator {

    public static final int FIVE_SECONDS = 5000;
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;
    private static Robot robot;

    public static void main(String[] args) throws Exception {
        //VoiceEmulator voiceEmulator = new VoiceEmulator();
//        mouseMover.centerMouse();
//        mouseMover.getPointerInfo();
        robot = new Robot();
        int key = KeyEvent.VK_F1;
        //keyPress(key); 

    }

    public VoiceEmulator() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println("Erro");
        }

    }

    public static void centerMouse() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int width = (int) d.getWidth() / 2;
        int heigth = (int) d.getHeight() / 2;
        System.out.println("witdh " + width + "  heigth " + heigth);
        robot.mouseMove(width, heigth);
    }

    public static void keyPress(int key) {
//        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); 
        robot.keyPress(key);
        robot.delay(100);
        robot.keyRelease(key);
    }
    //https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html 

    public static void ctrMais() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(50);
        robot.keyPress(KeyEvent.VK_PLUS);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_PLUS);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    public static void ctrMenos() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(50);
        robot.keyPress(KeyEvent.VK_LESS);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_LESS);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }
}
