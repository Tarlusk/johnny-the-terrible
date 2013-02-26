import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main 
{
	public static void main (String args[])
	{
		System.out.println("Hello World");

		try 
		{
			AppGameContainer app = new AppGameContainer(new Game("Hello World"));
			app.setDisplayMode(800, 600, false);
			app.start();
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
}
