package music;

import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		Scanner scan = new Scanner(System.in);
		Album rumors = createRumors();
		Album hotelCalifornia = createHotelCalifornia();
		Album abbeyRoad = createAbbyRoad();
		Album ziggyStardust = createZiggyStardust();
		Playlist hits = new Playlist("Greatest Hits");
		AudioPlayer player = null;// allows songs to be played by using the AudioPlayer Class

		// creating hits playlist
		hits.addSong(rumors, 7);
		hits.addSong(rumors, 4);
		hits.addSong(rumors, 5);
		hits.addSong(rumors, 2);
		hits.addSong(hotelCalifornia, 1);
		hits.addSong(hotelCalifornia, 3);
		hits.addSong(hotelCalifornia, 2);
		hits.addSong(abbeyRoad, 1);
		hits.addSong(abbeyRoad, 7);
		hits.addSong(ziggyStardust, 3);
		hits.addSong(ziggyStardust, 4);
		hits.remove(7);

		int menu = 1;// variable to keep track of the main menu
		System.out.println("Welcome to ");
		while (menu != 0) {// as long as the menu variable is not 0, the menu keeps running
			System.out.println("Main Menu:\n1 Albums\n2 Playlists\n0 Exit");
			menu = scan.nextInt();// get an input from the user as to which menu they would like to access

			if (menu == 1) {// view Albums menu
				int bigAMenu = 1;// the variable to keep track of the inside menu

				while (bigAMenu != 0) {// keeps the album menu running until the user decides to leave
					System.out.println("Album Menu:\n1 View Albums\n2 Listen to Albums\n3 Stop Audio\n0 Exit");
					bigAMenu = scan.nextInt();
					int aMenu = 1;// variable to keep tracks of the next level of menus
					if (bigAMenu == 1) {// the user has chosen to view the albums
						while (aMenu != 0) {// allows user to view the albums one after another
							System.out.println("View Albums:\n1 Rumors\n2 Hotel California"
									+ "\n3 Abby Road\n4 The Rise and Fall of Ziggy Stardust and the Spiders from Mars \n0 Exit");
							aMenu = scan.nextInt();

							while (aMenu < 0 || aMenu > 4) {// catches all invalid inputs
								System.out.println("Invalid input. Try again");
								aMenu = scan.nextInt();
							}
							if (aMenu == 1) {// the user wants to see rumors
								System.out.println(rumors);
							} else if (aMenu == 2) {// the user wants to see hotel California
								System.out.println(hotelCalifornia);
							} else if (aMenu == 3) {// the user wants to see abbey road
								System.out.println(abbeyRoad);
							} else if (aMenu == 4) {// the user wants to see The Rise and Fall of Ziggy Stardust and the
													// Spiders from Mars
								System.out.println(ziggyStardust);
							} else {// the user chose to exit so they are returning to the album menu
								System.out.println("Returning to...");
							}
						}
					} else if (bigAMenu == 2) {// the user has chosen to listen to the albums
						while (aMenu != 0) {
							System.out.println("Listen to Albums:\n1 Rumors\n2 Hotel California"
									+ "\n3 Abby Road\n4 The Rise and Fall of Ziggy Stardust and the Spiders from Mars \n0 Exit");
							aMenu = scan.nextInt();

							while (aMenu < 0 || aMenu > 4) {// catches invalid inputs
								System.out.println("Invalid input. Try again");
								aMenu = scan.nextInt();
							}
							if (aMenu == 1) {// listen to rumors
								player = new AudioPlayer(rumors);
								player.start();
							} else if (aMenu == 2) {// listen to hotel california
								player = new AudioPlayer(hotelCalifornia);
								player.start();
							} else if (aMenu == 3) {// listen to abbey road
								player = new AudioPlayer(abbeyRoad);
								player.start();
							} else if (aMenu == 4) {// listen to The Rise and Fall of Ziggy Stardust and the Spiders
													// from Mars
								player = new AudioPlayer(ziggyStardust);
								player.start();
							} else {// the user has chosen to exit and is now being returned to the album menu
								System.out.println("Returning to...");
							}
						}
					} else if (bigAMenu == 3) {// user has chosen to stop all audio being played
						player.interrupt();// wakes the AudioPlayer thread which causes it to throw an exception which
											// then in turn allows for all the songs to be stopped
					} else {// user has chosen to exit and is now returning to the main menu
						System.out.println("Returning to...");
					}
				}
			} else if (menu == 2) {// playlists menu
				int pMenu = 1;// keeps track of this inner menu
				while (pMenu != 0) {
					System.out.println("Playlists:\n1 View Playlist\n2 Listen to Playlist\n3 Stop Audio\n4 Reorder"
							+ "\n5 Currently Playing\n6 Up Next\n0 Exit");
					pMenu = scan.nextInt();

					while (pMenu < 0 || pMenu > 6) {// catches invalid inputs
						System.out.println("Invalid Input. Try again.");
						pMenu = scan.nextInt();
					}
					if (pMenu == 1) {// user wants to view the hits playlist
						System.out.println(hits);
					} else if (pMenu == 2) {// user wants to listen to the hits playlist
						player = new AudioPlayer(hits);
						player.start();// starts the run method in AudioPlayer
					} else if (pMenu == 3) {// user wants to stop the audio
						player.interrupt();// wakes the thread from AudioPlayer
					} else if (pMenu == 4) {// user wants to reorder the hits playlist
						int reorder = 1;
						while (reorder != 0) {
							System.out.println("Reordering Menu:\n1 Shuffle\n2 Sort by Duration\n0 Exit");
							reorder = scan.nextInt();
							while (reorder < 0 || reorder > 2) {// catches invalid inputs
								System.out.println("Invalid input. Try again.");
								reorder = scan.nextInt();
							}
							if (reorder == 1) {// randomly shuffles the hits playlist
								Collections.shuffle(hits.getPlaylist());
								System.out.println(hits);
							} else if (reorder == 2) {// sorts the hits playlist based on duration
								Collections.sort(hits.getPlaylist());
								System.out.println(hits);
							} else {// user wants to return to playlist menu
								System.out.println("Returning to...");
							}
						}
					} else if (pMenu == 5) {
						System.out.println("Currently Playing: " + hits.displayCurrent(player.getI()));
					} else if (pMenu == 6) {
						System.out.println("Next up is: " + hits.displayNext(player.getI()));
					} else {// user wants to return to main menu
						System.out.println("Returning to...");
					}

				}

			} else {// user is ready to end the program
				System.out.println("Goodbye.");
				scan.close();
				System.exit(0);
			}
		}

	}

	public Album createRumors() {// create the Rumors album
		Album Rumors = new Album("Fleetwood Mac", "Rumors", "Rock");
		Rumors.addSong(new Song("Second Hand News", 177, "\\audio\\Rumors\\Second Hand News.wav"));
		Rumors.addSong(new Song("Dreams", 258, "\\audio\\Rumors\\Dreams.wav"));
		Rumors.addSong(new Song("Never Going Back Again", 135, "\\audio\\Rumors\\Never Going Back Again.wav"));
		Rumors.addSong(new Song("Don't Stop", 194, "\\audio\\Rumors\\Don't Stop.wav"));
		Rumors.addSong(new Song("Go Your Own Way", 224, "\\audio\\Rumors\\Go Your Own Way.wav"));
		Rumors.addSong(new Song("Songbird", 200, "\\audio\\Rumors\\Songbird.wav"));
		Rumors.addSong(new Song("The Chain", 271, "\\audio\\Rumors\\The Chain.wav"));
		Rumors.addSong(new Song("You Make Loving Fun", 216, "\\audio\\Rumors\\You Make Loving Fun.wav"));
		Rumors.addSong(new Song("I Don't Want to Know", 197, "\\audio\\Rumors\\I Don't Want to Know.wav"));
		Rumors.addSong(new Song("Oh Daddy", 237, "\\audio\\Rumors\\Oh Daddy.wav"));
		Rumors.addSong(new Song("Gold Dust Woman", 296, "\\audio\\Rumors\\Gold Dust Woman.wav"));
		Rumors.addSong(new Song("Silver Springs", 289, "\\audio\\Rumors\\Silver Springs.wav"));
		return Rumors;
	}

	public Album createHotelCalifornia() {// creates the Hotel California Album
		Album Hotel = new Album("Eagles", "Hotel California", "Classic Rock");
		Hotel.addSong(new Song("Hotel California", 391, "\\audio\\Hotel California\\Hotel California.wav"));
		Hotel.addSong(new Song("New Kid in Town", 305, "\\audio\\Hotel California\\New Kid in Town.wav"));
		Hotel.addSong(new Song("Life in the Fast Lane", 286, "\\audio\\Hotel California\\Life in the Fast Lane.wav"));
		Hotel.addSong(new Song("Wasted Time", 297, "\\audio\\Hotel California\\Wasted Time.wav"));
		Hotel.addSong(new Song("Wasted Time(Reprise)", 83, "\\audio\\Hotel California\\Wasted Time (Reprise).wav"));
		Hotel.addSong(new Song("Victim of Love", 250, "\\audio\\Hotel California\\Victim of Love.wav"));
		Hotel.addSong(
				new Song("Pretty Maids All in a Row", 240, "\\audio\\Hotel California\\Pretty Maids All in a Row.wav"));
		Hotel.addSong(new Song("Try and Love Again", 312, "\\audio\\Hotel California\\Try and Love Again.wav"));
		Hotel.addSong(new Song("The Last Resort", 445, "\\audio\\Hotel California\\The Last Resort.wav"));
		return Hotel;
	}

	public Album createAbbyRoad() {// creates the Abbey Road album
		Album Abbey = new Album("The Beatles", "Abbey Road", "Classic Rock");
		Abbey.addSong(new Song("Come Together", 260, "\\audio\\Abbey Road\\Come Together.wav"));
		Abbey.addSong(new Song("Something", 183, "\\audio\\Abbey Road\\Something.wav"));
		Abbey.addSong(new Song("Maxwell's Silver Hammer", 208, "\\audio\\Abbey Road\\Maxwell's Silver Hammer.wav"));
		Abbey.addSong(new Song("Oh! Darling", 208, "\\audio\\Abbey Road\\Oh! Darling.wav"));
		Abbey.addSong(new Song("Octopus's Garden", 171, "\\audio\\Abbey Road\\Octopus's Garden.wav"));
		Abbey.addSong(
				new Song("I Want You (She's So Heavy)", 468, "\\audio\\Abbey Road\\I Want You (She's So Heavy).wav"));
		Abbey.addSong(new Song("Here Comes The Sun", 186, "\\audio\\Abbey Road\\Here Comes The Sun.wav"));
		Abbey.addSong(new Song("Because", 166, "\\audio\\Abbey Road\\Because.wav"));
		Abbey.addSong(
				new Song("You Never Give Me Your Money", 243, "\\audio\\Abbey Road\\You Never Give Me Your Money.wav"));
		Abbey.addSong(new Song("Sun King", 147, "\\audio\\Abbey Road\\Sun King.wav"));
		Abbey.addSong(new Song("Mean Mr Mustard", 67, "\\audio\\Abbey Road\\Mean Mr Mustard.wav"));
		Abbey.addSong(new Song("Polythene Pam", 73, "\\audio\\Abbey Road\\Polythene Pam.wav"));
		Abbey.addSong(new Song("She Came In Through The Bathroom Window", 119,
				"\\audio\\Abbey Road\\She Came In Through The Bathroom Window.wav"));
		Abbey.addSong(new Song("Golden Slumbers", 92, "\\audio\\Abbey Road\\Golden Slumbers.wav"));
		Abbey.addSong(new Song("Carry That Weight", 97, "\\audio\\Abbey Road\\Carry That Weight.wav"));
		Abbey.addSong(new Song("The End", 142, "\\audio\\Abbey Road\\The End.wav"));
		Abbey.addSong(new Song("Her Majesty", 26, "\\audio\\Abbey Road\\Her Majesty.wav"));
		return Abbey;
	}

	public Album createZiggyStardust() {// creates the Rise and Fall of Ziggy Stardust and the Spiders from Mars album
		Album Ziggy = new Album("David Bowie", "The Rise and Fall of Ziggy Stardust and the Spiders from Mars",
				"Glam Rock");
		Ziggy.addSong(new Song("Five Years", 284, "\\audio\\Ziggy Stardust\\Five Years.wav"));
		Ziggy.addSong(new Song("Soul Love", 215, "\\audio\\Ziggy Stardust\\Soul Love.wav"));
		Ziggy.addSong(new Song("Moonage Daydream", 280, "\\audio\\Ziggy Stardust\\Moonage Daydream.wav"));
		Ziggy.addSong(new Song("Starman", 255, "\\audio\\Ziggy Stardust\\Starman.wav"));
		Ziggy.addSong(new Song("It Ain't Easy", 178, "\\audio\\Ziggy Stardust\\It Ain't Easy.wav"));
		Ziggy.addSong(new Song("Lady Stardust", 202, "\\audio\\Ziggy Stardust\\Lady Stardust.wav"));
		Ziggy.addSong(new Song("Star", 168, "\\audio\\Ziggy Stardust\\Star.wav"));
		Ziggy.addSong(new Song("Hang on to Yourself", 160, "\\audio\\Ziggy Stardust\\Hang on to Yourself.wav"));
		Ziggy.addSong(new Song("Ziggy Stardust", 194, "\\audio\\Ziggy Stardust\\Ziggy Stardust.wav"));
		Ziggy.addSong(new Song("Suffragette City", 209, "\\audio\\Ziggy Stardust\\Suffragette City.wav"));
		Ziggy.addSong(new Song("Rock 'n' Roll Suicide", 179, "\\audio\\Ziggy Stardust\\Rock 'n' Roll Suicide.wav"));
		return Ziggy;
	}

}