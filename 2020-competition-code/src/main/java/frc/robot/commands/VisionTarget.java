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

  double neutralPan = 0;
  double neutralTilt = 0; 
  double turnSpeed = 0.8;
  double tolerance = 0.5;

  public VisionTarget() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_launcher, Robot.m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_launcher.setServos(neutralPan, neutralTilt);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Robot.m_launcher.getValues()[0] < 0) { //assuming we've tracked the target. 0 is probably the central position.
      Robot.m_drivetrain.driveByPercent(-turnSpeed, turnSpeed);
    }
    else if (Robot.m_launcher.getValues()[0] > 0) {
      Robot.m_drivetrain.driveByPercent(turnSpeed, -turnSpeed);
    }
    Robot.m_launcher.run();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_drivetrain.driveByPercent(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (-tolerance <= Robot.m_launcher.getValues()[0] && Robot.m_launcher.getValues()[0] <= tolerance) {
      return true;
    }
    return false;
  }
}
