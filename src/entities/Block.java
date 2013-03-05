package entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import collision.Box;

public class Block extends MoveableEntity 
{
	private int state = 0;
	public float width;
	public float height;
	public Box box;
	public Image img;
	public int direction;
	
	public Block()
	{
		super();
		width = 10;
		height = 10;
		box = new Box(x, y, width, height);
		try {
			img = new Image("res/borg2.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Block(float x, float y, float w, float h, String file)
	{
		super(x,y);
		this.width = w;
		this.height = h;
		box = new Box(x, y, width, height);
		try {
			img = new Image(file);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Block(float x, float y, float w, float h, float dx, float dy)
	{
		super(x,y);
		this.width = w;
		this.height = h;
		this.dx = dx;
		this.dy = dy;
		box = new Box(x, y, width, height);
		try {
			img = new Image("res/borg2.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x- 12, y -12, x+138, y+127, 0, 0, 150, 139);
		/*if (state == 1)
		{
			g.setColor(new Color(255,0,0));
			g.fillRect(x, y, width, height);
			g.setColor(new Color(255,255,255));
		}
		else
		{
			g.fillRect(x, y, width, height);
		}*/
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	@Override
	public void update(int delta) 
	{
		if (direction == 1)
		{
			dx = .4f;
		}
		else if (direction == 2)
		{
			dx = -.4f;
		}
		else 
		{
			dx = 0;
		}
		x += delta * dx;
		y += delta * dy;
		
		box.x = x;
		box.y = y;
	}
}