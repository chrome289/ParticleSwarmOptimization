package pso;

/**
 * Created by Siddharth on 30-06-2015.
 */
public class particle {
    public int pbest = 100000000;
    public static int gbest = 100000000;
    public int vx = 0, vy = 0;
    public int x = 0, y = 0;
    public static int gx = 0, gy = 0;
    public int px = 10000, py = 10000;
    public static int vmaxX=100,vmaxY=100;

    public void initialize() {
        x = (int) (Math.random() * 50);
        y = (int) (Math.random() * 50);
    }



    public void move() {
        double w = 0.729844, c1 = 2.5, c2 = 1.5;
        vx = (int) (w * vx + c1 * Math.random() * (px - x) + c2 * Math.random() * (gx - x));
        if(vx>vmaxX)
            vx=vmaxX;
        x = x + vx;
        vy = (int) (w * vy + c1 * Math.random() * (py - y) + c2 * Math.random() * (gy - y));
        if(vy>vmaxY)
            vy=vmaxY;
        y = y + vy;
        System.out.println(swarm.dx+"     "+swarm.dy);
    }

    public static int getFitness(particle p) {
        int t=(int) Math.sqrt(Math.pow((swarm.dx - p.x), 2) + Math.pow((swarm.dy - p.y), 2));
        return t;
    }
}