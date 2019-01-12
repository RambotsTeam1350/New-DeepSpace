/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;


public class TeleOpDriveTrain extends Command {
	
	private boolean squaredInputs;
	
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
		squaredInputs = false;
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
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
