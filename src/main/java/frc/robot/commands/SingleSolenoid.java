package frc.robot.commands;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.subsystems.Pneumatics;

public class SingleSolenoid  extends CommandBase {
    Pneumatics pneumatics = new Pneumatics();
    boolean buttonPressed = false;
    public SingleSolenoid() {
        Shuffleboard.getTab("main").add(pneumatics);
        addRequirements(pneumatics);
    }
    public void control(int button, int solenoid) {
        if (Controllers.isButtonPressed(button, true)) {
            pneumatics.setSingle(solenoid, true);
        }
        if (Controllers.isButtonReleased(button, true)) {
            pneumatics.setSingle(solenoid, false);
        }
    }
}
