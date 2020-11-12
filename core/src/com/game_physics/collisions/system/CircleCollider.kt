package com.game_physics.collisions.system

import com.badlogic.gdx.math.Circle
import kotlin.math.pow
import kotlin.math.sqrt

class CircleCollider(var x: Float, var y: Float, var radius: Float) {

    init {
        CollisionSystem.add(this)
    }

    fun update(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun collides(collider: CircleCollider): Boolean = sqrt((collider.x - x).pow(2) + (collider.y - y).pow(2)) < radius + collider.radius
}