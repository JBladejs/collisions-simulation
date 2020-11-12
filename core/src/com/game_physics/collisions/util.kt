package com.game_physics.collisions

import com.badlogic.gdx.graphics.glutils.ShapeRenderer

fun ShapeRenderer.setColor(color: Color) = this.setColor(color.red, color.green, color.blue, 1.0f)