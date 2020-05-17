/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.otonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.SneakyTrajectory;
import frc.robot.commands.HopperCommands;
import frc.robot.commands.RunIntake;
import frc.robot.commands.RunShooter;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Left7Cell extends SequentialCommandGroup {
  /**
   * Creates a new Left7Cell.
   */
  public Left7Cell(SneakyTrajectory s_trajectory, Shooter shooter, intake Intake
  , HopperSubsystem hopper, DriveSubsystem drive) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new RunShooter(shooter, 0.75).withTimeout(0.75),
    new HopperCommands(hopper, 0.5).raceWith(new RunShooter(shooter, 0.75)).withTimeout(2),
    s_trajectory.getRamsete(s_trajectory.left7cell_0).raceWith(new HopperCommands(hopper, 0.5))
    .raceWith(new RunIntake(Intake, 0.75)).andThen(() -> drive.arcadeDrive(0, 0)),
    s_trajectory.getRamsete(s_trajectory.left7cell_1).andThen(() -> drive.arcadeDrive(0, 0)),
    new RunShooter(shooter, 0.75).withTimeout(0.75),
    new HopperCommands(hopper, 0.5).raceWith(new RunShooter(shooter, 0.75)).withTimeout(2));
  }
}
