/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.commands.DriveTeleOp;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  private final TalonSRX frontLeftDrive;
  private final TalonSRX frontRightDrive;
  private final VictorSPX backLeftDrive;
  private final VictorSPX backRightDrive;

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    frontLeftDrive = new TalonSRX(Constants.frontLeftDrivePort);
    frontRightDrive = new TalonSRX(Constants.frontRightDrivePort);
    backLeftDrive = new VictorSPX(Constants.backLeftDrivePort);
    backRightDrive = new VictorSPX(Constants.backRightDrivePort);

    frontLeftDrive.setInverted(true);
    frontRightDrive.setInverted(false);
    backLeftDrive.setInverted(true);
    backRightDrive.setInverted(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    setDefaultCommand(new DriveTeleOp());
  }

  public void driveByPercent(double leftSpeed, double rightSpeed)
  {
    frontLeftDrive.set(ControlMode.PercentOutput, leftSpeed);
    frontRightDrive.set(ControlMode.PercentOutput, rightSpeed);
    backLeftDrive.set(ControlMode.PercentOutput, leftSpeed);
    backRightDrive.set(ControlMode.PercentOutput, rightSpeed);
  }
}
