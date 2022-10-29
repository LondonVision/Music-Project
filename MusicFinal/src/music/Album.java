package music;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Album implements Comparable<Album> {
	private String artist, title, genre;
	private HashMap<Integer, Song> trackList = new HashMap<Integer, Song>();
	private static int num;//this is the Integer that goes infront of the song in the tracklist
	static final DecimalFormat format = new DecimalFormat("00");//makes sure that the format of an integer has two digits

	//constructor
	public Album(String artist, String title, String genre) {
		super();
		this.artist = artist;
		this.title = title;
		this.genre = genre;
		num = 1;
	}

	//getters and setters
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public HashMap<Integer, Song> getTrackList() {
		return trackList;
	}

	public void setTrackList(HashMap<Integer, Song> trackList) {
		this.trackList = trackList;
	}

	//adds a song to the album
	public void addSong(Song song) {
		Integer i = num;
		trackList.put(i, song);
		num++;
	}

	//calculates the total duration of the album and returns it in a nice format
	public String totalDur() {
		int i = 0;
		for (Map.Entry<Integer, Song> e : trackList.entrySet()) {
			i += e.getValue().getDuration();
		}
		return i / 60 + ":" + format.format(i % 60);
	}

	//allows albums to be compared by artist's name
	@Override
	public int compareTo(Album a) {
		return getArtist().compareTo(a.getArtist());
	}

	//allows the album to display all important information when printed
	@Override
	public String toString() {
		return "Title: " + getTitle() + "\nArtist: " + getArtist() + "\nGenre: " + getGenre() + "\n"
				+ trackList.entrySet().stream().map(entry -> "#" + entry.getKey() + " | " + entry.getValue())
						.collect(Collectors.joining("\n", "", ""))
				+ "\nTotal Duration: " + totalDur();
	}
}