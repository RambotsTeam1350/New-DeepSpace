package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.ZuccCannonCommands;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;

public class ZuccCannon extends Subsystem
{
    //defines motor controllers
    private VictorSP zuccBottom;
    private VictorSP zuccTop;

    //instance variable of ZuccCannonCommands
    private ZuccCannonCommands zuccComInstance;

    //creates instance of ZuccCannon subsystem
    private static ZuccCannon instance;
    public static ZuccCannon getInstance()
    {
        if (instance == null)
            instance = new ZuccCannon();
        return instance;
    }

    public ZuccCannon()
    {
        //empty
    }

    //default command is ZuccCannonCommands, use its instance variable here
    public void initDefaultCommand()
    {
        setDefaultCommand(zuccComInstance);
    }

    //speed of bottom motor retrieved and set
    public void moveBottomZucc(boolean speed)
    {
        if (speed)
            zuccBottom.set(1.0);
        else
            zuccBottom.set(0);
    }

    //speed of top motor retrieved and set
    public void moveTopZucc(boolean speed)
    {
        if (speed)
            zuccTop.set(1.0);
        else
            zuccTop.set(0);
    }

    public void initialize()
    {
        //gets instance of ZuccCannonCommands
        zuccComInstance = ZuccCannonCommands.getInstance();

        //defines the motors as VictorSPs and binds them to the ports specified in RobotMap
        zuccBottom = new VictorSP(RobotMap.zuccCannonBottom);
        zuccTop = new VictorSP(RobotMap.zuccCannonTop);
    }

}
