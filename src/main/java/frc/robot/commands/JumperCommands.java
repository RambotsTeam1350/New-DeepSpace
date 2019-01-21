package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Jumper;


public class JumperCommands extends Command {
	
	private static JumperCommands instance;
	public static JumperCommands getInstance()
	{
		if(instance == null)
			instance = new JumperCommands();
		return instance;
		
		
	}
	
	public JumperCommands() 
	{
		//an instance of the Clapper subsystem must be created before this constructor can be used
		requires(Jumper.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		Jumper.getInstance().moveJumper1();
		Jumper.getInstance().moveJumper2();
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