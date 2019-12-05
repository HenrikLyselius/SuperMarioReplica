package graphics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import computation.Computation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import levels.Level1;
import screenObjects.*;
import sound.Sound;

public class GameView extends Application{
	
	private List<StationaryObject> allStationaryObjects; 
	private List<StationaryObject> StationaryObjectList;
	private List<Monster> monsterList;
	private List<ScreenObject> animationList;
	private List<Mushroom> MushroomList;
	private List<FireFlower> FireFlowerList;
	private List<FireBall> FireBallList;
	private List<Star> starList;
	private List<ScreenObject> backgroundList;
	private List<Monster> helpList;
	
	private Player player;
	private Sound sounds;
	private boolean stop = false;
	private Level1 level1;
	private Computation computation;
	private int scrollning = 0;
	
	private Group group = new Group();
	private Scene scene;
	
	
	public GameView() {
		
		player = new Player();  
		sounds = new Sound();
		level1 = new Level1();
		computation = new Computation();
		allStationaryObjects = new ArrayList<StationaryObject>();
		StationaryObjectList = new ArrayList<StationaryObject>();
		monsterList = new ArrayList<Monster>();
		animationList = new ArrayList<ScreenObject>();
		MushroomList = new ArrayList<Mushroom>();
		FireFlowerList = new ArrayList<FireFlower>();
		FireBallList = new ArrayList<FireBall>();
		starList = new ArrayList<Star>();
		helpList = new ArrayList<Monster>();
		
		Color background = Color.rgb(135,206,250);
		scene = new Scene(group, 1200, 600, background);
		createLevel();
	}
	
	
	public void startApplication () {
    	Application.launch();
    }
	
	
	
	
	@Override
	public void start(Stage stage) throws Exception {
	    
		scene.setOnKeyPressed(new KeyPress());
		scene.setOnKeyReleased(new KeyRelease());
		scene.setOnMousePressed(new MouseClick());
		
		stage.setScene(scene);
		stage.show();
		sounds.theme();
	    
		
		AnimationTimer timer = new AnimationTimer() {
			   	
			private long latestUpdate = 0 ;
	            
				@Override
	            public void handle(long now) {
					
					if (now - latestUpdate >= 28_000_000) {
						if(getStop()) {
							this.stop();
						}
	                    
						// Everything that is done before each update of the screen.
						
						readInActualLevel();
						checkIfPlayerJumps();
						checkIfPlayerShallMoveXWise();
						checkIfPlayerTakesAMushroom();
	                    checkIfPlayerTakesAFireFlower();
	                    checkIfPlayerFallsInAHole();
	                    checkIfPlayerLandsOnAMonster();
	                    checkIfPlayerTakesAStar();
	                    
	                    player.savexWiseChange();
	                    
	                    monsterMovement();
	                    starMovement();
	                    mushroomMovement();
	                    fireBallMovement();
	                    specialMovement();
	                    
	                    checkIfPlayerHitsMonster();
	                    checkIfAShellHitsAMonster();
	                    checkIfAFireBallHitsAMonster();
	                    checkIfThereIsAnAnimation();
	                    changeInvincible();
	                    handleFlashingPlayer();
	                    handleQuestionMarkFlashing();
	                    checkIfLevelIsFinished();
	                        
	                    latestUpdate = now ;
					}
	            }
		   };
		   timer.start();
		}
	       
	   
	  
	
	
	public void readInActualLevel() {
		
		for(ScreenObject so: StationaryObjectList) {
			group.getChildren().remove(so.getImageView());
		}
		// Resets the changes from the latest update. 
		scrollScreen(-scrollning);
		
		// Reads in the actual list for this update.
		StationaryObjectList = new ArrayList<StationaryObject> (computeActualList());
		
		// Computes how much the actual list has to scroll.
		scrollning = -(player.getXAbsolutValue() - player.getXValue());
		
		scrollScreen(scrollning);
	
		for(ScreenObject so: StationaryObjectList) {
			group.getChildren().add(so.getImageView());
		}  

	}
	   
	 
	
