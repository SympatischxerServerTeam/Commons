package com.github.sympatischxerserverteam.commons;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected boolean loadPlugin;
    protected ServerMock mock;
    protected Main plugin;

    public BaseTest() {
        this(true);
    }

    public BaseTest(boolean loadPlugin) {
        this.loadPlugin = loadPlugin;
    }

    @BeforeEach
    void setUp() {
        this.mock = MockBukkit.mock();
        if (loadPlugin) this.plugin = MockBukkit.load(Main.class);
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }
}
