package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ClapperCommands;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;


public class Clapper extends Subsystem {
	//Put methods for controlling this subsystem here.
	//Call these from Commands.

	
	//declaring the motor controllers and double solenoid
	//be sure to import your ClapperCommands, see above
	private VictorSP clapperMotorControllerLeft;
	private DoubleSolenoid clapperSolenoid;

	//the left and right clapper motor controllers defined above
	//will work together in a differential drive

	//creates instance of the ClapperCommands class
	private ClapperCommands clapperInstance;


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
	
	private static boolean isSleeping = false;

	//clapperMotors acts as arcade drive but with a fixed rotation of 0
	//this makes it so we can control moving the motors with just one stick
	//and the rotation never changes from 0 because we don't need it to.
	//"false" sets squared inputs to false
	public void moveClapperMotors()
	{
		double speed = OI.getInstance().xbox.getY(Hand.kLeft);
			System.out.println(speed);
			if (speed < 0.1 && speed > -0.1){
				speed = -0.05;
			}
			clapperMotorControllerLeft.set(-speed);
	}
	
	private boolean clapperOut;

	public void movePistons()
	{
/*
		Thread clapperPistonT = new Thread(() -> {
			active = true;
			clapperSolenoid.set(Value.kReverse);
			try{
				System.out.println("sleeping");
				Thread.sleep(1000);
			}catch (Exception e){
				System.out.println(e);
			}
			clapperSolenoid.set(Value.kForward);
			
		});
		if (!active){
			clapperPistonT.start();
		}
		*/
		//if the B button is pressed and the piston is not in the kReverse state, move the piston into that state
		//compresses piston
		
		if (clapperOut){
			clapperSolenoid.set(Value.kForward);
			clapperOut = false;
		}
		//if the B button is pressed and the piston is not in the kForward state, move the piston into that state
		//extends piston
		else if (!clapperOut){
			clapperSolenoid.set(Value.kReverse);
			clapperOut = true;
		
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
		
		//connects these to the pwm's
		clapperMotorControllerLeft = new VictorSP(RobotMap.clapperMotorLeft);
		clapperSolenoid = new DoubleSolenoid(RobotMap.solenoidPort4, RobotMap.solenoidPort5);

		//sets the state of clapperSolenoid back to kReverse as its default
		clapperSolenoid.set(DoubleSolenoid.Value.kForward);

		clapperOut = false;
	}
}