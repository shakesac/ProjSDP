package pt.ual.sdp.pl3.StockManagement.views;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import pt.ual.sdp.pl3.StockManagement.models.Delivery;
import pt.ual.sdp.pl3.StockManagement.models.Item;
import pt.ual.sdp.pl3.StockManagement.service.DeliveriesService;
import pt.ual.sdp.pl3.StockManagement.service.ItemsService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Iterator;

@Path("/deliveries")
public class DeliveriesResource {
    DeliveriesService deliveryService = new DeliveriesService();
    ItemsService itemServ = new ItemsService();
/*
    @GET
    @Produces("application/json")
    public Response getDeliveries() throws Exception {
        List<Delivery> depositsList = deliveryService.getAllDeliveries();
        deliveryService.getAllDeliveries();
        if (!depositsList.isEmpty()) return Response.ok(depositsList).build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getDelivery(@PathParam("id") int id) throws SQLException {
        Delivery delivery = deliveryService.getDelivery(id);
        if (delivery.getId() != 0) return Response.ok(delivery).build();
        else return Response.status(Response.Status.NOT_FOUND).build();
    }
*/
    @POST
    @Path("/new")
    //@Consumes("application/json")
    public Response newDelivery(String values) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(values);
        JsonNode addrNode = rootNode.path("address");
        JsonNode itemNode = rootNode.path("items");
        Iterator<JsonNode> items = itemNode.elements();
        HashMap<Item,Integer> itemsMap = new HashMap<Item, Integer>();
        while (items.hasNext()){
            JsonNode item = items.next();
            JsonNode itemId = item.path("id");
            JsonNode itemQt = item.path("qty");
            Item itemObj = itemServ.getItem(itemId.asInt());
            itemsMap.put(itemObj, itemQt.asInt());
        }
        Delivery delivery = new Delivery(addrNode.asText(), itemsMap);
        boolean confirm = deliveryService.createDelivery(delivery);
        if (confirm) return Response.ok().status(Response.Status.CREATED).build();
        else return Response.notModified().build();
    }

}
