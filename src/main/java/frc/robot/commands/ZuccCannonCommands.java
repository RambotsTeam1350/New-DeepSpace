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
    

    // Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{   
        //the top motor of the Zucc is bound to the Y button
        //while the Y button is pressed, the motor will spin full speed
        ZuccCannon.getInstance().moveTopZucc(OI.getInstance().xbox.getXButton());

        //the bottom motor of the Zucc is bound to the X button
        //while the X button is pressed, the motor will spin full speed
        ZuccCannon.getInstance().moveBottomZucc(OI.getInstance().xbox.getYButton());
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
