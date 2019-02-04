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

	
	//declaring the motor controllers and double solenoid
	//be sure to import your ClapperCommands, see above
	private VictorSP clapperMotorControllerLeft;
	private VictorSP clapperMotorControllerRight;
	private DoubleSolenoid clapperSolenoid;

	//the left and right clapper motor controllers defined above
	//will work together in a differential drive
	private static DifferentialDrive clapperMotors;

	//creates instance of the ClapperCommands class
	private ClapperCommands clapperInstance;

	//creates instance variable of the AutoClapper class
	private AutoClapper autoInstance;


	public void initDefaultCommand() {
		//uses the commands in the ClapperCommands command class
		setDefaultCommand(clapperInstance);
	}
	
	//creates instance of the Clapper subsystem
    private static Clapper instance;
	public static Clapper getInstance()
	{
		if(instance == null)
			instance = new Clapper();
		return instance;
    }
	
	//clapperMotors acts as arcade drive but with a fixed rotation of 0
	//this makes it so we can control moving the motors with just one stick
	//and the rotation never changes from 0 because we don't need it to.
	//"false" sets squared inputs to false
	public void moveClapperMotors(double speed)
	{
        clapperMotors.arcadeDrive(speed, 0, false);
	}
	
	public void movePistons()
	{
		//if the B button is pressed and the piston is not in the kReverse state, move the piston into that state
		//compresses piston
		if (OI.getInstance().xbox.getBButtonPressed() && clapperSolenoid.get() != Value.kReverse) 
			clapperSolenoid.set(DoubleSolenoid.Value.kReverse);

		//if the B button is pressed and the piston is not in the kForward state, move the piston into that state
		//extends piston
		if (OI.getInstance().xbox.getBButtonPressed() && clapperSolenoid.get() != Value.kForward) 
			clapperSolenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	
	//constructs clapper (nothing needed inside)
	public Clapper()
	{
		
	}

	public void initialize()
	{
		
		//the clapperInstance is the instance of the ClapperCommands
		clapperInstance = ClapperCommands.getInstance();

		//gets instance of AutoClapper
		autoInstance = AutoClapper.getInstance();
		
		//connects these to the pwm's
		clapperMotorControllerLeft = new VictorSP(RobotMap.clapperMotorLeft);
		clapperMotorControllerRight = new VictorSP(RobotMap.clapperMotorRight);
		clapperSolenoid = new DoubleSolenoid(RobotMap.solenoidPort4, RobotMap.solenoidPort5);

		//sets the state of clapperSolenoid back to kReverse as its default
		clapperSolenoid.set(DoubleSolenoid.Value.kReverse);

		//constructs clapperMotors as a differential drive
		clapperMotors = new DifferentialDrive(clapperMotorControllerLeft, clapperMotorControllerRight);

	}
}