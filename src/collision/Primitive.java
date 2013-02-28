package collision;

import linear.Interval;
import linear.Vector2d;

public abstract class Primitive 
{
	public float x;
	public float y;
	
	public Primitive(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public abstract Interval proj(Vector2d vector);
	
	public abstract boolean isInterior(float x, float y);
}
