package parser;

import action.FolderAction;
import com.box.sdk.BoxItem;
import entity.Folder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import repository.FolderRepository;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ParserFolder {

    public static void parse(BoxItem.Info itemInfo, String idParent) {
        FolderAction folderAction = new FolderAction();
        if (folderAction.isFolder(itemInfo)) {
            List<BoxItem.Info> child = folderAction.getChild(itemInfo);
            FolderRepository.getInstance().addFolder(new Folder(itemInfo, idParent));
            for (BoxItem.Info info : child) {
                parse(info, itemInfo.getID());
            }
        }
    }
}

