package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import collision.Box;

public class Player extends MoveableEntity {

	public int direction;
	public Box hitbox;
	public Image sprite;
	public Image back;
	public Animation runningLeft;
	public Animation runningRight;
	private boolean jumpReady = false;
	
	public Player() 
	{
		super();
		hitbox = new Box(x +7, y ,32 -14, 32 );
		try 
		{
			sprite = new Image("res/man2/man2_fr1.gif");
			back = new Image("res/man2/man2_bk2.gif");
			runningLeft = new Animation(new Image[]{new Image("res/man2/man2_lf1.gif") , new Image("res/man2/man2_lf2.gif")},100);
			runningRight = new Animation(new Image[]{new Image("res/man2/man2_rt1.gif") , new Image("res/man2/man2_rt2.gif")},100);
		} catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
	public Player(float x, float y) 
	{
		super(x, y);
		hitbox = new Box(x +7, y ,32 -14, 32 );
		try 
		{
			sprite = new Image("res/man2/man2_fr1.gif");
			runningLeft = new Animation(new Image[]{new Image("res/man2/man2_lf1.gif") , new Image("res/man2/man2_lf2.gif")},100);
			runningRight = new Animation(new Image[]{new Image("res/man2/man2_rt1.gif") , new Image("res/man2/man2_rt2.gif")},100);
			back = new Image("res/man2/man2_bk2.gif");

		} catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}
	@Override
	public void draw(Graphics g) 
	{
		if (direction == 1)
		{
			g.drawAnimation(runningRight, x, y);
		}
		else if (direction == 2)
		{
			g.drawAnimation(runningLeft, x, y);
		}
		else if (direction == 3)
		{
			g.drawImage(back, x,y);
		}
		else
		{
			g.drawImage(sprite, x,y);
		}
	}
	@Override
	public void update(int delta) 
	{
		if (direction == 1)
		{
			dx = .15f;
		}
		else if (direction == 2)
		{
			dx = -.15f;
		}
		else 
		{
			dx = 0;
		}
		
		x += dx *delta;
		y += dy * delta;
		hitbox.x = x +7;
		hitbox.y = y;
	}
	public Bullet shoot(char c) throws SlickException {
		switch (c)
		{
		case 'r':
			return new Bullet(x + 13, y +12,.3f, 0);
		case 'l':
			return new Bullet(x + 13, y +12,-.3f, 0);
		case 'u':
			return new Bullet(x + 13, y +12,0, -.3f);
		case 'd':
			return new Bullet(x + 13, y +12,0, .3f);
		}
		
		throw new SlickException("Invalid direction character");
	}
	public void jump() {
		if(jumpReady)
		{
			dy = -.6f;
			jumpReady = false;
		}
	}
	public void setJumpReady() {
		this.jumpReady = true;
	}

}
