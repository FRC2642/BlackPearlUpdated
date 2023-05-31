// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {

  private final DriveSubsystem drive;
  private final XboxController driveController;

  public DriveCommand(DriveSubsystem drive, XboxController Controller) {
    this.drive = drive;
    this.driveController = Controller;
    addRequirements(drive);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    double ForwardSpeed = driveController.getLeftX();
    double TurnSpeed = driveController.getLeftY();

    drive.drive(ForwardSpeed*.4, TurnSpeed*.4);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
