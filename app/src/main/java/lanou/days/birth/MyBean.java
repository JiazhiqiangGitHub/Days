package lanou.days.birth;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by 贾志强 on 16/12/8.
 */

public class MyBean {
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private String name;
    private String bir;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBir() {
        return bir;
    }

    public void setBir(String bir) {
        this.bir = bir;
    }
}
