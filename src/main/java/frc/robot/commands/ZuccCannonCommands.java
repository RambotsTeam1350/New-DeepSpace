package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.ZuccCannon;

public class ZuccCannonCommands extends Command
{
    private static ZuccCannonCommands instance;
    public static ZuccCannonCommands getInstance()
    {
        if (instance == null)
            instance = new ZuccCannonCommands();
        return instance;
    }

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
    

    public double getButtonY()
    {
        if (OI.getInstance().xbox.getYButton())
            return 1.0;
        return 0;
    }

    public double getButtonX()
    {
        if(OI.getInstance().xbox.getXButton())
            return 1.0;
        return 0;
    }

    // Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{   
        ZuccCannon.getInstance().moveTopZucc(getButtonY());
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
