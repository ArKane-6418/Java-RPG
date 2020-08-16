// Eventual Reverie
// Every input in the Main Menu, Instructions, Map Selection, and Battle Screen is mouse input based.
// Traditional RPG with health, enemies, bosses, etc.
// Moving with con.currentChar();
// To win the game, you have to defeat all the enemies.
// Defeating each enemy will give you permanent stat increases, similar to a level up.
// Instructions will be on a separate screen.
// Similar to Final Fantasy

import arc.*;
import java.awt.*;
import java.awt.image.*;

public class joshuaoRPG{
  public static void main(String[]args){
    
    
    Console con = new Console(600, 800);
    Console battle = new Console(800,600);
    
    // -----------------------Initializing variables, drawing the map, drawing each character sprite----------------------------------------------------------
    
    BufferedImage PlayerMFront = con.loadImage("Male Char Front Sprite.png"); // Loading a front sprite when the character moves down
    BufferedImage PlayerMBack = con.loadImage("Male Char Back Sprite.png"); // Loading a back sprite when the character moves up
    BufferedImage PlayerMLeft = con.loadImage("Male Char Left Sprite.png"); // Loading a left sprite when the character moves left
    BufferedImage PlayerMRight = con.loadImage("Male Char Right Sprite.png"); // Loading a right sprite when the character moves right
    BufferedImage MainMenu = con.loadImage("Main Menu.png"); // Loading the main menu at the beginning
    BufferedImage MapSelection = con.loadImage("Map Selection Menu.png"); // Loading the map selection screen when the player clicks play
    
    
    
    int intHP = 100; // Health that changes after the enemy attack
    int intTotalHP = 100; // Amount of Total Health
    int intAtk = 30; // Amount of Attack
    int intDef = 10; // Amount of Defense
    int intEn1HP = 50;
    int intEn1TotalHP = 50;
    int intEn1Atk = 15;
    int intEn1Def = 0;
    int intEn2HP = 85;
    int intEn2TotalHP = 85;
    int intEn2Atk = 15;
    int intEn2Def = 3;
    int intMid1HP = 200;
    int intMid1TotalHP = 200;
    int intMid1Atk = 30;
    int intMid1Def = 10;
    int intMid2HP = 250;
    int intMid2TotalHP = 250;
    int intMid2Atk = 40;
    int intMid2Def = 8;
    int intBossHP = 500;
    int intBossTotalHP = 500;
    int intBossAtk = 70;
    int intBossDef = 15;
    char chrMove; //Moving the character
    String strMap[][] = new String[20][20]; 
    BufferedImage[][] strBattle;
    int intLineCount = 0;
    int intCount1 = 0;
    int intCount2 = 0;
    int intNumber = 3;
    int intPosX1 = 270; // X Position of the player in the first map
    int intPosY1 = 330; // Y Position of the player in the first map
    int intPosX2 = 270; // X Position of the player in the second map
    int intPosY2 = 540; // Y Position of the player in the second map
    int intMouseX = 0;
    int intMouseY = 0;
    int intMouseBut = 0;
    int intMapX = 0; 
    int intMapY = 0;
    int intKills = 0;
    int intDmgCalc;
    int intEnDmgCalc;
    String strOption;
    String strName;
    boolean blnGame = true;
    boolean blnGame2 = true;
    boolean blnBattle = true;
    boolean blnBattle2 = true;
    boolean blnMainMenu = true;
    boolean blnInstructions = true;
    boolean blnMapSelect = true;
    
    
//    Console battle = new Console(800,600);
//    BufferedImage ForestBackground = battle.loadImage("Forest Background.png");
//    battle.drawImage(ForestBackground, 0, 0);
//    battle.repaint();
    
    // Main Menu
    
    while(blnMainMenu = true){
      
      con.drawImage(MainMenu,0, 0);
      con.repaint();
      
      
      intMouseX = con.currentMouseX();
      intMouseY = con.currentMouseY();
//      System.out.println(intMouseX);
//      System.out.println(intMouseY);
      intMouseBut = con.currentMouseButton();
      
      con.sleep(1000/60);
      
      if(intMouseX >= 246 && intMouseX <= 386 && intMouseY >= 360 && intMouseY <= 462 && intMouseBut == 1){
        
        con.setDrawColor(Color.BLACK);
        con.fillRect(0,0, 600,874);
        
        con.println("What is your name, warrior?");
        strName = con.readLine();
        con.clear();
        
        while(blnMapSelect == true){
          
          con.drawImage(MapSelection, 0, 0);
          
          intMouseX = con.currentMouseX();
          intMouseY = con.currentMouseY();
//          System.out.println(intMouseX);
//          System.out.println(intMouseY);
          intMouseBut = con.currentMouseButton();
          
          con.sleep(1000/60);
          
          if((intMouseX >= 8 && intMouseY >= 384 && intMouseX <= 265 && intMouseY <= 700 && intMouseBut == 1) || (intMouseX >= 312 && intMouseY >= 380 && intMouseX <= 580 && intMouseY <= 700 && intMouseBut == 1)){
            blnMapSelect = false;
          }
          
        }
        //-----------------------------------------MAP 1: FORESTS------------------------------------------------------------------------
        
        
        
        if(intMouseX >= 8 && intMouseY >= 384 && intMouseX <= 265 && intMouseY <= 700 && intMouseBut == 1){
          
          con.clear();
          
          strMap = RPGMethods.loadRPGMap("RPGmap1.csv");
          strBattle = RPGMethods.loadBattleFrames(con);
          RPGMethods.printRPGMap1(strMap, con);
          RPGMethods.HUDDisplay(strName, intHP, intTotalHP, intAtk, intDef, con);         
          
          int intEnemies = RPGMethods.EnemyCount(strMap);
          
          while(blnGame == true){
            
            
            
            //-----------------------------------Gameplay Mechanics-----------------------------------------------------------------------------------------------------------
            
            
            chrMove = con.currentChar();
            
            if(chrMove == 'w' || chrMove == 'W'){
              intPosY1 = intPosY1 - 30;
              RPGMethods.printRPGMap1(strMap, con);
              RPGMethods.HUDDisplay(strName, intHP, intTotalHP,intAtk, intDef, con);         
              intNumber = 1;
            }else if(chrMove == 'a' || chrMove == 'A'){
              intPosX1 = intPosX1 - 30;
              RPGMethods.printRPGMap1(strMap, con);
              RPGMethods.HUDDisplay(strName, intHP, intTotalHP, intAtk, intDef, con);         
              intNumber = 2;
            }else if(chrMove == 's' || chrMove == 'S'){
              intPosY1 = intPosY1 + 30;
              RPGMethods.printRPGMap1(strMap, con);
              RPGMethods.HUDDisplay(strName, intHP, intTotalHP, intAtk, intDef, con);         
              intNumber = 3;
            }else if(chrMove == 'd' || chrMove == 'D'){
              intPosX1 = intPosX1 + 30;
              RPGMethods.printRPGMap1(strMap, con);
              RPGMethods.HUDDisplay(strName, intHP, intTotalHP, intAtk, intDef, con);         
              intNumber = 4;
            }
            if(intPosX1 < 0 || intPosX1 > 570 || intPosY1 > 570 || intPosY1< 0){
              if(chrMove == 'w' || chrMove == 'W'){
                intPosY1 = intPosY1 + 30;
              }else if(chrMove == 'a' || chrMove == 'A'){
                intPosX1 = intPosX1 + 30;
              }else if(chrMove == 's' || chrMove == 'S'){
                intPosY1 = intPosY1- 30;
              }else if(chrMove == 'd' || chrMove == 'D'){
                intPosX1 = intPosX1 - 30;
              }
              
            }else if(strMap[intPosY1/30][intPosX1/30].equals("tree")){
              if(chrMove == 'w' || chrMove == 'W'){
                intPosY1 = intPosY1 + 30;
              }else if(chrMove == 'a' || chrMove == 'A'){
                intPosX1 = intPosX1 + 30;
              }else if(chrMove == 's' || chrMove == 'S'){
                intPosY1 = intPosY1 - 30;
              }else if(chrMove == 'd' || chrMove == 'D'){
                intPosX1 = intPosX1 - 30;
              }
              
              
              
            }else if(strMap[intPosY1/30][intPosX1/30].equals("sword")){
              strMap[intPosY1/30][intPosX1/30] = "grass";
              intAtk = intAtk + 1;
              con.repaint();
              
              
              
            }else if(strMap[intPosY1/30][intPosX1/30].equals("shield")){
              strMap[intPosY1/30][intPosX1/30] = "grass";
              intDef = intDef + 1;
              con.repaint();
              
              
              
            }else if(strMap[intPosY1/30][intPosX1/30].equals("SHwater") || strMap[intPosY1/30][intPosX1/30].equals("Dwater")){
              intHP = 0;
              blnGame = false;
              
              
              
            }else if(strMap[intPosY1/30][intPosX1/30].equals("enemy1")){
              RPGMethods.printBattle(battle, intPosX1, intPosY1);
              RPGMethods.BattleHUDEn1(strName, intHP, intTotalHP, intAtk, intDef, intEn1HP, intEn1TotalHP, intEn1Atk, intEn1Def, battle);
              
              blnBattle = true;
              while(blnBattle == true){
                intMouseX = battle.currentMouseX();
                intMouseY = battle.currentMouseY();
                intMouseBut = battle.currentMouseButton();;
//                System.out.println(intMouseX);
//                System.out.println(intMouseY);
                
                battle.sleep(1000/60);
                
                if(intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){ // Clicking Attack runs the attack animation
                  strBattle = RPGMethods.loadBattleFrames(battle); // Loading the animation frames into an array
                  intDmgCalc = RPGMethods.DmgCalc(intAtk, intEn1Def); // Damage calculation taking the Player's attack stat and subtracting it by the Enemy's defense stat +/- 1
                  intEn1HP = RPGMethods.BattleAnimationFightEn1(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intEn1HP, intEn1TotalHP, intEn1Atk, intEn1Def, intDmgCalc, battle); // Runs the battle animation for the player
                  
                  if(intEn1HP > 0 && intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){ //Runs the enemy animation as long as the enemy's health is above 0.
                    intEnDmgCalc = RPGMethods.EnDmgCalc(intEn1Atk, intDef); // Enemy Damage Calculator depending on Enemy's attack stat and player's defense stat
                    intHP = RPGMethods.BattleAnimationEn1(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intEn1HP, intEn1TotalHP, intEn1Atk, intEn1Def, intEnDmgCalc, battle);
                    if(intHP <= 0){
                      battle.sleep(1000);
                      battle.clear();
                      RPGMethods.GameOver2(battle);
                      battle.repaint();
                      battle.sleep(2000);
                      battle.closeConsole();
                    }  
                  }
                }else if(intMouseX >= 662 && intMouseY >= 486 && intMouseX <= 746 && intMouseY <= 524 && intMouseBut == 1){ // Clicking Guard to reduce oncoming damage
                  intEnDmgCalc = RPGMethods.EnDmgCalc(intEn1Atk, intDef); // Damage Calculation for the enemy
                  intHP = RPGMethods.GuardEn1(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intEn1HP, intEn1TotalHP, intEn1Atk, intEn1Def, intEnDmgCalc, battle); // Changes Enemy's attack to half of the original
                  if(intHP <= 0){
                    battle.sleep(1000);
                    battle.clear();
                    RPGMethods.GameOver2(battle);
                    battle.repaint();
                    battle.sleep(2000);
                    battle.closeConsole();
                  } 
                }else if(intMouseX >= 662 && intMouseY >= 534 && intMouseX <= 721 && intMouseY <= 569 && intMouseBut == 1){
                  RPGMethods.FleeBattle(battle, intPosX1, intPosY1);
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle = false;
                  if(chrMove == 'w' || chrMove == 'W'){
                    intPosY1 = intPosY1 + 30;
                  }else if(chrMove == 'a' || chrMove == 'A'){
                    intPosX1 = intPosX1 + 30;
                  }else if(chrMove == 's' || chrMove == 'S'){
                    intPosY1 = intPosY1 - 30;
                  }else if(chrMove == 'd' || chrMove == 'D'){
                    intPosX1 = intPosX1 - 30;
                  }
                  battle.repaint();
                }   
                if(intEn1HP <= 0){
                  intKills++;
                  intEn1HP = 50;
                  intAtk = intAtk + 2;
                  intDef = intDef + 1;
                  intTotalHP = intTotalHP + 25;
                  intHP = intTotalHP;
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle = false;
                  strMap[intPosY1/30][intPosX1/30] = "grass";
                  con.repaint();
                  battle.repaint();
                }
              }
            }else if(strMap[intPosY1/30][intPosX1/30].equals("enemy2")){
              RPGMethods.printBattle(battle, intPosX1, intPosY1);
              RPGMethods.BattleHUDEn2(strName, intHP, intTotalHP, intAtk, intDef, intEn2HP, intEn2TotalHP, intEn2Atk, intEn2Def, battle);
              blnBattle = true;
              while(blnBattle == true){
                intMouseX = battle.currentMouseX();
                intMouseY = battle.currentMouseY();
                intMouseBut = battle.currentMouseButton();
//                System.out.println(intMouseX);
//                System.out.println(intMouseY);
                
                battle.sleep(1000/60);
                
                if(intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){
                  strBattle = RPGMethods.loadBattleFrames(battle);
                  intDmgCalc = RPGMethods.DmgCalc(intAtk, intEn2Def);
                  intEn2HP = RPGMethods.BattleAnimationFightEn2(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intEn2HP, intEn2TotalHP, intEn2Atk, intEn2Def, intDmgCalc, battle);
                  
                  if(intEn2HP > 0 && intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){ //Runs the enemy animation as long as the enemy's health is above 0.
                    intEnDmgCalc = RPGMethods.EnDmgCalc(intEn1Atk, intDef); // Enemy Damage Calculator depending on Enemy's attack stat and player's defense stat
                    intHP = RPGMethods.BattleAnimationEn2(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intEn2HP, intEn2TotalHP, intEn2Atk, intEn2Def, intEnDmgCalc, battle);
                    if(intHP <= 0){
                      battle.sleep(1000);
                      battle.clear();
                      RPGMethods.GameOver2(battle);
                      battle.repaint();
                      battle.sleep(2000);
                      battle.closeConsole();
                    } 
                  }
                }else if(intMouseX >= 662 && intMouseY >= 486 && intMouseX <= 746 && intMouseY <= 524 && intMouseBut == 1){
                  intEnDmgCalc = RPGMethods.EnDmgCalc(intEn2Atk, intDef);
                  intHP = RPGMethods.GuardEn2(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intEn2HP, intEn2TotalHP, intEn2Atk, intEn2Def, intEnDmgCalc, battle);
                  battle.repaint();
                  if(intHP <= 0){
                    battle.sleep(1000);
                    battle.clear();
                    RPGMethods.GameOver2(battle);
                    battle.repaint();
                    battle.sleep(2000);
                    battle.closeConsole();
                  } 
                }else if(intMouseX >= 662 && intMouseY >= 534 && intMouseX <= 721 && intMouseY <= 569 && intMouseBut == 1){
                  RPGMethods.FleeBattle(battle, intPosX1, intPosY1);
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle = false;
                  if(chrMove == 'w' || chrMove == 'W'){
                    intPosY1 = intPosY1 + 30;
                  }else if(chrMove == 'a' || chrMove == 'A'){
                    intPosX1 = intPosX1 + 30;
                  }else if(chrMove == 's' || chrMove == 'S'){
                    intPosY1 = intPosY1 - 30;
                  }else if(chrMove == 'd' || chrMove == 'D'){
                    intPosX1 = intPosX1 - 30;
                  }
                  battle.repaint();
                }   
                if(intEn2HP <= 0){
                  intKills++;
                  intEn2HP = 85;
                  intAtk = intAtk + 2;
                  intDef = intDef + 1;
                  intTotalHP = intTotalHP + 25;
                  intHP = intTotalHP;
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle = false;
                  strMap[intPosY1/30][intPosX1/30] = "grass";
                  con.repaint();
                  battle.repaint();
                }
              }
              
            }else if(strMap[intPosY1/30][intPosX1/30].equals("midboss1")){
              RPGMethods.printBattle(battle, intPosX1, intPosY1);
              RPGMethods.BattleHUDMid1(strName, intHP, intTotalHP, intAtk, intDef, intMid1HP, intMid1TotalHP, intMid1Atk, intMid1Def, battle);
              blnBattle = true;
              while(blnBattle == true){
                intMouseX = battle.currentMouseX();
                intMouseY = battle.currentMouseY();
                intMouseBut = battle.currentMouseButton();
//                System.out.println(intMouseX);
//                System.out.println(intMouseY);
                
                battle.sleep(1000/60);
                
                if(intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){
                  strBattle = RPGMethods.loadBattleFrames(battle);
                  intDmgCalc = RPGMethods.DmgCalc(intAtk, intMid1Def);
                  intMid1HP = RPGMethods.BattleAnimationFightMid1(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intMid1HP, intMid1TotalHP, intMid1Atk, intMid1Def, intDmgCalc, battle);
                  
                  if(intMid1HP > 0 && intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){
                    intEnDmgCalc = RPGMethods.EnDmgCalc(intMid1Atk, intDef);
                    intHP = RPGMethods.BattleAnimationMid1(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intMid1HP, intMid1TotalHP, intMid1Atk, intMid1Def, intEnDmgCalc, battle);
                  }
                  if(intHP <= 0){
                    battle.sleep(1000);
                    battle.clear();
                    RPGMethods.GameOver2(battle);
                    battle.repaint();
                    battle.sleep(2000);
                    battle.closeConsole();
                  } 
                }else if(intMouseX >= 662 && intMouseY >= 486 && intMouseX <= 746 && intMouseY <= 524 && intMouseBut == 1){
                  intEnDmgCalc = RPGMethods.EnDmgCalc(intMid1Atk, intDef);
                  intHP = RPGMethods.GuardMid1(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intMid1HP, intMid1TotalHP, intMid1Atk, intMid1Def, intEnDmgCalc, battle);
                  battle.repaint();
                  if(intHP <= 0){
                    battle.sleep(1000);
                    battle.clear();
                    RPGMethods.GameOver2(battle);
                    battle.repaint();
                    battle.sleep(2000);
                    battle.closeConsole();
                  } 
                }else if(intMouseX >= 662 && intMouseY >= 534 && intMouseX <= 721 && intMouseY <= 569 && intMouseBut == 1){
                  RPGMethods.FleeBattle(battle, intPosX1, intPosY1);
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle = false;
                  if(chrMove == 'w' || chrMove == 'W'){
                    intPosY1 = intPosY1 + 30;
                  }else if(chrMove == 'a' || chrMove == 'A'){
                    intPosX1 = intPosX1 + 30;
                  }else if(chrMove == 's' || chrMove == 'S'){
                    intPosY1 = intPosY1 - 30;
                  }else if(chrMove == 'd' || chrMove == 'D'){
                    intPosX1 = intPosX1 - 30;
                  }
                  battle.repaint();
                }   
                
                if(intMid1HP <= 0){
                  intKills++;
                  intMid1HP = 200;
                  intAtk = intAtk + 5;
                  intDef = intDef + 3;
                  intTotalHP = intTotalHP+ 50;
                  intHP = intTotalHP;
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle = false;
                  strMap[intPosY1/30][intPosX1/30] = "grass";
                  con.repaint();
                  battle.repaint();
                  
                }
              }
              
            }else if(strMap[intPosY1/30][intPosX1/30].equals("midboss2")){
              RPGMethods.printBattle(battle, intPosX1, intPosY1);
              RPGMethods.BattleHUDMid2(strName, intHP, intTotalHP, intAtk, intDef, intMid2HP, intMid2TotalHP, intMid2Atk, intMid2Def, battle);
              blnBattle = true;
              while(blnBattle == true){
                intMouseX = battle.currentMouseX();
                intMouseY = battle.currentMouseY();
                intMouseBut = battle.currentMouseButton();
//                System.out.println(intMouseX);
//                System.out.println(intMouseY);
                
                battle.sleep(1000/60);
                
                if(intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){
                  strBattle = RPGMethods.loadBattleFrames(battle);
                  intDmgCalc = RPGMethods.DmgCalc(intAtk, intMid2Def);
                  intMid2HP = RPGMethods.BattleAnimationFightMid2(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intMid2HP, intMid2TotalHP, intMid2Atk, intMid2Def, intDmgCalc, battle);
                  
                  if(intMid2HP > 0 && intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){
                    intEnDmgCalc = RPGMethods.EnDmgCalc(intMid2Atk, intDef);
                    intHP = RPGMethods.BattleAnimationMid2(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intMid2HP, intMid2TotalHP, intMid2Atk, intMid2Def, intEnDmgCalc, battle);
                    if(intHP <= 0){
                      battle.sleep(1000);
                      battle.clear();
                      RPGMethods.GameOver2(battle);
                      battle.repaint();
                      battle.sleep(2000);
                      battle.closeConsole();
                    } 
                  }
                }else if(intMouseX >= 662 && intMouseY >= 486 && intMouseX <= 746 && intMouseY <= 524 && intMouseBut == 1){
                  intEnDmgCalc = RPGMethods.EnDmgCalc(intMid2Atk, intDef);
                  intHP = RPGMethods.GuardMid2(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intMid2HP, intMid2TotalHP, intMid2Atk, intMid2Def, intEnDmgCalc, battle);
                  battle.repaint();
                  if(intHP <= 0){
                    battle.sleep(1000);
                    battle.clear();
                    RPGMethods.GameOver2(battle);
                    battle.repaint();
                    battle.sleep(2000);
                    battle.closeConsole();
                  } 
                }else if(intMouseX >= 662 && intMouseY >= 534 && intMouseX <= 721 && intMouseY <= 569 && intMouseBut == 1){
                  RPGMethods.FleeBattle(battle, intPosX1, intPosY1);
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle = false;
                  if(chrMove == 'w' || chrMove == 'W'){
                    intPosY1 = intPosY1 + 30;
                  }else if(chrMove == 'a' || chrMove == 'A'){
                    intPosX1 = intPosX1 + 30;
                  }else if(chrMove == 's' || chrMove == 'S'){
                    intPosY1 = intPosY1 - 30;
                  }else if(chrMove == 'd' || chrMove == 'D'){
                    intPosX1 = intPosX1 - 30;
                  }
                  battle.repaint();
                }   
                
                if(intMid2HP <= 0){
                  intKills++;
                  intMid2HP = 250;      
                  intAtk = intAtk + 5;
                  intDef = intDef + 3;
                  intTotalHP = intTotalHP+ 50;
                  intHP = intTotalHP;
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle = false;
                  strMap[intPosY1/30][intPosX1/30] = "grass";
                  con.repaint();
                  battle.repaint();
                  
                }
              }
            }
            if(intKills == intEnemies){
              con.setDrawColor(Color.BLACK);
              con.fillRect(0,0,600, 874);
              con.println("Congratulations");
              con.sleep(2000);
              con.closeConsole();
            }
            
            if(intNumber == 1){
              con.drawImage(PlayerMBack, intPosX1, intPosY1);
            }else if(intNumber == 2){
              con.drawImage(PlayerMLeft,intPosX1, intPosY1);
            }else if(intNumber == 3){
              con.drawImage(PlayerMFront,intPosX1, intPosY1);
            }else if(intNumber == 4){
              con.drawImage(PlayerMRight, intPosX1, intPosY1);
            }
            con.repaint();
            con.sleep(4000/60);
          }
          
          
          if(intHP <= 0){
            con.sleep(1000);
            con.clear();
            RPGMethods.GameOver(con);
            con.repaint();
            con.sleep(2000);
            con.closeConsole();
          }
          
          // --------------------------------------MAP 2: CLIFFTOPS------------------------------------------------------------------
          
        }else if(intMouseX >= 312 && intMouseY >= 380 && intMouseX <= 580 && intMouseY <= 700 && intMouseBut == 1){
          
          intNumber = 3;
          
          con.clear();
          
          strMap = RPGMethods.loadRPGMap("RPGmap2.csv");
          strBattle = RPGMethods.loadBattleFrames(con);
          RPGMethods.printRPGMap2(strMap, con);
          RPGMethods.HUDDisplay(strName, intHP, intTotalHP, intAtk, intDef, con);         
          
          int intEnemies = RPGMethods.EnemyCount(strMap);
          
          while(blnGame2 == true){
            
            
            
            //-----------------------------------Gameplay Mechanics-----------------------------------------------------------------------------------------------------------
            
            
            chrMove = con.currentChar();
            
            if(chrMove == 'w' || chrMove == 'W'){
              intPosY2 = intPosY2 - 30;
              RPGMethods.printRPGMap2(strMap, con);
              RPGMethods.HUDDisplay(strName, intHP, intTotalHP,intAtk, intDef, con);         
              intNumber = 1;
            }else if(chrMove == 'a' || chrMove == 'A'){
              intPosX2 = intPosX2 - 30;
              RPGMethods.printRPGMap2(strMap, con);
              RPGMethods.HUDDisplay(strName, intHP, intTotalHP, intAtk, intDef, con);         
              intNumber = 2;
            }else if(chrMove == 's' || chrMove == 'S'){
              intPosY2 = intPosY2 + 30;
              RPGMethods.printRPGMap2(strMap, con);
              RPGMethods.HUDDisplay(strName, intHP, intTotalHP, intAtk, intDef, con);         
              intNumber = 3;
            }else if(chrMove == 'd' || chrMove == 'D'){
              intPosX2 = intPosX2 + 30;
              RPGMethods.printRPGMap2(strMap, con);
              RPGMethods.HUDDisplay(strName, intHP, intTotalHP, intAtk, intDef, con);         
              intNumber = 4;
            }
            if(intPosX2 < 0 || intPosX2 > 570 || intPosY2 > 570 || intPosY2 < 0){
              if(chrMove == 'w' || chrMove == 'W'){
                intPosY2 = intPosY2 + 30;
              }else if(chrMove == 'a' || chrMove == 'A'){
                intPosX2 = intPosX2 + 30;
              }else if(chrMove == 's' || chrMove == 'S'){
                intPosY2 = intPosY2 - 30;
              }else if(chrMove == 'd' || chrMove == 'D'){
                intPosX2 = intPosX2 - 30;
              }
              
            }else if(strMap[intPosY2/30][intPosX2/30].equals("rock")){
              if(chrMove == 'w' || chrMove == 'W'){
                intPosY2 = intPosY2 + 30;
              }else if(chrMove == 'a' || chrMove == 'A'){
                intPosX2 = intPosX2 + 30;
              }else if(chrMove == 's' || chrMove == 'S'){
                intPosY2 = intPosY2 - 30;
              }else if(chrMove == 'd' || chrMove == 'D'){
                intPosX2 = intPosX2 - 30;
              }
              
              
              
            }else if(strMap[intPosY2/30][intPosX2/30].equals("sword")){
              strMap[intPosY2/30][intPosX2/30] = "cliff";
              intAtk = intAtk + 1;
              con.repaint();
              
              
              
            }else if(strMap[intPosY2/30][intPosX2/30].equals("shield")){
              strMap[intPosY2/30][intPosX2/30] = "cliff";
              intDef = intDef + 1;
              con.repaint();
              
              
              
            }else if(strMap[intPosY2/30][intPosX2/30].equals("SHwater") || strMap[intPosY2/30][intPosX2/30].equals("Dwater")){
              intHP = 0;
              blnGame2 = false;
              
            }else if(strMap[intPosY2/30][intPosX2/30].equals("enemy1")){
              RPGMethods.printBattle2(battle, intPosX2, intPosY2);
              RPGMethods.BattleHUDEn1(strName, intHP, intTotalHP, intAtk, intDef, intEn1HP, intEn1TotalHP, intEn1Atk, intEn1Def, battle);
              blnBattle2 = true;
              while(blnBattle2 == true){
                intMouseX = battle.currentMouseX();
                intMouseY = battle.currentMouseY();
                intMouseBut = battle.currentMouseButton();;
//                System.out.println(intMouseX);
//                System.out.println(intMouseY);
                
                battle.sleep(1000/60);
                
                if(intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){ // Clicking Attack runs the attack animation
                  strBattle = RPGMethods.loadBattleFrames(battle); // Loading the animation frames into an array
                  intDmgCalc = RPGMethods.DmgCalc(intAtk, intEn1Def); // Damage calculation taking the Player's attack stat and subtracting it by the Enemy's defense stat +/- 1
                  intEn1HP = RPGMethods.BattleAnimation2FightEn1(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intEn1HP, intEn1TotalHP, intEn1Atk, intEn1Def, intDmgCalc, battle); // Runs the battle animation for the player
                  
                  if(intEn1HP > 0 && intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){ //Runs the enemy animation as long as the enemy's health is above 0.
                    intEnDmgCalc = RPGMethods.EnDmgCalc(intEn1Atk, intDef); // Enemy Damage Calculator depending on Enemy's attack stat and player's defense stat
                    intHP = RPGMethods.BattleAnimation2En1(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intEn1HP, intEn1TotalHP, intEn1Atk, intEn1Def, intEnDmgCalc, battle);
                    if(intHP <= 0){
                      battle.sleep(1000);
                      battle.clear();
                      RPGMethods.GameOver2(battle);
                      battle.repaint();
                      battle.sleep(2000);
                      battle.closeConsole();
                    }  
                  }
                }else if(intMouseX >= 662 && intMouseY >= 486 && intMouseX <= 746 && intMouseY <= 524 && intMouseBut == 1){ // Clicking Guard to reduce oncoming damage
                  intEnDmgCalc = RPGMethods.EnDmgCalc(intEn1Atk, intDef); // Damage Calculation for the enemy
                  intHP = RPGMethods.Guard2En1(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intEn1HP, intEn1TotalHP, intEn1Atk, intEn1Def, intEnDmgCalc, battle); // Changes Enemy's attack to half of the original
                  if(intHP <= 0){
                    battle.sleep(1000);
                    battle.clear();
                    RPGMethods.GameOver2(battle);
                    battle.repaint();
                    battle.sleep(2000);
                    battle.closeConsole();
                  } 
                }else if(intMouseX >= 662 && intMouseY >= 534 && intMouseX <= 721 && intMouseY <= 569 && intMouseBut == 1){
                  RPGMethods.FleeBattle2(battle, intPosX2, intPosY2);
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle2 = false;
                  if(chrMove == 'w' || chrMove == 'W'){
                    intPosY2 = intPosY2 + 30;
                  }else if(chrMove == 'a' || chrMove == 'A'){
                    intPosX2 = intPosX2 + 30;
                  }else if(chrMove == 's' || chrMove == 'S'){
                    intPosY2 = intPosY2 - 30;
                  }else if(chrMove == 'd' || chrMove == 'D'){
                    intPosX2 = intPosX2 - 30;
                  }
                  battle.repaint();
                }    
                if(intEn1HP <= 0){
                  intKills++;
                  intEn1HP = 50;
                  intAtk = intAtk + 2;
                  intDef = intDef + 1;
                  intTotalHP = intTotalHP + 25;
                  intHP = intTotalHP;
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle2 = false;
                  strMap[intPosY2/30][intPosX2/30] = "cliff";
                  con.repaint();
                  battle.repaint();
                }
              }
            }else if(strMap[intPosY2/30][intPosX2/30].equals("enemy2")){
              RPGMethods.printBattle2(battle, intPosX2, intPosY2);
              RPGMethods.BattleHUDEn2(strName, intHP, intTotalHP, intAtk, intDef, intEn2HP, intEn2TotalHP, intEn2Atk, intEn2Def, battle);
              blnBattle2 = true;
              while(blnBattle2 == true){
                intMouseX = battle.currentMouseX();
                intMouseY = battle.currentMouseY();
                intMouseBut = battle.currentMouseButton();
//                System.out.println(intMouseX);
//                System.out.println(intMouseY);
                
                battle.sleep(1000/60);
                
                if(intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){
                  strBattle = RPGMethods.loadBattleFrames(battle);
                  intDmgCalc = RPGMethods.DmgCalc(intAtk, intEn2Def);
                  intEn2HP = RPGMethods.BattleAnimation2FightEn2(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intEn2HP, intEn2TotalHP, intEn2Atk, intEn2Def, intDmgCalc, battle);
                  
                  if(intEn2HP > 0 && intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){ //Runs the enemy animation as long as the enemy's health is above 0.
                    intEnDmgCalc = RPGMethods.EnDmgCalc(intEn1Atk, intDef); // Enemy Damage Calculator depending on Enemy's attack stat and player's defense stat
                    intHP = RPGMethods.BattleAnimation2En2(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intEn2HP, intEn2TotalHP, intEn2Atk, intEn2Def, intEnDmgCalc, battle);
                    if(intHP <= 0){
                      battle.sleep(1000);
                      battle.clear();
                      RPGMethods.GameOver2(battle);
                      battle.repaint();
                      battle.sleep(2000);
                      battle.closeConsole();
                    } 
                  }
                }else if(intMouseX >= 662 && intMouseY >= 486 && intMouseX <= 746 && intMouseY <= 524 && intMouseBut == 1){
                  intEnDmgCalc = RPGMethods.EnDmgCalc(intEn2Atk, intDef);
                  intHP = RPGMethods.Guard2En2(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intEn2HP, intEn2TotalHP, intEn2Atk, intEn2Def, intEnDmgCalc, battle);
                  battle.repaint();
                  if(intHP <= 0){
                    battle.sleep(1000);
                    battle.clear();
                    RPGMethods.GameOver2(battle);
                    battle.repaint();
                    battle.sleep(2000);
                    battle.closeConsole();
                  } 
                }else if(intMouseX >= 662 && intMouseY >= 534 && intMouseX <= 721 && intMouseY <= 569 && intMouseBut == 1){
                  RPGMethods.FleeBattle2(battle, intPosX2, intPosY2);
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle2 = false;
                  if(chrMove == 'w' || chrMove == 'W'){
                    intPosY2 = intPosY2 + 30;
                  }else if(chrMove == 'a' || chrMove == 'A'){
                    intPosX2 = intPosX2 + 30;
                  }else if(chrMove == 's' || chrMove == 'S'){
                    intPosY2 = intPosY2 - 30;
                  }else if(chrMove == 'd' || chrMove == 'D'){
                    intPosX2 = intPosX2 - 30;
                  }
                  battle.repaint();
                }    
                if(intEn2HP <= 0){
                  intKills++;
                  intEn2HP = 85;
                  intAtk = intAtk + 2;
                  intDef = intDef + 1;
                  intTotalHP = intTotalHP + 25;
                  intHP = intTotalHP;
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle2 = false;
                  strMap[intPosY2/30][intPosX2/30] = "cliff";
                  con.repaint();
                  battle.repaint();
                }
              }
              
            }else if(strMap[intPosY2/30][intPosX2/30].equals("midboss1")){
              RPGMethods.printBattle2(battle, intPosX2, intPosY2);
              RPGMethods.BattleHUDMid1(strName, intHP, intTotalHP, intAtk, intDef, intMid1HP, intMid1TotalHP, intMid1Atk, intMid1Def, battle);
              blnBattle2 = true;
              while(blnBattle2 == true){
                intMouseX = battle.currentMouseX();
                intMouseY = battle.currentMouseY();
                intMouseBut = battle.currentMouseButton();
//                System.out.println(intMouseX);
//                System.out.println(intMouseY);
                
                battle.sleep(1000/60);
                
                if(intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){
                  strBattle = RPGMethods.loadBattleFrames(battle);
                  intDmgCalc = RPGMethods.DmgCalc(intAtk, intMid1Def);
                  intMid1HP = RPGMethods.BattleAnimation2FightMid1(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intMid1HP, intMid1TotalHP, intMid1Atk, intMid1Def, intDmgCalc, battle);
                  
                  if(intMid1HP > 0 && intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){
                    intEnDmgCalc = RPGMethods.EnDmgCalc(intMid1Atk, intDef);
                    intHP = RPGMethods.BattleAnimation2Mid1(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intMid1HP, intMid1TotalHP, intMid1Atk, intMid1Def, intEnDmgCalc, battle);
                  }
                  if(intHP <= 0){
                    battle.sleep(1000);
                    battle.clear();
                    RPGMethods.GameOver2(battle);
                    battle.repaint();
                    battle.sleep(2000);
                    battle.closeConsole();
                  } 
                }else if(intMouseX >= 662 && intMouseY >= 486 && intMouseX <= 746 && intMouseY <= 524 && intMouseBut == 1){
                  intEnDmgCalc = RPGMethods.EnDmgCalc(intMid1Atk, intDef);
                  intHP = RPGMethods.Guard2Mid1(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intMid1HP, intMid1TotalHP, intMid1Atk, intMid1Def, intEnDmgCalc, battle);
                  battle.repaint();
                  if(intHP <= 0){
                    battle.sleep(1000);
                    battle.clear();
                    RPGMethods.GameOver2(battle);
                    battle.repaint();
                    battle.sleep(2000);
                    battle.closeConsole();
                  } 
                }else if(intMouseX >= 662 && intMouseY >= 534 && intMouseX <= 721 && intMouseY <= 569 && intMouseBut == 1){
                  RPGMethods.FleeBattle2(battle, intPosX2, intPosY2);
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle2 = false;
                  if(chrMove == 'w' || chrMove == 'W'){
                    intPosY2 = intPosY2 + 30;
                  }else if(chrMove == 'a' || chrMove == 'A'){
                    intPosX2 = intPosX2 + 30;
                  }else if(chrMove == 's' || chrMove == 'S'){
                    intPosY2 = intPosY2 - 30;
                  }else if(chrMove == 'd' || chrMove == 'D'){
                    intPosX2 = intPosX2 - 30;
                  }
                  battle.repaint();
                }    
                
                if(intMid1HP <= 0){
                  intKills++;
                  intMid1HP = 200;
                  intAtk = intAtk + 5;
                  intDef = intDef + 3;
                  intTotalHP = intTotalHP+ 50;
                  intHP = intTotalHP;
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle2 = false;
                  strMap[intPosY2/30][intPosX2/30] = "cliff";
                  con.repaint();
                  battle.repaint();
                  
                }
              }
              
            }else if(strMap[intPosY2/30][intPosX2/30].equals("midboss2")){
              RPGMethods.printBattle2(battle, intPosX2, intPosY2);
              RPGMethods.BattleHUDMid2(strName, intHP, intTotalHP, intAtk, intDef, intMid2HP, intMid2TotalHP, intMid2Atk, intMid2Def, battle);
              blnBattle2 = true;
              while(blnBattle2 == true){
                intMouseX = battle.currentMouseX();
                intMouseY = battle.currentMouseY();
                intMouseBut = battle.currentMouseButton();
//                System.out.println(intMouseX);
//                System.out.println(intMouseY);
                
                battle.sleep(1000/60);
                
                if(intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){
                  strBattle = RPGMethods.loadBattleFrames(battle);
                  intDmgCalc = RPGMethods.DmgCalc(intAtk, intMid2Def);
                  intMid2HP = RPGMethods.BattleAnimation2FightMid2(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intMid2HP, intMid2TotalHP, intMid2Atk, intMid2Def, intDmgCalc, battle);
                  
                  if(intMid2HP > 0 && intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){
                    intEnDmgCalc = RPGMethods.EnDmgCalc(intMid2Atk, intDef);
                    intHP = RPGMethods.BattleAnimation2Mid2(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intMid2HP, intMid2TotalHP, intMid2Atk, intMid2Def, intEnDmgCalc, battle);
                    if(intHP <= 0){
                      battle.sleep(1000);
                      battle.clear();
                      RPGMethods.GameOver2(battle);
                      battle.repaint();
                      battle.sleep(2000);
                      battle.closeConsole();
                    } 
                  }
                }else if(intMouseX >= 662 && intMouseY >= 486 && intMouseX <= 746 && intMouseY <= 524 && intMouseBut == 1){
                  intEnDmgCalc = RPGMethods.EnDmgCalc(intMid2Atk, intDef);
                  intHP = RPGMethods.Guard2Mid2(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intMid2HP, intMid2TotalHP, intMid2Atk, intMid2Def, intEnDmgCalc, battle);
                  battle.repaint();
                  if(intHP <= 0){
                    battle.sleep(1000);
                    battle.clear();
                    RPGMethods.GameOver2(battle);
                    battle.repaint();
                    battle.sleep(2000);
                    battle.closeConsole();
                  } 
                }else if(intMouseX >= 662 && intMouseY >= 534 && intMouseX <= 721 && intMouseY <= 569 && intMouseBut == 1){
                  RPGMethods.FleeBattle2(battle, intPosX2, intPosY2);
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle2 = false;
                  if(chrMove == 'w' || chrMove == 'W'){
                    intPosY2 = intPosY2 + 30;
                  }else if(chrMove == 'a' || chrMove == 'A'){
                    intPosX2 = intPosX2 + 30;
                  }else if(chrMove == 's' || chrMove == 'S'){
                    intPosY2 = intPosY2 - 30;
                  }else if(chrMove == 'd' || chrMove == 'D'){
                    intPosX2 = intPosX2 - 30;
                  }
                  battle.repaint();
                }    
                
                if(intMid2HP <= 0){
                  intKills++;
                  intMid2HP = 250;      
                  intAtk = intAtk + 5;
                  intDef = intDef + 3;
                  intTotalHP = intTotalHP+ 50;
                  intHP = intTotalHP;
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle2 = false;
                  strMap[intPosY2/30][intPosX2/30] = "cliff";
                  con.repaint();
                  battle.repaint();
                  
                }
              }
            }else if(strMap[intPosY2/30][intPosX2/30].equals("boss")){
              RPGMethods.printBattle2(battle, intPosX2, intPosY2);
              RPGMethods.BattleHUDBoss(strName, intHP, intTotalHP, intAtk, intDef, intBossHP, intBossTotalHP, intBossAtk, intBossDef, battle);
              blnBattle2 = true;
              while(blnBattle2 == true){
                intMouseX = battle.currentMouseX();
                intMouseY = battle.currentMouseY();
                intMouseBut = battle.currentMouseButton();
//                System.out.println(intMouseX);
//                System.out.println(intMouseY);
                
                battle.sleep(1000/60);
                
                if(intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){
                  strBattle = RPGMethods.loadBattleFrames(battle);
                  intDmgCalc = RPGMethods.DmgCalc(intAtk, intBossDef);
                  intBossHP = RPGMethods.BattleAnimationFightBoss(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intBossHP, intBossTotalHP, intBossAtk, intBossDef, intDmgCalc, battle);
                  
                  if(intBossHP > 0 && intMouseX >= 665 && intMouseY >= 441 && intMouseX <= 754 && intMouseY <= 472 && intMouseBut == 1){
                    intEnDmgCalc = RPGMethods.EnDmgCalc(intBossAtk, intDef);
                    intHP = RPGMethods.BattleAnimationBoss(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intBossHP, intBossTotalHP, intBossAtk, intBossDef, intEnDmgCalc, battle);
                    
                    if(intHP <= 0){
                      battle.sleep(1000);
                      battle.clear();
                      RPGMethods.GameOver2(battle);
                      battle.repaint();
                      battle.sleep(2000);
                      battle.closeConsole();
                    } 
                  }
                }else if(intMouseX >= 662 && intMouseY >= 486 && intMouseX <= 746 && intMouseY <= 524 && intMouseBut == 1){
                  intEnDmgCalc = RPGMethods.EnDmgCalc(intBossAtk, intDef);
                  intHP = RPGMethods.GuardBoss(strBattle, strName, intHP, intTotalHP, intAtk, intDef, intBossHP, intBossTotalHP, intBossAtk, intBossDef, intEnDmgCalc, battle);
                  battle.repaint();
                  if(intHP <= 0){
                    battle.sleep(1000);
                    battle.clear();
                    RPGMethods.GameOver2(battle);
                    battle.repaint();
                    battle.sleep(2000);
                    battle.closeConsole();
                  } 
                }else if(intMouseX >= 662 && intMouseY >= 534 && intMouseX <= 721 && intMouseY <= 569 && intMouseBut == 1){
                  RPGMethods.FleeBattle2(battle, intPosX2, intPosY2);
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle2 = false;
                  if(chrMove == 'w' || chrMove == 'W'){
                    intPosY2 = intPosY2 + 30;
                  }else if(chrMove == 'a' || chrMove == 'A'){
                    intPosX2 = intPosX2 + 30;
                  }else if(chrMove == 's' || chrMove == 'S'){
                    intPosY2 = intPosY2 - 30;
                  }else if(chrMove == 'd' || chrMove == 'D'){
                    intPosX2 = intPosX2 - 30;
                  }
                  battle.repaint();
                }    
                
                if(intBossHP <= 0){
                  battle.setDrawColor(Color.BLACK);
                  battle.fillRect(0, 0, 800, 600);
                  blnBattle = false;
                  con.fillRect(0, 0, 600, 600);
                  con.repaint();
                  battle.repaint();
                   con.setDrawColor(Color.BLACK);
              con.fillRect(0,0,600, 874);
              con.println("Congratulations");
              con.sleep(2000);
              con.closeConsole(); 
                  
                }
              }
                             }
                   
            
            if(intNumber == 1){
              con.drawImage(PlayerMBack, intPosX2, intPosY2);
            }else if(intNumber == 2){
              con.drawImage(PlayerMLeft,intPosX2, intPosY2);
            }else if(intNumber == 3){
              con.drawImage(PlayerMFront,intPosX2, intPosY2);
            }else if(intNumber == 4){
              con.drawImage(PlayerMRight, intPosX2, intPosY2);
            }
            con.repaint();
            con.sleep(4000/60);
          }
          
          
          if(intHP <= 0){
            con.sleep(1000);
            con.clear();
            RPGMethods.GameOver(con);
            con.repaint();
            con.sleep(2000);
            con.closeConsole();
          }
          
        }
        
      }else if(intMouseX >= 171 && intMouseX <= 462 && intMouseY >= 502 && intMouseY <= 605 && intMouseBut == 1){
        con.clear();
        RPGMethods.Instructions(con);
        while(blnInstructions = true){
          intMouseX = con.currentMouseX();
          intMouseY = con.currentMouseY();
//          System.out.println(intMouseX);
//          System.out.println(intMouseY);
          
          battle.sleep(1000/60);
          
          intMouseBut = con.currentMouseButton();
          if(intMouseX >= 5 && intMouseX <= 73 && intMouseY >= 5 && intMouseY <= 70 && intMouseBut == 1){
            break;
          }
        }
      }else if(intMouseX >= 252 && intMouseX <= 378 && intMouseY >= 642 && intMouseY <= 742 && intMouseBut == 1){
        con.closeConsole();
      }
    }
  }
}