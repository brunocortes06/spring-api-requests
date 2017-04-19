package com.bruno;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bruno.api.Maplink;
import com.bruno.api.model.Address;
import com.bruno.api.model.Location;
import com.bruno.api.model.RouteSummary;
import com.bruno.service.ProcessService;

@RunWith(MockitoJUnitRunner.class)
public class ProcessServiceTest {
	
    private static final Address My_Address = new Address("Rua Marquês de Valença", "369", "São Paulo", "SP");
    private static final Location MY_ADDRESS_LOCATION = new Location("-23.558653", "-46.592364");

    private static final Address MapLink_Address = new Address("Rua Fidêncio  Ramos", "302", "São Paulo", "SP");
    private static final Location MAPLINK_LOCATION = new Location("-23.594913", "-46.685989");

    @Mock
    private Maplink api;

    @InjectMocks
    private ProcessService routeService;
    
    @Test
    public void forcedException() throws Exception {
        List<Address> locations = addressToList(My_Address, MapLink_Address);
        List<Location> positions = locationToList(MY_ADDRESS_LOCATION, MAPLINK_LOCATION);

        when(api.defineGeoLocation(My_Address.getStreetName(), My_Address.getNumber(), My_Address.getCity(),
        		My_Address.getState())).thenReturn(MY_ADDRESS_LOCATION);
        
        when(api.defineGeoLocation(MapLink_Address.getStreetName(), MapLink_Address.getNumber(), MapLink_Address.getCity(),
        		MapLink_Address.getState())).thenReturn(MAPLINK_LOCATION);

        when(api.defineRoute(positions, true)).thenThrow(new Exception("TestException"));

        try {
            routeService.processsRoute(locations, new BigDecimal(1.09), true);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Test
    public void NormalRequest() throws Exception {
        List<Location> positions = locationToList(MY_ADDRESS_LOCATION, MAPLINK_LOCATION);
        List<Address> locations = addressToList(My_Address, MapLink_Address);

        when(api.defineGeoLocation(My_Address.getStreetName(), My_Address.getNumber(), My_Address.getCity(),
        		My_Address.getState())).thenReturn(MY_ADDRESS_LOCATION);
        
        when(api.defineGeoLocation(MapLink_Address.getStreetName(), MapLink_Address.getNumber(), MapLink_Address.getCity(),
        		MapLink_Address.getState())).thenReturn(MAPLINK_LOCATION);
        
        when(api.defineRoute(positions, true)).thenReturn(new RouteSummary(1000L, 20000D, BigDecimal.valueOf(50)));
        
        routeService.processsRoute(locations, new BigDecimal(1.09), true);

        verify(api, times(1)).defineRoute(positions, true);
    }
    
    public List<Address> addressToList(Address... locations) {
        ArrayList<Address> list = new ArrayList<>();
        Collections.addAll(list, locations);
        return list;
    }

    public List<Location> locationToList(Location... positions) {
        ArrayList<Location> list = new ArrayList<>();
        Collections.addAll(list, positions);
        return list;
    }
}
