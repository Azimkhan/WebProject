package kz.enu.epam.azimkhan.tour.servlet;

import kz.enu.epam.azimkhan.tour.command.ActionCommand;
import kz.enu.epam.azimkhan.tour.exception.CommandException;
import kz.enu.epam.azimkhan.tour.helper.RequestHelper;
import kz.enu.epam.azimkhan.tour.resource.PathManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.MissingResourceException;

/**
 * Front Controller
 */
public class FrontController extends HttpServlet {

    private final RequestHelper requestHelper = RequestHelper.INSTANCE;
	private final Logger logger = Logger.getRootLogger();
	private String errorPagePath;

	@Override
	public void init() throws ServletException {
		try{
			errorPagePath = PathManager.INSTANCE.getString("path.error500");
		} catch (MissingResourceException e){
			logger.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Process any incoming request
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionCommand command = requestHelper.getCommand(request);

        try{
            logger.info("Command: " + command.getClass().getSimpleName());
            String page = command.execute(request, response);
            if (page != null){
                request.getRequestDispatcher(page).forward(request, response);
            }
        } catch (CommandException e) {
			if (errorPagePath != null){
				request.getRequestDispatcher(errorPagePath).forward(request, response);
			}

			logger.error(e.getMessage(), e);
        }
    }

}
