package collision;

//import java.util.Vector;

import linear.Vector2d;

public class Collider 
{	
	public Collider(){};
	public boolean intersects(Primitive a, Primitive b)
	{
		boolean flag = true;
		if(a instanceof Box)
		{
			if (b instanceof Circle)
			{
				Vector2d vector;
				Point[] points = ((Box) a).getVertices();
				Vector2d[] axes = ((Box) a).getAxes();

				for (int i = 0; i < 4 && flag; i++)
				{
					if( ! a.proj(axes[i]).intersects( b.proj(axes[i])))
					{
						flag = false;
					}
				}
				if (flag)
				{
					int index = 0;
					for (int i = 1; i < 4; i++)
					{
						if((points[i].x - b.x) * (points[i].x - b.x) + (points[i].y - b.y) * (points[i].y - b.y) < (points[index].x - b.x) * (points[index].x - b.x) + (points[index].y - b.y) * (points[index].y - b.y))
						{
							index = i;
						}
					}
					vector = new Vector2d( points[index].x - b.x, points[index].y - b.y);
					if( ! a.proj(vector).intersects( b.proj(vector)))
					{
						flag = false;
					}
				}
			}
			else if (b instanceof Box)
			{
				Vector2d[] axes = ((Box) a).getAxes();

				for (int i = 0; i < 4 && flag; i++)
				{
					if( ! a.proj(axes[i]).intersects( b.proj(axes[i])))
					{
						flag = false;
					}
				}
				
				axes = ((Box) b).getAxes();

				for (int i = 0; i < 4 && flag; i++)
				{
					if( ! a.proj(axes[i]).intersects( b.proj(axes[i])))
					{
						flag = false;
					}
				}
			}
		}	
		return flag;
	}
	public Vector2d getMinDistanceVector(Primitive a, Primitive b)
	{
		boolean distNotSet = true;
		boolean divideFound = false;
		float minDist = 0;
		Vector2d minVector = new Vector2d(0,0);
		
		if(a instanceof Box)
		{
			if (b instanceof Circle)
			{
				Vector2d vector;
				float currentDist;
				Point[] points = ((Box) a).getVertices();
				Vector2d[] axes = ((Box) a).getAxes();

				for (int i = 0; i < 2 && !divideFound; i++)
				{
					currentDist = a.proj(axes[i]).getIntersection( b.proj(axes[i]));
					if(currentDist == 0)
					{
						divideFound = true;
					}
					else if(distNotSet || Math.abs(currentDist) < Math.abs(minDist))
					{
						minDist = currentDist;
						minVector = axes[i];
						distNotSet = false;
					}
				}
				if (!divideFound)
				{
					int index = 0;
					for (int i = 1; i < 4; i++)
					{
						if((points[i].x - b.x) * (points[i].x - b.x) + (points[i].y - b.y) * (points[i].y - b.y) < (points[index].x - b.x) * (points[index].x - b.x) + (points[index].y - b.y) * (points[index].y - b.y))
						{
							index = i;
						}
					}
					vector = new Vector2d( points[index].x - b.x, points[index].y - b.y);
					currentDist = a.proj(vector).getIntersection( b.proj(vector));
					if(distNotSet || Math.abs(currentDist) < Math.abs(minDist))
					{
						minDist = currentDist;
						minVector = vector;
					}
				}
			}
			else if (b instanceof Box)
			{
				float currentDist;
				Vector2d[] axes = ((Box) a).getAxes();

				for (int i = 0; i < 4 && !divideFound; i++)
				{
					currentDist = a.proj(axes[i]).getIntersection( b.proj(axes[i]));
					if(currentDist == 0)
					{
						divideFound = true;
					}
					else if(distNotSet || Math.abs(currentDist) < Math.abs(minDist))
					{
						minDist = currentDist;
						minVector = axes[i];
						distNotSet = false;
					}
				}
				axes = ((Box) b).getAxes();

				for (int i = 0; i < 4 && !divideFound; i++)
				{
					currentDist = a.proj(axes[i]).getIntersection( b.proj(axes[i]));
					if(currentDist == 0)
					{
						divideFound = true;
					}
					else if(distNotSet || Math.abs(currentDist) < Math.abs(minDist))
					{
						minDist = currentDist;
						minVector = axes[i];
						distNotSet = false;
					}
				}
			}
		}
		if (divideFound)
			return new Vector2d(0,0);
		if (minVector.x == 0 && minVector.y == 0 || distNotSet )
		{
			return minVector;
		}
		return (minVector.scale(minDist/ (float) Math.sqrt(minVector.magnitudeSqr())));
	}
}