package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.JumperCommands;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;


public class Jumper extends Subsystem {

	//declares instance variables for the two solenoids
	private static DoubleSolenoid jumperSolenoid1;
	private static DoubleSolenoid jumperSolenoid2;

	//creates instance variable of JumperCommands
	private JumperCommands jumperInstance;
	
	//JumperCommands is the default command class
	public void initDefaultCommand() 
	{
		setDefaultCommand(jumperInstance);
	}
	
	//creates instance of Jumper subsystem
   	private static Jumper instance;
	public static Jumper getInstance()
	{
		if(instance == null){
			instance = new Jumper();
		}
		return instance;
	}
	
	//extends or compresses jumper piston1 with left bumper of xbox controller
	public void moveJumper1() 
	{
		if (OI.getInstance().xbox.getBumperPressed(Hand.kLeft) && jumperSolenoid1.get() != Value.kForward)
			jumperSolenoid1.set(DoubleSolenoid.Value.kForward);
		
		if (OI.getInstance().xbox.getBumperPressed(Hand.kLeft) && jumperSolenoid1.get() != Value.kReverse)
			jumperSolenoid1.set(DoubleSolenoid.Value.kReverse);
	}

	//extends and compresses jumper piston2 with right bumper of xbox controller
	public void moveJumper2() 
	{
		if (OI.getInstance().xbox.getBumperPressed(Hand.kRight) && jumperSolenoid2.get() != Value.kForward)
			jumperSolenoid2.set(DoubleSolenoid.Value.kForward);
		
		if (OI.getInstance().xbox.getBumperPressed(Hand.kRight) && jumperSolenoid2.get() != Value.kReverse) 
			jumperSolenoid2.set(DoubleSolenoid.Value.kReverse);
	}
	
	//constructs Jumper, nothing needed inside
	public Jumper()
	{
		
	}

	public void initialize()
	{
		//instnace of JumperCommands
		jumperInstance = JumperCommands.getInstance();
		
		//jumper solenoid1 is bound to port 1
		jumperSolenoid1 = new DoubleSolenoid(RobotMap.solenoidPort1,RobotMap.solenoidPort1);

		//default value of piston is reverse (compressed)
        jumperSolenoid1.set(DoubleSolenoid.Value.kReverse);

		//jumper solenoid2 is bound to port 2
		jumperSolenoid2 = new DoubleSolenoid(RobotMap.solenoidPort2, RobotMap.solenoidPort3);

		//default value of piston is reverse (compressed)
		jumperSolenoid2.set(DoubleSolenoid.Value.kReverse);
	}
}