// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.commands.DriveCommand;
import frc.robot.commands.HoodDownCommand;
import frc.robot.commands.HoodUpCommand;
import frc.robot.commands.IntakePistonExtend;
import frc.robot.commands.IntakePistonRetract;
import frc.robot.commands.RunMagBackward;
import frc.robot.commands.RunMagForward;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.SpinIntakeWheels;
import frc.robot.commands.StopTheMag;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HoodSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.MagazineSubsystem;
import frc.robot.subsystems.ShootingSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  //Stuff For DriveTrain
  public DriveSubsystem driveSubsystem = new DriveSubsystem();
  private DriveCommand driveCommand = new DriveCommand(driveSubsystem);

  //Shooter
  public ShootingSubsystem shootingSubsystem = new ShootingSubsystem();

  //Stuff for the Intake
  public IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  public IntakePistonExtend extendCommand = new IntakePistonExtend(intakeSubsystem);
  public IntakePistonRetract retractCommand = new IntakePistonRetract(intakeSubsystem);
  public SpinIntakeWheels WheelCommand = new SpinIntakeWheels(intakeSubsystem);

  //Stuff for the Magazine Belt
  public MagazineSubsystem magazineSubsystem = new MagazineSubsystem();
  public RunMagForward magForward = new RunMagForward(magazineSubsystem);
  public RunMagBackward magBackward = new RunMagBackward(magazineSubsystem);
  public StopTheMag StopMag = new StopTheMag(magazineSubsystem);

  //Stuff for the Hood
  public HoodSubsystem hoodSubsystem = new HoodSubsystem();
  public HoodDownCommand hoodDown = new HoodDownCommand(hoodSubsystem);
  public HoodUpCommand HoodUp = new HoodUpCommand(hoodSubsystem);

  //Stuff for the Camera
  public CameraSubsystem cameraSubsystem = new CameraSubsystem();


  //Controller & Buttons
  public static XboxController driveController = new XboxController(0);
  public static XboxController auxController = new XboxController(1);

  Trigger auxRightTrigger = new Trigger(shootingSubsystem::getAuxRightTrigger);
  Trigger aButton = new JoystickButton(auxController, 1);
  Trigger bButton = new JoystickButton(auxController, 2);
  Trigger yButton = new JoystickButton(auxController, 4);
  Trigger xButton = new JoystickButton(auxController, 3);
  Trigger RightBumper = new JoystickButton(auxController, 6);
  Trigger LeftBumper = new JoystickButton(auxController, 5);
 
  

  // Replace with CommandPS4Controller or CommandJoystick if needed
  /*  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort); */

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    driveSubsystem.setDefaultCommand(new DriveCommand(driveSubsystem));
    intakeSubsystem.setDefaultCommand(new SpinIntakeWheels(intakeSubsystem));
    // Configure the trigger bindings
    configureBindings();

    shootingSubsystem.setDefaultCommand(
      new RunCommand(
        () ->
        shootingSubsystem.stop(), shootingSubsystem
      )
    );
    
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`

    /*new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem)); */

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

    
    
    LeftBumper.onTrue(retractCommand);
    RightBumper.onTrue(extendCommand);
    

    bButton.whileTrue(magForward);//If the 'B' button is pressed the mag goes up
    bButton.whileFalse(StopMag);//If not the mag doesn't move

    yButton.whileTrue(magBackward);//If the 'Y' button is pressed the mag goes down;
    yButton.whileFalse(StopMag);//If not the mag doesn't move

    //xButton.toggleOnFalse(hoodDown);//if the 'X' button isn't pressed the intake pistons retract
    
    xButton.onTrue(hoodDown);//if the 'A' is pressed the intake pistons extend
    aButton.onTrue(HoodUp);

    new POVButton(auxController, 0).whileTrue(new RunCommand(
      () -> shootingSubsystem.setSpeed(900), shootingSubsystem
    ));

    new POVButton(auxController, 90).whileTrue(new RunCommand(
      () -> shootingSubsystem.setSpeed(2000), shootingSubsystem
    ));

    new POVButton(auxController, 180).whileTrue(new RunCommand(
      () -> shootingSubsystem.setSpeed(1500), shootingSubsystem
    ));

    new POVButton(auxController, 270).whileTrue(new RunCommand(
      () -> shootingSubsystem.setSpeed(2500), shootingSubsystem
    ));


    auxRightTrigger.whileTrue(new RunCommand(
      () -> shootingSubsystem.setSpeed(5600), shootingSubsystem
    ));

    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  /*  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new DriveCommand(drive);
  } */
}
