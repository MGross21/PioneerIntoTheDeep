import kotlin.math.sqrt

data class Point(
        var x: Double,
        var y: Double,
        var vx: Double = 0.0,
        var vy: Double = 0.0,
        var ax: Double = 0.0,
        var ay: Double = 0.0,
        var jx: Double = 0.0,
        var jy: Double = 0.0
        var heading: Double = 0.0,
        var name: String = ""

){

        // Method to calculate the distance to another point
        fun distanceTo(other: Point): Double {
                return sqrt((other.x - x) * (other.x - x) + (other.y - y) * (other.y - y))
        }

        // Method to update the position based on velocity and acceleration
        fun updatePosition(deltaTime: Double) {
                vx += ax * deltaTime
                vy += ay * deltaTime
                x += vx * deltaTime
                y += vy * deltaTime
        }
}

