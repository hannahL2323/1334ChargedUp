// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.Robot;
import frc.robot.commands.AutoBalance;
import frc.robot.commands.SolForward;
import frc.robot.commands.Intake.ArmCommand;
import frc.robot.commands.Intake.IntakeOutSequence;
import frc.robot.commands.Intake.IntakeParallel;
import frc.robot.commands.Intake.IntakeWheelCommand;
import frc.robot.commands.Intake.IntakeWristCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SideAutoSequence extends SequentialCommandGroup {
  /** Creates a new AutoSequence. */
  public SideAutoSequence() {
    // double driveSpeed = 0.5;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    
    // addCommands(new AutoArm(-0.05, 10), new IntakeWristCommand(-0.1), new AutoIntakeWheel(2000, -0.3), 
    // new AutoDrive(-0.2, 0, 10));

    addCommands(new IntakeParallel());

    // addCommands(new TimedDrive(-0.2, 0, 5000));

    // addCommands(new ArmCommand(0.05),  
    // new AutoDrive(-0.5, 0, 5));

    // addCommands(new AutoDrive(-0.1, 0, 10));
  }
}