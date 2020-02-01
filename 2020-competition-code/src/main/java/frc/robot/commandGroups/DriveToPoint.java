/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.FaceAngle;
import frc.robot.commands.DriveDistance;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveToPoint extends SequentialCommandGroup {
  /**
   * Creates a new DriveToPoint.
   */
  public DriveToPoint(double X, double Y) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super();
    
    double angle = Math.atan(Y/X);
    double hyp = Math.sqrt((X*X) + (Y*Y));

    addCommands(
      
    new FaceAngle(angle),
    
    new DriveDistance(hyp) 
    
    );
  }
}