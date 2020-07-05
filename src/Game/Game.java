package Game;

public class Game {
	
	public static final int WINNING_SCORE = 700; // which one how got it first win
	
	private Player myself;
	private Player enemy;
	
	private Pirate[] AllMyPirates;
	private Pirate[] AllMyLivingPirates;
	
	private Pirate[] AllEnemyPirates;
	private Pirate[] AllEnemyLivingPirates;
	
	
	public int turn;
	
	public Game() {
		
		myself = new Player("myself");
		enemy = new Player("enemy");
		
		int myPiratesNum = 1;
		int enemyPiratesNum = 4;
		
		AllMyPirates = new PiratesUnit(myPiratesNum, new Location(100,100), myself).getPiratesArray();
		
		int count = 0;
		for (int i = 0; i < AllMyPirates.length; i++) {
			if (AllMyPirates[i].isAlive()) 
				count++;
		}
		AllMyLivingPirates = new Pirate[count];
		int index = 0;
		for (int i = 0; i < AllMyPirates.length; i++) {
			if(AllMyPirates[i].isAlive()) {
				AllMyLivingPirates[index] = AllMyPirates[i];
				index++;
			}
		}
		
		
		AllEnemyPirates = new PiratesUnit(enemyPiratesNum, new Location(400,400), enemy).getPiratesArray();
		
		count = 0;
		for (Pirate pirate : AllEnemyPirates) {
			if(pirate.isAlive())
				count++;
		}
		AllEnemyLivingPirates = new Pirate[count];
		index = 0;
		for (int i = 0; i < AllEnemyPirates.length; i++) {
			if(AllEnemyPirates[i].isAlive()) {
				AllEnemyLivingPirates[index] = AllEnemyPirates[i];
				index++;
			}
		}
		
		turn = 1;
	}
	
	public void moveToNextTurn() {
		//turn++;
		
		/**
		 * Living pirates' stock refreshing
		 */
		
		int count = 0;
		for (int i = 0; i < AllMyPirates.length; i++) {
			if (AllMyPirates[i].isAlive()) 
				count++;
			else {
				AllMyPirates[i].turnToRevive--;
			}
		}
		AllMyLivingPirates = new Pirate[count];
		int index = 0;
		for (int i = 0; i < AllMyPirates.length; i++) {
			if(AllMyPirates[i].isAlive()) {
				AllMyLivingPirates[index] = AllMyPirates[i];
				index++;
			}
		}
		
		count = 0;
		for (Pirate pirate : AllEnemyLivingPirates) {
			if(pirate.isAlive())
				count++;
			else
				pirate.turnToRevive--;
		}
		AllEnemyLivingPirates = new Pirate[count];
		index = 0;
		for (int i = 0; i < AllEnemyPirates.length; i++) {
			if(AllEnemyPirates[i].isAlive()) {
				AllEnemyLivingPirates[index] = AllEnemyPirates[i];
				index++;
			}
		}
		
	for(Pirate p:AllEnemyLivingPirates)
		p.alreadyActed = false;
	
	for(Pirate p:AllEnemyPirates)
		p.alreadyActed = false;
	
	for(Pirate p:AllMyLivingPirates)
		p.alreadyActed = false;
	
	for(Pirate p:AllMyPirates)
		p.alreadyActed = false;
	
	}
	

	
	
	public Pirate[] getAllMyPirates() {
		return AllMyPirates;
	}

	public Pirate[] getAllMyLivingPirates() {
		return AllMyLivingPirates;
	}

	public Pirate[] getAllEnemyPirates() {
		return AllEnemyPirates;
	}

	public Pirate[] getAllEnemyLivingPirates() {
		return AllEnemyLivingPirates;
	}
	
	public Player getMyself() {
		return myself;
	}
	public Player getEnemy() {
		return enemy;
	}
	
	public static Game getCostumizedGameObjectForEnemy(Game game) {
		Game costumizedGame = new Game();
		costumizedGame.AllMyPirates = game.AllEnemyPirates;
		costumizedGame.AllEnemyPirates = game.AllMyPirates;
		costumizedGame.AllMyLivingPirates = game.AllEnemyLivingPirates;
		costumizedGame.AllEnemyLivingPirates = game.AllMyLivingPirates;
		costumizedGame.myself = game.enemy;
		costumizedGame.enemy = game.myself;
		
		
		return costumizedGame;
	}
	
	

}