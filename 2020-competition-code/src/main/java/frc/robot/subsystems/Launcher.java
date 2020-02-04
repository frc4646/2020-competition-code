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

  int deviceID;
  CANSparkMaxLowLevel.MotorType type = CANSparkMaxLowLevel.MotorType.kBrushless;
  double launchSpeed;

  AnalogTrigger opticTrigger;
  AnalogInput opticInput;

  final double enableTrigger = 0, disableTrigger = 0;


  NetworkTableInstance pixyInst;
  NetworkTable pixyTable;
  NetworkTableEntry tableX;
  NetworkTableEntry tableY;
  NetworkTableEntry tableWidth;
  NetworkTableEntry tableHeight;
  NetworkTableEntry tableAge;
  NetworkTableEntry tableVisible;

  int x, y, width, height, age;
  boolean isBlockVisible;

  public Launcher() {
    launcherSpark = new CANSparkMax(deviceID, type);
    launchSpeed = 0.8; //this is temporary, we'll find the right number through trial and error?
    opticTrigger = new AnalogTrigger(0);
    opticInput = new AnalogInput(1);
    opticTrigger = new AnalogTrigger(opticInput);

    pan = new Servo(0);
    tilt = new Servo(1);

    opticTrigger.setLimitsVoltage(disableTrigger, enableTrigger);

    pixyInst = NetworkTableInstance.getDefault();
    pixyTable = pixyInst.getTable("Pixy");

    tableX = pixyTable.getEntry("x");
    tableY = pixyTable.getEntry("y");
    tableWidth = pixyTable.getEntry("width");
    tableHeight = pixyTable.getEntry("height");
    tableAge = pixyTable.getEntry("age");
    tableVisible = pixyTable.getEntry("visible");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void run() {
    pixyInst.startClientTeam(4646);
    
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

    x = (int)tableX.getNumber(0);
    y = (int)tableY.getNumber(0);
    width = (int)tableWidth.getNumber(0);
    height = (int)tableHeight.getNumber(0); 
    age = (int)tableAge.getNumber(0);
    isBlockVisible = tableAge.getBoolean(false);
 }

  public void SpinUp() {
    launcherSpark.set(launchSpeed);
  }
  public void StopLauncher() {
    launcherSpark.set(0);
  }


  public boolean isBallInLauncher(){
    return opticTrigger.getTriggerState();
  }

  public void setServos(double servoPan, double servoTilt) {
    pan.set(servoPan);
    tilt.set(servoTilt);
  }

  public int[] getPos()
  {
    int[] pos = new int[]{x, y};
    return pos;
  }

  public int[] getSize()
  {
    int[] size = new int[]{width, height};
    return size;
  }

  public int getAge()
  {
    return age;
  }

  /**
   * @return the isBlockVisible
   */
  public boolean isBlockVisible() {
    return isBlockVisible;
  }
}
