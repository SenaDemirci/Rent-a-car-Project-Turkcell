package kodlama.io.rentacar.repository;

import kodlama.io.rentacar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// interfaceler kendi tipinde olan interfaceleri extend ederler.
// <Brand,Integer> (<> generic yapı) => <T,ID> (id kısmına brand classındaki id nin tipi yazılır. int kabul etmez, sınıfı olan Integer kullanılır.)
//@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {
    //Custom queries
    boolean existsByNameIgnoreCase(String name); //arkaplanda zaten olan bir fonk

    //JpaRepository yapınca gerek kalmadı.
    /*
    List<Brand> getAll();
    Brand getById(int id);
    Brand add(Brand brand);
    Brand update(int id, Brand brand);
    void delete(int id);
    */
}
