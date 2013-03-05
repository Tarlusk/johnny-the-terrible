package collision;

import linear.Vector2d;

public abstract class ConvexPolygon extends Primitive
{

	public ConvexPolygon(float x, float y) 
	{
		super(x, y);
	}

	public abstract Vector2d[] getAxes();
	
	public abstract Point[] getVertices();
}