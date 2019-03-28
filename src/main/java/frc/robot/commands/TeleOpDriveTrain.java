package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.DriveTrain;

public class TeleOpDriveTrain extends Command {
	
	//squaredInputs allows for a slower increase an speed instead a linear acceleration
	private boolean squaredInputs;
	private boolean forward;
	
	//creates instance of TeleOpDriveTrainClapper
	private static TeleOpDriveTrain instance;
	public static TeleOpDriveTrain getInstance()
	{
		if(instance == null)
			instance = new TeleOpDriveTrain();
		return instance;
	}
	
	public TeleOpDriveTrain() {
		//an instance of the drivetrain must be created before this constructor can be used
		requires(DriveTrain.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		//sets squaredInputs to false because we want linear acceleration
		squaredInputs = false;
		forward = true;
		isPressed = false;
		//the Drivetrain subsystem's default command is set to this drivetrain
		DriveTrain.getInstance().setCommand(TeleOpDriveTrain.getInstance());;
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
	
	public void setForward(boolean f){
		forward = f;
	}

	private boolean isPressed;
	private void toggleForward(){
		if (OI.getInstance().joyRightTrigger.get() && forward && !isPressed){
			forward = false; isPressed = true;}
		if (OI.getInstance().joyRightTrigger.get() && !forward && !isPressed){
			forward = true; isPressed = true;}
		if (!OI.getInstance().joyRightTrigger.get())
			isPressed = false;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		//calls the whichDrive() command from the Drivetrain subsystem class
		toggleForward();
		//flips the drivetrain's direction
		if (forward)
			DriveTrain.getInstance().tankDrive(-getLeftStick(), -getRightStick(), squaredInputs);
		else if (!forward)
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
		//calls end so that the motors will stop if we are not calling them
		end();
	}
}
