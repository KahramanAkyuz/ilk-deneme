/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;


public class intake extends SubsystemBase {
  
  private final VictorSP rightIntakeMotor = new VictorSP(IntakeConstants.rightintakeMotorPort);
  private final VictorSP leftIntakeMotor = new VictorSP(IntakeConstants.leftintakeMotorPort);
  public intake() {

  }

  @Override
  public void periodic() {
   
  }
  public void runIntake(double speed){
    rightIntakeMotor.set(speed);
    leftIntakeMotor.set(speed);
  }
  public void stopIntake()
  {
    rightIntakeMotor.set(0);
    leftIntakeMotor.set(0);
  }
}
