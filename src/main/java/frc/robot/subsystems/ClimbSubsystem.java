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
import frc.robot.Constants.climbConstants;

public class ClimbSubsystem extends SubsystemBase {
  /**
   * Creates a new ClimbSubsystem.
   */
  private final Compressor compressor = new Compressor(IntakConstants.compressorPin);
  private final DoubleSolenoid climbsolenoid = new DoubleSolenoid(climbConstants.solenoidForwardPin,climbConstants.solenoidBackPin);
  
  public ClimbSubsystem() {
    compressorDisable();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void compressorEnable(){
  compressor.setClosedLoopControl(true);

  }
  public void compressorDisable(){
    compressor.setClosedLoopControl(false);
  }
  public void climbUp(){
    climbsolenoid.set(Value.kForward);
  }
  public void climbDown(){
    climbsolenoid.set(Value.kReverse);
  }
  public void stopCylinders(){
    climbsolenoid.set(Value.kOff);
  }
}
