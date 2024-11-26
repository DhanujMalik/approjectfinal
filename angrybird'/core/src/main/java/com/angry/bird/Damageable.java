package com.angry.bird;

public interface Damageable {
    /**
     * Inflicts damage on the object.
     *
     * @param damage The amount of damage to inflict.
     */
    void takeDamage(int damage);

    /**
     * Checks if the object is destroyed.
     *
     * @return True if the object is destroyed, false otherwise.
     */
    boolean isDestroyed();
}

