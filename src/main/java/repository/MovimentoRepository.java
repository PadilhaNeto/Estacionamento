/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import MODEL.Movimento;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author User
 */

    public interface MovimentoRepository extends MongoRepository<Movimento, String> {

}