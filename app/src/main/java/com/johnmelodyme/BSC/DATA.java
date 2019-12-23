package com.johnmelodyme.BSC;
/**
 * @DEVELOPER: JOHN MELODY MELISSA
 * @PROJECT_NAME : BLOOD SUGAR CONTROL REGISTRATION
 * @DATE_COMPLETED : 23/12/2019
 * @CLASS_NAME : DATA
 */
public class DATA {
    private String NAME;
    private String EMAIL;
    private String PASSWORD;


    public DATA(){
    }

    public DATA(String NAME, String EMAIL, String PASSWORD){
        this.NAME = NAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
    }

    public String getNAME(){
        return NAME;
    }
    public void setNAME(String NAME){
        this.NAME = NAME;
    }

    public String getEMAIL(){
        return EMAIL;
    }
    public void setEMAIL(String EMAIL){
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD(){
        return PASSWORD;
    }
    public void setPASSWORD(String PASSWORD){
        this.PASSWORD = PASSWORD;
    }
}
