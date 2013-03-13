package collision;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import linear.Interval;
import linear.Vector2d;

public class Circle extends Primitive
{

	public float radius;
	Image img;
	
	public Circle(float x, float y, float radius) 
	{
		super(x, y);
		this.radius = radius;
		try {
			img = new Image ("res/earth2.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Interval proj(Vector2d vector) {
		float rsintheta = (radius * vector.y) / (float) Math.sqrt(vector.x*vector.x + vector.y * vector.y);
		float rcostheta = (radius * vector.x) / (float) Math.sqrt(vector.x*vector.x + vector.y * vector.y);;
		float p1= ((x + rcostheta ) * vector.x + (y + rsintheta) * vector.y) / (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y);
		float p2= ((x - rcostheta ) * vector.x + (y - rsintheta) * vector.y) / (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y);
		
		if(p1 > p2)
		{
			return new Interval(p2, p1);
		}
		else
		{
			return new Interval(p1,p2);
		}
	}
	
	public void draw(Graphics g)
	{
		//g.drawOval(x - radius, y - radius, radius*2, radius*2);
		g.drawImage(img, x - radius, y - radius, x +radius, y + radius, 0, 0, 221, 221);
	}

	@Override
	public boolean isInterior(float x, float y) {
		if ((x - this.x)* (x - this.x) + (y - this.y)*(y-this.y) <= radius*radius )
		{
			return true;
		}
		return false;
	}
}