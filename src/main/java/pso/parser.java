package pso;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Siddharth on 04-07-2015.
 */
public class parser {
    static ScriptEngineManager mgr = new ScriptEngineManager();
    static ScriptEngine engine = mgr.getEngineByName("JavaScript");

    public String parseEqn(String temp) {
        String tmp = "";
        try {

            tmp = temp.replace("sin", "Math.sin").
                    replace("cos", "Math.cos").
                    replace("tan", "Math.tan").
                    replace("sqrt", "Math.sqrt").
                    replace("sqr", "Math.pow").
                    replace("log", "Math.log").
                    replace("pow", "Math.pow").
                    replace("exp", "Math.exp");

            //System.out.print(tmp);
        } catch (Exception e) {
        }
        return tmp;
    }

    public static double getFitness(String temp, particle p) {
        int d = 0;
        for (int i = 0; i < swarm.variable.size(); i++) {
            if (temp.contains(" " + swarm.variable.get(i) + " ")) {
                temp = temp.replace(" " + swarm.variable.get(i) + " ", " " + String.valueOf(p.X.get(d)) + " ");
                d++;
            }
        }
        //System.out.println(temp);
        try {
            double t = (Double) engine.eval(temp);
             //System.out.println("@"+t);
            return Math.abs(t);
        } catch (ScriptException e) {
            e.printStackTrace();
            System.exit(5);
            return 0.0;
        }
    }
}
