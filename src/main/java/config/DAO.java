/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.*;

/**
 *
 * @author 
 */
public class DAO {

    private static final AnnotationConfigApplicationContext ctx
            = new AnnotationConfigApplicationContext(DBConfig.class);
    public static VeiculoRepository veiculoRepository = ctx.getBean(VeiculoRepository.class);
    
    public static VagaRepository vagaRepository = ctx.getBean(VagaRepository.class);
    public static MovimentoRepository movimentoRepository = ctx.getBean(MovimentoRepository.class);
    public static ClienteRepository clienteRepository = ctx.getBean(ClienteRepository.class);
      public static PrecoRepository precoRepository = ctx.getBean(PrecoRepository.class);

   

}
