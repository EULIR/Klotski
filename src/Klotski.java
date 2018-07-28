import org.frice.Game;
import org.frice.obj.sub.ImageObject;
import org.frice.resource.image.FileImageResource;
import org.frice.resource.image.ImageResource;
import org.frice.util.media.AudioManager;
import org.frice.util.media.AudioPlayer;

import java.awt.event.KeyEvent;

import static org.frice.Initializer.launch;

public class Klotski extends Game {
	private ImageObject[][] matrix;
	private int x;
	private int y;

	public static void main(String[] args) {
		launch(Klotski.class);
	}

	@Override
	public void onInit() {
		setShowFPS(true);
		setSize(800, 850);
		//setLocation(100, 100);
		setLoseFocusChangeColor(false);
		setTitle("FIGURE KLOTSKI");
		x = y = 3;
		matrix = new ImageObject[][]{
				{
						new ImageObject(new FileImageResource("./res/img/1.jpg"), 0, 0),
						new ImageObject(new FileImageResource("./res/img/2.jpg"), 200, 0),
						new ImageObject(new FileImageResource("./res/img/3.jpg"), 400, 0),
						new ImageObject(new FileImageResource("./res/img/4.jpg"), 600, 0)},
				{
						new ImageObject(new FileImageResource("./res/img/5.jpg"), 0, 200),
						new ImageObject(new FileImageResource("./res/img/6.jpg"), 200, 200),
						new ImageObject(new FileImageResource("./res/img/7.jpg"), 400, 200),
						new ImageObject(new FileImageResource("./res/img/8.jpg"), 600, 200)},
				{
						new ImageObject(new FileImageResource("./res/img/9.jpg"), 0, 400),
						new ImageObject(new FileImageResource("./res/img/10.jpg"), 200, 400),
						new ImageObject(new FileImageResource("./res/img/11.jpg"), 400, 400),
						new ImageObject(new FileImageResource("./res/img/12.jpg"), 600, 400)},
				{
						new ImageObject(new FileImageResource("./res/img/13.jpg"), 0, 600),
						new ImageObject(new FileImageResource("./res/img/14.jpg"), 200, 600),
						new ImageObject(new FileImageResource("./res/img/15.jpg"), 400, 600),
						new ImageObject(new FileImageResource("./res/img/0.jpg"), 600, 600)}
		};
		for (int i = 0; i < 1000; i++) {
			int a = (int) (Math.random() * 4);
			switch (a) {
				case 0:
					upMove(false);
					break;
				case 1:
					downMove(false);
					break;
				case 2:
					rightMove(false);
					break;
				case 3:
					leftMove(false);
					break;
			}
		}
		add();
		addKeyListener(null, keyEvent ->
		{
			switch (keyEvent.getKeyCode()) {
				case KeyEvent.VK_DOWN:
					downMove(true);
					break;
				case KeyEvent.VK_UP:
					upMove(true);
					break;
				case KeyEvent.VK_LEFT:
					leftMove(true);
					break;
				case KeyEvent.VK_RIGHT:
					rightMove(true);
					break;
			}
		}, keyEvent ->
		{
		});
	}

	@Override
	public void onRefresh() {
		//if (x == 3 && y == 3)
		//dialogShow("adadad");
	}

	public void add() {
		for (ImageObject[] i : matrix)
			for (ImageObject im : i)
				addObject(im);
	}

	public void downMove(boolean audioOn) {
		if (x != 0) {
			ImageResource image = matrix[x - 1][y].getResource();
			matrix[x][y].setRes(image);
			matrix[x - 1][y].setRes(new FileImageResource("./res/img/0.jpg"));
			x--;
			if (audioOn) {
				AudioPlayer audioPlayer = AudioManager.getPlayer("./res/mus/SpeechOn.wav");
				audioPlayer.start();
			}
		} else if (audioOn) {
			AudioPlayer audioPlayer = AudioManager.getPlayer("./res/mus/WindowsBackground.wav");
			audioPlayer.start();
		}
	}

	public void rightMove(boolean audioOn) {
		if (y != 0) {
			ImageResource image = matrix[x][y - 1].getResource();
			matrix[x][y].setRes(image);
			matrix[x][y - 1].setRes(new FileImageResource("./res/img/0.jpg"));
			y--;
			if (audioOn) {
				AudioPlayer audioPlayer = AudioManager.getPlayer("./res/mus/SpeechOn.wav");
				audioPlayer.start();
			}
		} else if (audioOn) {
			AudioPlayer audioPlayer = AudioManager.getPlayer("./res/mus/WindowsBackground.wav");
			audioPlayer.start();
		}
	}

	public void leftMove(boolean audioOn) {
		if (y != 3) {
			ImageResource image = matrix[x][y + 1].getResource();
			matrix[x][y].setRes(image);
			matrix[x][y + 1].setRes(new FileImageResource("./res/img/0.jpg"));
			y++;
			if (audioOn) {
				AudioPlayer audioPlayer = AudioManager.getPlayer("./res/mus/SpeechOn.wav");
				audioPlayer.start();
			}
		} else if (audioOn) {
			AudioPlayer audioPlayer = AudioManager.getPlayer("./res/mus/WindowsBackground.wav");
			audioPlayer.start();
		}
	}

	public void upMove(boolean audioOn) {
		if (x != 3) {
			ImageResource image = matrix[x + 1][y].getResource();
			matrix[x][y].setRes(image);
			matrix[x + 1][y].setRes(new FileImageResource("./res/img/0.jpg"));
			x++;
			if (audioOn) {
				AudioPlayer audioPlayer = AudioManager.getPlayer("./res/mus/SpeechOn.wav");
				audioPlayer.start();
			}
		} else if (audioOn) {
			AudioPlayer audioPlayer = AudioManager.getPlayer("./res/mus/WindowsBackground.wav");
			audioPlayer.start();
		}
	}
}