/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.EntryListenerFlags;


public class Launcher extends SubsystemBase {
  /**
   * Creates a new Lawn Chair.
   */
  CANSparkMax launcherSpark1;
  CANSparkMax launcherSpark2;
  CANEncoder launcherEncoder;

  Servo pan, tilt;

  //int deviceID;
  //CANSparkMaxLowLevel.MotorType type = CANSparkMaxLowLevel.MotorType.kBrushless;
  double launchSpeed;
  double encoderCountsPerInch;


  NetworkTableInstance pixyInst;
  NetworkTable pixyTable;
  NetworkTableEntry tableX;
  NetworkTableEntry tableY;
  NetworkTableEntry tableWidth;
  NetworkTableEntry tableHeight;
  NetworkTableEntry tableAge;
  NetworkTableEntry tableVisible;

  double x, y, width, height, age;
  boolean isBlockVisible;
  boolean isPixyRunning;

  public float xMaxPos, yMaxPos, xMidPos, yMidPos;

  public Launcher() {
    launcherSpark1 = new CANSparkMax(Constants.launcherID1, CANSparkMaxLowLevel.MotorType.kBrushless);
    launcherSpark2 = new CANSparkMax(Constants.launcherID2, CANSparkMaxLowLevel.MotorType.kBrushless);

    launcherSpark1.setInverted(true);
    launcherSpark2.setInverted(false);

    launcherSpark2.follow(launcherSpark1);
    launchSpeed = 0.8; //this is temporary, we'll find the right number through trial and error?
    launcherEncoder = launcherSpark1.getEncoder();

    pan = new Servo(Constants.PAN_PORT);
    tilt = new Servo(Constants.TILT_PORT);

    pixyInst = NetworkTableInstance.getDefault();
    pixyTable = pixyInst.getTable("Pixy");

    tableX = pixyTable.getEntry("x");
    tableY = pixyTable.getEntry("y");
    tableWidth = pixyTable.getEntry("width");
    tableHeight = pixyTable.getEntry("height");
    tableAge = pixyTable.getEntry("age");
    tableVisible = pixyTable.getEntry("visible");

    xMaxPos = 315;
    yMaxPos = 207;
    xMidPos = xMaxPos/2;
    yMidPos = yMaxPos/2;
    isPixyRunning = false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (isPixyRunning) run();
  }

  public void run() {
    pixyTable.addEntryListener("x", (table, key, entry, value, flags) -> {
       System.out.println("X changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    pixyTable.addEntryListener("y", (table, key, entry, value, flags) -> {
       System.out.println("Y changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    pixyTable.addEntryListener("width", (table, key, entry, value, flags) -> {
      System.out.println("Width changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    pixyTable.addEntryListener("height", (table, key, entry, value, flags) -> {
      System.out.println("Height changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    pixyTable.addEntryListener("age", (table, key, entry, value, flags) -> {
      System.out.println("Age changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    pixyTable.addEntryListener("visible", (table, key, entry, value, flags) -> {
      System.out.println("Visible changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    try {
       Thread.sleep(10000);
    } catch (InterruptedException ex) {
       System.out.println("Interrupted");
       Thread.currentThread().interrupt();
       return;
    }

    x = tableX.getDouble(0);
    y = tableY.getDouble(0);
    width = tableWidth.getDouble(0);
    height = tableHeight.getDouble(0); 
    age = tableAge.getDouble(0);
    isBlockVisible = tableAge.getBoolean(false);
 }
  
  public void StopLauncher() {
    launcherSpark1.set(0);
  }
  public void setServos(double servoPan, double servoTilt) {
    pan.set(servoPan);
    tilt.set(servoTilt);
  }

  public double[] getServoPos() {
    double[] array = {pan.get(), tilt.get()};
    return array;
  }

  public double[] getPos()
  {
    double[] pos = new double[]{x, y};
    return pos;
  }

  public double[] getSize()
  {
    double[] size = new double[]{width, height};
    return size;
  }

  public double getAge()
  {
    return age;
  }

  /**
   * @return the isBlockVisible
   */
  public boolean isBlockVisible() {
    return isBlockVisible;
  }

  public void startPixy2()
  {
    pixyInst.startClientTeam(4646);
    isPixyRunning = true;
  }

  public void stopPixy2()
  {
    isPixyRunning = false;
    pixyInst.close();
  }

  public double getSpeed() {
    return launcherEncoder.getVelocity();
  }
  
  public void setSpeed(double speed){
    launcherSpark1.set(speed);
  }

}
