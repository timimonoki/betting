package application.domain;

/**
 * Created by NegrutiA on 3/17/2017.
 */
public interface HasID<ID> {
    ID getId();
    void setId(ID newId);
}
