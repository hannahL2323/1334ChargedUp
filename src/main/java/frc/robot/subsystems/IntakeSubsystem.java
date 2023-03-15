// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMaxLowLevel.MotorType; 
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class IntakeSubsystem extends SubsystemBase {
  CANSparkMax intakeMotor;
  RelativeEncoder intakEncoder;
  boolean toggle = true;
  double threshold = 1;

  // CANSparkMax intakeWheel;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(RobotMap.intakeMotor, MotorType.kBrushless);
    intakEncoder = intakeMotor.getEncoder();
    // intakeWheel = new CANSparkMax(RobotMapintakeWheel, MotorType.kBrushless);
  }

  public void intakeWrist(double speed) {
    intakeMotor.set(speed);
    SmartDashboard.putNumber("intake wrist position", intakEncoder.getPosition());
  }

  public void intakeReset() {
    intakeMotor.set(0.0);
    intakEncoder.setPosition(0);
    toggle = !toggle;
  }

  public boolean toggleForward() {
    if (toggle) {
      return true;
    } else {
      return false;
    }
  }

  public boolean encoderLimitReached() {
    double encoderPosition = Math.abs(intakEncoder.getPosition());
    if (encoderPosition >= threshold) {
      return true;
    } else {
      return false;
    }
  }

  public void intakeWheels(double speed) {
    // intakeWheel.set(speed);
  }
}
