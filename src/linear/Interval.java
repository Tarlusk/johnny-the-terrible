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
	
	public float getIntersection(Interval inter)
	{
		if(this.min >= inter.max || this.max <= inter.min)
		{
			return 0;
		}
		if(inter.max >= this.max  && inter.min >= this.min)
		{
			return	inter.min - this.max;
		}
		else if(this.max <= inter.max && this.min >= inter.min)
		{
			return ((inter.max - this.min) < (this.max - inter.min) ? inter.max -this.min : inter.min - this.max);
		}
		else if (this.max >= inter.max && this.min <= inter.min)
		{
			return ((inter.max - this.min) < (this.max - inter.min) ? inter.max -this.min : inter.min - this.max);
		}
		else
		{
			return ((this.max < inter.max ? this.max : inter.max) - (this.min > inter.min ? this.min : inter.min));
		}
	}
}
