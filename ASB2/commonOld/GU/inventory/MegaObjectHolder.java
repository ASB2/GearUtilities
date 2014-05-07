package GU.inventory;

import java.util.ArrayList;

public class MegaObjectHolder<CurrentType> {

    public static final int USUAL_MAX_OBJECTS = 64;

    ArrayList<CurrentType> storedObjects;
    int maxObjects;

    public MegaObjectHolder() {
        this(USUAL_MAX_OBJECTS);
    }

    public MegaObjectHolder(int maxObjects) {

        storedObjects = new ArrayList<CurrentType>(maxObjects);
        this.maxObjects = maxObjects;
    }

    public ArrayList<CurrentType> getObjects() {

        return storedObjects;
    }

    public CurrentType getObject(int position) {

        if (position < storedObjects.size() && position >= 0) {

            return storedObjects.get(position);
        }
        return null;
    }

    public boolean setObject(int position, CurrentType object) {

        if (position < maxObjects && storedObjects.size() < maxObjects && position >= 0) {

            storedObjects.set(position, object);
            return true;
        }
        return false;
    }

    public boolean addObject(int position, CurrentType object) {

        if (position < maxObjects && storedObjects.size() < maxObjects && position >= 0) {

            storedObjects.add(position, object);
            return true;
        }
        return false;
    }

    public boolean addObject(CurrentType object) {

        if (storedObjects.size() < maxObjects) {

            storedObjects.add(object);
            return true;
        }
        return false;
    }

    public int getMaximumObjects() {

        return maxObjects;
    }
}
