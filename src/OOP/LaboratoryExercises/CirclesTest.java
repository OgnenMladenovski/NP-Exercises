package OOP.LaboratoryExercises;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

class ObjectCanNotBeMovedException extends Exception
{
    public ObjectCanNotBeMovedException (String message)
    {
        super(message);
    }
}

class MovableObjectNotFittableException extends Exception
{
    public MovableObjectNotFittableException(String message)
    {
        super(message);
    }
}

interface Movable {
    int getCurrentXPosition();
    int getCurrentYPosition();

    default void moveUp() throws ObjectCanNotBeMovedException
    {

    }
    default void moveDown() throws ObjectCanNotBeMovedException
    {

    }
    default void moveRight() throws ObjectCanNotBeMovedException
    {

    }
    default void moveLeft() throws ObjectCanNotBeMovedException
    {

    }
}

class MovablePoint implements Movable
{
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public int getCurrentXPosition() {
        return x;
    }

    @Override
    public int getCurrentYPosition() {
        return y;
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        int newY = y + ySpeed;
        if(newY < 0 || newY > MovablesCollection.getyMax())
        {
            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds", x, newY));
        }
        y = newY;
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException{
        int newY = y - ySpeed;
        if (newY < 0 || newY > MovablesCollection.getyMax())
        {
            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds", x, newY));
        }
        y = newY;
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        int newX = x + xSpeed;
        if(newX < 0 || newX > MovablesCollection.getxMax())
        {
            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds", newX, y));
        }
        x = newX;
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        int newX = x - xSpeed;
        if(newX < 0 || newX > MovablesCollection.getxMax())
        {
            throw new ObjectCanNotBeMovedException(String.format("Point (%d,%d) is out of bounds", newX, y));
        }
        x = newX;
    }

    @Override
    public String toString() {
        return "Movable point with coordinates ("+x+","+y+")";
    }
}

class MovableCircle implements Movable
{
    private int radius;
    private MovablePoint center;

    public MovableCircle(int radius, MovablePoint center) {
        this.radius = radius;
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public MovablePoint getCenter() {
        return center;
    }

    public void setCenter(MovablePoint center) {
        this.center = center;
    }

    @Override
    public int getCurrentXPosition() {
        return center.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return center.getCurrentYPosition();
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        center.moveUp();
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException{
        center.moveDown();
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        center.moveRight();
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        center.moveLeft();
    }

    @Override
    public String toString() {
        return "Movable circle with center coordinates ("+center.getCurrentXPosition()+","+center.getCurrentYPosition()+") and radius "+radius+"";
    }
}

class MovablesCollection {
    private Movable [] movables = new Movable[0];
    private static int x_MAX = 100;
    private static int y_MAX = 100;

    public MovablesCollection(int x_MAX, int y_MAX)
    {
        this.x_MAX = x_MAX;
        this.y_MAX = y_MAX;
    }

    public void addMovableObject(Movable m)
    {
        try
        {
            boolean fits;
            if(m instanceof MovableCircle)
            {
                MovableCircle mc = (MovableCircle) m;
                int circleX = mc.getCurrentXPosition();
                int circleY = mc.getCurrentYPosition();
                int circleRadius = mc.getRadius();
                if((circleX - circleRadius >= 0) && (circleX + circleRadius <= x_MAX) && (circleY - circleRadius >= 0) && (circleY + circleRadius <= y_MAX))
                {
                    fits = true;
                }
                else
                    fits = false;

                if (!fits)
                {
                    throw new MovableObjectNotFittableException(String.format("Movable circle with center (%d,%d) and radius %d can not be fitted into the collection", circleX, circleY, circleRadius));
                }
            }
            else {
                int squareX = m.getCurrentXPosition();
                int squareY = m.getCurrentYPosition();
                if((squareX  >= 0) && (squareX <= x_MAX) && (squareY >= 0) && (squareY <= y_MAX))
                {
                    fits = true;
                }
                else
                    fits = false;

                if (!fits)
                {
                    throw new MovableObjectNotFittableException(String.format("Movable point with coordinates (%d,%d) can not be fitted into the collection", squareX, squareY));
                }
            }
            movables = Arrays.copyOf(movables, movables.length+1);
            movables[movables.length-1] = m;
        } catch (MovableObjectNotFittableException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void moveObjectsFromTypeWithDirection (TYPE type, DIRECTION direction)
    {
        for(Movable m : movables)
        {
            boolean matches;
            if((m instanceof MovablePoint && type == TYPE.POINT) || (m instanceof MovableCircle && type == TYPE.CIRCLE))
            {
                matches = true;
            }
            else
                matches = false;

            if(!matches)
            {
                continue;
            }
            try {
                switch (direction)
                {
                    case UP: m.moveUp(); break;
                    case DOWN: m.moveDown(); break;
                    case LEFT: m.moveLeft(); break;
                    case RIGHT: m.moveRight(); break;
                }
            }
            catch (ObjectCanNotBeMovedException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int getxMax() {
        return x_MAX;
    }

    public static int getyMax() {
        return y_MAX;
    }

    public static void setxMax(int x_MAX) {
        MovablesCollection.x_MAX = x_MAX;
    }

    public static void setyMax(int y_MAX) {
        MovablesCollection.y_MAX = y_MAX;
    }

    @Override
    public String toString() {
        return "Collection of movable objects with size " + movables.length + ":\n" + Arrays.stream(movables).map(Movable::toString).collect(Collectors.joining("\n")) + "\n";
    }
}

public class CirclesTest {

    public static void main(String[] args) {

        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");

            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);

            if (Integer.parseInt(parts[0]) == 0) { //point
                collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
            }

        }
        System.out.println(collection.toString());

        System.out.println("MOVE POINTS TO THE LEFT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES DOWN");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        System.out.println(collection.toString());

        System.out.println("CHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);

        System.out.println("MOVE POINTS TO THE RIGHT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        System.out.println(collection.toString());

        System.out.println("MOVE CIRCLES UP");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        System.out.println(collection.toString());


    }


}

