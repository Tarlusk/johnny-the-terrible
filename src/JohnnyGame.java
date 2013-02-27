import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.ControllerButtonControl;
import org.newdawn.slick.command.ControllerDirectionControl;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.command.MouseButtonControl;

import entities.Block;
import entities.Entity;
import entities.MoveableEntity;

public class JohnnyGame extends BasicGame implements InputProviderListener{

	private Block block;
	
	private Command attack = new BasicCommand("attack");
	private Command left = new BasicCommand("left");
	private Command right = new BasicCommand("right");
	private Command up = new BasicCommand("up");
	private Command down = new BasicCommand("down");
	
	private Command select = new BasicCommand("select");
	private Command start = new BasicCommand("start");
	private Command ps = new BasicCommand("ps");
	private Command square = new BasicCommand("square");
	private Command triangle = new BasicCommand("triangle");
	private Command x = new BasicCommand("x");
	private Command circle = new BasicCommand("circle");
	private Command l1 = new BasicCommand("l1");
	private Command r1 = new BasicCommand("r1");
	private Command l2 = new BasicCommand("l2");
	private Command r2 = new BasicCommand("r2");
	private Command l3 = new BasicCommand("l3");
	private Command r3 = new BasicCommand("r3");
	private Command dup = new BasicCommand("dup");
	private Command ddown = new BasicCommand("ddown");
	private Command dleft = new BasicCommand("dleft");
	private Command dright = new BasicCommand("dright");
	
	private Command c1 = new BasicCommand("c");
	
	
	
	
	
	private InputProvider provider;
	
	public JohnnyGame(String title) 
	{
		super(title);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		gc.setShowFPS(false);
		//Set up controls
		provider = new InputProvider(gc.getInput());
		provider.addListener(this);
		
        provider.bindCommand(new KeyControl(Input.KEY_LEFT), left);
        provider.bindCommand(new KeyControl(Input.KEY_A), left);
        provider.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.LEFT), left);
        provider.bindCommand(new KeyControl(Input.KEY_RIGHT), right);
        provider.bindCommand(new KeyControl(Input.KEY_D), right);
        provider.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.RIGHT), right);
        provider.bindCommand(new KeyControl(Input.KEY_UP), up);
        provider.bindCommand(new KeyControl(Input.KEY_W), up);
        provider.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.UP), up);
        provider.bindCommand(new KeyControl(Input.KEY_DOWN), down);
        provider.bindCommand(new KeyControl(Input.KEY_S), down);
        provider.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.DOWN), down);
        provider.bindCommand(new KeyControl(Input.KEY_SPACE), attack);
        provider.bindCommand(new MouseButtonControl(0), attack);
        provider.bindCommand(new ControllerButtonControl(0, 14), attack);
        
        provider.bindCommand(new ControllerButtonControl(0, 0), ps);
    	provider.bindCommand(new ControllerButtonControl(0, 1), select);
    	provider.bindCommand(new ControllerButtonControl(0, 2), l3);
    	provider.bindCommand(new ControllerButtonControl(0, 3), r3);
    	provider.bindCommand(new ControllerButtonControl(0, 4), start);
    	provider.bindCommand(new ControllerButtonControl(0, 5), dup);
    	provider.bindCommand(new ControllerButtonControl(0, 6), dright);
    	provider.bindCommand(new ControllerButtonControl(0, 7), ddown);
    	provider.bindCommand(new ControllerButtonControl(0, 8), dleft);
    	provider.bindCommand(new ControllerButtonControl(0, 9), l2);
    	provider.bindCommand(new ControllerButtonControl(0, 10), r2);
    	provider.bindCommand(new ControllerButtonControl(0, 11), l1);
    	provider.bindCommand(new ControllerButtonControl(0, 12), r1);
    	provider.bindCommand(new ControllerButtonControl(0, 13), square);
    	provider.bindCommand(new ControllerButtonControl(0, 14), triangle);
    	provider.bindCommand(new ControllerButtonControl(0, 15), x);
    	provider.bindCommand(new ControllerButtonControl(0, 16), circle);
    	
    	
    	
    	
    	
    	


    	
		
		block = new Block(200, 200, 0, 0);
		gc.setMaximumLogicUpdateInterval(30);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{		
		//UPDATE
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
			AppGameContainer app = new AppGameContainer(new JohnnyGame("Hello World"));
			app.setDisplayMode(800, 600, false);
			app.start();
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void controlPressed(Command command) {
		if (command == left)
		{
			block.setDX(-.2f + block.getDX());
		}
		if (command == right)
		{
			block.setDX(.2f + block.getDX());
		}
		if (command == up)
		{
			block.setDY(-.2f + block.getDY());
		}
		if (command == down)
		{
			 block.setDY(.2f + block.getDY());
		}
		if (command == attack)
		{
			block.setState(1);
		}
		System.out.println(command);
	}

	@Override
	public void controlReleased(Command command) {
		if (command == left)
		{
			block.setDX(.2f + block.getDX());
		}
		if (command == right)
		{
			block.setDX(-.2f + block.getDX());
		}
		if (command == up)
		{
			block.setDY(.2f + block.getDY());
		}
		if (command == down)
		{
			 block.setDY(-.2f + block.getDY());
		}
		if (command == attack)
		{
			block.setState(0);
		}
		
	}
}