	public List<StationaryObject> computeActualList() {
		
		List<StationaryObject> aktuellList = new ArrayList<StationaryObject>();
		
		int index = computation.findIndex(player.getXAbsolutValue(), allStationaryObjects);
		
		// If one wants to be able to see the surroundings generated on the fly, one can 
		// set "distance" below, to 20 or lower.

		int distance = 75;
		aktuellList = allStationaryObjects.subList(Math.max(0, index - distance), Math.min(allStationaryObjects.size() - 1, index + distance));
		
		computation.setStationaryObjectList(aktuellList);
		return aktuellList;
	}
	


	
	public void createLevel() {
		
		allStationaryObjects = level1.getLevel();   
		monsterList = level1.getMonsterList();
		backgroundList = level1.getBackgroundList();
		
		group.getChildren().add(player.getImageView());

		for(ScreenObject so: monsterList) {
			   group.getChildren().add(so.getImageView());
		}
		
		for(ScreenObject so: backgroundList) {
			   group.getChildren().add(so.getImageView());
		}
	}
	
	
	
	public boolean getStop() {
		return this.stop;
	}
	   
	
	
	
	public void checkIfPlayerJumps() {
		
		if(player.getPlayerJumps()) {
			player.changeJumpTime();;
			player.jump(- freeSpaceAbove(player));
			
		}
		else {
			int change = computation.freeSpaceBelow(player, StationaryObjectList);
			int change2 = computation.freeSpaceBelow(player, monsterList);
			player.fall(Math.min(change, change2));
		}
	}
	
	
	
	
	public void checkIfPlayerShallMoveXWise(){
		
		group.getChildren().remove(player.getImageView());
		
		// The player moves to the right on the screen.
		if(player.getGoRight() && player.getXValue() < 600) {
				player.setXValue(computation.sizeOfMovementRightwards(player));
		}
		
		// The screen scrolls to the left.
		if(player.getGoRight() && player.getXValue() >= 600) {
				player.setXAbsolutValue(computation.sizeOfMovementRightwards(player));
				player.changeImage();
		}
		
		// The player moves to the left on the screen.
		if(player.getGoLeft() && player.getXValue() > 2 && player.getXAbsolutValue() < 600) {
				player.setXValue(-computation.sizeOfMovementLeftwards(player));
		}
		
		// The screen scrolls to the right. 
		if(player.getGoLeft() && player.getXAbsolutValue() >= 600) {
				player.setXAbsolutValue(- computation.sizeOfMovementLeftwards(player));
				player.changeImage();
		}
		
		group.getChildren().add(player.getImageView());
	}
	   
	
	
	
	public void checkIfPlayerTakesAMushroom() {
		   
		Iterator<Mushroom> it = MushroomList.iterator();
		while(it.hasNext()) {
			Mushroom Mushroom = it.next();
			if(computation.objectsWouldCollideInXDirection(player, Mushroom) &&
			   computation.objectsWouldCollideInYDirection(player, Mushroom)) {
				   
					group.getChildren().remove(Mushroom.getImageView());
					it.remove();
					player.setBig();
					sounds.getBig();
			}
		}
	}

	   
	
	
	
	public void checkIfPlayerTakesAFireFlower(){
		
		Iterator<FireFlower> it = FireFlowerList.iterator();
		while(it.hasNext()) {
			FireFlower FireFlower = it.next();
			if(computation.objectsWouldCollideInXDirection(player, FireFlower) &&
			   computation.objectsWouldCollideInYDirection(player, FireFlower)) {
				   		
					group.getChildren().remove(FireFlower.getImageView());
				   	it.remove();
				   	player.setFireBall(true);
				   	sounds.getBig();
			}
		}
	}
	

	

    
	public void checkIfPlayerFallsInAHole() {
		 
		if(player.getYValue() + player.getHeight() > 599) {
			 gameOver();
		}
	}
	
	
	  
	
	
