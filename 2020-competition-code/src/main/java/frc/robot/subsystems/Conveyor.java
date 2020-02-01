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
  Spark frontConveyor;
  Spark rearConveyor;
  double conveyorUpSpeed;
  double conveyorDownSpeed;

  DigitalInput lim1 = new DigitalInput(Constants.lim1Port);
  DigitalInput lim2 = new DigitalInput(Constants.lim2Port);
  DigitalInput lim3 = new DigitalInput(Constants.lim3Port);
  DigitalInput lim4 = new DigitalInput(Constants.lim4Port);
  DigitalInput lim5 = new DigitalInput(Constants.lim5Port);

  public Conveyor() {
    frontConveyor = new Spark (Constants.frontConveyorPort);
    rearConveyor = new Spark (Constants.rearConveyorPort);
    conveyorUpSpeed = 0.5;
    conveyorDownSpeed = -0.3;
  }

  public void UpConveyor() {
    frontConveyor.set(conveyorUpSpeed);
    rearConveyor.set(conveyorUpSpeed);
  }

  public void DownConveyor() {
    frontConveyor.set(conveyorDownSpeed);
    rearConveyor.set(conveyorDownSpeed);
  }

  public void StopConveyor() {
    frontConveyor.set(0);
    rearConveyor.set(0);
  }

  public int GetBallsStored() {
    if (lim5.get()) {
      return 5;
    }
    if (lim4.get()) {
      return 4;
    }
    if (lim3.get()) {
      return 3;
    }
    if (lim2.get()) {
      return 2;
    }
    if (lim1.get()) {
      return 1;
    }
    else {
      return 0;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
