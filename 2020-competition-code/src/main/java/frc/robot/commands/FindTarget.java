/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class FindTarget extends CommandBase {
  /**
   * Creates a new FindTarget.
   */

  double neutralPan;
  double neutralTilt; 
  double turnSpeed;
  int tolerance;

  public FindTarget() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_launcher);

    neutralPan = 90;
    neutralTilt = 0; //Placeholder
    turnSpeed = 0.8;
    tolerance = 2;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("YO! THE FIND TARGET FILE HAS INITIALIZED!");
    Robot.m_launcher.setServos(neutralPan, neutralTilt);

    // While the target is not being tracked, pan the Pixy2 to search for it.
    double incrementAngle1=0;
    while (!Robot.m_launcher.isBlockVisible())
    {
      Robot.m_launcher.setServos(incrementAngle1, neutralTilt);
      if (incrementAngle1 == 180) incrementAngle1 = 0;
      else incrementAngle1++;
    }
    
    //Center the Pixy2 to the target.
    double incrementAngle2=Robot.m_launcher.getServoPos()[0];
    while (Robot.m_launcher.getPos()[0] + tolerance < Robot.m_launcher.xMidPos)
    {
      Robot.m_launcher.setServos(incrementAngle2, neutralTilt);
      if (incrementAngle2 == 180) incrementAngle2 = 0;
      else incrementAngle2++;
    }
    double incrementAngle3=Robot.m_launcher.getServoPos()[0];
    while (Robot.m_launcher.getPos()[0] - tolerance > Robot.m_launcher.xMidPos)
    {
      Robot.m_launcher.setServos(incrementAngle3, neutralTilt);
      if (incrementAngle3 == 0) incrementAngle3 = 180;
      else incrementAngle3--;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!Robot.m_launcher.isBlockVisible()) //Find the Target
    {
      int incrementAngle=0;
      Robot.m_launcher.setServos(incrementAngle, neutralTilt);
      if (incrementAngle == 180) incrementAngle = 0;
      else incrementAngle++;
    }
    else //Center the Pixy2 to the Target
    {
      while (Robot.m_launcher.getPos()[0] + tolerance < Robot.m_launcher.xMidPos)
      {
        double incrementAngle=Robot.m_launcher.getServoPos()[0];
        Robot.m_launcher.setServos(incrementAngle, neutralTilt);
        if (incrementAngle == 180) incrementAngle = 0;
        else incrementAngle++;
      }
      while (Robot.m_launcher.getPos()[0] - tolerance > Robot.m_launcher.xMidPos)
      {
        double incrementAngle=Robot.m_launcher.getServoPos()[0];
        Robot.m_launcher.setServos(incrementAngle, neutralTilt);
        if (incrementAngle == 180) incrementAngle = 0;
        else incrementAngle--;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}