// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class WristSubsystem extends SubsystemBase {

  CANSparkMax wristMotor;
  RelativeEncoder wristEncoder;

  DigitalInput wristLimitSwitch;
  boolean wristEnabled;


  /** Creates a new IntakeWristSubsystem. */
  public WristSubsystem() {
    wristMotor = new CANSparkMax(RobotMap.wristMotor, MotorType.kBrushless);
    wristEncoder = wristMotor.getEncoder();
    wristLimitSwitch = new DigitalInput(RobotMap.wristLimitSwitch);
  }

  public void intakeWrist(double speed) {
    if (speed < 0) {
      if (limitSwitchClosed()) {
        speed = 0;
      }
    }
    wristMotor.set(speed);
    SmartDashboard.putNumber("intake wrist position", wristEncoder.getPosition());
  }

  public boolean wristLimitReached(double setpoint) {
    double encoderPosition = Math.abs(wristEncoder.getPosition());
    if (encoderPosition >= setpoint) {
      return true;
    } else {
      return false;
    }
  }

  public void wristReset() {
    wristMotor.set(0.0);
    wristEncoder.setPosition(0);
  }

  public boolean limitSwitchClosed() {
    if (wristLimitSwitch.get()) {
      SmartDashboard.putBoolean(" wrist switch closed", true);
      wristEnabled = false;
      return true;
    } else {
      SmartDashboard.putBoolean("wrist switch closed", false);
      wristEnabled = true;
      return false;
    }
  }


}
