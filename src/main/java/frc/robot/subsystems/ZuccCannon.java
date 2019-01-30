package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.commands.ZuccCannonCommands;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class ZuccCannon extends Subsystem
{
    private VictorSP zuccLeft;
    private VictorSP zuccRight;
    private VictorSP zuccTop;
    private DifferentialDrive zuccDifferential;
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

    public void moveZuccCannon(double speed)
    {
        zuccDifferential.arcadeDrive(speed, 0, false);
    }

    public void moveTopZucc(double speed)
    {
        zuccTop.set(speed);
    }

    public void intitialize()
    {
        zuccComInstance = ZuccCannonCommands.getInstance();

        zuccRight = new VictorSP(RobotMap.zuccCannonRight);
        zuccLeft = new VictorSP(RobotMap.zuccCannonLeft);
        zuccTop = new VictorSP(RobotMap.zuccCannonTop);
        zuccDifferential = new DifferentialDrive(zuccLeft, zuccRight);
    }

}
