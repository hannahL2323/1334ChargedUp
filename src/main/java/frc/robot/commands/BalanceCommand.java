// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Robot;
import frc.robot.commands.Auto.TimedDrive;

public class BalanceCommand extends CommandBase {
  int climb = 0;
  int backup = 1;
  int pause = 2;
  int balance = 3;
  int currentState;
  double backupSpeed;
  long stateStart;

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
      currentState = climb;
    } else {
      currentState = balance;
    }

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("current state", currentState);

    double pitch = Robot.DriveSubsystem.getPitch();

    if (currentState == climb) {
      Robot.DriveSubsystem.ArcadeDrive(Math.signum(pitch) * 0.4, 0);

      if (Math.abs(pitch) <= 4) {
        currentState = backup;
        stateStart = System.currentTimeMillis();
        backupSpeed = Math.signum(pitch) * -0.4;
      }
      return;
    } else if (currentState == backup) {
      Robot.DriveSubsystem.ArcadeDrive(backupSpeed, 0);

      if ((System.currentTimeMillis() - stateStart) > 1000) {
        currentState = balance;
      }
      return;
    } else if (currentState == balance) {
      Robot.DriveSubsystem.ArcadeDrive(Math.signum(pitch) * 0.1, 0);
    }    


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.DriveSubsystem.ArcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double pitch = Robot.DriveSubsystem.getPitch();    
    if (currentState == balance) {
      if (Math.abs(pitch) <= 2) {
      return true;
      }
    }
    return false;
  }
}
