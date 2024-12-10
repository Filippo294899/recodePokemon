package io.github.some_example_name.player.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import io.github.some_example_name.Main;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Inventory implements Screen {
    private final Main app;
    private SpriteBatch batch;
    private Texture texture;
    private BitmapFont font;

    public Inventory(final Main app) {
        this.app = app;
        System.out.println("uwu - Inventory screen initialized");
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        texture = new Texture("ui/talk.png");
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(200.0f, 200.0f, 0f, 0.1f);

        batch.begin();
        batch.draw(texture, 0, 0);
        font.draw(batch, "Ti trovi nell'inventario al momento", 10, 80);
        font.setColor(new Color(0,0,0,1));
        batch.end();
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            app.showcity();
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        batch.dispose();
        texture.dispose();
        font.dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        texture.dispose();
        font.dispose();
    }
}
