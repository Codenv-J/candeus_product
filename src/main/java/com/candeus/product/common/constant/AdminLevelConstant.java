package com.candeus.product.common.constant;

public enum AdminLevelConstant {
    ADMIN(1),
    SUPER_ADMIN(0);

    private final int level;

    AdminLevelConstant(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
