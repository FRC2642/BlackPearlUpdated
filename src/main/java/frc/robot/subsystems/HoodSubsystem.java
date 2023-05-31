// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HoodSubsystem extends SubsystemBase {
  private PneumaticHub pneumatics = new PneumaticHub(1);
  private Solenoid TurretHood;
  
  public HoodSubsystem() {
    pneumatics.enableCompressorAnalog(80, 100);
    TurretHood = pneumatics.makeSolenoid(15);
  }

  public void setHood(boolean hoodStatus){
    TurretHood.set(hoodStatus);
  }

  @Override
  public void periodic() {}
}
