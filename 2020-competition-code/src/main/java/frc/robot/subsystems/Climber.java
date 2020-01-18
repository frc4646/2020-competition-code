/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;


public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */

   Spark winchSpark1;
   Spark winchSpark2;
   Spark elevatorSpark;

   AnalogInput liftEncoderPin;
   AnalogInput liftStringPotPin;

  public Climber() {
    winchSpark1 = new Spark(Constants.winch1);
    winchSpark2 = new Spark(Constants.winch2);
    elevatorSpark = new Spark(Constants.elevator);
    
    liftEncoderPin = new AnalogInput(Constants.liftEncoderPort);
    liftStringPotPin = new AnalogInput(Constants.liftStringPotPin);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
