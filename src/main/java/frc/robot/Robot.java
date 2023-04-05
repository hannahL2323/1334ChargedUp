/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
// import frc.robot.commands.Auto.MiddleAutoSequence;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.DefaultWristCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.Arm.DefaultArmCommand;
import frc.robot.commands.Auto.AutoCommand_ScoreAndLeaveZone;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.WristSubsystem;
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
  CommandScheduler commandScheduler;

  private static Compressor compressor = new Compressor(11, PneumaticsModuleType.REVPH);


  // auto
  Command autoCommand = null;
  
   private static final String AUTO_SCORE_CONE_AND_EXIT_ZONE = "Score Cone and Exit";
   private static final String AUTO_DO_NOTHING               = "Do Nothing";
   
   private final SendableChooser<String> autoChooser = new SendableChooser<>();

  public static UsbCamera Camera;

  long startTime;



  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
     
     // Put the auto patterns on the SmartDashboard
     autoChooser.setDefaultOption(AUTO_SCORE_CONE_AND_EXIT_ZONE, AUTO_SCORE_CONE_AND_EXIT_ZONE);
     autoChooser.addOption(AUTO_DO_NOTHING, AUTO_DO_NOTHING);
     SmartDashboard.putData("AutoPattern", autoChooser);

    Camera = CameraServer.startAutomaticCapture();
    CameraServer.startAutomaticCapture();
    compressor.enableDigital();

    DriveSubsystem.setDefaultCommand(new DriveCommand());
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

    // At the beginning of auto, get the selected pattern and schedule the auto
    String selectedAuto = autoChooser.getSelected();
    
    System.out.println("Auto Selected : " + selectedAuto);
    
    switch (selectedAuto) {
    
    case AUTO_DO_NOTHING:
        autoCommand = new InstantCommand();
        break;
        
    case AUTO_SCORE_CONE_AND_EXIT_ZONE:
        autoCommand = new AutoCommand_ScoreAndLeaveZone();
        break;
        
    default:
        // Default to score and exit zone
        autoCommand = new AutoCommand_ScoreAndLeaveZone();
        break;
    }

    autoCommand.schedule();
   
    SmartDashboard.putBoolean("auto enabled", true);

    startTime = System.currentTimeMillis();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    
    compressor.enableDigital();

    if (autoCommand != null) {
      autoCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  

}
