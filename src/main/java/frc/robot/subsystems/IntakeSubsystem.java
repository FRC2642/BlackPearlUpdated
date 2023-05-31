// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class IntakeSubsystem extends SubsystemBase {

  public CANSparkMax IntakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_ID, MotorType.kBrushless);
  public CANSparkMax IntakeBigWheel = new CANSparkMax(Constants.INTAKE_BIGWHEEL_ID, MotorType.kBrushless);

  Solenoid RightIntakePiston = new Solenoid(PneumaticsModuleType.REVPH, 0);

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {}

  public void setIntake(Boolean IntakeStatus){
    RightIntakePiston.set(IntakeStatus);
  }

  /* 
  public void IntakePistonExtend() {
    RightIntakePiston.set(true);
  }

  public void IntakePistonRetract(){
    RightIntakePiston.set(false);
  }
  */
  public void spinIntake( double speed){
    IntakeBigWheel.set(speed);
    IntakeMotor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    /*
    SmartDashboard.putNumber("RT Pressed", RobotContainer.driveController.getRightTriggerAxis());
    SmartDashboard.putBoolean("A Pressed", RobotContainer.driveController.getAButton());*/
  }
}
