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

    // driver buttons
    public JoystickButton aButton;
    public JoystickButton bButton;
    public JoystickButton xButton;
    public JoystickButton yButton;

    // Driver bumpers
    public JoystickButton leftBumper;
    public JoystickButton rightBumper;

    

    
    // public static double getSpeed () {
    //     return ((Driver.getRightTriggerAxis() - Driver.getLeftTriggerAxis()) * Robot.m_Chooser.getSelected());
    // }

    public OI() {
        bButton = new JoystickButton(Driver, 2);
        bButton.whileTrue(new BalanceCommand());


    }

    public static double getSpeed () {


        // if (Math.abs(Driver.getRightTriggerAxis() - Driver.getLeftTriggerAxis()) > 0.15) {

            return (Driver.getRightTriggerAxis() - Driver.getLeftTriggerAxis());
        
        }
            //maybe-0.5hereONLYiftested
        

    public static double getTurn () {
        return (Driver.getRawAxis(0)) * 0.75;
    }
    

}
