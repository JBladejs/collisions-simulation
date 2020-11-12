package com.game_physics.collisions.system

import com.badlogic.gdx.utils.Array

object CollisionSystem {
    private val colliders = Array<CircleCollider>()

    fun add(collider: CircleCollider) = colliders.add(collider)

    fun remove(collider: CircleCollider) = colliders.removeValue(collider, true)

    fun update() {
        for (i in 0 until colliders.size - 1) {
            for (j in i + 1 until colliders.size) {
               if (colliders[i].collides(colliders[j])) println("COLLISION!!!")
            }
        }
    }
}