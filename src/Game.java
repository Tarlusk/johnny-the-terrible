import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entities.Block;
import entities.Entity;

public class Game extends BasicGame{

	private Entity block;
	
	public Game(String title) 
	{
		super(title);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		block = new Block(200, 200, .04f, .01f);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{
		block.update(delta);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		g.drawString("Hello World", 100, 100);
		block.draw(g);
		
	}
	
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
