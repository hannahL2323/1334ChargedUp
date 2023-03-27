// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands.Auto;

// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// import frc.robot.commands.Intake.ArmCommand;
// import frc.robot.commands.Intake.IntakeWristCommand;
// import frc.robot.commands.Auto.AutoIntakeWheel;
// import frc.robot.commands.SolForward;
// import frc.robot.commands.Auto.AutoDrive;
// import frc.robot.commands.AutoBalance;


// // NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// // information, see:
// // https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
// public class MiddleAutoSequence extends SequentialCommandGroup {
//   /** Creates a new MiddleAutoSequence. */
//   public MiddleAutoSequence() {
//     double driveSpeed = 0.5;
//     // Add your commands in the addCommands() call, e.g.
//     // addCommands(new FooCommand(), new BarCommand());
//     addCommands(new ArmCommand(-0.1), new IntakeWristCommand(-0.1), new AutoIntakeWheel(2000, -0.3), 
//     new SolForward(), new AutoDrive(driveSpeed, 0, 10), 
//     new AutoDrive(driveSpeed, -0.5, 5), 
//     new AutoDrive(driveSpeed, 0, 10), 
//     new AutoDrive(driveSpeed, 0.5, 5), new AutoDrive(driveSpeed, 0, 10), 
//     new AutoBalance());  }
// }
