package zheng.com;


public class ListCell {
	
	private String name;
	private String information;
	private int resourceId;
	
	public ListCell(String name,String information,int resourceId) {
		this.name = name;
		this.information = information;
		this.resourceId = resourceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	
}
