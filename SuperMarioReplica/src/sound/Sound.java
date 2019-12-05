package sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound {

		
		private AudioClip coin;
		private AudioClip jump;
		private AudioClip crushedBlock;
		private AudioClip gameOver;
		private AudioClip bump;
		private AudioClip getBig;
		private AudioClip newMushroom;
		private AudioClip click;
		private AudioClip getSmall;
		private Media theme;
		private Media star;
		private Media gameFinished;
		private MediaPlayer playerTheme;
		private MediaPlayer playerStar;
		private MediaPlayer playerGameFinished;

		public Sound() {
			getBig = new AudioClip(getClass().getResource("/media/BliStorLjud.wav").toString());
			gameOver = new AudioClip(getClass().getResource("/media/SpeletSlutLjud.wav").toString());
			jump = new AudioClip(getClass().getResource("/media/HoppLjud.mp3").toString());
			coin = new AudioClip(getClass().getResource("/media/MyntLjud.mp3").toString());
			newMushroom = new AudioClip(getClass().getResource("/media/SvampLjud.wav").toString());
			bump = new AudioClip(getClass().getResource("/media/BumpLjud.wav").toString());	
			crushedBlock = new AudioClip(getClass().getResource("/media/KrossaBlockLjud.wav").toString());
			click = new AudioClip(getClass().getResource("/media/SkjutEldbollLjud.wav").toString());
			getSmall = new AudioClip(getClass().getResource("/media/BliLitenLjud.wav").toString());
			theme = new Media(getClass().getResource("/media/Tema.mp3").toString());
			star = new Media(getClass().getResource("/media/StarSound.mp3").toString());
			gameFinished = new Media(getClass().getResource("/media/TheSafetyDance.mp3").toString());
			playerTheme = new MediaPlayer(this.theme);
			playerStar = new MediaPlayer(this.star);
			playerGameFinished = new MediaPlayer(this.gameFinished);
		}
		
		public void playCrushedBlock() {
			crushedBlock.setVolume(0.2);
			crushedBlock.play();
		}
		
		public void getBig() {
			getBig.setVolume(0.2);
			getBig.play();
		}
		
		public void gameOver() {
			gameOver.setVolume(0.2);
			gameOver.play();
		}
		
		public void coin() {
			coin.setVolume(0.2);
			coin.play();
		}
		
		public void newMushroom() {
			newMushroom.setVolume(0.2);
			newMushroom.play();
		}
		
//		public static void bump2() {
//			bump2.play();
//		}
		
		
		public void bump() {
			bump.setVolume(0.2);
			bump.play();
		}
		
		public void jump() {
			jump.setVolume(0.02);
			jump.play();
		}
		
		public void click() {
			click.setVolume(0.2);
			click.play();
		}
		
		public void getSmall() {
			getSmall.setVolume(0.2);
			getSmall.play();
		}
		
		public void stop() {
			playerTheme.stop();
			playerStar.stop();
		}
		
		public void theme() {
		    playerTheme.setVolume(0.04);  
			playerTheme.play();
		}
		
		public void star() {
			playerTheme.stop();
		    playerStar.setVolume(0.04); 
		    playerStar.play();
		}
		
		
		
		public void gameFinished() {
			playerGameFinished.setVolume(0.5);
			playerGameFinished.play();
		}
		

		
		public void changeMusic() {
			if(playerStar.getStatus() == MediaPlayer.Status.PLAYING) {
				playerStar.stop();
				playerTheme.play();
			}
		}
	
}
