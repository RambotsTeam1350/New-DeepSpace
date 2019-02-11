package frc.robot.commands;
import frc.robot.OI;
import edu.wpi.first.wpilibj.GenericHID.Hand;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Jumper;

public class JumperCommands extends Command {
	
	//creates instance of JumperCommands
	private static JumperCommands instance;
	public static JumperCommands getInstance()
	{
		if(instance == null)
			instance = new JumperCommands();
		return instance;
	}
	
	public JumperCommands() 
	{
		//an instance of the Jumper subsystem must be created before this constructor can be used
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
		//accesses the Jumper subsystem and call the method moveJumper1() and moveJumper2()
		if (OI.getInstance().xbox.getBumperPressed(Hand.kLeft)){
			Jumper.getInstance().moveJumper1();
		}
		if (OI.getInstance().xbox.getBumperPressed(Hand.kRight)){
			Jumper.getInstance().moveJumper2();
		}
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