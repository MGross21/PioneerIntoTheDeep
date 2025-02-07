package org.firstinspires.ftc.teamcode.Hardware

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo

/**
 * Core Servo class with basic functionalities
 */
open class ServoCore(
    hardwareMap: HardwareMap,
    val name: String,
    protected val openPos: Double,
    protected val closePos: Double,
    startPos: Double = openPos
) {
    protected val servo: Servo

    init {
        servo = hardwareMap.get(Servo::class.java, name)
    }

    open fun closeServo() {
        servo.position = closePos
    }

    open fun openServo() {
        servo.position = openPos
    }

    open fun setPosition(pos: Double) {
        servo.position = pos
    }

    open fun togglePosition(position: Boolean) {
        val newPos = if (position) openPos else closePos
        if (servo.position != newPos) {
            servo.position = newPos
        }
    }

    open val position: Double
        get() = servo.position
}