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


public class Clapper extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	
	//declaring the motor controllers, VictorSP is the type of motor controllers we use
	//be sure to import your teleopdrivetrain, see above
	//be sure to import the differentialdrive from wpi library
	private VictorSP clapperMotorController;
	
	//runs the tankdrive
	public void initDefaultCommand() {
		//setDefaultCommand(tankDrive);
		// Set the default command for a subsystem here.
	}
	
    private static Clapper instance;

	//creates drivetrain instance
	public static Clapper getInstance()
	{
		if(instance == null)
			instance = new Clapper();
		return instance;
    }
    
    public void moveClapperMotor(double speed){
        clapperMotorController.set(speed);
    }
	
	
	//constructs drivetrain
	public Clapper()
	{
		
	}
	
	
}