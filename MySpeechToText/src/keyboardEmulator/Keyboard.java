/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keyboardEmulator;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.PointerInfo;
import java.awt.event.InputEvent;
import static java.awt.event.KeyEvent.VK_F5;
import  java.awt.Toolkit;
import static java.awt.event.KeyEvent.VK_F11;
/**
 *
 * @author Andreia Patrocínio
 */
public class Keyboard {
    public static final int FIVE_SECONDS = 5000;
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;
    private static  Robot robot;

    public static void main(String[] args) throws Exception {
        Keyboard keyboard= new Keyboard();
        
        int keyF5 = VK_F5;
        int keyF11 = VK_F11;
        keyPress(keyF5);   
    }
    
    public Keyboard(){
        try{
            robot = new Robot();
        }catch(AWTException e){
            System.out.println("Erro");
        }
        
    }
    
    public static void keyPress(int key){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); 
        robot.keyPress(key);
        robot.delay(100);
        robot.keyRelease(key);
    }
    //https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html 
}


