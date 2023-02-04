/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  

  public static DriveSubsystem DriveSubsystem = new DriveSubsystem();
  public static DriveCommand DriveCommand = new DriveCommand();

  // public static SendableChooser<Double> m_Chooser = new SendableChooser<>();

  public static UsbCamera Camera;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  public void robotInit() {
    // Camera = CameraServer.startAutomaticCapture(RobotMap.camera);
    CameraServer.startAutomaticCapture();
  }

  // public Double getSpeedValue()
  // {
  //   // return m_Chooser.getSelected();
  // }
  
  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  public void autonomousPeriodic() {
  }

  /**
   * This function is called periodically during operator control.
   */
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    DriveCommand.schedule();
  }

  /**
   * This function is called periodically during test mode.
   */
  public void testPeriodic() {
  }

  

}
