package co.edu.javeriana.pica.kallsonys.dal.repository;

import co.edu.javeriana.pica.kallsonys.dal.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, String> {
}
