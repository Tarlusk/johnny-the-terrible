package linear;

public class Interval 
{
	public float min;
	public float max;
	
	public Interval(float min, float max) 
	{
		this.min = min;
		this.max = max;
	}
	
	public boolean intersects(Interval inter)
	{
		if(this.min > inter.max)
		{
			return false;
		}
		if (this.max < inter.min)
		{
			return false;
		}
		return true;	
	}
}
