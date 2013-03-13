package entities;

import org.newdawn.slick.Graphics;

import collision.Circle;
import collision.Primitive;

public class Bullet extends MoveableEntity
{
	Primitive hitbox = new Circle(this.x, this.y, 10);
	
	public Bullet( float x, float y, float dx, float dy)
	{
		super(x,y,dx,dy);
	}
	@Override
	public void draw(Graphics g) {
		g.fillOval(x, y, 10, 10);
		
	}

}
