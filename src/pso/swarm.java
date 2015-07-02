package pso;

/**
 * Created by Siddharth on 30-06-2015.
 */
public class swarm extends Thread {

    public static int swarmSize = 100;
    public static particle[] theSwarm = new particle[swarmSize];
    //public static int dx = 1000, dy = 400;
    public static boolean gb = false, stop = false;

    public swarm() {
    }

    public static void pso() throws InterruptedException {
        initialize(theSwarm);
        gui.f.paint(gui.f.getGraphics());
    }

    public void run() {
        try {
            //theSwarm = reinitialize(theSwarm);
            while ( !stop) {
                for (int j = 0; j < swarmSize; j++) {
                    double temp = particle.getFitness(theSwarm[j]);
                    System.out.println(particle.gbest);
                    if (temp < theSwarm[j].pbest) {
                        theSwarm[j].pbest = temp;
                        theSwarm[j].pX = theSwarm[j].X;
                        theSwarm[j].pY = theSwarm[j].Y;
                        theSwarm[j].pZ = theSwarm[j].Z;
                    }
                }
                for (int j = 0; j < swarmSize; j++) {
                    double temp = theSwarm[j].pbest;
                    if (temp < particle.gbest) {
                        particle.gbest = temp;
                        particle.gX = theSwarm[j].X;
                        particle.gY = theSwarm[j].Y;
                        particle.gZ = theSwarm[j].Z;
                    }
                }
                for (int j = 0; j < swarmSize; j++) {
                    theSwarm[j].move();
                }
                gui.f.paint(gui.f.getGraphics());
                //Thread.sleep(200);
            }
            gui.f.paint(gui.f.getGraphics());
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
        particle.gX = 0.0;
        particle.gY = 0.0;
        particle.gZ = 0.0;
        particle[] ne = new particle[swarmSize];
        initialize(ne);
        for (int j = 0; j < swarm.swarmSize; j++) {
            ne[j].X = p[j].X + Math.random() * particle.pmaxX;
            ne[j].Y = p[j].Y + Math.random() * particle.pmaxY;
            ne[j].Z = p[j].Z + Math.random() * particle.pmaxZ;
            ne[j].vX = 0;
            ne[j].vY = 0;
            ne[j].vZ = 0;
            ne[j].pX = 100000;
            ne[j].pY = 100000;
            ne[j].pZ = 100000;
            ne[j].pbest=10000000000.0;
        }
        return ne;
    }
}
