package autobazar;

import java.math.BigDecimal;
import java.sql.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleUnaryOperator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory("mysql");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		
		
		em.getTransaction().begin();
		
		
		String[] arrayCarColor = {"WHITE", "BLUE", "BLACK", "RED", "YELLOW", "GREY", "GREEN"};
			for(int i = 0; i < arrayCarColor.length; i++) {
			CarColor carColor = new CarColor();
			carColor.setColor(arrayCarColor[i]);
			em.persist(carColor);
		}
			
		double[] arrayEngineCapasity = {1.4, 2.5, 1.6, 2.0, 0.0, 1.3, 1.8};
			for (int j=0; j<arrayEngineCapasity.length; j++) {
				CarEngineCapacity carEngineCapacity = new CarEngineCapacity();
				carEngineCapacity.setEngineCapacity(BigDecimal.valueOf(arrayEngineCapasity[j]));
				em.persist(carEngineCapacity);
			}
		
		String[] arrayFuelType = {"DISEL","PETROL","DISEL","ELECTRIC","PETROL","PETROL","DISEL"}; 
			for(int l = 0; l < arrayFuelType.length; l++ ){
				CarFuelType carFuelType = new CarFuelType();
				carFuelType.setFuelType(arrayFuelType[l]);
				em.persist(carFuelType);
			}
	
		String[] arrayCarMake = {"NISAN", "AUDI", "VOLKSWAGEN", "MERCEDES", "SUBARU", "PORSHE", "LADA"};
			int[] arrayManufactureYear = {1980, 2010, 2012, 2009, 2008, 1996, 1986};
			for(int k = 0; k < arrayCarMake.length; k++) {
				CarMake carMake = new CarMake();
				carMake.setMakeTitle(arrayCarMake[k]);
				carMake.setManufactureYear(arrayManufactureYear[k]);
				em.persist(carMake);
			}
		
		
		String[] arrayCarModel = {"QUASHCAJ","A4","PASSAT","C100","FORESTER","PANAMERA","KALINA"};
		List<CarColor> carColor = em.createQuery("SELECT cc FROM CarColor cc ", CarColor.class).getResultList();
		List<CarFuelType> carFuelTypes = em.createQuery("SELECT ft FROM CarFuelType ft", CarFuelType.class).getResultList();
		List<CarMake> carMake = em.createQuery("SELECT cm FROM CarMake cm",CarMake.class).getResultList();
		List<CarEngineCapacity> carEngineCapacity = em.createQuery("SELECT ec FROM CarEngineCapacity ec",CarEngineCapacity.class).getResultList();
		for(int d = 0; d < arrayCarModel.length; d++) {
			CarModel carModel = new CarModel();
			carModel.setModelTitle(arrayCarModel[d]);
			carModel.setCarEngineCapacities(carEngineCapacity.get(new Random().nextInt(7)));
			carModel.setCarColor(carColor.get(new Random().nextInt(7)));
			carModel.setCarFuelTypes(carFuelTypes.get(new Random().nextInt(7)));
			carModel.setCarMake(carMake.get(new Random().nextInt(7)));
			em.persist(carModel);
		}
		

		
		CarSeller carSeller = new CarSeller();
		carSeller.setFirstName("Ivan");
		carSeller.setLastName("Ivanov");
		carSeller.setAge(35);
		carSeller.setPhoneNumber("0504445566");
		em.persist(carSeller);
		
		CarSeller carSeller2 = new CarSeller();
		carSeller2.setFirstName("Petro");
		carSeller2.setLastName("Petrov");
		carSeller2.setAge(35);
		carSeller2.setPhoneNumber("0504446655");
		em.persist(carSeller2);
		
		CarSeller carSeller3 = new CarSeller();
		carSeller3.setFirstName("Jon");
		carSeller3.setLastName("Jonson");
		carSeller3.setAge(35);
		carSeller3.setPhoneNumber("0505453453");
		em.persist(carSeller3);
		
		
		double[] arrayCarPrice = {1234.59, 4568.45, 45896.45, 78899.45, 88994.25, 66998.12, 336642.14 };
		List<CarSeller> carSellerL = em.createQuery("SELECT cs FROM CarSeller cs", CarSeller.class).getResultList();
		List<CarModel> carModel = em.createQuery("SELECT cm FROM CarModel cm", CarModel.class).getResultList();
		for(int g = 0; g < arrayCarPrice.length; g++) {
			Car car = new Car();
			car.setSelfPrice(BigDecimal.valueOf(arrayCarPrice[g]));
			car.setCarMake(carMake.get(new Random().nextInt(7)));
			car.setCarSeller(carSellerL);
			em.persist(car);
		}
	
//select		

		carColor.forEach(cc->System.out.println(cc));
		
		em.createQuery("SELECT c FROM CarMake c WHERE "
				+ "c.id >= ?1 ", CarMake.class).setParameter(1, 2).getResultList().forEach(System.out::println); 
		
		System.out.println(em.createQuery("SELECT c FROM Car c WHERE c.id = :id", Car.class).setParameter("id", 3).getSingleResult());
		
		em.createQuery("SELECT ft FROM CarFuelType ft WHERE fuel_type LIKE '%PET%' and ft.id>=?1", CarFuelType.class)
		.setParameter(1, 3).getResultList().forEach(System.out::println);
		
		em.createQuery("SELECT cm FROM CarMake cm WHERE manufacture_year BETWEEN 2000 and 2010", CarMake.class)
		.getResultList().forEach(System.out::println);
		
		em.createQuery("SELECT cc FROM CarEngineCapacity cc WHERE engineCapacity IN (1.3, 1.8, 2.5)", CarEngineCapacity.class).getResultList().forEach(System.out::println);
		
		em.createQuery("SELECT cm FROM CarModel cm JOIN cm.carColor cc WHERE cc.id=5",CarModel.class).getResultList().forEach(c -> {
			System.out.println(c.toString());
			System.out.println(c.getCarColor());
		});
		
		em.createQuery("SELECT c FROM Car c JOIN c.carMake ORDER BY c.selfPrice DESC", Car.class).getResultList().forEach(System.out::println);
		
		em.createQuery("SELECT cm FROM CarModel cm JOIN cm.carEngineCapacities ec WHERE ec.id=3", CarModel.class)
		.getResultList().forEach(c -> {
			System.out.println(c.toString());
			System.out.println(c.getCarEngineCapacities());
		});
		
		em.createQuery("SELECT cm FROM CarModel cm JOIN cm.carFuelTypes ft WHERE ft.fuelType=?1", CarModel.class)
		.setParameter(1, "ELECTRIC")	
		.getResultList().forEach(c -> {
			System.out.println(c.toString());
			System.out.println(c.getCarEngineCapacities());
		});

		
		
		em.getTransaction().commit();
		
		em.close();
		entityManagerFactory.close();

	}

}
