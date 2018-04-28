package autobazar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "car_make")
@NoArgsConstructor
@Getter @Setter
@ToString(callSuper = true)

public class CarMake extends BaseEntity {
	
	@Column(unique = true, name = "make_title")
	private String makeTitle;
	
	@Column(name = "manufacture_year")
	private int manufactureYear;
	
}
