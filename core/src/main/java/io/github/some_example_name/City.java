package io.github.some_example_name;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.some_example_name.player.Player;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class City  implements Screen  {
    private SpriteBatch batch;
    private Texture image;
    public float y=140;
    public float x=210;
    private final Main app;
    Player player = new Player(x,y);



    public City(final Main app){
        this.app = app;
        System.out.println("uwu");

    }
    @Override
    public void show() {
        System.out.println("create");
        player.create();
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");


    }

    @Override
    public void render(float delta) {
        
        ScreenUtils.clear(255.0f, 0.15f, 0.2f, 1f);
        batch.begin();
        player.render();
        batch.draw(image, 140, 210);

        batch.end();
        System.out.println("rendering");

    }
        @Override
    public void dispose() {
        player.dispose();
        batch.dispose();
        image.dispose();
        System.out.println("dispose");
    }
        
       
        @Override
        public void resize(int width, int height) {
            // TODO Auto-generated method stub
           
        }
        @Override
        public void pause() {
            // TODO Auto-generated method stub
         
        }
        @Override
        public void resume() {
            // TODO Auto-generated method stub
          
        }
        @Override
        public void hide() {
            // TODO Auto-generated method stub
           
        }
}