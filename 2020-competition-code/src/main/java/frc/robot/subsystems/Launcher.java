/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogTrigger;
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
  NetworkTableEntry tableHight;
  NetworkTableEntry tableAngle;

  double x, y, width, hight, angle;

  
  

  public Launcher() {
    launcherSpark = new CANSparkMax(deviceID, type);
    launchSpeed = 0.8; //this is temporary, we'll find the right number through trial and error?
    opticTrigger = new AnalogTrigger(0);
    opticInput = new AnalogInput(1);
    opticTrigger = new AnalogTrigger(opticInput);

    opticTrigger.setLimitsVoltage(disableTrigger, enableTrigger);

    pixyInst = NetworkTableInstance.getDefault();
    pixyTable = pixyInst.getTable("Pixy");

    tableX = pixyTable.getEntry("x");
    tableY = pixyTable.getEntry("y");
    tableWidth = pixyTable.getEntry("width");
    tableHight = pixyTable.getEntry("hight");
    tableAngle = pixyTable.getEntry("angle");


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

    pixyTable.addEntryListener("hight", (table, key, entry, value, flags) -> {
      System.out.println("Hight changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    pixyTable.addEntryListener("angle", (table, key, entry, value, flags) -> {
      System.out.println("Angle changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);


    try {
       Thread.sleep(10000);
    } catch (InterruptedException ex) {
       System.out.println("Interrupted");
       Thread.currentThread().interrupt();
       return;
    }

    x = tableX.getDouble(0.0);
    y = tableY.getDouble(0.0);
    width = tableWidth.getDouble(0.0);
    hight = tableHight.getDouble(0.0);
    angle = tableAngle.getDouble(0.0);  
 }

  public void SpinUp() {
    launcherSpark.set(launchSpeed);
  }
  public void StopLauncher() {
    launcherSpark.set(0);
  }

  public void FindTarget() {
    //uses pixy2 to find target, I don't know how to do that
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean isBallInIntake(){
    return opticTrigger.getTriggerState();
  }
}
