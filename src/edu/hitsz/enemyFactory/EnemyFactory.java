package edu.hitsz.enemyFactory;

import edu.hitsz.aircraft.AbstractAircraft;

public abstract class EnemyFactory 
{
    public abstract AbstractAircraft createAircraft(int speedX, int speedY, int hp, int power);    
}
