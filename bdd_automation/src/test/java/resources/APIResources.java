package resources;

//enum is a special class in java which has collection of constants
public enum APIResources {

	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String res;
	
	APIResources(String resource) {
		this.res = resource;
	}
	
	public String getResource() {
		return res;
	}
	
}