	public void checkIfPlayerLandsOnAMonster() {
		   
		Iterator<Monster> it = monsterList.iterator();
		while(it.hasNext()) {
			Monster monster = it.next();
			if(monster.getYValue() - player.getYValue() == player.getHeight() &&	
			   Math.abs(monster.getXValue() - player.getXValue()) <= 40) {
				
				// The player bounces if it lands on a monster.
				player.setPlayerJumps(true);
 				player.setJumpTime(3);
 				sounds.click();
				
 				// If the monster is a Koopa, it turns into a shell.
 				if(monster.getClass().getName().equals("screenObjects.MonsterKoopa")) {
	   				group.getChildren().remove(monster.getImageView());
	   				it.remove();
	   				KoopaShell ks = new KoopaShell(monster.getXValue(), monster.getYValue());
	   				helpList.add(ks);
	   				group.getChildren().add(ks.getImageView());
				}
				
 				// If the monster is a moving shell, it stops. 
				else if(monster.getClass().getName().equals("screenObjects.KoopaShell") && monster.getSpeed() != 0){
	   				monster.setSpeed2(0);
				}
				
 				// Otherwise the monster is removed.
				else {
					group.getChildren().remove(monster.getImageView());
	   				startRemoveMonsterAnimation(monster);
	   				it.remove();
				}
			}
		}
		
		AddList(monsterList);
	}
	   
	   
	   
	
	public void AddList(List<Monster> List) {
		  
		for(Monster monster: helpList) {
			List.add(monster);
		}
		helpList.clear();
	}
	   
	   
	public void removeFromList(List<Monster> List) {
		   
		for(ScreenObject so: helpList) {
			List.remove(so);
		}
		helpList.clear();
	}
	
	
	
	

	
	public void checkIfPlayerTakesAStar() {
		   
		Iterator<Star> it = starList.iterator();
		while(it.hasNext()) {
			ScreenObject star = it.next();
			if(computation.objectsWouldCollideInXDirection(player, star) &&
			   computation.objectsWouldCollideInYDirection(player, star)) {
				   		
					group.getChildren().remove(star.getImageView());
				   	it.remove();
				   	player.setFlashing(true);
				   	player.setFlashingTime(400);
				   	player.setInvincible(true);
				   	player.setInvincibleTime(400);
				   	sounds.star();
			}
		}
	} 
	   
	   
	
	
	public void monsterMovement() {
		  
		for(Monster monster: monsterList) {
			
			// Checks if the monster is close to the part of the level that is shown on the 
			// screen, and if it is, movement is initiated.
			if(Math.abs(monster.getXValue() - player.getXValue()) < 1200) {
			   
				group.getChildren().remove(monster.getImageView());
		   
				//Checks if the monster is standing on something or falling.
				monster.fall(computation.freeSpaceBelow(monster, StationaryObjectList));
			  
				if(monster.getMonsterType() == 1) {
				   monsterMovementType1(monster);
				}
			   
				if(monster.getMonsterType() == 2) {
				   monsterMovementType2(monster);
				}
			   
				if(monster.getMonsterType() == 3) {
				   monsterMovementType3(monster);
				}
			   
				monster.chooseImage();
				group.getChildren().add(monster.getImageView());
		   }
		}
	}
	  
	
	
	public void monsterMovementType1(Monster monster) {  // Monster type that follows the player.
		   
		if(computation.Objec1IsLeftOfObject2(monster, player)) {
			monster.setDirectionLeft(false);
			int freeSpace = computation.freeSpaceOnRightSide(monster, StationaryObjectList);
			int freeSpace2 = computation.freeSpaceOnRightSide(monster, monsterList);
			freeSpace = Math.min(freeSpace, freeSpace2);
			monster.monsterMovement(freeSpace);
		}
		else {
			monster.setDirectionLeft(true);
			int freeSpace = computation.freeSpaceOnLeftSide(monster, StationaryObjectList);
			int freeSpace2 = computation.freeSpaceOnLeftSide(monster, monsterList);
			freeSpace = Math.min(freeSpace, freeSpace2);
			monster.monsterMovement(-freeSpace);
		}
	}
	  
	  
	  
	public void monsterMovementType2(Monster monster) { // A Koopa shell. 
		  
		if(monster.directionLeft()) {
			int freeSpace = computation.freeSpaceOnLeftSide(monster, StationaryObjectList);
			if(freeSpace == 0) {
				sounds.bump();
			}
			int freeSpace2 = computation.freeSpaceOnLeftSide(monster, monsterList);
			freeSpace = Math.min(freeSpace, freeSpace2);
			monster.monsterMovement(-freeSpace);
		}
		else {
			int freeSpace = computation.freeSpaceOnRightSide(monster, StationaryObjectList);
			if(freeSpace == 0) {
				sounds.bump();
			}
			int freeSpace2 = computation.freeSpaceOnRightSide(monster, monsterList);
			freeSpace = Math.min(freeSpace, freeSpace2);
			monster.monsterMovement(freeSpace); 
		}
	} 
	  
	
	
	  // Monster type that stays on a platform.
	
