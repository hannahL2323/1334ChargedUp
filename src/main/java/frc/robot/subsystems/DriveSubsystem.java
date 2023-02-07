/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;

// import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends SubsystemBase {

  public boolean slow;
  
  TalonSRX Left1 = new TalonSRX(RobotMap.Left1);
  TalonSRX Left2 = new TalonSRX(RobotMap.Left2);
  TalonSRX Right1 = new TalonSRX(RobotMap.Right1);
  TalonSRX Right2 = new TalonSRX(RobotMap.Right2);

 

  public void TankDrive (double left, double right) {

    // Drive the left and right sides of the talons
    Left1.set(ControlMode.PercentOutput,left * 0.75);
    Left2.set(ControlMode.PercentOutput,left * 0.75);
    Right1.set(ControlMode.PercentOutput,-right);
    Right2.set(ControlMode.PercentOutput,-right);

    
  }

  public void ArcadeDrive (double speed, double turn) {
    TankDrive((speed - turn) * 0.5, (speed + turn) * 0.5);
  }

 
}
