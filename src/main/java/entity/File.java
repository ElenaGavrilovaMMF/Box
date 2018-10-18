package entity;

import com.box.sdk.BoxItem;

public class File {
    private String id;
    private String name;
    private BoxItem.Info item;

    public File() {
    }

    public File(BoxItem.Info item) {
        this.id = item.getID();
        this.name = item.getName();
        this.item = item;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BoxItem.Info getItem() {
        return item;
    }
}
