package entities;

import org.newdawn.slick.Graphics;

public abstract class Entity 
{
	protected float x;
	protected float y;
	
	public Entity()
	{
		x = 0;
		y = 0;
	}
	
	public Entity( float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public abstract void draw(Graphics g);
	
	public abstract void update(int delta);
	
	public void setX( float x)
	{
		this.x = x;
	}
	public void setY( float y)
	{
		this.y = y;
	}
	public float getX()
	{
		return x;
	}
	public float getY()
	{
		return y;
	}
}
