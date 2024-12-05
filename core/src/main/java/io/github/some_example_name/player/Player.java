package io.github.some_example_name.player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.badlogic.gdx.utils.Array;


@SuppressWarnings("all")
public class Player   {

    
    public OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture image;
    private Animation<TextureRegion> suAnimation;
    private Animation<TextureRegion> giuAnimation;
    private Animation<TextureRegion> destraAnimation;
    private Animation<TextureRegion> sinistraAnimation;
    private Animation<TextureRegion> currentAnimation;
    private float animationTime=0;
    public float y;
    public float x;
    public float speed=1;

    private String lastdirection = "right";
    public Player(float x, float y){
        this.x = x;
        this.y = y;

    }
    public float getx(){
        return x;
    }
    public float gety(){
        return y;
    }



    public void create() {

        batch = new SpriteBatch();
        image = new Texture("player/destra.png");
        camera = new OrthographicCamera(x, y);

        Texture suSpriteSheet = new Texture("player/su.png");
        Texture giuSpriteSheet = new Texture("player/giu.png");
        Texture destraSpriteSheet = new Texture("player/destra.png");
        Texture sinistraSpriteSheet = new Texture("player/sinistra.png");
        int width=64; //change to the width of ur sprite
        int height=64; //change to the height of ur sprite
        float frameTime=0.1f;
        suAnimation = makeCustomAnimation(suSpriteSheet,width,height,frameTime);
        giuAnimation = makeCustomAnimation(giuSpriteSheet,width,height,frameTime);
        destraAnimation = makeCustomAnimation(destraSpriteSheet,width,height,frameTime);
        sinistraAnimation = makeCustomAnimation(sinistraSpriteSheet,width,height,frameTime);
        currentAnimation = suAnimation;

        camera.position.set(x , y,0);
        camera.zoom=3;
        camera.update();
    }


    public void render() {
        
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            changeAnimation(suAnimation);
            y += speed;
            camera.translate(0, speed, 0);
        }else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            changeAnimation(giuAnimation);
            y -= speed;
            camera.translate(0, -speed, 0);
        }else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            changeAnimation(destraAnimation);
            x += speed;
            camera.translate(speed, 0, 0);
        }else if  (Gdx.input.isKeyPressed(Input.Keys.A)) {
            changeAnimation(sinistraAnimation);
            x -= speed;
            camera.translate(-speed, 0, 0);
        } else{ animationTime=0;}
        animationTime += Gdx.graphics.getDeltaTime();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(currentAnimation.getKeyFrame(animationTime,true), x, y);
        batch.end();

    }

    public void dispose() {
        batch.dispose();
        image.dispose();
    }


    public void changeAnimation(Animation<TextureRegion> anim) {
        if(anim==null){
            return;
        }
        if(anim==currentAnimation){
            return;
        }
        currentAnimation = anim;
        animationTime=0;
    }

    public Animation<TextureRegion> makeCustomAnimation(Texture sheet, int width, int height, float frameTime) {
        TextureRegion[][] tmp = TextureRegion.split(sheet, width,height);
        List<TextureRegion> tmpFlattened = Arrays.stream(tmp).flatMap(Arrays::stream).collect(Collectors.toList());
        Array<TextureRegion> regions=new Array<>();

        for(TextureRegion region : tmpFlattened){
            region.setRegionX(region.getRegionX());
            region.setRegionY(region.getRegionY());
            regions.add(region);
        }

        return new Animation<>(frameTime,regions);
    }


}