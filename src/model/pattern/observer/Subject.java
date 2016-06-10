package model.pattern.observer;

/**
 * This <PPP_1> project in package <model.pattern.observer> created by :
 * Name         : syafiq
 * Date / Time  : 02 June 2016, 11:37 PM.
 * Email        : syafiq.rezpector@gmail.com
 * Github       : syafiqq
 */
public interface Subject
{
    void registerObserver(String type, Observer observer);

    void removeObserver(String type, Observer observer);

    void notifyObserver(String type);

    void notifyAllObserver();
}
