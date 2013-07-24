package kz.enu.epam.azimkhan.tour.command;

import kz.enu.epam.azimkhan.tour.command.ActionCommand;
import kz.enu.epam.azimkhan.tour.dao.TourDAO;
import kz.enu.epam.azimkhan.tour.entity.Tour;
import kz.enu.epam.azimkhan.tour.entity.User;
import kz.enu.epam.azimkhan.tour.exception.CommandException;
import kz.enu.epam.azimkhan.tour.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.tour.exception.DAOTechnicalException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
public class ViewToursCommand extends ActionCommand{
	@Override
	public boolean checkAccess(User user) {
		return true;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

		TourDAO dao = new TourDAO();
		try {
			List<Tour> tours = dao.findAll();
			request.setAttribute("tours", tours);
		} catch (DAOLogicalException e) {
			throw new CommandException(e);
		} catch (DAOTechnicalException e) {
			throw new CommandException(e);
		}

		return pathManager.getString("path.page.tours");
	}
}