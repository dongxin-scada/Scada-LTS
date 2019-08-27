package org.scada_lts.config;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.text.MessageFormat;

import static org.junit.Assert.assertEquals;


public class ThreadPoolConfigKeysTest {

    private static ScadaConfig config;

    @BeforeClass
    public static void init() throws Exception {
        DOMConfigurator.configure("WebContent/WEB-INF/classes/log4j.xml");
        String path = MessageFormat.format("{0}resources{0}env-test.properties", File.separator);
        config = ScadaConfig.getConfigFromExternalFile(new File(path));
    }

    @Test
    public void invoke_parseInteger_for_key_corePoolSize_return_3() {
        //when:
        int corePoolSize = config.getInt(ThreadPoolConfigKeys.CORE_POOL_SIZE, -1);

        //then:
        assertEquals(3, corePoolSize);
    }

    @Test
    public void invoke_parseInteger_for_key_maximumPoolSize_return_100() {
        //when:
        int maximumPoolSize = config.getInt(ThreadPoolConfigKeys.MAXIMUM_POOL_SIZE, -1);

        //then:
        assertEquals(100, maximumPoolSize);
    }

    @Test
    public void invoke_parseLong_for_key_keepAliveTime_return_60() {
        //when:
        long keepAliveTime = config
                .getLong(ThreadPoolConfigKeys.KEEP_ALIVE_TIME,-1);

        //then:
        assertEquals(60, keepAliveTime);
    }

    @Test
    public void invoke_parseString_for_key_blockingQueueInterfaceImpl_return_LinkedBlockingQueue() {
        //when:
        String blockingQueueImplClassName = config
                .getString(ThreadPoolConfigKeys.BLOCKING_QUEUE_INTERFACE_IMPL, "");
        //then:
        assertEquals("java.util.concurrent.LinkedBlockingQueue", blockingQueueImplClassName);
    }

    @Test
    public void invoke_parseString_for_key_timeUnitEnumValue_return_SECONDS() {
        //when:
        String timeUnitValue = config
                .getString(ThreadPoolConfigKeys.TIME_UNIT_ENUM_VALUE, "");
        //then:
        assertEquals("SECONDS", timeUnitValue);
    }

    @Test
    public void invoke_getConfigFromExternalFile_for_no_exists_filePropertiesPath_then_return_empty_properties() {
        //when:
        ScadaConfig config = ScadaConfig.getConfigFromExternalFile(new File(""));
        //then:
        assertEquals(0, config.size());
    }

    @Test
    public void invoke_getConfigFromExternalFile_for_null_then_return_empty_properties() {
        //when:
        ScadaConfig config = ScadaConfig.getConfigFromExternalFile(null);
        //then:
        assertEquals(0, config.size());
    }
}