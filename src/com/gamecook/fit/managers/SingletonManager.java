package com.gamecook.fit.managers;

import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: jfreeman
 * Date: Aug 17, 2010
 * Time: 9:36:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingletonManager {

    private static SingletonManager ourInstance = new SingletonManager();

    private Hashtable<Class, Object> singletons = new Hashtable<Class, Object>();

    public static SingletonManager getInstance() {
        return ourInstance;
    }

    private SingletonManager() {
    }

    public Object getClassReference(Class classReference) {

        Object singleton = null;

        if (!singletons.containsKey(classReference)) {
            String className = classReference.getName();
            singleton = createObject(className);
            singletons.put(classReference, singleton);
        } else {
            singleton = singletons.get(classReference);
        }

        return singleton;
    }

    private Object createObject(String className) {
        Object object = null;
        try {
            Class classDefinition = Class.forName(className);
            object = classDefinition.newInstance();
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return object;
    }
}
