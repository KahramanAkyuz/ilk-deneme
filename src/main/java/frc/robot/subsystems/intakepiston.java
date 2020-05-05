/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakConstants;

public class intakepiston extends SubsystemBase {
  /**
   * Creates a new intakepiston.
   */
  private final Compressor compressor = new Compressor(IntakConstants.compressorPin);
  private final DoubleSolenoid intakesolenoid = new  DoubleSolenoid(IntakConstants.solenoidPin,IntakConstants.solenoidPin2);
  public intakepiston() {
    compressorClose();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void compressorOpen(){
    compressor.setClosedLoopControl(true);
  }
  public void compressorClose(){
    compressor.setClosedLoopControl(false);
  }
  public void Intakeopen(){
    intakesolenoid.set(Value.kForward);
  }
  public void Intakeclose(){
    intakesolenoid.set(Value.kReverse);
  }
}
