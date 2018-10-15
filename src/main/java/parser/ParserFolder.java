package parser;

import action.FileAction;
import action.FolderAction;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public class ParserFolder {

    public static void parseFolder(BoxItem.Info itemInfo) {
        FolderAction folderAction = new FolderAction();
        if (folderAction.isFolder(itemInfo)) {
            BoxFolder folder = new BoxFolder(itemInfo.getResource().getAPI(), itemInfo.getID());
            folderAction.addBoxFolder(folder);
            ArrayList<BoxItem.Info> child = folderAction.getChild(folder);
            for (BoxItem.Info info : child) {
                parseFolder(info);
            }
        } else {
            BoxFile file = new BoxFile(itemInfo.getResource().getAPI(), itemInfo.getID());
            new FileAction().addBoxFile(file);
        }
    }
}
