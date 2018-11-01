package entity;

import com.box.sdk.BoxItem;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Folder {
    private String id;
    private String idParent;
    private String name;

    public Folder(BoxItem.Info item,  String idParent) {
        this.id = item.getID();
        this.name = item.getName();
        this.idParent = idParent;
    }

    public String getId() {
        return id;
    }

    public String getIdParent() {
        return idParent;
    }

    public String getName() {
        return name;
    }
}
