/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;


public class DriveSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveSubsystem.
   */
  private final VictorSP frontLeftMotor =  new VictorSP(Constants.DriveConstants.frontLeftMotorPin);
  private final VictorSP frontRightMotor =  new VictorSP(Constants.DriveConstants.frontRightMotorPin);
  private final VictorSP rearLeftMotor =  new VictorSP(Constants.DriveConstants.rearLeftMotorPin);
  private final VictorSP rearRightMotor =  new VictorSP(Constants.DriveConstants.rearRightMotorPin);
  private final Encoder rightWheelencoder = new Encoder(DriveConstants.rightwhellEncoder_A, DriveConstants.rightWhellEncoder_B, false, EncodingType.k4X);
  private final SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftMotor, rearLeftMotor);
  private final SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightMotor, rearRightMotor);
  private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  private final Encoder leftWheelEncoder = new Encoder(DriveConstants.leftWhellEncoder_A, DriveConstants.leftWhellEncoder_B, false, EncodingType.k4X);

  private final DifferentialDrive m_drive = new DifferentialDrive(leftGroup,rightGroup);
  private DifferentialDriveOdometry m_odometry;


  public DriveSubsystem() {
    leftWheelEncoder.setDistancePerPulse(7.62*Math.PI*2.0/2048.0);
    rightWheelencoder.setDistancePerPulse(7.62*Math.PI*2.0/2048.0);
    gyro.calibrate();
    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
  }

  @Override
  public void periodic() {
    m_odometry.update(Rotation2d.fromDegrees(getHeading()), leftWheelEncoder.getDistance(),
                      rightWheelencoder.getDistance());
    // This method will be called once per scheduler run
  }
  

  public void arcadeDrive(double fwd, double rot){
    m_drive.arcadeDrive(fwd, rot, true);
  }
  public double getHeading(){
   return Math.IEEEremainder(gyro.getAngle(), 360);
  }
  public double getHeadingReversed(){
    return Math.IEEEremainder(-1 * gyro.getAngle(), 360);

  }
  public double gettotalDistance(){
    return (leftWheelEncoder.getDistance() + rightWheelencoder.getDistance())/2;
  }
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftWheelEncoder.getRate(), rightWheelencoder.getRate());
  }
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftGroup.setVoltage(leftVolts);
    rightGroup.setVoltage(-rightVolts);
    m_drive.feed();
  }

}
