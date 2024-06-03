package test;

import org.junit.Test;
import org.junit.Before;

import org.junit.After;
import org.junit.Assert;

import edu.hitsz.aircraft.*;
import edu.hitsz.bullet.*;

import java.util.LinkedList;
import java.util.List;

public class Test03 
{
    private AbstractAircraft heroAircraft;
    private List<BaseBullet> heroBullets;

    @Before
    public void setUp()
    {
        heroAircraft = HeroAircraft.getInstance();
        heroBullets = new LinkedList<>();
    }

    @After
    public void tearDown()
    {
        heroAircraft = null;
        heroBullets = null;
    }

    @Test
    public void shootTest()
    {
        for (int i = 0; i < 5; i++)
        {
            heroBullets.addAll(heroAircraft.executeShootStrategy());
        }
        Assert.assertEquals(5, heroBullets.size());
    }
}
