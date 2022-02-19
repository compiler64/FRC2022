package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.PortMap;
import frc.robot.subsystems.Intake;

/**
 * Tests the intake subsystem.
 */
public class TestIntake extends CommandBase {
    // private DoubleSolenoid intakeLift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
    Intake m_intake;
    
    /**
     * Creates a new TestIntake command.
     * @param intake the intake subsystem of the robot
     */
    public TestIntake(Intake intake) {
        m_intake = intake;

        addRequirements(intake);
    }

    @Override
    public void execute() {
        if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_A, true)) {
            m_intake.lower();
        }
        if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_B, true)) {
            m_intake.raise();
        }
    }
}
