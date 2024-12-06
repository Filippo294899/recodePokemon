package io.github.some_example_name;

import java.io.FileNotFoundException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;
import com.badlogic.gdx.video.scenes.scene2d.VideoActor;

import io.github.some_example_name.player.Player;
import io.github.some_example_name.player.inventory.Inventory;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
@SuppressWarnings("all")
public class Main extends Game {
    private SpriteBatch batch;
    private Texture image;
    private City ourCity = null;
    
    public  void showcity(){
        if (ourCity == null) ourCity = new City(this);
        this.setScreen(ourCity);
    }
    public void openinventory(){
        this.setScreen(new Inventory(this));
    }

    @Override
    public void create() {
            
            batch = new SpriteBatch();
            this.setScreen(new titlescreen(this));
            //this.setScreen(new City(this));
                


            
            
    }
    @Override
    public void render(){
        super.render();

    }

        

    @Override
    public void dispose() {
        
        batch.dispose();

    }
}

