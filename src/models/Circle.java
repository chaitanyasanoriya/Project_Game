package models;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle
{
    private int x, y, width, height;
    private Color color;
    private String team;

    public Circle(int x, int y, int width, Color color, String team)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = width;
        this.color = color;
        this.team = team;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public Color getColor()
    {
        return color;
    }

    public String getTeam()
    {
        return team;
    }

    public void draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x-20, y-20, width, height);
        g2d.setColor(color);
        g2d.fill(circle);
        g.setColor(Color.BLACK);
        g.drawString(team,x,y);
        //g.drawOval(x,y,width,height);
    }
}