	public void monsterMovementType3(Monster monster) {
		
		if(monster.directionLeft()) {
			int freeSpace = computation.freeSpaceOnLeftSide(monster, StationaryObjectList);
			int freeSpace2 = computation.freeSpaceOnLeftSide(monster, monsterList);
			freeSpace = Math.min(freeSpace, freeSpace2);
			  
			// Checks if there is anything to stand on in the direction the monster is moving.
			if(computation.isThereABlockUnderThisPoint(monster.getXValue() - monster.getSpeed(), 
			monster.getYValue() + monster.getHeight())) {
				  	
				monster.monsterMovement(-freeSpace);
			}
			else {
				monster.changeDirection();
			}
		}
		  
		else {
			 int freeSpace = computation.freeSpaceOnRightSide(monster, StationaryObjectList);
			 int freeSpace2 = computation.freeSpaceOnRightSide(monster, monsterList);
			 freeSpace = Math.min(freeSpace, freeSpace2);
			  
			 // Checks if there is anything to stand on in the direction the monster is moving.
			 if(computation.isThereABlockUnderThisPoint(monster.getXValue() + monster.getSpeed() + monster.getWidth(), 
			 monster.getYValue() + monster.getHeight())) {

				 monster.monsterMovement(freeSpace); 
			 }
			 else {
				 monster.changeDirection();
			 }
		}
			  
	}
		  
	
	
	
	   
	   
	public void starMovement() {
		
		for(ScreenObject so: starList) {
			Star star = (Star) so;
			group.getChildren().remove(star.getImageView());
			checkStarMovementYWise(star);
			   
			if(star.directionLeft()) {
				star.movement(- computation.freeSpaceOnLeftSide(star, StationaryObjectList));
			}
			else {
				star.movement(computation.freeSpaceOnRightSide(star, StationaryObjectList));
			}
			   
			group.getChildren().add(star.getImageView());
		}
	}
		   

	
	public void checkStarMovementYWise(Star star) {
		   
		if(star.jumps()) {
			star.jump(- freeSpaceAbove(star));	
		}
		else {
			star.fall(computation.freeSpaceBelow(star, StationaryObjectList));
		}
	}
	   
	
	
	
	
	
	
	public void mushroomMovement() {
		   
		for(ScreenObject so: MushroomList) {
			Mushroom Mushroom = (Mushroom) so;
			group.getChildren().remove(Mushroom.getImageView());
			   
			// Movement yWise.
			if(!computation.standsOnSomething(Mushroom)) {
				Mushroom.setYValue(5);
			}
			   
			// Movement xWise.
			if(so.directionLeft()) {
				Mushroom.movement(-computation.freeSpaceOnLeftSide(Mushroom, StationaryObjectList));
			}
			else {
				Mushroom.movement(computation.freeSpaceOnRightSide(Mushroom, StationaryObjectList));
				 
			}
				   
			group.getChildren().add(Mushroom.getImageView());
		}
	}
	

	
	
	
	
	public void fireBallMovement() {
		
		int change;
		Iterator<FireBall> it = FireBallList.iterator();
		while(it.hasNext()) {		
			FireBall FireBall = (FireBall) it.next();
			
			// Checks that the movement isn't bigger than the free space to the left.
			if(FireBall.directionLeft()) {
				change = computation.sizeOfMovementLeftwards(FireBall);
				FireBall.setXValue(- change);
			}
			
			// Checks that the movement isn't bigger than the free space to the right.
			else {
				change = computation.sizeOfMovementRightwards(FireBall);
				FireBall.setXValue(change);
			}
			 
			// Removes the fire ball if it hits a wall. 
			if(change == 0) {
				group.getChildren().remove(FireBall.getImageView());
				it.remove();   
			}
			
			
			// Removes the fire ball if it is outside the screen. 
			if(FireBall.getXValue() < 0 || FireBall.getXValue() > 1200) {
				   group.getChildren().remove(FireBall.getImageView());
				   it.remove();
			}
		}
	}
	
	
	
	
	public void specialMovement() {

		specialMovementhelp(MushroomList);
		specialMovementhelp(FireFlowerList);
		specialMovementhelp(StationaryObjectList);
	}
	   
	   
	
