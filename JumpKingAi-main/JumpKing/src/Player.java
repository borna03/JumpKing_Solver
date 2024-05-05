import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Player implements Comparable<Player>{
        ImagesLoading images = new ImagesLoading();
        KeyHandler keyH = new KeyHandler();
        public Color c;

        int ID,playerx,playery, spacestate, jumptimer, begging, bego, spacetime,jumpsleft;
        double playerwidth, playerheight , velx, vely,Jump, LeftFoot, RightFoot, LeftFootspr, RightFootspr, Gravity;
        boolean Error, Falling,StateL, StateR , FaceR, FaceL;
        Random rand = new Random();

        boolean Completed,Spacepressed;
        public Player(int ID,boolean Spacepressed,boolean Completed , Color c ,int playerx,int playery,int spacestate,int jumptimer,int begging,int bego,double playerwidth,double playerheight,double velx,double vely,double Jump,double LeftFoot,double RightFoot,double LeftFootspr,double RightFootspr,double Gravity,boolean Error,boolean Falling,boolean StateL,boolean StateR, int spacetime, int jumpsleft, boolean FaceR, boolean FaceL){
                this.ID = ID;
                this.Spacepressed = Spacepressed;
                this.Completed = Completed;
                this.c = c;
                this.playerx = playerx;
                this.playery = playery;
                this.spacestate = spacestate;
                this.jumptimer = jumptimer;
                this.begging = begging;
                this.bego = bego;

                this.playerwidth = playerwidth;
                this.playerheight = playerheight;
                this.velx = velx;
                this.vely = vely;
                this.Jump = Jump;
                this.LeftFoot = LeftFoot;
                this.RightFoot = RightFoot;
                this.LeftFootspr = LeftFootspr;
                this.RightFootspr = RightFootspr;
                this.Gravity = Gravity;

                this.Error = Error;
                this.Falling = Falling;
                this.StateL = StateL;
                this.StateR = StateR;
                this.spacetime = spacetime;
                this.jumpsleft = jumpsleft;
                this.FaceR = FaceR;
                this.FaceL = FaceL;

        }
        public void CreatPlayer(LinkedList list, int GensCount){
                for(int i = 0; i < GensCount; i++){
                        float r = rand.nextFloat();
                        float h = rand.nextFloat();
                        float b = rand.nextFloat();

                        Color randomColor = new Color(r, h, b);

                        list.add(new Player(i,false,false,randomColor, (int)400,(int)500,0,0,0,0,60,60,0,0,0,0,0,0,0,8,false,true,false,false, 0,0, false, false));

                }
        }

        @Override
        public int compareTo(Player player) {
                return this.playery - player.playery;
        }
}
