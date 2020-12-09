package com.game_physics.collisions

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils.cos
import com.badlogic.gdx.math.MathUtils.sin
import com.game_physics.collisions.system.CircleCollider
import com.game_physics.collisions.system.CollisionSystem
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.atan2
import kotlin.math.round
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
    val accuracy = 0.00001

    fun move(delta: Float, dt : Float) {
        x += vx * delta * dt
        y += vy * delta * dt
        if (x - radius < 0 || x + radius > Gdx.graphics.width) {
            x = if (x - radius < 0) radius
            else Gdx.graphics.width - radius
            vx *= -1
            CollisionSystem.removeCollisionsForCollider(collider)
        }
        if (y - radius < 0 || y + radius > Gdx.graphics.height) {
            y = if (y - radius < 0) radius
            else Gdx.graphics.height - radius
            vy *= -1
            CollisionSystem.removeCollisionsForCollider(collider)
        }
        if (vx < 0.05f && vx > -0.05f) {
            vx = 0f
        }
        if (vy < 0.05f && vy > -0.05f) {
            vy = 0f
        }
        collider.update(x, y, vx, vy)
        if (collider.isColiding) {
            var angle1 = (collider.collisionAngle + Math.PI).toFloat()
            val direction1 = (atan2(vx.toDouble(),vy.toDouble())).toFloat()
            val direction2 = (atan2(collider.vxHit.toDouble(),collider.vyHit.toDouble())).toFloat()
            val force1: Float = sqrt((vx*vx) + (vy*vy))
            val force2: Float = sqrt((collider.vxHit*collider.vxHit)+(collider.vyHit*collider.vyHit))
            angle1 = angle1

            var tempx1 = force2 * cos(direction2 - angle1).round(2) * sin(angle1).round(2)
            var tempx2 = force1 * sin(direction1 - angle1).round(2) * sin((angle1 + (Math.PI / 2).toFloat())).round(2)
            var tempy1 = force2 * cos(direction2 - angle1).round(2) * cos(angle1).round(2)
            var tempy2 = force1 * sin(direction1 - angle1).round(2) * cos((angle1 + (Math.PI / 2).toFloat())).round(2)

            //vy = force2 * cos(direction2-angle1) * cos(angle1) + force1*sin(direction1-angle1) * cos((angle1+(Math.PI/2).toFloat()))
            //vx = force2 * cos(direction2-angle1) * sin(angle1) + force1*sin(direction1-angle1) * sin((angle1+(Math.PI/2).toFloat()))
            vy = tempy1.toFloat() + tempy2.toFloat()
            vx = tempx1.toFloat() + tempx2.toFloat()
            collider.isColiding = false

            println("dir1: " + direction1 + " dir2: " + direction2)
            println("tempx1: " + tempx1 + " tempx2: " + tempx2)
            println("tempy1: " + tempy1 + " tempy2: " + tempy2)
            //println("sin: " + sin(Math.PI.toFloat()).round(6) + " cos: " + cos(Math.PI.toFloat()))
            println("vx: " + vx + " vy: " + vy + "\n\n")
        }
    }

    fun render(renderer: ShapeRenderer) {
        with(renderer) {
            set(ShapeRenderer.ShapeType.Filled)
            setColor(this@Ball.color)
            circle(x, y, radius)
        }
    }

    fun Float.round(decimals: Int): Float {
        var multiplier = 1f
        repeat(decimals) { multiplier *= 10f }
        return round(this * multiplier) / multiplier
    }
}