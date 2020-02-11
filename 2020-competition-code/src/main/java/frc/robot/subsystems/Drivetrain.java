/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DriveTeleOp;
import com.analog.adis16448.frc.ADIS16448_IMU;
import com.analog.adis16448.frc.ADIS16448_IMU.Axis;
//import 

public class Drivetrain extends SubsystemBase {

  private final TalonSRX frontLeftDrive;
  private final TalonSRX frontRightDrive;
  private final VictorSPX backLeftDrive;
  private final VictorSPX backRightDrive;

  //private final Encoder rightEncoder;
  //private final Encoder leftEncoder;

  private final int encoderCountsPerInch;
  private final ADIS16448_IMU gyro;

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    frontLeftDrive = new TalonSRX(Constants.frontLeftDrivePort);
    frontRightDrive = new TalonSRX(Constants.frontRightDrivePort);
    backLeftDrive = new VictorSPX(Constants.backLeftDrivePort);
    backRightDrive = new VictorSPX(Constants.backRightDrivePort);

    //rightEncoder = new Encoder(Constants.rightEncoderPort1, Constants.rightEncoderPort2);
    //leftEncoder = new Encoder(Constants.leftEncoderPort1, Constants.leftEncoderPort2);


    frontLeftDrive.configFactoryDefault();
    frontRightDrive.configFactoryDefault();
    backLeftDrive.configFactoryDefault();
    backRightDrive.configFactoryDefault();

    frontRightDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    frontLeftDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

    frontLeftDrive.setInverted(true);
    frontRightDrive.setInverted(false);
    backLeftDrive.setInverted(true);
    backRightDrive.setInverted(false);

    frontLeftDrive.setSensorPhase(true);
    frontRightDrive.setSensorPhase(false);
    backLeftDrive.setSensorPhase(true);
    backRightDrive.setSensorPhase(false);

    encoderCountsPerInch = 0;

    gyro = new ADIS16448_IMU(Axis.kX);
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

  public void resetEncoders(){
    //leftEncoder.reset();
    //rightEncoder.reset();
  }

  public double[] getDriveEncoderDistance(){
    return new double[] {frontLeftDrive.getSelectedSensorPosition() / encoderCountsPerInch, frontRightDrive.getSelectedSensorPosition() / encoderCountsPerInch};
  }

  public void resetGyro(){
    gyro.calibrate();
  }
  public double getAngle(){
    return gyro.getAngle();
  }

}
