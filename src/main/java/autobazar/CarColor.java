package autobazar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "car_color")
@NoArgsConstructor
@Getter @Setter
@ToString

public class CarColor extends BaseEntity {
	
	
	@Column(name = "color")
	private String color;
	
}
