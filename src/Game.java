import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Game extends BasicGame{

	public Game(String title) 
	{
		super(title);
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException 
	{
		
	}

	@Override
	public void update(GameContainer gc, int g) throws SlickException 
	{
		
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		g.drawString("Hello World", 100, 100);
		
	}
}
