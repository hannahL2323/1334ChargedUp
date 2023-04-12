// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.BalanceCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.OpenClawCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoCommand_ScoreAndBalance extends SequentialCommandGroup {
  /** Creates a new AutoCommand_ScoreAndBalance. */
  public AutoCommand_ScoreAndBalance() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    addCommands(new AutoArm(-0.35, -17)
          .alongWith(new AutoWrist(-0.25, -25)));

    // Open the Pincher
    addCommands(new OpenClawCommand());
    
    addCommands(new AutoWrist(0.25, -5));

    addCommands(new AutoArm(0.35, 25));
    
    addCommands(new TimedDrive(0.6, 0, 3500));

    addCommands(new TimedDrive(-0.4, 0, 2000));
    
    addCommands(new BalanceCommand());
  }
}