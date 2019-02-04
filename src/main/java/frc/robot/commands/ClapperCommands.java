package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.subsystems.Clapper;


public class ClapperCommands extends Command {
	
	//gets instance of ClapperCommands
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
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		//repeatedly calls moveClapperMotors and movePistons

		//gets instance of Clapper to access its methods
		//getting instance of OI to access the variable "xbox"
		//use the built in method "getY()" to get they y value of the left xbox joystick
		Clapper.getInstance().moveClapperMotors(OI.getInstance().xbox.getY(Hand.kLeft));
		
		//gets instance of Clapper subsystem to access its methods
		//uses the method movePistons which is written in Clapper
		Clapper.getInstance().movePistons();
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