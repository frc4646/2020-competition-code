/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax;


public class Launcher extends SubsystemBase {
  /**
   * Creates a new Lawn Chair.
   */
  CANSparkMax launcherSpark;

  int deviceID;
  CANSparkMaxLowLevel.MotorType type = CANSparkMaxLowLevel.MotorType.kBrushless;
  double launchSpeed;
  
  public Launcher() {
    launcherSpark = new CANSparkMax(deviceID, type);
    launchSpeed = 0.8; //this is temporary, we'll find the right number through trial and error?

  }
  public void SpinUp() {
    launcherSpark.set(launchSpeed);
  }

  public void FindTarget() {
    //uses pixy2 to find target, I don't know how to do that
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
