package graphics;

import gov.noaa.pmel.sgt.dm.Collection;
import models.Circle;
import utilities.SortCirclesComparator;
import utilities.Utilities;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class GameGraphics extends JPanel
{
    private ArrayList<Circle> circles;

    private final static String FIREWORK_1 = "src/graphics/gifs/firework1.gif";
    private final static String FIREWORK_2 = "src/graphics/gifs/firework2.gif";
    private final static int MAX_SIZE = 400;
    private final static int MIN_SIZE = 300;
    private final static int PADDING_LEFT = 20;

    public GameGraphics()
    {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(1000, 1000));
        circles = new ArrayList<>();
        //circles.add(new Circle(100,100,100));
    }

    public void setCircles(ArrayList<Circle> circles)
    {
        this.circles = circles;
        Collections.sort(this.circles, new SortCirclesComparator());
        repaint();
//        Graphics graphics = this.getGraphics();
//        for (Circle circle : this.circles)
//        {
//            graphics.setColor(Color.BLACK);
//            graphics.drawString(circle.getTeam(),circle.getX(),circle.getY());
//            graphics.setColor(circle.getColor());
//            graphics.drawOval(circle.getX(), circle.getY(), circle.getWidth(), circle.getHeight());
//        }
    }

    public void setWinner(ArrayList<Circle> circles, String str)
    {
        //this.circles = Arrays.asList(circle);
        this.circles = circles;
        this.repaint();
        Graphics graphics = this.getGraphics();
//        graphics.setColor(circles.get(0).getColor());
//        graphics.drawOval(circles.get(0).getX(), circles.get(0).getY(), circles.get(0).getWidth(), circles.get(0).getHeight());
//        graphics.drawString(circles.get(0).getTeam(),circles.get(0).getX(),circles.get(0).getY());
        Image picture = Toolkit.getDefaultToolkit().getImage(GameGraphics.FIREWORK_1);
        graphics.drawImage(picture,Utilities.getRandom().nextInt(this.getWidth()), Utilities.getRandom().nextInt(this.getHeight()),
                Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE, Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE,
                this);
        URL url;
        try
        {
            url = new File(GameGraphics.FIREWORK_1).toURI().toURL();
            Icon icon = new ImageIcon(url);
            JLabel label = new JLabel(icon);
            label.setBounds(Utilities.getRandom().nextInt(this.getWidth()), Utilities.getRandom().nextInt(this.getHeight()),
                    Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE, Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE);
            this.add(label);
            label = new JLabel(icon);
            label.setBounds(Utilities.getRandom().nextInt(this.getWidth()), Utilities.getRandom().nextInt(this.getHeight()),
                    Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE, Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE);
            this.add(label);
            label = new JLabel(icon);
            label.setBounds(Utilities.getRandom().nextInt(this.getWidth()), Utilities.getRandom().nextInt(this.getHeight()),
                    Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE, Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE);
            this.add(label);
            url = new File(GameGraphics.FIREWORK_2).toURI().toURL();
            icon = new ImageIcon(url);
            label = new JLabel(icon);
            label.setBounds(Utilities.getRandom().nextInt(this.getWidth()), Utilities.getRandom().nextInt(this.getHeight()),
                    Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE, Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE);
            this.add(label);
            label = new JLabel(icon);
            label.setBounds(Utilities.getRandom().nextInt(this.getWidth()), Utilities.getRandom().nextInt(this.getHeight()),
                    Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE, Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE);
            this.add(label);
            label = new JLabel(icon);
            label.setBounds(Utilities.getRandom().nextInt(this.getWidth()), Utilities.getRandom().nextInt(this.getHeight()),
                    Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE, Utilities.getRandom().nextInt(GameGraphics.MAX_SIZE) + GameGraphics.MIN_SIZE);
            this.add(label);
            label = new JLabel(str);
            int padding_top = this.getHeight() / 4;
            label.setBounds(GameGraphics.PADDING_LEFT, (this.getHeight() / 2) - padding_top, this.getWidth(), this.getHeight() / 2);
            label.setForeground(circles.get(0).getColor());
            label.setFont(new Font("Serif", Font.BOLD, 24));
            this.add(label);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (Circle s : circles)
        {
            s.draw(g);
        }
    }
}
