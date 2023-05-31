// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.MagazineSubsystem;

public class MoveMagCommand extends CommandBase {
  
  private final MagazineSubsystem magazine;
  private final XboxController auxController;
  public MoveMagCommand(MagazineSubsystem magazine, XboxController controller) {
    
    this.magazine = magazine; 
    this.auxController = controller;
    addRequirements(magazine);
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute() {
    if (auxController.getYButton()){
      magazine.setMagSpeed(-0.5);
    }
    else if (auxController.getBButton()){
      magazine.setMagSpeed(0.5);
    }
    else{
      magazine.setMagSpeed(0.0);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
