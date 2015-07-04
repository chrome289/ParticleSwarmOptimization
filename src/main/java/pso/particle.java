package pso;

import java.util.ArrayList;

/**
 * Created by Siddharth on 30-06-2015.
 */
public class particle {
    public double pbest = 1000000000;
    public ArrayList<Double> vX =new ArrayList<Double>();
    public ArrayList<Double> X =new ArrayList<Double>();
    public ArrayList<Double> pX =new ArrayList<Double>();
    public static ArrayList<Double> gX =new ArrayList<Double>();
    public static ArrayList<Double> vmaxX =new ArrayList<Double>();
    public static ArrayList<Double> pmaxX  =new ArrayList<Double>();
    public static ArrayList<Double> pminX  =new ArrayList<Double>();
    public static double gbest = 1000000000;


    //Enter constraints before optimizing function


    public void initialize() {
        for(int i=0;i<swarm.noOfDimensions;i++){
            X.add(Math.random()*pmaxX.get(i));
            vX.add(0.0);
            pX.add(100000.0);
        }
    }


    public void move() {
        if (Math.random() > 0.5) {
            //normal swarm behaviour
            double w = 0.509844, c1 = 2, c2 = 2;
            for (int i=0;i<swarm.noOfDimensions;i++) {
                vX.set(i, w * vX.get(i) + c1 * Math.random() * (pX.get(i) - X.get(i)) + c2 * Math.random() * (gX.get(i) - X.get(i)));
                if (vX.get(i) > vmaxX.get(i))
                    vX.set(i, vmaxX.get(i));
                X.set(i, X.get(i) + vX.get(i));
                if (X.get(i) > pmaxX.get(i))
                    X.set(i, pmaxX.get(i) * Math.random());
                if (X.get(i) < pminX.get(i))
                    X.set(i, pminX.get(i) + Math.abs(pminX.get(i) * Math.random()));
            }
        } else {
            //stray particles(distracted)
            double w = 0.909844, c1 = 2.4, c2 = 0;
            for (int i=0;i<swarm.noOfDimensions;i++) {
                vX.set(i, w * vX.get(i) + c1 * Math.random() * (pX.get(i) - X.get(i)) + c2 * Math.random() * (gX.get(i) - X.get(i)));
                if (vX.get(i) > vmaxX.get(i))
                    vX.set(i, vmaxX.get(i));
                X.set(i, X.get(i) + vX.get(i));
                if (X.get(i) > pmaxX.get(i))
                    X.set(i, pmaxX.get(i) * Math.random());
                if (X.get(i) < pminX.get(i))
                    X.set(i, pminX.get(i) + Math.abs(pminX.get(i) * Math.random()));
            }
        }
    }

    /*public static double getFitness(particle p) {
        //ackley function           //Damn you Java and your floating point arithmetic
        /*double sumX=Math.pow(p.X,2)+Math.pow(p.Y,2)+Math.pow(p.Z,2);
        double cosX=Math.cos(2*Math.PI*p.X)+Math.cos(2*Math.PI*p.Y)+Math.cos(2*Math.PI*p.Z);
        double t=(-20*Math.exp(-0.2*Math.sqrt((0.3333333333)*sumX)))-(Math.exp((0.3333333333)*cosX))+Math.exp(1)+20;
        System.out.println("@"+t);
        return t;*/

        //sphere function
        /*double t = Math.abs((Math.pow(p.X, 2)) + (Math.pow(p.Y, 2)) + (Math.pow(p.Z, 2)));
        System.out.println("@"+t);
        return t;*/

        //Rosenbrock function
        /*double t=(100*(Math.pow(p.X.get(1)-Math.pow(p.X.get(0),2),2)))+Math.pow(p.X.get(0)-1,2);
        t=t+(100*(Math.pow(p.X.get(2)-Math.pow(p.X.get(1),2),2)))+Math.pow(p.X.get(1)-1,2);
        //System.out.println("@"+t);
        return t;*/

        //Box Betts function
        /*double t = 0.0;
        for (int i = 0; i < 3; i++) {
            double temp = Math.exp(-0.1 * (i + 1) * p.X) - Math.exp(-0.1 * (i + 1) * p.Y) - ((Math.exp(-0.1 * (i + 1)) - Math.exp(-(i + 1))) * p.Z);
            temp = Math.pow(temp, 2);
            t += temp;
        }
        return t;*/

        //Styblinski–Tang function
        /*double temp1 = Math.pow(p.X, 4) - (16 * Math.pow(p.X, 2)) + (5 * p.X);
        double temp2 = Math.pow(p.Y, 4) - (16 * Math.pow(p.Y, 2)) + (5 * p.Y);
        double temp3 = Math.pow(p.Z, 4) - (16 * Math.pow(p.Z, 2)) + (5 * p.Z);
        double t = temp1 + temp2 + temp3;
        t = t * 0.5;
        return t;
        return new parser().fitness(gui.equation);
    }*/
}