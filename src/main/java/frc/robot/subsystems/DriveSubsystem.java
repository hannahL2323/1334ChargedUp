/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.OI;
import frc.robot.RobotMap;

// import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.RelativeEncoder;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.motorcontrol.Talon;


/**
 * Add your docs here.
 */
public class DriveSubsystem extends SubsystemBase {

  AHRS ahrs = new AHRS(SPI.Port.kMXP);

  CANSparkMax Left1;
  CANSparkMax Left2;
  CANSparkMax Right1;
  CANSparkMax Right2;
  RelativeEncoder driveEncoder;
  double rampValue;
 
  public DriveSubsystem() {
    rampValue = 0.3;

    Left1 = new CANSparkMax(RobotMap.Left1, MotorType.kBrushless);
    Left2 = new CANSparkMax(RobotMap.Left2, MotorType.kBrushless);
    Right1 = new CANSparkMax(RobotMap.Right1, MotorType.kBrushless);
    Right2 = new CANSparkMax(RobotMap.Right2, MotorType.kBrushless);

    Left1.setOpenLoopRampRate(rampValue);
    Left2.setOpenLoopRampRate(rampValue);
    Right1.setOpenLoopRampRate(rampValue);
    Right2.setOpenLoopRampRate(rampValue);

    driveEncoder = Left1.getEncoder();

    Right1.setInverted(true);
    Right2.setInverted(true);
  }

  public double speedRamp(double speed) {
    if (speed > 0) {
      return 0.2 * (Math.pow(speed, 3)) + 0.8 * (Math.pow(speed, 2));
    } else {
      speed = speed * -1;
    }
    return -1 * (0.2 * (Math.pow(speed, 3)) + 0.8 * (Math.pow(speed, 2)));
  }

  public void TankDrive (double left, double right) {
    // Drive the left and right sides of the neos
    Left1.set(left);
    Left2.set(left);
    Right1.set(right);
    Right2.set(right);
  }


  public void ArcadeDrive (double speed, double turn) {
    speed = speedRamp(speed);
    turn = speedRamp(turn);

    TankDrive((speed + turn), (speed - turn));
    SmartDashboard.putNumber("drive encoder", driveEncoder.getPosition());
  }

  // public void autoBalance() {
  //   double threshold = 10.0; // adjust as needed

  //   double yaw = ahrs.getAngle();
  //   double pitch = ahrs.getPitch();
  //   double roll = ahrs.getRoll(); 
  //   double left;
  //   double right;
    
  //   if (Math.abs(yaw) > threshold) {
  //     SmartDashboard.putBoolean("balance enabled", true);
  //     // If the pitch angle exceeds the threshold, reverse the direction of the motors
  //     double yawRadian = yaw * (Math.PI / 180.0);
  //     left = Math.sin(yawRadian) * -0.5;
  //     right = Math.sin(yawRadian) * -0.5;

  //     Left1.set(left);
  //     Left2.set(left);
  //     Right1.set(right);
  //     Right2.set(right);
  //   }   

  //   SmartDashboard.putNumber("pitch", pitch);
  //   SmartDashboard.putNumber("roll", roll);
  //   SmartDashboard.putNumber("yaw", yaw);
  // }

  public void driveReset(){
    ArcadeDrive(0, 0);
    driveEncoder.setPosition(0);
  }

  public boolean driveEncoderLimitReached(double setpoint) {
    double encoderPosition = Math.abs(driveEncoder.getPosition());
    if (encoderPosition >= setpoint) {
      return true;
    } else {
      return false;
    }
  }


}