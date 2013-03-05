package collision;

import linear.Interval;
import linear.Vector2d;

public class Point extends Primitive
{	
	public Point(float x, float y)
	{
		super(x, y);
	}
	
	@Override
	public Interval proj(Vector2d vector) 
	{
		float projection = (x * vector.x + y * vector.y) / (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y);
		return new Interval(projection, projection);
	}
	
	public Vector2d toVector()
	{
		return new Vector2d(x, y);
	}
	
	public Vector2d subtract( Point point)
	{
		return new Vector2d(x - point.x, y - point.y);
	}
	
	@Override
	public boolean isInterior(float x, float y) {
		if (this.x == x  && this.y == y)
		{
			return true;
		}
		return false;
	}
}