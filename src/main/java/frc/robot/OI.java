/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Robot;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * Add your docs here
 */
public class OI {
     

    public static XboxController Driver = new XboxController(0);
    public static XboxController Operator = new XboxController(1);


    // driver buttons
    public JoystickButton driverA = new JoystickButton(Driver, 1);
    public JoystickButton driverB = new JoystickButton(Driver, 2);
    public JoystickButton driverX = new JoystickButton(Driver, 3);
    public JoystickButton driverY = new JoystickButton(Driver, 4);
    // Driver bumpers
    public JoystickButton driverLeftBumper = new JoystickButton(Driver, 5);
    public JoystickButton driverRightBumper = new JoystickButton(Driver, 6);

    // operator buttons
    public JoystickButton operatorA = new JoystickButton(Operator, 1);
    public JoystickButton operatorB = new JoystickButton(Operator, 2);
    public JoystickButton operatorX = new JoystickButton(Operator, 3);
    public JoystickButton operatorY = new JoystickButton(Operator, 4);
    // operator bumpers
    public JoystickButton operatorLeftBumper = new JoystickButton(Operator, 5);
    public JoystickButton operatorRightBumper = new JoystickButton(Operator, 6);


    public OI() {
       operatorA.whileTrue(new ArmCommand(-0.1));
       operatorB.whileTrue(new ArmCommand(0.1));

       operatorX.whileTrue(new IntakeWristCommand(-0.1));
       operatorY.whileTrue(new IntakeWheelCommand());
       
       operatorRightBumper.onTrue(new SolForward());
       operatorLeftBumper.onTrue(new SolReverse());
       
       driverLeftBumper.whileTrue(new AutoBalance());
    }

    // public static boolean getAutoBalance() {
    //     return Driver.getRawButton(5);
    // }

    public static double getSpeed() {
        if (Math.abs(Driver.getRightTriggerAxis() - Driver.getLeftTriggerAxis()) > 0.04) {
            return (Driver.getRightTriggerAxis() - Driver.getLeftTriggerAxis());
        } else {
            return 0.0;
        }
    }

    public static double getTurn () {
        if (Math.abs(Driver.getRawAxis(0)) > 0.04) {
            return (Driver.getRawAxis(0));
        } else {
            return 0.0;
        }
    }
    

}
