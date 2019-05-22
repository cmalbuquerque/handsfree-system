package leapmotion_project1;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;
import java.awt.AWTException;
import mouseEmulator.MouseMover;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nunos
 */
class SampleListener extends Listener {

    private final int FPS = 100 / 10;
    MouseMover mouseMover = new MouseMover(); 
    
    @Override
    public void onConnect(Controller controller) {
        System.out.println("Connected");

    }

    public void recognizeGestures(Controller controller, Frame frame) {
        HandList hands = frame.hands();

        Hand firstHand = hands.get(0);
        if (firstHand.isValid()) {
            System.out.println("Hand normal, direction and position vectors are: " + firstHand.palmNormal() + " - " + firstHand.direction() + " - " + firstHand.palmPosition());
            double[] avgSpeed = getFPSAverage(controller);
            System.out.println("Hand average speed is " + avgSpeed[0] + " " + avgSpeed[1] + " over the last " + FPS + " frames.");
            System.out.println("Angles arround x axis " + firstHand.direction().pitch() + " and y axis " + firstHand.direction().yaw()+ " and axis " + firstHand.palmNormal().roll());
            //slide up
            if (avgSpeed[0] < 30 && avgSpeed[0] > -30 && avgSpeed[1] > 50) {
                System.out.println(firstHand);
                System.out.println("Hand moving up");
                System.out.println("Hand speed is: " + firstHand.palmVelocity().getY() + " m/s");
                mouseMover.dragUp();
            } 
            //slide down
            else if (avgSpeed[0] < 30 && avgSpeed[0] > -30  && avgSpeed[1] < -50) {
                System.out.println(firstHand);
                System.out.println("Hand moving down");
                System.out.println("Hand speed is: " + firstHand.palmVelocity().getY() * -1 + " m/s");
                mouseMover.dragDown();
            } 
            //slide right 
            else if (avgSpeed[0] > 50 && avgSpeed[1] < 30 && avgSpeed[1] > -30) {
                System.out.println(firstHand);
                System.out.println("Hand moving right");
                System.out.println("Hand speed is: " + firstHand.palmVelocity().getX() + " m/s");
                mouseMover.dragRight();
            }
            //slide left 
            else if (avgSpeed[0] < -50 && avgSpeed[1] < 30 && avgSpeed[1] > -30) {
                System.out.println(firstHand);
                System.out.println("Hand moving left");
                System.out.println("Hand speed is: " + firstHand.palmVelocity().getX() * -1 + " m/s");
                mouseMover.dragLeft();
            } else {
                System.out.println("Hand not moving duh.\n");
            }
        }
    }

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();

        if ((frame.id() % FPS) == 0) {
            System.out.println(frame.id());
            recognizeGestures(controller, frame);
        }
    }

    private double[] getFPSAverage(Controller controller) {
        double xspeed = 0;
        double yspeed = 0;
        double[] vals = new double[2];

        for (int i = 0; i < FPS; i++) {
            Hand Hand = controller.frame(i).hands().get(0);
            xspeed += Hand.palmVelocity().getX();
            yspeed += Hand.palmVelocity().getY();
        }
        vals[0] = xspeed / FPS;
        vals[1] = yspeed / FPS;
        return vals;
    }

}
