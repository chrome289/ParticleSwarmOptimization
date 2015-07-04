package pso;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Siddharth on 30-06-2015.
 */
public class swarm extends Thread {
    public static int noOfDimensions;
    public static int swarmSize = 2000;
    public static particle[] theSwarm = new particle[swarmSize];
    public static boolean stop = false;
    public static ArrayList<Character>variable=new ArrayList<Character>();

    public swarm() {
    }

    public static void pso(int n) throws InterruptedException {
        noOfDimensions = n;
        for (int i = 0; i < noOfDimensions; i++) {
            JSpinner spinner2 = new JSpinner();
            spinner2.setValue(new Double(5.0));
            double option = JOptionPane.showOptionDialog(null, spinner2, "Enter Max for Dimension " + (i + 1), JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (option == JOptionPane.OK_OPTION) {
                particle.pmaxX.add(Double.parseDouble(String.valueOf(spinner2.getValue())));
            }
            spinner2.setValue(new Double(-5.0));
            option = JOptionPane.showOptionDialog(null, spinner2, "Enter Min for Dimension " + (i + 1), JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (option == JOptionPane.OK_OPTION) {
                particle.pminX.add(Double.parseDouble(String.valueOf(spinner2.getValue())));
            }
            particle.gX.add(0.0);
            particle.vmaxX.add(1.0);
        }
        initialize(theSwarm);
    }

    public void run() {
        try {
            //theSwarm = reinitialize(theSwarm);
            while (!stop) {
                for (int j = 0; j < swarmSize; j++) {
                    double temp = parser.getFitness(gui.equation,theSwarm[j]);
                     System.out.println(particle.gbest);
                    if (temp < theSwarm[j].pbest) {
                        theSwarm[j].pbest = temp;
                        for (int i = 0; i < noOfDimensions; i++)
                            theSwarm[j].pX.set(i, theSwarm[j].X.get(i));
                    }
                }
                for (int j = 0; j < swarmSize; j++) {
                    double temp = theSwarm[j].pbest;
                    if (temp < particle.gbest) {
                        particle.gbest = temp;
                        for (int i = 0; i < noOfDimensions; i++)
                            particle.gX.set(i, theSwarm[j].X.get(i));
                    }
                }
                for (int j = 0; j < swarmSize; j++) {
                    theSwarm[j].move();
                }
                System.out.println(particle.gbest);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void moveit() throws InterruptedException {
        swarm t1 = new swarm();
        t1.setName("thread 1");
        t1.setPriority(10);
        Thread u1 = new Thread(t1);
        u1.start();

    }

    private static void initialize(particle[] theSwarm) {
        for (int i = 0; i < swarmSize; i++) {
            particle p = new particle();
            p.initialize();
            theSwarm[i] = p;
        }
    }

    public static particle[] reinitialize(particle p[]) {
        particle.gbest = 1000000000.0;
        for (int i = 0; i < noOfDimensions; i++)
            particle.gX.set(i, 0.0);
        particle[] ne = new particle[swarmSize];
        initialize(ne);
        for (int j = 0; j < swarm.swarmSize; j++) {
            for (int i = 0; i < noOfDimensions; i++) {
                ne[j].X.set(i, p[j].X.get(i) + Math.random() * particle.pmaxX.get(i));
                ne[j].vX.set(i, 0.0);
                ne[j].pX.set(i, 100000.0);
            }
            ne[j].pbest = 10000000000.0;
        }
        return ne;
    }

}
