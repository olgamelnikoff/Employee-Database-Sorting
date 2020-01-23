import java.util.ArrayList;

public class DataManagement {


    public boolean addRecord(Employee employee, ArrayList<Employee> emp, BinarySearchTree bst) {
        if ((searchHeapRecord(employee.getEmployeeId(), emp) != null) || (bst.contains(employee.getSIN()))) {
            System.out.println("Employee is already in the file");
            return false;
        } else {
            emp.add(employee);
            return true;
        }
    }
    
    public Employee searchHeapRecord(int id, ArrayList<Employee> list) { //used binary search as most efficient was to search heap
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (id == list.get(middle).getEmployeeId())
                return list.get(middle);
            else if (id < list.get(middle).getEmployeeId())
                high = middle - 1;
            else
                low = middle + 1;
        }
        return null;
    }
   
    public boolean updateRecord(Employee oldRecord, Employee newRecord, ArrayList<Employee> list) {
        int position = list.indexOf(oldRecord);
        if (position < 0) {
            System.out.println(oldRecord.getEmployeeId() + ", was not found.");
            return false;
        }
        list.set(position, newRecord);
        System.out.println(oldRecord.getEmployeeId() + " was replaced with " + newRecord.getEmployeeId());
        return true;
    }
}