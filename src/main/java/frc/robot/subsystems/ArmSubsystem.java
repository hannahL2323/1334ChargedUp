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

  CANSparkMax armMotor;
  DigitalInput limitSwitch;
  RelativeEncoder armEncoder;
  boolean limitReached; 

  public ArmSubsystem() {
    armMotor = new CANSparkMax(RobotMap.armMotor, MotorType.kBrushless);
    limitSwitch = new DigitalInput(RobotMap.limitSwitch);
    armEncoder = armMotor.getEncoder();
    limitReached = true; 

  }


  // public void armControl() {
  //   // double encoderPosition = armEncoder.getPosition();
  //   // double threshold = 5;

  //   // if (encoderPosition >= threshold) {
  //   //   armMotor.set(0);
  //   // } else {
  //   //   armMotor.set(0.5);
  //   // }

  //   if (limitSwitch.get()) {
  //     armMotor.set(0);
  //     SmartDashboard.putBoolean("switch closed", true);
  //     System.out.println("switch closed");
  //   } else {
  //     armMotor.set(0.5);
  //     SmartDashboard.putBoolean("switch closed", false);
  //     System.out.println("switch opened");

  //   }
  // }


  public void runArm() {
    armMotor.set(0.5);
  }


}

  

