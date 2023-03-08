// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType; 
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmSubsystem extends SubsystemBase {

  CANSparkMax armMotor;
  DigitalInput limitSwitch;
  RelativeEncoder armEncoder;
  double threshold = 3;

  public ArmSubsystem() {
    armMotor = new CANSparkMax(RobotMap.armMotor, MotorType.kBrushless);
    limitSwitch = new DigitalInput(RobotMap.limitSwitch);
    armEncoder = armMotor.getEncoder();
  }

  public boolean encoderLimitReached() {
    double encoderPosition = armEncoder.getPosition();

    if (encoderPosition >= threshold) {
      return true;
    } else {
      return false;
    }
  }

  public boolean limitSwitchClosed() {
    if (limitSwitch.get()) {
      SmartDashboard.putBoolean("switch closed", true);
      System.out.println("switch closed");
      return true;
      
    } else {
      SmartDashboard.putBoolean("switch closed", false);
      System.out.println("switch opened");
      return false;
      
    }

  }

  public void runArm(double speed) {
    armMotor.setIdleMode(IdleMode.kCoast);
    armMotor.set(speed);
    System.out.println("arm command is being triggered");
  }

  // public void limitSwitchArm() {
  //   if (limitSwitch.get()) {
  //     SmartDashboard.putBoolean("switch closed", true);
  //     armMotor.set(0.5);
      
  //   } else {
  //     SmartDashboard.putBoolean("switch closed", false);
  //     armMotor.set(0.0);
      
  //   }
  // }

  


}

  

