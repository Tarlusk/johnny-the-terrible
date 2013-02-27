package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Block extends MoveableEntity 
{
	private int state = 0;
	
	public Block()
	{
		super();
	}
	public Block(float x, float y)
	{
		super(x,y);
	}
	public Block(float x, float y, float dx, float dy)
	{
		super(x,y);
		this.dx = dx;
		this.dy = dy;
	}
	
	@Override
	public void draw(Graphics g) {
		if (state == 1)
		{
			g.setColor(new Color(255,0,0));
			g.fillRect(x, y, 20, 20);
			g.setColor(new Color(255,255,255));
		}
		else
		{
			g.fillRect(x, y, 20, 20);
		}
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}
