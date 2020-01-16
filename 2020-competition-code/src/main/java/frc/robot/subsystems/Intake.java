/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  Spark intakeSpark;
  Spark articulateSpark;
  double deploySpeed = 0.5;
  double retractSpeed = 0.5;
  double intakeSpeed = Constants.sparkForwardMax;
  double outtakeSpeed = -0.8;
  public Intake() {
    intakeSpark = new Spark(Constants.intakePort);
    articulateSpark = new Spark(Constants.articulateIntakePort);
  }
  public void intakeBall() {
    intakeSpark.set(intakeSpeed);
  }
  public void outtakeBall() {
    intakeSpark.set(outtakeSpeed);

  }
  public void stopIntake() {
    intakeSpark.set(0);
  }
  public void deployIntake() {
    //just run it for 2 seconds
    articulateSpark.set(deploySpeed);
  }
  public void retractIntake() {
    //just run it for 2 seconds
    articulateSpark.set(retractSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
