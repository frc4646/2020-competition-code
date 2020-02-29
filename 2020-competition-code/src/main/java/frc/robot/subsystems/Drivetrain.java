/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DriveTeleOp;

import edu.wpi.first.wpilibj.SPI;
import com.analog.adis16470.frc.ADIS16470_IMU;
import com.analog.adis16470.frc.ADIS16470_IMU.IMUAxis;
import com.analog.adis16470.frc.ADIS16470_IMU.ADIS16470CalibrationTime;

public class Drivetrain extends SubsystemBase {

  private final TalonSRX frontLeftDrive;
  private final TalonSRX frontRightDrive;
  private final VictorSPX backLeftDrive;
  private final VictorSPX backRightDrive;

  //private final Encoder rightEncoder;
  //private final Encoder leftEncoder;

  private final int encoderCountsPerInch;
  //private final ADIS16470_IMU imu;

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

    backLeftDrive.follow(frontLeftDrive);
    backRightDrive.follow(frontRightDrive);

    encoderCountsPerInch = 0;

    //imu = new ADIS16470_IMU(IMUAxis.kZ, SPI.Port.kOnboardCS0, ADIS16470CalibrationTime._4s);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void driveByPercent(double leftSpeed, double rightSpeed)
  {
    frontLeftDrive.set(ControlMode.PercentOutput, leftSpeed);
    frontRightDrive.set(ControlMode.PercentOutput, rightSpeed);
    //backLeftDrive.set(ControlMode.Follower, frontLeftDrive.getDeviceID());
    //backRightDrive.set(ControlMode.Follower, frontRightDrive.getDeviceID());
  }

  //TODO: NEED FRC CHARACTERIZATION TOOL TO SET PID VALUES!!!
  public void driveByEncoderInches(int leftInches, int rightInches)
  {
    int leftCount = leftInches * encoderCountsPerInch;
    int rightCount = rightInches * encoderCountsPerInch;
    frontLeftDrive.set(ControlMode.Position, leftCount);
    frontRightDrive.set(ControlMode.Position, rightCount);
    //backLeftDrive.set(ControlMode.Follower, frontLeftDrive.getDeviceID());
    //backRightDrive.set(ControlMode.Follower, frontRightDrive.getDeviceID());
  }

  public double[] getDriveEncoderDistance(){
    return new double[] {frontLeftDrive.getSelectedSensorPosition() / encoderCountsPerInch, frontRightDrive.getSelectedSensorPosition() / encoderCountsPerInch};
  }

  public void resetGyro(){
    //imu.calibrate();
  }
  public double getAngle(){
    //return imu.getAngle();
    return 0;
  }

}
