package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.DriveTrain;


public class TeleOpDriveTrainZucc extends Command {
	
	private boolean squaredInputs;
	
	//creates instance of this class
	private static TeleOpDriveTrainZucc instance;
	public static TeleOpDriveTrainZucc getInstance()
	{
		if(instance == null)
			instance = new TeleOpDriveTrainZucc();
		return instance;
	}
	
	public TeleOpDriveTrainZucc() {
		//an instance of the drivetrain must be created before this constructor can be used
		requires(DriveTrain.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		//this makes the acceleration linear instead of exponential
		squaredInputs = false;

		//sets the drivetrain in the direction of the intake of the Zucc
		DriveTrain.getInstance().setCommand(TeleOpDriveTrainZucc.getInstance());;
	}

	
	//these two methods get the y values of the left and right sticks
	//this is necessary for the execute method
	private static double getLeftStick()
	{
		return (OI.getInstance().leftStick.getY());
	}
	
	private static double getRightStick()
	{
		return (OI.getInstance().rightStick.getY());
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		//calls the whichDrive() method to determine which direction the drivetrain should drive
		DriveTrain.getInstance().whichDrive();

		//the side of the robot with the Zucc intake is considered the front
		//hence why the values for the right and left stick are set positive here
		//this sets the drivetrain to have the front where the intake of the Zucc is
		DriveTrain.getInstance().tankDrive(getRightStick(), getLeftStick(), squaredInputs);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		//stops the motors
		DriveTrain.getInstance().stopMotors();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		//stops the motors
		end();
	}
}
