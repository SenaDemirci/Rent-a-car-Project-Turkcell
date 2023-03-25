package kodlama.io.rentacar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Concrete class
//Lombok
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //serial olarak (oto) id yi artırarak ekler.
    //@Column(name = "ID")
    private int id; //Primary Key @Id sayesinde
    private String name;

    //@NoArgsConstructor
    /*
    public Brand() {
    }
     */

    //@AllArgsConstructor eklediğimiz için gerek kalmadı. İkisi aynı şey
    /*
    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }
    */

    //@Getter @Setter
    /*
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    */

}
