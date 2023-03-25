// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class IntakeWristCommand extends CommandBase {
  double speed;

  /** Creates a new IntakeWristCommand. */
  public IntakeWristCommand(double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.IntakeWristSubsystem);

    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.IntakeWristSubsystem.intakeWrist(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.IntakeWristSubsystem.wristReset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Robot.IntakeWristSubsystem.wristLimitReached(5)) {
      return true;
    }
    // if (Robot.IntakeSubsystem.limitSwitchClosed()) {
    //   return true;
    // }
    return false;
  }
}
