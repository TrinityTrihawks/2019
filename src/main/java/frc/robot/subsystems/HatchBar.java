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
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
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

  // DoubleSolenoid piston;

  VictorSP vacuumMotor1;
  VictorSP vacuumMotor2;

  Compressor compressor;

  Encoder liftEncoder;  

  // private double outwardsScalar = 100 / 120;
  // private double inwardsScalar = 100 / 30;

  public HatchBar(){
    // piston = new DoubleSolenoid(RobotMap.solenoidForwardChannel, RobotMap.solenoidReverseChannel);

    vacuumMotor1 = new VictorSP(RobotMap.vacuumMotor1);
    vacuumMotor2 = new VictorSP(RobotMap.vacuumMotor2);

    compressor = new Compressor(7);
    compressor.start();
  
    masterBarLift = new TalonSRX(RobotMap.hatchBarTalonSRX);
    slaveBarLift = new VictorSPX(RobotMap.hatchBarVictorSPX);

    slaveBarLift.set(ControlMode.Follower, RobotMap.hatchBarTalonSRX);
    slaveBarLift.setInverted(true);

    liftEncoder = new Encoder(RobotMap.hatchBarEncoderSourceA, RobotMap.hatchBarEncoderSourceB);
  }

  public void Lift(double liftPower) {
      System.out.println("Raw hatch angle:"+ liftEncoder.get());

      double angle = liftEncoder.get();
      // double scaledPower;
      // if(liftPower > 0) {
      //   //moving arm away from robot
      //   scaledPower = liftPower * angle / 150;
      // } else {
      //   //moving arm towards robot
      //   scaledPower = liftPower * (angle == 0 ? .25 : angle) / 60;
      // }

      double newPower = liftPower;

      if (liftPower < 0)
      {
        liftPower *= .5;
        if(angle > 45) {
          liftPower = 0;
        }  
      }

      masterBarLift.set(ControlMode.PercentOutput, liftPower);


      // System.out.println("Compressor enabled: " + compressor.enabled());
  } 

  public void resetEncoder() {
    liftEncoder.reset();
  }

  //PNEUMATICS

  // public void pneumaticsExtend() {
  //   piston.set(DoubleSolenoid.Value.kForward);
  //   System.out.println("Pneumatics forward");
  // }

  // public void pneumaticsRetract() {
  //   piston.set(DoubleSolenoid.Value.kReverse);
  //   System.out.println("Pneumatics reverse");
  // }

  // public void pneumaticsOff() {
  //   piston.set(DoubleSolenoid.Value.kOff);
  //   System.out.println("Pneumatics off");
  // }


  //SUCTION

  public void suctionIn() {
    System.out.println("Suction in");
    vacuumMotor1.set(1);
    vacuumMotor2.set(1);
    compressor.stop();
  }

  public void suctionOff() {
    System.out.println("Suction off");
    vacuumMotor1.set(0);
    vacuumMotor2.set(0);
    compressor.stop();
  }

  public void suctionOut() {
    System.out.println("Suction out");  
    vacuumMotor1.set(0);
    vacuumMotor2.set(0);
    compressor.start();
  }

  // public double getArmAngle() {
  //   // System.out.println("Raw hatch angle:"+ liftEncoder.get());
  //   return (liftEncoder.get() + armStartingAngle) / 1.7;
  // }


  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HatchBarCommand());
  }
}
