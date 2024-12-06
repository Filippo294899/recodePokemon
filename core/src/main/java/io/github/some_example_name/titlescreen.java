package io.github.some_example_name;

import java.io.FileNotFoundException;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.video.VideoPlayer;
import com.badlogic.gdx.video.VideoPlayerCreator;
import com.badlogic.gdx.video.scenes.scene2d.VideoActor;

import io.github.some_example_name.player.Player;
@SuppressWarnings("all")
public class titlescreen implements Screen {
    private final Main app;
    private SpriteBatch batch;
      VideoPlayer videoPlayer;
	VideoActor videoActor;
    //City city = new City(City.this);
    public titlescreen(final Main app){
        this.app = app;
        System.out.println("uwu");

    }

    protected FileHandle getVideoFile () {
       
		return Gdx.files.internal("assets\\loading-screen\\title.webm");
	}
    @Override
    public void show() {
        batch = new SpriteBatch();
        videoPlayer = VideoPlayerCreator.createVideoPlayer();
            //this.setScreen(new City(this));
            videoActor = new VideoActor(videoPlayer);
		    videoActor.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            try {
                videoPlayer.play(getVideoFile());
                } catch (FileNotFoundException e) {
                    Gdx.app.error("gdx-video", "Oh no!");
                    System.out.println(e);
                }
                videoPlayer.setOnCompletionListener(new VideoPlayer.CompletionListener() {
                    @Override
                    public void onCompletionListener (FileHandle file) {
                        videoPlayer= null;
                        Gdx.app.log("VideoTest", file.name() + " fully played.");
                        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
                        
                        
                    }
                });
    }

    @Override
    public void render(float delta) {
        
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)&&videoPlayer!=null) {
            videoPlayer.stop();
           
            videoPlayer = null;

        }
       
            
        

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        videoActor.act(Gdx.graphics.getDeltaTime());
        batch.begin();
        if (videoPlayer!=null){
            videoActor.draw(batch, 1f);
        }else{
            app.showcity();
        }
       
        batch.end();
        

    
    }

    @Override
    public void resize(int width, int height) {
     
    }

    @Override
    public void pause() {
  
    }

    @Override
    public void resume() {
       
    }

    @Override
    public void hide() {
     
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        batch.dispose();
    }
    
}
