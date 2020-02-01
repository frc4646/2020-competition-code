/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DriveDistance extends CommandBase {
  /**
   * Creates a new DriveDistance.
   */
  double driveInches;
  double leftSpeed, rightSpeed = 0.8;
  //YEEEEEET (Added as per Dave's Request)
  public DriveDistance(double inches) {
    // Use addRequirements() here to declare subsystem dependencies.
    // cartesian; positive x is to the right
    addRequirements(Robot.m_drivetrain);
    driveInches = inches;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.m_drivetrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.m_drivetrain.driveByPercent(leftSpeed, rightSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_drivetrain.driveByPercent(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Robot.m_drivetrain.getDriveEncoderDistance()[0] >= driveInches || 
       Robot.m_drivetrain.getDriveEncoderDistance()[1] >= driveInches) {
      return true;
    }

    return false;
  }
}
