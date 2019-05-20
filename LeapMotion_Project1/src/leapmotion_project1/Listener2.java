/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leapmotion_project1;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.PointableList;
import com.leapmotion.leap.Vector;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreia Patrocínio
 * @author Pedro Pires
 */
public class Listener2 extends Listener {

    Scanner sc = new Scanner(System.in);
    public String state = "waiting"; //can be recording and saving as well

    private Double totalFrames = 0.0;
    private Double[] framesFingers = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    private Double[] sumAngles = {0.0, 0.0, 0.0};
    private Double[] sumVelocities = {0.0, 0.0, 0.0};
    Vector vectorDir = new Vector();
    //thumbDir, indexDir, middleDir, ringDir, pinkyDir
    private Vector[] sumFingersDirVelo = {vectorDir, vectorDir, vectorDir, vectorDir, vectorDir};
    //right, left, up, down
    int[] sumMovDir = {0, 0, 0, 0};
    boolean saved = false;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        //System.out.println(state);

        if (state.equals("recording")) {
            HandList hands = frame.hands();
            for (Hand hand : hands) {
                if (hand.isValid() && hand.isRight()) {
                    fingerFramesRatios(controller, frame, hand);
                    anglesAverage(controller, frame, hand);
                    palmVelocity(controller, frame, hand);
                    fingerDirectionVelocity(controller, frame, hand);
                    //System.out.println("pst");
                    averageMovementDirection(controller, frame, hand);
                    totalFrames++;
                }
            }
        } else if (state.equals("saving") && !saved) {
            saved = true;
            try {
                gravarFicheiro(controller, frame);
                state = "waiting";
            } catch (IOException ex) {
                Logger.getLogger(Listener2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Double[] fingerFramesRatios(Controller controller, Frame frame, Hand hand) {
        Double[] racio = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        int extendedFingers = 0;

        if (state.equals("recording")) {
            for (Finger finger : hand.fingers()) {
                if (finger.isExtended()) {
                    extendedFingers++;
                }
            }
            framesFingers[extendedFingers] += 1;   //mesmo resultado do switch mas apenas uma linha de código 
        } else if (state.equals("saving")) {
            for (int i = 0; i < 6; i++) {
                racio[i] = framesFingers[i] / totalFrames;
            }
        }
        return racio;
    }

    public Double[] anglesAverage(Controller controller, Frame frame, Hand hand) {
        float pitch = 0;
        float yaw = 0;
        float roll = 0;
        Double[] avgAngles = {0.0, 0.0, 0.0};

        if (state.equals("recording")) {
            sumAngles[0] += hand.direction().pitch();
            sumAngles[1] += hand.direction().yaw();
            sumAngles[2] += hand.direction().roll();
        } else if (state.equals("saving")) {
            for (int i = 0; i < 3; i++) {
                avgAngles[i] = sumAngles[i] / totalFrames;
            }
        }
        return avgAngles;
    }

    public Double[] palmVelocity(Controller controller, Frame frame, Hand hand) {
        float velocityX = 0, velocityY = 0, velocityZ = 0;
        double avgX = 0.0, avgY = 0.0, avgZ = 0.0;
        Double[] avgVelocities = {0.0, 0.0, 0.0};

        if (state.equals("recording")) {
            sumVelocities[0] += hand.palmVelocity().getX();
            sumVelocities[1] += hand.palmVelocity().getY();
            sumVelocities[2] += hand.palmVelocity().getZ();
        } else if (state.equals("saving")) {
            for (int i = 0; i < 3; i++) {
                avgVelocities[i] = sumVelocities[i] / totalFrames;
            }
        }
        return avgVelocities;
    }

    public Double[] fingerDirectionVelocity(Controller controller, Frame frame, Hand hand) {
        Vector a = new Vector();
        Vector[] avgDirectionsVelocities = {a, a, a, a, a};
        Double[] avgDirectionsVelocitiesSpeed = {0.0 , 0.0, 0.0 , 0.0, 0.0 };
        double norma;

        if (state.equals("recording")) {
            PointableList pointables = hand.pointables();
            for (Pointable p : pointables) {
                Finger finger = new Finger(p);
                switch (finger.type()) {
                    case TYPE_THUMB:
                        sumFingersDirVelo[0] = sumFingersDirVelo[0].plus(p.direction());
                        break;
                    case TYPE_INDEX:
                        sumFingersDirVelo[1] = sumFingersDirVelo[1].plus(p.direction());
                        break;
                    case TYPE_MIDDLE:
                        sumFingersDirVelo[2] = sumFingersDirVelo[2].plus(p.direction());
                        break;
                    case TYPE_RING:
                        sumFingersDirVelo[3] = sumFingersDirVelo[3].plus(p.direction());
                        break;
                    case TYPE_PINKY:
                        sumFingersDirVelo[4] = sumFingersDirVelo[4].plus(p.direction());
                        break;
                }
            }
        } else if (state.equals("saving")) {
            for (int i = 0; i < 5; i++) {
                avgDirectionsVelocities[i] = sumFingersDirVelo[i].divide(totalFrames.intValue());
                avgDirectionsVelocitiesSpeed[i] = Math.sqrt(Math.pow(avgDirectionsVelocities[i].getX(),2) + (Math.pow(avgDirectionsVelocities[i].getY(),2)) +(Math.pow(avgDirectionsVelocities[i].getZ(),2)) ); 
            }
        }
        return avgDirectionsVelocitiesSpeed;
    }

    public Double[] averageMovementDirection(Controller controller, Frame frame, Hand handCurrent) {
        Frame previous = controller.frame(1); //The previous frame
        HandList handsPrevious = previous.hands();
        Hand handPrevious;
        Double racioRight = 0.0, racioLeft = 0.0, racioUp = 0.0, racioDown = 0.0;
        Double[] racios = {0.0, 0.0, 0.0, 0.0};

        if (state.equals("recording")) {
            for (Hand hand : handsPrevious) {
                if (hand.isRight()) {
                    handPrevious = hand;
                    if (handPrevious.isValid()) {
                        float positionXcurrent = handCurrent.palmPosition().getX();
                        float positionXprevious = handPrevious.palmPosition().getX();
                        float positionYcurrent = handCurrent.palmPosition().getZ();
                        float positionYprevious = handPrevious.palmPosition().getZ();

                        if (positionXcurrent < positionXprevious) {
                            sumMovDir[0] += 1;
                        }
                        if (positionXcurrent > positionXprevious) {
                            sumMovDir[1] += 1;
                        }
                        if (positionYcurrent > positionYprevious) {
                            sumMovDir[2] += 1;
                        }
                        if (positionYcurrent < positionYprevious) {
                            sumMovDir[3] += 1;
                        }
                    }
                }
            }
        }else if (state.equals("saving")) {
            for (int i = 0; i < 4; i++) {
                racios[i] = sumMovDir[i] / totalFrames;
            }
        }
        return racios;
    }

    public void gravarFicheiro(Controller controller, Frame frame) throws IOException {
        System.out.println("before hands");
        Hand hand = new Hand();
        System.out.println("on frame " + frame.id());
        System.out.println("Insira o nome do gesto: ");
        String nomeFicheiro ="teste.txt"; //sc.next();
        BufferedWriter writer = new BufferedWriter(new FileWriter(nomeFicheiro));
        writer.write(totalFrames.toString());
        writer.newLine();
        writer.write(Arrays.toString(fingerFramesRatios(controller, frame, hand)));
        writer.newLine();
        writer.write(Arrays.toString(anglesAverage(controller, frame, hand)));
        writer.newLine();
        writer.write(Arrays.toString(palmVelocity(controller, frame, hand)));
        writer.newLine();
        writer.write(Arrays.toString(fingerDirectionVelocity(controller, frame, hand)));
        writer.newLine();
        writer.write(Arrays.toString(averageMovementDirection(controller, frame, hand)));
        writer.close();
        
    }

}
