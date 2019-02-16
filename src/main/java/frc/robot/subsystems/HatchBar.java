/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HatchBarCommand;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class HatchBar extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX masterBarLift;
  VictorSPX slaveBarLift;

  DoubleSolenoid piston;

  VictorSP vacuumMotor1;
  VictorSP vacuumMotor2;

  public HatchBar(){
    piston = new DoubleSolenoid(RobotMap.solenoidForwardChannel, RobotMap.solenoidReverseChannel);

    vacuumMotor1 = new VictorSP(RobotMap.vacuumMotor1);
    vacuumMotor2 = new VictorSP(RobotMap.vacuumMotor2);
    

      masterBarLift = new TalonSRX(RobotMap.hatchBarTalonSRX);
      slaveBarLift = new VictorSPX(RobotMap.hatchBarVictorSPX);

      slaveBarLift.set(ControlMode.Follower, RobotMap.hatchBarTalonSRX);
  }

  public void Lift(double liftPower) {
      masterBarLift.set(ControlMode.PercentOutput, liftPower);
  } 

  //PNEUMATICS

  public void pneumaticsExtend() {
    piston.set(DoubleSolenoid.Value.kForward);
    System.out.println("Pneumatics forward");
  }

  public void pneumaticsRetract() {
    piston.set(DoubleSolenoid.Value.kReverse);
    System.out.println("Pneumatics reverse");
  }

  public void pneumaticsOff() {
    piston.set(DoubleSolenoid.Value.kOff);
    System.out.println("Pneumatics off");
  }


  //SUCTION

  public void suctionIn() {
    vacuumMotor1.set(1);
    vacuumMotor2.set(1);
  }

  public void suctionOff() {
    vacuumMotor1.set(0);
    vacuumMotor2.set(0);
  }

  public void suctionOut() {
    vacuumMotor1.set(-1);
    vacuumMotor2.set(-1);
  }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HatchBarCommand());
  }
}