	public void specialMovementhelp(List<? extends ScreenObject> List) {
	
		for(ScreenObject so: List) {
			if(so.getSpecialMovement()) {
				so.specialMovement();
			}
		}
	}
	
	
	
	
	public void jumpUpToBlock(Block b) {
		
		if(b.canBeRemoved() == false) {
			sounds.bump();
		}
		
		checkIfBlockContainsItem(b);
		   
		
		// Checks if the block shall be removed. 
		if(b.canBeRemoved() && player.getBig()) {
			startCrushedBlockAnimation(b);
			allStationaryObjects.remove(b);
   			group.getChildren().remove(b.getImageView());
		}	   
			  
		// Checks if the block shall bounce upwards. 
		if(b.canBeRemoved() && player.getBig() == false) {
			b.setSpecialMovement(true);
			b.setSpecialMovementTime(14);
		}
		   
		// Checks if there is a monster above the block. 
		Iterator<Monster> it = monsterList.iterator();
		while(it.hasNext()) {
			Monster monster = it.next();
			if(computation.object1IsAboveObject2(monster, b) &&
			   computation.distanceBetweenObjectsYWise(monster, b) == 0 &&
			   computation.objectsWouldCollideInYDirection(monster, b) &&
			   b.canBeRemoved()) {
				
					group.getChildren().remove(monster.getImageView());
					startRemoveMonsterAnimation((Monster)monster);
					it.remove();
					sounds.click();
			}
		}
		   
	
		// Checks if there is a mushroom on top of the block that shall bounce upwards. 
		for(ScreenObject Mushroom: MushroomList) {
			if(computation.object1IsAboveObject2(Mushroom, b) &&
			   computation.distanceBetweenObjectsYWise(Mushroom, b) == 0 &&
			   computation.objectsWouldCollideInYDirection(Mushroom, b) &&
			   b.canBeRemoved()) {
				
					Mushroom.setYValue(-60);
			}
		}
	}
	   

	   
	   

	   
	
	public void checkIfPlayerHitsMonster() {
		Iterator<Monster> it = monsterList.iterator();
		while(it.hasNext()) {
			Monster monster = it.next();
			
			if(Math.abs(player.getXValue() - monster.getXValue()) < 50 &&
			   computation.objectsWouldCollideInXDirection(player, monster)){
				
				// If the player has taken a star and is invincible, then the monster is removed.
					if(player.isFlashing()) {
						group.getChildren().remove(monster.getImageView());
						startRemoveMonsterAnimation(monster);
						it.remove();
						sounds.click();
					}
				
					// If it is a non moving shell, it is pushed away.
					else if(monster.getClass().getName().equals("screenObjects.KoopaShell") && monster.getSpeed() == 0) {
						monster.setSpeed2(21);
						monster.setDirectionLeft(player.directionLeft());
						sounds.click();
					}
				
					// If the player is big, it turns small. 
					else if(player.getBig() && player.isInvincible() == false) {
						player.setSmall();
						player.setFireBall(false);
						player.setInvincible(true);
						player.setInvincibleTime(90);
						sounds.getSmall();
					}
				
					// If the player is small, it is Game Over.  
					else if(player.getBig() == false && player.isInvincible() == false){
						gameOver();
					}
			}
		}
	}   
	   
	   
	   
	
	
	public void checkIfAShellHitsAMonster() {
		
		for(Monster monster: monsterList) {
			for(Monster monster2: monsterList) {
				
				if(computation.distanceBetweenObjectsXWise(monster, monster2) == 0 &&
				computation.objectsWouldCollideInXDirection(monster, monster2) &&
				monster2.getClass().getName().equals("screenObjects.KoopaShell") &&
				monster.equals(monster2) == false &&
				monster2.getSpeed() > 0 ){
					
					group.getChildren().remove(monster.getImageView());
					startRemoveMonsterAnimation((Monster)monster);
					helpList.add(monster);
					sounds.click();
					  		
					if(computation.Objec1IsLeftOfObject2(monster, monster2)){
						monster2.setDirectionLeft(true);
					}
					else {
						monster2.setDirectionLeft(false);
					}
				}
			}
		}
		
		removeFromList(monsterList);
	}


	   
	    
	  
	
