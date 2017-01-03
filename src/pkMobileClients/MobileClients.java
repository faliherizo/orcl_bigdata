/*-
 *
 *  This file is part of Oracle NoSQL Database
 *  Copyright (C) 2011, 2013 Oracle and/or its affiliates.  All rights reserved.
 *
 *  Oracle NoSQL Database is free software: you can redistribute it and/or
 *  modify it under the terms of the GNU Affero General Public License
 *  as published by the Free Software Foundation, version 3.
 *
 *  Oracle NoSQL Database is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public
 *  License in the LICENSE file along with Oracle NoSQL Database.  If not,
 *  see <http://www.gnu.org/licenses/>.
 *
 *  An active Oracle commercial licensing agreement for this product
 *  supercedes this license.
 *
 *  For more information please contact:
 *
 *  Vice President Legal, Development
 *  Oracle America, Inc.
 *  5OP-10
 *  500 Oracle Parkway
 *  Redwood Shores, CA 94065
 *
 *  or
 *
 *  berkeleydb-info_us@oracle.com
 *
 *  [This line intentionally left blank.]
 *  [This line intentionally left blank.]
 *  [This line intentionally left blank.]
 *  [This line intentionally left blank.]
 *  [This line intentionally left blank.]
 *  [This line intentionally left blank.]
 *  EOF
 *
 */

        package pkMobileClients;

        import java.util.ArrayList;
        import java.util.List;

        import oracle.kv.KVStore;
        import oracle.kv.KVStoreConfig;
        import oracle.kv.KVStoreFactory;
        import oracle.kv.Key;
        import oracle.kv.Value;
        import oracle.kv.ValueVersion;
        import oracle.kv.KeyValueVersion;
//import schema.KeyDefinition;
        import oracle.kv.Direction;

//pour MultiGet
        import java.util.Iterator;
        import java.util.SortedMap;
        import java.util.Map;
        import oracle.kv.ConsistencyException;
        import oracle.kv.RequestTimeoutException;

/**
 * An extremely simple KVStore client application that writes and reads a
 * single record.  It can be used to validate an installation.
 *
 * Before running this example program, start a KVStore instance.  The simplest
 * way to do that is to run KV Lite as described in the INSTALL document.  Use
 * the KVStore instance name, host and port for running this program, as
 * follows:
 *
 * <pre>
 * java schema.HelloBigDataWorld -store &lt;instance name&gt; \
 *                               -host  &lt;host name&gt;     \
 *                               -port  &lt;port number&gt;
 * </pre>
 *
 * For all examples the default instance name is kvstore, the default host name
 * is localhost and the default port number is 5000.  These defaults match the
 * defaults for running kvlite, so the simplest way to run the examples along
 * with kvlite is to omit all parameters.
 */
public class MobileClients {

    private final KVStore store;

    /**
     * Runs the HelloBigDataWorld command line program.
     */
    public static void main(String args[]) {
        try {
            MobileClients mobileCli = new MobileClients(args);
            mobileCli.runMobileClients();
            mobileCli.printAllClientInfo();
            List<String> majorPath= new ArrayList<String>();
            majorPath.add("Smith");
            majorPath.add("Bob");
            majorPath.add("bob.Smith@gmail.com");
            mobileCli.mutliGetClient(majorPath);
            mobileCli.clientMultiGetIterator(majorPath);
            // mobileCli.getClient(key...);
            // mobileCli.deleteClient(key ...);
            // mobileCli.mutliGetClient();
            // mobileCli.printAllClientInfo();
            // mobileCli.clientMultiGetIterator();
            mobileCli.closeStore();

        } catch (RuntimeException e) {
            System.out.println("\n des soucis graves\n");
            e.printStackTrace();
        }
    }

    /**
     * Parses command line args and opens the KVStore.
     */
    MobileClients(String[] argv) {

        String storeName = "kvstore";
        String hostName = "localhost";
        String hostPort = "5000";

        final int nArgs = argv.length;
        int argc = 0;

        store = KVStoreFactory.getStore
                (new KVStoreConfig(storeName, hostName + ":" + hostPort));
    }

    private void usage(String message) {
        System.out.println("\n" + message + "\n");
        System.out.println("usage: " + getClass().getName());
        System.out.println("\t-store <instance name> (default: kvstore) " +
                "-host <host name> (default: localhost) " +
                "-port <port number> (default: 5000)");
        System.exit(1);
    }

