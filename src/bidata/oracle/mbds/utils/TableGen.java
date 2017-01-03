package bidata.oracle.mbds.utils;

import oracle.kv.KVStore;
import oracle.kv.KVStoreConfig;
import oracle.kv.KVStoreFactory;
import oracle.kv.table.PrimaryKey;
import oracle.kv.table.Row;
import oracle.kv.table.Table;
import oracle.kv.table.TableAPI;

/**
 * Created by Faliherizo on 29/12/2016.
 */
public class TableGen {
    private KVStore store;
    private TableAPI tableH;
    public TableGen(){}
    public TableGen(KVStore store){
        this.store = store;
        tableH = store.getTableAPI();
    }
    public void createTableWithChild(){
        Table myTable = tableH.getTable("myTable");
        // Get a Row instance
        Row row = myTable.createRow();
        // Now put all of the cells in the row.
        row.put("itemCategory", "Bolts");
        row.put("description", "Metric & US sizes");
        // Now write the table row to the store.
        tableH.put(row, null, null);
        // Now populate the corresponding child table
        Table myChildTable = tableH.getTable("myTable.myChildTable");
        // Get a row instance
        Row childRow = myChildTable.createRow();
        // Populate the rows. Because the parent table's "itemCategory"
        //  field is a primary key, this must be populated in addition
        // to all of the child table's rows
        childRow.put("itemCategory", "Bolts");
        childRow.put("itemSKU", "1392610");
        childRow.put("itemDescription", "1/4-20 x 1/2 Grade 8 Hex");
        childRow.put("price", new Float(11.99));
        childRow.put("inventoryCount", 1457);

        tableH.putIfAbsent(childRow,null,null);
    }

    public void createTable(String name){
        Table myTable = tableH.getTable(name);
        Row row = myTable.createRow();

        // Now put all of the cells in the row.
        //row.put("itemCategory", "Bolts");
        //row.put("description", "Metric & US sizes");
        //PrimaryKey primaryKey = myTable.createPrimaryKey();

        //PrimaryKey primaryKey=row.createPrimaryKey();
        //primaryKey.put("item", "Bolts");

        // Now write the table row to the store.
        tableH.put(row, null, null);
    }

    public Row CreateRow(Table myTable){
        Row row = myTable.createRow();

        return row;
    }
}
