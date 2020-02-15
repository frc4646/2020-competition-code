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
  double incrementAngle;
  enum States{Init, Locate, Center, Done};
  States states;
  int count;
  boolean isGoingRight;

  public FindTarget() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.m_launcher);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      Robot.m_launcher.startPixy2();
      neutralPan = 0;
      neutralTilt = 0; //Placeholder
      turnSpeed = 0.8;
      tolerance = 2;
      isGoingRight = true;
      states = States.Init;

      System.out.println("State Init running!");
      Robot.m_launcher.setServos(neutralPan, neutralTilt);
      states = States.Locate;
      incrementAngle=0;
      count = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //TODO: TEST AND LOOK AT COUNT.
    if (count % 10 == 0)
    {
      switch (states)
      {
        case Locate:
          System.out.println("State Locate running");
          // While the target is not being tracked, pan the Pixy2 to search for it.
          if (!Robot.m_launcher.isBlockVisible())
          {
            if (isGoingRight)
            {
              if (incrementAngle >= 1) isGoingRight = false;
              else 
              {
                incrementAngle+=0.05; 
                Robot.m_launcher.setServos(incrementAngle, neutralTilt);
              }
            }
            else
            {
              if (incrementAngle <= 0) isGoingRight = true;
              else 
              {
                incrementAngle-=0.05; 
                Robot.m_launcher.setServos(incrementAngle, neutralTilt);
              }
            }
          }
          else {states = States.Center; incrementAngle=Robot.m_launcher.getServoPos()[0];}
          break;


        case Center:
          /*System.out.println("State Center running!");
          //Center the Pixy2 to the target.
            if (Robot.m_launcher.getPos()[0] + tolerance < Robot.m_launcher.xMidPos)
            {
              Robot.m_launcher.setServos(incrementAngle, neutralTilt);
              if (incrementAngle == 1) incrementAngle = 0;
              else incrementAngle+=0.05;
            }
            else if (Robot.m_launcher.getPos()[0] - tolerance > Robot.m_launcher.xMidPos)
            {
              Robot.m_launcher.setServos(incrementAngle, neutralTilt);
              if (incrementAngle == 0) incrementAngle = 1;
              else incrementAngle-=0.05;
            }
            else {*/ states = States.Done; //}
          break;

          
        case Done:
          System.out.println("State Done running!");
          Robot.m_launcher.stopPixy2();
          break;


        default:
            System.out.println("Oh no! An enum switch error! What will we ever do!");
            break;

      }
    }
    count++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.m_launcher.stopPixy2();
    System.out.println("Command done running.");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}