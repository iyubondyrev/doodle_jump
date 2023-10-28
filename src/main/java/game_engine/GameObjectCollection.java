package game_engine;

import java.awt.Component;
import java.util.ArrayList;

/**
 * Class represents collection of gameObjects which are contained on one Jpanel.
 * GameObjects have the same class.
 */
public abstract class GameObjectCollection<T extends GameObject> extends BaseSurface {
    protected ArrayList<T> list;

    /**
     * Constructor.
     * 
     * @param width  width of screen.
     * @param height height of screen.
     */
    public GameObjectCollection(int width, int height) {
        super(width, height);
        this.list = new ArrayList<T>();
    }

    /**
     * Add object in collection.
     * Methods adds object in list and on JPanel.
     * 
     * @param obj gameObject.
     */
    public void addObj(T obj) {
        this.list.add(obj);
        this.add(obj);
        this.update();
    }

    /**
     * Remove object from collection.
     * Method removes object from list and from JPanel.
     * 
     * @param obj obj whic is removed.
     * @return boolean (true - object was not found, false - not).
     */
    public boolean removeObj(T obj) {
        boolean isRemoved = false;
        Component[] componentList = this.getComponents();

        for (Component component : componentList) {
            if (component == obj) {
                this.list.remove(obj);
                this.remove(component);

                isRemoved = true;
                this.update();
            }
        }

        return isRemoved;
    }

    /**
     * Remove object from collection by index.
     * Method removes object from list and from JPanel.
     * 
     * @param i index of object in list.
     * @return boolean (true - object was not found, false - not).
     */
    public boolean removeObj(int i) {
        boolean isRemoved = false;

        if (i < this.getCollectionSize()) {
            T obj = this.getObj(i);
            isRemoved = this.removeObj(obj);
        }

        return isRemoved;
    }

    /**
     * Get object from list.
     * 
     * @param i index of object in list.
     * @return object.
     */
    public T getObj(int i) {
        return this.list.get(i);
    }

    /**
     * Get length of object-list.
     * 
     * @return length of list.
     */
    public int getCollectionSize() {
        return this.list.size();
    }

    /**
     * Remove all objects from collection.
     * Method removes all objects from list and JPanel.
     */
    public void clearCollection() {
        this.removeAll();
        this.list.clear();
        this.update();
    }

    /**
     * Update JPanel.
     */
    private void update() {
        this.revalidate();
        this.repaint();
    }

    /**
     * Get object-list.
     * 
     * @return object-list.
     */
    public ArrayList<T> getCollection() {
        return this.list;
    }
}
