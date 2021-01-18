package pt.ual.sdp.pl3.StockManagement.views;

import com.fasterxml.jackson.core.JsonProcessingException;
import pt.ual.sdp.pl3.StockManagement.models.Item;
import pt.ual.sdp.pl3.StockManagement.service.ItemsService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/items")
public class ItemsResource {
    ItemsService itemService = new ItemsService();

    @GET
    @Produces("application/json")
    public Response getItems() throws Exception {
        List<Item> itemList = itemService.getAllItems();
        if (!itemList.isEmpty()) return Response.ok(itemList).build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getItem(@PathParam("id") int id) throws SQLException {
        Item item = itemService.getItem(id);
        if (item.getId() != 0) return Response.ok(item).build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/new")
    //@Consumes("application/json")
    public Response newItem(String values) throws Exception {
        Item item = new ObjectMapper().readValue(values, Item.class);
        boolean confirm = itemService.createItem(item);
        if (confirm) return Response.ok().status(Response.Status.CREATED).build();
        else return Response.notModified().build();
    }

    @PUT
    @Path("/update/{id}")
    //@Consumes("application/json")
    public Response updateItem(String values) throws JsonProcessingException, SQLException {
        Item item = new ObjectMapper().readValue(values, Item.class);
        int confirm = itemService.updateItem(item);
        if (confirm == 1) return Response.ok().status(Response.Status.CREATED).build();
        else if (confirm == 0) return Response.status(Response.Status.NOT_FOUND).build();
        else return Response.notModified().build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    public Response delItem(@PathParam("id") int id) throws SQLException {
        int confirm = itemService.deleteItem(id);
        if (confirm == 1) return Response.ok().build();
        else if (confirm == 0) return Response.status(Response.Status.NOT_FOUND).build();
        else return Response.notModified().build();
    }
}