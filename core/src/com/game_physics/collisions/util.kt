package com.game_physics.collisions

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils.PI


fun ShapeRenderer.setColor(color: Color) = this.setColor(color.red, color.green, color.blue, 1.0f)

fun Float.toDegrees(): Float {
    var value = (180f * this) / PI
    while (value > 360f) value -= 360f
    while (value < 0f) value += 360f
    return value
}

fun Float.toRadians(): Float {
    var value = this
    while (value > 360f) value -= 360f
    while (value < 0f) value += 360f
    return (PI * value) / 180f
}