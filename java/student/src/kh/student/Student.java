package kh.student;


public class Student {
	private int no;
	private String stu_num;
	private String stu_name;
	private String stu_id;
	private String stu_pw;
	private String stu_birth;
	private String stu_phone;
	private String stu_email;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int no, String stu_num, String stu_name, String stu_id, String stu_pw, String stu_birth,
			String stu_phone, String stu_email) {
		super();
		this.no = no;
		this.stu_num = stu_num;
		this.stu_name = stu_name;
		this.stu_id = stu_id;
		this.stu_pw = stu_pw;
		this.stu_birth = stu_birth;
		this.stu_phone = stu_phone;
		this.stu_email = stu_email;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getStu_num() {
		return stu_num;
	}

	public void setStu_num(String stu_num) {
		this.stu_num = stu_num;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}

	public String getStu_pw() {
		return stu_pw;
	}

	public void setStu_pw(String stu_pw) {
		this.stu_pw = stu_pw;
	}

	public String getStu_birth() {
		return stu_birth;
	}

	public void setStu_birth(String stu_birth) {
		this.stu_birth = stu_birth;
	}

	public String getStu_phone() {
		return stu_phone;
	}

	public void setStu_phone(String stu_phone) {
		this.stu_phone = stu_phone;
	}

	public String getStu_email() {
		return stu_email;
	}

	public void setStu_email(String stu_email) {
		this.stu_email = stu_email;
	}

	@Override
	public String toString() {
		return "Student [no=" + no + ", stu_num=" + stu_num + ", stu_name=" + stu_name + ", stu_id=" + stu_id
				+ ", stu_pw=" + stu_pw + ", stu_birth=" + stu_birth + ", stu_phone=" + stu_phone + ", stu_email="
				+ stu_email + "]";
	}

}
