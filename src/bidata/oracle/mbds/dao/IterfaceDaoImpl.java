package bidata.oracle.mbds.dao;

import bidata.oracle.mbds.model.Person;
import oracle.kv.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Faliherizo on 29/12/2016.
 */
public class IterfaceDaoImpl<T> implements InterfaceDao<T> {
    private final KVStore store;

    public IterfaceDaoImpl() {
            String storeName = "kvstore";
            String hostName = "localhost";
            String hostPort = "5000";
            int argc = 0;
            store = KVStoreFactory.getStore
                    (new KVStoreConfig(storeName, hostName + ":" + hostPort));
    }


    @Override
    public void create(List<String> majorPath, String minorComponent, String data) {
        Key key = Key.createKey(majorPath, minorComponent);
        Value value = Value.createValue(data.getBytes());
        store.putIfAbsent(key, value);
    }

    @Override
    public void delete(Object id) {

    }

    @Override
    public T find(Object id) {
        return null;
    }

    @Override
    public T update(T person) {
        return null;
    }

    void deleteClient(String majorKey1, String majorKey2,String majorKey3, String minorKey) {
        String minorComponent=minorKey;
        List<String> majorPath = new ArrayList<String>();
        majorPath.add(majorKey1);
        majorPath.add(majorKey2);
        majorPath.add(majorKey3);
        Key key = Key.createKey(majorPath, minorComponent);
        store.delete(key);
    }
    void executeNSql(){


    }
}
