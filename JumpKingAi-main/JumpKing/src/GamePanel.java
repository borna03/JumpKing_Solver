import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Collections;

public class GamePanel extends JPanel implements Runnable{
    int GensCount = 300 , JumpsLimit = 3, Tempy = 720,Tempx = 400, screenwidth = 950, screenheight = 720;
    int  testnum = 1,FPS = 420*10,playerwidth = 60, playerheight = 60, spacestate = 0, begging = 0, bego = 0, jumptimer = 0,level = 0 , CameraX = 0 , CameraY = 0, jumpsranje = 0, picka = 0,bestMoveX , bestMoveY, min = 3,minn = 0,max = 85,maxx = 1, testcounter = 0, u = 0;
    double  Gravity = 200,playerx = 400,playery = 500, vely,Jump , LeftFoot = 0, RightFoot = 0, LeftFootspr = 1, RightFootspr = 1;
    float maxWidth, right , left , realWidth , maxHeight,realHeight, difX, difY, hright, hleft;
    boolean Falling = true, StateL = false, StateR = false;
    public LinkedList<Item> items = new LinkedList<Item>();
    public LinkedList<Item> Coins = new LinkedList<Item>();
    public List<Player> playerss = new ArrayList<Player>();
    public List<Player> ComepletedPlayers = new ArrayList<Player>();
    public List<Integer> IDsBias = new ArrayList<Integer>();
    int jebacutimajku = 0;
    int [][] Moves = new int[GensCount][JumpsLimit];
    int [][] Faceings = new int[GensCount][JumpsLimit];
    public ArrayList<Integer> testcase = new ArrayList<Integer>();
    public ArrayList<Integer> testcases = new ArrayList<Integer>();


