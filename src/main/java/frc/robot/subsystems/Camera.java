package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Camera extends SubsystemBase {
    private NetworkTable table;
    private NetworkTableEntry tx;
    private NetworkTableEntry ty;
    private NetworkTableEntry ta;
    private NetworkTableEntry pipeline;
    private double x;
    private double y;
    private double area;
    private double mode;

    public Camera() {
        //post to shuffleboard periodically
        table = NetworkTableInstance.getDefault().getTable("limelight");
       setPipeline();
        Shuffleboard.getTab("main").addNumber("Limelight X", () -> x);
        Shuffleboard.getTab("main").addNumber("Limelight Y", () -> y);
        Shuffleboard.getTab("main").addNumber("Limelight Area", () -> area);
        Shuffleboard.getTab("main").addNumber("Pipeline Number", () -> mode);
    }
    
    public void display() {
        // get the entries periodically
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        pipeline = table.getEntry("getpipe");

        //read values periodically
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
        mode = pipeline.getDouble(0.0);
    }

    public void setPipeline() {
        int number;
        // DriverStation.getAlliance();
        if (DriverStation.getAlliance() == Alliance.Blue) {
            number = 1;
        } else {
            number = 0;
        }

        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(number);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getArea() {
        return area;
    }
}
