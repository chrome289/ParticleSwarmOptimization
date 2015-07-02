package pso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Siddharth on 01-07-2015.
 */
public class gui extends JPanel {
    public static JFrame f;

    public gui() {
        JButton bt1 = new JButton();
        JButton bt2 = new JButton();
        JButton bt3 = new JButton();
        bt1.setText("STOP");
        bt1.setLocation(550, 650);
        bt1.setVisible(true);
        bt1.setSize(80, 30);
        bt1.setMaximumSize(new Dimension(150, 50));
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swarm.stop = true;
            }
        });

        bt2.setText("RESUME");
        bt2.setLocation(650, 650);
        bt2.setVisible(true);
        bt2.setSize(100, 30);
        bt2.setMaximumSize(new Dimension(150, 50));
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    swarm.moveit();
                    swarm.stop = false;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        f.add(bt1);
        f.add(bt2);

        bt3.setText("START");
        bt3.setLocation(770, 650);
        bt3.setVisible(true);
        bt3.setSize(80, 30);
        bt3.setMaximumSize(new Dimension(150, 50));
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    swarm.reinitialize(swarm.theSwarm);
                    swarm.moveit();
                    swarm.stop = false;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        });
        f.add(bt1);
        f.add(bt2);
        f.add(bt3);
    }

    protected void paintComponent(Graphics g) throws NullPointerException {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.drawString("Current   Best   fitness    -- >   " + String.format("%.9f", particle.gbest), 600, 20);
        String temp="X   -- >   " + String.format("%.9f", particle.gX) + "         Y   -- >   " + String.format("%.9f", particle.gY)+ "         Z   -- >   " +String.format("%.9f", particle.gZ);
        g2.drawString(temp, 550, 50);
        for (int i = 0; i < swarm.swarmSize; i++) {
            g2.setStroke(new BasicStroke(1));
            g2.drawLine((int)swarm.theSwarm[i].X, (int)swarm.theSwarm[i].Y, (int)swarm.theSwarm[i].X,(int) swarm.theSwarm[i].Y);
        }
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(5));
        g2.drawLine((int)particle.gX,(int) particle.gY,(int) particle.gX, (int)particle.gY);
    }

    public static void main(String[] args) throws InterruptedException {
        //initialize JFrame
        f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new gui());
        f.setTitle("Particle Swarm Optimization");
        f.setSize(1366, 768);
        f.setLocation(0, 0);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        swarm.pso();
        f.setVisible(true);
    }
}
