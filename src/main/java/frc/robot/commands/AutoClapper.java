package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.Clapper;


public class AutoClapper extends Command {
	
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

    private boolean pressed = false;
    private boolean isRunning = false;

    //gets the y value of the left stick on the xbox controller
	private void aPressed(){
        if (OI.getInstance().xbox.getAButtonPressed() && !isRunning)
		    pressed = true;
	}

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() 
	{
        aPressed();
        isRunning = true;
        while (Robot.limitSwitch1.get() && pressed){
		    Clapper.getInstance().moveClapperMotor(1.0);	
        }
        Clapper.getInstance().moveClapperMotor(0);
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