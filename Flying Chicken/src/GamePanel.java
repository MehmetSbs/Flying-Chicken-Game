package src;


import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.awt.RenderingHints;

public class GamePanel extends JPanel implements Runnable {

	private int screenWidth = 900;
    private int screenHeight = 650;

	private int direction = 1;

	final int FPS = 240;
	Thread gameThread;
	private Background background;
	private Actor actor;
	private Chicken chicken;

	private ArrayList<Corn> cornArray;
	private ArrayList<Cat> catArray;
	private ArrayList<Egg> eggArray;

	private BufferedImage cornTexture;
	private BufferedImage catTexture;
	private BufferedImage eggTexture;
	private BufferedImage startTexture;
	private BufferedImage winTexture;
	private BufferedImage failTexture;
	

	long lastCornSpawnTime = 0;
	long lastCatSpawnTime = 0;
	long lastEggSpawnTime = 0;

	String currentLevel;
	String exlevel;
	private int score = 0;
	int firstLevelScore = 250;
	int secondLevelScore = 350;
	int thirdLevelScore = 500;

	boolean secondLevelLocked = true;
	boolean thirdLevelLocked = true;

	boolean chickenAlive = true;
	
	Listener listener = new Listener();
	KeyHandler keyListener = new KeyHandler();
	MenuPanel menuPanel;

	Font scoreFont;
	
