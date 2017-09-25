package org.halo.thallo.mmr.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dell01 on 2017/9/30.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import({
//    JpaConfiguration.class
})
public class SimpleTest {

    @Test
    public void enableTest() {

    }
}
