/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.OI;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveCommand extends CommandBase {

  int cycle = 0;

  double prevSpeed = 0;
  double fCounter = 0;
  double bCounter = 0;

  public DriveCommand() {
    addRequirements(Robot.DriveSubsystem);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  public void execute() {

    Robot.DriveSubsystem.ArcadeDrive(OI.getSpeed(), OI.getTurn());

  }

  // Make this return true when this Command no longer needs to run execute()
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}
