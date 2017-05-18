package de.metraf.repository;

import de.metraf.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by metraf on 18.05.17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() throws Exception{
        User userArtem = new User( "Artem", 25);
        User userJulia = new User( "Julia", 29);

        assertNull(userArtem.getId());
        assertNull(userJulia.getId());
        userDao.save(userArtem);
        userDao.save(userJulia);
        assertNotNull(userArtem.getId());
        assertNotNull(userJulia.getId());
    }

    @Test
    public void testFetchData(){
        User userA = userDao.findByName("Artem");
        assertNotNull(userA);
        assertEquals(25, userA.getAge());
        Iterable<User> users = userDao.findAll();
        int count = 0;
        for (User u : users){
            count++;
        }
        assertEquals(2, count);
    }

    @Test
    public void testDataUpdate(){
        User userB = userDao.findByName("Julia");
        userB.setAge(28);
        userDao.save(userB);
        assertNotNull(userB.getId());
        assertEquals(28, userB.getAge());
    }

    @After
    public void deleteAll() throws Exception{
        userDao.deleteAll();
    }
}