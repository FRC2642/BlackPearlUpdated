// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.led.Animation;
import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.CANdleConfiguration;
import com.ctre.phoenix.led.LarsonAnimation;
import com.ctre.phoenix.led.RainbowAnimation;
import com.ctre.phoenix.led.CANdle.LEDStripType;
import com.ctre.phoenix.led.CANdle.VBatOutputMode;
import com.ctre.phoenix.led.LarsonAnimation.BounceMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShootingSubsystem extends SubsystemBase {
  
  private static ShootingSubsystem instance;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
  public double targetVelocity;
  public double range = 75;

  //LEDs
  private final CANdle candle = new CANdle(17, "rio");
  private final int ledCount = 68;
  private final Animation rainbowAnimation = new RainbowAnimation(.7, .8, ledCount);
  private final Animation blueAllianceLarsAnimation = new LarsonAnimation(0, 0, 255, 0, .99, ledCount, BounceMode.Back, 7);
  private final Animation redAllianceLarsAnimation = new LarsonAnimation(255, 0, 0, 0, .99, ledCount, BounceMode.Back, 7);

  //Motors and Encoders
  private CANSparkMax shooter;
  private SparkMaxPIDController pidController;
  private RelativeEncoder encoder;

  public static boolean isCloseToSetRPM(){
    return Math.abs(instance.targetVelocity - instance.getShooterSpeed()) < instance.range;
  }
  public ShootingSubsystem() {
    instance = this;
    shooter = new CANSparkMax(Constants.TURRET_SHOOTER_ID, MotorType.kBrushless);
    this.encoder = shooter.getEncoder();
    pidController = shooter.getPIDController();
    kP = .003;
    kI = .00000009;
    kD = 1.2;
    kIz = 0;
    kFF = .00009;
    kMaxOutput = .84;
    kMinOutput = .0;
    maxRPM = 5600;
    pidController.setP(kP);
    pidController.setI(kI);
    pidController.setD(kD);
    pidController.setIZone(kIz);
    pidController.setFF(kFF);
    pidController.setOutputRange(kMinOutput, kMaxOutput);

    CANdleConfiguration configAll = new CANdleConfiguration();
    configAll.statusLedOffWhenActive = true;
    configAll.disableWhenLOS = false; 
    configAll.stripType = LEDStripType.RGB;
    configAll.brightnessScalar = .1;
    configAll.vBatOutputMode = VBatOutputMode.Modulated;
    candle.configAllSettings(configAll, 100);
      
   }

   public void setSpeed(double speed){
    pidController.setReference(speed, CANSparkMax.ControlType.kVelocity);
    targetVelocity = speed;
   }

   public void maxSpeed(){
    shooter.set(1);
   }

   public double getShooterSpeed(){
    return encoder.getVelocity();
   }

   public void stop(){
    shooter.stopMotor();
   }

   public boolean getAuxRightTrigger(){
    double rtrigger = RobotContainer.auxController.getRightTriggerAxis();
    return (rtrigger > .5);
   }

   public void setRainbowAnimation(){
    candle.animate(rainbowAnimation);
   }
  @Override
  public void periodic() {
    if (isCloseToSetRPM()){
      candle.setLEDs(0, 255, 0);
    }

    else if (DriverStation.getAlliance() == Alliance.Blue){
      candle.animate(blueAllianceLarsAnimation);
    }
    else{
      candle.animate(redAllianceLarsAnimation);
    }
  }
}
