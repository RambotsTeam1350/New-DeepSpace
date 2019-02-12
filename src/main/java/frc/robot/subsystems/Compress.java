package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.CompressorCommands;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;


////THERE IS A SAFETY ON THAT SAYS A PRESSURE SWITCH IS REQUIRED FOR COMPRESSOR TO WORK
//To Me: Try to use the start/stop (its easier to read)

//Cannot name this class comressor or it tries to use FRC's compressor, not WPI's
public class Compress extends Subsystem {
	
	//initialize the Compressor object
    private Compressor comp;

    //creates instance variable of CompressorCommands
    private CompressorCommands compressorInstance; 
    
    //CompressorCommands is now the default command
    public void initDefaultCommand() {
        setDefaultCommand(compressorInstance);
    }

    //creates instance of Compress subsystem
    private static Compress instance;
	public static Compress getInstance()
	{
		if(instance == null)
			instance = new Compress();
		return instance;
    }
    
    //empty constructor
    public Compress(){
    }
    
    //allows for enabling and disabling of compressor
    //if the specified button is pressed and 
    public void setState(boolean buttonPressed){
        if (buttonPressed && !isEnabled()) 
            comp.start();
        else if (buttonPressed)
            comp.stop();
    }

    // return compressor state
    public boolean isEnabled(){
		return comp.enabled();
    }
    
   //blindly change the state to !state
    public void toggle(){
		setState(!isEnabled());
    }

    public void initialize()
	{
		//the compressorInstance is the instance of the CompressorCommands
		compressorInstance = CompressorCommands.getInstance();
        
        //binds the compressor to the PWM specified in RobotMap
        comp = new Compressor(RobotMap.Compressor);

        //set the PCM in closed loop control mode
        comp.setClosedLoopControl(true);
	}
}