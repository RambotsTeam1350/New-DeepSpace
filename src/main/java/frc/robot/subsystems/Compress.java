package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.CompressorCommands;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;


////THERE IS A SAFETY ON THAT SAYS A PRESSURE SWITCH IS REQUIRED FOR COMPRESSOR TO WORK
//To Me: Try to use the start/stop (its easier to read)

//Cannot name this class comressor or it tries to use FRC's compressor, not WPI's
public class Compress extends Subsystem {
	
	
    private Compressor comp; // initialize compressor
    private CompressorCommands compressorInstance;
    
    public void initDefaultCommand() {
        setDefaultCommand(compressorInstance);
    }

    private static Compress instance;

	//creates Compress instance
	public static Compress getInstance()
	{
		if(instance == null)
			instance = new Compress();
		return instance;
    }
    
    public Compress(){
    }
    
    /** enable/disable compressor */
    public void setState(boolean buttonPressed){
        if (buttonPressed && !isEnabled()) 
            comp.start();
        else if (buttonPressed)
            comp.stop();
    }

    /** return compressor state */
    public boolean isEnabled(){
		return comp.enabled();
    }
    
   /** blindly change the state to !state */
    public void toggle(){
		setState(!isEnabled());
    }

    public void initialize()
	{
		//the clapperInstance is the instance of the ClapperCommands
		compressorInstance = CompressorCommands.getInstance();
        
        comp = new Compressor(RobotMap.Compressor);

        comp.setClosedLoopControl(true);

		//makes it so the instance of the clapperMotorController in this class
		//is the same as the clapperMotorContoller and its port in the robotmap class
	}
}