package com.game_physics.collisions.system

import com.badlogic.gdx.math.Circle
import kotlin.math.pow
import kotlin.math.sqrt

class CircleCollider(var x: Float, var y: Float, var vx: Float, var vy: Float, var radius: Float) {
    var isColiding = false
    var collisionAngle = 0f
    var vxHit = 0f
    var vyHit = 0f

    init {
        CollisionSystem.add(this)
    }

    fun update(x: Float, y: Float, vx: Float, vy: Float) {
        this.x = x
        this.y = y
        this.vx = vx
        this.vy = vy
    }

    fun collides(collider: CircleCollider): Boolean = sqrt((collider.x - x).pow(2) + (collider.y - y).pow(2)) < radius + collider.radius
}