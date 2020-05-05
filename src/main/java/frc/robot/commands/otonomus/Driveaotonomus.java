/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.otonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutononomousDrive;
import frc.robot.commands.HopperCommands;
import frc.robot.commands.RunShooter;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Driveaotonomus extends SequentialCommandGroup {
  /**
   * Creates a new Driveaotonomus.
   */
  public Driveaotonomus(LiftSubsystem m_lift, ArmSubsystem m_arm, Shooter m_shooter, DriveSubsystem m_drive,
      HopperSubsystem m_hooper) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new RunShooter(m_shooter, 0.8).withTimeout(0.75),
        new RunShooter(m_shooter, 0.8).raceWith(new HopperCommands(m_hooper, 0.8)).withTimeout(2.5),
        new AutononomousDrive(m_drive, -1, 300).raceWith(new HopperCommands(m_hooper, 0.8))
            .raceWith(new RunShooter(m_shooter, -0.3)),
        new AutononomousDrive(m_drive, 1, 300), new RunShooter(m_shooter, 0.8).withTimeout(0.75),
        new RunShooter(m_shooter, 0.8).raceWith(new HopperCommands(m_hooper, 0.8).withTimeout(2.5)));
  }
}
