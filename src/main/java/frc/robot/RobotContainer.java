/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.JoystickConstants;
import frc.robot.commands.*;
import frc.robot.commands.otonomus.Autonomouscommand;
import frc.robot.commands.otonomus.Driveaotonomus;
import frc.robot.commands.otonomus.RightAuto;
//import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Shooter m_shooter = new Shooter();
  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final DriveSubsystem m_drive = new DriveSubsystem();
  public Joystick m_driverController = new Joystick(JoystickConstants.driverControllerPort);
  private final ClimbSubsystem m_climb = new ClimbSubsystem();
  public final ArmSubsystem m_arm = new ArmSubsystem();
  private final HopperSubsystem m_hooper = new HopperSubsystem();
  private final LiftSubsystem m_lift = new LiftSubsystem();

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_drive.setDefaultCommand(new joystickDrive(m_drive,() -> -m_driverController.getRawAxis(1),() -> m_driverController.getRawAxis(0)));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    new JoystickButton(m_driverController, 3).whileHeld(new RunShooter(m_shooter, 0.8));
    new JoystickButton(m_driverController, 4).whileHeld(new RunShooter(m_shooter, -0.8));
    new JoystickButton(m_driverController, 5).whenPressed(new ClimbOpen(m_climb));  
    new JoystickButton(m_driverController, 6).whenPressed(new ClimbClose(m_climb));
    new JoystickButton(m_driverController, 7).toggleWhenPressed(new CompressorToggle(m_climb));  
    new JoystickButton(m_driverController, 10).whileHeld(new Movearm(m_arm, 1));
    new JoystickButton(m_driverController, 11).whileHeld(new Movearm(m_arm, -1));
    new JoystickButton(m_driverController, 12).whileHeld(new HopperCommands(m_hooper, 0.5));
    new JoystickButton(m_driverController, 13).whileHeld(new HopperCommands(m_hooper, -0.5));
    new POVButton(m_driverController, 0).whileHeld(new FieldDrientedTurnPID(m_drive, 0)) ;
    new POVButton(m_driverController, 90).whileHeld(new FieldDrientedTurnPID(m_drive, 90)) ;
    new POVButton(m_driverController, 270).whileHeld(new FieldDrientedTurnPID(m_drive, 270)) ;
    new POVButton(m_driverController, 360).whileHeld(new FieldDrientedTurnPID(m_drive, 360)) ;
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(Integer auto) {
    // An ExampleCommand will run in autonomous
    switch(auto){
      case 1:
      return new Autonomouscommand(m_arm, m_shooter, m_drive);
      case 2:
      return new Driveaotonomus(m_lift, m_arm, m_shooter, m_drive, m_hooper);
      case 3:
      return new RightAuto();
      default:
      return null;
    }
  }
}
