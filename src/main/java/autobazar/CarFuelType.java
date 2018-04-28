package autobazar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "car_fuel_type")
@NoArgsConstructor
@Getter @Setter
@ToString(callSuper = true)

public class CarFuelType extends BaseEntity {
	
	@Column(name = "fuel_type", length = 20)
	private String fuelType;

}
