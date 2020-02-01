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
import edu.wpi.first.wpilibj.Encoder;


public class Climber extends SubsystemBase {
  /**
   * Creates a new Climber.
   */

   Spark winchSpark1;
   Spark winchSpark2;
   Spark elevatorSpark;
   
   Encoder winchEncoder1;
   Encoder winchEncoder2;
   AnalogInput liftStringPotPin;

   public final double MAX_VALUE = 0;
   public final double MIN_VALUE = 0;
   public final double MAX_HEIGHT = 0;
   public final double MIN_HEIGHT = 0;
   public final double HOLD_POWER = 0;
   public final double UP_POWER = 0;
   public final double DOWN_POWER = 0;
  

  public Climber() {
    winchSpark1 = new Spark(Constants.winch1);
    winchSpark2 = new Spark(Constants.winch2);
    elevatorSpark = new Spark(Constants.elevator);
    
    liftStringPotPin = new AnalogInput(Constants.liftStringPotPin);

  

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void ElevatorTeleOp() {
    elevatorSpark.set(0.5);
  }

  public void WinchTeleOp() {
    winchSpark1.set(0.5);
    winchSpark2.set(0.5);
  }

  public void ElevatorUp(double speed) {
    elevatorSpark.set(speed);
  }
  public void HoldHeight() {
    elevatorSpark.set(HOLD_POWER);
  }

  public void WinchPull(double count) {
    //winchSpark1.set(speed);
    //winchSpark2.set(speed);
  }

  public double GetLiftHeight() {
    double pinVoltage = liftStringPotPin.getVoltage();
    double m = (MIN_HEIGHT - MAX_HEIGHT) / (double)(MIN_VALUE - MAX_VALUE);
    double b = MIN_HEIGHT - ((MIN_VALUE)*(m));
    double height = ((m)*(pinVoltage)) + b;
    return height;
  }

}
