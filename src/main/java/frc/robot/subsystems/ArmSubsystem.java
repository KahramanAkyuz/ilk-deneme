/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;


public class ArmSubsystem extends SubsystemBase {
  /**
   * Creates a new ArmSubsystem.
   */
  private final VictorSP armMotor = new VictorSP(12);
  private final AnalogPotentiometer armPot = new AnalogPotentiometer(0, 270, 5);
  private final Encoder armEncoder = new Encoder(ArmConstants.EncoderPin1, ArmConstants.EncoderPin2, false,Encoder.EncodingType.k4X);
  public ArmSubsystem() {
   armEncoder.setDistancePerPulse(3.0/2048.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public double getArmAci(){
    return armEncoder.getDistance()+5;
   
  }
  public void RunArm(double speed){
    armMotor.set(speed);
  }
  public void stopArm(){
    armMotor.set(0);
  }
  public double getAngle(){
   return armPot.get();
  }
}
