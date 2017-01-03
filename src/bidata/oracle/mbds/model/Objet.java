package bidata.oracle.mbds.model;

import oracle.kv.table.Row;

/**
 * Created by Faliherizo on 03/01/2017.
 */
public class Objet {
    private String _id;
    protected void Hydratation(Row row){
        this._id=row.get("id").asString().get();
    }
}
