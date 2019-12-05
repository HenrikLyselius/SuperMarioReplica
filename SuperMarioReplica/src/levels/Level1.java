package levels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import screenObjects.*;



public class Level1 {

	private List<StationaryObject> stationaryList;
	private List<Monster> monsterList;
	private List<ScreenObject> backgroundList;
	
	public Level1() {
		
		
		stationaryList = new ArrayList<StationaryObject>();
		monsterList = new ArrayList<Monster>();
		backgroundList = new ArrayList<ScreenObject>();

		createLevel();
	}
	
	
	public List<StationaryObject> getLevel() {
		return stationaryList;
	}
	
	
	public List<Monster> getMonsterList(){
		return monsterList;
	}
	
	public List<ScreenObject> getBackgroundList(){
		return backgroundList;
	}
	
	
	public void createLevel() {
		
		backgroundList.add(new PlayingInstructions(20, 0));
		backgroundList.add(new Cloud(400, 120));
		backgroundList.add(new Cloud(750, 70));
		backgroundList.add(new Cloud(1200, 90));
		backgroundList.add(new Cloud(1450, 50));
		backgroundList.add(new Cloud(1500, 50));
		backgroundList.add(new Cloud(1750, 110));
		backgroundList.add(new Cloud(1972, 70));
		backgroundList.add(new Cloud(2200, 90));
		backgroundList.add(new Cloud(2650, 20));
		backgroundList.add(new Cloud(2750, 20));
		backgroundList.add(new Cloud(2960, 77));
		backgroundList.add(new Cloud(3200, 92));
		backgroundList.add(new Cloud(3320, 10));
		backgroundList.add(new Cloud(3900, 20));
		backgroundList.add(new Cloud(4020, 60));
		backgroundList.add(new Cloud(4260, 50));
		backgroundList.add(new Cloud(4400, 77));
		backgroundList.add(new Cloud(4425, 77));
		backgroundList.add(new Cloud(4675, 20));
		backgroundList.add(new Cloud(4700, 60));
		backgroundList.add(new Cloud(5200, 70));
		backgroundList.add(new Cloud(5275, 20));
		backgroundList.add(new Cloud(5560, 65));
		backgroundList.add(new Cloud(5590, 75));
		backgroundList.add(new Cloud(6000, 22));
		backgroundList.add(new Cloud(6400, 106));
		backgroundList.add(new Cloud(7226, 176));
		
		
		
		backgroundList.add(new Bush(250, 500));
		backgroundList.add(new Bush(400, 500));
		backgroundList.add(new Hill(650, 484));
	
		
		QuestionMarkBlock b1 = new QuestionMarkBlock(600, 375);
		b1.setCanBeRemoved(false);
		b1.setGoldCoin(true);
		b1.setCanBeRemoved(true);
		stationaryList.add(b1);
		
		QuestionMarkBlock b2 = new QuestionMarkBlock(875, 200);
		b2.setGoldCoin(true);
		stationaryList.add(b2);
		
		BlockType1 k1 = new BlockType1(775, 375);
		stationaryList.add(k1);
		QuestionMarkBlock b3 = new QuestionMarkBlock(825, 375);
		b3.setMushroom(true);
		stationaryList.add(b3);
		BlockType1 k2 = new BlockType1(875, 375);
		stationaryList.add(k2);
		BlockType1 k3 = new BlockType1(925, 375);
		stationaryList.add(k3);
		QuestionMarkBlock b4 = new QuestionMarkBlock(975, 375);
		b4.setGoldCoin(true);
		stationaryList.add(b4);
		BlockType1 k4 = new BlockType1(1025,375);
		stationaryList.add(k4);
		

		monsterList.add(new MonsterKoopa(700, 250));
		
		MonsterKoopa m1 = new MonsterKoopa(925, 320);
		m1.setDirectionLeft(true);
		m1.setMonsterType(3);
		monsterList.add(m1);
		
		monsterList.add(new MonsterGoomba(1100, 500));
		
		stationaryList.add(new TubeTop(1200, 400));
		stationaryList.add(new TubePart(1200, 450));
		
		stationaryList.add(new TubeTop(1425, 300));
		stationaryList.add(new TubePart(1425, 350));
		stationaryList.add(new TubePart(1425, 450));
		
		QuestionMarkBlock b5 = new QuestionMarkBlock(1625, 425);
		b5.setCanBeRemoved(false);
		b5.changeImage();
		stationaryList.add(b5);
		
		stationaryList.add(new TubeTop(1675, 300));
		stationaryList.add(new TubePart(1675, 350));
		stationaryList.add(new TubePart(1675, 450));
		
		
		backgroundList.add(new Bush(1900, 500));
		backgroundList.add(new Bush(1970, 500));
		monsterList.add(new MonsterGoomba(1925, 450));
		
		QuestionMarkBlock b6 = new QuestionMarkBlock(2150, 200);
		b6.setGoldCoin(true);
		stationaryList.add(b6);
		
		QuestionMarkBlock b7 = new QuestionMarkBlock(2150, 375);
		b7.setMushroom(true);
		stationaryList.add(b7);
		
		stationaryList.add(new BlockType1(2100, 375));
		
		backgroundList.add(new Hill(2760, 484));
		backgroundList.add(new Bush(2860, 500));
		backgroundList.add(new Bush(2950, 500));
		backgroundList.add(new Bush(3200, 500));
		backgroundList.add(new Bush(3420, 500));
		backgroundList.add(new Bush(3960, 500));
		backgroundList.add(new Bush(4220, 500));
		
		
		QuestionMarkBlock b8 = new QuestionMarkBlock(2900, 375);
		b8.setStar(true);
		stationaryList.add(b8);
		
		for(int i = 3200; i <= 3600; i = i + 200) {
			QuestionMarkBlock b = new QuestionMarkBlock(i, 375);
			b.setGoldCoin(true);
			stationaryList.add(b);
		}
			   
		
		QuestionMarkBlock b9 = new QuestionMarkBlock(3400, 200);
		b9.setMushroom(true);
		stationaryList.add(b9);
		
		
		monsterList.add(new MonsterKoopa(3600, 500));
		monsterList.add(new MonsterKoopa(4000, 500));
		monsterList.add(new MonsterGoomba(4200, 500));
		monsterList.add(new MonsterGoomba(4260, 500));
		
		
		
		for(int i = 4400; i <= 4800; i = i + 50) {
			stationaryList.add(new BlockType3(i, 500));
		}
		for(int i = 4450; i <= 4750; i = i + 50) {
			stationaryList.add(new BlockType3(i, 450));
		}
		for(int i = 4500; i <= 4700; i = i + 50) {
			stationaryList.add(new BlockType3(i, 400));
		}
		for(int i = 4550; i <= 4650; i = i + 50) {
			stationaryList.add(new BlockType3(i, 350));
		}
		stationaryList.add(new BlockType3(4600, 300));
		
		backgroundList.add(new Bush(4920, 500));
		
		stationaryList.add(new BlockType3(5520, 500));
		
		monsterList.add(new MonsterKoopa(5000, 500));
		monsterList.add(new MonsterKoopa(5200, 500));
		monsterList.add(new MonsterKoopa(5420, 500));
		
		backgroundList.add(new Bush(4950, 500));
		backgroundList.add(new Hill(5020, 484));
		backgroundList.add(new Bush(5225, 500));
		 
		
		backgroundList.add(new Bush(5660, 500));
		 
		stationaryList.add(new BlockType2(5950, 500));
		 
		for(int i = 6200; i < 6300; i = i + 50) {
			stationaryList.add(new BlockType2(i, 550));
		}
			
		stationaryList.add(new TubeTop(6200, 400));
		stationaryList.add(new TubePart(6200, 450));
		 
		 
		for(int i = 6400; i < 6500; i = i + 50) {
			stationaryList.add(new BlockType2(i, 550));
		}
		 
		stationaryList.add(new TubeTop(6400, 300));
		stationaryList.add(new TubePart(6400, 350));
		stationaryList.add(new TubePart(6400, 450));
		 
		
		stationaryList.add(new TubeTop(6600, 200));
		stationaryList.add(new TubePart(6600, 250));
		stationaryList.add(new TubePart(6600, 350));
		stationaryList.add(new TubePart(6600, 450));
		
		backgroundList.add(new Hill(6750, 484));
		backgroundList.add(new Bush(6900, 500));
		
		stationaryList.add(new BlockType3(7000, 500));
		backgroundList.add(new FlagPole(7012, 100));
		
		
		for(int i = 0; i < 2200; i = i + 50) {
			   stationaryList.add(new BlockType2(i, 550));
			   
		   }
		 
		 for(int i = 2600; i < 6000; i = i + 50) {
			   stationaryList.add(new BlockType2(i, 550));
		 }
		
		 for(int i = 6600; i < 7900; i = i + 50) {
			   stationaryList.add(new BlockType2(i, 550));
		}

		 
		 Collections.sort(stationaryList, new CompareScreenObjects());
		 
	}
	
}