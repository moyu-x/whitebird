package top.idwangmo.whitebird.commoncore.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class SequenceTest {

    @Test
    public void testRepeated() {
        Set<Long> sets = new HashSet<>();
        int maxTimes = 1000 * 10;
        Sequence sequence = new Sequence();
        for (int i = 0; i < maxTimes; i++) {
            long id = sequence.nextId();
            System.out.println(id);
            sets.add(id);
        }

        assertEquals(maxTimes, sets.size());
    }

    @Test
    public void testHutool() {
        Snowflake snowflake = IdUtil.createSnowflake(1, 2);
        for (int i = 0; i < 10000; i++) {
            log.info(String.valueOf(snowflake.nextId()));
        }

    }

}