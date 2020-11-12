package com.game_physics.collisions

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.game_physics.collisions.system.CollisionSystem
import com.game_physics.collisions.system.CollisionsGame


class GameScreen(private val game: CollisionsGame) : Screen {
    private val ball1 = Ball(30f, Color(255, 0, 0), 320f, 360f, 2.5f, 2.5f)
    private val ball2 = Ball(30f, Color(0, 0, 255), 980f, 360f, -2.5f, 2.5f)

    private fun update() {
        ball1.move()
        ball2.move()
        CollisionSystem.update()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        with(game.renderer) {
            begin()
            ball1.render(this)
            ball2.render(this)
            end()
        }
        update()
    }

    override fun resize(width: Int, height: Int) {}

    override fun dispose() {}

    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun show() {}
}