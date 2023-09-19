// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoDrive extends CommandBase {

  double speed;
  double turn;
  double setpoint;

  /** Creates a new AutoDrive. */
  public AutoDrive(double speed, double turn, double setpoint) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.DriveSubsystem);
    this.speed = speed;
    this.turn = turn;
    this.setpoint = setpoint;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.DriveSubsystem.arcadeDrive(speed, turn);
    SmartDashboard.putBoolean("auto drive enabled", true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.DriveSubsystem.driveReset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Robot.DriveSubsystem.driveEncoderLimitReached(setpoint)) {
      return true;
    }
    return false;
  }
}
