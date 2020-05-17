/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Add your docs here.
 */
public class SneakyTrajectory {
    public Trajectory centerRight6cell_0,centerRight6cell_1;
    public Trajectory centerleft5cell_0,centerleft5cell_1;
    public Trajectory center5cell_0,center5cell_1;
    public Trajectory right7cell_0,right7cell_1;
    public Trajectory left7cell_0,left7cell_1;
    private DriveSubsystem m_drive;


    public SneakyTrajectory(DriveSubsystem drive){
                
        m_drive = drive;
        var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                       DriveConstants.kvVoltSecondsPerMeter,
                                       DriveConstants.kaVoltSecondsSquaredPerMeter),
            DriveConstants.kDriveKinematics,
            10);
            TrajectoryConfig configForward =
   new TrajectoryConfig(DriveConstants.kMaxSpeedMetersPerSecond,
     DriveConstants.kMaxAccelerationMetersPerSecondSquared)
      // Add kinematics to ensure max speed is actually obeyed
    .setKinematics(DriveConstants.kDriveKinematics)
     // Apply the voltage constraint
     .addConstraint(autoVoltageConstraint);

     TrajectoryConfig configBackward =
   new TrajectoryConfig(DriveConstants.kMaxSpeedMetersPerSecond,
     DriveConstants.kMaxAccelerationMetersPerSecondSquared)
      // Add kinematics to ensure max speed is actually obeyed
    .setKinematics(DriveConstants.kDriveKinematics)
     // Apply the voltage constraint
     .addConstraint(autoVoltageConstraint);
     configBackward.setReversed(true);
     
     centerRight6cell_0 = TrajectoryGenerator.generateTrajectory(
        List.of(
             new Pose2d(12.80, 5.79, new Rotation2d(0)),
             new Pose2d(9.75, 7.54, new Rotation2d(0)),
             new Pose2d(7.92, 7.54, new Rotation2d(0))),
        
        configBackward);
        
     centerRight6cell_1 = TrajectoryGenerator.generateTrajectory(
        List.of(
             new Pose2d(7.92, 7.54, new Rotation2d(0)),
             new Pose2d(10.97, 5.79, new Rotation2d(0)),
             new Pose2d(12.80, 5.79, new Rotation2d(0))),
        
        configForward);

        centerleft5cell_0 = TrajectoryGenerator.generateTrajectory(
          List.of(
            new Pose2d(12.7, 4.8, new Rotation2d(0)),
            new Pose2d(10.2, 3.8, new Rotation2d(0))),
            
           configBackward);

         centerleft5cell_1 = TrajectoryGenerator.generateTrajectory(
           List.of(
             new Pose2d(10.2, 3.8, new Rotation2d(0)),
             new Pose2d(12.7, 4.8, new Rotation2d(0))
           ),
           configBackward);
          center5cell_0 = TrajectoryGenerator.generateTrajectory(
            List.of(
              new Pose2d(12.8, 6, new Rotation2d(0)),
              new Pose2d(9.8, 5.5, new Rotation2d(0))
            ),
          configBackward);
          center5cell_1 = TrajectoryGenerator.generateTrajectory(
            List.of(
              new Pose2d(9.8, 5.5, new Rotation2d(0)),
              new Pose2d(12.8, 6, new Rotation2d(0))
            ),
           configBackward);
           right7cell_0 = TrajectoryGenerator.generateTrajectory(
             List.of(
               new Pose2d(12.8, 5, new Rotation2d(0)),
               new Pose2d(9.8, 3.9, new Rotation2d(0)),
               new Pose2d(9.7, 5.1, new Rotation2d(0))
             ),
            configBackward);
            right7cell_1 = TrajectoryGenerator.generateTrajectory(
              List.of(
                new Pose2d(9.7, 5.1, new Rotation2d(0)),
               new Pose2d(9.8, 3.9, new Rotation2d(0)),
               new Pose2d(12.8, 5, new Rotation2d(0))
              ),
             configBackward);
             left7cell_0 = TrajectoryGenerator.generateTrajectory(
               List.of(
                 new Pose2d(12.6, 5.9, new Rotation2d(0)),
                 new Pose2d(9.7, 5.3, new Rotation2d(0)),
                 new Pose2d(9.8, 3.9, new Rotation2d(0))
               ),
             configBackward);
             left7cell_1 = TrajectoryGenerator.generateTrajectory(
               List.of(
                new Pose2d(9.8, 3.9, new Rotation2d(0)),
                new Pose2d(9.7, 5.3, new Rotation2d(0)),
                new Pose2d(12.6, 5.9, new Rotation2d(0))
               ),
             configBackward);

    }
     
    public RamseteCommand getRamsete(Trajectory trajectory ){

        return new RamseteCommand(
        trajectory,
        m_drive::getPose,
        new RamseteController(DriveConstants.kRamseteB, DriveConstants.kRamseteZeta),
        new SimpleMotorFeedforward(DriveConstants.ksVolts,
                                   DriveConstants.kvVoltSecondsPerMeter,
                                   DriveConstants.kaVoltSecondsSquaredPerMeter),
        DriveConstants.kDriveKinematics,
        m_drive::getWheelSpeeds,
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        new PIDController(DriveConstants.kPDriveVel, 0, 0),
        // RamseteCommand passes volts to the callback
        m_drive::tankDriveVolts,
        m_drive
    );
    }


}
