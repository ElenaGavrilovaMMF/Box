package repository;

import entity.Folder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FolderRepository {
    private static FolderRepository INSTANCE = new FolderRepository();

    private Map<String, Folder> allFolder = new HashMap<>();

    public Map<String, Folder> getAllFolder() {
        return allFolder;
    }

    public Folder getFolder(String id) {
        return allFolder.get(id);
    }

    public void addFolder(Folder item) {
        allFolder.put(item.getId(), item);
    }

    public static FolderRepository getInstance() {
        return INSTANCE;
    }
}
