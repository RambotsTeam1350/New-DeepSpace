package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.ZuccCannonCommands;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;

public class ZuccCannon extends Subsystem
{
    private VictorSP zuccBottom;
    private VictorSP zuccTop;
    private ZuccCannonCommands zuccComInstance;
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

    public void initDefaultCommand()
    {
        setDefaultCommand(zuccComInstance);
    }

    public void moveBottomZucc(double speed)
    {
        zuccBottom.set(speed);
    }

    public void moveTopZucc(double speed)
    {
        zuccTop.set(speed);
    }

    public void intitialize()
    {
        zuccComInstance = ZuccCannonCommands.getInstance();

        zuccBottom = new VictorSP(RobotMap.zuccCannonBottom);
        zuccTop = new VictorSP(RobotMap.zuccCannonTop);
    }

}
