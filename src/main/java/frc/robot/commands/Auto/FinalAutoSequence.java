// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.OpenClawCommand;
import frc.robot.commands.CloseClawCommand;
import frc.robot.commands.WristCommand;
import frc.robot.commands.Arm.ArmCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FinalAutoSequence extends SequentialCommandGroup {
  /** Creates a new FinalArmSequence. */
  public FinalAutoSequence() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());


    //oo addCommands(new SolForward(), new ArmCommand(0.05), 
    //  new ArmUpParallel(), new WaitCommand(0.5), new SolReverse(), new ArmDownSequence(), 
    // new TimedDrive(-0.2, 0, 5000));


    addCommands(new ArmUpParallel(), new WaitCommand(0.5), new OpenClawCommand(), 
    new AutoArm(0.1, 0), new TimedDrive(0.4, 0, 7250));
  }
}
