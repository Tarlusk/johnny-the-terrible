package main;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MenuButton 
{
	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private int status = UNSELECTED;
	
	private static final int UNSELECTED = 0;
	private static final int HOVER = 1;
	private static final int CLICKED = 2;
	Image image;
	
	public void setUnselected()
	{
		status = UNSELECTED;
	}
	public void setHover()
	{
		status = HOVER;
	}
	public void setClicked()
	{
		status = CLICKED;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void draw(Graphics g)
	{
		if (status == UNSELECTED)
		{
			g.drawImage(image, x, y, x +width, y +height, 0, 0, 200, 100);
		}
		else if (status == HOVER)
		{
			g.drawImage(image, x, y, x +width, y +height, 0, 0, 200, 100);
			g.setColor(new Color(0, 0, 255, 100));
			g.fillRect(x, y, width, height);
			g.setColor(new Color(255, 255, 255));
		}
		else if (status == CLICKED)
		{
			g.setColor(new Color(255, 0, 0));
			g.fillRect(x, y, width, height);
			g.setColor(new Color(255, 255, 255));
		}
	}
	public MenuButton(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		try {
			image = new Image("res/menu/setup.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
