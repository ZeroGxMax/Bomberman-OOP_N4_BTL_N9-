package media;

import constants.Constants;

import java.util.LinkedList;

public class SoundController {
    public static final String[] MUSIC_PATH = {
            "Area0.wav",
            "Area1.wav"
    };
    public static final String[] SOUND_PATH = {
            "Die.wav",
            "EnemyDie.wav",
            "EnemyDie1.wav",
            "EnemyDie2.wav",
            "Explosion.wav",
            "GameOver.wav",
            "ItemAppears.wav",
            "Victory.wav"
    };
    private static final LinkedList<Sound> soundsList = new LinkedList<>();
    private static final LinkedList<Sound> musicList = new LinkedList<>();

    public static void init() {
        for (String i : MUSIC_PATH) {
            addMusic(Constants.SOUND_PATH + i);
        }
        for (String i : SOUND_PATH) {
            addSound(Constants.SOUND_PATH + i);
        }
    }

    private static void addMusic(String s) {
        try {
            Sound t = new Sound(s, true);
            for (Sound i : musicList) {
                if (t.equals(i)) {
                    return;
                }
            }
            musicList.add(t);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void playMusic(int index) {
        if (index < 0 || index >= musicList.size()) {
            return;
        }
        Sound s = musicList.get(index);
        if (!s.isPlaying()) {
            s.play(0.4);
        }
        for (int i = 0; i < musicList.size(); i++) {
            if (i != index) {
                musicList.get(i).stop();
            }
        }
    }

    private static void addSound(String s) {
        try {
            Sound t = new Sound(s);
            for (Sound i : soundsList) {
                if (t.equals(i)) {
                    return;
                }
            }
            soundsList.add(t);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void playSound(int index) {
        if (index < 0 || index >= soundsList.size()) {
            return;
        }
        Sound s = soundsList.get(index);
        if (!s.isPlaying()) {
            s.play(1);
        }
    }

    public static void stop() {
        for (Sound i : musicList) {
            i.stop();
        }
    }
}
