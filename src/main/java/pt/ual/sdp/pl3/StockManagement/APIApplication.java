package pt.ual.sdp.pl3.StockManagement;

import pt.ual.sdp.pl3.StockManagement.views.DeliveriesResource;
import pt.ual.sdp.pl3.StockManagement.views.DepositsResource;
import pt.ual.sdp.pl3.StockManagement.views.ItemsResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class APIApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> set = new HashSet<>();
        set.add(ItemsResource.class);
        set.add(DepositsResource.class);
        set.add(DeliveriesResource.class);
        return set;
    }
}
