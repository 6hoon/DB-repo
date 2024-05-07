package kh.student;

import java.sql.Date;

public class Student {
	private int no;
	private String sd_num;
	private String sd_name;
	private String sd_id;
	private String sd_pw;
	private String sd_birth;
	private String sd_phone;
	private String sd_email;
	private Date sd_date;

	public Student(int no, String sd_num, String sd_name, String sd_id, String sd_pw, String sd_birth, String sd_phone,
			String sd_email, Date sd_date) {
		super();
		this.no = no;
		this.sd_num = sd_num;
		this.sd_name = sd_name;
		this.sd_id = sd_id;
		this.sd_pw = sd_pw;
		this.sd_birth = sd_birth;
		this.sd_phone = sd_phone;
		this.sd_email = sd_email;
		this.sd_date = sd_date;
	}

	public Student() {
		super();
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getSd_num() {
		return sd_num;
	}

	public void setSd_num(String sd_num) {
		this.sd_num = sd_num;
	}

	public String getSd_name() {
		return sd_name;
	}

	public void setSd_name(String sd_name) {
		this.sd_name = sd_name;
	}

	public String getSd_id() {
		return sd_id;
	}

	public void setSd_id(String sd_id) {
		this.sd_id = sd_id;
	}

	public String getSd_pw() {
		return sd_pw;
	}

	public void setSd_pw(String sd_pw) {
		this.sd_pw = sd_pw;
	}

	public String getSd_birth() {
		return sd_birth;
	}

	public void setSd_birth(String sd_birth) {
		this.sd_birth = sd_birth;
	}

	public String getSd_phone() {
		return sd_phone;
	}

	public void setSd_phone(String sd_phone) {
		this.sd_phone = sd_phone;
	}

	public String getSd_email() {
		return sd_email;
	}

	public void setSd_email(String sd_email) {
		this.sd_email = sd_email;
	}

	public Date getSd_date() {
		return sd_date;
	}

	public void setSd_date(Date sd_date) {
		this.sd_date = sd_date;
	}

	@Override
	public String toString() {
		return "[" + no + ", " + sd_num + ", " + sd_name + ", (id:" + sd_id + ", pw: "
				+ sd_pw + "), " + sd_birth + ", " + sd_phone + ", " + sd_email + ", 등록일:"
				+ sd_date + "]";
	}

}
