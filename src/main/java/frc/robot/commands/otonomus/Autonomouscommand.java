/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.otonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutononomousDrive;
import frc.robot.commands.Movearm;
import frc.robot.commands.RunShooter;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Autonomouscommand extends SequentialCommandGroup {
  /**
   * Creates a new Autonomouscommand.
   */
  public Autonomouscommand(ArmSubsystem m_arm, Shooter m_shooter, DriveSubsystem m_drive) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new Movearm(m_arm, 0.5), new RunShooter(m_shooter, 1).withTimeout(3),
        new AutononomousDrive(m_drive, 0.8, 300).withTimeout(3));
  }
}
