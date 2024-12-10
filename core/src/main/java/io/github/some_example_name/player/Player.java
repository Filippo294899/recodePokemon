
package io.github.some_example_name.player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.Array;

import io.github.some_example_name.player.inventory.Inventory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.badlogic.gdx.utils.Array;
import io.github.some_example_name.Main;

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
    public final Main app;
    TiledMap map;
    TiledMapTileLayer collisionlayer ;
    int collisionx ;
    int collisiony ;
    private Animation<TextureRegion> lasAnimation;
    private String lastdirection = "right";
    public Player(float x, float y , final Main app) {
        this.x = x;
        this.y = y;
        this.app =app;
       
      
    }
    
    
    public float getx(){
        return x;
    }
    public float gety(){
        return y;
    }
    
    

    public void create(TiledMap mappa) {

        batch = new SpriteBatch();
        image = new Texture("player/destra.png");
        camera = new OrthographicCamera(210, 140);
         map = mappa;
          collisionlayer =(TiledMapTileLayer) map .getLayers().get(1);
         collisionx = collisionlayer.getHeight();
            collisiony = collisionlayer.getWidth();
            
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
        
        if  (lasAnimation == null) {
            currentAnimation = suAnimation;
        }
        else{
            currentAnimation = lasAnimation;

        }
        
        

        camera.position.set(x , y,0);
        camera.zoom=3;
        camera.update();
        
    }
    boolean collision;
    
    public void render() {
        // Dimensioni di una cella
        float tileWidth = collisionlayer.getTileWidth();
        float tileHeight = collisionlayer.getTileHeight();
    
        // Dimensioni del giocatore (esempio, adattale alle dimensioni effettive)
        float playerWidth = 32; // larghezza del giocatore in pixel
        float playerHeight = 32; // altezza del giocatore in pixel
    
        // Calcola la nuova posizione
        float newX = x;
        float newY = y;
        if  (Gdx.input.isKeyJustPressed(Input.Keys.E)){
            app.openinventory();
        }
        if  (Gdx.input.isKeyJustPressed(Input.Keys.R)){
            app.ShowSm();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            changeAnimation(suAnimation);
            lasAnimation = suAnimation;
            newY += speed; // Movimento verso l'alto
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            changeAnimation(giuAnimation);
            lasAnimation = giuAnimation;
            newY -= speed; // Movimento verso il basso
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            changeAnimation(destraAnimation);
            lasAnimation = destraAnimation;
            newX += speed; // Movimento verso destra
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            lasAnimation = sinistraAnimation;
            changeAnimation(sinistraAnimation);
            newX -= speed; // Movimento verso sinistra
        }

         else {
            animationTime = 0; // Nessun movimento
        }
        
    
        // Verifica la collisione sulla nuova posizione
        if (!isColliding(newX, newY, playerWidth, playerHeight, tileWidth, tileHeight)) {
            // Nessuna collisione: aggiorna la posizione
            x = newX;
            y = newY;
            camera.position.set(x, y, 0);
        }
    
        // Aggiorna il tempo dell'animazione
        animationTime += Gdx.graphics.getDeltaTime();
        camera.update();
    
        // Disegna il giocatore
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(currentAnimation.getKeyFrame(animationTime, true), x, y);
        batch.end();
    }
    
    /**
     * Verifica la collisione con le celle della mappa.
     */
    private boolean isColliding(float x, float y, float width, float height, float tileWidth, float tileHeight) {
        // Calcola i bordi del giocatore
        float left = x;
        float right = x + width;
        float bottom = y;
        float top = y + height;
    
        // Controlla i quattro angoli del giocatore
        return isCellBlocked(left, bottom, tileWidth, tileHeight) ||
               isCellBlocked(left, top, tileWidth, tileHeight) ||
               isCellBlocked(right, bottom, tileWidth, tileHeight) ||
               isCellBlocked(right, top, tileWidth, tileHeight);
    }
    
    /**
     * Controlla se una cella specifica è bloccata.
     */
    private boolean isCellBlocked(float worldX, float worldY, float tileWidth, float tileHeight) {
        // Converti le coordinate del mondo in coordinate della griglia
        int cellX = (int) (worldX / tileWidth);
        int cellY = (int) (worldY / tileHeight);
    
        // Ottieni la cella
        TiledMapTileLayer.Cell cell = collisionlayer.getCell(cellX, cellY);
    
        // Se la cella non è vuota, considera che blocca
        return cell != null;
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