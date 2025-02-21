package org.eci.arep.app;

import static spark.Spark.*;

public class SparkWebServer {

    public static void main(String... args){
        port(getPort());

        staticFiles.location("/public");

        get("hello", (req,res) -> "Hello Docker!");


        get("sin", (req,res) -> {
            String x = req.queryParams("x");
            return Math.sin(Double.parseDouble(x));
        });

        get("cos", (req,res) -> {
            String x = req.queryParams("x");
            return Math.cos(Double.parseDouble(x));
        });

        get("palindrome", (req,res) -> {
            String s = req.queryParams("x");
            for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
            }
            return true;
        });

        get("vector", (req,res) -> {
            String x = req.queryParams("x");
            String y = req.queryParams("y");
            int x1 = Integer.parseInt(x.split(",")[0]);
            int y1 = Integer.parseInt(x.split(",")[1]);

            int x2 = Integer.parseInt(y.split(",")[0]);
            int y2 = Integer.parseInt(y.split(",")[1]);

            int diff1 = x2-x1;
            int diff2 = y2-y1;
            return Math.sqrt((diff1*diff1) + (diff2*diff2));
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}