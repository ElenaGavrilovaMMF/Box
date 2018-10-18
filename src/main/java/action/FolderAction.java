package action;

import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import entity.Folder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class FolderAction {
    private static final int LENGTH_ID_FOLDER = 11;

    private final ArrayList<Folder> BOX_FOLDERS = new ArrayList<>();

    public List<BoxItem.Info> getChild(BoxItem.Info itemInfo) {
        BoxFolder folder = new BoxFolder(itemInfo.getResource().getAPI(), itemInfo.getID());
        ArrayList<BoxItem.Info> childFolder = new ArrayList<>();
        for (BoxItem.Info info : folder) {
            childFolder.add(info);
        }
        return childFolder;
    }


    public Boolean isFolder(BoxItem.Info itemInfo) {
        return itemInfo.getID().length() == LENGTH_ID_FOLDER;
    }

    public void addBoxFolder(Folder folders) {
        BOX_FOLDERS.add(folders);
    }

    public ArrayList<Folder> getListFolders() {
        return BOX_FOLDERS;
    }
}
