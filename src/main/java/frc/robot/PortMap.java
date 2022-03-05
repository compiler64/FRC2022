package frc.robot;

/**
 * Stores constants that are information about the motor ports and Xbox controls.
 */
public class PortMap {
    public static final int MOTOR_LF_ID = 3;
	public static final int MOTOR_LM_ID = 2;
	public static final int MOTOR_LR_ID = 1;
	public static final int MOTOR_RF_ID = 6;
	public static final int MOTOR_RM_ID = 5;
	public static final int MOTOR_RR_ID = 4;
	public static final int MOTOR_INTAKE_ID = 11; // will be 7
	public static final int MOTOR_TRANSFER_ID = 8;
	public static final int MOTOR_INDEXING_WHEEL_ID = 9;
	public static final int MOTOR_FLYWHEEL_ID = 11;
	public static final int MOTOR_CLIMBER_EXTEND_ID_1 = 12;
	public static final int MOTOR_CLIMBER_EXTEND_ID_2 = 13;

	public static final int XBOX_DRIVER_PORT = 0;
	public static final int XBOX_CLIMBER_PORT = 1;

	//Left Stick
    public static final int XBOX_LS_X = 0;
    public static final int XBOX_LS_Y = 1;

	// triggers
	public static final int XBOX_L_TRIGGER = 2;
	public static final int XBOX_R_TRIGGER = 3;
    
	//Right Stick
	public static final int XBOX_RS_X = 4;
    public static final int XBOX_RS_Y = 5;
	
	// Buttons
	public static final int XBOX_BUTTON_A = 1;
	public static final int XBOX_BUTTON_B = 2;
	public static final int XBOX_BUTTON_X = 3;
	public static final int XBOX_BUTTON_Y = 4;
	public static final int XBOX_BUTTON_LB = 5;
	public static final int XBOX_BUTTON_RB = 6;
	public static final int XBOX_BUTTON_LSTICK = 9;
	public static final int XBOX_BUTTON_RSTICK = 10;

	// assignments
	public static final int XBOX_BUTTON_HIGH_GEAR = XBOX_BUTTON_RB;
	public static final int XBOX_BUTTON_HIGH_GEAR_2 = XBOX_BUTTON_LB;
	public static final int XBOX_BUTTON_INTAKE_WHEELS = XBOX_BUTTON_B;
	public static final int XBOX_BUTTON_INTAKE_LIFT = XBOX_BUTTON_A;
	public static final int XBOX_BUTTON_TRANSFER = XBOX_BUTTON_Y;
	public static final int XBOX_BUTTON_CLIMBER_ROTATE = XBOX_BUTTON_Y;
	public static final int XBOX_BUTTON_START_FLYWHEEL = XBOX_BUTTON_LB;
	public static final int XBOX_BUTTON_INDEXING_WHEEL = XBOX_BUTTON_RB;
	
}
