package io.github.some_example_name.pokemon;



import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public  class Pokemon {
    public String name;
    public int level;
    protected int hp;
    protected String type;
    Boolean isShiny; 
    public Texture frontTexture;
    public  Texture backTexture;
    public Texture Icon;
   private Animation<TextureRegion> currentAnimation;
   private Animation<TextureRegion> iconAnimation;
    private String basepath="assets/Pokemon";
    public float animationTime=0;
    

     public Pokemon(String name , int level , int hp , String type, boolean isShiny) {
        this.name = name;
        this.level = level;
        this.type = type;
        this.hp = hp;
        this.isShiny = isShiny;
         try {
            if (isShiny)
            {
               frontTexture=new Texture(basepath+"\\Front Shiny\\"+name.toUpperCase()+".png");
               backTexture=new Texture(basepath+"\\Back Shiny\\"+name.toUpperCase()+".png");
               Icon = new Texture(basepath+"\\Icons Shiny\\"+name.toUpperCase()+".png");
            }else {
               frontTexture=new Texture(basepath+"\\Front\\"+name.toUpperCase()+".png");
            backTexture=new Texture(basepath+"\\Back\\"+name.toUpperCase()+".png");
            Icon = new Texture(basepath+"\\Icons\\"+name.toUpperCase()+".png");
            }
            
            
         } catch (Exception e) {
            System.out.println(e);
         } 
         iconAnimation = makeCustomAnimation(Icon,64,128,0.15f);
         
            if (iconAnimation.getKeyFrames().length == 0) {
               throw new RuntimeException("Animation frames are empty for: ");
         }


        
     }
     public void render(){
      changeAnimation(iconAnimation);
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

    public Animation<TextureRegion> geAnimation(){
      return iconAnimation;
    } 
    public Animation<TextureRegion> getCurrentAnimation(){
      return currentAnimation;
    } 

}
