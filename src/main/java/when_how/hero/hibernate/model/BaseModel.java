package when_how.hero.hibernate.model;


public abstract class BaseModel implements IModel {

	private static final long serialVersionUID = 1L;
	
	protected String id;

	public final void setId(String id) {
		if(isNew()) {
		    this.id = id;
		}
	}
	
	public abstract String getId();
	
	public boolean isNew() {
		return id == null;
	}
	
	public abstract int hashCode();

	public abstract boolean equals(Object object);
	
	public abstract String toString();
}
