// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class TimedDrive extends CommandBase {
  long startTime;
  long duration;
  double speed;
  double turn;

  /** Creates a new TimedDrive. */
  public TimedDrive(double speed, double turn, long duration) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.DriveSubsystem);

    this.speed = speed;
    this.turn = turn;
    this.duration = duration;
    startTime = System.currentTimeMillis();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.DriveSubsystem.ArcadeDrive(speed, turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.DriveSubsystem.driveReset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double currentTime = System.currentTimeMillis();
    if (startTime + duration <= currentTime) {
      return true;
    }
    return false;
  }
}
