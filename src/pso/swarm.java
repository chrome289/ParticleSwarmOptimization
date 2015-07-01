package pso;

/**
 * Created by Siddharth on 30-06-2015.
 */
public class swarm extends Thread {

    public static int swarmSize = 10000;
    public static particle[] theSwarm = new particle[swarmSize];
    public static int dx = 1000, dy = 400;
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
            while (particle.gbest > 0 && !stop) {
                for (int j = 0; j < swarmSize; j++) {
                    int temp = particle.getFitness(theSwarm[j]);
                    //System.out.println(particle.gx + "   " + particle.gy);
                    if (temp < theSwarm[j].pbest) {
                        theSwarm[j].pbest = temp;
                        theSwarm[j].px = theSwarm[j].x;
                        theSwarm[j].py = theSwarm[j].y;
                        theSwarm[j].pz = theSwarm[j].z;
                    }
                }
                for (int j = 0; j < swarmSize; j++) {
                    int temp = theSwarm[j].pbest;
                    if (temp < particle.gbest) {
                        particle.gbest = temp;
                        particle.gx = theSwarm[j].x;
                        particle.gy = theSwarm[j].y;
                        particle.gz = theSwarm[j].z;
                    }
                }
                for (int j = 0; j < swarmSize; j++) {
                    theSwarm[j].move();
                }
                gui.f.paint(gui.f.getGraphics());
                Thread.sleep(200);
            }
            gui.f.paint(gui.f.getGraphics());
        } catch (InterruptedException e) {
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
        particle.gbest = 1000000000;
        particle.gx = 0;
        particle.gy = 0;
        particle.gz = 0;
        particle[] ne = new particle[swarmSize];
        initialize(ne);
        for (int j = 0; j < swarm.swarmSize; j++) {
            ne[j].x = (int) (p[j].x + Math.random() * 20);
            ne[j].y = (int) (p[j].y + Math.random() * 20);
            ne[j].z = (int) (p[j].z + Math.random() * 20);
            ne[j].vx = 0;
            ne[j].vy = 0;
            ne[j].vz = 0;
            ne[j].px = 100000;
            ne[j].py = 100000;
            ne[j].pz = 100000;
        }
        return ne;
    }
}
