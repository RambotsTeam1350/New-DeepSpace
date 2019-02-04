package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.TeleOpDriveTrainClapper;
import frc.robot.commands.TeleOpDriveTrainZucc;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;


public class DriveTrain extends Subsystem {

	//declaring the motor controllers, VictorSP is the type of motor controllers we use
	//be sure to import your teleopdrivetrain, see above
	//be sure to import the differentialdrive from wpi library
	private VictorSP leftMotorController;
	private VictorSP rightMotorController;

	//Differential drive is the type of drive we chose this year
	//there are other types of drivetrains such as mecanum
	//wpi gives us these drivetrains as object types
	private DifferentialDrive robotDrive;
	
	//makes default drivetrain that uses commands from TeleOpDriveTrainZucc
	//front of drivetrain is the side of the robot that has the intake of the Zucc
	public void initDefaultCommand() {
		setDefaultCommand(TeleOpDriveTrainZucc.getInstance());
	}

	//this method is used to change the direction of the drivetrain
	public void setCommand(Command c){
		setDefaultCommand(c);
	}
	
	//creates instance of DriveTrain class
	private static DriveTrain instance;
	public static DriveTrain getInstance()
	{
		if(instance == null)
			instance = new DriveTrain();
		return instance;
	}
	
	//creates timer
	Timer timer = new Timer();
	
	//empty constructor of drivetrain
	public DriveTrain()
	{

	}
	
	//we drive the robot with the left joystick controlling the left motors
	//and the right joystick controlling the right motors
	//this creates said drivetrain and gets values from the joysticks when called later
	public void tankDrive(double left, double right, boolean squaredInputs)
	{
		robotDrive.tankDrive(left, right, false);
	}

	//allows for switch of direction of drivetrain
	//if the trigger on the right joystick is pressed, drivetrain direction switches
	public void whichDrive(){
		if (DriveTrain.getInstance().getCurrentCommand() == TeleOpDriveTrainClapper.getInstance())
			OI.getInstance().joyRightTrigger.toggleWhenPressed(TeleOpDriveTrainZucc.getInstance());
		else if (DriveTrain.getInstance().getCurrentCommand() == TeleOpDriveTrainZucc.getInstance())
			OI.getInstance().joyRightTrigger.toggleWhenPressed(TeleOpDriveTrainClapper.getInstance());
	}
	
	//takes the input from the joysticks and reads them as speed for the tankdrive
	public void driveLeftMotor(double speed, double time)
	{
		leftMotorController.set(speed);

		//restricts this class from moving outside the method while method is active
		//but another method can be accessed by something like TeleopDriveTrain
		Timer.delay(time);
		
		//once the motor controller is released, the speed is set to 0
		leftMotorController.set(0);
		
	}
	
	public void driveRightMotor(double speed, double time)
	{
		rightMotorController.set(speed);
		
		//restricts this class from moving outside the method while method is active
		//but another method can be accessed by something like TeleopDriveTrain
		Timer.delay(time);
		
		//once the motor controller is released, the speed is set to 0
		rightMotorController.set(0);
		
	}
	
	//stops the motors
	public void stopMotors(){
		rightMotorController.stopMotor();
		leftMotorController.stopMotor();
	}
	
	public void initialize()
	{
		//makes it so the instance of the left and right motor controllers in this class
		//is the same as the motor controllers and their ports in the robotmap class
		leftMotorController = new VictorSP(RobotMap.leftMotor);
		rightMotorController = new VictorSP(RobotMap.rightMotor);
		
		//the robotdrive is a differential drive that uses the motor controllers defined above
		//the DifferentialDrive method is given by WPI
		robotDrive = new DifferentialDrive(leftMotorController, rightMotorController);
		
	}
	
	
	
}
