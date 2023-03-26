package kodlama.io.rentacar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "models")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id") //@ManyToOne gördüğün yere @JoinColumn yaz (brand_id (foreign key olur)-> brand tablosunu ile ilişki kurar)
    private Brand brand; //Brand sınıfındaki mappedBy="brand" ile aynı isim yazılır. Burdan iki tablo eşlenir.

    @OneToMany(mappedBy = "model") //arabanın id'si(primary key) model tablosunda bulunmayacak demek (gereksiz yere foreign key oluşturmasını engeller.)
    private List<Car> cars;
}
