package kz.enu.epam.azimkhan.tour.entity;

/**
 * User role
 */
public class Role extends Entity{

	public static final String ROLE_ADMIN = "admin";
	public static final String ROLE_CLIENT = "client";

	/**
	 * Role name
	 */
	private String rolename;

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Role role = (Role) o;

		if (!rolename.equals(role.rolename)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return rolename.hashCode();
	}
}