	public void checkIfAFireBallHitsAMonster() {
		   
		Iterator<FireBall> it = FireBallList.iterator();
		while(it.hasNext()) {
			ScreenObject FireBall = it.next();
			   
			Iterator<Monster> it2 = monsterList.iterator();
			while(it2.hasNext()) {
				Monster monster = it2.next();
				   
				// If a fire ball hits a monster, both objects are removed. 
				if(computation.objectsWouldCollideInYDirection(FireBall, monster)  &&
				   computation.objectsWouldCollideInXDirection(FireBall, monster)){
					   	
					   group.getChildren().remove(monster.getImageView());
					   startRemoveMonsterAnimation((Monster)monster);
					   it2.remove();
					   group.getChildren().remove(FireBall.getImageView());
					   it.remove();
				}
			}
		}
	}
	
	
	
	
	
	public void checkIfThereIsAnAnimation() {
		
		Iterator<ScreenObject> it = animationList.iterator();
		while(it.hasNext()) {
			ScreenObject so = it.next();
			
			if(so.getAnimationTime() == 0) {
				group.getChildren().remove(so.getObject());
				group.getChildren().remove(so.getImageView());
				it.remove();
			}
			else {
				so.animation();
			}
		}
	}
	   
	  
	
	
	   
	public void changeInvincible() {
		   
		if(player.getInvincibleTime() > 0) {
			player.setInvincibleTime(-1);
		}
		else {
			player.setInvincible(false);
		}
	}
	   
	   
	   
	   
	public void handleFlashingPlayer() {
		   
		if(player.getFlashingTime() > 0) {
			player.setFlashingTime(-1);
			group.getChildren().remove(player.getImageView());
			player.changeImage();
			group.getChildren().add(player.getImageView());
		}
		   
		else {
			player.setFlashing(false);
			sounds.changeMusic();
		}
	}
	   
	
	
	public void handleQuestionMarkFlashing() {
		  
		for(ScreenObject b: StationaryObjectList) {
			if(b.getClass().getName().equals("screenObjects.QuestionMarkBlock")) {
				QuestionMarkBlock f = (QuestionMarkBlock) b;
				group.getChildren().remove(f.getImageView());
				f.chooseImage();
				group.getChildren().add(f.getImageView());
			}
		}
	}
	   
	
	
	
	public void checkIfLevelIsFinished() {
		
		if(player.getXAbsolutValue() >= 6990 && player.getYValue() < 500 - player.getHeight()) {
			gameFinished();
		}
	}
	
	
	
	public void gameFinished() {
		   
		group.getChildren().remove(player.getImageView());

		Text t = new Text("YEAH! FUCK THE CORPORATE WORLD!");
		t.setX(9);
		t.setY(72);
		t.setFill(Color.YELLOW);
		t.setFont(Font.font ("Verdana", 60));
		group.getChildren().add(t);
		sounds.stop();
		this.stop = true;
		sounds.gameFinished();
	}
	
	
	
	
	
	public void gameOver() {
		
		group.getChildren().remove(player.getImageView());
		Text t = new Text("GAME OVER");
		t.setX(400);
		t.setY(200);
		t.setFill(Color.RED);
		t.setFont(Font.font ("Verdana", 60));
		group.getChildren().add(t);
		sounds.stop();
		sounds.gameOver();
		this.stop = true;
	}
	   
	   
	   
	
	public void checkIfBlockContainsItem(Block block) {
		
		if(block.goldCoin()) {
			startGoldCoinAnimation(block);
			block.setGoldCoin(false);
			changeCanBeRemoved(block);
			block.changeImage();
		}
		   
		if(block.mushroom()) {
			createMushroom(block.getXValue(), block.getYValue());
	   		block.setMushroom(false);
	   		changeCanBeRemoved(block);
	   		block.changeImage();
		}
		   
		if(block.star()) {
			createStar(block.getXValue(), block.getYValue());
			block.setStar(false);
			changeCanBeRemoved(block);
			block.changeImage();
		}
	}
	
	
	
	
	public void changeCanBeRemoved(Block b) {
	    group.getChildren().remove(b.getImageView());
		b.setCanBeRemoved(false);
		b.changeImage();
		group.getChildren().add(b.getImageView());
	}
	   
	   
  
	  
	
	public void scrollScreen(int change) {
		   
		scrollScreenHelp(StationaryObjectList, change);
		scrollScreenHelp(monsterList, change);
		scrollScreenHelp(animationList, change);
		scrollScreenHelp(MushroomList, change);
		scrollScreenHelp(FireBallList, change);
		scrollScreenHelp(FireFlowerList, change);
		scrollScreenHelp(starList, change);
		scrollScreenHelp(backgroundList, change);
	}
	   
	   
	public void scrollScreenHelp(List<? extends ScreenObject> list, int change) {
		for(ScreenObject so: list) {
			so.setXValue(change);
		}
	}
	   
   
	   

