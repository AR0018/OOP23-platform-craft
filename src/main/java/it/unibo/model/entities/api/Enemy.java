package it.unibo.model.entities.api;

/**
 * Interface that model the concept of an enemy
 */
public interface Enemy extends GameEntity{

    /**
     * Return the what type of enemy is present
     */
    void enemyType(); //TODO: return EnemyType

    //Usare Factory o Build nella impl

}
