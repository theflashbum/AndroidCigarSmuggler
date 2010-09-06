package com.gamecook.fit.managers;

import com.gamecook.fit.player.Player;
import org.junit.Before;
import org.junit.Test;

import static com.gamecook.fit.managers.SingletonManager.*;
import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 17, 2010
 * Time: 9:46:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingletonManagerTest {


    @Before
    public void setup()
    {
        
    }

    @Test
    public void testGetClassReference() throws Exception {
        MockObject mo1 = (MockObject) getInstance().getClassReference(MockObject.class);
        mo1.name = "MockObject1";

        MockObject mo2 = (MockObject) getInstance().getClassReference(MockObject.class);
        mo2.name = "MockObject2";
        
        assertEquals(mo1.name, mo2.name, "MockObject2");
    }
}

class MockObject
{
    public String name;
}
