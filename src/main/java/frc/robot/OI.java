/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  public Joystick controller = new Joystick(RobotMap.controller);

  public double getJoystickVerticalAxis() {
    return -1 * controller.getRawAxis(RobotMap.verticalAxis);
  }

  public double getJoystickTwist() {
    return controller.getTwist();
  }

  public double getSlider() {
		double SliderVal = (controller.getRawAxis(3) + 1) / 2;
		return SliderVal;
  }
  
  public boolean getJoystickTrigger() {
    // System.out.println("Pressed trigger");
    return controller.getTrigger();
  }

  public boolean getJoystickTopLeftButton() {
    // System.out.println("Pressed top left button");
    return controller.getRawButton(4);
  } 

  public boolean getJoystickTopRightButton() {
    // System.out.println("Pressed top right button");
    return controller.getRawButton(5);
  }

  public void testAllButtons() {
    // if(controller.getRawButton(1)) {
    //   System.out.println("Pressed button 1");
    // } else if(controller.getRawButton(2)) {
    // System.out.println("Pressed button 2");
    // } else if(controller.getRawButton(3)) {
    // System.out.println("Pressed button 3");
    // } else if(controller.getRawButton(4)) {
    // System.out.println("Pressed button 4");
    // } else if(controller.getRawButton(5)) {
    //   System.out.println("Pressed button 5");
    // } else if(controller.getRawButton(6)) {
    //   System.out.println("Pressed button 6");
    // } else if(controller.getRawButton(7)) {
    //   System.out.println("Pressed Button 7");
    // } else if(controller.getRawButton(8)) {
    //   System.out.println("Pressed button 8");
    // } else if(controller.getRawButton(9)) {
    //   System.out.println("Pressed button 9");
    // } else if(controller.getRawButton(10)) {
    //   System.out.println("Pressed button 10");
    // } else if(controller.getRawButton(11)) {
    //   System.out.println("Pressed button 11");
    // } else if(controller.getRawButton(12)) {
    //   System.out.println("Pressed button 12");
    // }
  }
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());


}
