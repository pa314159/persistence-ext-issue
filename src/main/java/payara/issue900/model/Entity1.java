
package payara.issue900.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Entity1
{

	@Id
	private Long id;

	private String text;
}
