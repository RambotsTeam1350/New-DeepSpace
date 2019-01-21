package frc.robot.util;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class Vision extends Thread {

	private UsbCamera camera;

	@Override
	public void run() {

        // Get the UsbCamera from CameraServer
		camera = CameraServer.getInstance().startAutomaticCapture("Front", 0);
		// Set the resolution
		camera.setResolution(640, 480);
		// int dashData0 = (int) SmartDashboard.getNumber("DB/Slider
		// 0",
		// 0.0);
		// camera.setExposureManual(dashData0);
		// int dashData0 = (int) SmartDashboard.getNumber("DB/Slider
		// 0",
		// 0.0);
        camera.setExposureManual(80);

	}

}