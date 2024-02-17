package it.unibo.model.engine.impl;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.SimpleEntity;
import it.unibo.common.impl.SimpleEntityImpl;
import it.unibo.model.engine.api.Engine;
import it.unibo.model.level.api.Level;
import it.unibo.model.physics.api.Direction;
import it.unibo.model.level.api.GameState;

    /**
     * Spotbugs suppression.
     */
    @SuppressFBWarnings(
        value = {
            "EI_EXPOSE_REP2"
        },
        justification = "A normal Level stored by this object would be externally mutable, "
            + "however, this class actually stores an instance of UnmodifiableLevel, which disables writing operations."
    )

/**
 * An implementation of Engine.
 */
public final class EngineImpl implements Engine {

    private final Level level;

    /**
     * The constructor of this EngineImpl.
     * @param level the Level to be run
     */
    public EngineImpl(final Level level) {
        this.level = Objects.requireNonNull(level);
    }

    @Override
    public void updateLevel() {
        this.level.computeChanges();
    }

    @Override
    public void moveCharacter(final Direction dir) {
        this.level.moveCharacter(dir);
    }

    @Override
    public Set<SimpleEntity> getLevelEntities() {
        return level.getGameEntities()
            .stream()
            .map(e -> new SimpleEntityImpl(e.getType(), e.getPosition().getX(), e.getPosition().getY()))
            .collect(Collectors.toSet());
    }

    @Override
    public GameState getGameState() {
        return this.level.getGameState();
    }
}
