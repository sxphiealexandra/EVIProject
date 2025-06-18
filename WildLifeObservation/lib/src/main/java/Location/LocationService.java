package Location;

import java.util.ArrayList; 
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class LocationService {
	@Autowired
	private LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }
    
    public List<Location> getLocationList() {
        List<Location> list = new ArrayList<>();
        locationRepository.findAll().forEach(list::add);
        return list;
    }
    
    public Location findByShortTitle(String shortTitle) {
        return locationRepository.findByShortTitle(shortTitle).orElse(null);
    }//test!

    public Location getLocation(long id) {
        return locationRepository.findById(id).orElse(null);
    }
    
    public void addLocation(Location location) {
        locationRepository.save(location);
    }
    
    public void updateLocation(long id, Location updatedLocation) {
    	updatedLocation.setlNr(id); 
        locationRepository.save(updatedLocation);
       
    }
    
    public void deleteLocation(long id) {
        locationRepository.deleteById(id);
    }
    
}