package droneSimulator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is the DroneInterface class. It is responsible for the JavaFX window and
 * all its features
 * 
 * @author Andreas Paridis
 *
 */

public class DroneInterface extends Application {
	private MyCanvas mc; // canvas to draw the objects
	private AnimationTimer timer; // timer for animating objects
	private VBox rtPane; // Vertical box for object info
	private DroneArena arena; // Arena that includes objects

	/**
	 * Shows information about the project
	 */
	private void showAbout() {
		timer.stop();
		Alert alert = new Alert(AlertType.INFORMATION); // box definition
		alert.setTitle("Game Info"); // window title
		alert.setHeaderText("MADE BY VJ017877"); // text title
		alert.setContentText(
				"Drone Simulation game using JavaFX. Extension of CW1 of the Java Module, University of reading."
						+ "\nMade by VJ017877"); // content
		alert.showAndWait(); // wait for use to close
		ButtonType a = alert.getResult(); // get button pressed
		if (a == ButtonType.OK) { // if hit okay
			timer.start(); // resume timer
		}
	}

	/**
	 * Shows tutorial of the game
	 */
	private void showTutorial() {
		timer.stop();
		Alert alert = new Alert(AlertType.INFORMATION); // box definition
		alert.setTitle("Game Tutorial"); // window title
		alert.setHeaderText("Droneium - Tutorial"); // text title
		alert.setContentText(
				"1. Chose your map\n2. Add your drones\n3. Save/Load Existing ones\n4. Load from pre existing ones\n4. Use your mouse to control the yellow block\n"
						+ "5. Assist the other drones in hitting the void in the middle"); // content
		alert.showAndWait(); // wait for use to close
		ButtonType a = alert.getResult(); // get button pressed
		if (a == ButtonType.OK) { // if hit okay
			timer.start(); // stop timer
		}
	}

	private void showTips() {
		timer.stop();
		Alert alert = new Alert(AlertType.INFORMATION); // box definition
		alert.setTitle("Tips"); // window title
		alert.setHeaderText("Tips & Tricks"); // text title
		alert.setContentText(
				"1. You can save/load your favourite arenas from the menu \n2. Don't waste your time on frenzy drones they're too fast to control\n"
						+ "3. Use the slow drones to beat the impossible level"); // content
		alert.showAndWait(); // wait for use to close
		ButtonType a = alert.getResult(); // get button pressed
		if (a == ButtonType.OK) { // if hit okay
			timer.start(); // stop timer
		}
	}

