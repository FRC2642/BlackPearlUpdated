// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class MagazineSubsystem extends SubsystemBase {
  /** Creates a new MagazineSubsystem. */

  CANSparkMax MagBeltMotor = new CANSparkMax(13, MotorType.kBrushless);

  DigitalInput UpperLightSensor = new DigitalInput(0);
  DigitalInput LowerLightSensor = new DigitalInput(1);

  public MagazineSubsystem() {}

  
  public void MagBeltForward(){
    MagBeltMotor.set(.5);
  }

  public void MagBeltBackward(){
    MagBeltMotor.set(-.5);
  }
  
  public void StopMagBelt(){
    MagBeltMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
