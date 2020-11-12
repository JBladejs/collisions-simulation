package com.game_physics.collisions

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils.*

class Ball(val radius: Float, private val color1: Color, private val color2: Color, x: Float = 0.0f, y: Float = 0.0f, private var vx: Float = 0.0f, private var vy: Float = 0.0f) {
    var x = x
        private set
    var y = y
        private set

    fun move() {
        x += vx
        y += vy

        if (x - radius < 0 || x + radius > Gdx.graphics.width) {
            x = if (x - radius < 0) radius
            else Gdx.graphics.width - radius
            vx *= -1
        }
        if (y - radius < 0 || y + radius > Gdx.graphics.height) {
            y = if (y - radius < 0) radius
            else Gdx.graphics.height - radius
            vy *= -1
        }
    }

    fun render(renderer: ShapeRenderer) {
        with(renderer) {
            set(ShapeRenderer.ShapeType.Filled)
            identity()
            setColor(color1)
            circle(x, y, radius)
            setColor(color2)
        }
    }
}