    /**
     * Performs example operations and closes the KVStore.
     */
    void runExamples() {

        final String keyString = "Hello";
        final String valueString = "Big Data World Faly!";

        store.put(Key.createKey(keyString),
                Value.createValue(valueString.getBytes()));

        final ValueVersion valueVersion = store.get(Key.createKey(keyString));

        System.out.println(keyString + " " +
                new String(valueVersion.getValue().getValue()));

        //store.close();
    }

    void createClient(String lastName, String firstName, String email,
                      String minorComponent, String data) {

        List<String> majorPath = new ArrayList<String>();

        majorPath.add(lastName);
        majorPath.add(firstName);
        majorPath.add(email);
        Key myKey = Key.createKey(majorPath, minorComponent);
        Value myValue = Value.createValue(data.getBytes());
        store.put(myKey,myValue);
        final ValueVersion valueVersion = store.get(myKey);

        System.out.println(myKey + " " +
                new String(valueVersion.getValue().getValue()));


    }

    void runMobileClients() {

        createClient("Smith", "Bob", "bob.Smith@gmail.com", "adresse", "12, rue du congrès, 06000 Nice");
        createClient("Smith", "Bob", "bob.Smith@gmail.com", "appelsReçus", "0611223367, 0021206732122");
        createClient("Adam", "Judith", "judith.adam@gmail.com", "adresse", "15, rue des sauges, 06560 Valbonne");
        //store.close();

    }

    void deleteClient(String majorKey1, String majorKey2,String majorKey3, String minorKey) {
        String minorComponent=minorKey;
        // A compléter
        List<String> majorPath = new ArrayList<String>();
        majorPath.add(majorKey1);
        majorPath.add(majorKey2);
        majorPath.add(majorKey3);
        Key key = Key.createKey(majorPath, minorComponent);
        store.delete(key);
    }
    void getClient(String majorKey1, String majorKey2,String majorKey3, String minorKey) {
        List<String> majorPath = new ArrayList<String>();
        String minorComponent=minorKey;
        // A compléter);
        majorPath.add(majorKey1);
        majorPath.add(majorKey2);
        majorPath.add(majorKey3);
        Key key = Key.createKey(majorPath, minorComponent);
        // A compléter
        ValueVersion vv=store.get(key);
        Value v=vv.getValue();
        String data= new String(v.getValue());
        System.out.println("Get client: "+data);

    }
    void closeStore() {
        store.close();

    }
    //Get multi client ayant une major key
    void mutliGetClient(List<String> majorPath) {

        //List<String> majorPath = new ArrayList<String>();

        //String data;
	/*majorPath.add("Smith");
	majorPath.add("Bob");
	majorPath.add("bob.Smith@gmail.com");*/

        Key myKey = Key.createKey(majorPath); //, minorComponent);

        SortedMap<Key, ValueVersion> myRecords = null;


        myRecords = store.multiGet(myKey, null, null);

        // a compléter pour effectuer un parcours
        for (Map.Entry<Key, ValueVersion> entry : myRecords.entrySet()) {
            ValueVersion vv = entry.getValue();
            Value v = vv.getValue();
            System.out.println("Multi client:");
            // Do some work with the Value here
            System.out.println(entry.getKey()+ " " +  new String(v.toByteArray()));
        }

    }

    // Que fait cette fonction
    // A tester
    //Get all data from the database
    void printAllClientInfo() {

        System.out.println("\n\n\n**************** printAllClientInfo *********************************");

        final Iterator<KeyValueVersion> iter = store.storeIterator(Direction.UNORDERED, 0);

        while (iter.hasNext()) {
            final KeyValueVersion keyValue = iter.next();
            System.out.println(keyValue.getKey()+ " " +  new String(keyValue.getValue().toByteArray())); //.getValue());
        }
    }

    // à compléter et généraliser : par multiple connaissant une clé majeure
    void clientMultiGetIterator(List<String> majorPath) {
	/*List<String> majorPath = new ArrayList<String>();

		// à généraliser
		majorPath.add("Smith");
		majorPath.add("Bob");
		majorPath.add("bob.Smith@gmail.com");*/

        Key myKey = Key.createKey(majorPath); //, minorComponent);

        System.out.println("\n\n\n**************** clientMultiGetIterator 1 *********************************");

        Iterator<KeyValueVersion> iter = store.multiGetIterator(Direction.FORWARD, 0, myKey, null, null);

        // à compléter
        while (iter.hasNext()) {
            final KeyValueVersion keyValue = iter.next();
            System.out.println("clientMultiGetIterator:");
            System.out.println(keyValue.getKey()+ " " +  new String(keyValue.getValue().toByteArray())); //.getValue());
        }

    }


}
