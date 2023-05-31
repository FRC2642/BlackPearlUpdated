// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {

  private WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.FRONT_LEFT_TALON_ID); 
  private WPI_TalonFX backLeft = new WPI_TalonFX(Constants.BACK_LEFT_TALON_ID); 
  private WPI_TalonFX frontRight = new WPI_TalonFX(Constants.FRONT_RIGHT_TALON_ID);
  private WPI_TalonFX backRight = new WPI_TalonFX(Constants.BACK_RIGHT_TALON_ID);

  private MotorControllerGroup RightMotors = new MotorControllerGroup(frontRight, backRight);
  private MotorControllerGroup LeftMotors = new MotorControllerGroup(frontLeft, backLeft);

  private DifferentialDrive DifferentialDrive = new DifferentialDrive(LeftMotors, RightMotors);

  public DriveSubsystem(){}

  public void drive(double ForwardSpeed, double TurnSpeed){
    DifferentialDrive.arcadeDrive(ForwardSpeed, TurnSpeed);
  }

  @Override
  public void periodic() {}

}
