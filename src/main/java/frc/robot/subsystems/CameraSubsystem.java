// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.vision.VisionThread;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CameraSubsystem extends SubsystemBase {
  /** Creates a new CameraSubsystem. */
  private UsbCamera Camera;
  private VisionThread visionThread;
  
  // private VisionThread VisionThread;
  public CameraSubsystem() {
    
    Camera = CameraServer.startAutomaticCapture(1);
    Camera.setFPS(10);
    Camera.setResolution(320, 240);
    Camera.setConnectionStrategy(ConnectionStrategy.kKeepOpen);

    visionThread = new VisionThread(Camera, null, null);

  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
