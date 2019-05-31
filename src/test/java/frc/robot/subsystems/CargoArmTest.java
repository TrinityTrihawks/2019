package frc.robot.subsystems;

import org.junit.*;

import frc.robot.OI;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class CargoArmTest {
    TalonSRX cargoLift;
    TalonSRX cargoIntake;
    OI oi;

    CargoArm cargoArm;

    @Before
    public void init() {
        cargoLift = mock(TalonSRX.class);
        cargoIntake = mock(TalonSRX.class);

        oi = new OI();

        cargoArm = new CargoArm(cargoLift, cargoIntake, oi);
    }

    @Test
    public void testThis() {
        assertTrue(true);
    }

}