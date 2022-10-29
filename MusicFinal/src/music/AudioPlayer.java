package music;

import java.util.Map;

public class AudioPlayer extends Thread {

	private Album album;
	private Playlist list;
	private int i;//helps keep track of what is being played in either an album or a playlist
	private Thread t1;// allows for the code to separate the playing of audio and executing the code

	// overloaded constructor #1
	public AudioPlayer(Album album) {
		super();
		this.album = album;
	}

	// overloaded constructor #2
	public AudioPlayer(Playlist list) {
		super();
		this.list = list;
	}

	// getters and setters
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Playlist getList() {
		return list;
	}

	public void setList(Playlist list) {
		this.list = list;
	}

	public Thread getThread() {
		return t1;
	}

	public void setThread(Thread t1) {
		this.t1 = t1;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	// allows an entire album or playlist to be played
	@Override
	public void run() {
		i=0;
		if (album != null) {//if the list is an album, use this for loop
			for (Map.Entry<Integer, Song> e : album.getTrackList().entrySet()) {
				try {
					t1 = new Thread(e.getValue());//makes the thread the song
					t1.setName(e.getValue().getTitle());//sets the name of the thread to the name of the given song
					t1.start();//starts the individual song to be playing
					AudioPlayer.sleep(e.getValue().getDuration() * 1000);//stops the for loop for the duration of the song
				} catch (Exception r) {// if an exception occurs(it gets purposely interrupted) the song stops and the
										// for loop also stops
					System.out.println(e.getValue().getTitle() + " has been stopped");
					t1.interrupt();
					break;
				}
				i++;
			}
		} else {//if the list is a Playlist, use this for loop
			for (Song s : list.getPlaylist()) {
				try {
					t1 = new Thread(s);
					t1.setName(s.getTitle());
					t1.start();
					Thread.sleep(s.getDuration() * 1000);
				} catch (Exception r) {
					System.out.println(s.getTitle() + " has been stopped");
					t1.interrupt();
					break;
				}
				i++;
			}
		}
	}

}