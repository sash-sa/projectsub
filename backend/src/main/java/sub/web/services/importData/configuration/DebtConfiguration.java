package sub.web.services.importData.configuration;

import java.io.Serializable;

public class DebtConfiguration implements Serializable {
    private Integer rowNum = 5-1;
    private String streetName = "B";
    private String nomerKvName = "C";
    private String familiName = "D";
    private String lsName = "E";
    private String summaName = "G";

    public DebtConfiguration(Integer rowNum, String streetName, String nomerKvName, String familiName, String lsName, String summaName) {
        this.rowNum = rowNum;
        this.streetName = streetName;
        this.nomerKvName = nomerKvName;
        this.familiName = familiName;
        this.lsName = lsName;
        this.summaName = summaName;
    }

    public DebtConfiguration() {
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNomerKvName() {
        return nomerKvName;
    }

    public void setNomerKvName(String nomerKvName) {
        this.nomerKvName = nomerKvName;
    }

    public String getFamiliName() {
        return familiName;
    }

    public void setFamiliName(String familiName) {
        this.familiName = familiName;
    }

    public String getLsName() {
        return lsName;
    }

    public void setLsName(String lsName) {
        this.lsName = lsName;
    }

    public String getSummaName() {
        return summaName;
    }

    public void setSummaName(String summaName) {
        this.summaName = summaName;
    }
}
