package io.keiji.mincomisample.paging.entity;

/**
 * Created by keiji_ariyama on 4/15/16.
 */
public enum Align {
    Left,
    Right,
    Center;

    public static Align flip(Align align) {
        switch (align) {
            case Left:
                return Right;
            case Right:
                return Left;
            default:
                return Center;
        }
    }
}
