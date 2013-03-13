package entities;

public abstract class MoveableEntity extends Entity
{	
	protected float dx;
	protected float dy;
	
	public MoveableEntity() {
		super();
		dx = 0;
		dy = 0;
	}
	public MoveableEntity(float x, float y) {
		super(x, y);
		dx = 0;
		dy = 0;
	}
	public MoveableEntity(float x, float y, float dx, float dy) {
		super(x, y);
		this.dx = dx;
		this.dy = dy;
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
