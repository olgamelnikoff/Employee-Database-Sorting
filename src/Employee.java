public class Employee {

    private int employeeId;
    private int SIN;
    private String name;
    private String department;
    private String address;
    private int salary;

    public Employee(int employeeId, int SIN, String name, String department, String address, int salary) {
        this.employeeId = employeeId;
        this.SIN = SIN;
        this.name = name;
        this.department = department;
        this.address = address;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getSIN() {
        return SIN;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getAddress() {
        return address;
    }

    public int getSalary() {
        return salary;
    }
    
    public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setSIN(int sIN) {
		SIN = sIN;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
    public String toString() {
        return ("\n" + this.getEmployeeId() +
                " " + this.getSIN() +
                " " + this.getName() +
                " " + this.getDepartment() +
                " " + this.getAddress() +
                " " + this.getSalary());
    }
}