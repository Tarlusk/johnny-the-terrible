package entities;

public abstract class MoveableEntity extends Entity
{	
	protected float dx;
	protected float dy;
	
	public MoveableEntity() {
		super();
	}
	public MoveableEntity(float x, float y) {
		super(x, y);
	}

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
	@Override
	public void update(int delta) 
	{
		x += delta * dx;
		y += delta * dy;
	}
}
