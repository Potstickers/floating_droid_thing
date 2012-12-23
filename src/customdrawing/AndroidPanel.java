package customdrawing;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;

public class AndroidPanel extends JPanel implements ActionListener {

    private Timer idleTimer;
    private Timer blinkTimer;
    private Timer textTimer;
    private Boolean eyeState = true;
    private Boolean eatApple = false;
    private int colorToPaint = 3;
    private Color[] colors = {
        new Color(30, 144, 255), new Color(153, 50, 204), new Color(176, 23, 31), new Color(164, 240, 80)
    };
    private File file = new File("DroidLogo-Regular.ttf");
    private Font font = Font.createFont(Font.TRUETYPE_FONT, file);
    private Font fontToUse = font.deriveFont(Font.PLAIN, 36);
    //the shapes that manipulation is done to
    private GeneralPath leftArm = new GeneralPath();
    private GeneralPath rightArm = new GeneralPath();
    private Rectangle body = new Rectangle(245, 525, 60, 50);
    private Arc2D.Float head = new Arc2D.Float(245, 495, 60, 50, 0, 180, Arc2D.CHORD);
    private Ellipse2D[] eyesOpen = new Ellipse2D[2];
    private Line2D[] eyesClosed = new Line2D[2];
    private Line2D[] antenna = new Line2D[2];
    private GeneralPath leftLeg = new GeneralPath();
    private GeneralPath rightLeg = new GeneralPath();
    private GeneralPath ground = new GeneralPath();
    //make Shape references
    private Shape toDrawLA;
    private Shape toDrawRA;
    private Shape toDrawBd;
    private Shape toDrawH;
    private Shape toDrawLL;
    private Shape toDrawRL;
    private Shape toDrawEO1;
    private Shape toDrawEO2;
    private Shape toDrawEC1;
    private Shape toDrawEC2;
    private Shape toDrawAt1;
    private Shape toDrawAt2;
    //init stars
    private Stars stars = new Stars(14);

