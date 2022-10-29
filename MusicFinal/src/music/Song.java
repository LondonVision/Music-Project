package music;

import java.io.File;
import java.text.DecimalFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Song implements Comparable<Song>, Runnable {
	private String title;
	private int duration;
	private String path;//where the file is stored
	private File sound;//the actual file
	private Clip c;//the sound of the file
	static final DecimalFormat format = new DecimalFormat("00");//makes sure that the format of an integer has two digits

	//Constructor
	public Song(String title, int duration, String path) {
		super();
		this.title = title;
		this.duration = duration;
		this.path = new File("").getAbsolutePath() + path;
		sound = new File(this.path);
	}

	//getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public String getDurationStr() {//gets the total duration of the song in a nice format
		return getDuration() / 60 + ":" + format.format(getDuration() % 60);
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public File getSound() {
		return sound;
	}

	public void setSound(File sound) {
		this.sound = sound;
	}


	//allows for the playing of a given file by converting it into a clip and then making use of AudioInputStream
	@Override
	public void run() {
		System.out.println("Now playing " + getTitle());
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(getSound());
			c = AudioSystem.getClip();
			c.open(ais); // Clip opens AudioInputStream
			c.start(); // Start playing audio
			Thread.sleep((int) (c.getMicrosecondLength() * 0.001));// sleep thread for length of the song
		} catch (Exception e) {
			c.stop();//if there is an exception, stop the clip
		}
	}

	//allows for the comparing of songs based on their duration
	@Override
	public int compareTo(Song song) {
		if (song.getDuration() > getDuration())
			return -1;
		else if (song.getDuration() < getDuration())
			return 1;

		return 0;
	}

	//nicely presents the information about a song
	@Override
	public String toString() {
		return "Name: " + getTitle() + " | Duration: " + getDurationStr();
	}

}