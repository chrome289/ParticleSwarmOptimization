package pso;

import java.awt.geom.Line2D;

/**
 * Created by Siddharth on 30-06-2015.
 */
public class particle {
    public int pbest = 1000000000;
    public static int gbest = 1000000000;
    public int vx = 0, vy = 0,vz=0;
    public int x = 0, y = 0,z=0;
    public static int gx = 0, gy = 0,gz=0;
    public int px = 100000, py = 100000,pz=100000;
    public static int vmaxX = 50, vmaxY = 50,vmaxZ=50;

    public void initialize() {
        x = (int) (Math.random() * 50);
        y = (int) (Math.random() * 50);
        z = (int) (Math.random() * 50);
    }


    public void move() {
        int x2, y2,z2;
        if (Math.random() > 0.0) {
            double w = 0.809844, c1 = 1.4, c2 = 2.4;
            vx = (int) (w * vx + c1 * Math.random() * (px - x) + c2 * Math.random() * (gx - x));
            if (vx > vmaxX)
                vx = vmaxX;
            x2 = x + vx;
            vy = (int) (w * vy + c1 * Math.random() * (py - y) + c2 * Math.random() * (gy - y));
            if (vy > vmaxY)
                vy = vmaxY;
            y2 = y + vy;
            vz = (int) (w * vz + c1 * Math.random() * (pz - z) + c2 * Math.random() * (gz - z));
            if (vz > vmaxZ)
                vz = vmaxZ;
            z2 = z + vz;
        } else {
            double w = 0.609844, c1 = 1.4, c2 = 0;
            vx = (int) (w * vx + c1 * Math.random() * (px - x) + c2 * Math.random() * (gx - x));
            if (vx > vmaxX)
                vx = vmaxX;
            x2 = x + vx;
            vy = (int) (w * vy + c1 * Math.random() * (py - y) + c2 * Math.random() * (gy - y));
            if (vy > vmaxY)
                vy = vmaxY;
            y2 = y + vy;
            vz = (int) (w * vz + c1 * Math.random() * (pz - z) + c2 * Math.random() * (gz - z));
            if (vz > vmaxZ)
                vz = vmaxZ;
            z2 = z + vz;
        }
        boolean intersect = true;
        while (intersect) {
            intersect = Line2D.linesIntersect(x, y, x2, y2, 50000, 10000, 20000, 40000);
            if (intersect) {
                System.out.println(x2 + " " + y2);
                double m = (400 - 100) / (200 - 500);
                //m=-1/m;
                double c = 500 - (m * 200);
                double d = (x2 + (y2 - c) * m) / (1 + Math.pow(m, 2));
                x2 = (int) (((2 * d) - x2)+(100*(Math.random()- Math.random())));
                y2 = (int) (((2 * d * m) - y2 + (2 * c))+(200*(Math.random()- Math.random())));
                //System.out.println(x + " " + y);
                //System.out.println(c + " " + d);
            }
            x = x2;
            y = y2;
            z=z2;
        }
    }

    public static int getFitness(particle p) {
        int t = Math.abs((20000000-(p.x*p.y*p.z)));
        return t;
    }
}