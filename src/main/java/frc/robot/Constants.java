/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
   public final class IntakeConstants
   {
       public static final int rightintakeMotorPort = 0 ;
       public static final int leftintakeMotorPort = 1 ;
   }
   public final class JoystickConstants
   {
       public static final int driverControllerPort = 0;
   }
   public final class ShooterConstants
   {
       public static final int firstShooterMotorPort = 2;
       public static final int secondShooterMotorPort = 3;
   }
   public static class DriveConstants{
     public static final int frontLeftMotorPin = 4;
     public static final int frontRightMotorPin = 5;
     public static final int rearLeftMotorPin = 6;
     public static final int rearRightMotorPin = 7;
     public static final int rightwhellEncoder_A = 2;
     public static final int rightWhellEncoder_B = 3;
     public static final int leftWhellEncoder_A = 4;
     public static final int leftWhellEncoder_B = 5;
      
      // These are example values only - DO NOT USE THESE FOR YOUR OWN ROBOT!
     // These characterization values MUST be determined either experimentally or theoretically
     // for *your* robot's drive.
     // The Robot Characterization Toolsuite provides a convenient tool for obtaining these
     // values for your robot.
     public static final double ksVolts = 0.22;
     public static final double kvVoltSecondsPerMeter = 1.98;
     public static final double kaVoltSecondsSquaredPerMeter = 0.2;

     // Example value only - as above, this must be tuned for your drive!
     public static final double kPDriveVel = 8.5;
     public static final double kTrackwidthMeters = 0.69;
     public static final DifferentialDriveKinematics kDriveKinematics =
        new DifferentialDriveKinematics(kTrackwidthMeters);

        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
       
        public static final double kRamseteB = 2;
     public static final double kRamseteZeta = 0.7;

     public static final double turnP = 1.0;
     public static final double trunI = 0.0;
     public static final double trunD = 0.0;

     
     public static final double driveP = 1.0;
     public static final double driveI = 0.0;
     public static final double driveD = 0.0;

     public static final double accuary = 2.0;
     public static final double distenceaccuary = 3.0;
   }
   public final class climbConstants{
       public static final int solenoidForwardPin = 0;
       public static final int solenoidBackPin = 1;
   }
   public final class IntakConstants{
       public static final int compressorPin = 0;
       public static final int solenoidPin = 2;
       public static final int solenoidPin2 = 3;
   }
   public final class ArmConstants{
    public static final int EncoderPin1 = 0;
    public static final int EncoderPin2 = 1;
   }
   public final class HopperConstants{
       public static final int HopperMotorPin = 8;
       public static final int HopperMotorPin2 = 9;
   }static final double kPDriveVel = 8.5;
}

