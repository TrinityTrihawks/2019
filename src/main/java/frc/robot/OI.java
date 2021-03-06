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

  private MainJoystick main = new MainJoystick();
  private AuxiliaryJoystick aux = new AuxiliaryJoystick();

  public MainJoystick getMain() {
    return main;
  }

  public AuxiliaryJoystick getAuxiliary() {
    return aux;
  }

  public class Button {

    Joystick joy;
    int id;

    public Button(Joystick joy, int id) {
      this.joy = joy;
      this.id = id;
    }

    public boolean isPressed() {
      //Note: the logger also calls this method
      return joy.getRawButton(id);
    }
    public boolean wasJustPressed() {
      return joy.getRawButtonPressed(id);
    }
    public boolean wasJustReleased() {
      return joy.getRawButtonReleased(id);
    }
  }


  public class MainJoystick {
    private Joystick joy = new Joystick(RobotMap.controller);

    Button trigger = new Button(joy, 1);
    Button sideThumb = new Button(joy, 2);
    Button keypad11 = new Button(joy, 11);
    Button keypad12 = new Button(joy, 12);

    public double getTwist() {
      return joy.getTwist();
    }
    public double getVerticalAxis() {
      return -1 * joy.getRawAxis(1);
    }
    public double getSlider() {
      return (-1 * joy.getRawAxis(3) + 1) / 2;
    }
    public Button getTrigger() {
      return trigger;
    }
    public Button getSideThumb() {
      return sideThumb;
    }
    public Button getKeypad11() {
      return keypad11;
    }
    public Button getKeypad12() {
      return keypad12;
    }

    /**
     * Only use if no other methods get your desired button
     */
    public Button getButtonWithId(int id) {
      return new Button(joy, id);
    }
  }

  public class AuxiliaryJoystick {
    private Joystick xbox = new Joystick(RobotMap.XboxController);

    Button x = new Button(xbox, RobotMap.XboxButtonX);
    Button y = new Button(xbox, RobotMap.XboxButtonY);
    Button a = new Button(xbox, RobotMap.XboxButtonA);
    Button b = new Button(xbox, RobotMap.XboxButtonB);

    Button leftBumper = new Button(xbox, RobotMap.XboxLeftBumper);
    Button rightBumper = new Button(xbox, RobotMap.XboxRightBumper);
    Button leftTrigger = new Button(xbox, RobotMap.XboxLeftTrigger);
    Button rightTrigger = new Button(xbox, RobotMap.XboxRightTrigger);


    public double getLeftVerticalAxis() {
      return xbox.getRawAxis(RobotMap.XboxLeftAxis);
    }
    public double getRightVerticalAxis() {
      return xbox.getRawAxis(RobotMap.XboxRightAxis);
    }
    public Button getButtonX() {
      return x;
    }
    public Button getButtonY() {
      return y;
    }
    public Button getButtonA() {
      return a;
    }
    public Button getButtonB() {
      return b;
    }
    public Button getLeftBumper() {
      return leftBumper;
    }
    public Button getRightBumper() {
      return rightBumper;
    }
    public Button getLeftTrigger() {
      return leftTrigger;
    }
    public Button getRightTrigger() {
      return rightTrigger;
    }

    /**
     * Only use if no other methods get your desired button
     */
    public Button getButtonWithId(int id) {
      return new Button(xbox, id);
    }

  }

}

/*
  public void testAllButtons() {
    if(controller.getRawButton(1)) {
      System.out.println("Pressed button 1");
    } else if(controller.getRawButton(2)) {
    System.out.println("Pressed button 2");
    } else if(controller.getRawButton(3)) {
    System.out.println("Pressed button 3");
    } else if(controller.getRawButton(4)) {
    System.out.println("Pressed button 4");
    } else if(controller.getRawButton(5)) {
      System.out.println("Pressed button 5");
    } else if(controller.getRawButton(6)) {
      System.out.println("Pressed button 6");
    } else if(controller.getRawButton(7)) {
      System.out.println("Pressed Button 7");
    } else if(controller.getRawButton(8)) {
      System.out.println("Pressed button 8");
    } else if(controller.getRawButton(9)) {
      System.out.println("Pressed button 9");
    } else if(controller.getRawButton(10)) {
      System.out.println("Pressed button 10");
    } else if(controller.getRawButton(11)) {
      System.out.println("Pressed button 11");
    } else if(controller.getRawButton(12)) {
      System.out.println("Pressed button 12");
    }
  }

`*/






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
