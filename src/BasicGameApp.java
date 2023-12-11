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
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

//*******************************************************************************
// Class Definition Section


public class BasicGameApp implements Runnable {

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
	public boolean isRunning;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Fish fish;

	private Fish fish2;
	private Fish fish3;

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
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		fishPic = Toolkit.getDefaultToolkit().getImage("animated fish.png"); //load the picture
		fish = new Fish((int)(Math.random()*940),(int)(Math.random()*700));
		fishPic2 = Toolkit.getDefaultToolkit().getImage("animated fish2.png"); //load the picture
		fish2 = new Fish((int)(Math.random()*940),(int)(Math.random()*700));
		background = Toolkit.getDefaultToolkit().getImage("aquarium background.jpeg"); //load the picture
		fishPic3 = Toolkit.getDefaultToolkit().getImage("animated fish3.png");
		fish3 = new Fish((int)(Math.random()*940),(int)(Math.random()*700));

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

		if(fish.rec.intersects(fish2.rec) && fish.isCrashing == false){
			System.out.println("Crash");
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

		if(fish.width >= 200 || fish2.width >= 200 || fish3.width >= 200){
			System.out.println("Fish 1 Wins!");
			System.exit(0);
		}

		if(fish.width < 40){

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

		g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);

      //draw the image of the astronaut
		g.drawImage(fishPic3, fish3.xpos, fish3.ypos, fish3.width, fish3.height, null);
		g.drawImage(fishPic2, fish2.xpos, fish2.ypos, fish2.width, fish2.height, null);
		g.drawImage(fishPic, fish.xpos, fish.ypos, fish.width, fish.height, null);

		g.dispose();

		bufferStrategy.show();
	}
}