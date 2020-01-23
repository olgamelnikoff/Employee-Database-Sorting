import java.util.ArrayList;

public class BinarySearchTree {
	private static class TreeNode {
		
		private Employee employee;
		private TreeNode leftLink;
		private TreeNode rightLink;
		
		public TreeNode (Employee newEmployee, TreeNode newLeftLink, TreeNode newRightLink) {
			employee = newEmployee;
			leftLink = newLeftLink;
			rightLink = newRightLink;
		}
	}//End of IntTreeNode inner class
	
	private static TreeNode root;
	private Employee employee;
	
	public BinarySearchTree () {
		root = null;
	}
	
	public TreeNode getRoot () {
		return root;
	}
	
	public void add (Employee newEmployee) {
		root = insertInSubtree (newEmployee, root);
	}
	
	public void delete (Employee oldEmployee) {
		root = deleteNode (root, oldEmployee);
	}
	
	public void showElements () {
		showElementsInSubtree (root);
	}
	
	/**
	 Returns the root node of a tree that is the tree with root node
	 subTreeRoot, but with a new node added that contains item.
	 */
	private TreeNode insertInSubtree (Employee newEmployee, TreeNode subTreeRoot) {
		if (subTreeRoot == null) {
			return new TreeNode (newEmployee, null, null);
		}
		
		else if (newEmployee.getSIN() < subTreeRoot.employee.getSIN()) {
			subTreeRoot.leftLink = insertInSubtree (newEmployee, subTreeRoot.leftLink);
			return subTreeRoot;
		}
		
		//item >= subTreeRoot.data
		else {
			subTreeRoot.rightLink = insertInSubtree (newEmployee, subTreeRoot.rightLink);
			return subTreeRoot;
		}
	}
	
	private static boolean isInSubtree (int key2, TreeNode subTreeRoot) {
		if (subTreeRoot == null) {
			return false;
		}
		
		else if (subTreeRoot.employee.getSIN() == key2) {
			return true;
		}
		
		else if (key2 < subTreeRoot.employee.getSIN()) {
			return isInSubtree (key2,subTreeRoot.leftLink);
		}
		
		//item >= link.data
		else {
			return isInSubtree (key2, subTreeRoot.rightLink);
		}
	}
	
	public static boolean contains(int item) {
		return isInSubtree(item, root);
	}
	
	private static void showElementsInSubtree (TreeNode subTreeRoot) {
		//Uses inorder traversal
		
		if (subTreeRoot != null) {
			showElementsInSubtree (subTreeRoot.leftLink);
			System.out.println(subTreeRoot.employee.getSIN() + " " + subTreeRoot.employee.getEmployeeId() + " " 
					+ subTreeRoot.employee.getName() + " " + subTreeRoot.employee.getDepartment() + " "
					+ subTreeRoot.employee.getAddress() + " " + subTreeRoot.employee.getSalary());
			showElementsInSubtree (subTreeRoot.rightLink);
		}
		//else do nothing. Empty tree has nothing to display.
	}
	
	public static Employee returnEmployeeEntry (int sin, TreeNode subTreeRoot) {
		if (isInSubtree(sin, subTreeRoot)) {
			if (subTreeRoot.employee.getSIN() == sin) {
				return (subTreeRoot.employee);
			}
			
			else if (sin < subTreeRoot.employee.getSIN()) {
				return returnEmployeeEntry (sin, subTreeRoot.leftLink);
			}
			else {
				return returnEmployeeEntry (sin, subTreeRoot.rightLink);
			}
		}
		
		else {
			return null;
		}
	}
	
	public static TreeNode minimumElement(TreeNode root) {
		if (root.leftLink == null)
			return root;
		else {
			return minimumElement(root.leftLink);
		}
	}
	
	public static TreeNode deleteNode(TreeNode root, Employee value) {
		if (root == null)
			return null;
		if (root.employee.getSIN() > value.getSIN()) {
			root.leftLink = deleteNode(root.leftLink, value);
		} else if (root.employee.getSIN() < value.getSIN()) {
			root.rightLink = deleteNode(root.rightLink, value);
 
		} else {
			// if nodeToBeDeleted have both children
			if (root.leftLink != null && root.rightLink != null) {
				TreeNode temp = root;
				// Finding minimum element from right
				TreeNode minNodeForRight = minimumElement(temp.rightLink);
				// Replacing current node with minimum node from right subtree
				root.employee.setSIN (minNodeForRight.employee.getSIN());
				// Deleting minimum node from right now
				deleteNode(root.rightLink, minNodeForRight.employee);
 
			}
			// if nodeToBeDeleted has only left child
			else if (root.leftLink != null) {
				root = root.leftLink;
			}
			// if nodeToBeDeleted has only right child
			else if (root.rightLink != null) {
				root = root.rightLink;
			}
			// if nodeToBeDeleted do not have child (Leaf node)
			else
				root = null;
		}
		return root;
	}
}