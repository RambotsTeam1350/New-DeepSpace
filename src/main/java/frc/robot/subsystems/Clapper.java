package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.AutoClapper;
import frc.robot.commands.ClapperCommands;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;


public class Clapper extends Subsystem {
	//Put methods for controlling this subsystem here.
	//Call these from Commands.

	
	//declaring the motor controllers, VictorSP is the type of motor controller we use
	//be sure to import your ClapperCommands, see above
	private VictorSP clapperMotorControllerLeft;
	private VictorSP clapperMotorControllerRight;
	private DoubleSolenoid clapperSolenoid;
	private static DifferentialDrive clapperMotors;
	private ClapperCommands clapperInstance;
	private AutoClapper autoInstance;
	
	//runs the clapper
	public void initDefaultCommand() {
		setDefaultCommand(clapperInstance);
		//clapperInstance is defined below and is an instance of ClapperCommands
	}
	
    private static Clapper instance;

	//creates Clapper subsystem instance
	public static Clapper getInstance()
	{
		if(instance == null)
			instance = new Clapper();
		return instance;
    }
	
	//clapperMotors acts as arcade drive but with a fixed rotation of 0
	//this makes it so we can control moving the motors with just one stick
	//and the rotation never changes from 0 because we don't need it to
	//"true" sets squared inputs to true
    public void moveClapperMotors(double speed){
        clapperMotors.arcadeDrive(speed, 0, true);
	}
	
	public void movePistons(){
		if (OI.getInstance().xbox.getBButtonPressed() && clapperSolenoid.get() != Value.kReverse) {
			// SmartDashboard.putString("DB/String 2", "The intake was called
			// pull");
			clapperSolenoid.set(DoubleSolenoid.Value.kReverse);
		}
		if (OI.getInstance().xbox.getBButtonPressed() && clapperSolenoid.get() != Value.kForward) {
			// SmartDashboard.putString("DB/String 2", "The intake was called
			// pull");
			clapperSolenoid.set(DoubleSolenoid.Value.kForward);
		}
	}
	
	
	//constructs clapper (nothing needed inside)
	public Clapper()
	{
		
	}

	public void initialize()
	{
		
		//the clapperInstance is the instance of the ClapperCommands
		clapperInstance = ClapperCommands.getInstance();

		autoInstance = AutoClapper.getInstance();
		
		//connects these to the pwm's
		clapperMotorControllerLeft = new VictorSP(RobotMap.clapperMotorLeft);
		clapperMotorControllerRight = new VictorSP(RobotMap.clapperMotorRight);

		clapperSolenoid = new DoubleSolenoid(RobotMap.solenoidPort4, RobotMap.solenoidPort5);
		clapperSolenoid.set(DoubleSolenoid.Value.kReverse);

		//constructs clapperMotors
		clapperMotors = new DifferentialDrive(clapperMotorControllerLeft, clapperMotorControllerRight);

	}
}