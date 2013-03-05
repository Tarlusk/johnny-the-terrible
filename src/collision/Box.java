package collision;

import linear.Interval;
import linear.Vector2d;
import collision.Point;

public class Box extends ConvexPolygon
{
	public float width;
	public float height;
	
	public Box(float x, float y, float width, float height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	@Override
	public Interval proj(Vector2d vector) 
	{
		float points[] = 
			{
				((x) * vector.x + (y) * vector.y) / (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y),
				((x) * vector.x + (y + height) * vector.y) / (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y),
				((x + width) * vector.x + (y) * vector.y) / (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y),
				((x + width) * vector.x + (y + height) * vector.y) / (float) Math.sqrt(vector.x * vector.x + vector.y * vector.y)
			};
		
		float max = points[0];
		float min = max;
		
		for (int i = 1; i < 4; i++)
		{
			if (points[i] > max)
			{
				max = points[i];
			}
			else if (points[i] < min)
			{
				min = points[i];
			}
		}
		return new Interval(min, max);
	}
	
	@Override
	public boolean isInterior(float x, float y) {
		if ( x < this.x || x > this.x + width || y < this.y || y > this.y + height)
		{
			return false;
		}
		return true;
	}
	
	@Override
	public Point[] getVertices()
	{
		Point[] vertices = {new Point(x, y), new Point(x , y + height), new Point(x + width, y + height), new Point(x + width, y)};

		return vertices;
	}
	
	@Override
	public Vector2d[] getAxes()
	{
		Vector2d[] axes = {(new Vector2d( 0, height)).orthoRight(), (new Vector2d( width, 0)).orthoRight(), (new Vector2d( 0, -height)).orthoRight(), (new Vector2d( -width, 0)).orthoRight() };
		return axes;
	}
}
