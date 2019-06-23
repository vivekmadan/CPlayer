package com.stackroute.playerrecommendationservice.repository;

import com.stackroute.playerrecommendationservice.domain.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    private Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player();
        player.setPid("1001");
        player.setBattingStyle("Right Hand");
        player.setBowlingStyle("Right Hand");
        player.setCount(1);
        player.setCountry("India");
        player.setName("Virat");
        player.setProfile("Profile");
        player.setImageUrl("Image URL");
    }


    @After
    public void tearDown() throws Exception {
        playerRepository.delete(player);
        player = null;
    }

    @Test
    public void testSaveRecommendedPlayer() {
        playerRepository.save(player);
        Player fetchedPlayer = playerRepository.findByPid(player.getPid());
        Assert.assertEquals(player.getPid(), fetchedPlayer.getPid());
    }
}
