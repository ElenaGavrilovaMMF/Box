package action;

import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class FolderAction {
    private static final int LENGTH_ID_FOLDER = 11;

    private  final ArrayList<BoxFolder> BOX_FOLDERS= new ArrayList<>();

    public ArrayList<BoxItem.Info> getChild(BoxFolder folder) {
        ArrayList<BoxItem.Info> childFolder = new ArrayList<>();
        for (BoxItem.Info info : folder) {
            System.out.format("[%s] %s\n", info.getID(), info.getName());
            childFolder.add(info);
        }
        return childFolder;
    }

    public Boolean isFolder(BoxItem.Info itemInfo) {
        return itemInfo.getID().length() == LENGTH_ID_FOLDER;
    }

    public void addBoxFolder(BoxFolder boxFolders) {
        BOX_FOLDERS.add(boxFolders);
    }

    public ArrayList<BoxFolder> getListFolders() {
        return BOX_FOLDERS;
    }
}
