package byog.Core.Idle;

import java.io.Serializable;

import byog.Core.Game;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class UI implements Serializable {
   /* public static void drawMainMenu(){
        StdDraw.setCanvasSize(Data.WIDTH * 16, Data.HEIGHT * 16);
        StdDraw.setXscale(0, Data.WIDTH);
        StdDraw.setYscale(0, Data.HEIGHT);
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 60));
        StdDraw.setPenColor(Color.WHITE);

        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.PRINCETON_ORANGE);
        StdDraw.text(Data.WIDTH / 2, Data.HEIGHT * 0.7, "Escaping B-Street");
        StdDraw.show();

        StdDraw.setFont( new Font("Times New Roman", Font.BOLD, 20));
        StdDraw.text(Data.WIDTH / 2, Data.HEIGHT * 0.4, "New Game (N)");
        StdDraw.text(Data.WIDTH / 2, Data.HEIGHT * 0.35, "Load Game (L)");
        StdDraw.text(Data.WIDTH / 2, Data.HEIGHT * 0.3, "Quit (Q)");


        StdDraw.show();
    }

    public static long askForSeed() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.picture(Data.WIDTH / 2,Data.HEIGHT / 2,"byog/image/truc1m.jpeg");
        StdDraw.show();
        StdDraw.text(Data.WIDTH / 2, Data.HEIGHT * 0.6,
                " Please enter code to be in a place in B-Street, or press r to be in a random seed. "
                        + " Press s to confirm: ");
        StdDraw.show();
        char c = Game.waitCommand();
        long seed = 0;
        while (c != 's') {
            if (c == 'r') {
                seed = System.currentTimeMillis();
                break;
            }
            if (Character.isDigit(c)) {
                StdDraw.clear(StdDraw.DARK_GRAY);
                *//* *//*
                StdDraw.picture(Data.WIDTH / 2,Data.HEIGHT / 2,"byog/image/truc1m.jpeg");
                StdDraw.show();
                *//* *//*
                StdDraw.text(Data.WIDTH / 2, Data.HEIGHT * 0.6,
                        " Please enter code to be in a place in B-Street, or press r to be in a random seed. "
                                + " Press s to confirm: ");
                StdDraw.show();
                seed = 10 * seed + Long.parseLong("" + c);
                StdDraw.text(Data.WIDTH / 2, Data.HEIGHT * 0.5, "Seed: " + seed);
                StdDraw.show();
            } else {
                StdDraw.clear(StdDraw.DARK_GRAY);
                *//* *//*
                StdDraw.picture(Data.WIDTH / 2,Data.HEIGHT / 2,"byog/image/truc1m.jpeg");
                StdDraw.show();
                *//* *//*
                StdDraw.text(Data.WIDTH / 2, Data.HEIGHT * 0.6,
                        " Please enter code to be in a place in B-Street, or press r to be in a random seed. "
                                + " Press s to confirm: ");
                StdDraw.show();
                StdDraw.text(Data.WIDTH / 2, Data.HEIGHT * 0.5, "Seed: " + seed);
                StdDraw.text(Data.WIDTH / 2, Data.HEIGHT * 0.4, "Numbers only pls");
                StdDraw.show();
            }
            c = Game.waitCommand();
        }
        return seed;
    }*/
}
