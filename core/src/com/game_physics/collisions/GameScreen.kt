package com.game_physics.collisions

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys.*
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.game_physics.collisions.system.CollisionSystem


class GameScreen(private val game: CollisionsGame) : Screen {
    private var ball1 = Ball(30f, Color(255, 0, 0), 320f, 370f, 4f, 0f)
    private var ball2 = Ball(30f, Color(0, 0, 255), 880f, 360f, -3f, 0f)
    private val dt = 33f

    private fun update(delta: Float) {
        ball1.grabForce(ball2.vx,ball2.vy)
        ball2.grabForce(ball1.vx,ball1.vy)
        ball1.move(delta, dt)
        ball2.move(delta, dt)
        CollisionSystem.update()

        if (Gdx.input.isKeyPressed(NUMPAD_1)){
            ball1.x = 320f
            ball1.y = 360f
            ball1.vx = 4f
            ball1.vy = 0f

            ball2.x = 880f
            ball2.y = 360f
            ball2.vx = 0f
            ball2.vy = 0f
        }
        if (Gdx.input.isKeyPressed(NUMPAD_2)){
            ball1.x = 320f
            ball1.y = 360f
            ball1.vx = 5f
            ball1.vy = 0f

            ball2.x = 680f
            ball2.y = 360f
            ball2.vx = 2f
            ball2.vy = 0f
        }
        if (Gdx.input.isKeyPressed(NUMPAD_3)){
            ball1.x = 320f
            ball1.y = 360f
            ball1.vx = 4f
            ball1.vy = 0f

            ball2.x = 880f
            ball2.y = 360f
            ball2.vx = -4f
            ball2.vy = 0f
        }
        if (Gdx.input.isKeyPressed(NUMPAD_5)){
            ball1.x = 320f
            ball1.y = 340f
            ball1.vx = 4f
            ball1.vy = 0f

            ball2.x = 880f
            ball2.y = 360f
            ball2.vx = -4f
            ball2.vy = 0f
        }
        if (Gdx.input.isKeyPressed(NUMPAD_6)){
            ball1.x = 320f
            ball1.y = 380f
            ball1.vx = 4f
            ball1.vy = 0f

            ball2.x = 880f
            ball2.y = 360f
            ball2.vx = 0f
            ball2.vy = 0f
        }
        if (Gdx.input.isKeyPressed(NUMPAD_7)){
            ball1.x = 520f
            ball1.y = 560f
            ball1.vx = 0f
            ball1.vy = -3f

            ball2.x = 580f
            ball2.y = 160f
            ball2.vx = 0f
            ball2.vy = 3f
        }
        if (Gdx.input.isKeyPressed(NUMPAD_8)){
            ball1.x = 320f
            ball1.y = 360f
            ball1.vx = 2f
            ball1.vy = 2f

            ball2.x = 980f
            ball2.y = 360f
            ball2.vx = -2f
            ball2.vy = 2f
        }
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