package controls;

import org.newdawn.slick.Input;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.ControllerButtonControl;
import org.newdawn.slick.command.ControllerDirectionControl;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.command.MouseButtonControl;

public class ControlProvider extends InputProvider{

	public Command attack = new BasicCommand("attack");
	public Command jump = new BasicCommand("jump");
	public Command left = new BasicCommand("left");
	public Command right = new BasicCommand("right");
	public Command up = new BasicCommand("up");
	public Command down = new BasicCommand("down");
	
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
	
	public ControlProvider(Input input) {
		super(input);
		
        this.bindCommand(new MouseButtonControl(0), attack);
		
        this.bindCommand(new KeyControl(Input.KEY_LEFT), left);
        this.bindCommand(new KeyControl(Input.KEY_A), left);
        this.bindCommand(new KeyControl(Input.KEY_RIGHT), right);
        this.bindCommand(new KeyControl(Input.KEY_D), right);
        this.bindCommand(new KeyControl(Input.KEY_UP), up);
        this.bindCommand(new KeyControl(Input.KEY_W), up);
        this.bindCommand(new KeyControl(Input.KEY_DOWN), down);
        this.bindCommand(new KeyControl(Input.KEY_S), down);
        this.bindCommand(new KeyControl(Input.KEY_SPACE), jump);
		
        this.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.LEFT), left);
        this.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.RIGHT), right);
        this.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.UP), up);
        this.bindCommand(new ControllerDirectionControl(0, ControllerDirectionControl.DOWN), down);
        this.bindCommand(new ControllerButtonControl(0, 14), attack);
        
        this.bindCommand(new ControllerButtonControl(0, 0), ps);
    	this.bindCommand(new ControllerButtonControl(0, 1), select);
    	this.bindCommand(new ControllerButtonControl(0, 2), l3);
    	this.bindCommand(new ControllerButtonControl(0, 3), r3);
    	this.bindCommand(new ControllerButtonControl(0, 4), start);
    	this.bindCommand(new ControllerButtonControl(0, 5), dup);
    	this.bindCommand(new ControllerButtonControl(0, 6), dright);
    	this.bindCommand(new ControllerButtonControl(0, 7), ddown);
    	this.bindCommand(new ControllerButtonControl(0, 8), dleft);
    	this.bindCommand(new ControllerButtonControl(0, 9), l2);
    	this.bindCommand(new ControllerButtonControl(0, 10), r2);
    	this.bindCommand(new ControllerButtonControl(0, 11), l1);
    	this.bindCommand(new ControllerButtonControl(0, 12), r1);
    	this.bindCommand(new ControllerButtonControl(0, 13), square);
    	this.bindCommand(new ControllerButtonControl(0, 14), triangle);
    	this.bindCommand(new ControllerButtonControl(0, 15), x);
    	this.bindCommand(new ControllerButtonControl(0, 16), circle);
	}

}
