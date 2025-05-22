package Location;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {
	@Autowired
	LocationService locationService;
	
	@RequestMapping("/Location")
	public List<Location> getLocation() {
		return locationService.getLocationList();
	}
	@RequestMapping("/Location/{lNr}")
	public Location getLocation(@PathVariable("lNr") long lNr) {
		return locationService.getLocation(lNr);
		
	}
	@PostMapping(value="/Location")
	public void addLocation(@RequestBody Location location) {
		locationService.addLocation(location);
	}
	@PutMapping("/Location/{lNr}")
	public void updateLocation(@PathVariable("lNr")long lNr,@RequestBody Location location) {
		locationService.updateLocation(lNr, location);
	}
	@DeleteMapping("/Location/{lNr}")
	public void deleteVideo(@PathVariable("lNr")long lNr) {
		locationService.deleteLocation(lNr);
	}
}
