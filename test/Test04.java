package test;

import org.junit.Test;
import org.junit.Before;

import org.junit.After;
import org.junit.Assert;

import edu.hitsz.aircraft.*;

public class Test04 
{
    private AbstractAircraft heroAircraft;
    
    @Before
    public void setUp()
    {
        heroAircraft = HeroAircraft.getInstance();
    }

    @After
    public void tearDown()
    {
        heroAircraft = null;
    }

    @Test
    public void decreaseHpTest()
    {
        heroAircraft.decreaseHp(120);
        Assert.assertEquals(true, heroAircraft.notValid());
        Assert.assertEquals(0, heroAircraft.getHp());
    }
}
