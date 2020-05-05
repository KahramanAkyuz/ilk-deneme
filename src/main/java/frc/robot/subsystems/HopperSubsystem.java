/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HopperConstants;

public class HopperSubsystem extends SubsystemBase {
  /**
   * Creates a new HopperSubsystem.
   */
  private final Talon hoopermotor = new Talon(HopperConstants.HopperMotorPin);
  private final Talon hoopermotor2 = new Talon(HopperConstants.HopperMotorPin2); 
  public HopperSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void runHoppper(double speed){
    hoopermotor.set(speed);
    hoopermotor2.set(speed);
  }
  public void stopHopper(){
   hoopermotor.set(0);
   hoopermotor2.set(0);
 }
  
}
