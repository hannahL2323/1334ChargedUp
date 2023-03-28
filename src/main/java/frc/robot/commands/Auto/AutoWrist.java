// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeWristSubsystem;

public class AutoWrist extends CommandBase {
  double speed;
  double setpoint;

  /** Creates a new AutoWrist. */
  public AutoWrist(double speed, double setpoint) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.IntakeWristSubsystem);
    this.speed = speed;
    this.setpoint = setpoint;
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
    if (Robot.IntakeWristSubsystem.wristLimitReached(setpoint)) {
      return true;
    }
    return false;
  }
}
