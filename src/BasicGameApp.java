//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************
// Class Definition Section


public class BasicGameApp implements Runnable, KeyListener {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image fishPic;
	public Image fishPic2;

	public Image fishPic3;
	public Image background;
	public Image winscreen;
	public boolean isRunning;
	public boolean isWinning;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Fish fish;

	private Fish fish2;
	private Fish fish3;
	public Fish[] fishes = new Fish[10];

   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       isWinning = false;
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		fishPic = Toolkit.getDefaultToolkit().getImage("animated fish.png"); //load the picture
		fish = new Fish((int)(450),(int)(500));
		fish.isControlled = true;
		fishPic2 = Toolkit.getDefaultToolkit().getImage("animated fish2.png"); //load the picture
		fish2 = new Fish((int)(Math.random()*940),(int)(Math.random()*700-100));
		background = Toolkit.getDefaultToolkit().getImage("aquarium background.jpeg"); //load the picture
		fishPic3 = Toolkit.getDefaultToolkit().getImage("animated fish3.png");
		fish3 = new Fish((int)(Math.random()*940),(int)(Math.random()*700-100));
		winscreen = Toolkit.getDefaultToolkit().getImage("win screen.jpeg");

		for(int z = 0; z < fishes.length; z++){
			fishes[z] = new Fish((int)(Math.random()*940),(int)(Math.random()*700-100));
		}


	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects
		for(int x = 0; x < fishes.length; x++){
			fishes[x].bounce();
		}

		if(fish.isWrapping){
			fish.wrap();
		} else {
			fish.bounce();
		}

		if(fish2.isWrapping){
			fish2.wrap();
		} else {
			fish2.bounce();
		}

		if(fish3.isWrapping){
			fish3.wrap();
		} else{
			fish3.bounce();
		}

		for(int g = 0; g < fishes.length; g++){
				if(fishes[g].rec.intersects(fishes[g].rec))
					fishes[g].dx = -fishes[g].dx;
				    fishes[g].dy = -fishes[g].dy;
		}

		for(int x = 0; x < fishes.length; x++){
			if(fish.rec.intersects(fishes[x].rec) && fish.isCrashing == false){
				if(fish.dx > fishes[x].dx){
					fish.dx = -fish.dx + 5;
					fish.dy = -fish.dy + 5;
					fish.width = fish.width + 10;
					fish.height = fish.height + 10;
					fishes[x].dx = -fishes[x].dx - 5;
					fishes[x].dy = -fishes[x].dy - 5;
					fishes[x].width = fishes[x].width - 10;
					fishes[x].height = fishes[x].height - 10;
				}

				if(fish.dx < fishes[x].dx){
					fish.dx = -fish.dx - 5;
					fish.dy = -fish.dy - 5;
					fish.width = fish.width - 10;
					fish.height = fish.height - 10;
					fishes[x].dx = -fishes[x].dx + 5;
					fishes[x].dy = -fishes[x].dy + 5;
					fishes[x].width = fishes[x].width + 10;
					fishes[x].height = fishes[x].height + 10;
				}
				fish.isCrashing = true;
				if(fish.isWrapping == false){
					fish.wrap();
					fish.isWrapping = true;
				}
				else{
					fish.isWrapping = false;
					fish.bounce();

				}
				if(fishes[x].isWrapping == false){
					fishes[x].wrap();
					fishes[x].isWrapping = true;
				}
				else{
					fishes[x].isWrapping = false;
					fishes[x].bounce();

				}
			}
		}

		if(fish.rec.intersects(fish2.rec) && fish.isCrashing == false){
			if(fish.dx > fish2.dx){
				fish.dx = -fish.dx + 5;
				fish.dy = -fish.dy + 5;
				fish.width = fish.width + 10;
				fish.height = fish.height + 10;
				fish2.dx = -fish2.dx - 5;
				fish2.dy = -fish2.dy - 5;
				fish2.width = fish2.width - 10;
				fish2.height = fish2.height - 10;
			}

			if(fish.dx < fish2.dx){
				fish.dx = -fish.dx - 5;
				fish.dy = -fish.dy - 5;
				fish.width = fish.width - 10;
				fish.height = fish.height - 10;
				fish2.dx = -fish2.dx + 5;
				fish2.dy = -fish2.dy + 5;
				fish2.width = fish2.width + 10;
				fish2.height = fish2.height + 10;
			}

			fish.isCrashing = true;
			if(fish.isWrapping == false){
				fish.wrap();
				fish.isWrapping = true;
			}
			else{
				fish.isWrapping = false;
				fish.bounce();

			}
			if(fish2.isWrapping == false){
				fish2.wrap();
				fish2.isWrapping = true;
			}
			else{
				fish2.isWrapping = false;
				fish2.bounce();

			}
		}

		if(fish.rec.intersects(fish2.rec) == false){
			fish.isCrashing = false;
		}

