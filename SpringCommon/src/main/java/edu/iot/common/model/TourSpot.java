package edu.iot.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TourSpot {
	private long tourSpotId;
	private long routeId;
	private String title;
	private String brief;
	
	public TourSpot(String title) {
		this.title = title;
	}
	
	/*public String getBrief() {
		if(brief.length() < 16) {
			return brief + "<br><br><br>";
		} else {
			return brief + "<br>";
		}
	}*/
}
