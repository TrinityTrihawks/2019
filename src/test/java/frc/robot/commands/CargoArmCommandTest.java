// package frc.robot.commands;

// import org.junit.*;
// import static org.junit.Assert.*;
// import static org.mockito.Mockito.*;

// import frc.robot.OI;
// import frc.robot.RobotMap;
// import frc.robot.subsystems.*;

// public class CargoArmCommandTest {

//     OI oiMock;
//     CargoArm cargoArmMock;
//     CargoArmCommand cargoArmCommand;

//     @Before
//     public void init() {
//         cargoArmMock = mock(CargoArm.class);
//         oiMock = mock(OI.class);
//         cargoArmCommand = new CargoArmCommand(cargoArmMock, oiMock);
//         cargoArmCommand.initialize();

//     }

//     @Test
//     public void doesExecuteCallCargoArmLift() {
//         when(oiMock.XboxGetAxis(anyInt())).thenReturn(0.3);
//         when(oiMock.XboxGetButton(anyInt())).thenReturn(false);

//         cargoArmCommand.execute();
//         verify(cargoArmMock, times(1)).Lift(anyDouble());
//     }

//     @Test
//     public void doesXboxButtonXCallIntake() {
//         when(oiMock.XboxGetAxis(anyInt())).thenReturn(-1.0);
//         when(oiMock.XboxGetButton(RobotMap.XboxButtonX)).thenReturn(true);

//         cargoArmCommand.execute();
//         verify(cargoArmMock, times(1)).intake();
//         verify(cargoArmMock, times(0)).spit();
//         verify(cargoArmMock, times(0)).off();
//     }

//     @Test
//     public void doesXboxButtonXNotPressedCallOff() {
//         when(oiMock.XboxGetAxis(anyInt())).thenReturn(-1.0);
//         when(oiMock.XboxGetButton(RobotMap.XboxButtonX)).thenReturn(false);

//         cargoArmCommand.execute();
//         verify(cargoArmMock, times(0)).intake();
//         verify(cargoArmMock, times(0)).spit();
//         verify(cargoArmMock, times(1)).off();
//     }

//     @Test
//     public void doesXboxButtonBPressedCallSpit() {
//         when(oiMock.XboxGetAxis(anyInt())).thenReturn(-1.0);
//         when(oiMock.XboxGetButton(RobotMap.XboxButtonB)).thenReturn(true);

//         cargoArmCommand.execute();
//         verify(cargoArmMock, times(0)).intake();
//         verify(cargoArmMock, times(1)).spit();
//         verify(cargoArmMock, times(0)).off();
//     }

//     @Test
//     public void doesXboxButtonBPressedNotPressedCallOff() {
//         when(oiMock.XboxGetAxis(anyInt())).thenReturn(-1.0);
//         when(oiMock.XboxGetButton(RobotMap.XboxButtonB)).thenReturn(false);

//         cargoArmCommand.execute();
//         verify(cargoArmMock, times(0)).intake();
//         verify(cargoArmMock, times(0)).spit();
//         verify(cargoArmMock, times(1)).off();
//     }

//     @Test
//     public void doesIsFinishedAlwaysReturnFalse() {
//         boolean isFinished = cargoArmCommand.isFinished();
//         assertFalse(isFinished);
//     }

// }