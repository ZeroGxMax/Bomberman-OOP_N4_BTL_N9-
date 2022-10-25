package graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Lưu trữ thông tin các pixel của 1 sprite (hình ảnh game)
 */
public class Sprite {

	public static final int DEFAULT_SIZE = 16;
	public static final int SCALED_SIZE = DEFAULT_SIZE * 2;
	private static final int TRANSPARENT_COLOR = 0xffff00ff;
	public final int SIZE;
	private int _x, _y;
	public int[] _pixels;
	protected int _realWidth;
	protected int _realHeight;
	private SpriteSheet _sheet;

	/*
	 * |--------------------------------------------------------------------------
	 * | Board sprites
	 * |--------------------------------------------------------------------------
	 */
	public static Sprite grass = new Sprite(DEFAULT_SIZE, 6, 0, SpriteSheet.tiles, 16, 16);
	public static Sprite brick = new Sprite(DEFAULT_SIZE, 7, 0, SpriteSheet.tiles, 16, 16);
	public static Sprite wall = new Sprite(DEFAULT_SIZE, 5, 0, SpriteSheet.tiles, 16, 16);
	public static Sprite portal = new Sprite(DEFAULT_SIZE, 4, 0, SpriteSheet.tiles, 14, 14);

	/*
	 * |--------------------------------------------------------------------------
	 * | Bomber Sprites
	 * |--------------------------------------------------------------------------
	 */
	public static Sprite[] player_up = {
			new Sprite(DEFAULT_SIZE, 0, 0, SpriteSheet.tiles, 12, 16),
			new Sprite(DEFAULT_SIZE, 0, 1, SpriteSheet.tiles, 12, 16),
			new Sprite(DEFAULT_SIZE, 0, 2, SpriteSheet.tiles, 12, 15)
	};

	public static Sprite[] player_down = {
			new Sprite(DEFAULT_SIZE, 2, 0, SpriteSheet.tiles, 12, 15),
			new Sprite(DEFAULT_SIZE, 2, 1, SpriteSheet.tiles, 12, 15),
			new Sprite(DEFAULT_SIZE, 2, 2, SpriteSheet.tiles, 12, 16)
	};

	public static Sprite[] player_left = {
			new Sprite(DEFAULT_SIZE, 3, 0, SpriteSheet.tiles, 10, 15),
			new Sprite(DEFAULT_SIZE, 3, 1, SpriteSheet.tiles, 11, 16),
			new Sprite(DEFAULT_SIZE, 3, 2, SpriteSheet.tiles, 12, 16)
	};

	public static Sprite[] player_right = {
			new Sprite(DEFAULT_SIZE, 1, 0, SpriteSheet.tiles, 10, 16),
			new Sprite(DEFAULT_SIZE, 1, 1, SpriteSheet.tiles, 11, 16),
			new Sprite(DEFAULT_SIZE, 1, 2, SpriteSheet.tiles, 12, 16)
	};

	public static Sprite[] player_dead = {
			new Sprite(DEFAULT_SIZE, 4, 2, SpriteSheet.tiles, 14, 16),
			new Sprite(DEFAULT_SIZE, 5, 2, SpriteSheet.tiles, 13, 15),
			new Sprite(DEFAULT_SIZE, 6, 2, SpriteSheet.tiles, 16, 16)
	};