    public AndroidPanel() throws FontFormatException, IOException {
        leftArm.moveTo(220, 530);//start x,y
        leftArm.quadTo(230, 520, 240, 530);//for top shoulder
        leftArm.lineTo(240, 560);
        leftArm.quadTo(230, 570, 220, 560); //bottom curve
        leftArm.closePath();
        rightArm.moveTo(310, 530);
        rightArm.quadTo(320, 520, 330, 530);
        rightArm.lineTo(330, 560);
        rightArm.quadTo(320, 570, 310, 560);
        rightArm.closePath();
        antenna[0] = new Line2D.Float(255, 500, 245, 485);
        antenna[1] = new Line2D.Float(295, 500, 305, 485);
        leftLeg.moveTo(250, 575);
        leftLeg.lineTo(270, 575);
        leftLeg.lineTo(270, 595);
        leftLeg.quadTo(260, 605, 250, 595);
        leftLeg.closePath();
        rightLeg.moveTo(body.getMaxX() - 25, 575);
        rightLeg.lineTo(body.getMaxX() - 5, 575);
        rightLeg.lineTo(body.getMaxX() - 5, 595);
        rightLeg.quadTo(body.getMaxX() - 15, 605, body.getMaxX() - 25, 595);
        eyesOpen[0] = new Ellipse2D.Float(260, 505, 6, 6);
        eyesOpen[1] = new Ellipse2D.Float(284, 505, 6, 6);
        eyesClosed[0] = new Line2D.Float(260, 511, 266, 511);
        eyesClosed[1] = new Line2D.Float(284, 511, 290, 511);
        ground.moveTo(0, 700);
        ground.quadTo(300, 600, 600, 700);
        ground.closePath();
        toDrawLA = leftArm;
        toDrawRA = rightArm;
        toDrawBd = body;
        toDrawH = head;
        toDrawLL = leftLeg;
        toDrawRL = rightLeg;
        toDrawEO1 = eyesOpen[0];
        toDrawEO2 = eyesOpen[1];
        toDrawEC1 = eyesClosed[0];
        toDrawEC2 = eyesClosed[1];
        toDrawAt1 = antenna[0];
        toDrawAt2 = antenna[1];
        idleTimer = new Timer(210, hover);
        idleTimer.start();
        blinkTimer = new Timer(100, blink);
        blinkTimer.start();
        textTimer = new Timer(3000, displayMsg);
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(new GradientPaint(300, 700, new Color(25, 25, 112), 300, 300, new Color(104, 34, 139)));
        g2d.fill(new Rectangle(0, 300, 600, 400));
        g2d.setPaint(new GradientPaint(300, 300, new Color(104, 34, 139), 300, 0, new Color(41, 41, 41)));
        g2d.fill(new Rectangle(0, 0, 600, 300));
        g2d.setColor(new Color(3, 191, 255));
        g2d.fill(new Ellipse2D.Float(300, 400, 600, 600));
        g2d.setColor(new Color(181, 255, 178));
        g2d.fill(new Ellipse2D.Float(530, 420, 60, 60));
        g2d.fill(new Ellipse2D.Float(400, 500, 120, 120));
        g2d.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.draw(new Ellipse2D.Float(300, 400, 600, 600));
        g2d.setPaint(new GradientPaint(300, 700, new Color(124, 255, 15), 300, 600, new Color(87, 221, 225)));
        g2d.fill(ground);
        stars.paintStars(g2d);
        g2d.setColor(colors[colorToPaint]);
        g2d.fill(toDrawLA);
        g2d.fill(toDrawRA);
        g2d.fill(toDrawBd);
        g2d.fill(toDrawH);
        g2d.fill(toDrawLL);
        g2d.fill(toDrawRL);
        g2d.setStroke(new BasicStroke(1.8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.draw(toDrawAt1);
        g2d.draw(toDrawAt2);
        g2d.setColor(Color.white);
        if (eyeState) {
            g2d.fill(toDrawEO1);
            g2d.fill(toDrawEO2);
        } else {
            g2d.draw(toDrawEC1);
            g2d.draw(toDrawEC2);
        }
        g2d.setColor(new Color(164, 230, 86));
        if (eatApple) {
            g2d.setFont(fontToUse);
            g2d.drawString("I NO UR TAMAGOTCHI!", 100, 400);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() instanceof JButton) {
            JButton jb = (JButton) ae.getSource();
            if (jb.getText().equals("Cycle Colors")) {
                if (colorToPaint == 3) {
                    colorToPaint = 0;
                } else {
                    colorToPaint++;
                }
            }
            if (jb.getText().equals("Eat Apple")) {
                eatApple = !eatApple;
                if (eatApple) {
                    textTimer.start();
                }
            }
        }
    }

    private void generalTransform(AffineTransform at) {
        toDrawLA = at.createTransformedShape(leftArm);
        toDrawRA = at.createTransformedShape(rightArm);
        toDrawBd = at.createTransformedShape(body);
        toDrawH = at.createTransformedShape(head);
        toDrawAt1 = at.createTransformedShape(antenna[0]);
        toDrawAt2 = at.createTransformedShape(antenna[1]);
        toDrawEC1 = at.createTransformedShape(eyesClosed[0]);
        toDrawEC2 = at.createTransformedShape(eyesClosed[1]);
        toDrawEO1 = at.createTransformedShape(eyesOpen[0]);
        toDrawEO2 = at.createTransformedShape(eyesOpen[1]);
        toDrawLL = at.createTransformedShape(leftLeg);
        toDrawRL = at.createTransformedShape(rightLeg);
        this.repaint();
    }
    private Action hover = new AbstractAction() {

        AffineTransform at = new AffineTransform();
        int i = 0;
        boolean dirChange = false;
        Random rand = new Random();

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!dirChange) {
                at.translate(0, -rand.nextInt(3));
                generalTransform(at);
                i++;
                if (i == 3) {
                    i = 0;
                    dirChange = !dirChange;
                }
            } else {
                at.translate(0, rand.nextInt(3));
                generalTransform(at);
                i++;
                if (i == 3) {
                    i = 0;
                    dirChange = !dirChange;
                }
            }
        }
    };
    private Action blink = new AbstractAction() {

        boolean blink = false;
        Random rand = new Random();

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (rand.nextInt(4) == 0) {
                blink = true;
            } else {
                blink = false;
            }
            if (blink) {
                eyeState = !eyeState;
                blink = !blink;
            } else {
                eyeState = true;
            }
        }
    };
    private Action displayMsg = new AbstractAction() {

        boolean disappear = false;

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (disappear) {
                textTimer.stop();
                eatApple = false;
            } else {
                disappear = true;
            }
        }
    };
}
