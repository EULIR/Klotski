import org.frice.Game;
import org.frice.obj.sub.ImageObject;
import org.frice.resource.image.FileImageResource;
import org.frice.resource.image.ImageResource;
import org.frice.util.FileUtils;
import org.frice.util.media.AudioManager;
import org.frice.util.media.AudioPlayer;

import java.awt.event.KeyEvent;

import static org.frice.Initializer.launch;

public class Klotski extends Game {
	private ImageObject[][] matrix;
	private int[][] figure;
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
		figure = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
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
	public void onExit() {
		System.exit(0);
	}

	@Override
	public void onRefresh() {
		if (win()) {
			new Thread(() -> FileUtils.image2File(getScreenCut().getImage(), "screenshot.png")).start();
			//SimpleText gameOver = new SimpleText(ColorResource.RED, "CONGRATULATIONS!", 100, 200);
			//gameOver.setTextSize(100);
			//addObject(2, gameOver);
			dialogShow("Excellent Job!", "Congratulations");
			onExit();
		}
	}

	public boolean win() {
		if (figure[3][3] != 16)
			return false;
		for (int i = 0; i < figure.length; i++) {
			for (int j = 0; j < figure[i].length; j++) {
				if (figure[i][j] != i * 4 + j + 1)
					return false;
			}
		}
		return true;
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
			int temp = figure[x][y];
			figure[x][y] = figure[x - 1][y];
			figure[x - 1][y] = temp;
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
			int temp = figure[x][y];
			figure[x][y] = figure[x][y - 1];
			figure[x][y - 1] = temp;
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
			int temp = figure[x][y];
			figure[x][y] = figure[x][y + 1];
			figure[x][y + 1] = temp;
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
			int temp = figure[x][y];
			figure[x][y] = figure[x + 1][y];
			figure[x + 1][y] = temp;
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