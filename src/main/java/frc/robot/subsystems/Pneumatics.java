package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
    Solenoid testSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    public Pneumatics() {
        
    }
    public void setSingle(int port, Boolean value) {
        testSolenoid.set(value);
        System.out.println("setting the solenoid");
    }
}