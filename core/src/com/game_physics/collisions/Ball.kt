package com.game_physics.collisions

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils.cos
import com.badlogic.gdx.math.MathUtils.sin
import com.game_physics.collisions.system.CircleCollider
import kotlin.math.abs

class Ball(val radius: Float, private val color: Color, x: Float = 0.0f, y: Float = 0.0f, vx: Float = 0.0f, vy: Float = 0.0f) {
    var x = x
    private set
    var y = y
        private set
    var vx = vx
        private set
    var vy = vy
        private set
    private val collider = CircleCollider(x, y, radius)

    var othervx = 0f
    var othervy = 0f

    fun move(delta: Float, dt : Float) {
        x += vx * delta * dt
        y += vy * delta * dt

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
        collider.update(x, y)
        if (collider.isColiding) {
            val angle1 = (collider.collisionAngle + 180f)
            //TODO: Refactor using abs()
            val perpendicular = if (vx >= 0) if (vy >= 0) -90f else 90f else if (vy >= 0) 90f else -90f
            val angle2 = (collider.collisionAngle + perpendicular)
            println("${collider.collisionAngle} $angle1 $angle2")
            //vx = abs(othervx) * (sin(angle1.toRadians()) + sin(angle2.toRadians()))
            //vy = abs(othervy) * (cos(angle1.toRadians()) + cos(angle2.toRadians()))
            vx = othervx * abs(sin(angle1.toRadians())) + abs(othervy) * abs(cos(angle1.toRadians()))
            vy = othervy * (sin(angle1.toRadians())) + abs(othervx) * (cos(angle1.toRadians()))
            //println("angle1: $angle1 sin: " + sin(angle1.toRadians()) + " cos: " + cos(angle1.toRadians()))
            collider.isColiding = false
        }
    }

    fun grabForce(forcex: Float, forcey: Float){
        othervx = forcex
        othervy = forcey
    }

    fun render(renderer: ShapeRenderer) {
        with(renderer) {
            set(ShapeRenderer.ShapeType.Filled)
            setColor(this@Ball.color)
            circle(x, y, radius)
        }
    }
}