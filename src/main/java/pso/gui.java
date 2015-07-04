package pso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.TimerTask;

/**
 * Created by Siddharth on 01-07-2015.
 */
public class gui extends Thread {
    public static JFrame frame;
    public JPanel panel1;
    public JTextField TextField;
    public JTextArea textArea1;
    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JSpinner spinner1;
    public JLabel label;
    public static String equation;
    public void run() {
    }

    public gui() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //initializing swarm
                    swarm.pso((Integer) spinner1.getValue());
                    //parsing eqn
                    equation=new parser().parseEqn(TextField.getText());
                    //getting variable name
                    for (int i = 65; i <= 122; i++) {
                        if ((i > 64 && i < 91) || (i > 96 && i < 123)) {
                            if (equation.contains(" " + (char) i + " ")) {
                                swarm.variable.add((char)i);
                            }
                        }
                    }
                    swarm.moveit();
                    swarm.stop = false;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                java.util.Timer t = new java.util.Timer();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        label.setText("Minimum Value " + String.format("%.10f", particle.gbest));
                        textArea1.setText("");
                        for (int i = 0; i < swarm.noOfDimensions; i++) {
                            textArea1.append("\n Dimension --> " + swarm.variable.get(i) + "\n Value -->" + String.format("%.10f", particle.gX.get(i)) + "\n");
                        }
                    }
                }, 0, 500);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                swarm.stop = true;
            }
        });
        button3.addActionListener(new ActionListener() {
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
        TextField.addMouseMotionListener(new MouseMotionAdapter() {
        });
    }

    public static void main(String[] args) throws Exception {
        //initialize JFrame

        frame = new JFrame("Particle Swarm Optimizer");
        frame.setContentPane(new gui().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 480);
        frame.setLocation(300, 250);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}