    public GamePanel () {
		
		actor = new Actor();
		chicken = new Chicken();
		cornArray = new ArrayList<Corn>();
		catArray = new ArrayList<Cat>();
		eggArray = new ArrayList<Egg>();
		background = new Background();
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.cyan);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyListener);
		this.setFocusable(true);
		cornTexture = actor.getTexture("../assets/corn.png");
		eggTexture = actor.getTexture("../assets/egg.png");
		catTexture = actor.getTexture("../assets/cat.png");
		startTexture = actor.getTexture("../assets/start.png");
		winTexture = actor.getTexture("../assets/win.png");
		failTexture = actor.getTexture("../assets/fail.png");
		currentLevel =  "startMenu";
		scoreFont = new Font("Purisa", Font.TYPE1_FONT, 50);


		


	}

	public void startGameThread(){

		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run(){

		long lastTime = System.nanoTime();


		while (gameThread != null) {

			if (System.nanoTime() - lastTime > 1000000000 / FPS) {

				repaint();
				update();
				
				lastTime = System.nanoTime();
				
			}

		}

		

	}

	private void update(){

		level1Clicked();
		level2Clicked();
		level3Clicked();
		restartClicked();
		//exitClicked();

		if (score >= firstLevelScore) {
			secondLevelLocked = false;

		}
		if (score >= secondLevelScore) {
			thirdLevelLocked = false;
		}

		menuPanel.changeIcon(exlevel,currentLevel, secondLevelLocked, thirdLevelLocked);

		background.move();
		cornCollision(cornArray, eggArray);
		chickenCollision(cornArray);
		
		if (currentLevel == "level1") {
			catCollision(catArray);
			cornSpawner(1);
			cornsMove(1);
			catSpawner();
			catsMove(1);
			if (score >= firstLevelScore) {
				exlevel = currentLevel;
				currentLevel = "finishMenu";
			}
		} else if (currentLevel == "level2") {
			catCollision(catArray);
			cornSpawner(2);
			cornsMove(2);
			catSpawner();
			catsMove(2);
			if (score >= secondLevelScore) {
				exlevel = currentLevel;
				currentLevel = "finishMenu";
			}
		} else if (currentLevel == "level3") {
			catCollision(catArray);
			cornSpawner(3);
			cornsMove(3);
			catSpawner();
			catsMove(3);
			if (score >= thirdLevelScore) {
				exlevel = currentLevel;
				currentLevel = "finishMenu";
			}
		}
		

		eggShooter();
	}



    public void paintComponent(Graphics g) {
		
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		//g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		//g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		//g2.setColor(Color.WHITE);
		g2.setFont(scoreFont);

		if(currentLevel == "startMenu"){
			renderStartMenu(g2);
		}
		else if (currentLevel == "level1") {
			renderLevel1(g2);
		}
		else if (currentLevel == "level2") {
			renderLevel2(g2);
		}
		else if (currentLevel == "level3") {
			renderLevel3(g2);
		}
		else if (currentLevel == "finishMenu") {
			renderFinishMenu(g2);
		}
		
		g2.dispose();
    }

	public void cornSpawner(int lvl){
		if (System.nanoTime() - lastCornSpawnTime > 1000000000) {
			spawnCorn(lvl);
		}
	}

	public void spawnCorn(int lvl){
		Corn newCorn = new Corn(lvl);
		newCorn.setTexture(cornTexture);
		if(newCorn.direction == -1){
			newCorn.position.setX(0);
		}
		else{
			newCorn.position.setX(screenWidth-newCorn.width);
		}
		cornArray.add(newCorn);
		lastCornSpawnTime = System.nanoTime();
	}

	/////////////////////////////////

	public void cornsRender(Graphics2D g2){
		
		for (int i = 0; i < cornArray.size(); i++) {
			cornArray.get(i).draw(g2);
		}
	}
	public void cornsMove(int lvl){
		
		for (int i = 0; i < cornArray.size(); i++) {
			cornArray.get(i).move(lvl);
			if(cornArray.get(i).position.getY() < - cornArray.get(i).height){
				cornArray.remove(i);
			}
		}
	}

	/////////////////////////////////////

	public void catSpawner(){
		if (System.nanoTime() - lastCatSpawnTime > 1000000000) {
			spawnCat();
		}
	}

	public void spawnCat(){
		Cat newCat = new Cat();
		newCat.setTexture(catTexture);
		
		newCat.position.setX(getRandomNumber(100, screenWidth - newCat.width - 100));
		newCat.position.setY(screenHeight);

		catArray.add(newCat);
		lastCatSpawnTime = System.nanoTime();
	}

	/////////////////////////////////

	public void catsRender(Graphics2D g2){
		
		for (int i = 0; i < catArray.size(); i++) {
			catArray.get(i).draw(g2);
		}
	}
	public void catsMove(int lvl){
		
		for (int i = 0; i < catArray.size(); i++) {
			catArray.get(i).move(lvl);
			if(catArray.get(i).position.getY() < - catArray.get(i).height){
				catArray.remove(i);
			}
		}
	}

	/////////////////////////

	public void eggFire(int dir){
		Egg newEgg = new Egg();
		if (direction == -1) {
			newEgg.position.setX(chicken.position.getX() -5);
			newEgg.position.setY(chicken.position.getY());
		}else if (direction == 1) {
			newEgg.position.setX(chicken.position.getX()+50);
			newEgg.position.setY(chicken.position.getY());
		}
		newEgg.changeDirection(dir);
		newEgg.setTexture(eggTexture);
		eggArray.add(newEgg);
		lastEggSpawnTime = System.nanoTime();
	}

	public void eggRender(Graphics2D g2){

		for (int i = 0; i < eggArray.size(); i++) {
			eggArray.get(i).movement();
			if(eggArray.get(i).position.getX() > screenWidth || eggArray.get(i).position.getX() < -eggArray.get(i).width ){
				eggArray.remove(i);
			}
		}
		for (int i = 0; i < eggArray.size(); i++) {
			eggArray.get(i).draw(g2);
		}
	}

	public void eggShooter(){
		if (keyListener.leftPressed == true) {
			direction = -1;
			chicken.move(direction);
			/*if (keyListener.spacePressed == false) {
				chicken.changeTexture(1);
			}*/
		}
		if (keyListener.spacePressed == false) {
			if (direction == -1) {
				chicken.changeTexture(1);
			}else if (direction == 1) {
				chicken.changeTexture(3);
			}
		}
		if (keyListener.rightPressed == true) {
			direction = 1;
			chicken.move(direction);
			/*if (keyListener.spacePressed == false) {
				chicken.changeTexture(3);
			}*/
		}
		
		if (chicken.position.getX() + chicken.width < 0 || chicken.position.getX()> screenWidth) {
			chickenAlive = false;
			exlevel = currentLevel;
			currentLevel = "finishMenu";
			chicken.defaultPos();
		}

		if (keyListener.spacePressed == true  && System.nanoTime() - lastEggSpawnTime > 300000000) {
			eggFire(direction);
			if (direction == -1) {
				chicken.changeTexture(2);
			}
			if (direction == 1) {
				chicken.changeTexture(4);
			}
		}
		/*if (direction == -1 && keyListener.leftPressed == false) {
			chicken.changeTexture(1);
		}
		if (direction == 1 && keyListener.rightPressed == false) {
			chicken.changeTexture(3);
		}*/
	}

	//////////////////////////

	public void level1Clicked(){
		if (listener.firstLevelPressed == true) {

			
			this.setBackground(Color.cyan);
			refreshArrays();
			currentLevel = "level1";


			listener.firstLevelPressed = false;
		}
	}

	
	public void level2Clicked(){
		if (listener.secondLevelPressed == true) {
			
			if (secondLevelLocked == false) {
				this.setBackground(Color.cyan);
				refreshArrays();
				currentLevel = "level2";
			}

			listener.secondLevelPressed = false;
		}
	}
	
	public void level3Clicked(){
		if (listener.thirdLevelPressed == true) {
			

			if (thirdLevelLocked == false) {
				this.setBackground(Color.cyan);
				refreshArrays();
				currentLevel = "level3";
			}
			
			listener.thirdLevelPressed = false;
		}
	}
	
	public void restartClicked(){
		if (listener.restartPressed == true) {
			this.setBackground(Color.cyan);
			
			if (currentLevel == "finishMenu") {
				currentLevel = exlevel;
			}
			refreshArrays();
			listener.restartPressed = false;
		}
	}
	
	/*public void exitClicked(){
		if (listener.exitPressed == true) {
			
			listener.exitPressed = false;
		}
	}*/

	public void cornCollision(ArrayList<Corn> cornList, ArrayList<Egg> eggList){
		for (int i = 0; i < cornList.size(); i++) {
			for (int j = 0; j < eggList.size(); j++) {
				if(cornList.get(i).getBoundingRectangle().intersects(eggList.get(j).getBoundingRectangle())){
					score += cornList.get(i).getSize();
					eggList.remove(j);
					cornList.remove(i);
				}
			}
		}
	}

	public void catCollision(ArrayList<Cat> catList){
		for (int j = 0; j < catList.size(); j++) {
			if(chicken.getBoundingRectangle().intersects(catList.get(j).getBoundingRectangle())){
				chickenAlive = false;
				exlevel = currentLevel;
				currentLevel = "finishMenu";
				chicken.defaultPos();
			}
		}
	}
	public void chickenCollision(ArrayList<Corn> cornList){
		for (int j = 0; j < cornList.size(); j++) {
			if(chicken.getBoundingRectangle().intersects(cornList.get(j).getBoundingRectangle())){
				chickenAlive = false;
				exlevel = currentLevel;
				currentLevel = "finishMenu";
				chicken.defaultPos();
			}
		}
	}

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}


	public void renderStartMenu(Graphics2D g2){

		this.setBackground(Color.MAGENTA);
		g2.drawImage(startTexture, 0, 0, screenWidth, screenHeight, null);
		/*g2.drawImage(chicken.returnTex(), screenWidth/5, screenHeight/5,chicken.returnTex().getWidth(), chicken.returnTex().getHeight() , null);
		g2.drawImage(catTexture, screenWidth/5*3, screenHeight/5,catTexture.getWidth(), catTexture.getHeight() , null);
		g2.drawString("Escape/Aim : Left/Right Keys",screenWidth/50 ,screenHeight/10*8);
		g2.drawString("Shoot : Space Key",screenWidth/50 ,screenHeight/10*9);*/

	}
	public void renderFinishMenu(Graphics2D g2){

		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		if (chickenAlive) {

			g2.drawImage(winTexture, 0, 0, screenWidth, screenHeight, null);

		}else{

			g2.drawImage(failTexture, 0, 0, screenWidth, screenHeight, null);

		}
	}
	public void renderLevel1(Graphics2D g2){
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		background.draw(g2);
		cornsRender(g2);
		eggRender(g2);
		catsRender(g2);
		chicken.draw(g2);
		g2.drawString(Integer.toString(firstLevelScore)+"/"+Integer.toString(score),screenWidth/50 ,screenHeight/11);
	}
	public void renderLevel2(Graphics2D g2){
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		background.draw(g2);
		cornsRender(g2);
		eggRender(g2);
		catsRender(g2);
		chicken.draw(g2);
		g2.drawString(Integer.toString(secondLevelScore)+"/"+Integer.toString(score),screenWidth/50 ,screenHeight/11);
	}
	public void renderLevel3(Graphics2D g2){
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		background.draw(g2);
		cornsRender(g2);
		eggRender(g2);
		catsRender(g2);
		chicken.draw(g2);
		g2.drawString(Integer.toString(thirdLevelScore)+"/"+Integer.toString(score),screenWidth/50 ,screenHeight/11);
	}

	public void refreshArrays(){
		chickenAlive = true;
		catArray.clear();
		eggArray.clear();
		cornArray.clear();
		score = 0;
	}

	public void addMenuPanel(MenuPanel menu){
		menuPanel = menu;
	}

	



	


	
}
