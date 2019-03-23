package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Clapper;


public class AutoClapper extends Command {
	
	//creates instance of AutoClapper
	private static AutoClapper instance;
	public static AutoClapper getInstance()
	{
		if(instance == null)
			instance = new AutoClapper();
		return instance;
	}
	
	public AutoClapper() 
	{
		//an instance of the Clapper subsystem must be created before this constructor can be used
		requires(Clapper.getInstance());
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	//initializes the pressed and isRunning variables and sets them to false
    private boolean pressed = false;
    private boolean isRunning = false;

    //gets the y value of the left stick on the xbox controller
	private void aPressed()
	{
		//if the A button is pressed and the clapper is not already moving
		//then the variable pressed = true
		if (OI.getInstance().xbox.getAButtonPressed() && !isRunning)
		    pressed = true;
	}

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() 
	{
		//checks to see if the A button is pressed and sets the variable accordingly
		aPressed();
		
		//sets isRunning to true, will later be turned back to false
		//necessary to intitialize as false, change to true here, and then change back to false
		//because it needs to be false for the aPressed() method, but needs to be true so aPressed only happens once
		//and then the clapper is no longer moving at the end of execute() and aPressed() can be called again
		isRunning = true;
		
		//while the limit switch is not pressed and the value of pressed is true
		//move the clapper at full speed
        while (Robot.limitSwitch1.get() && pressed){
		    Clapper.getInstance().moveClapperMotors();	
		}
		//after the switch is hit, stop the motors
		Clapper.getInstance().moveClapperMotors();
		
		//set variables back to false
        isRunning = false;
        pressed = false;
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