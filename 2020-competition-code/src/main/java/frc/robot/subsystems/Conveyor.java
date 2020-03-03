/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;

public class Conveyor extends SubsystemBase {
  /**
   * Creates a new Conveyor.
   */
  Spark topConveyor;
  Spark bottomConveyor;
  double conveyorUpSpeed;
  double conveyorDownSpeed;

  DigitalInput lowOptic;
  DigitalInput highOptic;
  DigitalInput launcherOptic;

  public Conveyor() {
    topConveyor = new Spark (Constants.frontConveyorPort);
    bottomConveyor = new Spark (Constants.rearConveyorPort);
    topConveyor.setInverted(true);
    bottomConveyor.setInverted(true);
    conveyorUpSpeed = 0.7;
    conveyorDownSpeed = -0.3;
    lowOptic = new DigitalInput(Constants.lowOpticPort);
    highOptic = new DigitalInput(Constants.highOpticPort);
    launcherOptic = new DigitalInput(Constants.launcherOpticPort);
  }

  public void UpTopConveyor() {
    topConveyor.set(conveyorUpSpeed);
  }

  public void DownTopConveyor() {
    topConveyor.set(conveyorDownSpeed);
  }

  public void UpBottomConveyor() {
    bottomConveyor.set(conveyorUpSpeed);
  }

  public void DownBottomConveyor() {
    bottomConveyor.set(conveyorDownSpeed);
  }

  public void StopConveyor() {
    topConveyor.set(0);
    bottomConveyor.set(0);
  }

  public boolean isLowBallPresent() {
    return lowOptic.get();
  }

  public boolean isHighBallPresent() {
    return highOptic.get();
  }

  public boolean isLauncherBallPresent() {
    return launcherOptic.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
