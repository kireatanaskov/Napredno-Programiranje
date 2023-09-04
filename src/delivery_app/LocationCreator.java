package delivery_app;

public class LocationCreator {
    public static Location create(int x, int y) {
        return new Location() {
            @Override
            public int getX() {
                return x;
            }

            @Override
            public int getY() {
                return y;
            }
        };
    }
}