	/**
	 * Sets up mouse event. Whenever the user clicks on the canvas, place their
	 * playerDrone there
	 * 
	 * @param canvas
	 */
	public void setMouseEvents(Canvas canvas) {
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, // mouse press trigger
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						arena.setPlayer(e.getX(), e.getY()); // set player on cursor
					}
				});
	}

	/**
	 * This is the top menu bar with all its buttons
	 * 
	 * @return The menu bar
	 */
	MenuBar setMenu() {

		// Menu Creation
		MenuBar menuBar = new MenuBar(); // Creates main menu

		// File Tab & sub tabs
		Menu fileTab = new Menu("File"); // File tab
		MenuItem fileExit = new MenuItem("Exit Game"); // Exit sub tab
		fileExit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) { // When click on exit alert them
				timer.stop();
				Alert alert = new Alert(AlertType.CONFIRMATION); // box definition
				alert.setTitle("QUIT GAME"); // window title
				alert.setHeaderText("Are you sure you want to exit?"); // title
				alert.setContentText("All your unsaved progress will be lost"); // content
				alert.showAndWait(); // wait for use to close
				ButtonType a = alert.getResult();
				if (a == ButtonType.OK) {
					timer.stop(); // stop timer
					System.exit(0); // exit program
				} else {
					timer.start();
				}

			}
		});

		// Save File sub
		MenuItem fileSave = new MenuItem("Save Arena");
		fileSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timer.stop();
				if (!arena.allDrones.isEmpty()) {
					try {
						saveFile();
					} catch (Exception a) {

					}
				}else {
					timer.stop();
					Alert alert = new Alert(AlertType.ERROR); // box definition
					alert.setTitle("NO DRONES"); // window title
					alert.setHeaderText("No drones in arena"); // title
					alert.setContentText("Please add drones in the arena before saving"); // content
					alert.showAndWait(); // wait for use to close
				}

			}
		});

		// Load File sub
		MenuItem fileLoad = new MenuItem("Load Arena");
		fileLoad.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timer.stop();
				if (arena.allObstacles.isEmpty()) {
					timer.stop();
					Alert alert = new Alert(AlertType.ERROR); // box definition
					alert.setTitle("NO ARENA"); // window title
					alert.setHeaderText("No arena loaded"); // title
					alert.setContentText("Please select an arena before loading drones"); // content
					alert.showAndWait(); // wait for use to close
				} else {
					try {
						loadFile();
					} catch (Exception a) {

					}
				}
			}
		});

		fileTab.getItems().addAll(fileSave, fileLoad, fileExit); // add sub to main tab

		// Make your own tab
		Menu ownTab = new Menu("Make Your Own");

		// Clear Arena Entirely
		MenuItem ownClear = new MenuItem("Clear Arena");
		ownClear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timer.stop();
				Alert alert = new Alert(AlertType.CONFIRMATION); // box definition
				alert.setTitle("Clear Arena"); // window title
				alert.setHeaderText("Are you sure you want to change difficulties?"); // text title
				alert.setContentText("All your unsaved progress will be LOST"); // content
				alert.showAndWait(); // wait for use to close
				ButtonType a = alert.getResult();
				if (a == ButtonType.OK) {
					arena.clearArena();
					arena.drawArena(mc);
				} else {
					timer.start();
				}

			}
		});

		// Clear Drones
		MenuItem ownClearD = new MenuItem("Clear Drones");
		ownClearD.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timer.stop();
				Alert alert = new Alert(AlertType.CONFIRMATION); // box definition
				alert.setTitle("Clear Drones"); // window title
				alert.setHeaderText("Are you sure you want to remove all drones?");// text title
				alert.setContentText("All your unsaved progress will be LOST"); // content
				alert.showAndWait(); // wait for use to close
				ButtonType a = alert.getResult();
				if (a == ButtonType.OK) {
					arena.clearDrones();
					arena.drawArena(mc);
				} else {
					timer.start();
				}

			}

		});

		// Add frenzy Drone
		MenuItem ownAddE = new MenuItem("Add Frenzy Drone");
		ownAddE.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int a = arena.allDrones.size() + arena.allObstacles.size();
				if (a == 40) {
					timer.stop();
					Alert alert = new Alert(AlertType.ERROR); // box definition
					alert.setTitle("Arena Full"); // window title
					alert.setHeaderText("Arena is full"); // text title
					alert.setContentText("You can't add more drones"); // content
					alert.showAndWait(); // wait for use to close
					ButtonType c = alert.getResult();
					if (c == ButtonType.OK) {
						timer.start();
					}
				} else {
					if (arena.allObstacles.isEmpty()) {
						timer.stop();
						Alert alert = new Alert(AlertType.ERROR); // box definition
						alert.setTitle("No arena"); // window title
						alert.setHeaderText("Arena does not exist"); // text title
						alert.setContentText("Please select an arena fist"); // content
						alert.showAndWait(); // wait for use to close
					} else {
						arena.addFrenzyDrone();
					}
				}

			}
		});

		// Add Fly Drone
		MenuItem ownAddF = new MenuItem("Add Fly Drone");
		ownAddF.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int a = arena.allDrones.size() + arena.allObstacles.size();
				if (a == 40) {
					timer.stop();
					Alert alert = new Alert(AlertType.ERROR); // box definition
					alert.setTitle("Arena Full"); // window title
					alert.setHeaderText("Arena is full"); // text title
					alert.setContentText("You can't add more drones"); // content
					alert.showAndWait(); // wait for use to close
					ButtonType c = alert.getResult();
					if (c == ButtonType.OK) {
						timer.start();
					}
				} else {
					if (arena.allObstacles.isEmpty()) {
						timer.stop();
						Alert alert = new Alert(AlertType.ERROR); // box definition
						alert.setTitle("No arena"); // window title
						alert.setHeaderText("Arena does not exist"); // text title
						alert.setContentText("Please select an arena fist"); // content
						alert.showAndWait(); // wait for use to close
					} else {
						arena.addFlyDrone();
					}

				}
			}
		});

		ownTab.getItems().addAll(ownClear, ownClearD, ownAddE, ownAddF); // add sub to main tab

		// Help tab
		Menu helpTab = new Menu("Help"); // Help

		// About sub
		MenuItem helpAbout = new MenuItem("About");
		helpAbout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {
				showAbout(); // show about text
			}
		});

		// Tutorial Sub
		MenuItem helpTutorial = new MenuItem("Tutorial");
		helpTutorial.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showTutorial();

			}
		});

		// Tips sub
		MenuItem helpTips = new MenuItem("Tips");
		helpTips.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showTips();// show tips

			}

		});

		helpTab.getItems().addAll(helpAbout, helpTutorial, helpTips); // add sub to main tab

		// Difficulties
		Menu diffTab = new Menu("Premade Arenas");

		// Easy sub
		MenuItem ez = new MenuItem("Easy");
		ez.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				timer.stop();
				Alert alert = new Alert(AlertType.CONFIRMATION); // box
																	// definition
				alert.setTitle("Change difficulty"); // window title
				alert.setHeaderText("Are you sure you want to change difficulties?"); // title
				alert.setContentText("All your unsaved progress will be LOST"); // content
				alert.showAndWait(); // wait for use to close
				ButtonType a = alert.getResult();
				if (a == ButtonType.OK) {
					arena.clearArena();
					arena.drawArena(mc);
					arena.showDifficulty('e');
					arena.drawArena(mc);
				} else {
					timer.start();
				}
			}
		});

		// Med sub
		MenuItem med = new MenuItem("Medium");
		med.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				timer.stop();
				Alert alert = new Alert(AlertType.CONFIRMATION); // box definition
				alert.setTitle("Change difficulty"); // window title
				alert.setHeaderText("Are you sure you want to change difficulties?"); // text title
				alert.setContentText("All your unsaved progress will be LOST"); // content
				alert.showAndWait(); // wait for use to close
				ButtonType a = alert.getResult();
				if (a == ButtonType.OK) {
					arena.clearArena();
					arena.drawArena(mc);
					arena.showDifficulty('m');
					arena.drawArena(mc);
				} else {
					timer.start();
				}

			}
		});

		// Hard sub
		MenuItem hard = new MenuItem("Hard");
		hard.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				timer.stop();
				Alert alert = new Alert(AlertType.CONFIRMATION); // box definition
				alert.setTitle("Change difficulty"); // window title
				alert.setHeaderText("Are you sure you want to change difficulties?"); // text title
				alert.setContentText("All your unsaved progress will be LOST"); // content
				alert.showAndWait(); // wait for use to close
				ButtonType a = alert.getResult();
				if (a == ButtonType.OK) {
					arena.clearArena();
					arena.drawArena(mc);
					arena.showDifficulty('h');
					arena.drawArena(mc);
				} else {
					timer.start();

				}

			}
		});

		// Impossible sub
		MenuItem imp = new MenuItem("Impossible");
		imp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				timer.stop();
				Alert alert = new Alert(AlertType.CONFIRMATION); // box definition
				alert.setTitle("Change difficulty"); // window title
				alert.setHeaderText("Are you sure you want to change difficulties?"); // title
				alert.setContentText("All your unsaved progress will be LOST"); // content
				alert.showAndWait(); // wait for use to close
				ButtonType a = alert.getResult();
				if (a == ButtonType.OK) {
					arena.clearArena();
					arena.drawArena(mc);
					arena.showDifficulty('i');
					arena.drawArena(mc);
				} else {
					timer.start();
				}

			}
		});

		diffTab.getItems().addAll(ez, med, hard, imp); // add sub to main tab

		// Chose arena menu
		Menu arenaTab = new Menu("Chose Arena");

		// Easy arena sub
		MenuItem arenaE = new MenuItem("Easy");
		arenaE.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				timer.stop();
				Alert alert = new Alert(AlertType.CONFIRMATION); // box definition
				alert.setTitle("Change difficulty"); // window title
				alert.setHeaderText("Are you sure you want to change difficulties?"); // text title
				alert.setContentText("All your unsaved progress will be LOST"); // content
				alert.showAndWait(); // wait for use to close
				ButtonType a = alert.getResult();
				if (a == ButtonType.OK) {
					arena.clearArena();
					arena.showObstacles('e');
					arena.drawArena(mc);
				} else {
					timer.start();
				}

			}
		});

		// Medium arena sub
		MenuItem arenaM = new MenuItem("Medium");
		arenaM.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timer.stop();
				Alert alert = new Alert(AlertType.CONFIRMATION); // box definition
				alert.setTitle("Change difficulty"); // window title
				alert.setHeaderText("Are you sure you want to change difficulties?"); // text title
				alert.setContentText("All your unsaved progress will be LOST"); // content
				alert.showAndWait(); // wait for use to close
				ButtonType a = alert.getResult();
				if (a == ButtonType.OK) {
					arena.clearArena();
					arena.showObstacles('m');
					arena.drawArena(mc);
				} else {
					timer.start();
				}

			}
		});

		// Hard arena sub
		MenuItem arenaH = new MenuItem("Hard");
		arenaH.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timer.stop();
				Alert alert = new Alert(AlertType.CONFIRMATION); // box definition
				alert.setTitle("Change difficulty"); // window title
				alert.setHeaderText("Are you sure you want to change difficulties?"); // Title
				alert.setContentText("All your unsaved progress will be LOST"); // content
				alert.showAndWait(); // wait for use to close
				ButtonType a = alert.getResult();
				if (a == ButtonType.OK) {
					arena.clearArena();
					arena.showObstacles('h');
					arena.drawArena(mc);
				} else {
					timer.start();
				}

			}
		});

		// Impossible arena sub
		MenuItem arenaI = new MenuItem("Impossible");
		arenaI.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timer.stop();
				Alert alert = new Alert(AlertType.CONFIRMATION); // box
																	// definition
				alert.setTitle("Change difficulty"); // window title
				alert.setHeaderText("Are you sure you want to change difficulties?"); // Title
				alert.setContentText("All your unsaved progress will be LOST"); // content
				alert.showAndWait(); // wait for use to close
				ButtonType a = alert.getResult();
				if (a == ButtonType.OK) {
					arena.clearArena();
					arena.showObstacles('i');
					arena.drawArena(mc);
				} else {
					timer.start();
				}
			}
		});

		arenaTab.getItems().addAll(arenaE, arenaM, arenaH, arenaI); // add sub to main tab

		menuBar.getMenus().addAll(fileTab, helpTab, diffTab, arenaTab, ownTab); // Add all tabs to MENU bar

		return menuBar; // Show menu bar
	}

	/**
	 * Returns the difficulty the user chose
	 * 
	 * @return difficulty
	 */
	public char getDifficulty() {
		char a = 'd';
		return a;
	}

	/**
	 * Horizontal Box with the object information
	 * 
	 * @return the Box
	 */
	private HBox setButtons() {

		// Play button
		Button btnStart = new Button("Play");

		// action on press
		btnStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timer.start(); // start timer

			}
		});

		// Stop button
		Button btnStop = new Button("Pause");

		// action on press
		btnStop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timer.stop(); // and its action to stop the timer

			}
		});

		return new HBox(btnStart, btnStop); // all everything and return
	}

	/**
	 * Shows the text score at x,y
	 * 
	 * @param x coordinate of the drone
	 * @param y coordinate of the drone
	 * @param score of the drone
	 */
	public void showScore(double x, double y, int score) {
		mc.showText(x, y, Integer.toString(score)); // change to String so it can be displayed as text

	}

	/**
	 * Redraw the world
	 */
	public void drawWorld() {
		mc.clearCanvas(); // Clear the canvas
		arena.drawArena(mc);
	}

	/**
	 * Shows information on right pane
	 */
	public void drawStatus() {
		rtPane.getChildren().clear(); // Clears pane
		ArrayList<String> allInfo = arena.showStatus();
		for (String info : allInfo) {
			Label label = new Label(info); // Makes sting a label
			rtPane.getChildren().add(label); // Adds label
		}
	}

	/**
	 * This prompts the user to save their arena using JFileChooser
	 * 
	 * @throws IOException
	 */
	public void saveFile() throws IOException {

		// JFileChooser properties
		JFileChooser chooser = new JFileChooser("C:\\Users\\antre\\Desktop");// directory
		chooser.setDialogTitle("Save arena to: "); // Window title
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // What files are shown

		/**
		 * The following is a filter for the file chooser. It only displays directories
		 * and files with the .arena extensions that are created from saving an arena
		 */
		chooser.setFileFilter(new FileFilter() {
			public String getDescription() {
				return "Arena Files (*.arena)"; // only .arena
			}

			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					String fileName = f.getName().toLowerCase();
					return fileName.endsWith(".arena");
				}
			}
		});

		// this where the windows opens
		chooser.setApproveButtonText("Save");// changes approve button
		chooser.setApproveButtonToolTipText("Save location");
		int returnVal = chooser.showOpenDialog(null); // Changes for Save/Cancel

		// If user clicks save
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// gets file selected by user
			String userInput = chooser.getSelectedFile().getName();
			File userFile = new File(chooser.getCurrentDirectory() + "\\" + userInput + ".arena");

			// Saving Process
			FileWriter fileWriter = new FileWriter(userFile); // creates a new file writer

			BufferedWriter writer = new BufferedWriter(fileWriter); // adds to buffer

			// Each line store one drone in the form TYPE X Y
			for (Drone d : arena.allDrones) {
				if (d instanceof FlyDrone) {
					writer.write("1"); // Type
					writer.write(" ");
				}
				if (d instanceof FrenzyDrone) {
					writer.write("2");
					writer.write(" ");
				}
				writer.write(Double.toString(Math.round(d.getX()))); // X position
				writer.write(" ");
				writer.write(Double.toString(Math.round(d.getY()))); // Y position
				writer.newLine();
			}
			writer.close();
		}
	}

	/**
	 * This prompts the user to load their arena using JFileChooser
	 * 
	 * @throws IOException
	 */
	public void loadFile() throws IOException {
		// JFileChooser properties
		JFileChooser chooser = new JFileChooser("C:\\Users\\antre\\Desktop");// directory
		chooser.setDialogTitle("Load arena from: ");// Window title
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);// What files are shown

		// This will eventually store the file contents
		String fileContents = " ";
		/**
		 * The following is a filter for the file chooser. It only displays directories
		 * and files with the .arena extensions that are created from saving an arena
		 */
		chooser.setFileFilter(new FileFilter() {
			public String getDescription() {
				return "Arena Files (*.arena)";
			}

			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					String fileName = f.getName().toLowerCase();
					return fileName.endsWith(".arena");
				}
			}
		});
		// This where the windows opens
		int returnVal = chooser.showOpenDialog(null); // stores if user clicks open/cancel
		if (returnVal == JFileChooser.APPROVE_OPTION) {// if user presses open

			File userFile = chooser.getSelectedFile(); // gets the file selected by user

			if (chooser.getSelectedFile().isFile()) { // if the file exists

				// Clear the current drone list
				if (!arena.allDrones.isEmpty()) {
					arena.clearDrones();
				}
				// Loading process
				FileReader fileReader = new FileReader(userFile); // File reader
				BufferedReader reader = new BufferedReader(fileReader); // New Buffer to read from file

				while (fileContents != null) { // while not in the end of the file

					fileContents = reader.readLine(); // read next line

					String[] numbers = fileContents.split(" ");
					Double t = Double.parseDouble(numbers[0]);
					double x = Double.parseDouble(numbers[1]); // First integer is drone X coordinate
					double y = Double.parseDouble(numbers[2]); // Second integer is drone Y coordinate

					// Adds all drones to the arena
					if (t == 1) {
						arena.allDrones.add(new FlyDrone(x, y));
					}
					if (t == 2) {
						arena.allDrones.add(new FrenzyDrone(x, y));
					}
					arena.drawArena(mc);
					//

				}
				reader.close();
			} else {
				timer.stop();
				Alert alert = new Alert(AlertType.ERROR); // box definition
				alert.setTitle("Error"); // window title
				alert.setHeaderText("Something Went Wrong"); // title
				alert.setContentText("File is empty. Please try again"); // content
				alert.showAndWait(); // wait for use to close
			}
		}

	}

	void errors() {

	}

	/**
	 * Sets the primary stage. (Window)
	 * 
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("vj017877 drone simulation"); // Window Title
		primaryStage.setWidth(1200); // Y axis of the window
		primaryStage.setHeight(1000); // X axis of the window

		// Menu bar at top
		BorderPane topBar = new BorderPane();
		topBar.setPadding(new Insets(1, 1, 1, 1)); // Padding of the top bar
		topBar.setTop(setMenu()); // Calls & Places menu bar

		// Group including canvas
		Group root = new Group();
		Canvas canvas = new Canvas(1024, 768); // canvas dimensions
		root.getChildren().add(canvas); // adds canvas to group
		topBar.setLeft(root); // Place it on far left of window

		// Change colour of canvas
		StackPane holder = new StackPane();
		holder.getChildren().add(canvas);
		root.getChildren().add(holder); // adds pane above already existing
										// canvas
		holder.setStyle("-fx-background-color: gainsboro"); // colour change

		// New canvas creation
		mc = new MyCanvas(canvas.getGraphicsContext2D(), 1024, 768);

		arena = new DroneArena(1024, 768, 'k'); // Create new arena
		drawWorld();

		setMouseEvents(canvas); // Mouse events

		rtPane = new VBox(); // Create new Vbox on right Pane
		rtPane.setAlignment(Pos.TOP_LEFT); // Align text in on top left side
		rtPane.setPadding(new Insets(5, 75, 75, 5)); // Add Padding
		topBar.setRight(rtPane); // //Add the pane on the right side

		topBar.setBottom(setButtons()); // Create bottom pane with buttons

		Scene scene = new Scene(topBar, 700, 600); // Set scene
		topBar.prefHeightProperty().bind(scene.heightProperty());
		topBar.prefWidthProperty().bind(scene.widthProperty());

		primaryStage.setScene(scene); // Add scene to primary stage
		primaryStage.show(); // Shows primary stage

		timer = new AnimationTimer() { // Timer setup

			public void handle(long currentNanoTime) { // Action when timer is on
				arena.checkDrones(); // check the angle of all balls
				arena.adjustDrones(); // move all balls
				drawWorld(); // redraw the world
				drawStatus(); // indicate where balls are
			}

		};

		drawWorld();
		drawStatus();

	}

	/**
	 * Calls the launcher of the application
	 * 
	 * @param args all 
	 */
	public static void main(String[] args) {
		Application.launch(args); // GUI Launch

	}

}
