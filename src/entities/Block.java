package entities;

import org.newdawn.slick.Graphics;

public class Block extends MoveableEntity 
{
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
		g.fillRect(x, y, 20, 20);
	}
}
