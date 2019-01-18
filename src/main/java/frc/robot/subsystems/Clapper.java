package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.ClapperCommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Clapper extends Subsystem {
	//Put methods for controlling this subsystem here.
	//Call these from Commands.

	
	//declaring the motor controllers, VictorSP is the type of motor controller we use
	//be sure to import your ClapperCommands, see above
	private VictorSP clapperMotorController;
	private ClapperCommands clapperInstance;
	
	//runs the clapper
	public void initDefaultCommand() 
	{
		setDefaultCommand(clapperInstance);
		//clapperInstance is defined below and is an instance of ClapperCommands
	}
	
   	private static Clapper instance;

	//creates Clapper instance
	public static Clapper getInstance()
	{
		if(instance == null)
			instance = new Clapper();
		return instance;
	}
    
    public void moveClapperMotor(double speed)
	{
       	clapperMotorController.set(speed);
   	}
	
	
	//constructs clapper (nothing needed inside)
	public Clapper()
	{
		
	}

	public void initialize()
	{
		//the clapperInstance is the instance of the ClapperCommands
		clapperInstance = ClapperCommands.getInstance();
		
		//makes it so the instance of the clapperMotorController in this class
		//is the same as the clapperMotorContoller and its port in the robotmap class
		clapperMotorController = new VictorSP(RobotMap.clapperMotor);
	}
}