package bidata.oracle.mbds.utils;

import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;

/**
 * Created by Faliherizo on 29/12/2016.
 */
public class InitDB {
    public final KVStore store;
    public InitDB(){
        String storeName = "kvstore";
        String hostName = "localhost";
        String hostPort = "5000";
        store = KVStoreFactory.getStore
                (new KVStoreConfig(storeName, hostName + ":" + hostPort));
    }

}
