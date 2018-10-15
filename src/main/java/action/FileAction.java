package action;

import com.box.sdk.BoxFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FileAction {
    private final ArrayList<BoxFile> BOX_FILES = new ArrayList();

    public void addBoxFile(BoxFile boxFile) {
        BOX_FILES.add(boxFile);
    }

    public ArrayList<BoxFile> getListFiles() {
        return BOX_FILES;
    }

}
