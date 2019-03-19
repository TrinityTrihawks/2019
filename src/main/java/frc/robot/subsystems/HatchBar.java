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

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
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

  VictorSP vacuumMotor1;
  VictorSP vacuumMotor2;

  Compressor compressor;

  Encoder liftEncoder;

  OI oi;

  public enum SuctionState {
    IN, OUT, OFF;
  }

  SuctionState suctionState = SuctionState.IN;

  // private double outwardsScalar = 100 / 120;
  // private double inwardsScalar = 100 / 30;

  public HatchBar(TalonSRX masterLift, VictorSPX slaveLift, VictorSP vacuumMotor1, VictorSP vacuumMotor2,
                  Compressor compressor, Encoder liftEncoder, OI oi){
    // piston = new DoubleSolenoid(RobotMap.solenoidForwardChannel, RobotMap.solenoidReverseChannel);



    //Grab all the injected hardware
    this.masterBarLift = masterLift;
    this.slaveBarLift = slaveLift;
    this.vacuumMotor1 = vacuumMotor1;
    this.vacuumMotor2 = vacuumMotor2;
    this.compressor = compressor;
    this.liftEncoder = liftEncoder;
    this.oi = oi;

    // Setup
    this.slaveBarLift.set(ControlMode.Follower, RobotMap.hatchBarTalonSRX);
    this.slaveBarLift.setInverted(true);
    suctionOff();

  }

  public void Lift(double liftPower) {
      //This worked on bag and tag day
      //double angle = liftEncoder.get();
      // if (liftPower < 0)
      // {
      //   liftPower *= .5;
      //   if(angle > 45) {
      //     liftPower = 0;
      //   }  
      // }

      //TODO: use gravity compensation method in lift method

      liftPower *= 0.8;
      masterBarLift.set(ControlMode.PercentOutput, liftPower);
      // System.out.println("Hatch bar lift (subsystem): "+liftPower);


      // System.out.println("Compressor enabled: " + compressor.enabled());
  }

  public double getEncoderValue() {
    return liftEncoder.get();
  }

  
  public double getMasterLiftVoltage() {
    return masterBarLift.getMotorOutputVoltage();
  }
  public double getSlaveLiftVoltage() {
    return slaveBarLift.getMotorOutputVoltage();
  }


  public double getArmAngle() {
    return (-1 * getEncoderValue()) + 90 + RobotMap.hatchBarStartingAngle;
    //This assumes that the unscaled encoder value increases as the arm moves away from the robot
    //If that is not the case, remove the -1
  }

  public double getGravityCompensation() {
    double encoderAngleInRadians = Math.toRadians(getArmAngle());
    return RobotMap.hatchBarMaintainPos * Math.cos(encoderAngleInRadians);
  }

  public void resetEncoder() {
    liftEncoder.reset();
  }

  //SUCTION

  public void suctionIn() {
    // System.out.println("Suction in");
    suctionState = SuctionState.IN;
    vacuumMotor1.set(-1);
    vacuumMotor2.set(-1);
    compressor.stop();
  }

  public void suctionOff() {
    // System.out.println("Suction off");
    suctionState = SuctionState.OFF;
    vacuumMotor1.set(0);
    vacuumMotor2.set(0);
    compressor.stop();
  }

  public void suctionOut() {
    // System.out.println("Suction out");
    suctionState = SuctionState.OUT;
    vacuumMotor1.set(0);
    vacuumMotor2.set(0);
    compressor.start();
  }

  public SuctionState getSuctionState() {
    return suctionState;
  }

  public boolean isCompressorEnabled() {
    return compressor.enabled();
  }

  // public double getArmAngle() {
  //   // System.out.println("Raw hatch angle:"+ liftEncoder.get());
  //   return (liftEncoder.get() + armStartingAngle) / 1.7;
  // }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HatchBarCommand(this, oi));
  }
}
