package thrr.asmr.finalproject.com.thrr;

public class SleepDTO {
	private String email;
	private String datetime;
	private String sleep_time;
	private String wakeup_time;
	private String sleep_amount;
	private String select_ASMR;
	private double db;
	private double light;
	
	
	
	public SleepDTO(String email, String sleep_time, String wakeup_time, String sleep_amount, String select_ASMR,
			double db, double light) {
		this.email = email;
		this.sleep_time = sleep_time;
		this.wakeup_time = wakeup_time;
		this.sleep_amount = sleep_amount;
		this.select_ASMR = select_ASMR;
		this.db = db;
		this.light = light;
	}
	public SleepDTO(String email, String datetime, String sleep_time, String wakeup_time, String sleep_amount,
			String select_ASMR, double db, double light) {
		this.email = email;
		this.datetime = datetime;
		this.sleep_time = sleep_time;
		this.wakeup_time = wakeup_time;
		this.sleep_amount = sleep_amount;
		this.select_ASMR = select_ASMR;
		this.db = db;
		this.light = light;
	}
	public String getEmail() {
		return email;
	}
	public String getDatetime() {
		return datetime;
	}
	public String getSleep_time() {
		return sleep_time;
	}
	public String getWakeup_time() {
		return wakeup_time;
	}
	public String getSleep_amount() {
		return sleep_amount;
	}
	public String getSelect_ASMR() {
		return select_ASMR;
	}
	public double getDb() {
		return db;
	}
	public double getLight() {
		return light;
	}
	
	
}
