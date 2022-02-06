package frc.robot.commands;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class SparkTest extends CommandBase {
    CANSparkMax sparkMax = new CANSparkMax(13, MotorType.kBrushless);
    SparkMaxPIDController pidController;
    
    RelativeEncoder encoder = sparkMax.getEncoder();
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
    public SparkTest() {
        Shuffleboard.getTab("main").addNumber("spark encoder",  () -> encoder.getVelocity());
        pidController = sparkMax.getPIDController();
        kP = 6e-5; 
        kI = 0;
        kD = 0; 
        kIz = 0; 
        kFF = 0.000015; 
        kMaxOutput = 1; 
        kMinOutput = -1;
        maxRPM = 5700;
        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
        pidController.setIZone(kIz);
        pidController.setFF(kFF);
        pidController.setOutputRange(kMinOutput, kMaxOutput);
    }

    @Override
    public void execute() {
        if (Controllers.isButtonPressed(1, true)) {
            pidController.setReference(500, CANSparkMax.ControlType.kVelocity);
        }
        if (Controllers.isButtonReleased(1, true)) {
            pidController.setReference(0, CANSparkMax.ControlType.kVelocity);
        }
    }
}
