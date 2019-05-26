/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mouseEmulator;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.PointerInfo;
import java.awt.event.InputEvent;
import static java.awt.event.KeyEvent.VK_F5;
import  java.awt.Toolkit;

/**
 *
 * @author Andreia Patrocínio
 */
public class MouseMover {
    public static final int FIVE_SECONDS = 5000;
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;
    private static  Robot robot;

    public static void main(String[] args) throws Exception {
        MouseMover mouseMover= new MouseMover();
        mouseMover.centerMouse();
        mouseMover.getPointerInfo();
//robot = new Robot();
        //int key = VK_F5;
        //keyPress(key);   
    }
    public MouseMover(){
        try{
            robot = new Robot();
        }catch(AWTException e){
            System.out.println("Erro");
        }
        
    }
    public static void centerMouse(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int width = (int) d.getWidth()/2;
        int heigth = (int) d.getHeight()/2;
        System.out.println("witdh " + width + "  heigth "+ heigth );
        robot.mouseMove(width, heigth);       
    }
    
    public static Point getPointerInfo(){
        PointerInfo a = MouseInfo.getPointerInfo();
        Point b = a.getLocation();
        System.out.println("Pointer info: " + b);
        return b;
    }

    public static void up(){
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newY = y-100;
        robot.mouseMove(x, newY);
    }
    
    public static void down(){
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newY = y+100;
        robot.mouseMove(x, newY);
    }
    
    public static void left(){
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newX = x-100;
        robot.mouseMove(newX, y);
    }
    
    public static void right(){
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newX = x+100;
        robot.mouseMove(newX, y);
    }
    
    public static void leftClick(){
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);   
    }
    
    public static void rightClick(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);   
    }
    
    public static void doubleClick(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(100);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);  
    }
    
     public static void scrollUp(){
        for(int i=0; i<50; i++){
            robot.mouseWheel(-1);
        }
    }
    
    public static void scrollDown(){
        for(int i=0; i<50; i++){
            robot.mouseWheel(1);
        }
    }
    
    public static void dragLeft(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newX = x-75;
        robot.mouseMove(newX, y);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); 
        centerMouse();
    }
    
    public static void dragRight(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newX = x+75;
        robot.mouseMove(newX, y);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); 
        centerMouse();
    }
    
    public static void dragUp(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newY = y-75;
        robot.mouseMove(x, newY);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);  
        centerMouse();
    }
    
    public static void dragDown(){
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        int x = (int) getPointerInfo().getX();
        int y = (int) getPointerInfo().getY();
        int newY = y+75;
        robot.mouseMove(x, newY);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); 
        centerMouse();
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


