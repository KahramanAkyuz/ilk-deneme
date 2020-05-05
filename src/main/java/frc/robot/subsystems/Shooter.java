/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
  private final VictorSP firstShooterMotor = new VictorSP(ShooterConstants.firstShooterMotorPort);
  private final VictorSP secondShooterMotor = new VictorSP(ShooterConstants.secondShooterMotorPort);
  public Shooter() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void runShooter(double speed)
  {
    firstShooterMotor.set(speed);
    secondShooterMotor.set(speed);
  }
  public void stopShooter()
  {
    firstShooterMotor.set(0);
    secondShooterMotor.set(0);
  }
}
