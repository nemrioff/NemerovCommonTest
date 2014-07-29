package ru.nemiroff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nemiroff on 29.07.2014.
 */
public class ThingsManager {

    private List<Thing> things;

    public Thing getThingByName(String thingName) {
        for (Thing thing : things) {
            if (thing.getName().equals(thingName)) {
                return thing;
            }
        }
        return null;        
    }

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }
}
