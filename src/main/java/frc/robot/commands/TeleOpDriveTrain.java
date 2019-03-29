package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.DriveTrain;

public class TeleOpDriveTrainClapper extends Command {
	
	//squaredInputs allows for a slower increase an speed instead a linear acceleration
	private boolean squaredInputs;
	
	//creates instance of TeleOpDriveTrainClapper
	private static TeleOpDriveTrainClapper instance;
	public static TeleOpDriveTrainClapper getInstance()
	{
		if(instance == null)
			instance = new TeleOpDriveTrainClapper();
		return instance;
	}
	
	public TeleOpDriveTrainClapper() {
		//an instance of the drivetrain must be created before this constructor can be used
		requires(DriveTrain.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		//sets squaredInputs to false because we want linear acceleration
		squaredInputs = false;
		//the Drivetrain subsystem's default command is set to this drivetrain
		DriveTrain.getInstance().setCommand(TeleOpDriveTrainClapper.getInstance());;
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
		//calls the whichDrive() command from the Drivetrain subsystem class
		DriveTrain.getInstance().whichDrive();

		//flips the drivetrain's direction
		DriveTrain.getInstance().tankDrive(-getLeftStick(), -getRightStick(), squaredInputs);
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
		//System.out.println("Interrupting Clapp");
		//calls end so that the motors will stop if we are not calling them
		end();
	}
}
