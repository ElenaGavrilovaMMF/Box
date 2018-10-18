package entity;

import com.box.sdk.BoxItem;

public class Folder {
    private String id;
    private String idParent;
    private String name;

    public Folder() {
    }

    public Folder(BoxItem.Info item,  String idParent) {
        this.id = item.getID();
        this.name = item.getName();
        this.idParent = idParent;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getIdParent() {
        return idParent;
    }
}