	/*
	 * |--------------------------------------------------------------------------
	 * | Character
	 * |--------------------------------------------------------------------------
	 */
	// BALLOM
	public static Sprite[] balloom_left = {
			new Sprite(DEFAULT_SIZE, 9, 0, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 9, 1, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 9, 2, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite[] balloom_right = {
			new Sprite(DEFAULT_SIZE, 10, 0, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 10, 1, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 10, 2, SpriteSheet.tiles, 16, 16)
	};

	//
	public static Sprite balloom_dead = new Sprite(DEFAULT_SIZE, 9, 3, SpriteSheet.tiles, 16, 16);

	// ONEAL
	public static Sprite[] oneal_left = {
			new Sprite(DEFAULT_SIZE, 11, 0, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 11, 1, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 11, 2, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite[] oneal_right = {
			new Sprite(DEFAULT_SIZE, 12, 0, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 12, 1, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 12, 2, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite oneal_dead = new Sprite(DEFAULT_SIZE, 11, 3, SpriteSheet.tiles, 16, 16);

	//DOLL
	public static Sprite[] doll_left = {
			new Sprite(DEFAULT_SIZE,13,0,SpriteSheet.tiles,16,16),
			new Sprite(DEFAULT_SIZE,13,1,SpriteSheet.tiles,16,16),
			new Sprite(DEFAULT_SIZE,13,2,SpriteSheet.tiles,16,16),
	};

	public static Sprite[] doll_right = {
			new Sprite(DEFAULT_SIZE,14,0,SpriteSheet.tiles,16,16),
			new Sprite(DEFAULT_SIZE,14,1,SpriteSheet.tiles,16,16),
			new Sprite(DEFAULT_SIZE,14,2,SpriteSheet.tiles,16,16),
	};

	public static Sprite doll_dead = new Sprite(DEFAULT_SIZE,13,3,SpriteSheet.tiles,16,16);

	// GHOST

	public static Sprite[] ghost_right = {
			new Sprite(DEFAULT_SIZE,6,5,SpriteSheet.tiles,16,16),
			new Sprite(DEFAULT_SIZE,6,6,SpriteSheet.tiles,16,16),
			new Sprite(DEFAULT_SIZE,6,7,SpriteSheet.tiles,16,16),
	};

	public static Sprite[] ghost_left = {
			new Sprite(DEFAULT_SIZE,7,5,SpriteSheet.tiles,16,16),
			new Sprite(DEFAULT_SIZE,7,6,SpriteSheet.tiles,16,16),
			new Sprite(DEFAULT_SIZE,7,7,SpriteSheet.tiles,16,16)
	};

	public static Sprite ghost_dead = new Sprite(DEFAULT_SIZE,6,8,SpriteSheet.tiles,16,16);

	// MINVO
	public static Sprite[] minvo_left = {
			new Sprite(DEFAULT_SIZE, 8, 5, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 8, 6, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 8, 7, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite[] minvo_right = {
			new Sprite(DEFAULT_SIZE, 9, 5, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 9, 6, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 9, 7, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite minvo_dead = new Sprite(DEFAULT_SIZE, 8, 8, SpriteSheet.tiles, 16, 16);

	//KONDORIA

	public static Sprite[] kondoria_left = {
			new Sprite(DEFAULT_SIZE, 10, 5, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 10, 6, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 10, 7, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite[] kondoria_right = {
			new Sprite(DEFAULT_SIZE, 11, 5, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 11, 6, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 11, 7, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite kondoria_dead = new Sprite(DEFAULT_SIZE, 10, 8, SpriteSheet.tiles, 16, 16);

	// ALL
	public static Sprite[] mob_dead = {
			new Sprite(DEFAULT_SIZE, 15, 0, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 15, 1, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 15, 2, SpriteSheet.tiles, 16, 16)
	};

	/*
	 * |--------------------------------------------------------------------------
	 * | Bomb Sprites
	 * |--------------------------------------------------------------------------
	 */
	public static Sprite[] bomb = {
			new Sprite(DEFAULT_SIZE, 0, 3, SpriteSheet.tiles, 15, 15),
			new Sprite(DEFAULT_SIZE, 1, 3, SpriteSheet.tiles, 13, 15),
			new Sprite(DEFAULT_SIZE, 2, 3, SpriteSheet.tiles, 12, 14)
	};

	/*
	 * |--------------------------------------------------------------------------
	 * | FlameSegment Sprites
	 * |--------------------------------------------------------------------------
	 */
	public static Sprite[] bomb_exploded = {
			new Sprite(DEFAULT_SIZE, 0, 4, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 0, 5, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 0, 6, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite[] explosion_vertical = {
			new Sprite(DEFAULT_SIZE, 1, 5, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 2, 5, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 3, 5, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite[] explosion_horizontal = {
			new Sprite(DEFAULT_SIZE, 1, 7, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 1, 8, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 1, 9, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite[] explosion_horizontal_left_last = {
			new Sprite(DEFAULT_SIZE, 0, 7, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 0, 8, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 0, 9, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite[] explosion_horizontal_right_last = {
			new Sprite(DEFAULT_SIZE, 2, 7, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 2, 8, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 2, 9, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite[] explosion_vertical_top_last = {
			new Sprite(DEFAULT_SIZE, 1, 4, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 2, 4, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 3, 4, SpriteSheet.tiles, 16, 16)
	};

	public static Sprite[] explosion_vertical_down_last = {
			new Sprite(DEFAULT_SIZE, 1, 6, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 2, 6, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 3, 6, SpriteSheet.tiles, 16, 16)
	};

	/*
	 * |--------------------------------------------------------------------------
	 * | Brick FlameSegment
	 * |--------------------------------------------------------------------------
	 */
	public static Sprite[] brick_exploded = {
			new Sprite(DEFAULT_SIZE, 7, 1, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 7, 2, SpriteSheet.tiles, 16, 16),
			new Sprite(DEFAULT_SIZE, 7, 3, SpriteSheet.tiles, 16, 16)
	};

	/*
	 * |--------------------------------------------------------------------------
	 * | Powerups
	 * |--------------------------------------------------------------------------
	 */
	public static Sprite powerup_bombs = new Sprite(DEFAULT_SIZE, 0, 10, SpriteSheet.tiles, 16, 16);
	public static Sprite powerup_flames = new Sprite(DEFAULT_SIZE, 1, 10, SpriteSheet.tiles, 16, 16);
	public static Sprite powerup_speed = new Sprite(DEFAULT_SIZE, 2, 10, SpriteSheet.tiles, 16, 16);
	public static Sprite powerup_wallpass = new Sprite(DEFAULT_SIZE, 3, 10, SpriteSheet.tiles, 16, 16);
	public static Sprite powerup_detonator = new Sprite(DEFAULT_SIZE, 4, 10, SpriteSheet.tiles, 16, 16);
	public static Sprite powerup_bombpass = new Sprite(DEFAULT_SIZE, 5, 10, SpriteSheet.tiles, 16, 16);
	public static Sprite powerup_flamepass = new Sprite(DEFAULT_SIZE, 6, 10, SpriteSheet.tiles, 16, 16);

	public Sprite(int size, int x, int y, SpriteSheet sheet, int rw, int rh) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		_x = x * SIZE;
		_y = y * SIZE;
		_sheet = sheet;
		_realWidth = rw;
		_realHeight = rh;
		load();
	}

	public Sprite(int size, int color) {
		SIZE = size;
		_pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	private void setColor(int color) {
		for (int i = 0; i < _pixels.length; i++) {
			_pixels[i] = color;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				_pixels[x + y * SIZE] = _sheet._pixels[(x + _x) + (y + _y) * _sheet.SIZE];
			}
		}
	}

	public static Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2, int animate, int time) {
		int calc = animate % time;
		int diff = time / 3;

		if (calc < diff) {
			return normal;
		}

		if (calc < diff * 2) {
			return x1;
		}

		return x2;
	}

	public static Sprite movingSprite(Sprite[] sprite, int animate, int time) {
		int calc = animate % time;
		double diff = time / 3;
		for (int i = 0; i < sprite.length; i++) {
			if (calc < diff * (i + 1)) {
				return sprite[i];
			}
		}
		return sprite[sprite.length - 1];
	}

	public static Sprite movingSprite(Sprite x1, Sprite x2, int animate, int time) {
		int diff = time / 2;
		return (animate % time > diff) ? x1 : x2;
	}

	public int getSize() {
		return SIZE;
	}

	public int getPixel(int i) {
		return _pixels[i];
	}

	public Image getFxImage() {
		WritableImage wr = new WritableImage(SIZE, SIZE);
		PixelWriter pw = wr.getPixelWriter();
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				if (_pixels[x + y * SIZE] == TRANSPARENT_COLOR) {
					pw.setArgb(x, y, 0);
				} else {
					pw.setArgb(x, y, _pixels[x + y * SIZE]);
				}
			}
		}
		Image input = new ImageView(wr).getImage();
		return resample(input, SCALED_SIZE / DEFAULT_SIZE);
	}

	private Image resample(Image input, int scaleFactor) {
		final int W = (int) input.getWidth();
		final int H = (int) input.getHeight();
		final int S = scaleFactor;

		WritableImage output = new WritableImage(
				W * S,
				H * S);

		PixelReader reader = input.getPixelReader();
		PixelWriter writer = output.getPixelWriter();

		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				final int argb = reader.getArgb(x, y);
				for (int dy = 0; dy < S; dy++) {
					for (int dx = 0; dx < S; dx++) {
						writer.setArgb(x * S + dx, y * S + dy, argb);
					}
				}
			}
		}

		return output;
	}
}
