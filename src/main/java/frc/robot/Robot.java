/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.cscore.AxisCamera;
import frc.robot.GlobalState;
import frc.robot.GlobalState.DrivePerspectives;
import frc.robot.commands.CargoArmCommand;
import frc.robot.commands.TeleopDrive;
import frc.robot.commands.HatchBarCommand;
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


  private final OI oi;
  private final GlobalState state;

  //Subsystems
  private final Drivetrain drivetrain;
  private final CargoArm cargoArm;
  private final HatchBar hatchBar;

  //Comands
  // private final CargoArmCommand cargoArmCommand;
  // private final TeleopDrive teleopDrive;
  // private final HatchBarCommand hatchBarCommand;

  public Robot() {
    System.out.println("Robot constructor");

    oi = new OI();
    state = new GlobalState();

    drivetrain = createDrivetrain(oi, state);
    cargoArm = createCargoArm(oi);
    hatchBar = createHatchBar(oi);

    // teleopDrive = new TeleopDrive(drivetrain, oi, state);
    // cargoArmCommand = new CargoArmCommand(cargoArm, oi);
    // hatchBarCommand = new HatchBarCommand(hatchBar, oi);

  }

  private CargoArm createCargoArm(OI oi) {
    TalonSRX cargoLift = new TalonSRX(RobotMap.cargoLift);
    TalonSRX cargoIntake = new TalonSRX(RobotMap.cargoIntake);
    return new CargoArm(cargoLift, cargoIntake, oi);
  }

  private Drivetrain createDrivetrain(OI oi, GlobalState state) {
    TalonSRX frontLeft = new TalonSRX(RobotMap.frontLeftWheel);
    TalonSRX frontRight = new TalonSRX(RobotMap.frontRightWheel);
    TalonSRX backLeft = new TalonSRX(RobotMap.backLeftWheel);
    TalonSRX backRight = new TalonSRX(RobotMap.backRightWheel);
    return new Drivetrain(frontLeft, frontRight, backLeft, backRight, oi, state);
  }

  private HatchBar createHatchBar(OI oi) {
    TalonSRX masterBarLift = new TalonSRX(RobotMap.hatchBarTalonSRX);
    VictorSPX slaveBarLift = new VictorSPX(RobotMap.hatchBarVictorSPX);
    VictorSP vacuumMotor1 = new VictorSP(RobotMap.vacuumMotor1);
    VictorSP vacuumMotor2 = new VictorSP(RobotMap.vacuumMotor2);
    Compressor compressor = new Compressor(RobotMap.compressor);
    Encoder liftEncoder = new  Encoder(RobotMap.hatchBarEncoderSourceA, RobotMap.hatchBarEncoderSourceB);
    Solenoid solenoid = new Solenoid(0);
    return new HatchBar(masterBarLift, slaveBarLift, vacuumMotor1, vacuumMotor2, compressor, liftEncoder, solenoid, oi);
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
    
    //subsystems
    SmartDashboard.putData(drivetrain);
    SmartDashboard.putData(cargoArm);
    SmartDashboard.putData(hatchBar);


    //run commands from SmartDashboard
    // SmartDashboard.putData("Run teleop drive", teleopDrive);
    // SmartDashboard.putData("Run cargo arm command", cargoArmCommand);
    // SmartDashboard.putData("Run hatch bar command", hatchBarCommand);

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
    SmartDashboard.putString("Perspective", state.getPerspective().toString());

    //joystick input
    SmartDashboard.putNumber("Drive joystick vertical axis", oi.getJoystickVerticalAxis());
    SmartDashboard.putNumber("Drive joystick twist", oi.getJoystickTwist());
    SmartDashboard.putNumber("Drive joystick slider", oi.getSlider());

    SmartDashboard.putNumber("Arm joystick left axis", oi.XboxController.getRawAxis(RobotMap.XboxLeftAxis));
    SmartDashboard.putNumber("Arm joystick right axis", oi.XboxController.getRawAxis(RobotMap.XboxRightAxis));

    //drivetrain
    SmartDashboard.putNumber("Back left voltage", drivetrain.getBackLeftVoltage());
    SmartDashboard.putNumber("Back right voltage", drivetrain.getBackRightVoltage());
    SmartDashboard.putNumber("Front left voltage", drivetrain.getFrontLeftVoltage());
    SmartDashboard.putNumber("Front right voltage", drivetrain.getFrontRightVoltage());

    //hatch bar
    SmartDashboard.putNumber("Hatch lift master voltage", hatchBar.getMasterLiftVoltage());
    SmartDashboard.putNumber("Hatch lift slave voltage", hatchBar.getSlaveLiftVoltage());
    SmartDashboard.putNumber("Hatch encoder value", hatchBar.getEncoderValue());
    SmartDashboard.putNumber("Hatch arm angle", hatchBar.getArmAngle());
    SmartDashboard.putNumber("Hatch gravity compensation", hatchBar.getGravityCompensation());
    SmartDashboard.putString("Hatch suction state", hatchBar.getSuctionState().toString());
    SmartDashboard.putBoolean("Compressor enabled", hatchBar.isCompressorEnabled());

    //cargo arm
    SmartDashboard.putNumber("Cargo arm lift voltage",cargoArm.getLiftVoltage());
    SmartDashboard.putNumber("Carog arm intake voltage", cargoArm.getIntakeVoltage());

    //test to see if drive perspective should change
    if(oi.controller.getTriggerPressed()) {
      if(state.getPerspective() == DrivePerspectives.CARGO) {
        state.setPerspective(DrivePerspectives.HATCH);
        System.out.println("Drive perspective switched to HATCH");
      } else {
        state.setPerspective(DrivePerspectives.CARGO);
        System.out.println("Drive perspective switched to CARGO");
      }
    }

    //update dashboard camera stream to current drive perspective
    if(state.getPerspective() == DrivePerspectives.CARGO) {
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
    // teleopDrive.start();
    // cargoArmCommand.start();
    // hatchBarCommand.start();

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
    // teleopDrive.start();
    // cargoArmCommand.start();
    // hatchBarCommand.start();

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

}
