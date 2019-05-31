package frc.robot.commands;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.*;
import org.mockito.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.*;
import frc.robot.GlobalState;
import frc.robot.OI;
import frc.robot.commands.*;

public class TeleopDriveTest {

    Drivetrain drivetrainMock;
    OI oiMock;
    GlobalState globalStateMock;

    Scheduler scheduler;
    TeleopDrive teleopDrive;

    @Before
    public void init() {
        drivetrainMock = Mockito.mock(Drivetrain.class);
        oiMock = mock(OI.class);
        globalStateMock = mock(GlobalState.class);

        teleopDrive = new TeleopDrive(drivetrainMock, oiMock, globalStateMock);
        teleopDrive.initialize();
    }

    @Test
    public void shouldRequireDrivetrain() {
        assertTrue(teleopDrive.doesRequire(drivetrainMock));
    }

    // @Test
    // public void itShouldRunDriveWhenScheduled() {
    //     Scheduler.getInstance().add(teleopDrive);
    //     Scheduler.getInstance().run();
    //     verify(drivetrainMock).Drive(anyDouble(), anyDouble());
    // }

}