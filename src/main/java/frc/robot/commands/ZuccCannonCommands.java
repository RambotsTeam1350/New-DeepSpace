package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.ZuccCannon;

public class ZuccCannonCommands extends Command
{
    //creates instance of ZuccCannonCommands
    private static ZuccCannonCommands instance;
    public static ZuccCannonCommands getInstance()
    {
        if (instance == null)
            instance = new ZuccCannonCommands();
        return instance;
    }

    //needs an instance of the ZuccCannon subystem before this can be constructed
    public ZuccCannonCommands()
    {
        requires(ZuccCannon.getInstance());
    }

    // Called just before this Command runs the first time
	@Override
    protected void initialize() 
    {
        //empty
    }
    private boolean yPressed = false;
    //while the Y button is pressed, return full speed, else off
    public double getButtonY()
    {

        if (OI.getInstance().xbox.getYButtonPressed())
            yPressed = true;
        if (OI.getInstance().xbox.getYButtonPressed())
            yPressed = false;
        if (yPressed)
            return 1.0;
        return 0;
    }

    private boolean xPressed = false;
    //while the X button is pressed, return full speed, else off
    public double getButtonX()
    {
        if(OI.getInstance().xbox.getXButtonPressed())
            xPressed = true;
        if(OI.getInstance().xbox.getXButtonReleased())
            xPressed = false;
        if (xPressed)
            return 1.0;
        return 0;
    }

    // Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{   
        //the top motor of the Zucc is bound to the Y button
        //while the Y button is pressed, the motor will spin full speed
        ZuccCannon.getInstance().moveTopZucc(getButtonY());

        //the bottom motor of the Zucc is bound to the X button
        //while the X button is pressed, the motor will spin full speed
        ZuccCannon.getInstance().moveBottomZucc(getButtonX());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
