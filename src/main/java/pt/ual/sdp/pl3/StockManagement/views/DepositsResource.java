package pt.ual.sdp.pl3.StockManagement.views;

import com.fasterxml.jackson.databind.ObjectMapper;
import pt.ual.sdp.pl3.StockManagement.models.Deposit;
import pt.ual.sdp.pl3.StockManagement.service.DepositsService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/deposits")
public class DepositsResource {
    DepositsService depositService = new DepositsService();

    @GET
    @Produces("application/json")
    public Response getDeposits() throws Exception {
        List<Deposit> depositsList = depositService.getAllDeposits();
        depositService.getAllDeposits();
        if (!depositsList.isEmpty()) return Response.ok(depositsList).build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getDeposit(@PathParam("id") int id) throws SQLException {
        Deposit deposit = depositService.getDeposit(id);
        if (deposit.getId() != 0) return Response.ok(deposit).build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/new")
    //@Consumes("application/json")
    public Response newDeposit(String values) throws Exception {
        Deposit deposit = new ObjectMapper().readValue(values, Deposit.class);
        boolean confirm = depositService.createDeposit(deposit);
        if (confirm) return Response.ok().status(Response.Status.CREATED).build();
        else return Response.notModified().build();
    }

    @GET
    @Path("/stock/{id}")
    public Response getItemStock(@PathParam("id") int id) throws SQLException {
        int stock = depositService.getItemStock(id);
        return Response.ok(stock).build();
    }


}
