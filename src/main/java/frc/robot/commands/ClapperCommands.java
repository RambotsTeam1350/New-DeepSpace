package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Clapper;


public class ClapperCommands extends Command {
	
	private static ClapperCommands instance;
	public static ClapperCommands getInstance()
	{
		if(instance == null)
			instance = new ClapperCommands();
		return instance;
		
		
	}
	
	public ClapperCommands() 
	{
		//an instance of the Clapper subsystem must be created before this constructor can be used
		requires(Clapper.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	
	//gets the y value of the left stick on the xbox controller
	private static double getXboxControllerLeft()
	{
		return (OI.getInstance().XboxControllerLeft.getY());
	}
	
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		Clapper.getInstance().moveClapperMotors(getXboxControllerLeft());	
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