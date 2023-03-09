// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ArmCommand extends CommandBase {
  /** Creates a new ArmCommand. */
  public ArmCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.ArmSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Robot.ArmSubsystem.toggleForward()) {
      Robot.ArmSubsystem.runArm(0.05);
    } else {
      Robot.ArmSubsystem.runArm(-0.05);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.ArmSubsystem.runArm(0.0);
    Robot.ArmSubsystem.armReset();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Robot.ArmSubsystem.encoderLimitReached()) {
      return true;
    }
    else {
      return false;
    }
    // return false;

  }
  
}
