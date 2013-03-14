package main;

import java.util.Vector;

import linear.Vector2d;

import org.lwjgl.input.Mouse;
//import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
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
import entities.MoveableEntity;
import entities.Player;

public class JohnnyGame extends BasicGame implements InputProviderListener{

	public static final int MAIN_MENU = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE_MENU = 2;
	public static final Vector2d gravityVector = new Vector2d(0, 0.03f);
	
	private Image background;
	private Player player;
	private Box ground;
	private Circle sphere;
	private Collider collider;
	private int gameState = MAIN_MENU;
	private MenuButton setupButton;
	
	Vector<MoveableEntity> moveables = new Vector<MoveableEntity>(10);

	private ControlProvider provider;
	
	public JohnnyGame(String title) 
	{
		super(title);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		gc.setAlwaysRender(true);
		gc.setShowFPS(false);
		//create collider
		collider = new Collider();
		background = new Image("res/space.jpg");
		//Set up controls
		provider = new ControlProvider(gc.getInput());
		provider.addListener(this);

		player = new Player(0, 400);
		
		sphere = new Circle( 400, 570, 100);
		ground = new Box (0,550,800,50);
		gc.setMaximumLogicUpdateInterval(50);
		setupButton = new MenuButton(100, 200, 200, 100);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{			
		switch(gameState)
		{
		case RUNNING:
			//INPUT
			if (provider.isCommandControlDown(provider.left) && provider.isCommandControlDown(provider.right))
			{
				player.direction = 0;
			}
			else if (provider.isCommandControlDown(provider.right))
			{
				player.direction = 1;
			}
			else if (provider.isCommandControlDown(provider.left))
			{
				player.direction = 2;
			}
			else if (provider.isCommandControlDown(provider.up))
			{
				player.direction = 3;
			}
			else
			{
				player.direction = 0;
			}
			
			if (provider.isCommandControlDown(provider.jump))
			{
				player.jump();
			}
			
			//UPDATE
			//Pull player down
			player.setDX(player.getDX() + gravityVector.x);
			player.setDY(player.getDY() + gravityVector.y);
			
			//update player position
			player.update(delta);
			
			//get vector distance needed to move player not to intersect sphere
			Vector2d distVec = collider.getMinDistanceVector(player.hitbox, sphere);
			
			//move player
			player.setX(player.getX() + distVec.x);
			player.setY(player.getY() + distVec.y);
			
			player.setJumpUnready();
			if(distVec.x != 0 || distVec.y != 0)
			{
				Vector2d velocityVec = new Vector2d(player.getDX(), player.getDY());
				velocityVec = velocityVec.subtract(velocityVec.proj(distVec));
				player.setDX(velocityVec.x);
				player.setDY(velocityVec.y);
				
				//if character moved within 45 degrees of up
				if (distVec.y < 0 && distVec.y < -Math.abs(distVec.x))
				{
					player.setJumpReady();
				}
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
				//if character moved within 45 degrees of up
				if (distVec.y < 0 && distVec.y < -Math.abs(distVec.x))
				{
				player.setJumpReady();
				}
			}
			
			for(MoveableEntity a : moveables)
			{
				a.update(delta);
			}
			break;
		case MAIN_MENU:
			if( Mouse.getX() >= setupButton.getX() && Mouse.getX() <= setupButton.getX() + setupButton.getWidth() && (gc.getHeight() - Mouse.getY()) >= setupButton.getY() && (gc.getHeight() - Mouse.getY()) <= setupButton.getY() + setupButton.getHeight())
			{
				setupButton.setHover();
			}
			else
			{
				setupButton.setUnselected();
			}
			break;
		case PAUSE_MENU:
			break;
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		switch(gameState)
		{
		case RUNNING:
			g.drawImage(background, 0, 0);
			sphere.draw(g);
			//g.drawImage(ship.img, 400, 100);
			g.fillRect(ground.x, ground.y, ground.width, ground.height); 
			//g.fillRect(box.x, box.y, box.width, box.height);
			for(MoveableEntity a : moveables)
			{
				a.draw(g);
			}
			player.draw(g);
			break;
		case MAIN_MENU:
			g.drawString("Main Menu", 100, 100);
			setupButton.draw(g);
			//g.drawString("Set-Up", 120, 250);
			break;
		case PAUSE_MENU:
			g.drawImage(background, 0, 0);
			sphere.draw(g);
			g.fillRect(ground.x, ground.y, ground.width, ground.height);
			player.draw(g);
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(0, 0, 800, 600);
			g.setColor(new Color(255,255,255));
			g.drawString("Pause", 380, 300);
			break;
		}
	}
	
	@Override
	public void controlPressed(Command command) {
		switch(gameState)
		{
		case RUNNING:
			if (command == provider.pause)
			{
				gameState = PAUSE_MENU;
			}
			if (command == provider.attackRight)
			{
				try {
					moveables.add(player.shoot('r'));
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
			if (command == provider.attackLeft)
			{
				try {
					moveables.add(player.shoot('l'));
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
			if (command == provider.attackUp)
			{
				try {
					moveables.add(player.shoot('u'));
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
			if (command == provider.attackDown)
			{
				try {
					moveables.add(player.shoot('d'));
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
			break;
		case MAIN_MENU:
			if (command == provider.pause)
			{
				System.exit(0);
			}
			if (command == provider.jump)
			{
				gameState = RUNNING;
			}
			break;
		case PAUSE_MENU:
			if (command == provider.pause)
			{
				gameState = MAIN_MENU;
			}
			if (command == provider.jump)
			{
				gameState = RUNNING;
			}
			break;
		}
	}

	@Override
	public void controlReleased(Command command) {
		switch(gameState)
		{
		case RUNNING:
			break;
		case MAIN_MENU:
			break;
		case PAUSE_MENU:
			break;
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