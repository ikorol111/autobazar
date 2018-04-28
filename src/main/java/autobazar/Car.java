package autobazar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "car")
@NoArgsConstructor
@Getter @Setter
@ToString(callSuper = true)


public class Car  extends BaseEntity {
	
	@Column(columnDefinition = "DECIMAL(10,2)")
	private BigDecimal selfPrice; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "make_id")
	private CarMake carMake;
	
	@ManyToMany
	@JoinTable(name = "seller_car", 
			joinColumns = @JoinColumn(name="car_id"),
			inverseJoinColumns = @JoinColumn(name = "seller_id"))
	private List<CarSeller> carSeller = new ArrayList<>();
	
	

}


