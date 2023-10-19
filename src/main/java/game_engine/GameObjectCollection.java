package game_engine;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;

import game_engine.GameObject;

/**
 * Class represents collection of gameObjects which are contained on one Jpanel.
 * GameObjects have the same class.
 */
public class GameObjectCollection<T extends GameObject> extends JPanel {
    private ArrayList<T> list;

    /**
     * Constructor.
     * @param width width of screen.
     * @param height height of screen.
     */
    public GameObjectCollection(int width, int height) {
        super(null);
        this.setOpaque(false);
        this.setLocation(0, 0);
        this.setSize(width, height);

        this.list = new ArrayList<T>();
    }

    /**
     * Add object in collection.
     * @param obj gameObject.
     */
    public void addObj(T obj) {
        this.list.add(obj);
        this.add(obj);
        this.update();
    }

    /**
     * Remove object from list.
     * @param obj obj whic is removed.
     * @return boolean (true - object was removed, false - not).
     */
    public boolean removeObj(T obj) {
        boolean isRemoved = false;
        Component[] componentList = this.getComponents();

        for (Component component: componentList) {
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
     * Remove object from list by index.
     * @param i index of object in list.
     * @return boolean (true - object was removed, false - not).
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
     * @param i index of object in list.
     * @return object.
     */
    public T getObj(int i) {
        return this.list.get(i);
    }

    /**
     * Get length of object list.
     * @return length of list.
     */
    public int getCollectionSize() {
        return this.list.size();
    }

    /**
     * Remove all objects from list.
     */
    public void clearCollection() {
        this.removeAll();
        this.list.clear();
        this.update();
    }

    /**
     * Update panel on which objects are located.
     */
    private void update() {
        this.revalidate();
        this.repaint();
    }

    /**
     * Get object collection.
     * @return object collection.
     */
    public ArrayList<T> getCollection() {
        return this.list;
    }
}
