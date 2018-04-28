package autobazar;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "car_model")
@NoArgsConstructor
@Getter @Setter
@ToString

public class CarModel extends BaseEntity {
	
	@Column(name = "model_title", length = 1000)
	private String modelTitle;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "color_id")
	private CarColor carColor ;
		
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "engine_capasity_id")
	private CarEngineCapacity carEngineCapacities;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fuel_tupe_id")
	private CarFuelType carFuelTypes;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "car_make_id")
	private CarMake carMake;
	

}
