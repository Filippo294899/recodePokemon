package io.github.some_example_name.player;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.Main;
import io.github.some_example_name.pokemon.Pokemon;


public class Squad {
    private static final int MaxSize = 6;
    private Pokemon[] squad = new Pokemon[MaxSize];
    private Random random = new Random();

    public Squad() {
        for (int index = 0; index < MaxSize; index++) {
            int prob = random.nextInt(4943) + 1;
            squad[index] = new Pokemon("Giratina", 10, 403, "Grass", prob == 1);
        }
    }
    public Pokemon[] getSquadPokemons() {
        return this.squad;
    }
}
