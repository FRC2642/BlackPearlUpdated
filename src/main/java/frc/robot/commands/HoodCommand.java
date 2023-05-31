// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HoodSubsystem;

public class HoodCommand extends CommandBase {
  
  private final HoodSubsystem hood;
  private final XboxController auxController;

  private boolean hoodUp = false;
  public HoodCommand(HoodSubsystem hood, XboxController controller) {
    this.hood = hood;
    this.auxController = controller;
    addRequirements(hood);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(auxController.getXButtonPressed()){
      if (hoodUp){
        hoodUp = false;
        hood.setHood(hoodUp);
      }

      else{
        hoodUp = true;
        hood.setHood(hoodUp);
      }
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