	public void createFireBall() {
		   
		FireBall FireBall = new FireBall(player.getXValue(), player.getYValue(), 
		player.directionLeft(), player.getBig());
		
		FireBallList.add(FireBall);
		group.getChildren().add(FireBall.getImageView());
	}
	   
	   

	   
	public void createMushroom(int x, int y) {
		   
		if(player.getBig() == false) {
			Mushroom Mushroom = new Mushroom(x, y);
			Mushroom.setSpecialMovement(true);
			Mushroom.setSpecialMovementTime(25);
			MushroomList.add(Mushroom);
			group.getChildren().add(Mushroom.getImageView());
		}
		   
		else {
			FireFlower FireFlower = new FireFlower (x, y);
			FireFlower.setSpecialMovement(true);
			FireFlower.setSpecialMovementTime(25);
			FireFlowerList.add(FireFlower);
			group.getChildren().add(FireFlower.getImageView());
		}
		
		sounds.newMushroom();
	}
	   
	   
	   
	   
	public void createStar(int x, int y) {
		   
		Star star = new Star(x, y);
		starList.add(star);
		group.getChildren().add(star.getImageView());
	}
	   
	   
	   
	   
	public void startGoldCoinAnimation(StationaryObject b) {
		   
		GoldCoin g = new GoldCoin(b.getXValue(), b.getYValue());
		g.setAnimationTime(15);
		animationList.add(g);
		group.getChildren().add(g.getObject());
		sounds.coin();
	}
	   
	   
	   
	   
	   
	public void startCrushedBlockAnimation(StationaryObject b) {
		   
		for(int i = 0; i <= 3; i++) {
			CrushedBlock k = new CrushedBlock(b.getXValue(), b.getYValue(), i);
			k.setAnimationTime(5);
			animationList.add(k);
			group.getChildren().add(k.getImageView());
		}
		sounds.playCrushedBlock();
	}
	   
	   
	   
	   
	public void startRemoveMonsterAnimation(Monster m) {
		   	
		animationList.add(m);
		group.getChildren().add(m.getImageView());
		m.setAnimationTime(15);
	}
	
	
	
	
	public int freeSpaceAbove(ScreenObject so) {
		
		int freeSpace = 100;
		for(ScreenObject b: StationaryObjectList) {
			if(computation.objectsWouldCollideInYDirection(so, b) &&
			computation.object1IsAboveObject2(b, so) &&
			computation.distanceBetweenObjectsYWise(b, so) < freeSpace) {
			
				freeSpace = computation.distanceBetweenObjectsYWise(b, so);
				if(freeSpace == 0 && b.getClass().getSuperclass().getName().equals("screenObjects.Block")) {
					jumpUpToBlock((Block) b);
				}
			}
		}
		return freeSpace;
	}
	
	
	
	
	class MouseClick implements EventHandler<MouseEvent>{
		
		@Override
		public void handle(MouseEvent event) {
			   
			if(player.getJumpTime() == 0 && computation.standsOnSomething(player)) {
				player.setPlayerJumps(true);;
				player.setJumpTime(7);
				sounds.jump();
			}
		}
	}
	
	class KeyRelease implements EventHandler<KeyEvent>{
		
		@Override
		public void handle(KeyEvent ke) {
			
			if(ke.getCode() == KeyCode.D) {
				player.setGoRight(false);
			}
			if(ke.getCode() == KeyCode.A) {
				player.setGoLeft(false);
			}
			if(ke.getCode() == KeyCode.SPACE) {
				player.setSpeed2(5);
				if(player.getFireBall() && stop == false) {
					createFireBall();
					sounds.click();
				}
				
			}
		}
	}
	
	
	class KeyPress implements EventHandler<KeyEvent>{
		
		@Override
	    public void handle(KeyEvent event) {
			
			if (event.getCode() == KeyCode.D) {
				player.setGoRight(true);
	    	}
	    		
	    	if (event.getCode() == KeyCode.A) {
	    		player.setGoLeft(true);
	    	}
	    		
	    	if (event.getCode() == KeyCode.SPACE) {
	    		player.setSpeed2(9);
	    	}
	    }
	}

}
