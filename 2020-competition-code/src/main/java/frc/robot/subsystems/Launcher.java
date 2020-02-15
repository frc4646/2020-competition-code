/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.EntryListenerFlags;


public class Launcher extends SubsystemBase {
  /**
   * Creates a new Lawn Chair.
   */
  CANSparkMax launcherSpark;

  Servo pan, tilt;

  //int deviceID;
  //CANSparkMaxLowLevel.MotorType type = CANSparkMaxLowLevel.MotorType.kBrushless;
  //double launchSpeed;

  //AnalogTrigger opticTrigger;
  //AnalogInput opticInput;

  //final double enableTrigger = 0, disableTrigger = 0;


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
    //launcherSpark = new CANSparkMax(deviceID, type);
    //launchSpeed = 0.8; //this is temporary, we'll find the right number through trial and error?
    //opticTrigger = new AnalogTrigger(0);
    //opticInput = new AnalogInput(1);
    //opticTrigger = new AnalogTrigger(opticInput);

    pan = new Servo(Constants.PAN_PORT);
    tilt = new Servo(Constants.TILT_PORT);

    //opticTrigger.setLimitsVoltage(disableTrigger, enableTrigger);

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
/*
  public void SpinUp() {
    launcherSpark.set(launchSpeed);
  }
  public void StopLauncher() {
    launcherSpark.set(0);
  }


  public boolean isBallInLauncher(){
    return opticTrigger.getTriggerState();
  }
*/
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
}
