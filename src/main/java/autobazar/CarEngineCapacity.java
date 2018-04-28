package autobazar;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "car_engine_capacity")
@NoArgsConstructor
@Getter @Setter
@ToString

public class CarEngineCapacity extends BaseEntity{
	
	@Column(columnDefinition = "DECIMAL(3,1)")
	private BigDecimal engineCapacity;

}
