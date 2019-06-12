package frc.robot.subsystems;

import org.junit.*;

import frc.robot.GlobalState;
import frc.robot.OI;

import static org.mockito.Mockito.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DrivetrainTest {
    TalonSRX frontRight;
    TalonSRX frontLeft;
    TalonSRX backRight;
    TalonSRX backLeft;

    Drivetrain drivetrain;

    OI oi;
    GlobalState globalState;

    @Before
    public void init() {
        frontLeft = mock(TalonSRX.class);
        frontRight = mock(TalonSRX.class);
        backLeft = mock(TalonSRX.class);
        backRight = mock(TalonSRX.class);
        oi = mock(OI.class);
        globalState = mock(GlobalState.class);

        drivetrain = new Drivetrain(frontLeft, frontRight, backLeft, backRight, oi, globalState);
    }

    @Test
    public void itShouldResetAllTalonsToFactoryDefaultOnInit() {
        verify(frontLeft).configFactoryDefault();
        verify(frontRight).configFactoryDefault();
        verify(backLeft).configFactoryDefault();
        verify(backRight).configFactoryDefault();
    }

    @Test
    public void itShouldDisableBothForwardLimitSwitchesOnInit() {
        verify(frontLeft).configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
        verify(frontRight).configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
    }

    @Test
    public void itShouldNotEnableAnyForwardLimitSwitchesOnInit() {
        verify(frontLeft, times(0)).configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        verify(frontLeft, times(0)).configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

        verify(frontRight, times(0)).configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        verify(frontRight, times(0)).configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

        verify(backLeft, times(0)).configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        verify(backLeft, times(0)).configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

        verify(backRight, times(0)).configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        verify(backRight, times(0)).configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    }

    @Test
    public void itShouldDisableBothReverseLimitSwitchesOnInit() {
        verify(frontLeft).configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
        verify(frontRight).configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled);
    }

    @Test
    public void itShouldNotEnableAnyReverseLimitSwitchesOnInit() {
        verify(frontLeft, times(0)).configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        verify(frontLeft, times(0)).configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

        verify(frontRight, times(0)).configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        verify(frontRight, times(0)).configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

        verify(backLeft, times(0)).configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        verify(backLeft, times(0)).configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

        verify(backRight, times(0)).configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
        verify(backRight, times(0)).configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    }

    @Test
    public void itShouldNotSetAnyTalonsToFollowOnInit() {
        verify(frontLeft, times(0)).follow(any());
        verify(frontRight, times(0)).follow(any());
        verify(backLeft, times(0)).follow(any());
        verify(backRight, times(0)).follow(any());
    }

    @Test
    public void driveLeftPowerShouldBeSetToLeftTalons() {
        drivetrain.Drive(0.3, -0.9);
        verify(frontLeft).set(ControlMode.PercentOutput, 0.3);
        verify(backLeft).set(ControlMode.PercentOutput, 0.3);
    }

    @Test
    public void driveRightPowerShouldBeSetToRightTalons() {
        drivetrain.Drive(0.6, -0.8);
        verify(frontRight).set(ControlMode.PercentOutput, -0.8);
        verify(backRight).set(ControlMode.PercentOutput, -0.8);
    }


}