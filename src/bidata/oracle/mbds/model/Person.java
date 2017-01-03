package bidata.oracle.mbds.model;

import oracle.kv.table.Row;

import java.io.Serializable;

/**
 * Created by Faliherizo on 29/12/2016.
 */
public class Person extends Objet implements Serializable {
    private String pName;
    private String pFirstname;
    private String pAdress;

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpFirstname() {
        return pFirstname;
    }

    public void setpFirstname(String pFirstname) {
        this.pFirstname = pFirstname;
    }

    public String getpAdress() {
        return pAdress;
    }

    public void setpAdress(String pAdress) {
        this.pAdress = pAdress;
    }

    @Override
    protected void Hydratation(Row row){

    }
}
