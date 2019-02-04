package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.subsystems.Compress;;


public class CompressorCommands extends Command {
	
	//creates instance of CompressorCommands
	private static CompressorCommands instance;
	public static CompressorCommands getInstance()
	{
		if(instance == null)
			instance = new CompressorCommands();
		return instance;
		
		
	}
	
	public CompressorCommands() 
	{
		//an instance of the Compress subsystem must be created before this constructor can be used
		requires(Compress.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		//accesses the Compress subsystem and gets an instance of Clapper
		//uses the method setState from Compress
		//gets an instance of the OI to access the "xbox" variable
		//uses the built in command getAButtonPressed to return a boolean which is used as the parameter
		Compress.getInstance().setState(OI.getInstance().xbox.getAButtonPressed());	
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