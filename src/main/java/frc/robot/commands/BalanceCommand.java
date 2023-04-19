// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class BalanceCommand extends CommandBase {

  // TODO: Learn about enums
  private enum Step { CLIMB, BACK_UP, BALANCE }

  private Step currentStep = null;

  double backupSpeed;
  long stepStartTime;

  /** Creates a new BalanceCommand. */
  public BalanceCommand() {

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.DriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double pitch = Robot.DriveSubsystem.getPitch();

    if (Math.abs(pitch) > 4) {
      currentStep = Step.CLIMB;
    } else {
      currentStep = Step.BALANCE;
    }

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    SmartDashboard.putString("Balance Step", currentStep.toString());

    double pitch = Robot.DriveSubsystem.getPitch();

    switch (currentStep) {

    case CLIMB:

      // Climb up the ramp
      Robot.DriveSubsystem.ArcadeDrive(Math.signum(pitch) * 0.4, 0);

      // When the ramp falls so that the angle is less than 4 deg,
      // then back up a little bit.
      if (Math.abs(pitch) <= 4) {
        currentStep = Step.BACK_UP;
        stepStartTime = System.currentTimeMillis();
        backupSpeed = Math.signum(pitch) * -0.4;
      }
      return;

    case BACK_UP:

      // Back up for a short time.
      Robot.DriveSubsystem.ArcadeDrive(backupSpeed, 0);

      // When the time is expired, start to balance.
      if ((System.currentTimeMillis() - stepStartTime) > 700) {
        currentStep = Step.BALANCE;
      }
      return;

    case BALANCE:
      // Drive slowly to the balance point
      Robot.DriveSubsystem.ArcadeDrive(Math.signum(pitch) * 0.1, 0);

      return;
    }

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    double pitch = Robot.DriveSubsystem.getPitch();

    if (currentStep == Step.BALANCE) {
      if (Math.abs(pitch) <= 2) {
        return true;
      }
    }
    return false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Stop when balanced
    Robot.DriveSubsystem.ArcadeDrive(0, 0);
  }
}
