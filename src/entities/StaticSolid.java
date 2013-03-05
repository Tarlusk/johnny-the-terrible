package entities;

import org.newdawn.slick.Graphics;

import collision.Box;
import collision.Primitive;

public class StaticSolid extends Entity 
{
	public Primitive box;

	public StaticSolid() {
		super();
		box = new Box(x, y, 100, 4);
	}

	public StaticSolid(float x, float y) {
		super(x, y);
		box = new Box(x, y, 100, 4);
	}

	@Override
	public void draw(Graphics g) 
	{
		g.drawRect(x, y, 100, 4);
	}

	@Override
	public void update(int delta) 
	{
		//nothing
	}
}
