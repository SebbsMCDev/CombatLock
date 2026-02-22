package me.magicsebs.combatLock.combat;

import org.bukkit.entity.Player;
import java.util.UUID;

public class CombatSession {

    private final UUID playerUUID;
    private final UUID opponentUUID;
    private int remainingSeconds;

    public CombatSession(Player player, Player opponent, int duration) {
        this.playerUUID = player.getUniqueId();
        this.opponentUUID = opponent.getUniqueId();
        this.remainingSeconds = duration;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public UUID getOpponentUUID() {
        return opponentUUID;
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public void setRemainingSeconds(int remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
    }

    public void decrement() {
        if (remainingSeconds > 0) remainingSeconds--;
    }
}