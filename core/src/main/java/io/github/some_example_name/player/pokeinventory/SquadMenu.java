package io.github.some_example_name.player.pokeinventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import io.github.some_example_name.Main;
import io.github.some_example_name.player.Squad;
import io.github.some_example_name.pokemon.Pokemon;

public class SquadMenu implements Screen {
    private final Main app;  
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private Squad S = new Squad();
    private static final int ICON_SIZE = 100;
    private static final int RECT_WIDTH = 200;
    private static final int RECT_HEIGHT = 120;
    private static final int CIRCLE_RADIUS = 200;
    private Pokemon[] squad;

    public SquadMenu(final Main app) {
        this.app = app;
        this.squad = S.getSquadPokemons();
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
    
        int centerX = Gdx.graphics.getWidth() / 2;
        int centerY = Gdx.graphics.getHeight() / 2;
    
        int rectWidth = 200;
        int rectHeight = 120;
        int iconSize = 100;
        int spacing = 20;
        int startXLeft = centerX - rectWidth - spacing;
        int startXRight = centerX + spacing;
        int startY = centerY + rectHeight + spacing;
    
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        for (int i = 0; i < squad.length; i++) {
            int x = (i < 3) ? startXLeft : startXRight;
            int y = startY - (i % 3) * (rectHeight + spacing);
            shapeRenderer.rect(x, y, rectWidth, rectHeight);
        }
        shapeRenderer.end();
    
        batch.begin();
        for (int i = 0; i < squad.length; i++) {
            Pokemon pokemon = squad[i];
            pokemon.render();
            if (pokemon != null) {
                int x = (i < 3) ? startXLeft : startXRight;
                int y = startY - (i % 3) * (rectHeight + spacing);
    
                if (pokemon.Icon != null) {
                    int iconX = x + 10;
                    int iconY = y + (rectHeight - iconSize) / 2;
                    batch.draw(pokemon.getCurrentAnimation().getKeyFrame(pokemon.animationTime, true), iconX, iconY, iconSize, iconSize);
                }
    
                font.draw(batch, pokemon.name, x + iconSize + 20, y + rectHeight / 2 + 10);
                font.draw(batch, "Lv. " + pokemon.level, x + iconSize + 20, y + rectHeight / 2 - 10);
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
        for (Pokemon pokemon : squad) {
            if (pokemon != null && pokemon.Icon != null) {
                pokemon.Icon.dispose();
            }
        }
    }
}
