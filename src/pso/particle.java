package pso;

/**
 * Created by Siddharth on 30-06-2015.
 */
public class particle {
    public double pbest = 1000000000;
    public static double gbest = 1000000000;
    public double vX = 0.0, vY = 0.0, vZ = 0.0;
    public double X = 0.0, Y = 0.0, Z = 0.0;
    public static double gX = 0.0, gY = 0.0, gZ = 0.0;
    public double pX = 100000.0, pY = 100000.0, pZ = 100000.0;
    public static double vmaxX = 1.0, vmaxY = 1.0, vmaxZ = 1.0;

    //Enter constraints before optimizing function
    public static double pmaxX = 5.0, pmaxY = 5.0, pmaxZ = 5.0;
    public static double pminX = -5.0, pminY = -5.0, pminZ = -5.0;

    public void initialize() {
        X = Math.random() * pmaxX;
        Y = Math.random() * pmaxY;
        Z = Math.random() * pmaxZ;
    }


    public void move() {
        if (Math.random() > 0.5) {
            //normal swarm behaviour
            double w = 0.509844, c1 = 2, c2 = 2;
            vX = w * vX + c1 * Math.random() * (pX - X) + c2 * Math.random() * (gX - X);
            if (vX > vmaxX)
                vX = vmaxX;
            X = X + vX;
            if (X > pmaxX)
                X = pmaxX * Math.random();
            if (X < pminX)
                X = pminX + Math.abs(pminX * Math.random());
            vY = w * vY + c1 * Math.random() * (pY - Y) + c2 * Math.random() * (gY - Y);
            if (vY > vmaxY)
                vY = vmaxY;
            Y = Y + vY;
            if (Y > pmaxY)
                Y = pmaxY * Math.random();
            if (Y < pminY)
                Y = pminY + Math.abs(pminY * Math.random());
            vZ = w * vZ + c1 * Math.random() * (pZ - Z) + c2 * Math.random() * (gZ - Z);
            if (vZ > vmaxZ)
                vZ = vmaxZ;
            Z = Z + vZ;
            if (Z > pmaxZ)
                Z = pmaxZ * Math.random();
            if (Z < pminZ)
                Z = pminZ + Math.abs(pminZ * Math.random());
        } else {
            //stray particles(distracted)
            double w = 0.909844, c1 = 2.4, c2 = 0;
            vX = w * vX + c1 * Math.random() * (pX - X) + c2 * Math.random() * (gX - X);
            if (vX > vmaxX)
                vX = vmaxX;
            X = X + vX;
            if (X > pmaxX)
                X = pmaxX * Math.random();
            if (X < pminX)
                X = pminX + Math.abs(pminX * Math.random());
            vY = w * vY + c1 * Math.random() * (pY - Y) + c2 * Math.random() * (gY - Y);
            if (vY > vmaxY)
                vY = vmaxY;
            Y = Y + vY;
            if (Y > pmaxY)
                Y = pmaxY * Math.random();
            if (Y < pminY)
                Y = pminY + Math.abs(pminY * Math.random());
            vZ = w * vZ + c1 * Math.random() * (pZ - Z) + c2 * Math.random() * (gZ - Z);
            if (vZ > vmaxZ)
                vZ = vmaxZ;
            Z = Z + vZ;
            if (Z > pmaxZ)
                Z = pmaxZ * Math.random();
            if (Z < pminZ)
                Z = pminZ + Math.abs(pminZ * Math.random());
        }
    }

    public static double getFitness(particle p) {
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
        /*double t=(100*(Math.pow(p.Y-Math.pow(p.X,2),2)))+Math.pow(p.X-1,2);
        t=t+(100*(Math.pow(p.Z-Math.pow(p.Y,2),2)))+Math.pow(p.Y-1,2);
        System.out.println("@"+t);
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
        double t;
        double temp1 = Math.pow(p.X, 4) - (16 * Math.pow(p.X, 2)) + (5 * p.X);
        double temp2 = Math.pow(p.Y, 4) - (16 * Math.pow(p.Y, 2)) + (5 * p.Y);
        double temp3 = Math.pow(p.Z, 4) - (16 * Math.pow(p.Z, 2)) + (5 * p.Z);
        t = temp1 + temp2 + temp3;
        t = t * 0.5;
        return t;
    }
}