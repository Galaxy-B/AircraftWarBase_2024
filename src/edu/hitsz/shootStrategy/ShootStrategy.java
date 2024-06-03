package edu.hitsz.shootStrategy;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.aircraft.AbstractAircraft;

import java.util.List;

public interface ShootStrategy 
{
    public List<BaseBullet> shoot(AbstractAircraft aircraft);
};
