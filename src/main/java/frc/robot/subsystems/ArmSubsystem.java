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

  CANSparkMax armMotorOne;
  CANSparkMax armMotorTwo;

  DigitalInput limitSwitch;
  RelativeEncoder armEncoder;
  double threshold = 1;
  boolean toggle = true;

  public ArmSubsystem() {
    armMotorOne = new CANSparkMax(RobotMap.armMotorOne, MotorType.kBrushless);
    armMotorTwo = new CANSparkMax(RobotMap.armMotorTwo, MotorType.kBrushless);
    limitSwitch = new DigitalInput(RobotMap.limitSwitch);
    armEncoder = armMotorOne.getEncoder();

    armMotorTwo.setInverted(true);

  }

  public boolean encoderLimitReached() {
    double encoderPosition = Math.abs(armEncoder.getPosition());

    if (encoderPosition >= threshold) {
      return true;
    } else {
      return false;
    }
  }


  public void runArm(double speed) {
    armMotorOne.set(speed);
    armMotorTwo.set(speed);
    SmartDashboard.putNumber("arm encoder", armEncoder.getPosition());
  }

  public void armReset() {
    armEncoder.setPosition(0);
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
  //     System.out.println("switch closed");
  //     return true;
      
  //   } else {
  //     SmartDashboard.putBoolean("switch closed", false);
  //     System.out.println("switch opened");
  //     return false;
      
  //   }

  // }

  


}

  

