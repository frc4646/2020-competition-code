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

  public FindTarget() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_launcher);

    neutralPan = 90;
    neutralTilt = 90; //Placeholder
    turnSpeed = 0.8;
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