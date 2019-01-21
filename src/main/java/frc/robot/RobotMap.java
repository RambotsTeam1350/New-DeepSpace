/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	
	
	//Motor controllers, number = PWM port on bot
	public static final int rightMotor = 0;
  public static final int leftMotor = 1; 
	public static final int clapperMotorLeft = 2; 
	public static final int clapperMotorRight = 3;
	
	
	//Joysticks, number = driver station port
	public static final int rightJoystick = 0;
	public static final int leftJoystick = 1;
	public static final int xboxController = 2;
	
	public static final int xboxLeftJoystickY = 1;
  public static final int xboxRightJoystickY = 5;
  

  //Compressor
	public static final int Compressor = 0;

	public static final int xboxCompressorButtonOn = 1;
	public static final int xboxCompressorButtonOff = 2;
	
	
	//Solenoids
	public static final int solenoidPort0 = 0;
	public static final int solenoidPort1 = 1;
	public static final int solenoidPort2 = 2;
  public static final int solenoidPort3 = 3;
  

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
