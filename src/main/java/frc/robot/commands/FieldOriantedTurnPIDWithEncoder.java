/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class FieldOriantedTurnPIDWithEncoder extends PIDCommand {
  /**
   * Creates a new FieldOriantedTurnPIDWithEncoder.
   */
  public FieldOriantedTurnPIDWithEncoder(DriveSubsystem m_drive, double targetdistance) {
    super(
        // The controller that the command will use
        new PIDController(DriveConstants.driveP, DriveConstants.driveI, DriveConstants.driveD),
        // This should return the measurement
        () -> m_drive.gettotalDistance(),
        // This should return the setpoint (can also be a constant)
        targetdistance,
        // This uses the output
        output -> {
          m_drive.arcadeDrive(output, 0);
          // Use the output here
        });
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
    getController().setTolerance(DriveConstants.distenceaccuary);
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
