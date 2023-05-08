package world.tina.bulletinboard.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "categories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Size(min =1, max = 60)
	private String name;

	@Column(name = "parent_id")
	private Long parentId;

	@OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL)
	private List<Category> children = new ArrayList<>();

	public Category(Long id, String name, Long parentId, List<Category> children) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.children = children;
	}

}
