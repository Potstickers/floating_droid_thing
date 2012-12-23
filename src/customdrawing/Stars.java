package customdrawing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.Random;

public class Stars {
    
    private Path2D[]stars;
    private Random rand = new Random();

    public Stars(int n) {
        stars = new Path2D[n];
        int x =0;
        int dx = 600/n;
        for (int i =0; i < n; i++){
            generateStar(i, x, rand.nextInt(301));
            x+=dx;
        }
    }

    private void generateStar(int i,int x,int y) {
        Path2D.Double cur = new Path2D.Double();
        cur.moveTo(x, y);
        cur.lineTo(cur.getCurrentPoint().getX() + 6, y - 2);
        cur.lineTo(cur.getCurrentPoint().getX() + 2, cur.getCurrentPoint().getY() - 6);
        cur.lineTo(cur.getCurrentPoint().getX() + 2, cur.getCurrentPoint().getY() + 6);
        cur.lineTo(cur.getCurrentPoint().getX() + 6, cur.getCurrentPoint().getY() + 2);
        cur.lineTo(cur.getCurrentPoint().getX() - 6, cur.getCurrentPoint().getY() + 2);
        cur.lineTo(cur.getCurrentPoint().getX() - 2, cur.getCurrentPoint().getY() + 6);
        cur.lineTo(cur.getCurrentPoint().getX() - 2, cur.getCurrentPoint().getY() - 6);
        cur.closePath();
        stars[i]=cur;
    }

    public void paintStars(Graphics2D g) {
        //super.paintComponent(g);
        g.setColor(new Color(246, 246, 255));
        for (int i = 0; i < stars.length; i++) {
            
            g.fill(stars[i]);
        }
    }
}
