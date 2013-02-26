package entities;

public abstract class MoveableEntity extends Entity
{	
	protected float dx;
	protected float dy;

	public void setDX( float dx)
	{
		this.dx = dx;
	}
	public void setDY( float dy)
	{
		this.dy = dy;
	}
	public float getDX()
	{
		return dx;
	}
	public float getDY()
	{
		return dy;
	}
}
