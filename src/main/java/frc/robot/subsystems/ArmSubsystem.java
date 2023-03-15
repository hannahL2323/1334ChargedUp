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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmSubsystem extends SubsystemBase {

  CANSparkMax armMotorOne;
  // CANSparkMax armMotorTwo;

  DigitalInput limitSwitch;
  RelativeEncoder armEncoderOne;
  // RelativeEncoder armEncoderTwo;

  double threshold = 3;
  boolean toggle = false;

  public ArmSubsystem() {
    armMotorOne = new CANSparkMax(RobotMap.armMotorOne, MotorType.kBrushless);
    // armMotorTwo = new CANSparkMax(RobotMap.armMotorTwo, MotorType.kBrushless);
    limitSwitch = new DigitalInput(RobotMap.limitSwitch);

    // armMotorTwo.setInverted(true);

    armEncoderOne = armMotorOne.getEncoder();
    // armEncoderTwo = armMotorTwo.getEncoder();


  }

  public boolean encoderLimitReached() {
    double encoderPosition = Math.abs(armEncoderOne.getPosition());

    if (encoderPosition >= threshold) {
      return true;
    } else {
      return false;
    }
  }


  public void runArm(double speed) {
    // armMotorOne.set(speed * 0.1);
    // armMotorTwo.set(speed * 0.1);

    armMotorOne.set(speed);
    // armMotorTwo.set(speed);

    SmartDashboard.putNumber("#1 arm position", armEncoderOne.getPosition());
    // SmartDashboard.putNumber("#2 arm position", armEncoderTwo.getPosition());
    SmartDashboard.putNumber("#1 arm velocity", armEncoderOne.getVelocity());
    // SmartDashboard.putNumber("#2 arm velocity", armEncoderTwo.getVelocity());
  }

  public void armReset() {
    armMotorOne.set(0);
    // armMotorTwo.set(0);
    armEncoderOne.setPosition(0);
    // armEncoderTwo.setPosition(0);
    toggle = !toggle;
  }

  public boolean toggleForward() {
    if (toggle) {
      return true;
    } else {
      return false;
    }
  }

  // public boolean limitSwitchClosed() {
  //   if (limitSwitch.get()) {
  //     SmartDashboard.putBoolean("switch closed", true);
  //     return true;
  //   } else {
  //     SmartDashboard.putBoolean("switch closed", false);
  //     return false;
      
  //   }

  // }

  


}

  

