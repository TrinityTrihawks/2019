/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.commands.CargoArmCommand;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class CargoArm extends Subsystem implements Loggable {
  // Put methods  controlling this subsystem
  // here. Call these from Commands.

  private final TalonSRX cargoLift;
  private final TalonSRX cargoIntake;

  @Log.Exclude
  private final OI oi;

  public CargoArm(TalonSRX cargoLift, TalonSRX cargoIntake, OI oi){
    // These hardware objects are "injected" to this class via the constructor
    // This allows us to instantiate this class with mocked hardware so that we
    // test its functionality away from the robot
    this.cargoLift = cargoLift;
    this.cargoIntake = cargoIntake;
    this.oi = oi;

    this.cargoIntake.setNeutralMode(NeutralMode.Brake);
  }

  public void Lift(double liftPower) {
      cargoLift.set(ControlMode.PercentOutput, liftPower);
  };


  public void intake() {
    //   System.out.println("Intake intake");
      cargoIntake.set(ControlMode.PercentOutput, -1);
  }
  public void spit() {
    //   System.out.println("Intake spit");
      cargoIntake.set(ControlMode.PercentOutput, 1);
  }
  public void off() {
    //   System.out.println("Intake off");
      cargoIntake.set(ControlMode.PercentOutput, 0);
  }


  @Log(name = "Lift voltage")
  public double getLiftVoltage() {
    return cargoLift.getMotorOutputVoltage();
  }

  @Log(name = "Intake voltage")
  public double getIntakeVoltage() {
    return cargoIntake.getMotorOutputVoltage();
  }


  @Override
  public void initDefaultCommand() {
    //The subsystem really shouldn't have to know anything about the command.
    //This allows us to mock our subystem without it deending on a command
    setDefaultCommand(new CargoArmCommand(this, oi));
  }

  public String configureLogName() {
    return "Cargo Arm";
  }
}
