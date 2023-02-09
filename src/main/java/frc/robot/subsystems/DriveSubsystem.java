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
import edu.wpi.first.wpilibj.DriverStation;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


/**
 * Add your docs here.
 */
public class DriveSubsystem extends SubsystemBase {

  AHRS ahrs = new AHRS(SPI.Port.kMXP);

  boolean autoBalanceXMode;
  boolean autoBalanceYMode;
  double xSpeed;
  double ySpeed;
  double yawAngleDegrees = ahrs.getAngle();
  double pitchAngleDegrees = ahrs.getPitch();
  double rollAngleDegrees = ahrs.getRoll();  
  
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

  static final double kOffBalanceAngleThresholdDegrees = 10;
  static final double kOonBalanceAngleThresholdDegrees = 5;

    
  public void AutoBalance() {
     
    if (!autoBalanceXMode && (Math.abs(yawAngleDegrees) >= Math.abs(kOffBalanceAngleThresholdDegrees))) {
        autoBalanceXMode = true;
    } else if (autoBalanceXMode && (Math.abs(yawAngleDegrees) <= Math.abs(kOonBalanceAngleThresholdDegrees))) {
        autoBalanceXMode = false;
    }
    // if (!autoBalanceYMode && (Math.abs(pitchAngleDegrees) >= Math.abs(kOffBalanceAngleThresholdDegrees))) {
    //     autoBalanceYMode = true;
    // } else if (autoBalanceYMode && (Math.abs(pitchAngleDegrees) <= Math.abs(kOonBalanceAngleThresholdDegrees))) {
    //     autoBalanceYMode = false;
    // }



    // Control drive system automatically,
    // driving in reverse direction of pitch/roll angle,
    // with a magnitude based upon the angle

    if (autoBalanceXMode) {
        double yawAngleRadians = yawAngleDegrees * (Math.PI / 180.0);
        xSpeed = Math.sin(yawAngleRadians) * -0.5;
    }
    // if (autoBalanceYMode) {
    //     double rollAngleRadians = rollAngleDegrees * (Math.PI / 180.0);
    //     ySpeed = Math.sin(rollAngleRadians) * -1;
    // }

    try {
      Left1.set(ControlMode.PercentOutput, xSpeed);
      Left2.set(ControlMode.PercentOutput, xSpeed);
      Right1.set(ControlMode.PercentOutput, xSpeed);
      Right2.set(ControlMode.PercentOutput, xSpeed);
    } catch (RuntimeException ex) {
        String err_string = "Drive system error:  " + ex.getMessage();
        DriverStation.reportError(err_string, true);
    }

    System.out.println("xSpeed: " + xSpeed + " yaw: " + yawAngleDegrees);
  }

 
}
