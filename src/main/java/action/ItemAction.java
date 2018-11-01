package action;

import com.box.sdk.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import repository.FolderRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@NoArgsConstructor
public class ItemAction {
    private static final int LIMIT = 200;
    private static final int OFFSET = 0;
    private static final String REGEX = "(\\s)*";

    public PartialCollection<BoxItem.Info> searchItem(HttpServletRequest req, String searchTerm, String type) {
        BoxSearchParameters bsp = new BoxSearchParameters();
        if (isEmptySearch(type)) {
            bsp.setType(type);
        }
        PartialCollection<BoxItem.Info> resultSearch;
        if (isEmptySearch(req.getParameter("searchTerm"))) {
            bsp.setQuery(searchTerm);
            BoxSearch bs = new BoxSearch((BoxAPIConnection) req.getSession().getAttribute("api"));
            resultSearch = bs.searchRange(OFFSET, LIMIT, bsp);
        } else {
            resultSearch = null;
        }
        return resultSearch;
    }

    public String getLink(BoxItem.Info item) {
        BoxSharedLink.Access access = BoxSharedLink.Access.OPEN;
        BoxSharedLink.Permissions permissions = new BoxSharedLink.Permissions();
        permissions.setCanDownload(true);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 15);
        Date unshareDate = c.getTime();
        BoxSharedLink sharedLink;
        if (FolderRepository.getInstance().getFolder(item.getID()) != null) {
            sharedLink = new BoxFolder(item.getResource().getAPI(), item.getID()).getInfo().getResource().createSharedLink(access, unshareDate, permissions);

        } else {
            sharedLink = new BoxFile(item.getResource().getAPI(), item.getID()).getInfo().getResource().createSharedLink(access, unshareDate, permissions);
        }
        return sharedLink.getURL();
    }

    private boolean isEmptySearch(String testString){
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(testString);
        return !m.matches();
    }
}
