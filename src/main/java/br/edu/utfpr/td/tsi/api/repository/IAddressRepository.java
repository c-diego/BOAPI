package br.edu.utfpr.td.tsi.api.repository;

import br.edu.utfpr.td.tsi.api.model.Address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, String> {
    
    Address findByIdentification(String identification);

}
