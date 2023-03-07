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
     

    public static XboxController Driver;

    // driver buttons
    public JoystickButton aButton;
    public JoystickButton bButton;
    public JoystickButton xButton;
    public JoystickButton yButton;

    // Driver bumpers
    public JoystickButton leftBumper;
    public JoystickButton rightBumper;


    public OI() {

        Driver = new XboxController(0);
        aButton = new JoystickButton(Driver, 1);
        bButton = new JoystickButton(Driver, 2);
        xButton = new JoystickButton(Driver, 3);
        yButton = new JoystickButton(Driver, 4);

        
        aButton.whileTrue(new ArmCommand());


    }

    public static boolean getAutoBalance() {
        return Driver.getXButton();
    }

    public static double getSpeed () {


        // if (Math.abs(Driver.getRightTriggerAxis() - Driver.getLeftTriggerAxis()) > 0.15) {

            return (Driver.getRightTriggerAxis() - Driver.getLeftTriggerAxis());
        
        }
        

    public static double getTurn () {
        return (Driver.getRawAxis(0)) * 0.75;
    }
    

}
