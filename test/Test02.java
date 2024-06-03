package test;

import org.junit.Test;
import org.junit.Before;

import org.junit.After;
import org.junit.Assert;

import edu.hitsz.aircraft.*;

public class Test02 
{
    private AbstractAircraft heroAircraft;
    private AbstractAircraft mobEnemyAircraft;

    @Before
    public void setUp()
    {
        heroAircraft = HeroAircraft.getInstance();
        mobEnemyAircraft = new MobEnemy(0, 0, 0, 0, 30, 0);
    }

    @After
    public void tearDown()
    {
        heroAircraft = null;
        mobEnemyAircraft = null;
    }

    @Test
    public void crashTest()
    {
        heroAircraft.setLocation(100, 100);
        mobEnemyAircraft.setLocation(100, 100);
        Assert.assertEquals(true, heroAircraft.crash(mobEnemyAircraft));
    }
}
