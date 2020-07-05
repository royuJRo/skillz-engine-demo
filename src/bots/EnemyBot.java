package bots;

import Game.*;

public class EnemyBot implements SkillzBot {

	@Override
	public void doTurn(Game game) {
		// TODO Auto-generated method stub
		for(int i = 0;i<game.getAllMyLivingPirates().length;i++) {
			game.getAllMyLivingPirates()[i].go(game.getAllEnemyLivingPirates()[0]);
		}
	}

}
