package pioneer.tools.utils

/**
 * A class containing utility methods for working with angles.
 */
object AngleUtils {

    /**
     * Given any input angle in radians, return its normalized value in the -pi to +pi range
     * @param radians input angle in radians
     * @return normalized angle in radians (-pi to pi)
     */
    fun normalizeRadians(radians: Double): Double {
        return ((radians + Math.PI) / (2.0 * Math.PI) - Math.floor((radians + Math.PI) / (2.0 * Math.PI)) - 0.5) * 2.0 * Math.PI
    }

    /**
     * Given any input angle in degrees, return its normalized value in the -180 to +180 range.
     * @param degrees input angle in degrees
     * @return normalized angle in degrees (-180 to 180)
     */
    fun normalizeDegrees(degrees: Double): Double {
        var normalized = (degrees + 180.0) % 360.0
        if (normalized < 0) {
            normalized += 360.0
        }
        return normalized - 180.0
    }

    /**
     * Given any input angle in degrees, return its normalized value in the 0 to 360 degree range.
     * @param degrees input angle in degrees
     * @return normalized angle in degrees (0 to 360)
     */
    fun normalizeDegrees360(degrees: Double): Double {
        return (degrees % 360.0 + 360.0) % 360.0
    }

    /**
     * Meant to help you find the shortest angle separating the angle you're
     * at from the angle you want
     * @param targetAngle the angle you want the robot to be at
     * @param currentAngle the current angle of the robot
     * @return an angle between -pi and pi that is the amount you should turn
     */
    fun subtractAnglesRad(targetAngle: Double, currentAngle: Double): Double {
        return normalizeRadians(normalizeRadians(targetAngle) - normalizeRadians(currentAngle))
    }
}