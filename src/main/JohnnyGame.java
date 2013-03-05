package main;
import linear.Vector2d;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProviderListener;

import controls.ControlProvider;

import collision.Box;
import collision.Circle;
import collision.Collider;

import entities.Block;
import entities.Player;

public class JohnnyGame extends BasicGame implements InputProviderListener{

	private Image background;
	private Image sun;
	private Player player;
	private Box ground;
	private Circle sphere;
	private Collider collider;

	private ControlProvider provider;
	
	public JohnnyGame(String title) 
	{
		super(title);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		gc.setShowFPS(false);
		//create collider
		collider = new Collider();
		background = new Image("res/space.jpg");
		sun = new Image("res/sun.png");
		//Set up controls
		provider = new ControlProvider(gc.getInput());
		provider.addListener(this);

		player = new Player(0, 400);
		
		sphere = new Circle( 400, 360, 100);
		ground = new Box (0,550,800,50);
		gc.setMaximumLogicUpdateInterval(50);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{		
		//UPDATE
		player.update(delta);
		Vector2d gravityVector = new Vector2d(0, 0.03f);
		
		player.setDX(player.getDX() + gravityVector.x);
		player.setDY(player.getDY() + gravityVector.y);
		
		/*
		gravityVector = (new Vector2d( (sphere.x - block.getX() - block.width/2), sphere.y - block.getY() - block.height/2));
		gravityVector = gravityVector.scale(10/(gravityVector.magnitude()*gravityVector.magnitudeSqr()));
		block.setDX(block.getDX() + gravityVector.x);
		block.setDY(block.getDY() + gravityVector.y);
		*/
		
		Vector2d distVec = collider.getMinDistanceVector(player.hitbox, sphere);
		player.setX(player.getX() + distVec.x);
		player.setY(player.getY() + distVec.y);
		
		if(distVec.x != 0 || distVec.y != 0)
		{
			Vector2d velocityVec = new Vector2d(player.getDX(), player.getDY());
			velocityVec = velocityVec.subtract(velocityVec.proj(distVec));
			player.setDX(velocityVec.x);
			player.setDY(velocityVec.y);
		}
		
		distVec = collider.getMinDistanceVector(player.hitbox, ground);
		player.setX(player.getX() + distVec.x);
		player.setY(player.getY() + distVec.y);
		if(distVec.x != 0 || distVec.y != 0)
		{
			Vector2d velocityVec = new Vector2d(player.getDX(), player.getDY());
			velocityVec = velocityVec.subtract(velocityVec.proj(distVec));
			player.setDX(velocityVec.x);
			player.setDY(velocityVec.y);
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		g.drawImage(background, 0, 0);
		sphere.draw(g);
		//g.drawImage(ship.img, 400, 100);
		g.fillRect(ground.x, ground.y, ground.width, ground.height);
		//g.fillRect(box.x, box.y, box.width, box.height);
		player.draw(g);
	}
	
	@Override
	public void controlPressed(Command command) {
		if (command == provider.left)
		{
			player.direction = 2;
		}
		if (command == provider.right)
		{
			player.direction = 1;
		}
		if (command == provider.up && player.direction == 0)
		{
			player.direction = 3;
		}
		if (command == provider.jump)
		{
			player.setDY(-.5f);
		}
	}

	@Override
	public void controlReleased(Command command) {
		if (command == provider.left && player.direction == 2)
		{
			player.direction = 0;
		}
		if (command == provider.right && player.direction == 1)
		{
			player.direction = 0;
		}
		if (command == provider.up && player.direction == 3)
		{
			player.direction = 0;
		}
	}
	
	public static void main (String args[])
	{
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
}