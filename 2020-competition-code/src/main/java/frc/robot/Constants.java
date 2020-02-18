/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
      //Drivetrain
//   public static final int frontLeftDrivePort = 2;  This stuff is the actual competition ports
//   public static final int frontRightDrivePort = 1; 
//   public static final int backLeftDrivePort = 3; 
//   public static final int backRightDrivePort = 0; 
  public static final int frontLeftDrivePort = 1;  //talon; this stuff is for when I (Prithvi) tested the code on Demobot
  public static final int frontRightDrivePort = 2; //talon
  public static final int backLeftDrivePort = 3;   //victor
  public static final int backRightDrivePort = 0;  //victor
  public static final int intakePort = 0;
  public static final int articulateIntakePort = 1;
  public static final int leftEncoderPort1 = 0;
  public static final int leftEncoderPort2 = 1;
  public static final int rightEncoderPort1 = 2;
  public static final int rightEncoderPort2 = 3;
  

      //Climber
  public static final int winch1Spark = 2;
  public static final int winch2Spark = 3;
  public static final int elevatorSpark = 0;
  public static final int liftStringPotPin = 3;
  public static final int winch1EncoderPort1 = 4;
  public static final int winch1EncoderPort2 = 5;
  public static final int winch2EncoderPort1 = 6;
  public static final int winch2EncoderPort2 = 7;

      //Launcher

  public static final int PAN_PORT = 5;
  public static final int TILT_PORT = 6;

      //Conveyor
  public static final int frontConveyorPort = 7;
  public static final int rearConveyorPort = 8;
  public static final int lim1Port = 8;
  public static final int lim2Port = 9;
  public static final int lim3Port = 10;
  public static final int lim4Port = 11;
  public static final int lim5Port = 12;


      //Joysticks
  public static final int leftJoyPort = 0;
  public static final int rightJoyPort = 1;
  public static final int mechJoyPort = 2;
}