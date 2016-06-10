package model.pattern.adapter;

import model.db.DBProperties;
import model.pattern.observer.DatabaseStateObserver;
import model.pattern.observer.Observer;

/**
 * This <PPP_1> project in package <model.pattern.observer> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 10:54 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public class DatabaseStateObserverAdapter implements Observer
{
    private final DBProperties          properties;
    private final DatabaseStateObserver observer;

    public DatabaseStateObserverAdapter(DatabaseStateObserver observer, DBProperties properties)
    {
        this.properties = properties;
        this.observer = observer;
    }

    @Override public void update()
    {
        observer.update(properties.isActive);
    }
}
