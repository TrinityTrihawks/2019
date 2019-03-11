/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.cscore.AxisCamera;
import frc.robot.commands.CargoArmCommand;
import frc.robot.commands.TeleopDrive;
import frc.robot.subsystems.CargoArm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchBar;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */

public class Robot extends TimedRobot {


  public static OI oi;
  public static Drivetrain drivetrain = new Drivetrain();
  public static HatchBar hatchBar = new HatchBar();

  private final CargoArm cargoArm;
  private final CargoArmCommand cargoArmCommand;

  public enum DrivePerspectives {
    HATCH, CARGO;
  }
  public static DrivePerspectives drivePerspective = DrivePerspectives.HATCH;


  public Robot() {
    cargoArm = createCargoArm();
    cargoArmCommand = new CargoArmCommand(cargoArm, oi);
  }

  private CargoArm createCargoArm() {
    TalonSRX cargoLift = new TalonSRX(RobotMap.cargoLift);
    TalonSRX cargoIntake = new TalonSRX(RobotMap.cargoIntake);
    return new CargoArm(cargoLift, cargoIntake);
  }






  AxisCamera cameraFront;
  AxisCamera cameraBack;

  final int IMG_WIDTH = 320;
  final int IMG_HEIGHT = 240;

  //TODO: unfold command

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

      //TODO: arm power scalar on Shuffleboard

    cameraFront = CameraServer.getInstance().addAxisCamera("Front Camera", RobotMap.cameraFrontIPAddress);
    cameraBack = CameraServer.getInstance().addAxisCamera("Back Camera", RobotMap.cameraBackIPAddress);
    
    // CameraServer.getInstance().startAutomaticCapture(camera);

    // CameraServer.getInstance().getServer().setSource(source);
    // NetworkTableInstance.getDefault().getTable("").putData("CameraSelection", camera.getName());
		// camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
    //System.out.println("Front camera initialized properly");
    

		 
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    //general robot status
    SmartDashboard.putData(Scheduler.getInstance());
    SmartDashboard.putString("Perspective", drivePerspective.toString());

    //joystick input
    SmartDashboard.putNumber("Joystick vertical axis", oi.getJoystickVerticalAxis());
    SmartDashboard.putNumber("Joystick twist", oi.getJoystickTwist());
    SmartDashboard.putNumber("Joystick slider", oi.getSlider());

    //drivetrain
    SmartDashboard.putData(drivetrain);

    //hatch bar
    SmartDashboard.putData(hatchBar);
    // SmartDashboard.putNumber("Hatch encoder value", hatchBar.getEncoderValue());
    // SmartDashboard.putNumber("Hatch arm angle", hatchBar.getArmAngle());
    // SmartDashboard.putNumber("Hatch gravity compensation", hatchBar.getGravityCompensation());

    //cargo arm
    SmartDashboard.putData(cargoArm);


    //test to see if drive perspective should change
    if(oi.controller.getTriggerPressed()) {
      if(drivePerspective == DrivePerspectives.CARGO) {
        drivePerspective = DrivePerspectives.HATCH;
        System.out.println("Drive perspective switched to HATCH");
      } else {
        drivePerspective = DrivePerspectives.CARGO;
        System.out.println("Drive perspective switched to CARGO");
      }
    }

    //update dashboard camera stream to current drive perspective
    if(drivePerspective == DrivePerspectives.CARGO) {
      NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection").setString(cameraBack.getName());
      // System.out.println("Back camera currently displayed");
    } else {
      NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection").setString(cameraFront.getName());
      // System.out.println("Front camera currently displayed");
    }

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    TeleopDrive teleopDrive = new TeleopDrive();
    teleopDrive.start();

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    hatchBar.resetEncoder();

    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
        oi.testAllButtons();
  }

  public static DrivePerspectives getDrivePerspective() {
    return drivePerspective;
  }
}
