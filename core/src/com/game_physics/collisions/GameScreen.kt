package com.game_physics.collisions

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.game_physics.collisions.system.CollisionSystem
import com.game_physics.collisions.system.CollisionsGame


class GameScreen(private val game: CollisionsGame) : Screen {
    private val ball1 = Ball(30f, Color(255, 0, 0), 320f, 360f, 2f, 2f)
    private val ball2 = Ball(30f, Color(0, 0, 255), 980f, 360f, -2f, 2f)
    private val dt = 100f

    private fun update(delta: Float) {
        ball1.move(delta, dt)
        ball2.move(delta, dt)
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
        update(delta)
    }

    override fun resize(width: Int, height: Int) {}

    override fun dispose() {}

    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun show() {}
}