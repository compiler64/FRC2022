package frc.robot.commands;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.PortMap;
import frc.robot.subsystems.Pneumatics;

/**
 * Controls the high gear with an Xbox controller.
 */
public class SingleSolenoid extends CommandBase {
    private Pneumatics m_pneumatics;
    // private boolean m_buttonPressed = false;
    private boolean highGear = false;

    /**
     * Creates a new SingleSolenoid command.
     * @param pneumatics the pneumatics of the robot
     */
    public SingleSolenoid(Pneumatics pneumatics) {
        Shuffleboard.getTab("main").add(pneumatics);
        Shuffleboard.getTab("main").addBoolean("high gear", () -> highGear);
        
        m_pneumatics = pneumatics;

        addRequirements(pneumatics);
    }

    /**
     * Controls the high gear with an Xbox controller.
     * @param button
     * @param solenoid
     */
    public void control(int button, int solenoid) {
        if (Controllers.isButtonPressed(button, true)) {
            m_pneumatics.setSingle(solenoid, !highGear);
            highGear = !highGear;
        }
    }

    public void execute() {
        control(PortMap.XBOX_BUTTON_HIGH_GEAR, 0);
        // control(PortMap.XBOX_BUTTON_HIGH_GEAR_2, 0);
    }

    /**
     * Checks if the robot is in high gear.
     * @return {@literal true} if the robot is in high gear,
     * {@literal false} otherwise
     */
    public boolean isHighGear() {
        return highGear;
    }
}
