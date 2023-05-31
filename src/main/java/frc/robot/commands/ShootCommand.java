// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShootingSubsystem;

public class ShootCommand extends CommandBase {

  ShootingSubsystem shooter;
  double rpm;

  public ShootCommand(ShootingSubsystem shooter, double rpm) {
    this.shooter = shooter;
    this.rpm = rpm;
    addRequirements(shooter);
  }

  
  @Override
  public void initialize() {}

  @Override
  public void execute() {
    shooter.setSpeed(rpm);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
