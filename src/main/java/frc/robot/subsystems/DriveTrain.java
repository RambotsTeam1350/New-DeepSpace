
package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.TeleOpDriveTrain;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveTrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	
	//declaring the motor controllers, VictorSP is the type of motor controllers we use
	//be sure to import your teleopdrivetrain, see above
	//be sure to import the differentialdrive from wpi library
	private VictorSP leftMotorController;
	private VictorSP rightMotorController;
	private TeleOpDriveTrain tankDrive;
	private DifferentialDrive robotDrive;
	
	//runs the tankdrive
	public void initDefaultCommand() {
		setDefaultCommand(tankDrive);
		// Set the default command for a subsystem here.
	}
	
	//instance variable of Drivetrain
	private static DriveTrain instance;
	
	//creates drivetrain instance
	public static DriveTrain getInstance()
	{
		if(instance == null)
			instance = new DriveTrain();
		return instance;
	}
	
	//creates timer
	Timer timer = new Timer();
	
	//constructs drivetrain
	public DriveTrain()
	{
		
	}
	
	public void tankDrive(double left, double right, boolean squaredInputs)
	{
		robotDrive.tankDrive(left, right, false);
	}

	private boolean xPressed = false;
	private void getXButton(){
		if (OI.getInstance().xbox.getXButtonPressed() && xPressed == false)
			xPressed = true;
		if (OI.getInstance().xbox.getXButtonPressed() && xPressed == true)
			xPressed = false;		
	}
	
	//takes the input from the joysticks and reads them as speed for the tankdrive
	public void driveLeftMotor(double speed, double time)
	{
		getXButton();

		if (xPressed == false)
			leftMotorController.set(speed);
		else if (xPressed == true)
			rightMotorController.set(speed);
		//restricts this class from moving outside the method while method is active
		//but another method can be accessed by something like TeleopDriveTrain
		Timer.delay(time);
		
		//once the motor controller is released, the speed is set to 0
		if (xPressed == false)
			leftMotorController.set(0);
		else if (xPressed == true)
			rightMotorController.set(0);
		
	}
	
	public void driveRightMotor(double speed, double time)
	{
		if (xPressed == false)
			rightMotorController.set(speed);
		else if (xPressed == true)
			leftMotorController.set(speed);
		
		//restricts this class from moving outside the method while method is active
		//but another method can be accessed by something like TeleopDriveTrain
		Timer.delay(time);
		
		//once the motor controller is released, the speed is set to 0
		if (xPressed == false)
			rightMotorController.set(0);
		else if (xPressed == true)
			leftMotorController.set(0);
		
	}
	
	public void initialize()
	{
		
		//the tankdrive is the instance of the teleopdrivetrain
		tankDrive = TeleOpDriveTrain.getInstance();
		
		//makes it so the instance of the left and right motor controllers in this class
		//is the same as the motor controllers and their ports in the robotmap class
		leftMotorController = new VictorSP(RobotMap.leftMotor);
		rightMotorController = new VictorSP(RobotMap.rightMotor);
		
		//the robotdrive is a differential drive that uses the motor controllers defined above
		//the DifferentialDrive method is given by WPI
		robotDrive = new DifferentialDrive(leftMotorController, rightMotorController);
		
	}
	
	
	
}
