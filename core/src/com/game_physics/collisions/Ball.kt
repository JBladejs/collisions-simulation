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
            //TODO: Refactor using abs() (not necessary anymore, perpendicular is not used)
            //val perpendicular = if (vx >= 0) if (vy >= 0) -90f else 90f else if (vy >= 0) 90f else -90f
            //val angle2 = (collider.collisionAngle + perpendicular)
            //println("${collider.collisionAngle} $angle1 $angle2")
            val direction1 = (180.0 / Math.PI * Math.atan2(vx.toDouble(),vy.toDouble())).toFloat()
            val direction2 = (180.0 / Math.PI * Math.atan2(othervx.toDouble(),othervy.toDouble())).toFloat()
            val force1 = sqrt((vx*vx) + (vy*vy))
            val force2 = sqrt((othervx*othervx)+(othervy+othervy))

            vy = force2 * cos((direction2-angle1).toRadians()) * cos(angle1.toRadians()) + force1*sin((direction1-angle1).toRadians()) * cos((angle1.toRadians()+(Math.PI/2).toFloat()))
            vx = force2 * cos((direction2-angle1).toRadians()) * sin(angle1.toRadians()) + force1*sin((direction1-angle1).toRadians()) * sin((angle1.toRadians()+(Math.PI/2).toFloat()))

            /*
            println("angle1: $angle1 angle2: $angle2 direction1: $direction1 direction2: $direction2")
            println("vx: $vx vy: $vy force1: $force1 force2: $force2")
            println("othervx: $othervx othervy: $othervy")
            println()*/
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