    Random rand = new Random();
    KeyHandler keyH = new KeyHandler();
    MouseClick mouse = new MouseClick();
    ImagesLoading images = new ImagesLoading();
    Thread gameThread;


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenwidth, screenheight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseListener(mouse);
        this.setFocusable(true);


    }

    public void startGameThread(){

        gameThread = new Thread(this);

        CreatePlayer(playerss , GensCount, 400 , 500);

        Coins.add(new Platform(ObjectIDs.Platform,Color.WHITE,(int)623 ,(int) 547- screenheight*2,(int)731 - 620,605 - 547));
        Coins.add(new Platform(ObjectIDs.Platform,Color.WHITE,(int)83 ,(int) 109- screenheight*4,(int)139 - 83,171 - 109));
        Coins.add(new Platform(ObjectIDs.Platform,Color.WHITE,(int)655 ,(int) 413- screenheight*4,(int)720 - 655,471 - 413));
        Coins.add(new Platform(ObjectIDs.Platform,Color.WHITE,(int)363 ,(int) 379- screenheight*3,(int)416 - 363,435 - 379));
        Coins.add(new Platform(ObjectIDs.Platform,Color.WHITE,(int)731 ,(int) 72- screenheight*3,(int)808 - 731,137 - 72));






        items.add(new Platform(ObjectIDs.Platform,Color.red,(int)0,(int)660,(int)screenwidth,screenheight - 600));
        items.add(new Platform(ObjectIDs.Platform,Color.blue,(int)0,(int)369,(int)250,717 - 369));
        items.add(new Platform(ObjectIDs.Platform,Color.yellow,(int)689,(int)372,(int)938 - 650,659 - 368));
        items.add(new Platform(ObjectIDs.Platform,Color.blue,(int)363,(int)81,(int)577 - 363,177 - 81));

        items.add(new Platform(ObjectIDs.Platform,Color.red, 580 ,  665 - screenheight - 60, 775 - 580 , 60));
        items.add(new Platform(ObjectIDs.Platform,Color.red, 806 ,  403 - screenheight, 927 - 806 , 60));
        items.add(new Platform(ObjectIDs.Platform,Color.red, 504 ,  403 - screenheight, 645 - 505 , 60));
        items.add(new Platform(ObjectIDs.Platform,Color.red, 234 ,  213 - screenheight, 379 - 234 , 335 - 214));
        items.add(new Platform(ObjectIDs.Platform,Color.red, 8 ,  158 - screenheight, 157 - 8 , 342 - 158));


        items.add(new Platform(ObjectIDs.Platform,Color.white, 414 ,  610 - 2*screenheight, 504 - 410 , 640 - 600 -3));
        items.add(new Platform(ObjectIDs.Platform,Color.blue, 626 ,  610 - 2*screenheight, 741 - 632 + 5 , 640 - 595));
        items.add(new Platform(ObjectIDs.Platform,Color.pink, 836 ,  511 - 2*screenheight, 923 - 836 , 540 - 511));
        items.add(new Platform(ObjectIDs.Platform,Color.yellow, 378 ,  448 - 2*screenheight, 663 - 475 , 508 - 448));
        items.add(new Platform(ObjectIDs.Platform,Color.green, 566 ,  419 - 2*screenheight, 662 - 566 , 446 - 357));
        items.add(new Platform(ObjectIDs.Platform,Color.green, 21 ,  194 - 2*screenheight, 127 - 21 , 226 - 194));
        items.add(new Platform(ObjectIDs.Platform,Color.blue, 318 ,  240 - 2*screenheight, 426 - 318 , 329 - 240));


        items.add(new Platform(ObjectIDs.Platform,Color.blue, 8 ,  438 - 3*screenheight, 132 - 12 , 475 - 438));
        items.add(new Platform(ObjectIDs.Platform,Color.black, 278 ,  440 - 3*screenheight, 419 - 272 , 468 - 430));
        items.add(new Platform(ObjectIDs.Platform,Color.red, 273 ,  190 - 3*screenheight, 354 - 273 , 340 - 195));
        items.add(new Platform(ObjectIDs.Platform,Color.PINK, 866 ,  263 - 3*screenheight, 947 - 865 , 292 - 263));



        items.add(new Platform(ObjectIDs.Platform,Color.red, 273 ,  647 - 3*screenheight, 419 - 280 , 716 - 600));
        items.add(new Platform(ObjectIDs.Platform,Color.PINK, 659 ,  150 - 3*screenheight, 802 - 645 , 184 - 149));
        items.add(new Platform(ObjectIDs.Platform,Color.white, 659 ,  150 - 3*screenheight, 691 - 659 , 324 - 5));
        items.add(new Platform(ObjectIDs.Platform,Color.white, 273 ,   - 3*screenheight - 90, 311 - 275 , 200 + 80));
        items.add(new Platform(ObjectIDs.Platform,Color.pink, 594 ,  326 - 3*screenheight, 692 - 627 , 469 - 326));


        items.add(new Platform(ObjectIDs.Platform,Color.yellow, 82 ,  179-4*screenheight, 143 - 82 , 203 - 172));
        items.add(new Platform(ObjectIDs.Platform,Color.yellow, 320 ,  118 - 4*screenheight, 380 - 320 , 142 - 114));
        items.add(new Platform(ObjectIDs.Platform,Color.yellow, 447 ,  150 - 4*screenheight, 508 - 447 , 177 - 149));
        items.add(new Platform(ObjectIDs.Platform,Color.PINK, 575 ,  179 - 4*screenheight, 635 - 575 , 206 - 179));
        items.add(new Platform(ObjectIDs.Platform,Color.PINK, 874 ,  324 - 4*screenheight, 943 - 874 , 366 - 324));
        items.add(new Platform(ObjectIDs.Platform,Color.yellow, 657 ,  480 - 4*screenheight, 728 - 657 , 515 - 492));
        items.add(new Platform(ObjectIDs.Platform,Color.yellow, 684 ,  625 - 4*screenheight, 728 - 652 , 657 - 625));
        items.add(new Platform(ObjectIDs.Platform,Color.yellow, 653 ,  625 - 4*screenheight, 751 - 720 , 716 - 590));
        items.add(new Platform(ObjectIDs.Platform,Color.red, 0 ,  479 - 4*screenheight, 80 , 511 - 479));
        items.add(new Platform(ObjectIDs.Platform,Color.red, 223 ,  624 - 4*screenheight, 307 - 221 , 659 - 624));


        items.add(new Platform(ObjectIDs.Platform,Color.red, 309 ,  662 - 5*screenheight, 639 - 300 , 80));
        items.add(new Platform(ObjectIDs.Platform,Color.PINK, 256 ,  482 - 5*screenheight, 343 - 256 , 509 - 482));
        items.add(new Platform(ObjectIDs.Platform,Color.PINK, 571 ,  162 - 5*screenheight, 928 - 550 , 511 - 162));
        items.add(new Platform(ObjectIDs.Platform,Color.PINK, 0 ,  367 - 5*screenheight, 111 , 399 - 367));
        items.add(new Platform(ObjectIDs.Platform,Color.PINK, 111 ,  144 - 5*screenheight, 188 - 111, 174 - 144));
        items.add(new Platform(ObjectIDs.Platform,Color.red, 159 ,  174 - 5*screenheight, 188 - 159 , 217 - 166));
        items.add(new Platform(ObjectIDs.Platform,Color.PINK, 159 ,  225 - 5*screenheight, 378 - 159, 257 - 225));
        items.add(new Platform(ObjectIDs.Platform,Color.PINK, 269 ,  0 - 5*screenheight, 376 - 269 , 114));
        items.add(new Platform(ObjectIDs.Platform,Color.red, 0 ,  0 - 5*screenheight, 269 , 33));
        items.add(new Platform(ObjectIDs.Platform,Color.black, 0 ,  33 - 5*screenheight, 17 , 384 - 50));
        items.add(new Platform(ObjectIDs.Platform,Color.yellow, 568 ,  0 - 5*screenheight, 832 - 568 , 48));
        items.add(new Platform(ObjectIDs.Platform,Color.red, 646 ,  48 - 5*screenheight, 831 - 645 , 81 - 48));
        items.add(new Platform(ObjectIDs.Platform,Color.blue, 628 + 10 + 5,  665 - 4*screenheight, 10 , 60));
        items.add(new Platform(ObjectIDs.Platform,Color.blue, 253 ,  665 - 4*screenheight, 20 , 275));
        System.out.println("Current working directory: " + new java.io.File(".").getAbsolutePath());

        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 100000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){
            if(!keyH.foreal && picka == 0){
                playerss.clear();
                playerss.add(new Player(0,false,false,Color.black, (int)400,(int)500,0,0,0,0,60,60,0,0,0,0,0,0,0,8,false,true,false,false, 0,0, false, false));

                GensCount = 1;
                JumpsLimit = testcase.size();
                FPS = 200;
                picka+=1;
                drawInterval = 1000000000 / FPS;
                nextDrawTime = System.nanoTime() + drawInterval;
                playerss.get(0).jumpsleft = 0;

            }
            if(keyH.generate && jumpsranje !=6999){
                int bias = 0;
                if(Coins.size() > 0 ){
                for(Player player: playerss){
                    for(Item i: Coins){
                        if(i.ID == ObjectIDs.Platform){
                        Platform p = (Platform)i;
                        if(new Rectangle((int)player.playerx,(int)player.playery, (int)player.playerwidth,(int)player.playerheight).intersects(p.x,p.y, p.width, p.height)) {
                                player.playery -= 100;
                                IDsBias.add(player.ID);
                        }}}}}
                Collections.sort(playerss);

                u = playerss.get(0).ID;
                bestMoveX = playerss.get(0).playerx;
                bestMoveY = playerss.get(0).playery;


                for(int biases = 0; biases < IDsBias.size(); biases++){
                    if(playerss.get(0).ID == IDsBias.get(biases)){
                        bestMoveY +=100;
                        break;
                    }
                }

                for(int j = 0; j<JumpsLimit ; j++){
                    testcase.add(Moves[u][j]);
                    testcases.add(Faceings[u][j]);
                    for (int range = 0; range < testcase.size(); range ++){
                        System.out.print(testcase.get(range) + " ");
                    }System.out.println(" ");
                }

                playerss.clear();
                IDsBias.clear();
                bias = 0;

                CreatePlayer(playerss , GensCount, bestMoveX , bestMoveY);

                keyH.generate = false;
                Tempx = bestMoveX;
                Tempy = bestMoveY;
            }else if(jumpsranje !=6999 && keyH.generate == false){
            for(Player player: playerss){
                if(playerss.size() > 0){

                    if(player.jumpsleft == JumpsLimit && player.Completed == false){
                        jumpsranje+=1;
                        player.Completed = true;
                    }

                    update(player);
                    repaint();
                    tick(player);

                    if (player.spacestate == 1 && player.vely < 2){
                        player.playery += 25;
                        player.playerheight -= 25;
                    }

                    if (player.jumptimer > 90){
                        player.spacestate = 0;
                        player.begging = 1;
                        player.bego = 1;
                        player.jumptimer = 90;
                        player.Error = true;
                    }
                    if(player.vely > 1.5 || player.vely < 0.0){
                        player.Error = true;
                    }else{
                        player.Error = false;
                    }

                    if (player.spacestate == 0 && player.begging > 0 && player.bego == 1 && player.vely < 2 && player.vely > -0.1 && player.jumptimer > 1){
                        player.playery -= 25;
                        player.playerheight += 25;
                        player.Jump = player.jumptimer/5;
                        player.vely = -player.Jump;
                        player.jumptimer = 0;
                    }
                }
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 100000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }}}}}



    public void update(Player player){
        if(keyH.SpacePressed == true && player.Error == false){
            player.spacestate += 1;
            player.begging += 1;
            player.bego = 0;

        }else{
            player.spacestate = 0;
            player.bego += 1;
        }
        if(keyH.RigthPressed == true){
            player.FaceL = false;
            player.FaceR = true;
        }if(keyH.LeftPressed == true){
            player.FaceL = true;
            player.FaceR = false;
        }
    }

    public void tick(Player player){
        player.playery+=player.vely;
        if(player.vely < 8 && player.Falling){
            player.vely+=0.5;
        }
        else if(!player.Falling && player.vely > 0){
            player.vely = 0;
        }
        if(player.playerx < 0){
            player.FaceL = false;
            player.FaceR = true;
        }
        if(player.playerx + player.playerwidth > screenwidth){
            player.FaceR = false;
            player.FaceL = true;
        }
        if(player.vely > 2 || player.vely < 0){
            if (player.FaceR == true && player.FaceL == false){
                player.playerx+=8;
            }if (player.FaceR == false && player.FaceL == true){
                player.playerx-=8;
            }
        }
        if(player.vely == 1.5 || player.vely == 0.0 || player.vely == 1 || player.vely == 0.5){
            if(player.spacetime < 15){
                player.spacetime += 1;
            }else{
                player.spacetime+=0;
            }
        }else{
            if(player.spacetime < -10){
                player.spacetime+=0;
            }else{
                player.spacetime-=1;
            }
        }
        if(player.jumptimer == 0 && player.spacetime == 14){
            if (player.jumpsleft <  JumpsLimit){
                int b = (int)(Math.random()*(max-min+1)+min);
                int a = (int)(Math.random()*(maxx-minn+1)+minn);
                int face = 0;
                if(keyH.foreal == true){
                    if(a == 0){
                        player.FaceR = true;
                        player.FaceL = false;
                        face = 0;
                        player.jumptimer = b;
                    }else{
                        player.FaceR = false;
                        player.FaceL = true;
                        face = 1;
                        player.jumptimer = b;
                    }
                    Moves[player.ID][player.jumpsleft] = b;
                    Faceings[player.ID][player.jumpsleft] = face;
                }if(keyH.foreal == false){
                    if(testcases.get(player.jumpsleft) == 0){
                        player.FaceR = true;
                        player.FaceL = false;
                        face = 1;
                        player.jumptimer = testcase.get(player.jumpsleft);
                    }else{
                        player.FaceR = false;
                        player.FaceL = true;
                        face = 0;
                        player.jumptimer = testcase.get(player.jumpsleft);
                    }
                    System.out.println(testcase.get(player.jumpsleft) + "  " + player.FaceR);
                }
                player.spacestate = 0;
                player.begging += 1;
                player.bego = 1;
                player.spacetime = -9;

                player.jumpsleft+=1;
            }
        }

        Collision(player);
    }

    public void CreatePlayer(List list, int Genscount, int x , int y){
        for(int i = 0; i < Genscount; i++){
            float r = rand.nextFloat();
            float h = rand.nextFloat();
            float b = rand.nextFloat();

            Color randomColor = new Color(r, h, b);

            list.add(new Player(i,false,false,randomColor, x,y,0,0,0,0,60,60,0,0,0,0,0,0,0,8,false,true,false,false, 0,0, false, false));

        }
    }
    public void  Collision(Player player){
            player.Falling = true;
            for(Item i: items){
                if(i.ID == ObjectIDs.Platform){
                    Platform p = (Platform)i;
                    if(new Rectangle((int)player.playerx,(int)player.playery, (int)player.playerwidth,(int)player.playerheight).intersects(p.x,p.y, p.width, p.height)){
                        maxWidth = (int)player.playerwidth + p.width;
                        int objectY, objectX, playery, playerx;
                        objectY = p.y;
                        objectX = p.x;
                        playery = player.playery;
                        playerx = player.playerx;


                        if(objectX < playerx){
                            left = objectX;
                        }else {
                            left = (float)playerx;
                        }
                        if((objectX + p.width) < (playerx + player.playerwidth)){
                            right = (float)playerx + (float)player.playerwidth;
                        }else{
                            right = objectX + p.width;
                        }

                        realWidth = right - left;
                        difX = maxWidth - realWidth;


                        maxHeight = (int)player.playerheight + p.height;

                        if(objectY > playery){
                            hleft = (float)playery;
                        }else if(objectY < playery){
                            hleft = objectY;
                        }
                        if((objectY + p.height) > (playery + player.playerheight)){
                            hright = objectY + p.height;
                        }else if((objectY + p.height) < (playery + player.playerheight)){
                            hright = (float)playery + (float)player.playerheight;
                        }

                        realHeight = hright - hleft;
                        difY = maxHeight - realHeight;

                        if (difY > difX || (int)difY == (int)difX) {
                            player.Falling = true;
                            if (player.FaceR == false && player.FaceL == true){
                                player.playerx +=10;
                                player.FaceR = true;
                                player.FaceL = false;
                            }else if(player.FaceR == true && player.FaceL == false){
                                player.playerx -=10;
                                player.FaceR = false;
                                player.FaceL = true;
                            }
                        }

                        if (difX > difY){
                            if(difY + 10 > difX) {
                                player.Falling = true;
                                if (player.FaceR == false && player.FaceL == true) {
                                    player.playerx +=10;
                                    player.FaceR = true;
                                    player.FaceL = false;
                                } else if (player.FaceR == true && player.FaceL == false) {
                                    player.playerx -=10;
                                    player.FaceR = false;
                                    player.FaceL = true;
                                }
                            }else{
                            if((player.playery + player.playerheight) > (p.height + p.y)){
                                player.vely = -1;
                            }
                            if(player.vely >= 0){
                                player.playery = p.y-(int)player.playerheight;
                                player.Falling = false;
                            }
                            if(player.vely < 0){
                                player.playery-=(player.vely-2);
                                player.vely = -1*player.vely;
                                player.Falling = true;
                            }}
                        }
                    }
                }
            }
        }

    @Override
    public void paint(Graphics g){
        super.paintComponent(g);
        g.translate(CameraX , keyH.counter * screenheight);
        g.drawImage(images.lvl1, 0 ,0,this);
        g.drawImage(images.lvl2, 0 ,0 - screenheight,this);
        g.drawImage(images.lvl3, 0 ,0 - (2*screenheight) ,this);
        g.drawImage(images.lvl4, 0 ,0 - (3*screenheight) ,this);
        g.drawImage(images.lvl5, 0 ,0 - (4*screenheight) ,this);
        g.drawImage(images.lvl6, 0 ,0 - (5*screenheight) ,this);



        for(Player player: playerss){

            if(player.playerheight >60 || player.vely > 2){
                player.playerheight = 60;
            }
            if(player.vely < -1.7 && player.FaceR == true){
                g.drawImage(images.jumpingRight, (int)player.playerx ,(int)player.playery,this);
            }if(player.vely < -1.7 && player.FaceL == true){
                g.drawImage(images.jumpingLeft, (int)player.playerx ,(int)player.playery,this);
            }if(player.vely > 1.7 && player.FaceR == true){
                g.drawImage(images.fallingRight, (int)player.playerx ,(int)player.playery,this);
            }if(player.vely > 1.7 && player.FaceL == true){
                g.drawImage(images.fallingLeft, (int)player.playerx ,(int)player.playery,this);
            }if(player.vely > -0.1 && player.vely < 1.7 && keyH.SpacePressed == false){
                if(player.FaceR == true){
                    g.drawImage(images.idleKingRight, (int)player.playerx ,(int)player.playery,this);
                }if(player.FaceL == true){
                    g.drawImage(images.idleKingLeft, (int)player.playerx ,(int)player.playery,this);
                }
            }
            if(player.playerheight >60 || player.vely > 2){
                player.playerheight = 60;
            }
//            g.setColor(player.c);
//            g.drawRect(player.playerx,player.playery,(int)player.playerwidth,(int)player.playerheight);
//            g.fillRect(player.playerx,player.playery,(int)player.playerwidth,(int)player.playerheight);
        }
        for(Item i: items){
            i.Render(g);
        }for(Item i: Coins){
            i.Render(g);
        }
    }
}
