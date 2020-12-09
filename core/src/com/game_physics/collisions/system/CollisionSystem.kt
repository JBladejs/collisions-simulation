package com.game_physics.collisions.system

import com.badlogic.gdx.math.MathUtils.PI
import com.badlogic.gdx.math.MathUtils.atan2
import com.badlogic.gdx.utils.Array
import com.game_physics.collisions.toDegrees

object CollisionSystem {
    private val colliders = Array<CircleCollider>()
    private val collisions = Array<Collision>()

    fun add(collider: CircleCollider) = colliders.add(collider)

    fun remove(collider: CircleCollider) = colliders.removeValue(collider, true)

    fun removeCollisionsForCollider(collider: CircleCollider) {
        for (collider2 in colliders) {
            val collision = Collision(collider, collider2)
            if (collisions.contains(collision, false))
                collisions.removeValue(collision, false)
        }
    }

    fun update() {
        for (i in 0 until colliders.size - 1) {
            for (j in i + 1 until colliders.size) {
               if (colliders[i].collides(colliders[j])) {
                   if (!collisions.contains(Collision(colliders[i], colliders[j]), false)) {
                       collisions.add(Collision(colliders[i], colliders[j]))
                       colliders[i].isColiding = true
                       colliders[i].collisionAngle = atan2(colliders[j].x - colliders[i].x, colliders[j].y - colliders[i].y)
                       colliders[i].vxHit = colliders[j].vx
                       colliders[i].vyHit = colliders[j].vy
                       colliders[j].isColiding = true
                       colliders[j].collisionAngle = atan2(colliders[i].x - colliders[j].x, colliders[i].y - colliders[j].y)
                       colliders[j].vxHit = colliders[i].vx
                       colliders[j].vyHit = colliders[i].vy
                       println("Collision!")
                   }
               } else {
                   val collision = Collision(colliders[i], colliders[j])
                   if (collisions.contains(collision, false))
                       collisions.removeValue(collision, false)
               }
            }
        }
    }
}