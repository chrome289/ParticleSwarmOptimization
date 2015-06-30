package pso;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Siddharth on 30-06-2015.
 */
public class swarm extends JPanel {
    public static int swarmSize = 200;
    public static particle[] theSwarm = new particle[swarmSize];
    public static int dx = 1000, dy = 400;

    public swarm() {
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < swarmSize; i++) {
            g2.setStroke(new BasicStroke(3));
            g2.drawLine(theSwarm[i].x, theSwarm[i].y, theSwarm[i].x, theSwarm[i].y);

        }
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(dx, dy, dx, dy);
    }

    public static void main(String[] args) throws InterruptedException {

        //initialize JFrame
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new swarm());
        f.setTitle("Particle Swarm Optimization");
        f.setSize(1366, 768);
        f.setLocation(0, 0);
        f.setVisible(true);

        initialize(theSwarm);
        f.paint(f.getGraphics());
        int a = 5000;
        while (a != 0) {
            a--;
            if (a % 70 == 0) {
                dx = (int) (Math.random() * 700);
                dy = (int) (Math.random() * 400);
                particle.gbest = 100000000;
                particle.gx = 0;
                particle.gy = 0;
                particle p = new particle();
                theSwarm = reinitialize(theSwarm);

            }
            for (int j = 0; j < swarmSize; j++) {
                int temp = particle.getFitness(theSwarm[j]);
                //System.out.println(temp);
                if (temp < theSwarm[j].pbest) {
                    theSwarm[j].pbest = temp;
                    theSwarm[j].px = theSwarm[j].x;
                    theSwarm[j].py = theSwarm[j].y;
                }
            }
            for (int j = 0; j < swarmSize; j++) {
                int temp = theSwarm[j].pbest;
                if (temp < particle.gbest) {
                    particle.gbest = temp;
                    particle.gx = theSwarm[j].x;
                    particle.gy = theSwarm[j].y;
                }
            }
            for (int j = 0; j < swarmSize; j++) {
                theSwarm[j].move();
            }
            f.paint(f.getGraphics());
            Thread.sleep(100);
        }
    }

    private static void initialize(particle[] theSwarm) {
        for (int i = 0; i < swarmSize; i++) {
            particle p = new particle();
            p.initialize();
            theSwarm[i] = p;
        }
    }

    public static particle[] reinitialize(particle p[]) {
        particle[] ne = new particle[swarmSize];
        initialize(ne);
        for (int j = 0; j < swarm.swarmSize; j++) {
            ne[j].x = p[j].x;
            ne[j].y = p[j].y;
        }
        return ne;
    }
}
