/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machineLearning;

import Emulator.GesturesEmulator;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.PointableList;
import com.leapmotion.leap.Vector;
import java.io.FileReader;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

/**
 *
 * @author Pedro Pires
 */
public class MListener extends Listener {

    private String filepath = "gestureData.arff";
    private FileReader trainreader;
    private Instances train;
    private Instances helper;
    private RandomForest forest;
    private Evaluation eval, evalT;
    private Instance toPredict;
    private boolean process = false;//não sei o que é isto, maybe algo vai falhar.
    private Double[] framesFingers;
    private Double[] sumAngles;
    private Double[] sumVelocities;
    //thumbDir, indexDir, middleDir, ringDir, pinkyDir
    private Vector[] sumFingersDirVelo;
    //right, left, up, down
    private int[] sumMovDir;
    private Double totalFrames;
    private String state;
        String[] gestos= {"click", "twoFingerClick", "panLeft", "panRight", "panUp", "panDown", "closedUp", "up", "stoped", "closedStoped"};
        private GesturesEmulator emulator;
        
    public MListener(GesturesEmulator emulator) {
        try {
            this.trainreader = new FileReader(filepath);
            this.train = new Instances(trainreader);
            this.train.setClassIndex(0);
            this.trainreader = new FileReader(filepath);
            this.helper = new Instances(trainreader);
            this.helper.setClassIndex(0);
            this.helper.delete();
            this.forest = (RandomForest) SerializationHelper.read("savedTreeModel.model");
            this.eval = new Evaluation(train);
            this.toPredict = train.firstInstance();
            resetInstance();
            this.process = false;
            this.framesFingers = new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
            this.sumAngles = new Double[]{0.0, 0.0, 0.0};
            this.sumVelocities = new Double[]{0.0, 0.0, 0.0};
            this.sumFingersDirVelo = new Vector[]{new Vector(), new Vector(), new Vector(), new Vector(), new Vector()};             //thumbDir, indexDir, middleDir, ringDir, pinkyDir
            this.sumMovDir = new int[]{0, 0, 0, 0};            //right, left, up, down
            this.totalFrames = 0.0;
            this.state = "data";
            this.emulator = emulator;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        resetDataStructures();
        resetInstance();
        if (frame.id() % 10 == 0) {
            for (Hand hand : frame.hands()) {
                if (hand.isValid() && hand.isRight()) {
                    try {
                        this.state = "data";
                        getData(controller);
                        this.state = "average";
                        getData(controller);
                        Double treeResult = eval.evaluateModelOnce(forest, toPredict);
                        System.out.println("PREDICTED:");
                        System.out.println("forest predicted: " + gestos[treeResult.intValue()]);
                        //emulator.receiveGesture(gestos[treeResult.intValue()]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    private void resetInstance() {
        int ai = toPredict.numAttributes();
        for (int i = 0; i < ai; i++) {
            //System.out.println(i);
            if (i == 0) {
                toPredict.setMissing(i);

            } else {
                toPredict.setMissing(i);
            }
        }
    }

    private void getData(Controller controller) {
        Frame frame = controller.frame();
        if ("average".equals(this.state)) {
            HandList hands = frame.hands();
            int index = 1;
            int allow = 1;
            for (Hand hand : hands) {
                if (hand.isValid() && hand.isRight()) {
                    Double[] fingers = fingerFramesRatios(controller, frame, hand);
                    for (Double d : fingers) {
                        toPredict.setValue(index, d);
                        index++;
                    }
                    Double[] angles = anglesAverage(controller, frame, hand);
                    for (Double d : angles) {
                        toPredict.setValue(index, d);
                        index++;
                    }
                    Double[] palm = palmVelocity(controller, frame, hand);
                    for (Double d : palm) {
                        toPredict.setValue(index, d);
                        index++;
                    }
                    //Double[] fingersVel = fingerDirectionVelocity(controller, frame, hand);
                    Double[] moveDir = averageMovementDirection(controller, frame, hand);
                    for (Double d : moveDir) {
                        toPredict.setValue(index, d);
                        index++;
                    }
                }
            }
        } else {
            for (int i = 1; i < 6; i++) {
                HandList hands = frame.hands();
                for (Hand hand : hands) {
                    if (hand.isValid() && hand.isRight()) {
                        fingerFramesRatios(controller, frame, hand);
                        anglesAverage(controller, frame, hand);
                        palmVelocity(controller, frame, hand);
                        //fingerDirectionVelocity(controller, frame, hand);
                        averageMovementDirection(controller, frame, hand);
                        totalFrames++;
                    }
                }
                frame = controller.frame(i);
            }
        }
    }

    private void resetDataStructures() {
        this.process = false;
        this.framesFingers = new Double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        this.sumAngles = new Double[]{0.0, 0.0, 0.0};
        this.sumVelocities = new Double[]{0.0, 0.0, 0.0};
        this.sumFingersDirVelo = new Vector[]{new Vector(), new Vector(), new Vector(), new Vector(), new Vector()};        //thumbDir, indexDir, middleDir, ringDir, pinkyDir
        this.sumMovDir = new int[]{0, 0, 0, 0};         //right, left, up, down
        this.totalFrames = 0.0;
    }

    public Double[] fingerFramesRatios(Controller controller, Frame frame, Hand hand) {
        Double[] racio = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        int extendedFingers = 0;

        if (state.equals("data")) {
            for (Finger finger : hand.fingers()) {
                if (finger.isExtended()) {
                    extendedFingers++;
                }
            }
            framesFingers[extendedFingers] += 1;   //mesmo resultado do switch mas apenas uma linha de código 
        } else if (state.equals("average")) {
            for (int i = 0; i < 6; i++) {
                racio[i] = framesFingers[i] / totalFrames;
            }
        }
        return racio;
    }

    public Double[] anglesAverage(Controller controller, Frame frame, Hand hand) {
        Double[] avgAngles = {0.0, 0.0, 0.0};

        if (state.equals("data")) {
            sumAngles[0] += hand.direction().pitch();
            sumAngles[1] += hand.direction().yaw();
            sumAngles[2] += hand.direction().roll();
        } else if (state.equals("average")) {
            for (int i = 0; i < 3; i++) {
                avgAngles[i] = sumAngles[i] / totalFrames;
            }
        }
        return avgAngles;
    }

    public Double[] palmVelocity(Controller controller, Frame frame, Hand hand) {
        Double[] avgVelocities = {0.0, 0.0, 0.0};

        if (state.equals("data")) {
            sumVelocities[0] += hand.palmVelocity().getX();
            sumVelocities[1] += hand.palmVelocity().getY();
            sumVelocities[2] += hand.palmVelocity().getZ();
        } else if (state.equals("average")) {
            for (int i = 0; i < 3; i++) {
                avgVelocities[i] = sumVelocities[i] / totalFrames;
            }
        }
        return avgVelocities;
    }

    public Double[] fingerDirectionVelocity(Controller controller, Frame frame, Hand hand) {
        Vector a = new Vector();
        Vector[] avgDirectionsVelocities = {a, a, a, a, a};
        Double[] avgDirectionsVelocitiesSpeed = {0.0, 0.0, 0.0, 0.0, 0.0};
        if (state.equals("data")) {
            PointableList pointables = hand.pointables();
            for (Pointable p : pointables) {
                Finger finger = new Finger(p);
                switch (finger.type()) {
                    case TYPE_THUMB:
                        sumFingersDirVelo[0] = sumFingersDirVelo[0].plus(p.tipVelocity());
                        break;
                    case TYPE_INDEX:
                        sumFingersDirVelo[1] = sumFingersDirVelo[1].plus(p.tipVelocity());
                        break;
                    case TYPE_MIDDLE:
                        sumFingersDirVelo[2] = sumFingersDirVelo[2].plus(p.tipVelocity());
                        break;
                    case TYPE_RING:
                        sumFingersDirVelo[3] = sumFingersDirVelo[3].plus(p.tipVelocity());
                        break;
                    case TYPE_PINKY:
                        sumFingersDirVelo[4] = sumFingersDirVelo[4].plus(p.tipVelocity());
                        break;
                }
            }
        } else if (state.equals("average")) {
            for (int i = 0; i < 5; i++) {
                avgDirectionsVelocities[i] = sumFingersDirVelo[i].divide(totalFrames.intValue());
                avgDirectionsVelocitiesSpeed[i] = Math.sqrt(Math.pow(avgDirectionsVelocities[i].getX(), 2) + (Math.pow(avgDirectionsVelocities[i].getY(), 2))
                        + (Math.pow(avgDirectionsVelocities[i].getZ(), 2)));
            }
        }
        return avgDirectionsVelocitiesSpeed;
    }

    public Double[] averageMovementDirection(Controller controller, Frame frame, Hand handCurrent) {
        Frame previous = controller.frame(1); //The previous frame
        HandList handsPrevious = previous.hands();
        Hand handPrevious;
        Double[] racios = {0.0, 0.0, 0.0, 0.0};
        if (state.equals("data")) {
            for (Hand hand : handsPrevious) {
                if (hand.isRight()) {
                    handPrevious = hand;
                    if (handPrevious.isValid()) {
                        float positionXcurrent = handCurrent.palmPosition().getX();
                        float positionXprevious = handPrevious.palmPosition().getX();
                        float positionZcurrent = handCurrent.palmPosition().getZ();
                        float positionZprevious = handPrevious.palmPosition().getZ();
                        if (positionXcurrent < positionXprevious) {    //left
                            sumMovDir[0] += 1;
                        }
                        if (positionXcurrent > positionXprevious) {    //right
                            sumMovDir[1] += 1;
                        }
                        if (positionZcurrent > positionZprevious) {    //up
                            sumMovDir[2] += 1;
                        }
                        if (positionZcurrent < positionZprevious) {    //down
                            sumMovDir[3] += 1;
                        }
                    }
                }
            }
        } else if (state.equals("average")) {
            for (int i = 0; i < 4; i++) {
                racios[i] = sumMovDir[i] / totalFrames;
            }
        }
        return racios;
    }
}