		if(fish.rec.intersects(fish3.rec) && fish.isCrashing == false){
			System.out.println("Crash");
			if(fish.dx > fish3.dx){
				fish.dx = -fish.dx + 5;
				fish.dy = -fish.dy + 5;
				fish.width = fish.width + 10;
				fish.height = fish.height + 10;
				fish3.dx = -fish3.dx - 5;
				fish3.dy = -fish3.dy - 5;
				fish3.width = fish3.width - 10;
				fish3.height = fish3.height - 10;
			}

			if(fish.dx < fish3.dx){
				fish.dx = -fish.dx - 5;
				fish.dy = -fish.dy - 5;
				fish.width = fish.width - 10;
				fish.height = fish.height - 10;
				fish3.dx = -fish3.dx + 5;
				fish3.dy = -fish3.dy + 5;
				fish3.width = fish3.width + 10;
				fish3.height = fish3.height + 10;
			}
			fish.isCrashing = true;
			if(fish.isWrapping == false){
				fish.wrap();
				fish.isWrapping = true;
			}
			else{
				fish.isWrapping = false;
				fish.bounce();

			}
			if(fish3.isWrapping == false){
				fish3.wrap();
				fish3.isWrapping = true;
			}
			else{
				fish3.isWrapping = false;
				fish3.bounce();

			}
		}

		if(fish.rec.intersects(fish3.rec) == false){
			fish.isCrashing = false;
		}

		if(fish2.rec.intersects(fish3.rec) && fish2.isCrashing == false){
			System.out.println("Crash");
			if(fish2.dx > fish3.dx){
				fish2.dx = -fish2.dx + 5;
				fish2.dy = -fish2.dy + 5;
				fish2.width = fish2.width + 10;
				fish2.height = fish2.height + 10;
				fish3.dx = -fish3.dx - 5;
				fish3.dy = -fish3.dy - 5;
				fish3.width = fish3.width - 10;
				fish3.height = fish3.height - 10;
			}

			if(fish2.dx < fish3.dx){
				fish2.dx = -fish2.dx - 5;
				fish2.dy = -fish2.dy - 5;
				fish2.width = fish2.width - 10;
				fish2.height = fish2.height - 10;
				fish3.dx = -fish3.dx + 5;
				fish3.dy = -fish3.dy + 5;
				fish3.width = fish3.width + 10;
				fish3.height = fish3.height + 10;
			}
			fish2.isCrashing = true;
			if(fish2.isWrapping == false){
				fish2.wrap();
				fish2.isWrapping = true;
			}
			else{
				fish2.isWrapping = false;
				fish2.bounce();

			}
			if(fish3.isWrapping == false){
				fish3.wrap();
				fish3.isWrapping = true;
			}
			else{
				fish3.isWrapping = false;
				fish3.bounce();

			}
		}

		if(fish2.rec.intersects(fish3.rec) == false){
			fish2.isCrashing = false;
		}

		if(fish.width >= 200 ){
			isWinning = true;
			System.out.println("Fish 1 Wins!");
		}

		if(fish2.width >= 200){
			isWinning = true;
			System.out.println("Fish 2 Wins!");
		}

		if(fish3.width >= 200){
			isWinning = true;
			System.out.println("Fish 3 Wins!");
		}

		if(fish.width < 40){
			fish.isAlive = false;
		}

		if(fish2.width < 40){
			fish2.isAlive = false;
		}

		if(fish3.width < 40){
			fish3.isAlive = false;
		}
	}


	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
	  canvas.addKeyListener(this);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

		if(isWinning == true){
			g.drawImage(winscreen, 0, 0, WIDTH, HEIGHT, null);
		}
		if(isWinning == false) {
			g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);

			//draw the image of the astronaut
			if (fish3.isAlive = true) {
				g.drawImage(fishPic3, fish3.xpos, fish3.ypos, fish3.width, fish3.height, null);
			}
			if (fish2.isAlive == true) {
				g.drawImage(fishPic2, fish2.xpos, fish2.ypos, fish2.width, fish2.height, null);
			}
			if (fish.isAlive == true) {
				g.drawImage(fishPic, fish.xpos, fish.ypos, fish.width, fish.height, null);
			}
			for(int x = 0; x < fishes.length; x++){
				g.drawImage(fishPic3, fishes[x].xpos, fishes[x].ypos, fishes[x].width, fishes[x].height, null);
			}
		}
		g.dispose();

		bufferStrategy.show();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if(e.getKeyCode() == 38){
			System.out.println("going up");
			fish.isNorth = true;
		}
		if(e.getKeyCode() == 39){
			System.out.println("going right");
			fish.isEast = true;
		}
		if(e.getKeyCode() == 37){
			System.out.println("going left");
			fish.isWest = true;
		}
		if(e.getKeyCode() == 40){
			System.out.println("going down");
			fish.isSouth = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 38){
			System.out.println("going up");
			fish.isNorth = false;
		}
		if(e.getKeyCode() == 40){
			System.out.println("going down");
			fish.isSouth = false;
		}
		if(e.getKeyCode() == 37){
			System.out.println("going left");
			fish.isWest = false;
		}
		if(e.getKeyCode() == 39){
			System.out.println("going right");
			fish.isEast = false;
		}
	}
}