/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class VisionTarget extends CommandBase {
  /**
   * Creates a new VisionTarget.
   */

  double neutralPan;
  double neutralTilt; 
  double turnSpeed;
  int tolerance;
  int xMaxPos;
  int yMaxPos;
  int xMidPos;
  int yMidPos;

  public VisionTarget() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_launcher, Robot.m_drivetrain);

    neutralPan = 90;
    neutralTilt = 90; //Placeholder
    turnSpeed = 0.8;
    tolerance = 5;
    xMaxPos = 315;
    yMaxPos = 207;
    xMidPos = xMaxPos/2;
    yMidPos = yMaxPos/2;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_launcher.setServos(neutralPan, neutralTilt);

    // While the target is not being tracked, pan the Pixy2 to search for it.
    while (!Robot.m_launcher.isBlockVisible())
    {
      int incrementAngle=0;
      Robot.m_launcher.setServos(incrementAngle, neutralTilt);
      if (incrementAngle == 180) incrementAngle = 0;
      else incrementAngle++;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Code below in execute() will have the robot face the Pixy2 target.
    Robot.m_launcher.run();

    if (Robot.m_launcher.getPos()[0] < xMidPos) { 
      Robot.m_drivetrain.driveByPercent(-turnSpeed, turnSpeed);
    }
    else if (Robot.m_launcher.getPos()[0] > xMidPos) {
      Robot.m_drivetrain.driveByPercent(turnSpeed, -turnSpeed);
    }

    //TODO Make code below in execute() that will get the distance to the target using the width and height.
    // After that, drive to the needed distance away from the target to shoot.
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_drivetrain.driveByPercent(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (-tolerance <= Robot.m_launcher.getPos()[0] && Robot.m_launcher.getPos()[0] <= tolerance) {
      return true;
    }
    return false;
  }
}
