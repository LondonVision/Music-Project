package music;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.LinkedList;

public class Playlist {
	private String title; //title of any playlist object
	private LinkedList<Song> playlist = new LinkedList<Song>(); //where the songs in the playlist are stored
	private HashSet<String> compiledArtists = new HashSet<String>(); //where the artists in the playlist are stored but they are only added once
	static final DecimalFormat format = new DecimalFormat("00"); //makes sure that the format of an integer has two digits

	public Playlist(String title) { //Constructor - you have to add songs after creation
		super();
		this.title = title;
	}

	public String getTitle() { //allows for the getting of the playlist's title
		return title;
	}

	public void setTitle(String title) {//allows for the setting of the playlist's title
		this.title = title;
	}

	public LinkedList<Song> getPlaylist() {//allows for the getting of the playlist
		return playlist;
	}

	public void setPlaylist(LinkedList<Song> playlist) {//allows for the setting of the playlist
		this.playlist = playlist;
	}

	public void addSong(Album a, int t) { //allows for the adding of a given song to be added to the playlist
		compiledArtists.add(a.getArtist());
		playlist.add(a.getTrackList().get(t));
	}

	public void remove(int i) { //allows the removal of songs from the playlist as long as they are within the playlist's size
		if (i < 1 || i > playlist.size()) {
			System.out.println("Inalid Input. Nothing Removed");
		} else {//next line is purposely commented out. only useful if the user was removing items while code was running
//			System.out.println("Removed: " + playlist.get(i - 1).getTitle() + " from the playlist");
			playlist.remove(i - 1);
		}
	}

	public String displayCurrent(int i) {//returns the name of whatever song from the playlist is currently being played
		return playlist.get(i).getTitle();
	}
	
	public String displayNext(int i) {////returns the name of whatever song is next to be played
		if(i>=playlist.size()) {
			System.out.println("There is nothing next");
			return "";
		}
		else {
			return playlist.get(i+1).getTitle();
		}
	}
	
	public String displayArtists() { //returns all of the artists that are in the given playlist
		String string = "";
		for (String s : compiledArtists) {
			string += s + " | ";
		}
		return string;
	}

	public String displayTracks() { //returns all of the tracks that are in the playlist
		String string = "";
		int i = 1;
		for (Song s : playlist) {
			string += "#" + i + " | " + s + "\n";
			i++;
		}
		return string;
	}

	public String totalDur() { //returns the total duration of the playlist in a string format
		int i = 0;
		for (Song s : playlist) {
			i += s.getDuration();
		}
		return i / 60 + ":" + format.format(i % 60);
	}

	@Override
	public String toString() {//nicely presents all information regarding the playlist
		return "Title: " + title + "\nCompiled Artists: " + displayArtists() + "\nTracks:\n" + displayTracks()
				+ "Total Duration: " + totalDur();
	}

}