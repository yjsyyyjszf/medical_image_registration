package medicalimagebrowser.entity;

/*import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
*/
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany
    private List<Permission> permissions;

    @Column(unique = true)
    private String name;

    private String displayName;
    
    private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", permissions=" + permissions + ", name=" + name + ", displayName=" + displayName
				+ ", description=" + description + "]";
	}

    
}
