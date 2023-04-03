/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.commands.*;
import frc.robot.commands.Arm.ArmUpParallel;
import frc.robot.commands.Arm.DefaultArmCommand;
import frc.robot.commands.Auto.AutoDrive;
import frc.robot.commands.Auto.FinalAutoSequence;
// import frc.robot.commands.Auto.MiddleAutoSequence;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  //initializing subsystems
  public static DriveSubsystem DriveSubsystem = new DriveSubsystem();
  public static ArmSubsystem ArmSubsystem = new ArmSubsystem();
  public static IntakeSubsystem IntakeSubsystem = new IntakeSubsystem();
  public static WristSubsystem WristSubsystem = new WristSubsystem();

  public static OI OI = new OI();

  // initializing commands
  // public static ScoringSequence ScoringSequence = new ScoringSequence();
  public static DriveCommand DriveCommand = new DriveCommand();
  CommandScheduler commandScheduler;

  private static Compressor compressor = new Compressor(11, PneumaticsModuleType.REVPH);


  // auto
  Command m_autoCommand = new FinalAutoSequence();
  // private static final String kDefaultAuto = "Default";
  // private static final String kCustomAuto = "My Auto";
  // private String m_autoSelected;
  // private final SendableChooser<String> m_chooser = new SendableChooser<>();

  public static UsbCamera Camera;

  long startTime;



  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    // m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    // m_chooser.addOption("My Auto", kCustomAuto);
    // SmartDashboard.putData("Auto choices", m_chooser);

    Camera = CameraServer.startAutomaticCapture();
    CameraServer.startAutomaticCapture();
    compressor.enableDigital();

    ArmSubsystem.setDefaultCommand(new DefaultArmCommand());
    WristSubsystem.setDefaultCommand(new DefaultWristCommand());
  }

 
  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
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
  @Override
  public void autonomousInit() {
    compressor.enableDigital();

    SmartDashboard.putBoolean("auto enabled", true);

    m_autoCommand = new FinalAutoSequence();

    if (m_autoCommand != null) {
      m_autoCommand.schedule();
    }

    // m_autoSelected = m_chooser.getSelected();
    // System.out.println("Auto selected: " + m_autoSelected);

    startTime = System.currentTimeMillis();

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // switch (m_autoSelected) {
    //   case kCustomAuto:
    //     // Put custom auto code here
    //     commandScheduler.schedule(new SideAutoSequence());
    //     break;
    //   case kDefaultAuto:
    //   default:
    //     // Put default auto code here
    //     commandScheduler.schedule(new SideAutoSequence());
    //     break;
    // }

    // long currentTime = System.currentTimeMillis();

    // if (startTime + 4500 > currentTime) {
    //   DriveSubsystem.ArcadeDrive(0.4, 0);
    // } else {
    //   DriveSubsystem.ArcadeDrive(0, 0);
    // }
 

  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    compressor.enableDigital();

    if (m_autoCommand != null) {
      m_autoCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    DriveCommand.schedule();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  

}
