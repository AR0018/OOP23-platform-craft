/*package it.unibo.controller.serializer;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.common.EntityType;
import it.unibo.common.SimpleEntity;
import it.unibo.common.impl.SimpleEntityImpl;
import it.unibo.controller.api.LevelSerializer;
import it.unibo.controller.impl.SerializerImpl; TODO: remove

public class TestSerializer {

    private static final String ROOT = "./it/unibo/serializer/";
    private static final String CORRECT_FILE = "test.json";

    private LevelSerializer serializer;

    @BeforeEach
    public void setUp() {
        this.serializer = new SerializerImpl();
    }

    @Test
    public void testSaveLoad() {
        Set<SimpleEntity> levelToSave = Set.of(
            new SimpleEntityImpl(EntityType.CHARACTER, 0, 0),
            new SimpleEntityImpl(EntityType.ENEMY, 10, 4));
        try{
            serializer.saveLevel(levelToSave, new File(ROOT + CORRECT_FILE));
        } catch (final IOException e) {
            fail();
        }
        Set<SimpleEntity> levelToLoad = new HashSet<>();
        try{
            levelToLoad = serializer.loadLevel(new File(ROOT + CORRECT_FILE));
        } catch (final IOException e) {
            fail();
        }
    }

}
*/