package frc.robot.subsystems;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DrivetrainTest {
    TalonSRX frontRight;
    TalonSRX frontLeft;
    TalonSRX backRight;
    TalonSRX backLeft;

    Drivetrain drivetrain;

    @Before
    public void init() {
        frontLeft = mock(TalonSRX.class);
        frontRight = mock(TalonSRX.class);
        backLeft = mock(TalonSRX.class);
        backRight = mock(TalonSRX.class);

        drivetrain = new Drivetrain(frontLeft, frontRight, backLeft, backRight);
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


}