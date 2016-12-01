package lanou.days.birth;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/11/26.
 */

public class UserBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private int kind;
    private String name;
    private String constellation;
    private String date;
    private int monKind;
    private String month;
    private int birKind;

    public int getBirKind() {
        return birKind;
    }

    public void setBirKind(int birKind) {
        this.birKind = birKind;
    }

    public int getMonKind() {
        return monKind;
    }

    public void setMonKind(int monKind) {
        this.monKind = monKind;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
