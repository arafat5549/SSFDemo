package test.demo;


public class Model {

//    private Integer id;
//
//    private String logname;//失踪人姓名
//
//    private String birthplace;//籍贯
//
//    private Integer age;
//    @JsonFormat(pattern = Globals.DATE_FORMAT)
//    private Date losttime;
//
//    private Integer logtype;
//
//    private Integer losttype;
//
//    private String avatarurl;
//
//    private String note;
//    private Integer userId;
    
	private String logname;//失踪人姓名
	private String logtype;//寻找类别
	private String gender;//性别
	private String birthplace;//失踪人籍贯
	private String birthday;//出生日期
	private String losttime;//失踪日期
	private String height;//失踪时身高
	private String lostplace;//失踪地点
	private String reward1;//提供线索酬金
	private String reward2;//护送回家酬金
	private String mobile;//联系​电话
	private String contact;//联系人
	private String note;//详细信息
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getLogtype() {
		return logtype;
	}
	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthplace() {
		return birthplace;
	}
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getLosttime() {
		return losttime;
	}
	public void setLosttime(String losttime) {
		this.losttime = losttime;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getLostplace() {
		return lostplace;
	}
	public void setLostplace(String lostplace) {
		this.lostplace = lostplace;
	}
	public String getReward1() {
		return reward1;
	}
	public void setReward1(String reward1) {
		this.reward1 = reward1;
	}
	public String getReward2() {
		return reward2;
	}
	public void setReward2(String reward2) {
		this.reward2 = reward2;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "[logname=" + logname + ", logtype=" + logtype
				+ ", gender=" + gender + ", birthplace=" + birthplace
				+ ", birthday=" + birthday + ", losttime=" + losttime
				+ ", height=" + height + ", lostplace=" + lostplace
				+ ", reward1=" + reward1 + ", reward2=" + reward2 + ", mobile="
				+ mobile + ", contact=" + contact + ", note=" + note + "]";
	}
	
	
}
