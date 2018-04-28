package autobazar;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name = "car_seller")
@NoArgsConstructor
@Getter @Setter
@ToString

public class CarSeller extends BaseEntity{
	
	@Column(name = "first_name", length = 30)
	private String firstName;
	
	@Column(name = "last_name", length = 30)
	private String lastName;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "phone_number", length = 10)
	private String phoneNumber;
	
	@ManyToMany
	@JoinTable(name = "seller_car", 
			joinColumns = @JoinColumn(name="seller_id"),
			inverseJoinColumns = @JoinColumn(name = "car_id"))
	private List<Car> car = new ArrayList<>();

}
