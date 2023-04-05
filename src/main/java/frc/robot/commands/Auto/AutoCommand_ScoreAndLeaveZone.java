// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.OpenClawCommand;

public class AutoCommand_ScoreAndLeaveZone extends SequentialCommandGroup {

  public AutoCommand_ScoreAndLeaveZone() {

      // Move the arm into the scoring position
      // was previously ArmUpParallel
      addCommands(new AutoArm(-0.1, -17)
          .alongWith(new AutoWrist(-0.15, -25)));

      // Wait 0.5 seconds
      addCommands(new WaitCommand(0.5));
      
      // Open the Pincher
      addCommands(new OpenClawCommand());
      
      // Move the arm back to the top
      addCommands(new AutoArm(0.1, 0));
      
      // Drive out of the zone
      addCommands(new TimedDrive(0.4, 0, 7250));
  }
}
