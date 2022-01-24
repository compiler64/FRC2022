package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
    // Compressor pcmcompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
    Solenoid testSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
    //DoubleSolenoid solenoid12 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
    public Pneumatics() {
        // pcmcompressor.enableDigital();
    }
    public void setSingle(int port, Boolean value) {
        testSolenoid.set(value);
        System.out.println("setting the solenoid");
    }
}