// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.WestCoastDriveSubsystem;
import frc.robot.commands.TagDetectorCMD;
import frc.robot.subsystems.LEDsSubsystem;

public class RobotContainer {
  private final LimelightSubsystem m_Limelight = new LimelightSubsystem();
  private final LEDsSubsystem m_LEDs = new LEDsSubsystem();
  private XboxController m_DriveController = new XboxController(Constants.OperatorInter.DriverController);
  private PS4Controller m_ManipController = new PS4Controller(Constants.OperatorInter.ManipController);
  private final WestCoastDriveSubsystem m_WestCoastDrive = new WestCoastDriveSubsystem();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    m_WestCoastDrive.setDefaultCommand(new RunCommand(
      () -> 
        m_WestCoastDrive.tankDrive(
          MathUtil.applyDeadband(m_DriveController.getLeftY(), Constants.OperatorInter.kDriveDeadband),
          MathUtil.applyDeadband(m_DriveController.getRightY(), Constants.OperatorInter.kDriveDeadband)
          )
      , m_WestCoastDrive)
    );

    m_Limelight.setDefaultCommand(new TagDetectorCMD(m_Limelight, m_LEDs));

    new JoystickButton(m_ManipController, PS4Controller.Button.kR1.value).onTrue(new InstantCommand( () -> m_LEDs.lightsNormal(0.5)));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
