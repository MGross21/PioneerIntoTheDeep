package org.firstinspires.ftc.teamcode.Helpers

import org.firstinspires.ftc.teamcode.Bot

/*
 * Class used to manage Points of Interest (POI) for the bot to navigate to.
 * Exemplar Class
 */

class POIManager {
    // Example POIs
    enum class POI(val x: Double, val y: Double, val vx: Double, val vy: Double) {
        SPECIMEN(1.0, 2.0, 0.5, 0.0),
        SUBMERSIBLE(4.0, 5.0, 0.0, 0.5),
        SAMPLE(7.0, 8.0, 0.0, 0.0)
    }

    // Companion = Class-level static methods
    companion object {
        fun generateHermiteToPoint(bot: Bot, poi: POI): Array<DoubleArray> {
            return SplineCalc.cubicHermite(
                    Point(bot.x, bot.y, bot.vx, bot.vy),
                    Point(poi.x, poi.y, poi.vx, poi.vy),
                    100
            )
        }
    }
}