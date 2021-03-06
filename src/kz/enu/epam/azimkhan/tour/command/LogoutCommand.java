package kz.enu.epam.azimkhan.tour.command;

import kz.enu.epam.azimkhan.tour.entity.User;
import kz.enu.epam.azimkhan.tour.exception.CommandException;
import kz.enu.epam.azimkhan.tour.logic.authentication.AuthenticationLogic;
import kz.enu.epam.azimkhan.tour.resource.PathManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Logout command
 */
public class LogoutCommand extends ActionCommand{

    private final Logger logger = Logger.getRootLogger();

    /**
     * Everyone allowed to logout
     * @param user can be null
     * @return
     */
    @Override
    public boolean checkAccess(User user) {
        return true;
    }

    /**
     * Execute logout
     * @param request request to read the command from
     * @param response
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        User user = (User) request.getSession().getAttribute(AuthenticationLogic.SESSION_VAR);
        if (user != null){
            AuthenticationLogic.logout(request.getSession());
            logger.info("Logged out: " + user.getUsername());
        }
        return pathManager.getString("path.page.main");
    }
}
