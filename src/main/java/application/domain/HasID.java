package application.domain;

/**
 * Created by NegrutiA on 3/17/2017.
 */
public interface HasID<K> {
    K getId();
    void setId(K newId);
}
