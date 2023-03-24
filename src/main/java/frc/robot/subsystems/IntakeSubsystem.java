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
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DigitalInput;

public class IntakeSubsystem extends SubsystemBase {
  CANSparkMax wristMotor;
  RelativeEncoder wristEncoder;
  CANSparkMax intakeWheel;

  DigitalInput wristLimitSwitch;

  DoubleSolenoid intakeSol;

  /** Creates a new IntakeSubsystem. */
  public IntakeSubsystem() {
    wristMotor = new CANSparkMax(RobotMap.wristMotor, MotorType.kBrushless);
    wristEncoder = wristMotor.getEncoder();
    intakeWheel = new CANSparkMax(RobotMap.intakeWheel, MotorType.kBrushless);

    wristLimitSwitch = new DigitalInput(RobotMap.wristLimitSwitch);

    intakeSol = new DoubleSolenoid(11, PneumaticsModuleType.REVPH, 0, 1);
  }

  public void intakeWrist(double speed) {
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

  public void intakeWheels(double speed) {
    intakeWheel.set(speed);
  }

  public void solForward() {
    intakeSol.set(Value.kForward);
    SmartDashboard.putBoolean("solenoid forward", true);
  }

  public void solReverse() {
    intakeSol.set(Value.kReverse);
    SmartDashboard.putBoolean("solenoid reverse", true);
  }

  public void solOff() {
    intakeSol.set(Value.kOff);
  }

  public boolean limitSwitchClosed() {
    if (wristLimitSwitch.get()) {
      SmartDashboard.putBoolean("switch closed", true);
      return true;
    } else {
      SmartDashboard.putBoolean("switch closed", false);
      return false;
    }
  }
}
