/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Pneumatics extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid suction;
 

  public Pneumatics(){
    suction = new DoubleSolenoid(RobotMap.solenoidForwardChannel, RobotMap.solenoidReverseChannel);
  }

  public void goForward() {
    suction.set(DoubleSolenoid.Value.kForward);
  }

  public void goBackwards() {
    suction.set(DoubleSolenoid.Value.kReverse);
  }

  public void off() {
    suction.set(DoubleSolenoid.Value.kOff);
  }


  @Override
  public void initDefaultCommand() {
    // setDefaultCommand();
  }
}
