package thrr.asmr.finalproject.com.thrr;

public class FocusDTO {
	private String email;
	private String focus_time;
	private String select_ASMR;
	private String datetime;
	private double db;
	
	
	public FocusDTO(String email, String focus_time, String select_ASMR, double db) {
		this.email = email;
		this.focus_time = focus_time;
		this.select_ASMR = select_ASMR;
		this.db = db;
	}
	
	public FocusDTO(String email, String focus_time, String select_ASMR, String datetime, double db) {
		this.email = email;
		this.focus_time = focus_time;
		this.select_ASMR = select_ASMR;
		this.datetime = datetime;
		this.db = db;
	}

	public String getDatetime() {
		return datetime;
	}

	public String getEmail() {
		return email;
	}

	public String getFocus_time() {
		return focus_time;
	}

	public String getSelect_ASMR() {
		return select_ASMR;
	}

	public double getDb() {
		return db;
	}
	
	
}
