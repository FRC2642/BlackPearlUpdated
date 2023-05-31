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

  private CANSparkMax MagBeltMotor = new CANSparkMax(13, MotorType.kBrushless);

  private DigitalInput UpperLightSensor = new DigitalInput(0);
  private DigitalInput LowerLightSensor = new DigitalInput(1);

  public MagazineSubsystem() {}

  public void setMagSpeed(double speed){
    MagBeltMotor.set(speed);
  }

  public boolean isOneBallThere(){
    return !areTwoBallsThere() && (UpperLightSensor.get() || LowerLightSensor.get());
  }

  public boolean areTwoBallsThere(){
    return UpperLightSensor.get() && LowerLightSensor.get();
  }

  @Override
  public void periodic() {}
}
