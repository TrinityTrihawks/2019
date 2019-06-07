/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.CargoArm;

/**
 * An example command.  You can replace me with your own command.
 */
public class CargoArmCommand extends Command {

    CargoArm cargoArm;
    OI oi;

    private final double powerScalarTowardsBody = 0.5;
    private final double powerScalarAwayFromBody = 0.4;


  public CargoArmCommand(CargoArm cargoArm, OI oi) {
    this.cargoArm = cargoArm;
    this.oi = oi;

    // Use requires() here to declare subsystem dependencies
    requires(this.cargoArm);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double power = oi.getAuxiliary().getRightVerticalAxis();
    // System.out.println("Cargo arm joystick "+power);


    // Positive power: towards body
    // Negative power: away from body
    if(power > 0) {
        power *= powerScalarTowardsBody;
    } else {
        power *= powerScalarAwayFromBody;
    }


    // System.out.println("Cargo arm power: "+power);
    cargoArm.Lift(power);
    // System.out.println("Cargo arm: "+ power);

    //TODO: override limit switch to intake/spit ball

    if(oi.getAuxiliary().getButtonX().isPressed()) {
        cargoArm.intake();
    } else if(oi.getAuxiliary().getButtonB().isPressed()) {
        cargoArm.spit();
    } else {
        cargoArm.off();
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
      return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
      end();
  }
}
