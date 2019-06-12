package frc.robot.subsystems;

import org.junit.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.OI;

import static org.mockito.Mockito.*;


import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class HatchBarTest {
    TalonSRX masterLift;
    VictorSPX slaveLift;
    VictorSP vacuumMotor1;
    VictorSP vacuumMotor2;
    Compressor compressor;
    Encoder liftEncoder;
    Solenoid solenoid1;
    Solenoid solenoid2;
    OI oi;

    HatchBar hatchBar;

    @Before
    public void init() {
        masterLift = mock(TalonSRX.class);
        slaveLift = mock(VictorSPX.class);
        vacuumMotor1 = mock(VictorSP.class);
        vacuumMotor2 = mock(VictorSP.class);
        compressor = mock(Compressor.class);
        liftEncoder = mock(Encoder.class);
        solenoid1 = mock(Solenoid.class);
        solenoid2 = mock(Solenoid.class);
        oi = mock(OI.class);

        hatchBar = new HatchBar(masterLift, slaveLift, vacuumMotor1, vacuumMotor2, compressor, liftEncoder, solenoid1, solenoid2, oi);
    }

}