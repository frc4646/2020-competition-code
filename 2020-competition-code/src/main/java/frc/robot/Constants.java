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
  public static final int frontLeftDrivePort = 2;
  public static final int frontRightDrivePort = 1; 
  public static final int backLeftDrivePort = 3; 
  public static final int backRightDrivePort = 0; 
  public static final int intakePort = 0;
  public static final int articulateIntakePort = 0;
  public static final int leftEncoderPort1 = 0;
  public static final int leftEncoderPort2 = 0;
  public static final int rightEncoderPort1 = 0;
  public static final int rightEncoderPort2 = 0;
  

      //Climber
  public static final int winch1 = 0;
  public static final int winch2 = 0;
  public static final int elevator = 0;
  public static final int liftStringPotPin = 0;
  public static final double MAX_VALUE = 0;
  public static final double MIN_VALUE = 0;
  public static final double MAX_HEIGHT = 0;
  public static final double MIN_HEIGHT = 0;


      //Conveyor
  public static final int frontConveyorPort = 0;
  public static final int rearConveyorPort = 0;
  public static final int lim1Port = 0;
  public static final int lim2Port = 0;
  public static final int lim3Port = 0;
  public static final int lim4Port = 0;
  public static final int lim5Port = 0;


      //Joysticks
  public static final int leftJoyPort = 0;
  public static final int rightJoyPort = 1;
  public static final int mechJoyPort = 2;
}
