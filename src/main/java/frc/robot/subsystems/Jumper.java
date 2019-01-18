package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.JumperCommands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID.Hand;


public class Jumper extends Subsystem {
	//Put methods for controlling this subsystem here.
	//Call these from Commands.

	
	//declaring the motor controllers, VictorSP is the type of motor controller we use
	//be sure to import your ClapperCommands, see above
	private static DoubleSolenoid jumperSolenoid;
	private JumperCommands jumperInstance;
	
	//runs the clapper
	public void initDefaultCommand() 
	{
		setDefaultCommand(jumperInstance);
		//clapperInstance is defined below and is an instance of ClapperCommands
	}
	
   	private static Jumper instance;

	//creates Clapper instance
	public static Jumper getInstance()
	{
		if(instance == null)
			instance = new Jumper();
		return instance;
	}
    
    public void moveJumper() {
		// move the intakes in and out
		if (OI.getInstance().xbox.getBumperPressed(Hand.kLeft)) {
			// SmartDashboard.putString("DB/String 1", "The intake was called
			// push");
			jumperSolenoid.set(DoubleSolenoid.Value.kForward);
		}

		if (OI.getInstance().xbox.getBumperPressed(Hand.kRight)) {
			// SmartDashboard.putString("DB/String 2", "The intake was called
			// pull");
			jumperSolenoid.set(DoubleSolenoid.Value.kReverse);
		}
		// SmartDashboard.putString("DB/String 8", "encR Raw " +
		// IntakeEncoder.getRaw());

	}
	
	//constructs clapper (nothing needed inside)
	public Jumper()
	{
		
	}

	public void initialize()
	{
		//the clapperInstance is the instance of the ClapperCommands
		jumperInstance = JumperCommands.getInstance();
        
        jumperSolenoid = new DoubleSolenoid(RobotMap.solenoidPort0, RobotMap.solenoidPort1);
		jumperSolenoid.set(DoubleSolenoid.Value.kReverse);

		//makes it so the instance of the clapperMotorController in this class
		//is the same as the clapperMotorContoller and its port in the robotmap class
	}
}