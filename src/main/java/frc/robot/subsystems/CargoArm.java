/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.CargoArmCommand;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class CargoArm extends Subsystem {
  // Put methods  controlling this subsystem
  // here. Call these from Commands.

  TalonSRX cargoLift;
  TalonSRX cargoIntake;

  public CargoArm(){
    cargoLift = new TalonSRX(RobotMap.cargoLift);
    cargoIntake = new TalonSRX(RobotMap.cargoIntake);

  }

  public void Lift(double liftPower) {
      cargoLift.set(ControlMode.PercentOutput, liftPower);
  };


  public void intake() {
      cargoIntake.set(ControlMode.PercentOutput, 1);
  }
  public void spit() {
      cargoIntake.set(ControlMode.PercentOutput, -1);
  }
  public void off() {
      cargoIntake.set(ControlMode.PercentOutput, 0);
  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoArmCommand());
  }
}
