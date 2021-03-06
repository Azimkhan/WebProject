package kz.enu.epam.azimkhan.tour.command.admin;

import kz.enu.epam.azimkhan.tour.command.AdminCommand;
import kz.enu.epam.azimkhan.tour.dao.OrderDAO;
import kz.enu.epam.azimkhan.tour.entity.Order;
import kz.enu.epam.azimkhan.tour.exception.CommandException;
import kz.enu.epam.azimkhan.tour.exception.DAOLogicalException;
import kz.enu.epam.azimkhan.tour.exception.DAOTechnicalException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
public class OrdersCommand extends AdminCommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        List<Order> orders = null;

        OrderDAO dao = OrderDAO.getInstance();
        if (request.getParameter("id") != null && request.getParameter("paid") != null){
            try{
                int id = Integer.parseInt(request.getParameter("id"));
                boolean paid = Boolean.parseBoolean(request.getParameter("paid"));
                dao.updatePaid(id, paid);
            } catch (DAOTechnicalException e) {
                throw new CommandException(e);
            } catch (DAOLogicalException e) {
                throw new CommandException(e);
            } catch (NumberFormatException e){
                throw new CommandException(e);
            }
        }
        try {

            orders = dao.findAll();

            request.setAttribute("orders", orders);
        } catch (DAOLogicalException e) {
            throw new CommandException(e);
        } catch (DAOTechnicalException e) {
            throw new CommandException(e);
        }

        return pathManager.getString("path.page.admin.orders");
    }
}
