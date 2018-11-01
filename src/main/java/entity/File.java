package entity;

import com.box.sdk.BoxItem;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class File {
    private String id;
    private String name;

    public File(BoxItem.Info item) {
        this.id = item.getID();
        this.name = item.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
