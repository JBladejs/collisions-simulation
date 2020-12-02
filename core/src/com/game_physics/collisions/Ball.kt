package com.game_physics.collisions

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils.cos
import com.badlogic.gdx.math.MathUtils.sin
import com.game_physics.collisions.system.CircleCollider
import kotlin.math.abs
import kotlin.math.sqrt

class Ball(val radius: Float, private val color: Color, x: Float = 0.0f, y: Float = 0.0f, vx: Float = 0.0f, vy: Float = 0.0f) {
    var x = x
     set
    var y = y
         set
    var vx = vx
         set
    var vy = vy
         set
    private val collider = CircleCollider(x, y, vx, vy, radius)

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
        collider.update(x, y, vx, vy)
        if (collider.isColiding) {
            var angle1 = (collider.collisionAngle + 180f)
            val direction1 = (Math.atan2(vx.toDouble(),vy.toDouble())).toFloat()
            val direction2 = (Math.atan2(collider.vxHit.toDouble(),collider.vyHit.toDouble())).toFloat()
            val force1 = sqrt((vx*vx) + (vy*vy))
            val force2 = sqrt((collider.vxHit*collider.vxHit)+(collider.vyHit*collider.vyHit))
            angle1 = angle1.toRadians()

            vy = force2 * cos(direction2-angle1) * cos(angle1) + force1*sin(direction1-angle1) * cos((angle1+(Math.PI/2).toFloat()))
            vx = force2 * cos(direction2-angle1) * sin(angle1) + force1*sin(direction1-angle1) * sin((angle1+(Math.PI/2).toFloat()))

            collider.isColiding = false
        }
    }

    fun render(renderer: ShapeRenderer) {
        with(renderer) {
            set(ShapeRenderer.ShapeType.Filled)
            setColor(this@Ball.color)
            circle(x, y, radius)
        }
    }
}