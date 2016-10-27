/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import MODEL.Cliente;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author User
 */

    public interface ClienteRepository extends MongoRepository<Cliente, String> {

}