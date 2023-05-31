// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
  
  private final XboxController auxController;
  private final IntakeSubsystem intake;
  private boolean intakeExtended = false;

  public IntakeCommand(IntakeSubsystem intake, XboxController controller) {
    this.intake = intake;
    this.auxController = controller;
    addRequirements(intake);
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute() {
    double speed = auxController.getLeftTriggerAxis();
    if (speed > .1){
      intake.spinIntake(speed);
    }
    else{
      intake.spinIntake(0);
    }

    if(auxController.getAButtonPressed()){
      if(intakeExtended){
        intakeExtended = false;
        intake.setIntake(intakeExtended);
      }

      else{
        intakeExtended = false;
        intake.setIntake(intakeExtended);
      }
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
