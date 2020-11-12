package com.game_physics.collisions

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20


class GameScreen(private val game: CollisionsGame) : Screen {

    private fun update() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        with(game.renderer) {
            begin()
            //TODO: whole rendering
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