package bidata.oracle.mbds.dao;

import java.util.List;

/**
 * Created by Faliherizo on 29/12/2016.
 */
public interface InterfaceDao<T> {

    void create(List<String> majorPath, String minorComponent, String data);

    void delete(Object id);

    T find(Object id);

    T update(T t);
}
