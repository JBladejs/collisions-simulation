package com.game_physics.collisions.system

import com.badlogic.gdx.utils.Array

object CollisionSystem {
    private val colliders = Array<CircleCollider>()
    private val collisions = Array<Collision>()

    fun add(collider: CircleCollider) = colliders.add(collider)

    fun remove(collider: CircleCollider) = colliders.removeValue(collider, true)


    //TODO: Refactor - Try to make it faster using .equals()
    private fun getCollision(collider1: CircleCollider, collider2: CircleCollider): Collision? {
        collisions.forEach {
            if (it.collider1 == collider1) if (it.collider2 == collider2) return it
            else if (it.collider1 == collider2 && it.collider2 == collider1) return it
        }
        return null
    }

    fun update() {
        for (i in 0 until colliders.size - 1) {
            for (j in i + 1 until colliders.size) {
               if (colliders[i].collides(colliders[j])) {
                   if (getCollision(colliders[i], colliders[j]) == null) {
                       collisions.add(Collision(colliders[i], colliders[j]))
                       println("COLLISION!!!")
                   }
               } else {
                   val collision = getCollision(colliders[i], colliders[j])
                   if (collision != null) {
                       collisions.removeValue(collision, true)
                   }
               }
            }
        }
    }
}