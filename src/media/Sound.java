package media;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.File;

public class Sound {
    public static Boolean mute = false;
    private final String path;
    private AudioClip sound;
    private boolean loop = false;

    public Sound(String path) {
        this.path = path;
        sound = chooseSound(path);
    }

    public Sound(String _path, boolean _loop) {
        this.path = _path;
        sound = chooseSound(_path);
        setLoop(_loop);
    }

    public static AudioClip chooseSound(String path) {
        File file = new File(path);
        Media media = new Media(file.toURI().toString());
        AudioClip audioClip = new AudioClip(media.getSource());
        return audioClip;
    }

    public boolean isPlaying() {
        return sound.isPlaying();
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
        if (loop) {
            sound.setCycleCount(AudioClip.INDEFINITE);
        } else {
            sound.setCycleCount(1);
        }
    }

    public void update() {
        if (mute) {
            sound.stop();
        } else {
            sound.play();
        }
    }

    public void play(String path) {
        if (!mute) {
            sound.stop();
            sound = chooseSound(path);
            if (loop) {
                sound.setCycleCount(1000);
            }
            sound.play(0.5);
        }
    }

    public void play(double volume) {
        if (!mute) {
            sound.play(volume);
        }
    }

    public void stop() {
        sound.stop();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sound) {
            Sound s = (Sound) obj;
            return path.equals(s.path);
        }
        return false;
    }

}
