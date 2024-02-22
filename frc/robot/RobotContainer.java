// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.Constants.Drivetrain;
import frc.robot.Constants.OperatorInter;
import frc.robot.commands.TagDetectorCMD;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDsSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.utils.GamepadUtils;

public class RobotContainer {
    private final LimelightSubsystem m_Limelight = new LimelightSubsystem();
    private final LEDsSubsystem m_LEDs = new LEDsSubsystem();
    private SwerveDriveSubsystem m_SwerveDrive = new SwerveDriveSubsystem();
    private final ArmSubsystem m_ShooterArm = new ArmSubsystem();
    private final IntakeSubsystem m_ShooterIntake = new IntakeSubsystem();
    private final LauncherSubsystem m_Shooter = new LauncherSubsystem();

    private XboxController m_DriveController = new XboxController(Constants.OperatorInter.DriverController);
    private XboxController m_ManipController = new XboxController(Constants.OperatorInter.ManipController);

    public RobotContainer() {
        configureBindings();

        m_SwerveDrive.setDefaultCommand(
            // The left stick controls translation of the robot.
            // Turning is controlled by the X axis of the right stick.
            new RunCommand(
                () ->
                    m_SwerveDrive.drive(
                        GamepadUtils.squareInput(
                            m_DriveController.getLeftY(), OperatorInter.kDriveDeadband),
                        GamepadUtils.squareInput(
                            m_DriveController.getLeftX(), OperatorInter.kDriveDeadband),
                        -GamepadUtils.squareInput(
                            m_DriveController.getRightX(), OperatorInter.kDriveDeadband),
                        true,
                        true,
                        Drivetrain.kMaxSpeedMPSRegular),
                m_SwerveDrive));
    
        m_ShooterArm.setDefaultCommand(new RunCommand(() -> m_ShooterArm.runAutomatic(), m_ShooterArm));
        m_ShooterIntake.setDefaultCommand(new RunCommand(() -> m_ShooterIntake.setPower(0.0), m_ShooterIntake));
        m_Shooter.setDefaultCommand(new RunCommand(() -> m_Shooter.stopLauncher(), m_Shooter));
    }

    private void configureBindings() {
        // button to put swerve modules in an "x" configuration to hold position
        new JoystickButton(m_DriveController, XboxController.Button.kLeftStick.value)
            .whileTrue(new RunCommand(() -> m_SwerveDrive.setX(), m_SwerveDrive));

        new JoystickButton(m_DriveController, XboxController.Button.kLeftBumper.value)
            .whileTrue(new RunCommand(
                () ->
                    m_SwerveDrive.drive(
                        GamepadUtils.squareInput(
                            m_DriveController.getLeftY(), OperatorInter.kDriveDeadband),
                        GamepadUtils.squareInput(
                            m_DriveController.getLeftX(), OperatorInter.kDriveDeadband),
                        -GamepadUtils.squareInput(
                            m_DriveController.getRightX(), OperatorInter.kDriveDeadband),
                        false,
                        true,
                        Drivetrain.kMaxSpeedMPSSlow),
                m_SwerveDrive));
    
        new JoystickButton(m_DriveController, XboxController.Button.kLeftBumper.value)
            .whileTrue(new RunCommand(
                () ->
                    m_SwerveDrive.drive(
                        GamepadUtils.squareInput(
                            m_DriveController.getLeftY(), OperatorInter.kDriveDeadband),
                        GamepadUtils.squareInput(
                            m_DriveController.getLeftX(), OperatorInter.kDriveDeadband),
                        -GamepadUtils.squareInput(
                            m_DriveController.getRightX(), OperatorInter.kDriveDeadband),
                        false,
                        false,
                        Drivetrain.kMaxSpeedMPSRegular),
                m_SwerveDrive));
        // m_Limelight.setDefaultCommand(new TagDetectorCMD(m_Limelight, m_LEDs));

        new POVButton(m_ManipController, Constants.OperatorInter.kDPadUp)
            .whileTrue(new InstantCommand(() -> m_ShooterArm.setTargetPosition(Constants.Arm.kScoringPosition)));
        new POVButton(m_ManipController, Constants.OperatorInter.kDPadDown)
            .whileTrue(new InstantCommand(() -> m_ShooterArm.setTargetPosition(Constants.Arm.kIntakePosition)));
        
        new JoystickButton(m_ManipController, XboxController.Button.kLeftBumper.value)
            .whileTrue(new RunCommand(() -> m_ShooterIntake.setPower(Constants.Intake.kIntakePower), m_ShooterIntake));
        new JoystickButton(m_ManipController, XboxController.Button.kRightBumper.value)
            .whileTrue(new RunCommand(() -> m_ShooterIntake.setPower(-Constants.Intake.kIntakePower), m_ShooterIntake));
        
        new Trigger(() -> m_ManipController.getRightTriggerAxis() > 0.5)
            .onTrue(new InstantCommand(() -> m_Shooter.runLauncher(), m_Shooter));
        
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
