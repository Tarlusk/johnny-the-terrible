package linear;

public class Vector2d 
{
	public float x;
	public float y;
	
	public Vector2d()
	{
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2d(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float inner(Vector2d vector)
	{
		return (this.x * vector.x + this.y * vector.y);
	}
	
	public float cross(Vector2d vector)
	{
		return (this.x * vector.y - this.y * vector.x);
	}
	
	public Vector2d add(Vector2d vector)
	{
		return new Vector2d(this.x + vector.x, this.y + vector.y);
	}
	
	public Vector2d subtract(Vector2d vector)
	{
		return new Vector2d(this.x - vector.x, this.y - vector.y);
	}
	
	public float magnitude()
	{
		return this.x *this.x + this.y * this.y;
	}
	
	public Vector2d scale(float c)
	{
		return new Vector2d(this.x* c, this.y *c);
	}
}
