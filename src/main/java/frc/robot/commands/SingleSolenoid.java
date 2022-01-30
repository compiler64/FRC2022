package frc.robot.commands;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.subsystems.Pneumatics;

public class SingleSolenoid  extends CommandBase {
    private Pneumatics m_pneumatics;
    // private boolean m_buttonPressed = false;
    private boolean highGear = false;

    public SingleSolenoid(Pneumatics pneumatics) {
        Shuffleboard.getTab("main").add(pneumatics);
        Shuffleboard.getTab("main").addBoolean("high gear", () -> highGear);
        addRequirements(pneumatics);
    }

    public void control(int button, int solenoid) {
        if (Controllers.isButtonPressed(button, true)) {
            m_pneumatics.setSingle(solenoid, true);
            highGear = true;
        }
        if (Controllers.isButtonReleased(button, true)) {
            m_pneumatics.setSingle(solenoid, false);
            highGear = false;

        }
    }

    public boolean isHighGear() {
        return highGear;
    }
}
