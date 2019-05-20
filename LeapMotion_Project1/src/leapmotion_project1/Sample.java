package leapmotion_project1;


import com.leapmotion.leap.Controller;
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nunos
 */
public class Sample {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        // Create a sample listener and controller
//        SampleListener listener = new SampleListener();
        Listener2 listener = new Listener2();

        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
        controller.addListener(listener);

        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");

        try {
            System.in.read();
            System.out.println("recording");
            listener.setState("recording");
            reader.nextLine();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        listener.setState("saving");
        System.out.println("saving");
        listener.onFrame(controller);
        // Remove the sample listener when done
        
        controller.removeListener(listener);
        }
    
}
