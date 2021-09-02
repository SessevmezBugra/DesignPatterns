package factories;

enum CoordinateSystem {
    CARTESIAN,
    POLAR
}

class Point {
    private double x, y;

    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static class Factory {
        public static Point newCartesianPoint(double x, double y) {
            return new Point(x, y);
        }

        public static Point newPolarPoint(double rho, double theta) {
            return new Point(rho * Math.cos(theta), rho * Math.sin(theta));
        }
    }

//    private Factories.Point(double a, double b, Factories.CoordinateSystem cs) {
//        switch (cs) {
//            case CARTESIAN -> {
//                this.x = a;
//                this.y = b;
//            }
//            case POLAR -> {
//                x = a * Math.cos(b);
//                y = a * Math.sin(b);
//            }
//        }
//    }

//    public static Factories.Point newCartesianPoint(double x, double y) {
//        return new Factories.Point(x, y);
//    }
//
//    public static Factories.Point newPolarPoint(double rho, double theta) {
//        return new Factories.Point(rho*Math.cos(theta), rho*Math.sin(theta));
//    }

//    public Factories.Point(double rho, double theta) {
//        x = rho * Math.cos(theta);
//        y = rho * Math.sin(theta);
//    }
}

//class PointFactory {
//    public static Factories.Point newCartesianPoint(double x, double y) {
//        return new Factories.Point(x, y);
//    }
//
//    public static Factories.Point newPolarPoint(double rho, double theta) {
//        return new Factories.Point(rho * Math.cos(theta), rho * Math.sin(theta));
//    }
//}

class FactoryMethodTest {
    public static void main(String[] args) {
//        Factories.Point.newPolarPoint(2, 3);
        Point point = Point.Factory.newCartesianPoint(2, 3);
//        Factories.Point point1 = new Factories.Point(4,5);
    }
}
