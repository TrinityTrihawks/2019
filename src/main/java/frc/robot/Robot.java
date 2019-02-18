/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.cscore.AxisCamera;
import frc.robot.commands.AutonomousDriveForward;
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
  public static Drivetrain drivetrain = new Drivetrain();
  // public static HatchBar hatchBar = new HatchBar();
  // public static CargoArm cargoArm = new CargoArm();

  public static OI oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  AxisCamera cameraFront;
  AxisCamera cameraBack;

  final int IMG_WIDTH = 320;
  final int IMG_HEIGHT = 240;

  String CameraDisplayed;

  //TODO: unfold command
  
  // Ultrasonics ultrasonics;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
      //TODO: arm power scalar on Shuffleboard

    oi = new OI();
    //m_chooser.addDefault("Default Auto", new ExampleCommand());
    // chooser.addObject("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

    cameraFront = CameraServer.getInstance().addAxisCamera("Front Camera", RobotMap.cameraFrontIPAddress);
    cameraBack = CameraServer.getInstance().addAxisCamera("Back Camera", RobotMap.cameraBackIPAddress);
    
    NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection").setString(cameraFront.getName());
    CameraDisplayed = cameraFront.getName();
    // CameraServer.getInstance().startAutomaticCapture(camera);

    // CameraServer.getInstance().getServer().setSource(source);
    // NetworkTableInstance.getDefault().getTable("").putData("CameraSelection", camera.getName());
		// camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
    //System.out.println("Front camera initialized properly");
    
    // ultrasonics = new Ultrasonics();


		 
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
    SmartDashboard.putNumber("Joystick vertical axis", oi.getJoystickVerticalAxis());
    SmartDashboard.putNumber("Joystick twist", oi.getJoystickTwist());
    SmartDashboard.putNumber("Joystick slider", oi.getSlider());
    // SmartDashboard.putNumber("Left ultrasonic", ultrasonics.getLeftDistance());
    // SmartDashboard.putNumber("Right ultrasonic", ultrasonics.getRightDistance());

    // oi.testAllButtons();
    if(oi.getJoystickTrigger()) {
      System.out.println("Joystick pressed");

      CameraDisplayed = NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection").getString("");
      System.out.println("Camera displayed ||" + CameraDisplayed + "||");
      System.out.println("Front camera name ||" + cameraFront.getName() + "||");

      if(CameraDisplayed.equals(cameraFront.getName())) {
        System.out.println("Front camera currently displayed");
        NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection").setString(cameraBack.getName());
      } else {
        System.out.println("Back camera currently displayed");
        NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection").setString(cameraFront.getName());
      }
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

    // m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.start();
    // }
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
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
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
  }
}
