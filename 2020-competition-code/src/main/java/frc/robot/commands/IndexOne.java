/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class IndexOne extends CommandBase {
  /**
   * Creates a new IndexOne.
   */
  int currentBalls;
  public IndexOne() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_conveyor);
    currentBalls = Robot.m_conveyor.GetBallsStored();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (currentBalls != 5)
    {
      Robot.m_conveyor.UpConveyor();
    }
    else
    {
      end(true);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_conveyor.StopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Robot.m_conveyor.GetBallsStored() == (currentBalls + 1)) {
      return true;
    }
    return false;
  }
}
