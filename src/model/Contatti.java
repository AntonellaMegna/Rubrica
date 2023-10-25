package model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
 

@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter 
 

@Entity
@Table(name = "contatti")
public class Contatti {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
      private long contattiId;
	  private String first_name;
	  private String last_name;
	  private String email;
	  private String cell1;
	  
	  
	public Contatti(String first_name, String last_name, String email, String cell1) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.cell1 = cell1;
	}
	  
	  
	  
}
 
 